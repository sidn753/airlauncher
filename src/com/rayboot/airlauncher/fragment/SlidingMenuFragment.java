package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;

/**
 * @author rayboot
 * @from 14-8-19 11:07
 * @TODO
 */
public class SlidingMenuFragment extends BaseFragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sliding_menu, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
