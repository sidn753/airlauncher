package com.rayboot.airlauncher.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import de.greenrobot.event.EventBus;

/**
 * @author rayboot
 * @from 14-6-24 9:11
 * @TODO
 */
public class BaseActionBarActivity extends ActionBarActivity
{
    protected String TAG = "";

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = ((Object) this).getClass().getSimpleName();
        this.getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
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
