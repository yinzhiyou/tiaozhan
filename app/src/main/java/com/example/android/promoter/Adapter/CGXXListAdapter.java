package com.example.android.promoter.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.promoter.R;
import com.example.android.promoter.Entity.CGXXEntity;

import com.example.android.promoter.Toos.StarBar;

import java.util.List;

public class CGXXListAdapter extends BaseAdapter {
    private List<CGXXEntity.DataBean.CommentsBean> list;
    private Context context;
    private MyjingcaiListGridAdapter adapter;
    public CGXXListAdapter(Context context, List<CGXXEntity.DataBean.CommentsBean> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.cgxx_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getNickname());
        viewHolder.time.setText(list.get(position).getCommentDate());
        viewHolder.neirong.setText(list.get(position).getContent());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL())
                .placeholder(R.mipmap.logo).error(R.mipmap.logo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.touxiang);
        viewHolder.starBar.setStarMark(list.get(position).getScore(),1);
        viewHolder.fenshu.setText(list.get(position).getScore()+"");

        adapter = new MyjingcaiListGridAdapter(context,list.get(position).getImages());

        if (list.get(position).getImgcount() >=1) {


            if (list.get(position).getImages().size()>1){

            viewHolder.gridView.setAdapter(adapter);
            viewHolder.gridView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
        }else if (list.get(position).getImages().size()==1) {
            viewHolder.gridView.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImages().get(0)).into( viewHolder.imageView);

        }
        }else{

                viewHolder.gridView.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.GONE);

        }
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang,imageView;
        private TextView name, time, neirong,fenshu;
        private StarBar starBar;
        private GridView gridView;
         public ViewHolder(View view) {
            touxiang = view.findViewById(R.id.cgxx_list_touxiang);
            name = view.findViewById(R.id.cgxx_list_name);
            time = view.findViewById(R.id.cgxx_list_time);
            neirong = view.findViewById(R.id.cgxx_list_neirong);
            starBar = view.findViewById(R.id.cgxx_list_StarBar);
            fenshu = view.findViewById(R.id.cgxx_list_fenshu);
            imageView = view.findViewById(R.id.cgxx_list_image);
             gridView = view.findViewById(R.id.cgxx_list_grid);
        }


    }
}