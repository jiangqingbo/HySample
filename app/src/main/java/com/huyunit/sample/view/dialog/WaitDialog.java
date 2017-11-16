package com.huyunit.sample.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.huyunit.sample.R;

/**
 * author: bobo
 * create time: 2017/11/16 下午3:11
 * email: jqbo84@163.com
 */
public class WaitDialog extends ProgressDialog {
    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(context.getText(R.string.wait_dialog_title));
    }
}
