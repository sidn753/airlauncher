package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;

/**
 * @author rayboot
 * @from 14-6-24 14:51
 * @TODO
 */
public class HomeMainFragment extends BaseFragment
{
    public static HomeMainFragment newInstance()
    {
        HomeMainFragment fragment = new HomeMainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_main, container, false);
        ButterKnife.inject(this, vFragment);
        return vFragment;
    }
}
