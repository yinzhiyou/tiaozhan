package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;

import java.util.List;

public class FQTZAdapter extends BaseAdapter {
    private Context context;
    //    private List<Map<String, Object>> list;
    private int s;
    private List<String> listQ;
    private List<String> listTX;
    private List<String> listDJ;
    private SPUtileFQTZ spUtileFQTZ;
    private String FQHDmoshihao,token,fqtzXiangmudaid;
    private SPUtils spUtils;
    private String level;

    public FQTZAdapter(Context context1, List<String> listTX, List<String> listQ, List<String> listDJ, int s) {
        this.context = context1;
        this.listTX = listTX;
        this.listQ = listQ;
        this.listDJ = listDJ;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s;
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
        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.hdxq_a_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            token = (String) spUtils.get(context, "logintoken", "");

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        LogU.Ld("1608", "适配A队人数" +position+"---"+list.size()+"--"+list.get(0)+"--"+list.get(1));
//        if (list.get(position).get("qiu") == null) {
//
//        } else {
//        for (int i = 0; i < list.size(); i++) {
//            LogU.Ld("1608", "B走了" + "--" + i + "--" + list.get(i));
//
//
//        }
        //    LogU.Ld("1609", "B走了" +listQ.get(position)+"====="+listTX.size()+"=="+position );
        FQHDmoshihao = (String) spUtileFQTZ.get(context, "FQHDmoshihao", "1");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(context, "fqtzXiangmudasportId", " ");

        if (position < listTX.size()) {
            LogU.Ld("1609", "B走了=====" + listQ.get(position) + "=====" + listTX.size() + "==" + position);
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + listTX.get(position)).into(viewHolder.touxiang);
            if (listQ.get(position).equals("羽毛球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
            } else if (listQ.get(position).equals("乒乓球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
            } else if (listQ.get(position).equals("台球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
            } else if (listQ.get(position).equals("篮球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
            } else if (listQ.get(position).equals("足球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
            } else if (listQ.get(position).equals("排球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
            } else if (listQ.get(position).equals("网球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
            } else if (listQ.get(position).equals("高尔夫")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
            }
//                Glide.with(context).load("http://192.168.0.203/tzOne/public/" + list.get(position).get("touxiang")).into(viewHolder.touxiang);
//                if (list.get(position).get("qiu").equals("羽毛球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
//                } else if (list.get(position).get("qiu").equals("乒乓球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
//                } else if (list.get(position).get("qiu").equals("台球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
//                } else if (list.get(position).get("qiu").equals("篮球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
//                } else if (list.get(position).get("qiu").equals("足球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
//                } else if (list.get(position).get("qiu").equals("排球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
//                } else if (list.get(position).get("qiu").equals("网球")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
//                } else if (list.get(position).get("qiu").equals("高尔夫")) {
//                    viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
//                }


            viewHolder.lv.setText("Lv1");



        } else {
            viewHolder.qiulei.setVisibility(View.GONE);
            viewHolder.lv.setVisibility(View.GONE);

        }

//        }

        if (FQHDmoshihao.equals("3")) {
            viewHolder.no.setText("陪练方");
        } else if (FQHDmoshihao.equals("4")) {
            viewHolder.no.setText("练习方");
        } else {
            viewHolder.no.setText("A" + (position + 1));
        }

        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, qiulei;
        private TextView lv, no;

        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.hdxq_a_grid_touxiang);
            qiulei = view.findViewById(R.id.hdxq_a_grid_qiulei);
            lv = view.findViewById(R.id.hdxq_a_grid_dengji);
            no = view.findViewById(R.id.hdxq_a_grid_no);
        }


    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}