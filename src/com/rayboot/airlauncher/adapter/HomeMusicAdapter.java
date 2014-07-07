package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.model.MusicObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;
import java.util.List;

/**
 * @author rayboot
 * @from 14-7-7 14:37
 * @TODO
 */
public class HomeMusicAdapter<T> extends BaseListAdapter<T>
{
    public HomeMusicAdapter(Context context, List<T> listDatas)
    {
        super(context, listDatas);
    }

    @Override public View getView(int position, View convertView,
            ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView != null)
        {
            holder = (ViewHolder) convertView.getTag();
        }
        else
        {
            convertView = mLayoutInflater.inflate(R.layout.item_music, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        MusicObj data = (MusicObj) getItem(position);
        holder.mTvMusicName.setText(data.title);
        File imgFile = new File(data.imgPath);
        if (imgFile.exists())
        {
            PicUtil.getPicasso()
                    .load(imgFile)
                    .placeholder(R.drawable.ic_launcher)
                    .into(holder.mIvMusicLogo);
        }
        convertView.setTag(R.string.obj_data, data);
        return convertView;
    }

    static class ViewHolder
    {
        @InjectView(R.id.ivMusicLogo) ImageView mIvMusicLogo;
        @InjectView(R.id.tvMusicName) TextView mTvMusicName;
        @InjectView(R.id.btnPlay) Button mBtnPlay;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }
}
