// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeMoviesFragment$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.fragment.HomeMoviesFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099800, "field 'mC4ilImg'");
    target.mC4ilImg = (com.rayboot.airlauncher.customviews.Common4ImageLayout) view;
    view = finder.findRequiredView(source, 2131099801, "field 'mC6ilImg'");
    target.mC6ilImg = (com.rayboot.airlauncher.customviews.Common6ImageLayout) view;
  }

  public static void reset(com.rayboot.airlauncher.fragment.HomeMoviesFragment target) {
    target.mC4ilImg = null;
    target.mC6ilImg = null;
  }
}
