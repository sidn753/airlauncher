package com.rayboot.airlauncher.customviews.pagertransform;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class FlipVerticalTransformer extends ABaseTransformer {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onTransform(View view, float position) {
		final float rotation = -180f * position;

		view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
		view.setPivotX(view.getWidth() * 0.5f);
		view.setPivotY(view.getHeight() * 0.5f);
		view.setRotationX(rotation);
	}

}
