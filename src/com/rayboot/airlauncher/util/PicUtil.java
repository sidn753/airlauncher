package com.rayboot.airlauncher.util;

import com.rayboot.airlauncher.App;
import com.squareup.picasso.Picasso;

/**
 * @author rayboot
 * @from 14-6-24 17:25
 * @TODO
 */
public class PicUtil
{
    private static Picasso picasso;
    public static boolean isDebug = false;

    public static Picasso getPicasso()
    {
        if (picasso == null)
        {
            picasso = new Picasso.Builder(App.getInstance()).indicatorsEnabled(
                    isDebug).build();
        }
        return picasso;
    }
}
