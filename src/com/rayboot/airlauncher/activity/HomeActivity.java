package com.rayboot.airlauncher.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import com.rayboot.airlauncher.App;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.adapter.HomePagerAdapter;
import com.rayboot.airlauncher.customviews.pagertransform.ZoomOutSlideTransformer;
import com.rayboot.airlauncher.model.HomeObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 15:06
 * @TODO
 */
public class HomeActivity extends ActionBarActivity
{
    ArrayList<HomeObj> listMainObjs = new ArrayList<HomeObj>();

    @InjectViews(
            { R.id.btnMain, R.id.btnMovies, R.id.btnBooks, R.id.btnMusics })
    List<Button> pagerBtns;
    @InjectView(R.id.vpContent) ViewPager vpContent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        initViewPager();
    }

    private void initViewPager()
    {

        listMainObjs.add(new HomeObj(HomeObj.TYPE_HOME, pagerBtns.get(0)));
        listMainObjs.add(new HomeObj(HomeObj.TYPE_MOVIES, pagerBtns.get(1)));
        listMainObjs.add(new HomeObj(HomeObj.TYPE_BOOKS, pagerBtns.get(2)));
        listMainObjs.add(new HomeObj(HomeObj.TYPE_MUSICS, pagerBtns.get(3)));
        //给ViewPager设置适配器
        vpContent.setAdapter(new HomePagerAdapter(getSupportFragmentManager(),
                listMainObjs));
        onPagerBtnClick(pagerBtns.get(0));
        vpContent.setCurrentItem(0);//设置当前显示标签页为第一页
        try
        {
            if (android.os.Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB)
            {
                vpContent.setPageTransformer(true,
                        ZoomOutSlideTransformer.class.newInstance());
            }
        }
        catch (java.lang.InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override public void onPageScrolled(int i, float v, int i2)
            {

            }

            @Override public void onPageSelected(int i)
            {
                setPagerBtnSelected(pagerBtns.get(i));
            }

            @Override public void onPageScrollStateChanged(int i)
            {

            }
        });
    }

    public void onPagerBtnClick(View view)
    {
        int index = Integer.valueOf((String) view.getTag());
        if (index != vpContent.getCurrentItem())
        {
            vpContent.setCurrentItem(index);
        }
        setPagerBtnSelected(view);
    }

    public void onMoreClick(View view)
    {
        String tag = (String) view.getTag();
        Intent intent = new Intent(this, ContentListActivity.class);
        if (tag.equals("movie"))
        {
            intent.putExtra("content_type", App.PAGER_MOVIE);
        }else if (tag.equals("book"))
        {
            intent.putExtra("content_type", App.PAGER_BOOK);
        }else if (tag.equals("music"))
        {
            intent.putExtra("content_type", App.PAGER_MUSIC);
        }else
        {
            return;
        }
        startActivity(intent);
    }

    public void setPagerBtnSelected(View view)
    {
        for (Button btn : pagerBtns)
        {
            view.setSelected(btn.equals(view));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            menu.add("设置").setIntent(new Intent(this, SettingActivity.class))
                    .setIcon(android.R.drawable.ic_menu_manage)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }
}
