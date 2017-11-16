package com.huyunit.sample.util;

import android.content.Context;
import android.widget.Toast;

/**
 * author: bobo
 * create time: 2017/11/16 下午3:15
 * email: jqbo84@163.com
 */
public class ToastUtil {

    public static void show(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int resId){
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

}
