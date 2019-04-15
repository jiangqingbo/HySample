package com.huyunit.sample.tinker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.huyunit.sample.R;
import com.huyunit.sample.fragment.BaseFragment;
import com.huyunit.sample.util.LogUtil;
import com.huyunit.sample.util.ToastUtil;

import java.io.File;

/**
 * author: bobo
 * create time: 2017/11/17 下午12:17
 * email: jqbo84@163.com
 * <p>
 * /storage/emulated/0/Android/data/com.huyunit.sample/cache/tpatch/tinker.apk
 */
public class TestTinkerFragment extends BaseFragment implements View.OnClickListener {

    private static final String FILE_END = ".apk";

    private String mPatchDir;

    private Button loadPatchButton;

    private Button addButtonResource;

    public static TestTinkerFragment getInstance() {
        TestTinkerFragment fragment = new TestTinkerFragment();
        return fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        setContentView(inflater, R.layout.f_test_tinker);

        loadPatchButton = getViewById(R.id.loadPatchButton);
        loadPatchButton.setOnClickListener(this);

        addButtonResource = getViewById(R.id.addButtonResource);
        addButtonResource.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPatchDir = getActivity().getExternalCacheDir().getAbsolutePath() + "/tpatch/";
        File file = new File(mPatchDir);
        if (file == null || !file.exists()) {
            file.mkdir();
        }

        LogUtil.d("" + mPatchDir);
    }

    public void loadPatch() {
        String patchName = getPatchName();
        LogUtil.d("patchName = " + patchName);
        TinkerManager.loadPatch(patchName);
    }

    public String getPatchName() {
        return mPatchDir.concat("tinker").concat(FILE_END);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadPatchButton:
                loadPatch();
                break;
            case R.id.addButtonResource:
                ToastUtil.show(getActivity(), "Tinker加载loadPatchFile.success");
                break;
        }
    }
}
