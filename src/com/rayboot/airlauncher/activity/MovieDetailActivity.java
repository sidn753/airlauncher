package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;

/**
 * @author rayboot
 * @from 14-6-26 10:05
 * @TODO
 */
public class MovieDetailActivity extends BaseActionBarActivity
{
    @InjectView(R.id.ivBg) ImageView mIvBg;
    @InjectView(R.id.ivLogo) ImageView mIvLogo;
    @InjectView(R.id.tvTitle) TextView mTvTitle;
    @InjectView(R.id.tvDesc) TextView mTvDesc;
    @InjectView(R.id.tvDirector) TextView mTvDirector;
    @InjectView(R.id.tvTimeLength) TextView mTvTimeLength;
    @InjectView(R.id.tvType) TextView mTvType;
    @InjectView(R.id.tvPlayTimes) TextView mTvPlayTimes;
    @InjectView(R.id.tvActs) TextView mTvActs;
    @InjectView(R.id.linearLayout) LinearLayout mLinearLayout;
    @InjectView(R.id.btnPlay) Button mBtnPlay;

    MovieObj movieObj;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.argb(80, 0, 0, 0)));

        this.movieObj =
                (MovieObj) getIntent().getSerializableExtra("movie_obj");

        PicUtil.getPicasso().load(new File(movieObj.bgImgPath)).into(mIvBg);
        PicUtil.getPicasso().load(new File(movieObj.imgPath)).into(mIvLogo);
        mTvTitle.setText(movieObj.title);
        mTvDesc.setText(movieObj.desc);
        mTvDirector.setText(movieObj.director);
        mTvTimeLength.setText(movieObj.timelength);
        mTvType.setText(movieObj.type);
        mTvPlayTimes.setText(movieObj.playtimes + "次");
        mTvActs.setText(movieObj.acts);
    }

    public void onPlayClick(View view)
    {
        movieObj.playtimes += 1;
        movieObj.save();
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra("play_obj", movieObj);
        startActivity(intent);
    }
}
