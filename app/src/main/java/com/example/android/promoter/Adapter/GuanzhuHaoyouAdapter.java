package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.HaoyouEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.My.MyGuanzhuActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class GuanzhuHaoyouAdapter extends BaseAdapter {
    private Context context;
    private List<HaoyouEntity.DataBean.LstBean> list;
    private String token;

    public GuanzhuHaoyouAdapter(Context context, List<HaoyouEntity.DataBean.LstBean> list, String token) {
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
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.yqhy_list_adapter, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getUserInfo().getImgURL()).into(viewHolder.touxiang);

        if (list.get(position).getUserInfo().getSex() == 0) {
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        } else if (list.get(position).getUserInfo().getSex() == 1) {
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }
        if (list.get(position).getHightName().equals("羽毛球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        } else if (list.get(position).getHightName().equals("乒乓球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        } else if (list.get(position).getHightName().equals("台球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        } else if (list.get(position).getHightName().equals("篮球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        } else if (list.get(position).getHightName().equals("足球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        } else if (list.get(position).getHightName().equals("排球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        } else if (list.get(position).getHightName().equals("网球")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        } else if (list.get(position).getHightName().equals("高尔夫")) {
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        viewHolder.jibie.setText(list.get(position).getHightLevel());
        viewHolder.nianling.setText("年龄: " + list.get(position).getUserInfo().getAge() + "岁");
        viewHolder.tizhong.setText("体重: " + list.get(position).getUserInfo().getWeight() + "kg");
        viewHolder.shengao.setText("身高: " + list.get(position).getUserInfo().getTall() + "cm");
        viewHolder.yundong.setText("喜爱运动项目：" + list.get(position).getSportidNameStr());

        viewHolder.anniuText.setText("取消");
        viewHolder.anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initDownload(list.get(position).getUuid() + "");
                mOnItemDeleteListener.onDeleteClick(position);
            }
        });

        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, sex, qiuImage;
        private TextView name, jibie, nianling, tizhong, shengao, yundong, anniuText;
        private RelativeLayout anniu;

        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.haoyou_touxiang);

            sex = view.findViewById(R.id.haoyou_sex);
            qiuImage = view.findViewById(R.id.haoyou_image_qiu);
            name = view.findViewById(R.id.haoyou_name);
            jibie = view.findViewById(R.id.haoyou_jibie);
            nianling = view.findViewById(R.id.haoyou_nianling);
            tizhong = view.findViewById(R.id.haoyou_tizhong);
            shengao = view.findViewById(R.id.haoyou_shengao);
            yundong = view.findViewById(R.id.haoyou_yundong);

            anniu = view.findViewById(R.id.haoyou_anniu);
            anniuText = view.findViewById(R.id.haoyou_anniu_text);
        }
//        cancelFollow
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }


//    private void initDownload(String uid) {
////        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "取消关注" + token);
//        OkHttpUtils
//                .post()
//                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/cancelFollow")
//                .addHeader("token", token)
//                .addParams("followUUID", uid)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "取消关注" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
//                    }
//                });
//
//    }

}