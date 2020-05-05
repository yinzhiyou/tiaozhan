package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HomeListEntity;
import com.example.android.tiaozhan.R;

import java.text.DecimalFormat;
import java.util.List;

public class HomeListAdapter extends BaseAdapter {
    private Context context;
    private List<HomeListEntity.DataBean.ActiveLstBean> list;
    public HomeListAdapter(Context context,List<HomeListEntity.DataBean.ActiveLstBean> list){
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
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//            viewHolder.touxiang

        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.name.setText(list.get(position).getNickname());
        if (list.get(position).getSportMode() == 1){
            viewHolder.moshi.setText("娱乐模式");
//            viewHolder.feiyong.setText("打赏费"+ df.format(list.get(position).getTips()/(list.get(position).getNeedPlayerNumber()-1))+"元/人");
            viewHolder.feiyong.setText("打赏费:"+"¥" + df.format(list.get(position).getTips())+"/人");

        }else  if (list.get(position).getSportMode() == 2){
            viewHolder.moshi.setText("竞技模式");
//            viewHolder.feiyong.setText("打赏费"+ df.format(list.get(position).getTips()/(list.get(position).getNeedPlayerNumber()-1))+"元/人");
            viewHolder.feiyong.setText("打赏费:"+"¥" +df.format(list.get(position).getTips())+"/人");
        }else if (list.get(position).getSportMode() == 3){
            viewHolder.moshi.setText("我是陪练");
            viewHolder.feiyong.setText("陪练费:"+"¥" +df.format(list.get(position).getMoneyPerhour())+"/人");

        }else if (list.get(position).getSportMode() == 4){
            viewHolder.moshi.setText("我找陪练");
            viewHolder.feiyong.setText("陪练费:"+"¥" +df.format(list.get(position).getMoneyPerhour())+"/人");
        }


        viewHolder.qiuleiText.setText(list.get(position).getSportName());
        viewHolder.renshu.setText(list.get(position).getSportTypeName());
        viewHolder.queren.setText("缺"+list.get(position).getLockNumber()+"人");
        viewHolder.time1.setText(list.get(position).getStartDays());
        viewHolder.time2.setText(list.get(position).getWeek());
        viewHolder.time3.setText(list.get(position).getStartTimes()+"-"+list.get(position).getEndTimes());
        viewHolder.dizhi.setText(list.get(position).getAddress());
        if (list.get(position).getPaySiteMoneyType()==1) {
            viewHolder.fangshi.setText("场地费:AA");
        } else{
            viewHolder.fangshi.setText("场地费:输方买单");
        }

        viewHolder.dengji.setText(list.get(position).getNowLevel());
        viewHolder.juli.setText(list.get(position).getRange());
        if (list.get(position).getSportName().equals("羽毛球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        }else  if (list.get(position).getSportName().equals("乒乓球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        }else if (list.get(position).getSportName().equals("台球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        }else if (list.get(position).getSportName().equals("篮球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        }else if (list.get(position).getSportName().equals("足球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        }else if (list.get(position).getSportName().equals("排球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        }else if (list.get(position).getSportName().equals("网球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        }else if (list.get(position).getSportName().equals("高尔夫")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        if (list.get(position).getSex() == 0){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        }else   if (list.get(position).getSex() == 1){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }


//        if (list.get(position).getPublicStatus() == 1){
//            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daichufa));
//        }else if (list.get(position).getPublicStatus() == 3){
//            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.huodongzhong));
//        }

        if (list.get(position).getPublicStatus() == 1){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pipeizhong));
        }else if (list.get(position).getPublicStatus() == 2){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daichufa));
        }else if (list.get(position).getPublicStatus() == 3){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.huodongzhong));
        }else if (list.get(position).getPublicStatus() == 4){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.tianxiejieguo));
        }else if (list.get(position).getPublicStatus() == 5){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiwancheng));
        }else if (list.get(position).getPublicStatus() == 6){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daipingjia));
        }else if (list.get(position).getPublicStatus() == 7){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiquxiao));
        }
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang,qiuleiImag,zhuangtai,sex;
        private TextView name,moshi,qiuleiText,renshu,queren,time1,time2,time3,dizhi,fangshi,feiyong,dengji,juli;


        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.home_list_touxiang);
            qiuleiImag = view.findViewById(R.id.home_list_image_qiulei);
            zhuangtai = view.findViewById(R.id.home_list_zhuangtai);
            name = view.findViewById(R.id.home_list_name);
            sex = view.findViewById(R.id.home_list_sex);
            moshi = view.findViewById(R.id.home_list_moshi);
            qiuleiText = view.findViewById(R.id.home_list_text_qiulei);
            renshu = view.findViewById(R.id.home_list_renshu);
            queren = view.findViewById(R.id.home_list_queren);
            time1 = view.findViewById(R.id.home_list_time1);
            time2 = view.findViewById(R.id.home_list_time2);
            time3 = view.findViewById(R.id.home_list_time3);
            dizhi = view.findViewById(R.id.home_list_dizhi);
            fangshi = view.findViewById(R.id.home_list_fangshi);
            feiyong = view.findViewById(R.id.home_list_feiyong);
            dengji = view.findViewById(R.id.home_list_dengji);
            juli = view.findViewById(R.id.home_list_juli);
        }


    }
}
