package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-26 17:13
 * @TODO
 */
public class HomeBookAdapter<T> extends BaseListAdapter<T>
{
    public HomeBookAdapter(Context context, List<T> listDatas)
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
            convertView = mLayoutInflater.inflate(R.layout.item_book, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        BookObj data = (BookObj) getItem(position);
        holder.mTvBookTitle.setText(data.title);
        File imgFile = new File(data.imgPath);
        if (imgFile.exists())
        {
            PicUtil.getPicasso()
                    .load(imgFile)
                    .placeholder(R.drawable.ic_launcher)
                    .into(holder.mIvBookLogo);
        }
        convertView.setTag(R.string.obj_data, data);
        return convertView;
    }

    static class ViewHolder
    {
        @InjectView(R.id.ivBookLogo) ImageView mIvBookLogo;
        @InjectView(R.id.tvBookTitle) TextView mTvBookTitle;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }
}
