package com.rayboot.airlauncher.customviews.pagertransform;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class ForegroundToBackgroundTransformer extends ABaseTransformer {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onTransform(View view, float position) {
		final float height = view.getHeight();
		final float width = view.getWidth();
		final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

		view.setScaleX(scale);
		view.setScaleY(scale);
		view.setPivotX(width * 0.5f);
		view.setPivotY(height * 0.5f);
		view.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);
	}

	private static final float min(float val, float min) {
		return val < min ? min : val;
	}

}
