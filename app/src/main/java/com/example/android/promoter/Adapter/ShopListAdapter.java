package com.example.android.promoter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.ShopEntity;
import com.example.android.promoter.Home.TianjiaDizhiActivity;
import com.example.android.promoter.R;

import java.util.List;

public class ShopListAdapter extends BaseAdapter {
    private Context context;
    private List<ShopEntity.DataBean.LstBean> list;
    public ShopListAdapter(Context context,List<ShopEntity.DataBean.LstBean> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.shop_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgpath()).into(viewHolder.imageView);
        viewHolder.jiage.setText(list.get(position).getCost()+"金币");
        viewHolder.shichangjiage.setText(list.get(position).getPrice()+"");
        viewHolder.shichangjiage.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.lijiduihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(context, TianjiaDizhiActivity.class);
                bundle.putString("uid",list.get(position).getUUID());
                bundle.putInt("jiage",list.get(position).getCost());
                bundle.putInt("shu",1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        private ImageView imageView;
        private TextView jiage,shichangjiage,name,lijiduihuan;
        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.shop_list_image);
            jiage = view.findViewById(R.id.shop_list_jiage);
            shichangjiage = view.findViewById(R.id.shop_list_shichangjiage);
            name = view.findViewById(R.id.shop_list_name);
            lijiduihuan = view.findViewById(R.id.shop_list_lijiduihuan);
        }


    }
}
