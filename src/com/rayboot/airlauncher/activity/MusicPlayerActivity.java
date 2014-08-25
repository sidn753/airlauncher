package com.rayboot.airlauncher.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.facebook.rebound.ui.Util;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.adapter.MusicDetailAdapter;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MusicDetailObj;
import com.rayboot.airlauncher.model.MusicObj;
import com.rayboot.airlauncher.musicservice.ICurMusicListener;
import com.rayboot.airlauncher.musicservice.MusicService;
import com.rayboot.airlauncher.musicservice.PlayList;
import com.rayboot.airlauncher.musicservice.PlayMode;
import com.rayboot.airlauncher.util.FontUtils;
import com.rayboot.airlauncher.util.PicUtil;
import com.rayboot.airlauncher.util.StackBlurManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class MusicPlayerActivity extends BaseActionBarActivity
        implements ICurMusicListener
{
    @InjectView(R.id.ivMusicLogo) ImageView mIvMusicLogo;
    @InjectView(R.id.tvMusicTitle) TextView mTvMusicTitle;
    @InjectView(R.id.tvMusicOwner) TextView mTvMusicOwner;
    @InjectView(R.id.lvMusic) ListView mLvMusic;
    @InjectView(R.id.btnPre) ImageButton mBtnPre;
    @InjectView(R.id.btnPlayPause) ImageButton mBtnPlayPause;
    @InjectView(R.id.btnNext) ImageButton mBtnNext;
    @InjectView(R.id.btnPlayMode) ImageButton mBtnPlayMode;
    @InjectView(R.id.seekBar) SeekBar mSeekBar;

    MusicObj curMusicObj;
    MusicService mService = null;
    List<MusicDetailObj> musicDetailObjs = new ArrayList<MusicDetailObj>(10);
    MusicDetailAdapter<MusicDetailObj> adapter;
    @InjectView(R.id.ivMainBg) KenBurnsView mIvMainBg;
    @InjectView(R.id.rlMain) RelativeLayout mRlMain;
    private StackBlurManager _stackBlurManager;
    Spring spring = SpringSystem.create()
            .createSpring()
            .setSpringConfig(new SpringConfig(90, 10));
    boolean isStop = true;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.inject(this);
        materialMenu.animatePressedState(MaterialMenuDrawable.IconState.X);

        curMusicObj = (MusicObj) getIntent().getSerializableExtra("music_obj");
        _stackBlurManager = new StackBlurManager(
                BitmapFactory.decodeFile(curMusicObj.imgPath));

        for (String path : curMusicObj.filePath.split(FileObj.SPLIT_STRING))
        {
            musicDetailObjs.add(new MusicDetailObj(path));
        }

        adapter = new MusicDetailAdapter<MusicDetailObj>(this, musicDetailObjs);
        mLvMusic.setAdapter(adapter);
        mLvMusic.setOnItemClickListener(onItemClickListener);

        PicUtil.getPicasso()
                .load(new File(curMusicObj.imgPath))
                .placeholder(R.drawable.ic_launcher)
                .into(mIvMusicLogo);
        mTvMusicTitle.setText(curMusicObj.title);
        mTvMusicOwner.setText(musicDetailObjs.get(0).owner);

        mSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser)
                    {
                        if (fromUser && mService != null)
                        {
                            mService.setPosition(progress * 1000);
                        }
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                });
        onBlur();
        FontUtils.overrideFonts(this, mIvMainBg.getRootView());
        spring.addListener(new SimpleSpringListener()
        {
            @Override public void onSpringUpdate(Spring spring)
            {
                try
                {
                    float value = (float) spring.getCurrentValue();
                    float titleTranslateY =
                            (float) SpringUtil.mapValueFromRangeToRange(value,
                                    0, 1, Util.dpToPx(-1000,
                                            MusicPlayerActivity.this.getResources()),
                                    0);
                    if (mRlMain != null)
                    {
                        ViewHelper.setTranslationY(mRlMain, titleTranslateY);
                        //ViewHelper.setScaleX(mRlMain, value);
                        //ViewHelper.setScaleY(mRlMain, value);
                    }
                }
                catch (Exception e)
                {
                }
            }
        });
        spring.setEndValue(1);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        isStop = false;
        updateHandler.postDelayed(new Updater(), 1000);
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
            mService.setPlayList(new PlayList(musicDetailObjs));
            mService.setCurMusicListener(MusicPlayerActivity.this);
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
                    if (mService != null)
                    {
                        mService.processPlayNowRequest(
                                (MusicDetailObj) adapter.getItem(position));
                    }
                }
            };

    public void onControlClick(View view)
    {
        if (mService == null)
        {
            return;
        }

        switch (view.getId())
        {
        case R.id.btnNext:
            mService.processPlayNextRequest();
            break;
        case R.id.btnPre:
            mService.processPlayPreRequest();
            break;
        case R.id.btnPlayPause:
            mService.processPlayPauseRequest();
            break;
        }
    }

    public void onModeClick(View view)
    {
        ((ImageButton) view).setImageResource(
                PlayMode.getModeName(PlayMode.nextMode()));
        ObjectAnimator.ofFloat(view, "rotationY", 0, 180, 0)
                .setDuration(1000)
                .start();
    }

    public void onVoiceClick(View view)
    {

    }

    private Handler updateHandler = new Handler();

    private class Updater implements Runnable
    {
        public void run()
        {
            if (mService == null)
            {
                updateHandler.postDelayed(this, 1000);
                return;
            }
            MusicDetailObj currentObj = mService.getPlayList().getCurrent();
            if (currentObj != null)
            {
                int position = mService.getPosition();
                int duration = currentObj.time;

                mSeekBar.setMax(duration);
                mSeekBar.setProgress(position / 1000);

                switch (mService.getState())
                {
                case Stopped:
                case Paused:
                    mBtnPlayPause.setImageResource(R.drawable.selector_play);
                    break;
                case Preparing:
                case Playing:
                    mBtnPlayPause.setImageResource(R.drawable.selector_pause);
                    break;
                }
            }
            else
            {
                mSeekBar.setMax(0);
                mSeekBar.setProgress(0);
                mBtnPlayPause.setImageResource(R.drawable.selector_play);
            }
            if (!isStop)
            {
                updateHandler.postDelayed(this, 1000);
            }
        }
    }

    private void onBlur()
    {
        mIvMainBg.setImageBitmap(_stackBlurManager.process(10));
    }

    public void doClose(View view)
    {
        if (mService.getState() == MusicService.State.Playing)
        {
            mService.processPlayPauseRequest();
        }
        isStop = true;
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            doClose(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDestroy()
    {
        super.onDestroy();
        unbindService(mConnection);
        if (mService != null)
        {
            mService.onDestroy();
            mService = null;
        }
    }

    @Override public void currentMusic(MusicDetailObj cur)
    {
        for (MusicDetailObj file : musicDetailObjs)
        {
            if (file.path.equals(cur.path))
            {
                adapter.setSelectedIndex(musicDetailObjs.indexOf(file));
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void onEvent(View view)
    {

    }
}