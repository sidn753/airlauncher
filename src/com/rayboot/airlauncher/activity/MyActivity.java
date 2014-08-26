package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActivity;

public class MyActivity extends BaseActivity
{
    @InjectView(R.id.imageview) ImageView mImageview;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setTheme(android.R.style.Theme_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);

        mImageview.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(MyActivity.this, HomeActivity.class));
            }
        }, 3000);
    }

    public void onEvent(View view)
    {

    }
}
