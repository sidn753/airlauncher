// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SettingActivity$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.activity.SettingActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099746, "field 'mBtnAbout'");
    target.mBtnAbout = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131099747, "field 'mBtnSuperSetting'");
    target.mBtnSuperSetting = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131099748, "field 'mTvAbout'");
    target.mTvAbout = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099750, "field 'mTvPassword'");
    target.mTvPassword = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099751, "field 'mEtPassword'");
    target.mEtPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131099752, "field 'mBtnPassword'");
    target.mBtnPassword = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131099749, "field 'mLlSuperSetting'");
    target.mLlSuperSetting = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2131099753, "field 'mTvVersion'");
    target.mTvVersion = (android.widget.TextView) view;
  }

  public static void reset(com.rayboot.airlauncher.activity.SettingActivity target) {
    target.mBtnAbout = null;
    target.mBtnSuperSetting = null;
    target.mTvAbout = null;
    target.mTvPassword = null;
    target.mEtPassword = null;
    target.mBtnPassword = null;
    target.mLlSuperSetting = null;
    target.mTvVersion = null;
  }
}
