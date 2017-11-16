package com.huyunit.sample.andfix.model;

import java.io.Serializable;

/**
 * author: bobo
 * create time: 2017/11/16 下午3:24
 * email: jqbo84@163.com
 */
public class BasePatch implements Serializable {
    private static final long serialVersionUID = -5712767027267901547L;

    private int code;
    private String message;
    private PatchInfo data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PatchInfo getData() {
        return data;
    }

    public void setData(PatchInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BasePatch{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
