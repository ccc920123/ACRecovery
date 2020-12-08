package com.example.administrator.outchecknewstandard.sp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.example.administrator.outchecknewstandard.sp.i.ISharePreferences;
import java.util.Map;
import java.util.Set;

public class AnchePreference implements ISharePreferences {
    private Editor editor = null;
    private SharedPreferences sharedPreferences = null;

    @SuppressLint({"CommitPrefEdits"})
    public AnchePreference(Context context, String name) {
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        this.editor = this.sharedPreferences.edit();
    }

    public Editor putString(String key, String value) {
        return this.editor.putString(key, value);
    }

    public Editor putStringSet(String key, Set<String> values) {
        return this.editor.putStringSet(key, values);
    }

    public Editor putInt(String key, int value) {
        return this.editor.putInt(key, value);
    }

    public Editor putLong(String key, long value) {
        return this.editor.putLong(key, value);
    }

    public Editor putFloat(String key, float value) {
        return this.editor.putFloat(key, value);
    }

    public Editor putBoolean(String key, boolean value) {
        return this.editor.putBoolean(key, value);
    }

    public Editor remove(String key) {
        return this.editor.remove(key);
    }

    public Editor clear() {
        return this.editor.clear();
    }

    public boolean commit() {
        return this.editor.commit();
    }

    public void apply() {
        this.editor.apply();
    }

    public Map<String, ?> getAll() {
        return this.sharedPreferences.getAll();
    }

    public String getString(String key, String defValue) {
        return this.sharedPreferences.getString(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        return this.sharedPreferences.getStringSet(key, defValues);
    }

    public int getInt(String key, int defValue) {
        return this.sharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return this.sharedPreferences.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return this.sharedPreferences.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.sharedPreferences.getBoolean(key, defValue);
    }

    public boolean contains(String key) {
        return this.sharedPreferences.contains(key);
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
