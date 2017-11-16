package com.huyunit.sample.net.nohttp;

import android.content.Context;
import android.content.SharedPreferences;

import com.huyunit.sample.App;
import com.huyunit.sample.util.FileUtil;
import com.yanzhenjie.nohttp.tools.IOUtils;

/**
 * author: bobo
 * create time: 2017/3/30 13:26
 * Email: jqbo84@163.com
 */
public class AppConfig {
    private static AppConfig appConfig;

    private SharedPreferences preferences;

    /**
     * App根目录.
     */
    public String APP_PATH_ROOT;

    private AppConfig() {
        preferences = App.getInstance().getSharedPreferences("sp_huyunit_sample", Context.MODE_PRIVATE);

        APP_PATH_ROOT = FileUtil.getRootPath(App.getInstance()).getAbsolutePath();
    }

    public static AppConfig getInstance() {
        if (appConfig == null)
            synchronized (AppConfig.class) {
                if (appConfig == null)
                    appConfig = new AppConfig();
            }
        return appConfig;
    }

    public void initialize() {
        IOUtils.createFolder(APP_PATH_ROOT);
    }

    public void putInt(String key, int value) {
        preferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void putLong(String key, long value) {
        preferences.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public void putFloat(String key, float value) {
        preferences.edit().putFloat(key, value).commit();
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }
}
