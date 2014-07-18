// Generated code from Butter Knife. Do not modify!
package com.rayboot.airlauncher.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ContentListActivity$$ViewInjector {
  public static void inject(Finder finder, final com.rayboot.airlauncher.activity.ContentListActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099708, "field 'mGvContent'");
    target.mGvContent = (android.widget.GridView) view;
  }

  public static void reset(com.rayboot.airlauncher.activity.ContentListActivity target) {
    target.mGvContent = null;
  }
}
