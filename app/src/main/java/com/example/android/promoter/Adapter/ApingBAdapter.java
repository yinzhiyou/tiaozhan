package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.HDXQEntity;
import com.example.android.promoter.R;


import java.util.List;

public class ApingBAdapter extends BaseAdapter {
    private Context context;
    private List<HDXQEntity.DataBean.AdrawBuserInfoBean> list;
    public ApingBAdapter (Context context, List<HDXQEntity.DataBean.AdrawBuserInfoBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.ayingb_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(viewHolder.my_touxiang!=null) {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.my_touxiang);
        }
        return convertView;
    }


    public class ViewHolder {
        private ImageView my_touxiang;
        public ViewHolder(View view) {
            my_touxiang = view.findViewById(R.id.my_touxiang);
        }


    }
}
