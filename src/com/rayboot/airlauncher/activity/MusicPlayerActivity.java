package com.rayboot.airlauncher.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
import com.rayboot.airlauncher.musicservice.MusicService;
import com.rayboot.airlauncher.util.PicUtil;
import java.io.File;
import java.util.ArrayList;
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
    @InjectView(R.id.llMusicInfo) RelativeLayout mLlMusicInfo;
    @InjectView(R.id.tvMusicTitle) TextView mTvMusicTitle;
    @InjectView(R.id.tvMusicOwner) TextView mTvMusicOwner;
    @InjectView(R.id.tvMusicYear) TextView mTvMusicYear;
    @InjectView(R.id.lvMusic) ListView mLvMusic;
    @InjectView(R.id.btnPre) Button mBtnPre;
    @InjectView(R.id.btnPlayPause) Button mBtnPlayPause;
    @InjectView(R.id.btnNext) Button mBtnNext;
    @InjectView(R.id.tvCurTime) TextView mTvCurTime;
    @InjectView(R.id.btnPlayMode) Button mBtnPlayMode;
    @InjectView(R.id.btnVoice) Button mBtnVoice;
    @InjectView(R.id.tvCurDuration) TextView mTvCurDuration;
    @InjectView(R.id.seekBar) SeekBar mSeekBar;
    @InjectView(R.id.ivCurLogo) ImageView mIvCurLogo;
    @InjectView(R.id.tvCurName) TextView mTvCurName;
    @InjectView(R.id.tvCurOwner) TextView mTvCurOwner;
    @InjectView(R.id.rlControlInfo1) RelativeLayout mRlControlInfo1;

    MusicObj curMusicObj;
    MusicService mService = null;
    List<MusicDetailObj> musicDetailObjs = new ArrayList<MusicDetailObj>(10);

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.inject(this);

        curMusicObj = (MusicObj) getIntent().getSerializableExtra("music_obj");

        for (String path : curMusicObj.filePath.split(FileObj.SPLIT_STRING))
        {
            musicDetailObjs.add(new MusicDetailObj(path));
        }

        MusicDetailAdapter<MusicDetailObj> adapter =
                new MusicDetailAdapter<MusicDetailObj>(this, musicDetailObjs);
        mLvMusic.setAdapter(adapter);
        mLvMusic.setOnItemClickListener(onItemClickListener);

        PicUtil.getPicasso()
                .load(new File(curMusicObj.imgPath))
                .placeholder(R.drawable.ic_launcher)
                .into(mIvMusicLogo);
        mTvMusicDesc.setText(curMusicObj.desc);
        mTvMusicTitle.setText(curMusicObj.title);
        mTvMusicOwner.setText("歌手：" + musicDetailObjs.get(0).owner);
        mTvMusicYear.setText("发行时间：" + musicDetailObjs.get(0).year);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder =
                    (MusicService.LocalBinder) service;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            mService = null;
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener()
            {
                @Override public void onItemClick(AdapterView<?> parent,
                        View view, int position, long id)
                {

                }
            };

    public void onControlClick(View view)
    {

    }

    public void onModeClick(View view)
    {

    }

    public void onVoiceClick(View view)
    {


    }

    public void changeFile(MusicDetailObj file)
    {
        //ImageView art = (ImageView) findViewById(R.id.playingIcon);
        //TextView info = (TextView) findViewById(R.id.playingInfo);
        //ImageView play = (ImageView) findViewById(R.id.playPauseIcon);
        //
        //if (file != null) {
        //    try {
        //        InputStream in = getContentResolver().openInputStream(file.getImageUri());
        //        Bitmap bitmap = BitmapFactory.decodeStream(in);
        //        art.setImageBitmap(bitmap);
        //    }
        //    catch (FileNotFoundException e) {
        //        art.setImageResource(R.drawable.ic_tab_artists_white);
        //    }
        //
        //    info.setText(file.toString());
        //    play.setImageResource(R.drawable.play);
        //}
        //else {
        //    art.setImageBitmap(null);
        //    info.setText("");
        //    play.setImageBitmap(null);
        //}
        //
        //playListAdapter.notifyDataSetChanged();
    }
}