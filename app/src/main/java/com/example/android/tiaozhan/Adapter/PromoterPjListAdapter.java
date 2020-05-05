package com.example.android.tiaozhan.Adapter;

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
import com.example.android.tiaozhan.Entity.PromoterPJListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.StarBar;

import java.util.ArrayList;
import java.util.List;

public class PromoterPjListAdapter extends BaseAdapter {
    private List<PromoterPJListEntity.DataBean.CommentLstBean> list;
    private Context context;
    private MyjingcaiListGridAdapter adapter;
    public PromoterPjListAdapter(Context context, List<PromoterPJListEntity.DataBean.CommentLstBean> list) {
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
      /*  Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL())
                .placeholder(R.mipmap.logo).error(R.mipmap.logo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.touxiang);
*/
        viewHolder.starBar.setStarMark(list.get(position).getScore(),1);
        viewHolder.fenshu.setText(list.get(position).getScore()+"");
;

        List<String> listImage=new ArrayList<>();



        if (list.get(position).getImgcount() >=1) {


            if (list.get(position).getImages().size()>1){
                for (int i = 0; i < list.get(position).getImages().size(); i++) {

                    listImage.add(list.get(position).getImgbaseurl()+list.get(position).getImages().get(i));
                }
              //  listImage.addAll(list.get(position).getImages());
                adapter = new MyjingcaiListGridAdapter(context,listImage);
                viewHolder.gridView.setAdapter(adapter);
                viewHolder.gridView.setVisibility(View.VISIBLE);
                viewHolder.imageView.setVisibility(View.GONE);
              //  Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImages().get(0)).into( viewHolder.imageView);

            }else if (list.get(position).getImages().size()==1) {
                viewHolder.gridView.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgbaseurl()+list.get(position).getImages().get(0)).into( viewHolder.imageView);

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
