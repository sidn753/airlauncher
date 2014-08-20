package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import com.artifex.mupdfdemo.MuPDFActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.adapter.HomePagerAdapter;
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.customviews.LineItemLayout;
import com.rayboot.airlauncher.customviews.TitleViewLayout;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.model.MusicObj;
import com.viewpagerindicator.UnderlinePageIndicator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 15:06
 * @TODO
 */
public class HomeActivity extends BaseActionBarActivity
{
    final int PAGE_LINE_COUNT = 5;
    @InjectViews({ R.id.t1, R.id.t2, R.id.t3 }) TitleViewLayout[]
            titleViewLayouts;
    @InjectViews({ R.id.l1, R.id.l2, R.id.l3 }) LineItemLayout[]
            lineItemLayouts;
    @InjectView(R.id.vpHot) ViewPager mVpHot;
    @InjectView(R.id.vUnderLine) UnderlinePageIndicator mVUnderLine;

    List<MovieObj> movieObjs = new ArrayList<MovieObj>();
    List<BookObj> bookObjs = new ArrayList<BookObj>();
    List<MusicObj> musicObjs = new ArrayList<MusicObj>();
    @InjectView(R.id.tvDesc) TextView mTvDesc;
    @InjectView(R.id.tvTitle) TextView mTvTitle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        ButterKnife.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.argb(32, 0, 0, 0)));

        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.view_slidingmenu);
        menu.addIgnoredView(mVpHot);

        initContent();
    }

    private void initContent()
    {

        movieObjs = MovieObj.getTopObjs(PAGE_LINE_COUNT);
        bookObjs = BookObj.getTopObjs(PAGE_LINE_COUNT);
        musicObjs = MusicObj.getTopObjs(PAGE_LINE_COUNT);

        HomePagerAdapter mAdapter =
                new HomePagerAdapter(this.getSupportFragmentManager(),
                        (ArrayList) movieObjs);
        mVpHot.setAdapter(mAdapter);
        mVUnderLine.setViewPager(mVpHot);
        mVpHot.setCurrentItem(0);
        mTvDesc.setText(movieObjs.get(0).desc);
        mTvTitle.setText(movieObjs.get(0).title);
        mVUnderLine.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override public void onPageScrolled(int i, float v, int i2)
            {

            }

            @Override public void onPageSelected(int i)
            {
                MovieObj fileObj = movieObjs.get(i);
                mTvDesc.setText(fileObj.desc);
                mTvTitle.setText(fileObj.title);
            }

            @Override public void onPageScrollStateChanged(int i)
            {

            }
        });

        titleViewLayouts[0].setType(FileObj.TYPE_MOVIE);
        titleViewLayouts[1].setType(FileObj.TYPE_BOOK);
        titleViewLayouts[2].setType(FileObj.TYPE_MUSIC);
        lineItemLayouts[0].setContent((List) movieObjs, onClickListener,
                FileObj.TYPE_MOVIE);
        lineItemLayouts[1].setContent((List) bookObjs, onClickListener,
                FileObj.TYPE_BOOK);
        lineItemLayouts[2].setContent((List) musicObjs, onClickListener,
                FileObj.TYPE_MUSIC);
    }

    public void doMoreClick(View view)
    {
        Intent intent = new Intent(this, ContentListActivity.class);
        intent.putExtra("content_type", (Integer) view.getTag());
        startActivity(intent);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override public void onClick(View v)
        {
            int type = (Integer) v.getTag(R.string.tag_type);
            Intent intent;
            switch (type)
            {
            case FileObj.TYPE_MOVIE:
                MovieObj movieObj = (MovieObj) v.getTag();
                intent = new Intent(HomeActivity.this,
                        MovieDetailActivity.class);
                intent.putExtra("movie_obj", movieObj);
                startActivity(intent);
                break;
            case FileObj.TYPE_BOOK:
                BookObj bookObj = (BookObj) v.getTag();
                Uri uri = Uri.parse(bookObj.filePath);
                intent = new Intent(HomeActivity.this, MuPDFActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
                break;
            case FileObj.TYPE_MUSIC:
                intent = new Intent(HomeActivity.this,
                        MusicPlayerActivity.class);
                intent.putExtra("music_obj", (MusicObj) v.getTag());
                startActivity(intent);
                break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("设置")
                .setIntent(new Intent(this, SettingActivity.class))
                .setIcon(android.R.drawable.ic_menu_manage)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }
}
