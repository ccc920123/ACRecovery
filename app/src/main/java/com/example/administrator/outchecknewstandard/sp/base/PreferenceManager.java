package com.example.administrator.outchecknewstandard.sp.base;

import android.content.Context;
import com.example.administrator.outchecknewstandard.sp.AccreditPreferences;
import com.example.administrator.outchecknewstandard.sp.JdPreferences;
import com.example.administrator.outchecknewstandard.sp.PhotoPreferences;
import com.example.administrator.outchecknewstandard.sp.UserPreferences;
import com.example.administrator.outchecknewstandard.sp.ZjPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IAccreditPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IJdPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IPhotoPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IZjPreferences;

public class PreferenceManager {
    private static volatile PreferenceManager instance;
    private IAccreditPreferences accreditPreferences;
    private IJdPreferences jdPreferences;
    private IPhotoPreferences photoPreferences;
    private IUserPreferences userPreferences;
    private IZjPreferences zjPreferences;

    public static PreferenceManager getInstance() {
        if (instance == null) {
            synchronized (PreferenceManager.class) {
                if (instance == null) {
                    instance = new PreferenceManager();
                }
            }
        }
        return instance;
    }

    public IUserPreferences getUserPreferences() {
        return this.userPreferences;
    }

    public void setUserPreferences(IUserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public IPhotoPreferences getPhotoPreferences() {
        return this.photoPreferences;
    }

    public void setPhotoPreferences(IPhotoPreferences photoPreferences) {
        this.photoPreferences = photoPreferences;
    }

    public IJdPreferences getJdPreferences() {
        return this.jdPreferences;
    }

    public void setJdPreferences(IJdPreferences jdPreferences) {
        this.jdPreferences = jdPreferences;
    }

    public IZjPreferences getZjPreferences() {
        return this.zjPreferences;
    }

    public void setZjPreferences(IZjPreferences zjPreferences) {
        this.zjPreferences = zjPreferences;
    }

    public IAccreditPreferences getAccreditPreferences() {
        return this.accreditPreferences;
    }

    public void setAccreditPreferences(IAccreditPreferences accreditPreferences) {
        this.accreditPreferences = accreditPreferences;
    }

    public void initPreferences(Context context) {
        getInstance().setUserPreferences(new UserPreferences(context));
        getInstance().setPhotoPreferences(new PhotoPreferences(context));
        getInstance().setJdPreferences(new JdPreferences(context));
        getInstance().setZjPreferences(new ZjPreferences(context));
        getInstance().setAccreditPreferences(new AccreditPreferences(context));
    }
}
