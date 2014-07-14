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

}
