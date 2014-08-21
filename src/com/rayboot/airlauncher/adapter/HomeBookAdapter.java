package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.customviews.CommonImage;
import com.rayboot.airlauncher.model.BookObj;
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
        holder.mVBook.setContent(data.title, data.imgPath);
        convertView.setTag(R.string.obj_data, data);
        return convertView;
    }

    static class ViewHolder
    {
        @InjectView(R.id.vBook) CommonImage mVBook;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }
}
