package com.example.administrator.outchecknewstandard.sp.base;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.i.IPreference;
import com.example.administrator.outchecknewstandard.sp.i.ISharePreferences;

public class DefaultPreference implements IPreference {
    private String TAG = "DefaultPreference";
    private ISharePreferences sharePreferences;

    public DefaultPreference(Context context) {
        this.sharePreferences = new AnchePreference(context, getPrefenceName());
    }

    public String getPrefenceName() {
        return this.TAG;
    }

    public ISharePreferences getPreferences() {
        return this.sharePreferences;
    }

    public void commit() {
        getPreferences().commit();
    }
}
