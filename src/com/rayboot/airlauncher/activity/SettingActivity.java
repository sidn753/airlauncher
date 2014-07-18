package com.rayboot.airlauncher.activity;

import android.os.Bundle;
import android.view.View;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;

/**
 * @author rayboot
 * @from 14-7-7 16:51
 * @TODO
 */
public class SettingActivity extends BaseActionBarActivity
{
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onTestClick(View view)
    {
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(Intent.ACTION_MAIN);
        //filter.addCategory(Intent.CATEGORY_HOME);
        //filter.addCategory(Intent.CATEGORY_DEFAULT);
        //
        //PackageManager manager = getPackageManager();
        //Intent intent = new Intent(Intent.ACTION_MAIN, null);
        //intent.addCategory(Intent.CATEGORY_HOME);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        //List<ResolveInfo> list = manager.queryIntentActivities(intent, 0);
        //for (int i = 0; i < list.size(); i++) {
        //    // 作用是： 清除之前选择的Homescreen,比如即使你手动设置了Launcher作为你的                           //Homescreen，执行以下方法之后，Launcher就不再是默认的Homescreen了。
        //    manager.clearPackagePreferredActivities(list.get(i).activityInfo.packageName);
        //}
        //
        //Intent intent2 = new Intent(Intent.ACTION_MAIN);
        //intent2.addCategory(Intent.CATEGORY_HOME);
        //startActivity(intent2);
        getPackageManager().clearPackagePreferredActivities(getPackageName());
    }
}
