package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.tiaozhan.Entity.PromoterYTGEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class PromoYTGAdapter extends BaseAdapter {
    private Context context;
    private List<PromoterYTGEntity.DataBean> list;
    public PromoYTGAdapter(Context context, List<PromoterYTGEntity.DataBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.promo_ytg_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(list.get(position).getCg_name());
        viewHolder.diqu.setText(list.get(position).getCity()+"    "+list.get(position).getArea());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getSiteImg()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).placeholder(R.mipmap.logo).error(R.mipmap.logo).into( viewHolder.touxiang);

        if (list.get(position).getStatus() == 0){
            viewHolder.dcl.setText("待处理投诉"+list.get(position).getCount());
        }else if(list.get(position).getStatus() == 2){
            viewHolder.dcl.setText("处理中投诉"+list.get(position).getCount());

        }else if(list.get(position).getStatus() == 1){
            viewHolder.dcl.setText("正常"+list.get(position).getCount());
        }



        return convertView;
    }


    public class ViewHolder {
        private TextView name,diqu,dcl;
        private ImageView touxiang;
        public ViewHolder(View view) {

            name = view.findViewById(R.id.promo_ytg_list_name);
            diqu = view.findViewById(R.id.promo_ytg_list_diqu);
            dcl = view.findViewById(R.id.promo_ytg_list_dcl);
            touxiang = view.findViewById(R.id.touxiang);
        }


    }
}
