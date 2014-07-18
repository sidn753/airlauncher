// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeMainFragment$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.fragment.HomeMainFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099795, "field 'mAd'");
    target.mAd = (com.rayboot.airlauncher.customviews.CommonImage) view;
    view = finder.findRequiredView(source, 2131099797, "field 'mHotMovies'");
    target.mHotMovies = (com.rayboot.airlauncher.customviews.Common3ImageLayout) view;
    view = finder.findRequiredView(source, 2131099799, "field 'mHotBooks'");
    target.mHotBooks = (com.rayboot.airlauncher.customviews.Common3ImageLayout) view;
  }

  public static void reset(com.rayboot.airlauncher.fragment.HomeMainFragment target) {
    target.mAd = null;
    target.mHotMovies = null;
    target.mHotBooks = null;
  }
}
