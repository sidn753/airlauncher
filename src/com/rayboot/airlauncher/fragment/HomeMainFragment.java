package com.rayboot.airlauncher.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import com.artifex.mupdfdemo.MuPDFActivity;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.activity.MovieDetailActivity;
import com.rayboot.airlauncher.activity.MusicPlayerActivity;
import com.rayboot.airlauncher.adapter.HomePagerAdapter;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.customviews.LineItemLayout;
import com.rayboot.airlauncher.customviews.TitleViewLayout;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.FileObj;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.model.MusicObj;
import com.rayboot.airlauncher.util.FontUtils;
import com.viewpagerindicator.UnderlinePageIndicator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 14:51
 * @TODO
 */
public class HomeMainFragment extends BaseFragment
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
    @InjectView(R.id.root) FrameLayout mRoot;

    public static HomeMainFragment newInstance()
    {
        HomeMainFragment fragment = new HomeMainFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View vFragment =
                inflater.inflate(R.layout.fragment_home_main, container, false);
        ButterKnife.inject(this, vFragment);

        initContent();
        FontUtils.overrideFonts(getActivity(), mRoot);
        return vFragment;
    }

    private void initContent()
    {
        movieObjs = MovieObj.getTopObjs(PAGE_LINE_COUNT);
        bookObjs = BookObj.getTopObjs(PAGE_LINE_COUNT);
        musicObjs = MusicObj.getTopObjs(PAGE_LINE_COUNT);
        HomePagerAdapter mAdapter =
                new HomePagerAdapter(this.getChildFragmentManager(),
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
                intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movie_obj", movieObj);
                startActivity(intent);
                break;
            case FileObj.TYPE_BOOK:
                BookObj bookObj = (BookObj) v.getTag();
                Uri uri = Uri.parse(bookObj.filePath);
                intent = new Intent(getActivity(), MuPDFActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
                break;
            case FileObj.TYPE_MUSIC:
                intent = new Intent(getActivity(), MusicPlayerActivity.class);
                intent.putExtra("music_obj", (MusicObj) v.getTag());
                startActivity(intent);
                break;
            }
        }
    };

    public void onEvent(View view)
    {

    }
}
