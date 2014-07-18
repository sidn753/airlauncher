// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeBookAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.adapter.HomeBookAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099803, "field 'mIvBookLogo'");
    target.mIvBookLogo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131099804, "field 'mTvBookTitle'");
    target.mTvBookTitle = (android.widget.TextView) view;
  }

  public static void reset(com.rayboot.airlauncher.adapter.HomeBookAdapter.ViewHolder target) {
    target.mIvBookLogo = null;
    target.mTvBookTitle = null;
  }
}
