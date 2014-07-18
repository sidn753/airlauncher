// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class VideoPlayerActivity$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.activity.VideoPlayerActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099748, "field 'mVV'");
    target.mVV = (com.baidu.cyberplayer.core.BVideoView) view;
    view = finder.findRequiredView(source, 2131099749, "field 'mVVCtl'");
    target.mVVCtl = (com.baidu.cyberplayer.core.BMediaController) view;
  }

  public static void reset(com.rayboot.airlauncher.activity.VideoPlayerActivity target) {
    target.mVV = null;
    target.mVVCtl = null;
  }
}
