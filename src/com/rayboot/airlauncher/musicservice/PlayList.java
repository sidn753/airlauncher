package com.rayboot.airlauncher.musicservice;

import android.app.Service;
import com.rayboot.airlauncher.activity.MusicPlayerActivity;
import com.rayboot.airlauncher.model.MusicDetailObj;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Singleton */
public enum PlayList
{
    instance;

    public List<MusicDetailObj> files =
            new CopyOnWriteArrayList<MusicDetailObj>();
    private MusicDetailObj current;

    // HAAAAAAAAAAACK
    public Service service = null;
    public MusicPlayerActivity frontEnd = null;

    public synchronized MusicDetailObj getCurrent()
    {
        return current;
    }

    public synchronized MusicDetailObj nextFile()
    {
        current = null;

        if (!files.isEmpty())
        {
            current = files.remove(0);
        }

        if (frontEnd != null)
        {
            frontEnd.changeFile(current);
        }

        return current;
    }
}
