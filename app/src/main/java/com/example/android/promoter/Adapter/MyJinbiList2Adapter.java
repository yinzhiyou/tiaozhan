package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.promoter.Entity.JBSMEntity;
import com.example.android.promoter.R;

import java.util.List;

public class MyJinbiList2Adapter extends BaseAdapter {
    private Context context;
    private List<JBSMEntity.DataBean> list;
    public MyJinbiList2Adapter (Context context,List<JBSMEntity.DataBean> list){
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
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.my_jinbi_list2, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("LV"+list.get(position).getLevel()+":专用金币数（"+list.get(position).getMincoins()+"-"
                +list.get(position).getMaxcoins()+"）；");

        return convertView;
    }


    public class ViewHolder {
        private TextView textView;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.my_jinbi_list2_text);
        }


    }
}