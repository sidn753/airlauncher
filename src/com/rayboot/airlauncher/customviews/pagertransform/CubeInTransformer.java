package com.rayboot.airlauncher.customviews.pagertransform;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class CubeInTransformer extends ABaseTransformer {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onTransform(View view, float position) {
		// Rotate the fragment on the left or right edge
		view.setPivotX(position > 0 ? 0 : view.getWidth());
		view.setPivotY(0);
		view.setRotationY(-90f * position);
	}

	@Override
	public boolean isPagingEnabled() {
		return true;
	}

}
