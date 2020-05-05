package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.PromoterNewsListEntyty;
import com.example.android.tiaozhan.Promoter.PromoterXiaoxiItemActivity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class PromoterXiaoxNewListAdapter extends BaseAdapter {
    private Context context;
    private List<PromoterNewsListEntyty.DataBean> list;

    public PromoterXiaoxNewListAdapter(Context context, List<PromoterNewsListEntyty.DataBean> list) {
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

        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.promoter_xiaoxi_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (list.get(position).getIsred()==1){
            viewHolder.hongdian.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.hongdian.setVisibility(View.VISIBLE);
        }
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    list.remove(position);
                    mOnItemDeleteListener.onDeleteClick(position);
                    Toast.makeText(context, "点击了删除"+position, Toast.LENGTH_SHORT).show();


            }
        });

       viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent1 = new Intent();
                    Bundle bundle1 = new Bundle();//传值
                    intent1.setClass(context, PromoterXiaoxiItemActivity.class);
                    bundle1.putString("uuid",list.get(position).getUuid());
                    intent1.putExtras(bundle1);
                    context.startActivity(intent1);

                //   Toast.makeText(context, "点击了item", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.time.setText(list.get(position).getIntime());
        viewHolder.neirong.setText(list.get(position).getContent());
        return convertView;
    }
    public class ViewHolder {
        private TextView btnDelete,name,time,neirong,tishi;
        private LinearLayout layout;
        private Button tongyi,hulue;
        private ImageView touxiang,hongdian;
        public ViewHolder(View view) {

            btnDelete = view.findViewById(R.id.pro_btnDelete);
            layout = view.findViewById(R.id.pro_xiaoxi_xt_layout);
            name = view.findViewById(R.id.pro_xiaoxi_name);
            time = view.findViewById(R.id.pro_xiaoxi_time);
            neirong = view.findViewById(R.id.pro_xiaoxi_neirong);
            touxiang = view.findViewById(R.id.xiaoxi_touxiang);
            hongdian = view.findViewById(R.id.pro_xiaoxi_hongdian);
        }


    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
//        void onDeleteClick(int i, int b);

        void onDeleteClick(int position);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }
}
