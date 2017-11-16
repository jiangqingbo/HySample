package com.huyunit.sample.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 后台打印输出类
 * Created by bobo on 2015/11/17 0017.
 */
public class LogUtil {
    public static String customTagPrefix = "Debug";
    public static boolean isDebug = true;
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWTF = true;
    private static final int MAX_LENGTH = 8000;
    private static final String EMPTY = "====>Empty<====";

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

//    public static void showDPI(Context context) {
//        if (!isDebug) return;
//        Configuration config = context.getResources().getConfiguration();
//        LogUtil.showToast(context, context.getString(R.string.dpi, config.smallestScreenWidthDp));
//    }

    public static void showToast(Context context, String msg) {
        if (!isDebug) return;
        if (TextUtils.isEmpty(msg)) msg = EMPTY;
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int num) {
        if (!isDebug) return;
        Toast.makeText(context, String.valueOf(num), Toast.LENGTH_LONG).show();
    }

    public static void d(String content) {
        if (!isDebug || !allowD) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            d(content.substring(0, content.length() / 2));
            d(content.substring(content.length() / 2, content.length()));
        } else {
            Log.d(tag, content);
        }
    }

    public static void d(String content, Throwable tr) {
        if (!isDebug || !allowD) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            d(content.substring(0, content.length() / 2), tr);
            d(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.d(tag, content, tr);
        }
    }

    public static void e(String content) {
        if (!isDebug || !allowE) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            e(content.substring(0, content.length() / 2));
            e(content.substring(content.length() / 2, content.length()));
        } else {
            Log.e(tag, content);
        }
    }

    public static void e(String content, Throwable tr) {
        if (!isDebug || !allowE) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            e(content.substring(0, content.length() / 2), tr);
            e(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.e(tag, content, tr);
        }
    }

    public static void i(String content) {
        if (!isDebug || !allowI) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            i(content.substring(0, content.length() / 2));
            i(content.substring(content.length() / 2, content.length()));
        } else {
            Log.i(tag, content);
        }
    }

    public static void i(String content, Throwable tr) {
        if (!isDebug || !allowI) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            i(content.substring(0, content.length() / 2), tr);
            i(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.i(tag, content, tr);
        }
    }

    public static void v(String content) {
        if (!isDebug || !allowV) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            v(content.substring(0, content.length() / 2));
            v(content.substring(content.length() / 2, content.length()));
        } else {
            Log.v(tag, content);
        }
    }

    public static void v(String content, Throwable tr) {
        if (!isDebug || !allowV) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            v(content.substring(0, content.length() / 2), tr);
            v(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.v(tag, content, tr);
        }
    }

    public static void w(String content) {
        if (!isDebug || !allowW) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            w(content.substring(0, content.length() / 2));
            w(content.substring(content.length() / 2, content.length()));
        } else {
            Log.w(tag, content);
        }
    }

    public static void w(String content, Throwable tr) {
        if (!isDebug || !allowW) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            w(content.substring(0, content.length() / 2), tr);
            w(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.w(tag, content, tr);
        }
    }

    public static void w(Throwable tr) {
        if (!isDebug || !allowW) return;
        String tag = generateTag();
        Log.w(tag, tr);
    }

    public static void wtf(String content) {
        if (!isDebug || !allowWTF) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            wtf(content.substring(0, content.length() / 2));
            wtf(content.substring(content.length() / 2, content.length()));
        } else {
            Log.wtf(tag, content);
        }
    }

    public static void wtf(String content, Throwable tr) {
        if (!isDebug || !allowWTF) return;
        String tag = generateTag();
        if (TextUtils.isEmpty(content)) content = EMPTY;
        if (content.length() > MAX_LENGTH) {
            wtf(content.substring(0, content.length() / 2), tr);
            wtf(content.substring(content.length() / 2, content.length()), tr);
        } else {
            Log.wtf(tag, content, tr);
        }
    }

    public static void wtf(Throwable tr) {
        if (!isDebug || !allowWTF) return;
        String tag = generateTag();
        Log.wtf(tag, tr);
    }

    private static long lastTime;

    public static void t(String msg) {
        if (!isDebug) return;
        long current = System.currentTimeMillis();
        e(msg + ":" + (current - lastTime));
        lastTime = current;
    }

    private static long lastTime2;

    public static void t2(String msg) {
        if (!isDebug) return;
        long current = System.currentTimeMillis();
        e(msg + ":" + (current - lastTime2));
        lastTime2 = current;
    }

    private static long lastTime3;

    public static void t3(String msg) {
        if (!isDebug) return;
        long current = System.currentTimeMillis();
        e(msg + ":" + (current - lastTime3));
        lastTime3 = current;
    }

}
