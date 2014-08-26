package com.rayboot.airlauncher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import com.rayboot.airlauncher.fragment.HomeHeaderPagerFragment;
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
    View.OnClickListener onClickListener;

    public HomePagerAdapter(FragmentManager fm, List<FileObj> listDatas,
            View.OnClickListener onClickListener)
    {
        super(fm);
        this.listDatas = listDatas;
        this.mCount = listDatas == null ? 0 : listDatas.size();
        this.onClickListener = onClickListener;
    }

    @Override
    public Fragment getItem(int position)
    {
        return HomeHeaderPagerFragment.newInstance(listDatas.get(position), onClickListener);
    }

    @Override
    public int getCount()
    {
        return mCount;
    }

}
