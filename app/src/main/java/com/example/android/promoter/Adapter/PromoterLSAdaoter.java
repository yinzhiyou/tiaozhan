package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.XSLSEntity;
import com.example.android.promoter.Promoter.PromoterXQActivity;
import com.example.android.promoter.R;

import java.util.List;

public class PromoterLSAdaoter extends BaseAdapter {

    private Context context;
    private List<XSLSEntity.DataBean> list;
    public PromoterLSAdaoter(Context context, List<XSLSEntity.DataBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.promo_ls_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(list.get(position).getPlayerName());
        viewHolder.time.setText(list.get(position).getIntime());
        if (list.get(position).getType()==0){
            viewHolder.neirong.setText(list.get(position).getTitle());
//            viewHolder.neirong.setText("投        诉："+list.get(position).getTitle());
        }else    if (list.get(position).getType()==1){
//            viewHolder.neirong.setText("反        馈："+list.get(position).getTitle());
            viewHolder.neirong.setText(list.get(position).getTitle());
        }else{
            viewHolder.touxiang.setBackgroundResource(R.mipmap.pingtai);
            viewHolder.neirong.setVisibility(View.GONE);
        }


        viewHolder.neirong2.setText("详细说明："+list.get(position).getComment());


        if (list.get(position).getFilesURL().length()<2){
        viewHolder.bofang.setVisibility(View.GONE);
        }else{
            viewHolder.bofang.setVisibility(View.VISIBLE);
        }

        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getPlayerimgurl()).into(viewHolder.touxiang);

        return convertView;
    }

    public class ViewHolder {
        private TextView name,time,neirong,neirong2,bofang;
        private ImageView touxiang;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.promo_ls_list_name);
            time = view.findViewById(R.id.promo_ls_list_time);
            neirong = view.findViewById(R.id.promo_ls_list_neirong);
            neirong2 = view.findViewById(R.id.promo_ls_list_neirong2);
            touxiang = view.findViewById(R.id.promo_ls_list_touxiang);
            bofang = view.findViewById(R.id.promo_ls_list_bofang);
        }

    }
}
