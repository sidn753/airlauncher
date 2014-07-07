package com.rayboot.airlauncher.activity;

import android.os.Bundle;
import android.view.Window;
import butterknife.ButterKnife;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.model.BookObj;

/**
 * @author rayboot
 * @from 14-6-24 9:08
 * @TODO
 */
public class BookDetailActivity extends BaseActionBarActivity
{

    BookObj bookObj;

    @Override public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.inject(this);

        bookObj = (BookObj) getIntent().getSerializableExtra("book_obj");
    }
}
