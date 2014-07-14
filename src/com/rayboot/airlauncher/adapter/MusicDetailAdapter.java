package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.model.MusicDetailObj;
import java.util.List;

/**
 * @author rayboot
 * @from 14-7-11 10:10
 * @TODO
 */
public class MusicDetailAdapter<T> extends BaseListAdapter<T>
{
    public MusicDetailAdapter(Context context, List<T> listDatas)
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
            convertView =
                    mLayoutInflater.inflate(R.layout.item_music_detail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        MusicDetailObj detailObj = (MusicDetailObj) getItem(position);

        holder.mTvMusicName.setText(detailObj.name);
        holder.mTvOwner.setText(detailObj.owner);
        holder.mTvTime.setText(detailObj.timeString);
        return convertView;
    }

    static class ViewHolder
    {
        @InjectView(R.id.ivPlaying) ImageView mIvPlaying;
        @InjectView(R.id.tvMusicName) TextView mTvMusicName;
        @InjectView(R.id.tvTime) TextView mTvTime;
        @InjectView(R.id.tvOwner) TextView mTvOwner;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }
}
