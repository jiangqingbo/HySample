package com.huyunit.sample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huyunit.sample.R;
import com.huyunit.sample.adapter.base.BaseArrayAdapter;
import com.huyunit.sample.adapter.base.BaseViewHolder;

/**
 * Created by bobo on 2016/9/26.
 */
public class TextArrayAdapter extends BaseArrayAdapter<String> {

    public TextArrayAdapter(Context context, String[] datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.a_text_list_item, null);
        }
        TextView textView = BaseViewHolder.get(convertView, R.id.tv_name);

        String s = datas[position];

        textView.setText(s);

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
            String name = datas[position];
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }

    }

}
