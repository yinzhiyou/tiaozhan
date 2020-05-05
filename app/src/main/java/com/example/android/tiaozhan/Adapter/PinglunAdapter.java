package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.JCItemEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class PinglunAdapter extends BaseAdapter {

    private Context context;
    List<JCItemEntity.DataBean.CommentsBean.CommentLstBean> list;

    public PinglunAdapter( Context context,List<JCItemEntity.DataBean.CommentsBean.CommentLstBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.pinglun, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.neirong.setText(list.get(position).getComment());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getUserImgURL()).into(viewHolder.touxiang);
        viewHolder.name.setText(list.get(position).getUserNickName());
        viewHolder.time.setText(list.get(position).getDate());

        return convertView;
    }


    public class ViewHolder {
        private TextView neirong,name,time;
        private ImageView touxiang;

        public ViewHolder(View view) {
        neirong = view.findViewById(R.id.pinglun_neirong);
        touxiang = view.findViewById(R.id.pinglun_touxiang);
        name = view.findViewById(R.id.pinglun_name);
        time = view.findViewById(R.id.pinglun_time);
        }


    }
}
