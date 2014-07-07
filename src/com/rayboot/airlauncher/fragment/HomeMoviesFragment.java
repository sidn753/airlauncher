package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.MovieDetailActivity;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.customviews.Common4ImageLayout;
import com.rayboot.airlauncher.customviews.Common6ImageLayout;
import com.rayboot.airlauncher.model.MovieObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 14:51
 * @TODO
 */
public class HomeMoviesFragment extends BaseFragment
{

    List<MovieObj> movieObjs = new ArrayList<MovieObj>(10);
    @InjectView(R.id.c4ilImg) Common4ImageLayout mC4ilImg;
    @InjectView(R.id.c6ilImg) Common6ImageLayout mC6ilImg;

    public static HomeMoviesFragment newInstance()
    {
        HomeMoviesFragment fragment = new HomeMoviesFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        movieObjs = MovieObj.getAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_movies, container,
                        false);
        ButterKnife.inject(this, vFragment);
        mC4ilImg.setContent(
                (List) movieObjs.subList(0, Math.min(4, movieObjs.size())),
                onClickListener);
        if (movieObjs.size() > 4)
        {
            mC6ilImg.setContent((List) movieObjs.subList(4,
                    Math.min(10, movieObjs.size())), onClickListener);
        }
        return vFragment;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override public void onClick(View v)
        {
            MovieObj movieObj = (MovieObj) v.getTag();
            Intent intent =
                    new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie_obj", movieObj);
            startActivity(intent);
        }
    };
}
