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
import com.example.android.tiaozhan.Entity.MyjingcaiEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class MyjingcaiListAdapter extends BaseAdapter {

    private Context context;
    private List<MyjingcaiEntity.DataBean.ResLstBean> list;
    private MyjingcaiListGridAdapter adapter;
    public MyjingcaiListAdapter (Context context,List<MyjingcaiEntity.DataBean.ResLstBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.my_jingcai_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(position).getMonth()+"月"+list.get(position).getDay()+"日");
        viewHolder.neirong.setText(list.get(position).getComment());
        viewHolder.time2.setText(list.get(position).getTime());
        int on = list.get(position).getContentCount();
        LogU.Ld("1608", "我的精彩图片" + on);
        adapter = new MyjingcaiListGridAdapter(context,list.get(position).getFullPath());


        if (list.get(position).getContentCount() >=1){


        if (list.get(position).getFullPath().get(0).indexOf(".mp4") != -1 ){
            LogU.Ld("1608","视频地址"+list.get(position).getFullPath().get(0));
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//            }
            viewHolder.jcVideoPlayerStandard.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.gridView.setVisibility(View.GONE);
            viewHolder.jcVideoPlayerStandard.setUp(context.getResources().getString(R.string.imgurl) +list.get(position).getFullPath().get(0)
                    , viewHolder.jcVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) +list.get(position).getOneImgs()).into( viewHolder.jcVideoPlayerStandard.thumbImageView);
            viewHolder.jcVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewHolder.jcVideoPlayerStandard.setUp("http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4"
//                    , viewHolder.jcVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
//            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
//            imageLoader.getInstance().displayImage("",
//                    viewHolder.jcVideoPlayerStandard.thumbImageView);
        }else if (on > 1) {
            viewHolder.gridView.setAdapter(adapter);
            viewHolder.gridView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getFullPath().get(0)).into(viewHolder.imageView);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.gridView.setVisibility(View.GONE);
            viewHolder.jcVideoPlayerStandard.setVisibility(View.GONE);
        }
        }else{
            viewHolder.gridView.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.jcVideoPlayerStandard.setVisibility(View.GONE);

        }

        viewHolder.pinglunshu.setText(list.get(position).getCommentCount()+"");
        viewHolder.dianzanshu.setText(list.get(position).getPraiseCount()+"");
        return convertView;
    }


    public class ViewHolder {
        private TextView time,time2,neirong,pinglunshu,dianzanshu;
        private ImageView imageView;
        private GridView gridView;
        private JCVideoPlayerStandard jcVideoPlayerStandard;
        public ViewHolder(View view) {
            time = view.findViewById(R.id.my_jingcai_time);
            time2 = view.findViewById(R.id.my_jingcai_time2);
            neirong = view.findViewById(R.id.my_jingcai_neirong);
            imageView = view.findViewById(R.id.my_jingcai_image);
            gridView = view.findViewById(R.id.my_jingcai_grid);
            pinglunshu = view.findViewById(R.id.my_jingcai_pinglunshu);
            dianzanshu = view.findViewById(R.id.my_jingcai_dianzanshu);
            jcVideoPlayerStandard = view.findViewById(R.id.my_jingcai_list_video);

        }


    }
}
