package com.huyunit.sample.net.nohttp;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * 自定义JavaBean请求。
 * author: bobo
 * create time: 2017/3/28 18:11
 * Email: jqbo84@163.com
 */
public class JavaBeanRequest<T> extends RestRequest<T> {

    private Class<T> clazz;

    public JavaBeanRequest(String url, Class<T> clazz) {
        this(url, RequestMethod.GET, clazz);
    }

    public JavaBeanRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        this.clazz = clazz;
    }

    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String response = StringRequest.parseResponseString(responseHeaders, responseBody);

        // 这里如果数据格式错误，或者解析失败，会在失败的回调方法中返回 ParseError 异常。
        return JSON.parseObject(response, clazz);
    }
}
