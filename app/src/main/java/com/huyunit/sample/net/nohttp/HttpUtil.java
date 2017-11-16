package com.huyunit.sample.net.nohttp;

import android.app.Activity;
import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * author: bobo
 * create time: 2017/3/28 18:11
 * Email: jqbo84@163.com
 */
public class HttpUtil {
    private Context context;

    /**
     * 用来标记取消。
     */
    private Object object = new Object();

    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    private static HttpUtil instance;

    public static HttpUtil instance(Context context){
        if(instance == null){
            instance = new HttpUtil(context);
        }
        return instance;
    }

    private HttpUtil(Context context){
        this.context = context;
        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue(1);
    }

    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param callback  回调函数。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(int what, Request<T> request, boolean canCancel, boolean isLoading, HttpListener<T> callback) {
        if(mQueue == null) {
            // 初始化请求队列，传入的参数是请求并发值。
            mQueue = NoHttp.newRequestQueue(1);
        }
        request.setCancelSign(object);
        mQueue.add(what, request, new HttpResponseListener<T>((Activity) context, request, callback, canCancel, isLoading));
    }

    public void cancelRequest() {
        if(mQueue != null){
            // 和声明周期绑定，退出时取消这个队列中的所有请求，当然可以在你想取消的时候取消也可以，不一定和声明周期绑定。
            mQueue.cancelBySign(object);

            // 因为回调函数持有了activity，所以退出activity时请停止队列。
            mQueue.stop();
        }
    }

    public void cancelAll() {
        if(mQueue != null) mQueue.cancelAll();
    }

    public void cancelBySign(Object object) {
        if(mQueue != null) mQueue.cancelBySign(object);
    }
}
