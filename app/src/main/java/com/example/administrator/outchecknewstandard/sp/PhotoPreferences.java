package com.example.administrator.outchecknewstandard.sp;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.base.DefaultPreference;
import com.example.administrator.outchecknewstandard.sp.i.IPhotoPreferences;

public class PhotoPreferences extends DefaultPreference implements IPhotoPreferences {
    private static final String TAG = "PhotoPreferences";

    public PhotoPreferences(Context context) {
        super(context);
    }

    public String getPrefenceName() {
        return TAG;
    }

    public String getScreenFilePath(String key) {
        return getPreferences().getString(key, null);
    }

    public void saveScreenFilePath(String key, String value) {
        getPreferences().putString(key, value).commit();
    }
}
