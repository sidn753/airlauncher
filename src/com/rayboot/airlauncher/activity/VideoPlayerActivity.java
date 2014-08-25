package com.rayboot.airlauncher.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.baidu.cyberplayer.core.BVideoView;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
import com.nineoldandroids.animation.ObjectAnimator;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.customviews.VerticalSeekBar;
import com.rayboot.airlauncher.model.MovieObj;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class VideoPlayerActivity extends BaseActionBarActivity
{
    MovieObj movieObj;
    String[] paths;
    public AudioManager audiomanage;
    @InjectView(R.id.btnVoice) ImageButton mBtnVoice;
    @InjectView(R.id.rlLeftController) RelativeLayout mRlLeftController;
    private int maxVolume, currentVolume;
    int videoH = 0;
    int videoW = 0;
    @InjectView(R.id.player) BVideoView mVV;
    @InjectView(R.id.btnPre) ImageButton mBtnPre;
    @InjectView(R.id.btnPlayPause) ImageButton mBtnPlayPause;
    @InjectView(R.id.btnNext) ImageButton mBtnNext;
    @InjectView(R.id.tvCurTime) TextView mTvCurTime;
    @InjectView(R.id.seekBar) SeekBar mSeekBar;
    @InjectView(R.id.tvTotalTime) TextView mTvTotalTime;
    @InjectView(R.id.llControllerBottom) LinearLayout mLlControllerBottom;
    @InjectView(R.id.vsbVoice) VerticalSeekBar mVsbVoice;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.argb(80, 0, 0, 0)));
        materialMenu.animatePressedState(MaterialMenuDrawable.IconState.X);

        movieObj = (MovieObj) getIntent().getSerializableExtra("play_obj");
        this.setActionBarTitleText(movieObj.title);

        paths = movieObj.filePath.split(MovieObj.SPLIT_STRING);
        initPlayer();

        //音量
        audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audiomanage.getStreamMaxVolume(
                AudioManager.STREAM_MUSIC);  //获取系统最大音量
        mVsbVoice.setMax(maxVolume);
        currentVolume =
                audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值
        mVsbVoice.setProgress(currentVolume);

        play(paths[0]);

        mSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser)
                    {
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override public void onStopTrackingTouch(SeekBar seekBar)
                    {
                        mVV.seekTo(seekBar.getProgress());
                    }
                });
        mVsbVoice.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser)
                    {
                        audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC,
                                progress, 0);
                        currentVolume = audiomanage.getStreamVolume(
                                AudioManager.STREAM_MUSIC);  //获取当前值
                        seekBar.setProgress(currentVolume);
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                });
        mVV.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                if (mRlLeftController.getVisibility() == View.VISIBLE)
                {
                    controlPannel(false);
                }else
                {
                    controlPannel(true);
                }
            }
        });
    }

    private void play(String path)
    {
        path = path.replace(MovieObj.SPLIT_STRING, "");
        mVV.setVideoPath(path);
        mVV.start();
    }

    private void initPlayer()
    {
        BVideoView.setAKSK("t8Qvin5wxrd6GkGVOjnhsEHT",
                "V8KQW7bY7u2ZG6BZ6xeAbSqbk13grmNA");
        mVV.setOnPreparedListener(onPreparedListener);
        mVV.setOnCompletionListener(onCompletionListener);
        mVV.setOnErrorListener(onErrorListener);
        mVV.setOnInfoListener(onInfoListener);

        mVV.setDecodeMode(BVideoView.DECODE_HW); //可选择软解模式或硬解模式
        mVV.showCacheInfo(false);
        mVV.setVideoScalingMode(BVideoView.VIDEO_SCALING_MODE_SCALE_TO_FIT);
    }

    private BVideoView.OnPreparedListener onPreparedListener =
            new BVideoView.OnPreparedListener()
            {
                @Override public void onPrepared()
                {
                    mSeekBar.setMax(mVV.getDuration());
                    mSeekBar.setProgress(mVV.getCurrentPosition());
                    mTvCurTime.setText(getTime(mVV.getCurrentPosition()));
                    mTvTotalTime.setText(getTime(mVV.getDuration()));
                    updateHandler.postDelayed(new Updater(), 1000);
                    videoH = mVV.getVideoHeight();
                    videoW = mVV.getVideoWidth();
                    mBtnPlayPause.setImageResource(
                            mVV.isPlaying() ? R.drawable.movie_pause
                                    : R.drawable.movie_play);
                }
            };

    private BVideoView.OnCompletionListener onCompletionListener =
            new BVideoView.OnCompletionListener()
            {
                @Override public void onCompletion()
                {

                }
            };

    private BVideoView.OnErrorListener onErrorListener =
            new BVideoView.OnErrorListener()
            {
                @Override public boolean onError(int i, int i2)
                {
                    return false;
                }
            };

    private BVideoView.OnInfoListener onInfoListener =
            new BVideoView.OnInfoListener()
            {
                @Override public boolean onInfo(int i, int i2)
                {
                    return false;
                }
            };

    /**
     * 实现切换示例
     */
    private View.OnClickListener mPreListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            // TODO Auto-generated method stub
            Log.v(TAG, "pre btn clicked");
        }
    };

    private View.OnClickListener mNextListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            // TODO Auto-generated method stub
            Log.v(TAG, "next btn clicked");
        }
    };

    public String getTime(int time)
    {
        int hour = time / 3600;
        int min = (time - hour * 3600) / 60;
        int sec = time % 60;
        return String.format("%1$02d:%2$02d:%3$02d", hour, min, sec);
    }

    @Override protected void onPause()
    {
        mVV.stopPlayback();
        super.onPause();
    }

    private Handler updateHandler = new Handler();

    private class Updater implements Runnable
    {
        public void run()
        {
            if (mVV.isPlaying())
            {
                mSeekBar.setProgress(mVV.getCurrentPosition());
                mTvCurTime.setText(getTime(mVV.getCurrentPosition()));
                updateHandler.postDelayed(this, 1000);
            }
        }
    }

    public void onEvent(View view)
    {

    }

    public void onControlClick(View view)
    {
        switch (view.getId())
        {
        case R.id.btnPlayPause:
            if (mVV.isPlaying())
            {
                mVV.pause();
                mBtnPlayPause.setImageResource(R.drawable.movie_play);
            }
            else
            {
                mVV.resume();
                mBtnPlayPause.setImageResource(R.drawable.movie_pause);
            }
            break;
        case R.id.btnNext:
            mVV.seekTo(mSeekBar.getProgress() + 20 > mSeekBar.getMax()
                    ? mSeekBar.getMax() : mSeekBar.getProgress() + 20);
            break;
        case R.id.btnPre:
            mVV.seekTo(mSeekBar.getProgress() - 20 >= 0 ? mSeekBar.getProgress()
                    - 20 : 0);
            break;
        case R.id.vBg:
            if (isShow)
            {
                controlPannel(false);
            }else
            {
                controlPannel(true);
            }
            break;
        }
    }

    boolean isShow = true;
    private void controlPannel(boolean isShow)
    {
        this.isShow = isShow;
        if (isShow)
        {
            ObjectAnimator.ofFloat(mLlControllerBottom, "translationY", 200, 0)
                    .setDuration(500)
                    .start();
            ObjectAnimator.ofFloat(mRlLeftController, "translationX", -200, 0)
                    .setDuration(500)
                    .start();
            getSupportActionBar().show();
        }
        else
        {
            ObjectAnimator.ofFloat(mLlControllerBottom, "translationY", 0, 200)
                    .setDuration(500)
                    .start();
            ObjectAnimator.ofFloat(mRlLeftController, "translationX", 0, -200)
                    .setDuration(500)
                    .start();
            getSupportActionBar().hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            mVV.stopPlayback();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
