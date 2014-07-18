// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeMusicAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.adapter.HomeMusicAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099736, "field 'mIvMusicLogo'");
    target.mIvMusicLogo = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131099807, "field 'mTvMusicName'");
    target.mTvMusicName = (android.widget.TextView) view;
  }

  public static void reset(com.rayboot.airlauncher.adapter.HomeMusicAdapter.ViewHolder target) {
    target.mIvMusicLogo = null;
    target.mTvMusicName = null;
  }
}
