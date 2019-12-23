package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.promoter.Entity.GRTXEntity;
import com.example.android.promoter.Entity.YonghuBQEntity;
import com.example.android.promoter.R;

import java.util.List;

public class GRTXGridAdapter extends BaseAdapter {
    private Context context;
//    private List<YonghuBQEntity.DataBean> list;
private List<GRTXEntity.DataBean.UserLabelBean> list;
    public GRTXGridAdapter (Context context,List<GRTXEntity.DataBean.UserLabelBean> list){
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
        final ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.grtx_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.textView.setText(list.get(position).getLabelName());
            viewHolder.shuliang.setText(list.get(position).getLabelCount()+"");
        return convertView;
    }


    public class ViewHolder {
        private TextView textView,shuliang;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.grtx_grid_text);
            shuliang = view.findViewById(R.id.grtx_grid_shuliang);
        }
    }
}