// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MoviesAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.adapter.MoviesAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099805, "field 'mIvMovieLogo'");
    target.mIvMovieLogo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131099806, "field 'mTvMovieName'");
    target.mTvMovieName = (android.widget.TextView) view;
  }

  public static void reset(com.rayboot.airlauncher.adapter.MoviesAdapter.ViewHolder target) {
    target.mIvMovieLogo = null;
    target.mTvMovieName = null;
  }
}
