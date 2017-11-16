package com.huyunit.sample.andfix.model;

import java.io.Serializable;

/**
 * author: bobo
 * create time: 2017/11/16 下午3:25
 * email: jqbo84@163.com
 */
public class PatchInfo implements Serializable {
    private static final long serialVersionUID = -5472382561240583571L;

    private String downloadUrl;//不为空表明有更新

    private String versionName;//本次patch包的版本号

    private String patchMessage;//本次patch包含的相关信息，例如：主要做了那些改动

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPatchMessage() {
        return patchMessage;
    }

    public void setPatchMessage(String patchMessage) {
        this.patchMessage = patchMessage;
    }

    @Override
    public String toString() {
        return "PatchInfo{" +
                "downloadUrl='" + downloadUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                ", patchMessage='" + patchMessage + '\'' +
                '}';
    }
}
