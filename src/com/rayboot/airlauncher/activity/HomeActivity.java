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
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.fragment.AboutFragment;
import com.rayboot.airlauncher.fragment.ContentListFragment;
import com.rayboot.airlauncher.fragment.HomeMainFragment;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.util.Util;

/**
 * @author rayboot
 * @from 14-6-24 15:06
 * @TODO
 */
public class HomeActivity extends BaseActionBarActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().hide();

        showFragments(getResources().getString(R.string.menu_home), false);
        Util.disableAllLauncher(this);
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

    public void doAboutClick(View view)
    {

        FragmentTransaction trans = mFragMgr.beginTransaction();
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.add(R.id.mainRoot,
                getFragmentByTag(getResources().getString(R.string.menu_about)),
                getResources().getString(R.string.menu_about));
        trans.addToBackStack(getResources().getString(R.string.menu_about));
        trans.commit();
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
        else if (tag.equals(getResources().getString(R.string.menu_about)))
        {
            return AboutFragment.getInstance();
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(View view)
    {

    }
}
