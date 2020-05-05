package com.example.android.tiaozhan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.tiaozhan.Entity.ChangguanListEntity;
import com.example.android.tiaozhan.Home.StartTimeActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.StarBar;

import java.util.List;

public class ChangguanListAdapter extends BaseAdapter {


    private Context context;
    private List<ChangguanListEntity.DataBean.SitelstBean> list;
    private SPUtileFQTZ spUtils;
    private SPUtils spUtils1;
    private String yId;
    public ChangguanListAdapter(Context context, List<ChangguanListEntity.DataBean.SitelstBean> list) {
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
        final ViewHolder viewHolder;
        spUtils = new SPUtileFQTZ();
        spUtils1 = new SPUtils();
        yId = (String) spUtils1.get(context, "yId", "2");
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.cg_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tousu.setVisibility(View.GONE);
        viewHolder.starBar.setStarMark(list.get(position).getScores(),1);

        viewHolder.displayTv.setText(list.get(position).getScores() + "分");
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.juli.setText(list.get(position).getRange());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getSiteimg()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) viewHolder.cg_list_image);

        if (list.get(position).getIsCooper() == 1){
            viewHolder.hezuo.setVisibility(View.VISIBLE);
        }else if (list.get(position).getIsCooper() == 0){
            viewHolder.hezuo.setVisibility(View.INVISIBLE);
        }


//        viewHolder.yuding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context,StartTimeActivity.class);
//                context.startActivity(intent);
//            }
//        });

        LogU.Ld("1608","sdfd"+yId);
        viewHolder.yuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).getIsCooper() == 1){
                    Intent intent = new Intent();
                    Bundle bundle=new Bundle();
                    if (yId.equals("1")){
                       spUtils.put(context, "yId", "1");
                        spUtils.put(context, "wcgid", list.get(position).getUid());
                        spUtils.put(context, "wcgnameN", list.get(position).getName());
                        spUtils.put(context, "wcity", list.get(position).getCity());
                        bundle.putString("yId", "1");

                        bundle.putString("wcgid", list.get(position).getUid());
                        intent.putExtras(bundle);
                        spUtils.put(context, "whezuo", list.get(position).getIsCooper() + "");

                    }else if (yId.equals("2")) {
                       spUtils.put(context, "yId", "2");
                        bundle.putString("yId", "2");

                        bundle.putString("cgid", list.get(position).getUid());
                        intent.putExtras(bundle);

                        spUtils.put(context, "cgid2", list.get(position).getUid());
                        spUtils.put(context, "cgnameN", list.get(position).getName());
                        spUtils.put(context, "city", list.get(position).getCity());

                        spUtils.put(context, "hezuo", list.get(position).getIsCooper() + "");
                    }
                   // spUtils.put(context, "hezuo", list.get(position).getIsCooper() + "");

                    intent.setClass(context,StartTimeActivity.class);
                    context.startActivity(intent);
                }else {
//                    String str = null, str2 = null;

//                    str2 = list.get(position).getTelephone().substring(list.get(position).getTelephone().indexOf(","));


                    showNormalDialog(list.get(position).getTelephone(), position);
//                    showNormalDialog(list.get(position).getTelephone().substring(0, list.get(position).getTelephone().indexOf(",")),position);

                }


            }
        });

//        viewHolder.starBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
//            @Override
//            public void onStarChange(float mark) {
//
//            }
//        });

        return convertView;
    }


    public class ViewHolder {

        StarBar starBar;
        TextView displayTv, name,hezuo,juli;
        RelativeLayout yuding;
        ImageView cg_list_image;
        private LinearLayout tousu;
        public ViewHolder(View view) {
            starBar = view.findViewById(R.id.starBar);
            displayTv = view.findViewById(R.id.display);
            name = view.findViewById(R.id.cg_list_name);
            hezuo = view.findViewById(R.id.cg_list_hezuo);
            juli = view.findViewById(R.id.cg_list_juli);
            yuding = view.findViewById(R.id.changguan_yuding);
            tousu = view.findViewById(R.id.cg_list_tousu_layout);
            cg_list_image=view.findViewById(R.id.cg_list_image);
        }


    }
    private void showNormalDialog(final String telephone, final int position ) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("该场馆为未合作场馆，请与场馆方联系，确定可选时间和场地费，谢谢");
        normalDialog.setPositiveButton("现在联系",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                        if (list.get(position).getTelephone().contains(",")){

                            showListDialog(list.get(position).getTelephone().split(","));
                        }else{
                            Intent intent1 = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + telephone);
                            intent1.setData(data);
                            context.startActivity(intent1);
                        }


                    }
                });
        normalDialog.setNegativeButton("已联系",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle=new Bundle();
                        if (yId.equals("1")){
                            spUtils.put(context, "yId", "1");
                            spUtils.put(context, "wcgid", list.get(position).getUid());
                            spUtils.put(context, "wcgnameN", list.get(position).getName());
                            spUtils.put(context, "wcity", list.get(position).getCity());
                            bundle.putString("yId", "1");

                            bundle.putString("wcgid", list.get(position).getUid());
                            intent.putExtras(bundle);
                            spUtils.put(context, "hezuo", list.get(position).getIsCooper() + "");

                        }else if (yId.equals("2")) {
                            spUtils.put(context, "yId", "2");
                            bundle.putString("yId", "2");

                            bundle.putString("cgid", list.get(position).getUid());
                            intent.putExtras(bundle);

                            spUtils.put(context, "cgid2", list.get(position).getUid());
                            spUtils.put(context, "cgnameN", list.get(position).getName());
                            spUtils.put(context, "city", list.get(position).getCity());

                            spUtils.put(context, "hezuo", list.get(position).getIsCooper() + "");
                        }
                        spUtils.put(context, "cgid", list.get(position).getUid());
                        spUtils.put(context, "cgname", list.get(position).getName());
                        spUtils.put(context, "hezuo", list.get(position).getIsCooper()+"");

                        intent.setClass(context,StartTimeActivity.class);
                        context.startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }
    private void showListDialog(String[] length) {
        final String[] items = length;
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(context);
        listDialog.setTitle("请选择你要拨打的电话");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + items[which]);
                intent1.setData(data);
                context.startActivity(intent1);
            }
        });
        listDialog.show();
    }
}
