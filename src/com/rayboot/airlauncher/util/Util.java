package com.rayboot.airlauncher.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.rayboot.airlauncher.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-8-22 17:24
 * @TODO
 */
public class Util
{

    private static final String MAIN_LAUNCHER_PACKAGE_NAME =
            "com.android.launcher";

    public static void addFragment(FragmentManager fragmentManager, int root,
            BaseFragment fragment)
    {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(root, fragment);
        trans.addToBackStack("");
        trans.commit();
    }

    public static List<String> getAllLauncher(Context context)
    {
        List<String> res = new ArrayList<String>(5);
        PackageManager pm = context.getPackageManager();
        Intent i = new Intent("android.intent.action.MAIN");
        i.addCategory("android.intent.category.HOME");
        List<ResolveInfo> lst = pm.queryIntentActivities(i, 0);
        if (lst != null)
        {
            for (ResolveInfo resolveInfo : lst)
            {
                res.add(resolveInfo.activityInfo.packageName);
            }
        }
        return res;
    }

    public static void disableAllLauncher(Context context)
    {
        String myPN = context.getPackageName();
        List<String> apks = getAllLauncher(context);
        if (apks != null && apks.size() > 0)
        {
            for (String pn : apks)
            {
                if (!myPN.equals(pn))
                {
                    TerminalUtils.disableApp(pn);
                }
            }
        }
    }

    public static void enableAllLauncher(Context context)
    {
        String myPN = context.getPackageName();
        List<String> apks = getAllLauncher(context);
        if (apks != null && apks.size() > 0)
        {
            for (String pn : apks)
            {
                if (!myPN.equals(pn))
                {
                    TerminalUtils.enableApp(pn);
                }
            }
        }
        TerminalUtils.enableApp(MAIN_LAUNCHER_PACKAGE_NAME);
    }
}
