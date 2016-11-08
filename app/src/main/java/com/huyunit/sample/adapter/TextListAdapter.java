package com.huyunit.sample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huyunit.sample.R;
import com.huyunit.sample.adapter.base.BaseListAdapter;
import com.huyunit.sample.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by bobo on 2016/9/26.
 */
public class TextListAdapter extends BaseListAdapter<String> {

    public TextListAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.a_text_list_item, null);
        }
        TextView text = BaseViewHolder.get(convertView, R.id.tv_name);

        String s = dataList.get(position);
        text.setText(s);
        convertView.setOnClickListener(new MyOnClickListener(position));
        return convertView;
    }

    private class MyOnClickListener implements View.OnClickListener {

        private int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            String name = dataList.get(position);
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }

    }

}
