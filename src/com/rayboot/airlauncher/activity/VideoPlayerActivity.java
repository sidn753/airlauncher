package com.rayboot.airlauncher.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.baidu.cyberplayer.core.BVideoView;
import com.balysv.material.drawable.menu.MaterialMenuDrawable;
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
    @InjectView(R.id.player) BVideoView mVV;
    @InjectView(R.id.btnPre) ImageButton mBtnPre;
    @InjectView(R.id.btnPlayPause) ImageButton mBtnPlayPause;
    @InjectView(R.id.btnNext) ImageButton mBtnNext;
    @InjectView(R.id.tvCurTime) TextView mTvCurTime;
    @InjectView(R.id.seekBar) SeekBar mSeekBar;
    @InjectView(R.id.tvTotalTime) TextView mTvTotalTime;
    @InjectView(R.id.llControllerBottom) LinearLayout mLlControllerBottom;
    @InjectView(R.id.vsbVoice) VerticalSeekBar mVsbVoice;
    @InjectView(R.id.vsbBrightness) VerticalSeekBar mVsbBrightness;

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

        paths = movieObj.filePath.split(MovieObj.SPLIT_STRING);
        initPlayer();
        mVsbBrightness.setMax(100);
        mVsbBrightness.setProgress(100);
        setScreenBrightness(1);

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
        mVsbBrightness.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser)
                    {
                        setScreenBrightness((float)progress/100);
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                });
        mVsbVoice.setOnSeekBarChangeListener(
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

                    }
                });
    }

    private void setScreenBrightness(float num)
    {
        System.out.println("111111  + " + num);
        if (num < 0.3)
        {
            num = 0.3f;
        }
        WindowManager.LayoutParams layoutParams =
                super.getWindow().getAttributes();
        layoutParams.screenBrightness = num;//设置屏幕的亮度
        super.getWindow().setAttributes(layoutParams);
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
}
