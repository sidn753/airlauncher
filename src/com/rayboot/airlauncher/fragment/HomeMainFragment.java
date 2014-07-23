package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.artifex.mupdfdemo.MuPDFActivity;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.MovieDetailActivity;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.customviews.Common3ImageLayout;
import com.rayboot.airlauncher.customviews.CommonImage;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.MovieObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 14:51
 * @TODO
 */
public class HomeMainFragment extends BaseFragment
{
    @InjectView(R.id.ad) CommonImage mAd;
    @InjectView(R.id.hotMovies) Common3ImageLayout mHotMovies;
    @InjectView(R.id.hotBooks) Common3ImageLayout mHotBooks;

    List<BookObj> bookObjs = new ArrayList<BookObj>(3);
    List<MovieObj> movieObjs = new ArrayList<MovieObj>(3);

    public static HomeMainFragment newInstance()
    {
        HomeMainFragment fragment = new HomeMainFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        movieObjs = MovieObj.getTopObjs(3);
        bookObjs = BookObj.getTopObjs(3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_main, container, false);
        ButterKnife.inject(this, vFragment);

        mHotMovies.setContent(
                (List) movieObjs.subList(0, Math.min(3, movieObjs.size())),
                new View.OnClickListener()
                {
                    @Override public void onClick(View v)
                    {
                        MovieObj movieObj = (MovieObj) v.getTag();
                        Intent intent = new Intent(getActivity(),
                                MovieDetailActivity.class);
                        intent.putExtra("movie_obj", movieObj);
                        startActivity(intent);
                    }
                });
        mHotBooks.setContent(
                (List) bookObjs.subList(0, Math.min(3, bookObjs.size())),
                new View.OnClickListener()
                {
                    @Override public void onClick(View v)
                    {
                        BookObj bookObj = (BookObj) v.getTag();
                        Uri uri = Uri.parse(bookObj.filePath);
                        Intent intent =
                                new Intent(getActivity(), MuPDFActivity.class);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
        mAd.setContent("推广位", R.drawable.ad);
        return vFragment;
    }
}
