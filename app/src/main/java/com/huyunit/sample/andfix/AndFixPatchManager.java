package com.huyunit.sample.andfix;

import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.huyunit.sample.util.UiUtils;

import java.io.IOException;

/**
 * author: bobo
 * create time: 2017/11/14 下午7:39
 * email: jqbo84@163.com
 */
public class AndFixPatchManager {

    private static AndFixPatchManager instance = null;

    private static PatchManager patchManager = null;

    private AndFixPatchManager(){}

    public static AndFixPatchManager getInstance(){
        if(instance == null) {
            synchronized (AndFixPatchManager.class){
                if(instance == null){
                    instance = new AndFixPatchManager();
                }
            }
        }
        return instance;
    }

    public void initPatch(Context context){
        patchManager = new PatchManager(context);
        patchManager.init(UiUtils.getVersionName(context));
        patchManager.loadPatch();
    }

    public void addPatch(String path){
        try {
            Log.e("andfix", patchManager.toString());
            Log.e("andfix", "addPatch()=" + path);
            if(patchManager != null) {
                patchManager.addPatch(path);
                Log.e("andfix", "addPatch()=" + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
