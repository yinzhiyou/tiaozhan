package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.ProMoneyDetailEntity;
import com.example.android.tiaozhan.R;


import java.util.List;

public class ProMoneyDetailAdapter extends BaseAdapter {

    private Context context;
    private List<ProMoneyDetailEntity.DataBean> list;

    public ProMoneyDetailAdapter(Context context, List<ProMoneyDetailEntity.DataBean> list) {
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
        final ProMoneyDetailAdapter.ViewHolder viewHolder;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.qb_detail_list, parent, false);
            viewHolder = new ProMoneyDetailAdapter.ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProMoneyDetailAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.detail_time.setText(list.get(position).getRecordDate());
        viewHolder.top_text.setText(list.get(position).getRecordReason());

        if (list.get(position).getInOrOut()==1){
            viewHolder.detail_num.setText("+"+list.get(position).getMoney()+"");
            viewHolder.detail_num.setTextColor(context.getResources().getColor(R.color.hongse));
        }else{
            viewHolder.detail_num.setText(""+list.get(position).getMoney()+"");
            viewHolder.detail_num.setTextColor(context.getResources().getColor(R.color.huise));
        }

        return convertView;
    }

    public class ViewHolder {


        TextView detail_num, detail_time,top_text;

        public ViewHolder(View view) {
            detail_time = view.findViewById(R.id.detail_time);
            detail_num = view.findViewById(R.id.detail_num);
            top_text = view.findViewById(R.id.top_text);

        }


    }
}
