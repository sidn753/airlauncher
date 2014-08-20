package com.rayboot.airlauncher.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.model.FileObj;

/**
 * @author rayboot
 * @from 14-8-19 11:51
 * @TODO
 */
public class TitleViewLayout extends LinearLayout
{
    TextView tvContent;
    RelativeLayout titleLine;
    public TitleViewLayout(Context context)
    {
        super(context);
        initControl(context);
    }

    public TitleViewLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context);
    }

    public TitleViewLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initControl(context);
    }

    private void initControl(Context context)
    {
        inflate(getContext(), R.layout.view_title_layout, this);
        this.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        tvContent = (TextView) this.findViewById(R.id.tvTitleName);
        titleLine = (RelativeLayout) this.findViewById(R.id.titleLine);
    }

    public void setType(int type)
    {
        titleLine.setTag(type);
        switch (type)
        {
        case FileObj.TYPE_MOVIE:
            setText("热门电影");
            break;
        case FileObj.TYPE_BOOK:
            setText("热门书籍");
            break;
        case FileObj.TYPE_MUSIC:
            setText("热门音乐");
            break;
        }
    }

    public void setText(String text)
    {
        tvContent.setText(text);
    }
}
