package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.JSJBListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;

import java.text.DecimalFormat;
import java.util.List;

public class GRTXList1Adapter extends BaseAdapter {
    private Context context;
    private List<JSJBListEntity.DataBean> list;

    public GRTXList1Adapter(Context context, List<JSJBListEntity.DataBean> list) {
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
        DecimalFormat df = new DecimalFormat("0.00");

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.grtx_list1, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getSport_name());
        viewHolder.shuliang.setText(df.format(list.get(position).getTotal()) +"");
        viewHolder.lv1.setText(list.get(position).getNowlevel());
        viewHolder.lv2.setText(list.get(position).getNextlevel());
        viewHolder.bili.setText(df.format(list.get(position).getTotal())+"/"+list.get(position).getMaxcoins());

//        double bili1 = list.get(position).getTotal()-list.get(position).getMincoins();
//        double bili2 = list.get(position).getMaxcoins()-list.get(position).getMincoins();
        double bili1 = list.get(position).getTotal();
        double bili2 = list.get(position).getMaxcoins();
        double bili3 = bili1/bili2;

        LogU.Ld("1608", bili1+"aaaaa"+bili2+"比例"+bili3 );
        viewHolder.progressBar.setProgress((int) (bili3*100));
        return convertView;
    }


    public class ViewHolder {
        private TextView name, shuliang, lv1, lv2,bili;
        private ProgressBar progressBar;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.grtx_list1_name);
            shuliang = view.findViewById(R.id.grtx_list1_shuliang);
            lv1 = view.findViewById(R.id.grtx_list1_lv1);
            lv2 = view.findViewById(R.id.grtx_list1_lv2);
            bili = view.findViewById(R.id.grtx_list1_bili);
            progressBar = view.findViewById(R.id.progressBarLarge);
        }


    }
}
