package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.content.res.Resources;
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
    int selIndex = -1;

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
        holder.mTvPos.setText(position + 1 + "");
        holder.mTvTime.setText(detailObj.timeString);

        holder.setSelect(mContext.getResources(), selIndex == position);

        return convertView;
    }

    public void setSelectedIndex(int selIndex)
    {
        this.selIndex = selIndex;
    }

    static class ViewHolder
    {
        @InjectView(R.id.tvPos) TextView mTvPos;
        @InjectView(R.id.tvMusicName) TextView mTvMusicName;
        @InjectView(R.id.tvTime) TextView mTvTime;
        @InjectView(R.id.tvOwner) TextView mTvOwner;
        @InjectView(R.id.ivPlaying) ImageView mIvPlaying;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }

        public void setSelect(Resources res, boolean isSel)
        {
            mTvMusicName.setTextColor(res.getColor(
                    isSel ? R.color.main_color : R.color.music_common_2));
            mTvTime.setTextColor(res.getColor(
                    isSel ? R.color.main_color : R.color.music_common_2));
            mTvOwner.setTextColor(res.getColor(
                    isSel ? R.color.main_color : R.color.music_common_2));
            mTvPos.setVisibility(isSel ? View.GONE : View.VISIBLE);
            mIvPlaying.setVisibility(isSel ? View.VISIBLE : View.GONE);
        }
    }
}
