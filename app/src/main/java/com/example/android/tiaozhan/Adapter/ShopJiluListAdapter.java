package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.DuihuanjiluEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class ShopJiluListAdapter extends BaseAdapter {
    private Context context;
    private List<DuihuanjiluEntity.DataBean.LstBean> list;

    public ShopJiluListAdapter(Context context, List<DuihuanjiluEntity.DataBean.LstBean> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.shop_jilu_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(list.get(position).getGoodsName());
        viewHolder.beizhu.setText(list.get(position).getComment());
        viewHolder.shuliang.setText("数量："+list.get(position).getAmount()+"个");
        viewHolder.time.setText(list.get(position).getExchagetime().substring(0, list.get(position).getExchagetime().indexOf(" ")));
        viewHolder.heji.setText(list.get(position).getOutCommonCoins()+"");
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getGoodsImg()).into(viewHolder.imageView);


        return convertView;
    }


    public class ViewHolder {
        private TextView name,beizhu,shuliang,time,heji;
        private ImageView imageView;
        public ViewHolder(View view) {

            name = view.findViewById(R.id.dhjl_name);
            beizhu = view.findViewById(R.id.dhjl_beizhu);
            shuliang = view.findViewById(R.id.dhjl_shuliang);
            time = view.findViewById(R.id.dhjl_time);
            heji = view.findViewById(R.id.dhjl_heji);
            imageView = view.findViewById(R.id.dhjl_image);
        }


    }
}
