package com.huyunit.sample.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * author: bobo
 * create time: 2017/11/16 下午3:08
 * email: jqbo84@163.com
 */
public class FileUtil {

    /**
     * 得到SD卡根目录.
     */
    public static File getRootPath(Context context) {
        if (FileUtil.sdCardIsAvailable()) {
            return Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            return context.getFilesDir();
        }
    }

    /**
     * SD卡是否可用.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

}
