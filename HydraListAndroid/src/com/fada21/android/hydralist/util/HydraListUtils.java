package com.fada21.android.hydralist.util;

import android.util.Log;

import com.fada21.android.hydralist.BuildConfig;

public class HydraListUtils {

	public static void logd(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void logd(String tag, String msg, Throwable tr) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg, tr);
		}
	}
}
