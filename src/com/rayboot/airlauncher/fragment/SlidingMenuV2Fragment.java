package com.rayboot.airlauncher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.rayboot.airlauncher.R;
import com.rayboot.airlauncher.base.BaseFragment;
import com.rayboot.airlauncher.util.FontUtils;

/**
 * @author rayboot
 * @from 14-8-26 10:40
 * @TODO
 */
public class SlidingMenuV2Fragment extends BaseFragment
{
    @InjectView(R.id.llSlidingBg) RelativeLayout mRlSlidingBg;
    @InjectViews({ R.id.rbHome, R.id.rbMovie, R.id.rbBook, R.id.rbMusic, })
    RadioButton[] rbs;
    private static SlidingMenuV2Fragment fragment;

    public static SlidingMenuV2Fragment getInstance()
    {
        if (fragment == null)
        {
            fragment = new SlidingMenuV2Fragment();
        }
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sliding_menu_v2, null);
        ButterKnife.inject(this, view);
        FontUtils.overrideFonts(getActivity(), view);
        for (RadioButton rb : rbs)
        {
            rb.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override public void onPause()
    {
        if (rbs.length == 0)
        {
            return;
        }
        for (int i = 0; i < rbs.length; i++)
        {
            final int index = i;
            if (rbs[index] == null)
            {
                continue;
            }
            ObjectAnimator ani =
                    ObjectAnimator.ofFloat(rbs[index], "rotationY", 0, 180)
                            .setDuration(200);
            ViewHelper.setPivotX(rbs[index], 0);
            ViewHelper.setPivotY(rbs[index], 0);
            ani.setStartDelay(index * 150);
            ani.setInterpolator(new AccelerateInterpolator());
            ani.addListener(new Animator.AnimatorListener()
            {
                @Override public void onAnimationStart(Animator animator)
                {
                }

                @Override public void onAnimationEnd(Animator animator)
                {
                    rbs[index].setVisibility(View.INVISIBLE);
                }

                @Override public void onAnimationCancel(Animator animator)
                {

                }

                @Override public void onAnimationRepeat(Animator animator)
                {

                }
            });
            ani.start();
        }
        super.onPause();
    }

    @Override public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
    }

    @Override public void onResume()
    {
        super.onResume();
        if (rbs.length == 0)
        {
            return;
        }
        for (int i = 0; i < rbs.length; i++)
        {
            final int index = i;
            if (rbs[index] == null)
            {
                continue;
            }
            ObjectAnimator ani =
                    ObjectAnimator.ofFloat(rbs[index], "rotationY", 90, 0)
                            .setDuration(150);
            ViewHelper.setPivotX(rbs[index], 0);
            ViewHelper.setPivotY(rbs[index], 0);
            ani.setStartDelay(index * 50);
            ani.setInterpolator(new AccelerateInterpolator());
            ani.addListener(new Animator.AnimatorListener()
            {
                @Override public void onAnimationStart(Animator animator)
                {
                    rbs[index].setVisibility(View.VISIBLE);
                }

                @Override public void onAnimationEnd(Animator animator)
                {

                }

                @Override public void onAnimationCancel(Animator animator)
                {

                }

                @Override public void onAnimationRepeat(Animator animator)
                {

                }
            });
            ani.start();
        }
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public void onEvent(View view)
    {

    }
}
