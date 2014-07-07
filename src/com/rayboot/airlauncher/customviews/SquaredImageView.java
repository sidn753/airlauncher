package com.rayboot.airlauncher.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author rayboot
 * @from 14-7-7 15:08
 * @TODO
 */
public class SquaredImageView extends ImageView
{
    public SquaredImageView(Context context)
    {
        super(context);
    }

    public SquaredImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquaredImageView(Context context, AttributeSet attrs, int defStyle)
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
