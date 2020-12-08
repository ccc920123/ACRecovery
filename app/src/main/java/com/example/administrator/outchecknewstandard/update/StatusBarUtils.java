package com.example.administrator.outchecknewstandard.update;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;

public class StatusBarUtils {
    public static int getHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        Log.d(StatusBarUtils.class.getSimpleName(), "statusBarHeight--->" + statusBarHeight);
        if (isFlymeOs4x()) {
            return statusBarHeight * 2;
        }
        return statusBarHeight;
    }

    public static boolean isFlymeOs4x() {
        if (!"4.4.4".equals(VERSION.RELEASE)) {
            return false;
        }
        String sysIncrement = VERSION.INCREMENTAL;
        String displayId = Build.DISPLAY;
        if (TextUtils.isEmpty(sysIncrement)) {
            return displayId.contains("Flyme OS 4");
        }
        return sysIncrement.contains("Flyme_OS_4");
    }
}
