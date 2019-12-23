package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.PromoterComplainthdlistEntity;
import com.example.android.promoter.Entity.PromoterDCLEntity;
import com.example.android.promoter.R;

import java.util.List;

public class PromoterDCLAdapter extends BaseAdapter {

    private Context context;
    private List<PromoterComplainthdlistEntity.DataBean> list;
    private String tab;
    public PromoterDCLAdapter(Context context, List<PromoterComplainthdlistEntity.DataBean> list, String tab){
        this.context = context;
        this.list = list;
        this.tab = tab;

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

            convertView = LayoutInflater.from(context).inflate(R.layout.promo_dcl_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*if (tab.equals("2")){
            viewHolder.ticheng.setVisibility(View.GONE);
        }else{
            viewHolder.ticheng.setVisibility(View.VISIBLE);
        }*/
            viewHolder.name.setText(list.get(position).getNickname());
            viewHolder.moshi.setText(list.get(position).getSportMode()+"");
            viewHolder.qiu.setText(list.get(position).getSportId());
            viewHolder.renshu.setText(list.get(position).getSportType());
            viewHolder.cdf.setText("场地费:"+list.get(position).getSiteMoney()+"");
            viewHolder.ticheng.setText("提成:"+list.get(position).getRoyaltyMoney()+"%");
            viewHolder.time1.setText(list.get(position).getYear());
        viewHolder.time2.setText(list.get(position).getWeek());
        viewHolder.time3.setText(list.get(position).getTime());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.dizhi.setText(list.get(position).getSitename());


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
        }else if (list.get(position).getPublicStatus() == 8){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.querenjieshu));
        }

        if (list.get(position).getSportId().equals("羽毛球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        }else  if (list.get(position).getSportId().equals("乒乓球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        }else if (list.get(position).getSportId().equals("台球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        }else if (list.get(position).getSportId().equals("篮球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        }else if (list.get(position).getSportId().equals("足球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        }else if (list.get(position).getSportId().equals("排球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        }else if (list.get(position).getSportId().equals("网球")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        }else if (list.get(position).getSportId().equals("高尔夫")){
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }



        if (list.get(position).getStatus() == 0){
            viewHolder.chuli.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daichuli));
        }else if(list.get(position).getStatus() == 2){
            viewHolder.chuli.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.chulizhong));

        }else if(list.get(position).getStatus() == 1){
            viewHolder.chuli.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yichuli));
        }

        return convertView;
    }

    public class ViewHolder {

        private TextView name,moshi,qiu,renshu,time1,time2,time3,dizhi,cdf,ticheng,lv;
        private ImageView qiuleiImag,touxiang,zhuangtai,chuli;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.promo_dcl_list_name);
            moshi = view.findViewById(R.id.promo_dcl_list_moshi);
            qiu = view.findViewById(R.id.promo_dcl_list_text_qiulei);
            renshu = view.findViewById(R.id.promo_dcl_list_renshu);
            time1 = view.findViewById(R.id.promo_dcl_list_time1);
            time2 = view.findViewById(R.id.promo_dcl_list_time2);
            time3 = view.findViewById(R.id.promo_dcl_list_time3);
            dizhi = view.findViewById(R.id.promo_dcl_list_dizhi);
            cdf = view.findViewById(R.id.promo_dcl_list_fangshi);
            ticheng = view.findViewById(R.id.promo_dcl_list_feiyong);
            touxiang = view.findViewById(R.id.promo_dcl_list_touxiang);
            qiuleiImag = view.findViewById(R.id.promo_dcl_list_image_qiulei);
            lv = view.findViewById(R.id.promo_dcl_list_dengji);
            zhuangtai = view.findViewById(R.id.promo_dcl_list_zhuangtai);
            chuli = view.findViewById(R.id.promo_dcl_list_chuli);


        }


    }
}
