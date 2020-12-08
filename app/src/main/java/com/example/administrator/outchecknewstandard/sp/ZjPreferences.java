package com.example.administrator.outchecknewstandard.sp;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.base.DefaultPreference;
import com.example.administrator.outchecknewstandard.sp.i.IZjPreferences;

public class ZjPreferences extends DefaultPreference implements IZjPreferences {
    private static final String TAG = "ZjPreferences";

    public ZjPreferences(Context context) {
        super(context);
    }

    public String getPrefenceName() {
        return TAG;
    }

    public String getZjJygwh(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveZjJygwh(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getZjlx(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveZjlx(String key, String value) {
        getPreferences().putString(key, value).commit();
    }
}
