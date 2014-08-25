package com.rayboot.airlauncher.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.balysv.material.drawable.menu.MaterialMenuIcon;
import com.rayboot.airlauncher.R;
import de.greenrobot.event.EventBus;

/**
 * @author rayboot
 * @from 14-6-24 9:11
 * @TODO
 */
public class BaseActionBarActivity extends ActionBarActivity
{
    protected String TAG = "";
    protected MaterialMenuIcon materialMenu;
    protected FragmentManager mFragMgr;
    protected TextView actionBarTitle;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = ((Object) this).getClass().getSimpleName();
        this.getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");
        materialMenu = new MaterialMenuIcon(this, Color.WHITE);
        getSupportActionBar().setLogo(materialMenu.getDrawable());
        getSupportActionBar().setBackgroundDrawable(
                getResources().getDrawable(R.drawable.nv_bg));
        mFragMgr = getSupportFragmentManager();
        actionBarTitle = new TextView(this);
        actionBarTitle.setLayoutParams(
                new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        actionBarTitle.setGravity(Gravity.CENTER);
        actionBarTitle.setTextSize(24);
        actionBarTitle.setTextColor(Color.WHITE);
        getSupportActionBar().setCustomView(actionBarTitle);
    }

    @Override protected void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override protected void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    protected void setActionBarTitleText(String text)
    {
        actionBarTitle.setText(text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
