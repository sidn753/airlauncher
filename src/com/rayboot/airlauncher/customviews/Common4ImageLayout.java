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
 * @from 14-6-25 11:16
 * @TODO
 */
public class Common4ImageLayout extends LinearLayout
{
    Context mContext;

    CommonImage[] imgs = new CommonImage[4];

    public Common4ImageLayout(Context context)
    {
        super(context);
        initControl(context);
    }

    public Common4ImageLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context);
    }

    private void initControl(Context context)
    {
        mContext = context;
        inflate(getContext(), R.layout.custom_view_4_images_layout, this);
        this.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        imgs[0] = (CommonImage) this.findViewById(R.id.img0);
        imgs[1] = (CommonImage) this.findViewById(R.id.img1);
        imgs[2] = (CommonImage) this.findViewById(R.id.img2);
        imgs[3] = (CommonImage) this.findViewById(R.id.img3);
    }

    public void setContent(List<FileObj> files, OnClickListener listener)
    {

        for (int i = 0; i < files.size(); i++)
        {
            if (i >= imgs.length)
            {
                break;
            }
            imgs[i].setContent(files.get(i).title, files.get(i).imgPath);
            imgs[i].setTag(files.get(i));
            imgs[i].setOnClickListener(listener);
        }
    }
}
