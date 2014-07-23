package com.rayboot.airlauncher.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author rayboot
 * @from 14-7-23 15:11
 * @TODO
 */
public class FakeLauncherActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        finish();
    }
}