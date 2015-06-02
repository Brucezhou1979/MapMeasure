package com.tool;

import android.app.Activity;
import android.util.DisplayMetrics;

public class PublicData {

	public static String SHARED_PRE_FILE_NAME = "setting";
	public static String CAMERA_HEIGHT = "CAMERA_HEIGHT";
	public static String WALKSIZE = "WALKSIZE";
	public static String CCD = "CCD";

	public static float getDensity(Activity mActivity) {
		DisplayMetrics metrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.density;
	}

	public static float getDensityDpi(Activity mActivity) {
		DisplayMetrics metrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return (float) metrics.densityDpi;
	}

	public static int[] getResolution(Activity mActivity) {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return new int[] { dm.widthPixels, dm.heightPixels };
	}

	public static float[] getResolutionDPI(Activity mActivity) {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return new float[] { dm.xdpi, dm.ydpi };
	}
}
