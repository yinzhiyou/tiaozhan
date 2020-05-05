package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class QquanAdapter extends BaseAdapter {
    private Context context;
    private List<HDXQEntity.DataBean.GetwaiverInfoBean> list;

    public QquanAdapter(Context context, List<HDXQEntity.DataBean.GetwaiverInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Math.min(5,list.size());
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
        viewHolder.no.setText(list.get(position).getTeam());
        if(viewHolder.my_touxiang!=null) {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.my_touxiang);
        }

        return convertView;
    }


    public class ViewHolder {
        private ImageView my_touxiang;
        private TextView no;

        public ViewHolder(View view) {
            my_touxiang = view.findViewById(R.id.my_touxiang);
            no = view.findViewById(R.id.hdxq_a_grid_no);
        }


    }
}
