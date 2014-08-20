package com.rayboot.airlauncher;

import com.rayboot.airlauncher.util.FileUtil;

/**
 * @author rayboot
 * @from 14-6-24 9:30
 * @TODO
 */
public class App extends com.activeandroid.app.Application
{
    private static App app = null;

    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
        checkFileData();
        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse(
        //        "file://" + Environment.getExternalStorageDirectory())));
    }

    public static App getInstance()
    {
        if (app == null)
        {
            app = new App();
        }
        return app;
    }

    public void checkFileData()
    {
        FileUtil fileUtil = new FileUtil();
        fileUtil.getAllMoviesInfo();
        fileUtil.getAllBooksInfo();
        fileUtil.getAllMusicsInfo();
    }
}
