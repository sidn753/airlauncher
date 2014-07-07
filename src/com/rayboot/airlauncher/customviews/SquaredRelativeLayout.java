package com.rayboot.airlauncher.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author rayboot
 * @from 14-6-26 17:12
 * @TODO
 */
public class SquaredRelativeLayout extends RelativeLayout
{
    public SquaredRelativeLayout(Context context)
    {
        super(context);
    }

    public SquaredRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquaredRelativeLayout(Context context, AttributeSet attrs,
            int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
