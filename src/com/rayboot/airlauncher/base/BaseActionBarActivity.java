package com.rayboot.airlauncher.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

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
    }

}
