package com.rayboot.airlauncher.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.widget.crouton.Crouton;
import com.rayboot.airlauncher.widget.crouton.Style;

/**
 * @author rayboot
 * @from 14-7-7 16:51
 * @TODO
 */
public class SettingActivity extends BaseActionBarActivity
{
    @InjectView(R.id.btnAbout) Button mBtnAbout;
    @InjectView(R.id.btnSuperSetting) Button mBtnSuperSetting;
    @InjectView(R.id.tvAbout) TextView mTvAbout;
    @InjectView(R.id.tvPassword) TextView mTvPassword;
    @InjectView(R.id.etPassword) EditText mEtPassword;
    @InjectView(R.id.btnPassword) Button mBtnPassword;
    @InjectView(R.id.llSuperSetting) LinearLayout mLlSuperSetting;
    @InjectView(R.id.tvVersion) TextView mTvVersion;

    long hideClickTime = 0;
    int hideClickCount = 0;



    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
        mTvVersion.setText(getAppInfo());
    }

    public void onAboutClick(View view)
    {
        mTvAbout.setVisibility(View.VISIBLE);
        mLlSuperSetting.setVisibility(View.GONE);
    }

    public void onSuperSettingClick(View view)
    {
        mTvAbout.setVisibility(View.GONE);
        mLlSuperSetting.setVisibility(View.VISIBLE);
    }

    public void onPasswordAccept(View view)
    {
        String pas = mEtPassword.getText().toString();
        if (pas.equals("timeface"))
        {
            mEtPassword.setText("");
            Crouton.makeText(this, "成功进入", Style.CONFIRM).show();
            makePrefered(this);
        }
        else
        {
            YoYo.with(Techniques.Tada).duration(700).playOn(mEtPassword);
            Crouton.makeText(this, "密码输入错误", Style.ALERT).show();
        }
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

    public void onHideSettingClick(View view)
    {
        hideClickCount = System.currentTimeMillis() - hideClickTime < 200 ?
                hideClickCount
                        + 1 : 0;
        if (hideClickCount > 0 && hideClickCount % 4 == 0)
        {
            Crouton.makeText(this, "成功进入", Style.CONFIRM).show();
            makePrefered(this);
        }
        hideClickTime = System.currentTimeMillis();

    }

    private String getAppInfo()
    {
        try
        {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionName;
            return versionName;
        }
        catch (Exception e)
        {
        }
        return "v1.0.0";
    }
}
