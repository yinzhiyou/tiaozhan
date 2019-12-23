package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.promoter.Entity.MyQBMXEntity;
import com.example.android.promoter.R;

import java.util.List;

public class JBMXListTYAdapter extends BaseAdapter{
    private Context context;
    private List<MyQBMXEntity.DataBean> list;
    public JBMXListTYAdapter (Context context,List<MyQBMXEntity.DataBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.jbmx_list_ty, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.name.setText(list.get(position).getSportName()+"");
            viewHolder.time.setText(list.get(position).getRecordDate()+"");

            if (list.get(position).getInOrOut()==1){
                viewHolder.jine.setText("+"+list.get(position).getMoney()+"");
                viewHolder.jine.setTextColor(context.getResources().getColor(R.color.hongse));
            }else{
                viewHolder.jine.setText(""+list.get(position).getMoney()+"");
                viewHolder.jine.setTextColor(context.getResources().getColor(R.color.huise));
            }

        return convertView;
    }


    public class ViewHolder {
        private TextView name,time,jine;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.qbmx_list_name);
            time = view.findViewById(R.id.qbmx_list_time);
            jine = view.findViewById(R.id.qbmx_list_jine);

        }


    }
}