package com.rayboot.airlauncher.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.rayboot.airlauncher.base.BaseFragment;

/**
 * @author rayboot
 * @from 14-8-22 17:24
 * @TODO
 */
public class Util
{

    public static void addFragment(FragmentManager fragmentManager,int root,
            BaseFragment fragment)
    {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(root,fragment);
        trans.addToBackStack("");
        trans.commit();
    }
}
