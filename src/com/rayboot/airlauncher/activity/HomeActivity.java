package com.rayboot.airlauncher.activity;
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.fragment.ContentListFragment;
import com.rayboot.airlauncher.fragment.HomeMainFragment;
import com.rayboot.airlauncher.model.FileObj;

/**
 * @author rayboot
 * @from 14-6-24 15:06
 * @TODO
 */
public class HomeActivity extends BaseActionBarActivity
{
    private FragmentManager mFragMgr;
    SlidingMenu menu;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mFragMgr = getSupportFragmentManager();

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(0);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.view_slidingmenu);
        menu.setOnCloseListener(new SlidingMenu.OnCloseListener()
        {
            @Override public void onClose()
            {
                materialMenu.animatePressedState(
                        MaterialMenuDrawable.IconState.BURGER);
            }
        });
        menu.setOnClosedListener(new SlidingMenu.OnClosedListener()
        {
            @Override public void onClosed()
            {
                materialMenu.animatePressedState(
                        MaterialMenuDrawable.IconState.BURGER);
            }
        });
        menu.setOnOpenListener(new SlidingMenu.OnOpenListener()
        {
            @Override public void onOpen()
            {
                materialMenu.animatePressedState(
                        MaterialMenuDrawable.IconState.ARROW);
            }
        });
        menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener()
        {
            @Override public void onOpened()
            {
                materialMenu.animatePressedState(
                        MaterialMenuDrawable.IconState.ARROW);
            }
        });

        showFragments(getResources().getString(R.string.menu_home), false);
    }

    public SlidingMenu getMenu()
    {
        return menu;
    }

    public void doMoreClick(View view)
    {
        materialMenu.setState(MaterialMenuDrawable.IconState.ARROW);
        Intent intent = new Intent(this, ContentListActivity.class);
        intent.putExtra("content_type", (Integer) view.getTag());
        startActivity(intent);
    }

    public void doMenuClick(View view)
    {
        showFragments((String) view.getTag(), false);
    }

    private void showFragments(String tag, boolean needback)
    {
        FragmentTransaction trans = mFragMgr.beginTransaction();
        if (needback)
        {
            //trans.setCustomAnimations(R.anim.frag_enter,
            //        R.anim.frag_exit);
            trans.add(R.id.root, getFragmentByTag(tag), tag);
            trans.addToBackStack(tag);
        }
        else
        {
            trans.replace(R.id.root, getFragmentByTag(tag), tag);
        }
        trans.commit();
    }

    

    private Fragment getFragmentByTag(String tag)
    {

        if (tag.equals(getResources().getString(R.string.menu_home)))
        {
            return HomeMainFragment.newInstance();
        }
        else if (tag.equals(getResources().getString(R.string.menu_movie)))
        {
            return ContentListFragment.newInstance(FileObj.TYPE_MOVIE);
        }
        else if (tag.equals(getResources().getString(R.string.menu_book)))
        {
            return ContentListFragment.newInstance(FileObj.TYPE_BOOK);
        }
        else if (tag.equals(getResources().getString(R.string.menu_music)))
        {
            return ContentListFragment.newInstance(FileObj.TYPE_MUSIC);
        }
        else if (tag.equals(getResources().getString(R.string.menu_setting)))
        {
            return ContentListFragment.newInstance(FileObj.TYPE_MOVIE);
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            menu.toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(View view)
    {

    }
}
