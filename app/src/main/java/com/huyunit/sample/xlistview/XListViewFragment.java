package com.huyunit.sample.xlistview;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.huyunit.sample.R;
import com.huyunit.sample.adapter.TextListAdapter;
import com.huyunit.sample.fragment.BaseFragment;
import com.huyunit.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: bobo
 * create time: 2016/11/8 18:16
 * Email: jqbo84@163.com
 */
public class XListViewFragment extends BaseFragment implements XListView.IXListViewListener{

    private XListView xListView;

    private TextListAdapter textListAdapter;

    private List<String> sList = new ArrayList<>();

    public static XListViewFragment getInstance() {
        XListViewFragment fragment = new XListViewFragment();

        return fragment;
    }

    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        setContentView(inflater, R.layout.f_xlistview);
        xListView = getViewById(R.id.xlistview);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        textListAdapter = new TextListAdapter(getActivity(), sList);
        xListView.setAdapter(textListAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initData(0);
    }

    public void refreshListView() {
        textListAdapter.dataList = sList;
        textListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        initData(1);
    }

    @Override
    public void onLoadMore() {
        initData(2);
    }

    public void initData(int refreshModel) {
        int n = sList.size();
        if (refreshModel == 1) {
            for (int i = n; i < n + 15; i++) {
                sList.add(0, "XListView_Header_" + (i + 1));
            }
            xListView.stopRefresh();
        } else if (refreshModel == 2) {
            for (int i = n; i < n + 15; i++) {
                sList.add("XListView_Footer_" + (i + 1));
            }
            xListView.stopLoadMore();
        } else {
            for (int i = 0; i < 15; i++) {
                sList.add("XListView_" + (i + 1));
            }
        }
        refreshListView();
    }
}
