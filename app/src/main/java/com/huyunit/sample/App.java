package com.huyunit.sample;

import android.app.Application;

/**
 * author: bobo
 * create time: 2016/10/18 15:25
 * Email: jqbo84@163.com
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
