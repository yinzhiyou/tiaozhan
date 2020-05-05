package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.YundongTwoEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class HomeShaixuanTwoAdapter extends BaseAdapter {
    private Context context;
    private List<YundongTwoEntity.DataBean> list;
    public HomeShaixuanTwoAdapter (Context context, List<YundongTwoEntity.DataBean> list){
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeShaixuanTwoAdapter.ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.home_shaixuan_one, parent, false);
            viewHolder = new HomeShaixuanTwoAdapter.ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        private TextView textView;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.home_shaixuan_text);
        }


    }
}