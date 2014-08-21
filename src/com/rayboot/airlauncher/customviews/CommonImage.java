package com.rayboot.airlauncher.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.util.FontUtils;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;

/**
 * @author rayboot
 * @from 14-6-24 17:16
 * @TODO
 */
public class CommonImage extends RelativeLayout
{
    Context mContext;
    ImageView ivLogo;
    TextView tvLogo;

    public CommonImage(Context context)
    {
        super(context);
        initControl(context);
    }

    public CommonImage(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context);
    }

    public CommonImage(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initControl(context);
    }

    private void initControl(Context context)
    {
        mContext = context;
        inflate(getContext(), R.layout.custom_view_common_image, this);
        this.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        ivLogo = (ImageView) this.findViewById(R.id.ivLogo);
        tvLogo = (TextView) this.findViewById(R.id.tvLogo);
        FontUtils.overrideFonts(getContext(), tvLogo);
    }

    public void setContent(String strLogo, int resId)
    {
        tvLogo.setText(strLogo);
        PicUtil.getPicasso().load(resId).fit().centerCrop().into(ivLogo);
    }

    public void setContent(String strLogo, String imgPath)
    {
        tvLogo.setText(strLogo);
        PicUtil.getPicasso().load(new File(imgPath)).fit().centerCrop().into(ivLogo);
    }
}
