package com.rayboot.airlauncher.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.balysv.material.drawable.menu.MaterialMenuView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.FakeLauncherActivity;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.widget.crouton.Crouton;
import com.rayboot.airlauncher.widget.crouton.Style;
import com.widget.GestureLock;
import com.widget.GestureLockView;

/**
 * @author rayboot
 * @from 14-8-27 10:01
 * @TODO
 */
public class AboutFragment extends BaseFragment
{
    private static AboutFragment fragment;
    @InjectView(R.id.btnSuperSetting) Button mBtnSuperSetting;
    @InjectView(R.id.tvVersion) TextView mTvVersion;
    @InjectView(R.id.vClose) MaterialMenuView mVClose;
    @InjectView(R.id.llReset) LinearLayout mLlReset;
    @InjectView(R.id.llAbout) LinearLayout mLlAbout;
    @InjectView(R.id.gestureLock) GestureLock mGestureLock;

    public static AboutFragment getInstance()
    {
        if (fragment == null)
        {
            fragment = new AboutFragment();
        }
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.inject(this, vFragment);
        mVClose.animatePressedState(MaterialMenuDrawable.IconState.X);
        mTvVersion.setText(getAppInfo());

        mGestureLock.setCorrectGesture(new int[] { 2, 8, 6, 0 });
        mGestureLock.setMode(
                GestureLockView.MODE_ERROR | GestureLockView.ARROW_BOTTOM_LEFT);
        mGestureLock.setOnGestureEventListener(
                new GestureLock.OnGestureEventListener()
                {

                    @Override
                    public void onGestureEvent(boolean matched)
                    {
                        if (matched)
                        {
                            makePrefered(getActivity());
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "输入错误",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onUnmatchedExceedBoundary()
                    {
                        Toast.makeText(getActivity(), "输入错误太多，1分钟后才能输入",
                                Toast.LENGTH_SHORT).show();
                        mGestureLock.setTouchable(false);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mGestureLock.setTouchable(true);
                            }
                        }, 60000);
                    }

                    @Override
                    public void onBlockSelected(int position)
                    {
                        Log.d("position", position + "");
                    }
                });
        return vFragment;
    }

    @OnClick({ R.id.vBg, R.id.vClose })
    public void onBgClick(View view)
    {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    @OnClick(R.id.btnSuperSetting)
    public void onSettingClick(View view)
    {
        if (mLlAbout.getVisibility() == View.VISIBLE)
        {
            mLlAbout.setVisibility(View.GONE);
            mLlReset.setVisibility(View.VISIBLE);
            mBtnSuperSetting.setText("关于");
        }
        else
        {
            mLlAbout.setVisibility(View.VISIBLE);
            mLlReset.setVisibility(View.GONE);
            mBtnSuperSetting.setText("重置启动页");
        }
    }

    long hideClickTime = 0;
    int hideClickCount = 0;

    @OnClick(R.id.tvVersion)
    public void onHideSettingClick(View view)
    {
        hideClickCount = System.currentTimeMillis() - hideClickTime < 200 ?
                hideClickCount
                        + 1 : 0;
        if (hideClickCount > 0 && hideClickCount % 4 == 0)
        {
            Crouton.makeText(getActivity(), "成功进入", Style.CONFIRM).show();
            makePrefered(getActivity());
        }
        hideClickTime = System.currentTimeMillis();
    }

    public void makePrefered(Context c)
    {
        PackageManager p = c.getPackageManager();
        ComponentName cN = new ComponentName(c, FakeLauncherActivity.class);
        p.setComponentEnabledSetting(cN,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Intent selector = new Intent(Intent.ACTION_MAIN);
        selector.addCategory(Intent.CATEGORY_HOME);
        c.startActivity(selector);

        p.setComponentEnabledSetting(cN,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private String getAppInfo()
    {
        try
        {
            String pkName = getActivity().getPackageName();
            String versionName = getActivity().getPackageManager()
                    .getPackageInfo(pkName, 0).versionName;
            return versionName;
        }
        catch (Exception e)
        {
        }
        return "v1.0.0";
    }

    public void onEvent(View view)
    {

    }
}
