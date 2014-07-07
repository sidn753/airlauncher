package com.rayboot.airlauncher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rayboot.airlauncher.model.HomeObj;
import java.util.ArrayList;

/**
 * @author rayboot
 * @from 14-6-24 15:45
 * @TODO
 */
public class HomePagerAdapter extends FragmentPagerAdapter
{
    ArrayList<HomeObj> listData;

    public HomePagerAdapter(FragmentManager fm, ArrayList<HomeObj> list)
    {
        super(fm);
        this.listData = list;
    }

    @Override public Fragment getItem(int i)
    {
        return listData == null ? null : listData.get(i).fragment;
    }

    @Override public int getCount()
    {
        return listData == null ? 0 : listData.size();
    }
}
