package com.rayboot.airlauncher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rayboot.airlauncher.fragment.HomeMainFragment;
import com.rayboot.airlauncher.model.FileObj;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 15:45
 * @TODO
 */
public class HomePagerAdapter extends FragmentPagerAdapter
{
    List<FileObj> listDatas;
    private int mCount = 0;

    public HomePagerAdapter(FragmentManager fm, List<FileObj> listDatas)
    {
        super(fm);
        this.listDatas = listDatas;
        this.mCount = listDatas == null ? 0 : listDatas.size();
    }

    @Override
    public Fragment getItem(int position)
    {
        return HomeMainFragment.newInstance(listDatas.get(position));
    }

    @Override
    public int getCount()
    {
        return mCount;
    }

}
