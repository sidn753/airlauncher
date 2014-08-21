package com.rayboot.airlauncher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.customviews.CommonImage;
import com.rayboot.airlauncher.model.MovieObj;
import java.util.List;

/**
 * @author rayboot
 * @from 14-7-7 17:25
 * @TODO
 */
public class MoviesAdapter<T> extends BaseListAdapter<T>
{
    public MoviesAdapter(Context context, List<T> listDatas)
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
            convertView = mLayoutInflater.inflate(R.layout.item_movie, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        MovieObj data = (MovieObj) getItem(position);
        holder.mVMovies.setContent(data.title, data.imgPath);
        convertView.setTag(R.string.obj_data, data);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_movie.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Inmite Developers (http://inmite.github.io)
     */

    static class ViewHolder
    {
        @InjectView(R.id.vMovies) CommonImage mVMovies;
        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }

}
