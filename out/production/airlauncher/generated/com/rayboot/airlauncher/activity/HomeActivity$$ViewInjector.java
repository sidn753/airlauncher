// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeActivity$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.activity.HomeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099714, "field 'vpContent'");
    target.vpContent = (android.support.v4.view.ViewPager) view;
    target.pagerBtns = Finder.listOf(
        (android.widget.Button) finder.findRequiredView(source, 2131099710, "pagerBtns"),
        (android.widget.Button) finder.findRequiredView(source, 2131099711, "pagerBtns"),
        (android.widget.Button) finder.findRequiredView(source, 2131099712, "pagerBtns"),
        (android.widget.Button) finder.findRequiredView(source, 2131099713, "pagerBtns")
    );  }

  public static void reset(com.rayboot.airlauncher.activity.HomeActivity target) {
    target.vpContent = null;
    target.pagerBtns = null;
  }
}
