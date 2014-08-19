package com.rayboot.airlauncher.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @author rayboot
 * @from 14-8-19 10:58
 * @TODO
 */
public class BaseSlidingActivity extends SlidingFragmentActivity
{
    protected String TAG = "";

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = ((Object) this).getClass().getSimpleName();
        this.getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {
        //// Respond to the action bar's Up/Home button
        //case android.R.id.home:
        //    if (!(this instanceof HomeActivity))
        //    {
        //        finish();
        //    }
        //    return true;
        //}
        return super.onOptionsItemSelected(item);
    }
}
