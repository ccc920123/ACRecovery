package com.example.administrator.outchecknewstandard.sp;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.base.DefaultPreference;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;

public class UserPreferences extends DefaultPreference implements IUserPreferences {
    private static final String TAG = "UserPreferences";

    public UserPreferences(Context context) {
        super(context);
    }

    public String getPrefenceName() {
        return TAG;
    }

    public String getStationNumber(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveStationNumber(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getIP(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveIP(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getPort(String key) {
        return getPreferences().getString(key, null);
    }

    public void savePort(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getAPI(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveAPI(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getPdaSbm(String key) {
        return getPreferences().getString(key, null);
    }

    public void savePdaSbm(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getVersionNumber(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveVersionNumber(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getUserName(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveUserName(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getUserAccount(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveUserAccount(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getUserPwd(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveUserPwd(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getJczmc(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveJczmc(String key, String value) {
        getPreferences().putString(key, value).commit();
    }

    public String getJygwh(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveJjygwh(String key, String value) {
        getPreferences().putString(key, value).commit();
    }
}
