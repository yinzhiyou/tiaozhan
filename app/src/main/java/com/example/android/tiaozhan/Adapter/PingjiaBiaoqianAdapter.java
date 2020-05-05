package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class PingjiaBiaoqianAdapter extends BaseAdapter {
    private Context context;
//    private List<PingjiaListEntity.DataBean.LabelBean> list;
    private List<BiaoqianEntity.DataBean> list;
    public PingjiaBiaoqianAdapter(Context context, List<BiaoqianEntity.DataBean> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.grtx_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.name.setText(list.get(position).getLabelName());

        return convertView;
    }


    public class ViewHolder {

        private TextView name;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.grtx_grid_text);


        }


    }
}
