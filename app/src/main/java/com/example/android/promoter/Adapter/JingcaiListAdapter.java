package com.example.android.promoter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.JingcaiEntity;
import com.example.android.promoter.Entity.MyjingcaiEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyImageDialog;
import com.example.android.promoter.Wonderful.MainJingcaiItemActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;


import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

public class JingcaiListAdapter extends BaseAdapter {

    Context context;
    List<JingcaiEntity.DataBean.LstBean> list;
    private MyjingcaiListGridAdapter adapter;
    private String token;
    public JingcaiListAdapter (Context context,List<JingcaiEntity.DataBean.LstBean> list,String token){
        this.context = context;
        this.list = list;
        this.token = token;
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
        ImageLoader imageLoader = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.jingcai_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//            viewHolder.time.setText(list.get(position).getMonth()+"月"+list.get(position).getDay()+"日");
        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        viewHolder.neirong.setText(list.get(position).getComment());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getUserInfo().getImgURL()).into(viewHolder.touxiang);
        viewHolder.pinglun.setText(list.get(position).getCommentCount()+"");
        viewHolder.dianzan.setText(list.get(position).getPraiseCount()+"");
        if (list.get(position).getIsPraise() == 1){
                viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.zan));
        }else {
            viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.dianzanhui));
        }


        viewHolder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIsPraise() == 1){

                    viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.dianzanhui));
//                    jingcai( token,list.get(position).getUUID()+"",viewHolder);
                }else {
//                    jingcai( token,list.get(position).getUUID()+"",viewHolder);
                    viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.zan));
                }


                mOnItemDeleteListener.onDeleteClick(position);
            }
        });

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
        }else if (list.get(position).getFullPath().size()>1){
            viewHolder.jcVideoPlayerStandard.setVisibility(View.GONE);
            viewHolder.gridView.setAdapter(adapter);
            viewHolder.gridView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
        } else if(list.get(position).getContentCount() ==1){
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getFullPath().get(0)).into(viewHolder.imageView);
            viewHolder.jcVideoPlayerStandard.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.gridView.setVisibility(View.GONE);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    viewHolder.imageView.setDrawingCacheEnabled(true);
//                    MyImageDialog myImageDialog = new MyImageDialog(context,R.style.dialog_custom,0,0,  viewHolder.imageView.getDrawingCache());
//                    myImageDialog.show();
//                    Window window = myImageDialog.getWindow();
//                    window.setGravity(Gravity.BOTTOM);
//                    window.setWindowAnimations(R.style.dialog_custom);
//                    window.getDecorView().setPadding(0, 0, 0, 0);
//
//                    WindowManager.LayoutParams lp = window.getAttributes();
//                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                    window.setAttributes(lp);
                }
            });
        }
        }else{
                viewHolder.gridView.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.jcVideoPlayerStandard.setVisibility(View.GONE);

        }
//        LogU.Ld("1608","图片数量"+list.get(position).getFullPath().size());
        viewHolder.qiulei.setText("("+list.get(position).getSportName()+")");
        viewHolder.time.setText(list.get(position).getTimes());
        return convertView;
    }


    public class ViewHolder {
        private TextView time,name,neirong,pinglun,dianzan,qiulei;
        private ImageView touxiang,imageView,zan;
        private GridView gridView;
//        private JCVideoPlayerStandard jcVideoPlayerStandard;
        private JCVideoPlayerStandard jcVideoPlayerStandard;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.jingcai_name);
            touxiang = view.findViewById(R.id.jingcai_touxiang);
            neirong = view.findViewById(R.id.jingcai_neirong);
            imageView = view.findViewById(R.id.jingcai_imae);
            gridView = view.findViewById(R.id.jingcai_grid);
            jcVideoPlayerStandard = view.findViewById(R.id.jingcai_list_video);
            pinglun = view.findViewById(R.id.jingcai_pinglun);
            dianzan = view.findViewById(R.id.jingcai_dianzai);
            zan = view.findViewById(R.id.jingcai_zan);
            qiulei = view.findViewById(R.id.jingcai_qiulei);
            time = view.findViewById(R.id.my_jingcai_time);
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


//    //精彩瞬间
//    private void jingcai(String token ,String wonderfulId, final ViewHolder viewHolder) {
////        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "攒点"+token+wonderfulId);
//        OkHttpUtils
//                .post()
//                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/praiseWonderful")
//                .addHeader("token",token)
//                .addParams("wonderfulId",wonderfulId)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "点赞" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//
//                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//
//                            if (entity.getMsg().equals("点赞成功")){
//                                viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.zan));
//                            }else{
//                                viewHolder.zan.setImageDrawable(context.getResources().getDrawable(R.mipmap.dianzanhui));
//
//                            }
//
//
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
//                    }
//                });
//
//    }
}
