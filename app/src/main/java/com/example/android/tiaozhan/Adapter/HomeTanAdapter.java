package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.android.tiaozhan.Entity.HomeTanEntity;
import com.example.android.tiaozhan.MyApplication;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;

import java.util.List;

public class HomeTanAdapter extends BaseAdapter {
    private Context context;
    private List<HomeTanEntity.DataBean> list;

    public HomeTanAdapter(Context context,List<HomeTanEntity.DataBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.home_tan_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(list.get(position).getNickname());
        viewHolder.moshi.setText(list.get(position).getSportMode());
        viewHolder.qiulei.setText(list.get(position).getSportName());
        viewHolder.renshu.setText(list.get(position).getSportTypeName());
        viewHolder.time1.setText(list.get(position).getStartDays());
        viewHolder.time2.setText(list.get(position).getWeek());
        viewHolder.time3.setText(list.get(position).getStartTimes()+"-"+list.get(position).getEndTimes());
        viewHolder.dizhi.setText(list.get(position).getSitename());
        viewHolder.lv.setText(list.get(position).getNowLevel());
        if (Util.isOnMainThread()) {
            Glide.with(MyApplication.getContext()).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
        }
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
        }else if (list.get(position).getPublicStatus() == 9){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.icon_tousuzhong));
        }

        if (list.get(position).getSex()==0){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        }else   if (list.get(position).getSex()==1){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }

       /* if (list.get(position).getStatus().equals("1")){
            //确认结束
            viewHolder.anniuYES.setVisibility(View.GONE);
            viewHolder.anniuNO.setVisibility(View.GONE);
            viewHolder.anniu.setVisibility(View.VISIBLE);
            viewHolder.anniuText.setText("确认结束");

        }else*/
       if (!EmptyUtils.isStrEmpty(list.get(position).getStatus())) {

           LogU.Ld("1608","弹窗"+list.get(position).getStatus());
           if (list.get(position).getStatus().equals("2")) {
               //签到
               viewHolder.anniuYES.setVisibility(View.GONE);
               viewHolder.anniuNO.setVisibility(View.GONE);
               viewHolder.anniu.setVisibility(View.VISIBLE);
               viewHolder.tan_fan_layout.setVisibility(View.GONE);
               viewHolder.anniuText.setText("签到");
           } else if (list.get(position).getStatus().equals("0")) {
               //填写结果
               viewHolder.anniuYES.setVisibility(View.GONE);
               viewHolder.anniuNO.setVisibility(View.GONE);
               viewHolder.anniu.setVisibility(View.VISIBLE);
               viewHolder.tan_fan_layout.setVisibility(View.GONE);
               viewHolder.anniuText.setText("填写结果");
           } else if (list.get(position).getStatus().equals("4")) {
               viewHolder.anniuText.setText("去评价");
               viewHolder.anniuYES.setVisibility(View.GONE);
               viewHolder.anniuNO.setVisibility(View.GONE);
               viewHolder.anniu.setVisibility(View.VISIBLE);
               viewHolder.tan_fan_layout.setVisibility(View.GONE);
           } else if (list.get(position).getStatus().equals("3")) {
               viewHolder.anniuYES.setVisibility(View.VISIBLE);
               viewHolder.anniuNO.setVisibility(View.VISIBLE);
               viewHolder.anniu.setVisibility(View.GONE);
               viewHolder.tan_fan_layout.setVisibility(View.VISIBLE);
               viewHolder.fanK.setText(list.get(position).getComment());
           }else if (list.get(position).getStatus().equals("5")) {
               viewHolder.anniuYES.setVisibility(View.VISIBLE);
               viewHolder.anniuNO.setVisibility(View.VISIBLE);
               viewHolder.anniu.setVisibility(View.GONE);
               viewHolder.tan_fan_layout.setVisibility(View.VISIBLE);
               viewHolder.fanK.setText(list.get(position).getComment());
           }else if (list.get(position).getStatus().equals("6")) {
               viewHolder.anniuYES.setVisibility(View.VISIBLE);
               viewHolder.anniuNO.setVisibility(View.VISIBLE);
               viewHolder.anniu.setVisibility(View.GONE);
               viewHolder.tan_fan_layout.setVisibility(View.VISIBLE);
               viewHolder.fanK.setText(list.get(position).getComment());
           }

       }
        viewHolder.anniuNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getStatus().equals("3")) {
                    mOnItemDeleteListener.onDeleteClick(position, 3, 2);
                }else if (list.get(position).getStatus().equals("5")) {
                    mOnItemDeleteListener.onDeleteClick(position, 5,2);
                }if (list.get(position).getStatus().equals("6")) {
                    mOnItemDeleteListener.onDeleteClick(position, 6,2);
                }
            }
        });


        viewHolder.anniuYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getStatus().equals("3")) {
                    mOnItemDeleteListener.onDeleteClick(position, 3, 1);
                }else if (list.get(position).getStatus().equals("5")) {
                    mOnItemDeleteListener.onDeleteClick(position, 5,1);
                }if (list.get(position).getStatus().equals("6")) {
                    mOnItemDeleteListener.onDeleteClick(position, 6,1);
                }

            }
        });



        viewHolder.anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.Ld("1608","点击了111"+list.get(position).getStatus());
//                initDownload( list.get(position).getPublicUUID());
                if(list.get(position).getStatus().equals("2")){
                    //签到
                    mOnItemDeleteListener.onDeleteClick(position, 2,0);
                }else if(list.get(position).getStatus().equals("0")){
                    //填写结果
                    mOnItemDeleteListener.onDeleteClick(position, 0,0);
                } if(list.get(position).getStatus().equals("4")){
                    //填写结果
                    mOnItemDeleteListener.onDeleteClick(position, 4,0);
                }


            }
        });
//            viewHolder.textView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemDeleteListener.onDeleteClick(position);
//                }
//            });

        return convertView;
    }


    public class ViewHolder {
        private TextView name, qiulei,renshu,time1,time2,time3,dizhi,lv,moshi,anniuText,fanK;
        private ImageView touxiang,qiuleiImag,zhuangtai,sex;
        private RelativeLayout anniu,anniuNO,anniuYES;

        private LinearLayout tan_fan_layout;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.home_tan_list_name);
            qiulei = view.findViewById(R.id.home_tan_list_qiulei);
            renshu = view.findViewById(R.id.home_tan_list_renshu);
            moshi = view.findViewById(R.id.home_tan_list_moshi);
            time1 = view.findViewById(R.id.home_tan_list_time1);
            time2 = view.findViewById(R.id.home_tan_list_time2);
            time3 = view.findViewById(R.id.home_tan_list_time3);
            dizhi = view.findViewById(R.id.home_tan_list_dizhi);
            lv = view.findViewById(R.id.home_tan_list_dengji);
            moshi = view.findViewById(R.id.home_tan_list_moshi);
            touxiang = view.findViewById(R.id.home_tan_list_touxiang);
            qiuleiImag = view.findViewById(R.id.home_tan_list_image_qiulei);
            zhuangtai = view.findViewById(R.id.home_tan_list_zhuangtai);
            sex = view.findViewById(R.id.home_tan_list_sex);
            anniu = view.findViewById(R.id.home_tan_list_anniu);

            anniuText = view.findViewById(R.id.home_tan_list_anniu_text);
            anniuNO = view.findViewById(R.id.home_tan_list_no);
            anniuYES = view.findViewById(R.id.home_tan_list_yes);
            fanK = view.findViewById(R.id.home_tan_list_fank);
            tan_fan_layout = view.findViewById(R.id.tan_fan_layout);


        }


    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i, int tag,int b);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

}
