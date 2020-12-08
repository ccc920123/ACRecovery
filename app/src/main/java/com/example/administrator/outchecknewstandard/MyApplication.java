package com.example.administrator.outchecknewstandard;

import android.app.Application;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;

import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        PreferenceManager.getInstance().initPreferences(this);
//        Builder builder = new Builder();
//        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();
    }

}