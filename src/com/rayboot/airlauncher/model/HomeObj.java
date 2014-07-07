package com.rayboot.airlauncher.model;

import android.support.v4.app.Fragment;
import android.widget.Button;
import com.rayboot.airlauncher.fragment.HomeBooksFragment;
import com.rayboot.airlauncher.fragment.HomeMainFragment;
import com.rayboot.airlauncher.fragment.HomeMoviesFragment;
import com.rayboot.airlauncher.fragment.HomeMusicsFragment;

/**
 * @author rayboot
 * @from 14-6-24 15:11
 * @TODO
 */

public class HomeObj
{
    public static final int TYPE_HOME = 0;
    public static final int TYPE_MOVIES = 1;
    public static final int TYPE_BOOKS = 2;
    public static final int TYPE_MUSICS = 3;

    public int index;
    public Button btn;
    public Fragment fragment;

    public HomeObj(int type, Button btn)
    {
        this.index = type;
        this.btn = btn;
        switch (type)
        {
        case TYPE_HOME:
            this.fragment = HomeMainFragment.newInstance();
            this.btn.setText("首页");
            break;
        case TYPE_MOVIES:
            this.fragment = HomeMoviesFragment.newInstance();
            this.btn.setText("电影");
            break;
        case TYPE_BOOKS:
            this.fragment = HomeBooksFragment.newInstance();
            this.btn.setText("小说");
            break;
        case TYPE_MUSICS:
            this.fragment = HomeMusicsFragment.newInstance();
            this.btn.setText("音乐");
            break;
        }
    }
}
