package com.rayboot.airlauncher.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.adapter.MusicDetailAdapter;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.customviews.SquaredImageView;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MusicDetailObj;
import com.rayboot.airlauncher.model.MusicObj;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class MusicPlayerActivity extends BaseActionBarActivity
{
    @InjectView(R.id.rlControl) RelativeLayout mRlControl;
    @InjectView(R.id.ivMusicLogo) SquaredImageView mIvMusicLogo;
    @InjectView(R.id.tvMusicDesc) TextView mTvMusicDesc;
    @InjectView(R.id.llMusicInfo) LinearLayout mLlMusicInfo;
    @InjectView(R.id.tvMusicTitle) TextView mTvMusicTitle;
    @InjectView(R.id.tvMusicOwner) TextView mTvMusicOwner;
    @InjectView(R.id.tvMusicYear) TextView mTvMusicYear;
    @InjectView(R.id.lvMusic) ListView mLvMusic;
    MusicObj curMusicObj;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.inject(this);

        curMusicObj = (MusicObj) getIntent().getSerializableExtra("music_obj");

        List<String> pathList = Arrays.asList(
                curMusicObj.filePath.split(FileObj.SPLIT_STRING));

        MusicDetailAdapter<String> adapter =
                new MusicDetailAdapter<String>(this, pathList);
        mLvMusic.setAdapter(adapter);

        PicUtil.getPicasso()
                .load(new File(curMusicObj.imgPath))
                .placeholder(R.drawable.ic_launcher)
                .into(mIvMusicLogo);
        mTvMusicDesc.setText(curMusicObj.desc);
        mTvMusicTitle.setText(curMusicObj.title);
        MusicDetailObj detailObj = new MusicDetailObj(pathList.get(0));
        mTvMusicOwner.setText("歌手：" + detailObj.owner);
        mTvMusicYear.setText("发行时间：" + detailObj.year);
    }
}
