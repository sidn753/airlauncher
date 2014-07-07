package com.rayboot.airlauncher.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class MusicPlayerActivity extends BaseActionBarActivity
{
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.inject(this);
    }
}
