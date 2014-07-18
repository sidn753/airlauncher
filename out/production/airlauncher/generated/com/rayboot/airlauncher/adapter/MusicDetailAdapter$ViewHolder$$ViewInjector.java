// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MusicDetailAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.adapter.MusicDetailAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099808, "field 'mIvPlaying'");
    target.mIvPlaying = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131099807, "field 'mTvMusicName'");
    target.mTvMusicName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099809, "field 'mTvTime'");
    target.mTvTime = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099810, "field 'mTvOwner'");
    target.mTvOwner = (android.widget.TextView) view;
  }

  public static void reset(com.rayboot.airlauncher.adapter.MusicDetailAdapter.ViewHolder target) {
    target.mIvPlaying = null;
    target.mTvMusicName = null;
    target.mTvTime = null;
    target.mTvOwner = null;
  }
}
