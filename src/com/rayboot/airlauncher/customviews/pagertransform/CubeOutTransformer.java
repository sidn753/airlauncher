package com.rayboot.airlauncher.customviews.pagertransform;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class CubeOutTransformer extends ABaseTransformer {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onTransform(View view, float position) {
		view.setPivotX(position < 0f ? view.getWidth() : 0f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setRotationY(90f * position);
	}

	@Override
	public boolean isPagingEnabled() {
		return true;
	}

}
