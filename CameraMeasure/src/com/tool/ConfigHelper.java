package com.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigHelper {
	public static final int DATA_BOOLEAN = 4;
	public static final int DATA_FLOAT = 5;
	public static final int DATA_INT = 2;
	public static final int DATA_LONG = 3;
	public static final int DATA_STRING = 1;
	private static final String TAG = "ConfigHelper";

	public static boolean setSharePref(Context context, String fileName,
			String configKey, Object configValue) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				DATA_STRING);
		SharedPreferences.Editor editor = preferences.edit();
		if (configValue.getClass() == String.class) {
			Object str = configValue;
			editor.putString(configKey, (String) str);
		} else if (configValue.getClass() == Integer.class) {
			Object i = configValue;
			editor.putInt(configKey, ((Integer) i).intValue());
		} else if (configValue.getClass() == Boolean.class) {
			Object b = configValue;
			editor.putBoolean(configKey, ((Boolean) b).booleanValue());
		} else if (configValue.getClass() == Long.class) {
			Object l = configValue;
			editor.putLong(configKey, ((Long) l).longValue());
		} else if (configValue.getClass() == Float.class) {
			Object f = configValue;
			editor.putFloat(configKey, ((Float) f).floatValue());
		} else {
			return false;
		}
		editor.commit();
		return true;
	}

	public static boolean delSharePref(Context context, String fileName,
			String configKey) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				0x1);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(configKey);
		return editor.commit();
	}

	public static boolean delSharePref(Context context, String fileName) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				0x1);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		return editor.commit();
	}

	public static Object getSharePref(Context context, String fileName,
			String configKey, int dataType) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				DATA_STRING);
		switch (dataType) {
		case 1: {
			return preferences.getString(configKey, "");
		}
		case 2: {
			return Integer.valueOf(preferences.getInt(configKey, 0));
		}
		case 3: {
			return Long.valueOf(preferences.getLong(configKey, 0L));
		}
		case 4: {
			return Boolean.valueOf(preferences.getBoolean(configKey, false));
		}
		case 5: {
			return Float.valueOf(preferences.getFloat(configKey, 0.0f));
		}
		}
		return null;
	}
}