package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.android.tiaozhan.R;

import java.util.List;

public class MyjingcaiListGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyjingcaiListGridAdapter(Context context, List<String> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.my_jingcai_list_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position)).into(viewHolder.imageView);

        return convertView;
    }


    public class ViewHolder {
        private ImageView imageView;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.my_jingcai_list_grid_image);
        }


    }
}