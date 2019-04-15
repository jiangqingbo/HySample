package com.huyunit.sample.tinker;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * author: bobo
 * create time: 2017/11/17 上午12:39
 * email: jqbo84@163.com
 */
public class TinkerManager {

    private static boolean isInstalled = false;

    private static ApplicationLike mAppLike;

    public static void installTinker(ApplicationLike applicationLike){
        mAppLike = applicationLike;
        if(isInstalled){
            return;
        }
        TinkerInstaller.install(mAppLike);//完成Tinker初始化
        isInstalled = true;
    }

    /**
     * 完成patch文件的加载
     * @param path
     */
    public static void loadPatch(String path){
        if(Tinker.isTinkerInstalled()){
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    /**
     * 通过ApplicationLike获取Context
     * @return
     */
    private static Context getApplicationContext(){
        if(mAppLike != null){
            return mAppLike.getApplication();
        }
        return null;
    }

}
