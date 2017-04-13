package com.shoplex.bible.biblelock.utils;

/**
 * Created by qsk on 2017/4/13.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;

import java.util.Map;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 *
 * @author xiaanming
 */
public class SharedPreferencesUtils {
    private static final String TAG = "SharedPreferencesUtils";
    public static String FILLNAME = "config";

    /**
     * 存入某个key对应的value值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        Log.i(TAG,"put = 1");
        SharedPreferences sp = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            Log.i(TAG,"put = 2");
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        Log.i(TAG,"put = 3");
        SharedPreferencesCompat.EditorCompat.getInstance().apply(edit);
    }

    /**
     * 得到某个key对应的值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object get(Context context, String key, Object defValue) {
        Log.i(TAG,"put = 11");
        SharedPreferences sp = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            Log.i(TAG,"put = 22");
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        } else {
            Log.i(TAG,"put = 33");

        }
        Log.i(TAG,"put = 44");

        return null;
    }

    /**
     * 返回所有数据
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.remove(key);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(edit);
    }

    /**
     * 清除所有内容
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.clear();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(edit);
    }

}
