package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.os.Bundle;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;

public class MyActivity extends BaseActionBarActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startActivity(new Intent(this, HomeActivity.class));
    }

}
