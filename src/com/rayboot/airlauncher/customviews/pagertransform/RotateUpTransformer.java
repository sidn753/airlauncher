package com.rayboot.airlauncher.customviews.pagertransform;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class RotateUpTransformer extends ABaseTransformer {

	private static final float ROT_MOD = -15f;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	protected void onTransform(View view, float position) {
		final float width = view.getWidth();
		final float rotation = ROT_MOD * position;

		view.setPivotX(width * 0.5f);
		view.setPivotY(0f);
		view.setTranslationX(0f);
		view.setRotation(rotation);
	}
	
	@Override
	protected boolean isPagingEnabled() {
		return true;
	}

}