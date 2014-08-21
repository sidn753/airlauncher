package com.rayboot.airlauncher.musicservice;

import com.rayboot.airlauncher.R;

/**
 * @author rayboot
 * @from 14-7-11 18:00
 * @TODO
 */
public class PlayMode
{
    public static final int MODE_LOOP = 0;
    public static final int MODE_ALONE = 1;
    public static final int MODE_RAMDOM = 2;

    public static int CUR_PLAY_MODE = 0;

    public static int getModeName(int mode)
    {
        int result = R.drawable.mode_circle;
        switch (mode)
        {
        case MODE_LOOP:
            result = R.drawable.mode_circle;
            break;
        case MODE_ALONE:
            result = R.drawable.mode_signle;
            break;
        case MODE_RAMDOM:
            result = R.drawable.mode_random;
            break;
        }
        return result;
    }

    public static int nextMode()
    {
        CUR_PLAY_MODE = (CUR_PLAY_MODE + 1) % 3;
        return CUR_PLAY_MODE;
    }

}
