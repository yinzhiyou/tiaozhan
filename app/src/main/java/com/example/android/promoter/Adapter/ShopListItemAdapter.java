package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.R;

import java.util.List;

public class ShopListItemAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public ShopListItemAdapter(Context context, List<String> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.shop_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position)).into(viewHolder.imageView);
        return convertView;
    }


    public class ViewHolder {
        private ImageView  imageView;
        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.shop_item_image);
        }


    }
}
