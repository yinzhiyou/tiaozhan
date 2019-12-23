package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.promoter.Entity.PromoterFJCGEntity;
import com.example.android.promoter.Home.CGXXActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.StarBar;

import java.util.List;

public class MyPromterCGListAdapter extends BaseAdapter {
    private Context context;
    private List<PromoterFJCGEntity.DataBean> list;
    public MyPromterCGListAdapter(Context context,List<PromoterFJCGEntity.DataBean> list) {
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
        final ViewHolder viewHolder;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.cg_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.starBar.setStarMark((float) list.get(position).getFraction(),1);
        viewHolder.starBar.setClickable(false);
        viewHolder.displayTv.setText(list.get(position).getFraction() + "分");
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.juli.setText(list.get(position).getRange());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getSiteimgs()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) viewHolder.cg_list_image);
        viewHolder.hezuo.setText("非合作场馆");
        viewHolder.tousu.setText("投诉  "+list.get(position).getCompcount());
//        if (list.get(position).getIsCooper() == 1){
//            viewHolder.hezuo.setVisibility(View.VISIBLE);
//        }else if (list.get(position).getIsCooper() == 0){
//            viewHolder.hezuo.setVisibility(View.INVISIBLE);
//        }
        viewHolder.yuding.setVisibility(View.GONE);
        return convertView;
    }

    public class ViewHolder {

        StarBar starBar;
        TextView displayTv, name,hezuo,juli,tousu;
        RelativeLayout yuding;
        ImageView cg_list_image;
        public ViewHolder(View view) {
            starBar = view.findViewById(R.id.starBar);
            displayTv = view.findViewById(R.id.display);
            name = view.findViewById(R.id.cg_list_name);
            hezuo = view.findViewById(R.id.cg_list_hezuo);
            juli = view.findViewById(R.id.cg_list_juli);
            yuding = view.findViewById(R.id.changguan_yuding);
            tousu = view.findViewById(R.id.cg_list_tousu);
            cg_list_image = view.findViewById(R.id.cg_list_image);
        }


    }
}
