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

    public static final int PAGER_HOME = 0;
    public static final int PAGER_MOVIE = 1;
    public static final int PAGER_BOOK = 2;
    public static final int PAGER_MUSIC = 3;

    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
        checkFileData();
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
