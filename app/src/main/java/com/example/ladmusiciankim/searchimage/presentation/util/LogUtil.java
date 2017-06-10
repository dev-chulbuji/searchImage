package com.example.ladmusiciankim.searchimage.presentation.util;

import android.util.Log;

import com.example.ladmusiciankim.searchimage.BuildConfig;

public class LogUtil {
    private static final boolean isDebug = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (isDebug) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) Log.e(tag, msg);
    }
}
