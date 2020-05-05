package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.FQTZEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;

import java.util.List;

public class FQYQAdapter extends BaseAdapter {
    private Context context;
    //    private List<Map<String, Object>> list;
    private int s;
    private List<FQTZEntity> list;

    private SPUtileFQTZ spUtileFQTZ;
    private String FQHDmoshihao, token, fqtzXiangmudaid;
    private SPUtils spUtils;
    private String level;

    public FQYQAdapter(Context context1, List<FQTZEntity> list, int s) {
        this.context = context1;
        this.list = list;
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
        FQYQAdapter.ViewHolder viewHolder;
        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.hdxq_a_grid, parent, false);
            viewHolder = new FQYQAdapter.ViewHolder(convertView);

            token = (String) spUtils.get(context, "logintoken", "");

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FQYQAdapter.ViewHolder) convertView.getTag();
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

        if (position < list.size()) {
            LogU.Ld("1609", "B走了=====" + list.get(position) + "=====" + list.size() + "==" + position);
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
            if (list.get(position).getHeightLevelName().equals("羽毛球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
            } else if (list.get(position).getHeightLevelName().equals("乒乓球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
            } else if (list.get(position).getHeightLevelName().equals("台球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
            } else if (list.get(position).getHeightLevelName().equals("篮球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
            } else if (list.get(position).getHeightLevelName().equals("足球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
            } else if (list.get(position).getHeightLevelName().equals("排球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
            } else if (list.get(position).getHeightLevelName().equals("网球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
            } else if (list.get(position).getHeightLevelName().equals("高尔夫")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
            }

            if (position==0){
                viewHolder.lv.setText("Lv"+level);
            }else {
                viewHolder.lv.setText(list.get(position).getHeightLevel());
            }


           // viewHolder.lv.setText("Lv"+level);


        } else {
            viewHolder.qiulei.setVisibility(View.GONE);
            viewHolder.lv.setVisibility(View.GONE);

        }
      //  viewHolder.no.setText("A" + (position + 1));
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

    public void setNumLv(String Lv) {
        this.level = Lv;
      //  this.FQHDmoshihao = ms;
    }
}
