package com.rayboot.airlauncher.musicservice;

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

    public static String getModeName(int mode)
    {
        String result = "循环";
        switch (mode)
        {
        case MODE_LOOP:
            result = "循环";
            break;
        case MODE_ALONE:
            result = "单独";
            break;
        case MODE_RAMDOM:
            result = "随机";
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
