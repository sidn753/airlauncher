package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.util.FontUtils;

/**
 * @author rayboot
 * @from 14-8-26 10:40
 * @TODO
 */
public class SlidingMenuV2Fragment extends BaseFragment
{
    @InjectView(R.id.llSlidingBg) RelativeLayout mRlSlidingBg;
    @InjectViews({ R.id.rbHome, R.id.rbMovie, R.id.rbBook, R.id.rbMusic, })
    RadioButton[] rbs;
    private static SlidingMenuV2Fragment fragment;

    public static SlidingMenuV2Fragment getInstance()
    {
        if (fragment == null)
        {
            fragment = new SlidingMenuV2Fragment();
        }
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sliding_menu_v2, null);
        ButterKnife.inject(this, view);
        FontUtils.overrideFonts(getActivity(), view);
        for (RadioButton rb : rbs)
        {
            rb.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public void onEvent(View view)
    {

    }
}
