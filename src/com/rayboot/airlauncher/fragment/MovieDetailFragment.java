package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.VideoPlayerActivity;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;

/**
 * @author rayboot
 * @from 14-8-22 17:19
 * @TODO
 */
public class MovieDetailFragment extends BaseFragment
{

    MovieObj movieObj;
    @InjectView(R.id.tvTitle) TextView mTvTitle;
    @InjectView(R.id.tvOwner) TextView mTvOwner;
    @InjectView(R.id.linearLayout) LinearLayout mLinearLayout;
    @InjectView(R.id.ivLogo) ImageView mIvLogo;
    @InjectView(R.id.tvDesc) TextView mTvDesc;
    @InjectView(R.id.btnPlay) ImageButton mBtnPlay;

    public static MovieDetailFragment newInstance(MovieObj fileObj)
    {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("file_obj", fileObj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        movieObj = (MovieObj) getArguments().getSerializable("file_obj");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.activity_movie_detail, container,
                        false);
        ButterKnife.inject(this, vFragment);

        PicUtil.getPicasso().load(new File(movieObj.imgPath)).into(mIvLogo);
        mTvTitle.setText(movieObj.title);
        mTvOwner.setText(
                "导演：" + movieObj.director + "\n" + "主演：" + movieObj.acts);

        mTvDesc.setText(movieObj.desc);
        return vFragment;
    }

    @OnClick(R.id.btnPlay)
    public void onPlayClick(View view)
    {
        Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
        intent.putExtra("play_obj", movieObj);
        startActivity(intent);
    }

    @OnClick(R.id.vBg)
    public void onBackClick(View view)
    {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    public void onEvent(View view)
    {

    }
}
