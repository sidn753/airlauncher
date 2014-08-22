package com.rayboot.airlauncher.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * @author rayboot
 * @from 14-6-24 9:10
 * @TODO
 */
public class BaseActivity extends FragmentActivity
{
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(View.GONE);
    }
}
