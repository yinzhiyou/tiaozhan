package com.example.android.promoter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.HaoyouEntity;
import com.example.android.promoter.Home.FaqiTiaozhanActivity;
import com.example.android.promoter.Home.HomeGRTXActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.SPUtileFQTZ;

import java.util.List;

public class YQHYListAdapter extends BaseAdapter {

    private Context context;
    private List<HaoyouEntity.DataBean.LstBean> list;
    private SPUtileFQTZ spUtileFQTZ;
    public YQHYListAdapter(Context context, List<HaoyouEntity.DataBean.LstBean> list) {
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
        spUtileFQTZ = new SPUtileFQTZ();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.yqhy_list_adapter, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getUserInfo().getImgURL()).into(viewHolder.touxiang);

        if (list.get(position).getUserInfo().getSex() == 0){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        }else   if (list.get(position).getUserInfo().getSex() == 1){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }
        if (list.get(position).getHightName().equals("羽毛球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        }else  if (list.get(position).getHightName().equals("乒乓球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        }else if (list.get(position).getHightName().equals("台球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        }else if (list.get(position).getHightName().equals("篮球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        }else if (list.get(position).getHightName().equals("足球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        }else if (list.get(position).getHightName().equals("排球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        }else if (list.get(position).getHightName().equals("网球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        }else if (list.get(position).getHightName().equals("高尔夫")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        viewHolder.jibie.setText(list.get(position).getHightLevel());
        viewHolder.nianling.setText("年龄:"+list.get(position).getUserInfo().getAge()+"岁");
        viewHolder.tizhong.setText("体重:"+list.get(position).getUserInfo().getWeight()+"kg");
        viewHolder.shengao.setText("身高:"+list.get(position).getUserInfo().getTall()+"cm");
        viewHolder.yundong.setText("喜爱运动项目:"+list.get(position).getSportidNameStr());

        viewHolder.touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//接收
                intent.setClass(context,HomeGRTXActivity.class);
                bundle.putString("uid",list.get(position).getUuid());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        viewHolder.tiaozhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                Bundle bundle = new Bundle();//接收
                intent.setClass(context,FaqiTiaozhanActivity.class);
//                bundle.putString("uid",list.get(position).getUuid());
//                intent.putExtras(bundle);

                spUtileFQTZ.put(context, "YQtab","2");
                spUtileFQTZ.put(context, "YQtouxiang",list.get(position).getUserInfo().getImgURL());
                spUtileFQTZ.put(context, "YQuuid",list.get(position).getUuid());
                spUtileFQTZ.put(context, "YQdengji",list.get(position).getHightLevel());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, sex, qiuImage;
        private TextView name, jibie, nianling, tizhong, shengao, yundong ;
        private RelativeLayout tiaozhan;
        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.haoyou_touxiang);

            sex = view.findViewById(R.id.haoyou_sex);
            qiuImage = view.findViewById(R.id.haoyou_image_qiu);
            name = view.findViewById(R.id.haoyou_name);
            jibie = view.findViewById(R.id.haoyou_jibie);
            nianling = view.findViewById(R.id.haoyou_nianling);
            tizhong = view.findViewById(R.id.haoyou_tizhong);
            shengao = view.findViewById(R.id.haoyou_shengao);
            yundong = view.findViewById(R.id.haoyou_yundong);
            tiaozhan = view.findViewById(R.id.haoyou_anniu);

        }

    }
}