package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.util.FontUtils;

/**
 * @author rayboot
 * @from 14-8-19 11:07
 * @TODO
 */
public class SlidingMenuFragment extends BaseFragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sliding_menu, null);
        FontUtils.overrideFonts(getActivity(), view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public void onEvent(View view)
    {

    }
}
