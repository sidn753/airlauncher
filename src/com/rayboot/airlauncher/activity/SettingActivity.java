package com.rayboot.airlauncher.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.homeselector.HomeManager;

/**
 * @author rayboot
 * @from 14-7-7 16:51
 * @TODO
 */
public class SettingActivity extends BaseActionBarActivity
{
    private HomeManager mHManager;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        PackageManager pm = getPackageManager();
        ActivityManager am = (ActivityManager) this.getSystemService(
                Context.ACTIVITY_SERVICE);
        mHManager = new HomeManager(pm, am);
    }

    public void onTestClick(View view)
    {

        if(mHManager.queryDefaultHome() != null) {
            mHManager.removeDefaultHome(this, MyActivity.class);
        }
        mHManager.switchHome(this);
    }

    public void onClearClick(View view)
    {
        if(mHManager.queryDefaultHome() != null) {
            mHManager.removeDefaultHome(this, MyActivity.class);
        }
    }
}
