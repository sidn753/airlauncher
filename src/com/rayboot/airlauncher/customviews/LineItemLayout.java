package com.rayboot.airlauncher.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.model.FileObj;
import java.util.List;

/**
 * @author rayboot
 * @from 14-8-19 14:10
 * @TODO
 */
public class LineItemLayout extends LinearLayout
{
    CommonImage[] imgs = new CommonImage[5];
    int type = FileObj.TYPE_MOVIE;
    public LineItemLayout(Context context)
    {
        super(context);
        initControl(context);
    }

    public LineItemLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context);
    }

    public LineItemLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initControl(context);
    }

    private void initControl(Context context)
    {
        inflate(getContext(), R.layout.view_line_item, this);
        this.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        imgs[0] = (CommonImage) this.findViewById(R.id.v1);
        imgs[1] = (CommonImage) this.findViewById(R.id.v2);
        imgs[2] = (CommonImage) this.findViewById(R.id.v3);
        imgs[3] = (CommonImage) this.findViewById(R.id.v4);
        imgs[4] = (CommonImage) this.findViewById(R.id.v5);
    }

    public void setContent(List<FileObj> files, OnClickListener listener, int type)
    {
        this.type = type;
        for (int i = 0; i < files.size(); i++)
        {
            if (i >= imgs.length)
            {
                break;
            }
            imgs[i].setContent(files.get(i).title, files.get(i).imgPath);
            imgs[i].setTag(files.get(i));
            imgs[i].setTag(R.string.tag_type, type);
            imgs[i].setOnClickListener(listener);
            imgs[i].setVisibility(VISIBLE);
        }
    }
}
