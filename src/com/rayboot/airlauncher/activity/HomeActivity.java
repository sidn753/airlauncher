package com.rayboot.airlauncher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.rayboot.airlauncher.base.BaseActionBarActivity;
import com.rayboot.airlauncher.customviews.pagertransform.ZoomOutSlideTransformer;
import com.rayboot.airlauncher.model.HomeObj;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 15:06
 * @TODO
 */
public class HomeActivity extends BaseActionBarActivity
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

    /**
     * 设置系统栏可见性
     */
    public void setSystemBarVisible(final Activity context,
            boolean visible)
    {
        int flag = context.getWindow()
                .getDecorView()
                .getSystemUiVisibility();   // 获取当前SystemUI显示状态
        // int fullScreen = View.SYSTEM_UI_FLAG_SHOW_FULLSCREEN;
        int fullScreen =
                0x8;   // 4.1 View.java的源码里面隐藏的常量SYSTEM_UI_FLAG_SHOW_FULLSCREEN，其实Eclipse里面也可以调用系统隐藏接口，重新提取下android.jar，这里就不述了。
        if (visible)
        {   // 显示系统栏
            if ((flag & fullScreen) != 0)
            {  // flag标志位中已经拥有全屏标志SYSTEM_UI_FLAG_SHOW_FULLSCREEN
                context.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_VISIBLE);   // 显示系统栏
            }
        }
        else
        {    // 隐藏系统栏
            if ((flag & fullScreen) == 0)
            {  // flag标志位中不存在全屏标志SYSTEM_UI_FLAG_SHOW_FULLSCREEN
                context.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(flag | fullScreen); // 把全屏标志位加进去
            }
        }
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
