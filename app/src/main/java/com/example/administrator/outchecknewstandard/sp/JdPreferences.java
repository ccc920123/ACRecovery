package com.example.administrator.outchecknewstandard.sp;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.base.DefaultPreference;
import com.example.administrator.outchecknewstandard.sp.i.IJdPreferences;

public class JdPreferences extends DefaultPreference implements IJdPreferences {
    private static final String TAG = "JdPreferences";

    public JdPreferences(Context context) {
        super(context);
    }

    public String getPrefenceName() {
        return TAG;
    }

    public String getJdJygwh(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveJdJygwh(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getJdlx(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveJdlx(String key, String value) {
        getPreferences().putString(key, value).commit();
    }
}
