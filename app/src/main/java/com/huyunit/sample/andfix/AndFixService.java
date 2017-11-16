package com.huyunit.sample.andfix;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;

import com.huyunit.sample.andfix.model.BasePatch;
import com.huyunit.sample.net.nohttp.AppConfig;
import com.huyunit.sample.net.nohttp.HttpListener;
import com.huyunit.sample.net.nohttp.HttpUtil;
import com.huyunit.sample.net.nohttp.JavaBeanRequest;
import com.huyunit.sample.util.LogUtil;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;

/**
 * author: bobo
 * create time: 2017/11/14 下午7:39
 * email: jqbo84@163.com
 * @function: 1. 检查patch文件 2. 下载patch文件 3. 加载下载好的patch文件
 */
public class AndFixService extends Service {
    private static final String FILE_END = ".apatch";
    private static final int UPDATE_APATCH = 0x02;
    private static final int DOWNLOAD_APATCH = 0x01;
    private String mPatchFileDir;
    private String mPatchFile;

    private BasePatch mBasePatchInfo;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_APATCH:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_APATCH:
                    downloadPatch();
                    break;

            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(UPDATE_APATCH);
        return START_NOT_STICKY;//service 被回收之后不会自动重启
    }

    /**
     * 完成目录的构造
     */
    private void init(){
        mPatchFileDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File file = new File(mPatchFileDir);
        try {
            if(file == null || !file.exists()){
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }
    }

    /**
     * 检查服务器是否有patch文件
     */
    private void checkPatchUpdate(){
        Request<BasePatch> request = new JavaBeanRequest("url", BasePatch.class);
        //从服务器端获取的版本号和描述信息数据
        HttpUtil.instance(getApplicationContext()).request(0, request, true, false, new HttpListener<BasePatch>() {
            @Override
            public void onSucceed(int what, Response<BasePatch> response) {
                mBasePatchInfo = response.get();
                LogUtil.d("mBasePatchInfo = " + mBasePatchInfo);
                if(!TextUtils.isEmpty(mBasePatchInfo.getData().getDownloadUrl())){
                    //下载patch文件
                    mHandler.sendEmptyMessage(DOWNLOAD_APATCH);
                } else {
                    stopSelf();
                }
            }

            @Override
            public void onFailed(int what, Response<BasePatch> response) {
                LogUtil.d(response.getException().getMessage());
            }
        });
    }

    //下载请求.
    private DownloadRequest mDownloadRequest;
    private final static String PROGRESS_KEY = "download_single_progress";

    /**
     * 完成patch文件下载
     */
    private void downloadPatch(){
        //初始化patch下载文件路径
        mPatchFile = mPatchFileDir.concat(System.currentTimeMillis() + "").concat(FILE_END);
        if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。
            mDownloadRequest = NoHttp.createDownloadRequest(mBasePatchInfo.getData().getDownloadUrl(), mPatchFileDir, mPatchFile, true, true);
            NoHttp.getDownloadQueueInstance().add(0, mDownloadRequest, downloadListener);
        }
    }

    /**
     * 下载监听
     */
    private DownloadListener downloadListener = new DownloadListener() {

        long allSize;

        @Override
        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
            int progress = AppConfig.getInstance().getInt(PROGRESS_KEY, 0);
            if (allCount != 0) {
                progress = (int) (beforeLength * 100 / allCount);
            }
            updateProgress(progress, beforeLength, 0);
        }

        @Override
        public void onDownloadError(int what, Exception exception) {
            Logger.e(exception);
        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            updateProgress(progress, fileCount, speed);
            AppConfig.getInstance().putInt(PROGRESS_KEY, progress);
        }

        @Override
        public void onFinish(int what, String filePath) {
            Logger.d("Download finish, file path: " + filePath);
            //将我们的下载好的patch文件添加到我们的andfix中
            AndFixPatchManager.getInstance().addPatch(mPatchFile);
        }

        @Override
        public void onCancel(int what) {
            Logger.d("user cancel download");
        }

        private void updateProgress(int progress, long fileSize, long speed) {
            Logger.d("current progress = " + progress);
        }
    };

}
