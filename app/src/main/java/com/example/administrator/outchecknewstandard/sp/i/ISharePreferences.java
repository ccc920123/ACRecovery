package com.example.administrator.outchecknewstandard.sp.i;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import java.util.Map;
import java.util.Set;

public interface ISharePreferences {
    void apply();

    Editor clear();

    boolean commit();

    boolean contains(String str);

    Map<String, ?> getAll();

    boolean getBoolean(String str, boolean z);

    float getFloat(String str, float f);

    int getInt(String str, int i);

    long getLong(String str, long j);

    String getString(String str, String str2);

    Set<String> getStringSet(String str, Set<String> set);

    Editor putBoolean(String str, boolean z);

    Editor putFloat(String str, float f);

    Editor putInt(String str, int i);

    Editor putLong(String str, long j);

    Editor putString(String str, String str2);

    Editor putStringSet(String str, Set<String> set);

    void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    Editor remove(String str);

    void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);
}
