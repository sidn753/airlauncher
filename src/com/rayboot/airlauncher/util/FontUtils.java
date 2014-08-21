package com.rayboot.airlauncher.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author rayboot
 * @from 14-8-21 11:54
 * @TODO
 */
public class FontUtils
{
    public static void overrideFonts(final Context context, final View v,
            final Typeface fontName)
    {
        try
        {
            if (v instanceof ViewGroup)
            {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++)
                {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child, fontName);
                }
            }
            else if (v instanceof TextView)
            {
                ((TextView) v).setTypeface(fontName);
            }
        }
        catch (Exception e)
        {
        }
    }

    // Override fonts and set to chalkduster (for entire activity!)
    public static void overrideFonts(final Context context, View... views)
    {
        return;
        //try
        //{
        //    for (View v : views)
        //    {
        //        if (v instanceof ViewGroup)
        //        {
        //            ViewGroup vg = (ViewGroup) v;
        //
        //            for (int i = 0; i < vg.getChildCount(); i++)
        //            {
        //                View child = vg.getChildAt(i);
        //                overrideFonts(context, child);
        //            }
        //        }
        //        else if (v instanceof TextView)
        //        {
        //            ((TextView) v).setTypeface(
        //                    Typeface.createFromAsset(context.getAssets(),
        //                            "fonts/SourceHanSansCN-Light.otf"));
        //        }
        //    }
        //}
        //catch (Exception e)
        //{
        //}
    }

    // Override fonts and set to defined font
    public static void overrideFonts(final Context context, Typeface t, View... views) {
        try {
            for (View v : views) {
                if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;

                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        overrideFonts(context, child);
                    }

                } else if (v instanceof TextView ) {
                    ((TextView) v).setTypeface(t);
                }
            }
        } catch (Exception e) {
        }
    }
}