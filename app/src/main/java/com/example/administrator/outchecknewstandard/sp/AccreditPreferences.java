package com.example.administrator.outchecknewstandard.sp;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.base.DefaultPreference;
import com.example.administrator.outchecknewstandard.sp.i.IAccreditPreferences;

public class AccreditPreferences extends DefaultPreference implements IAccreditPreferences {
    private static final String TAG = "AccreditPreferences";

    public AccreditPreferences(Context context) {
        super(context);
    }

    public String getPrefenceName() {
        return TAG;
    }

    public Boolean getIsAccredit(String key) {
        return Boolean.valueOf(getPreferences().getBoolean(key, false));
    }

    public void saveIsAccredit(String key, Boolean value) {
        getPreferences().putBoolean(key, value.booleanValue()).commit();
    }

    public String getLock(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveLock(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getYxrq(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveYxrq(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getAccreditNumber(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveAccreditNumber(String key, String value) {
        getPreferences().putString(key, value).commit();
    }
}
