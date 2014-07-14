package com.rayboot.airlauncher.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.baidu.cyberplayer.core.BMediaController;
import com.baidu.cyberplayer.core.BVideoView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.model.MovieObj;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class VideoPlayerActivity extends BaseActionBarActivity
{

    @InjectView(R.id.player) BVideoView mVV;
    @InjectView(R.id.controller) BMediaController mVVCtl;

    MovieObj movieObj;

    String [] paths;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().hide();
        ButterKnife.inject(this);

        movieObj = (MovieObj) getIntent().getSerializableExtra("play_obj");

        paths = movieObj.filePath.split(MovieObj.SPLIT_STRING);
        initPlayer();

        play(paths[0]);
    }

    private void play(String path)
    {
        path = path.replace(MovieObj.SPLIT_STRING, "");
        mVV.setVideoPath(path);
        mVV.start();
    }

    private void initPlayer()
    {
        /**
         * 设置ak及sk的前16位
         */
        BVideoView.setAKSK("t8Qvin5wxrd6GkGVOjnhsEHT",
                "V8KQW7bY7u2ZG6BZ6xeAbSqbk13grmNA");
        /**
         * 注册listener
         */
        mVV.setOnPreparedListener(onPreparedListener);
        mVV.setOnCompletionListener(onCompletionListener);
        mVV.setOnErrorListener(onErrorListener);
        mVV.setOnInfoListener(onInfoListener);


        mVVCtl.setPreNextListener(mPreListener, mNextListener);

        /**
         * 关联BMediaController
         */
        mVV.setMediaController(mVVCtl);
        /**
         * 设置解码模式
         */
        mVV.setDecodeMode(BVideoView.DECODE_HW); //可选择软解模式或硬解模式
        mVV.showCacheInfo(false);
    }

    private BVideoView.OnPreparedListener onPreparedListener =
            new BVideoView.OnPreparedListener()
            {
                @Override public void onPrepared()
                {

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
}
