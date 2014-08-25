package com.rayboot.airlauncher.musicservice;

import com.rayboot.airlauncher.model.MusicDetailObj;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/** Singleton */
public class PlayList
{

    public List<MusicDetailObj> files =
            new CopyOnWriteArrayList<MusicDetailObj>();
    private MusicDetailObj current;

    public PlayList(List<MusicDetailObj> files)
    {
        this.files = files;
    }

    public synchronized MusicDetailObj getCurrent()
    {
        return current;
    }
    public void setCurrent(MusicDetailObj current)
    {
        this.current = current;
    }

    public synchronized MusicDetailObj preFile()
    {
        switch (PlayMode.CUR_PLAY_MODE)
        {
        case PlayMode.MODE_LOOP:
            int curIndex = 0;
            for (MusicDetailObj file : files)
            {
                if (file.path.equals(current.path))
                {
                    curIndex = files.indexOf(file);
                    break;
                }
            }
            if (curIndex > 0)
            {
                current = files.get(files.size() - 1);
            }else
            {
                current = files.get(curIndex - 1);
            }
            break;
        case PlayMode.MODE_ALONE:
            break;
        case PlayMode.MODE_RAMDOM:
            current = files.get(new Random().nextInt(files.size()));
            break;
        }
        //if (frontEnd != null)
        //{
        //    frontEnd.changeFile(current);
        //}

        return current;
    }

    public synchronized MusicDetailObj nextFile()
    {
        switch (PlayMode.CUR_PLAY_MODE)
        {
        case PlayMode.MODE_LOOP:
            if (current == null)
            {
                current = files.get(0);
            }else
            {
                int curIndex = files.size() - 1;
                for (MusicDetailObj file : files)
                {
                    if (file.path.equals(current.path))
                    {
                        curIndex = files.indexOf(file);
                        break;
                    }
                }
                if (curIndex == files.size() - 1)
                {
                    current = files.get(0);
                }else
                {
                    current = files.get(curIndex + 1);
                }
            }
            break;
        case PlayMode.MODE_ALONE:
            break;
        case PlayMode.MODE_RAMDOM:
            current = files.get(new Random().nextInt(files.size()));
            break;
        }
        //if (frontEnd != null)
        //{
        //    frontEnd.changeFile(current);
        //}

        return current;
    }
}
