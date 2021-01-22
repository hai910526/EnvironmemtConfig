package com.zly.environmemtconfig.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Cerated by xiaoyehai
 * Create date : 2019/10/31 8:27
 * description : SharePreference的工具类
 */
public class SpUtils {

    public static final String SP_NAME = "config";

    public static SharedPreferences sp;

    public static void init(Context context) {
        init(context, SP_NAME);
    }

    public static void init(Context context, String spName) {
        if (sp == null) {
            sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
    }

    public static void putString(String key, String value) {
        //sp.edit().putString(key, value).commit();
        sp.edit().putString(key, value).apply();
    }

    public String getString(final String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }


    public static void putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putLong(String key, final long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, -1L);
    }

    public long getLong(String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }


    public void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(final String key) {
        return getFloat(key, -1f);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }


    public void putStringSet(String key, Set<String> values) {
        sp.edit().putStringSet(key, values).apply();
    }

    public Set<String> getStringSet(String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }


    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * 移除某个key值对应的值
     *
     * @param key 键
     */
    public void remove(@NonNull final String key) {
        sp.edit().remove(key).apply();
    }


    /**
     * 清除所有数据
     */
    public static void clear() {
        sp.edit().clear().apply();
    }

}

