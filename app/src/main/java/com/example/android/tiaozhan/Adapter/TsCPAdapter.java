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

public class TsCPAdapter extends BaseAdapter {
    private Context context;
    private List<HDXQEntity.DataBean.GetRefereeBean> list;

    public TsCPAdapter(Context context, List<HDXQEntity.DataBean.GetRefereeBean> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.ts_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(viewHolder.my_touxiang!=null) {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.my_touxiang);
        }

        if (viewHolder.ts_name!=null) {
            viewHolder.ts_name.setText(list.get(position).getNickname());
        }
        return convertView;
    }


    public class ViewHolder {
        private ImageView my_touxiang;
        private TextView ts_name;
        public ViewHolder(View view) {
            my_touxiang = view.findViewById(R.id.my_touxiang);
            ts_name = view.findViewById(R.id.ts_name);
        }


    }
}
