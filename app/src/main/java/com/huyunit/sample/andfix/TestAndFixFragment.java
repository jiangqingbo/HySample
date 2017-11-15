package com.huyunit.sample.andfix;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.huyunit.sample.R;
import com.huyunit.sample.fragment.BaseFragment;
import com.huyunit.sample.util.UiUtils;

import java.io.File;

/**
 * author: bobo
 * create time: 2017/11/14 下午7:51
 * email: jqbo84@163.com
 * xiaomi: /storage/emulated/0/Android/data/com.huyunit.sample/cache/apatch/
 */
public class TestAndFixFragment extends BaseFragment implements View.OnClickListener{

    private static final String FILE_END = ".apatch";

    private String mPatchDir;

    private Button generateButton, fixButton;

    public static TestAndFixFragment getInstance(){
        TestAndFixFragment fragment = new TestAndFixFragment();
        return fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        setContentView(inflater, R.layout.f_test_andfix);
        generateButton = getViewById(R.id.generateButton);
        fixButton = getViewById(R.id.fixButton);
        generateButton.setOnClickListener(this);
        fixButton.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPatchDir = getActivity().getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File file = new File(mPatchDir);
        if(file == null || !file.exists()){
            file.mkdir();
        }

        Log.d("andfix", "" + mPatchDir);
    }

    public void generateBug(){
        UiUtils.log();
    }

    public void fixBug(){
        String filePath = getPatchName();
        Log.e("andfix", filePath);
        AndFixPatchManager.getInstance().addPatch(filePath);
    }

    private String getPatchName(){
        return mPatchDir.concat("andfix").concat(FILE_END);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generateButton:
                generateBug();
                break;
            case R.id.fixButton:
                fixBug();
                break;
        }
    }
}
