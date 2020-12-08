package com.example.administrator.outchecknewstandard.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;

public class MobileConfigureUtils {
    public static String getPdaSBM(Context context) {
        return MD5.MD5Encode(((TelephonyManager) context.getSystemService("phone")).getDeviceId() + BluetoothAdapter.getDefaultAdapter().getAddress()).substring(20, 31);
    }

    public static String getVersionName(Context context) throws Exception {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    public static String getAndroidVersion() {
        return VERSION.RELEASE;
    }

    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static String getMobileBrand() {
        return Build.BRAND;
    }
}
