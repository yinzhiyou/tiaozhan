package com.example.android.promoter.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Entity.TYJBMingxiEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;

import java.util.List;

public class TYMingxiAdapter extends BaseAdapter {
    private Context context;
    private List<TYJBMingxiEntity.DataBean.GoldLstBean> list;

    public TYMingxiAdapter(Context context,List<TYJBMingxiEntity.DataBean.GoldLstBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.item_duishoubi_mx, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getReason()+"");
        viewHolder.time.setText(list.get(position).getCoinsDate()+"");
        LogU.Ld("1608",list.get(position).getGetCoins()+"");
            viewHolder.jine.setText(""+list.get(position).getGetCoins()+"");
            if (list.get(position).getGetCoins().indexOf("-") != -1){
                viewHolder.jine.setTextColor(context.getResources().getColor(R.color.heise));

            }else{
                viewHolder.jine.setTextColor(context.getResources().getColor(R.color.hongse));

            }


        return convertView;
    }


    public class ViewHolder {
        private TextView name,time,jine;
        private ImageView imageView;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.ds_qbmx_list_name);
            time = view.findViewById(R.id.ds_qbmx_list_time);
            jine = view.findViewById(R.id.ds_qbmx_list_jine);

            imageView = view.findViewById(R.id.ds_jbmx_image);

        }


    }
}