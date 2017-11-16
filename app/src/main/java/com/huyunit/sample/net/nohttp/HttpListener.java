package com.huyunit.sample.net.nohttp;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * author: bobo
 * create time: 2017/3/3 14:16
 * Email: jqbo84@163.com
 */
public interface HttpListener<T> {

    void onSucceed(int what, Response<T> response);

    void onFailed(int what, Response<T> response);

}
