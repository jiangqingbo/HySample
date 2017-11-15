package com.huyunit.sample.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * author: bobo
 * create time: 2017/11/14 下午7:43
 * email: jqbo84@163.com
 */
public class UiUtils {

    /**
     * 获取APP版本号
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        String versionName = "1.0.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void log(){
        String message = "AndFix Bug, at new versionName v1.0.0";
        Log.e("andfix", message);
        log("andfix.log(msg).method");
    }

    public static void log(String msg){
        Log.e("andfix", msg);
    }
}
