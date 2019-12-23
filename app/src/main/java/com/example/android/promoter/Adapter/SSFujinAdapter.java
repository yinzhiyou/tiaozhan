package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.SSFujinEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class SSFujinAdapter extends BaseAdapter {
    private Context context;
    private List<SSFujinEntity.DataBean.LstBean> list;
    private String token;
    public SSFujinAdapter(Context context, List<SSFujinEntity.DataBean.LstBean> list,String token){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.fujin_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.jiahaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDownload(token,list.get(position).getUuid());
            }
        });
        if (list.get(position).getSex() == 0){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        }else   if (list.get(position).getSex() == 1){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }
        if (list.get(position).getHightName().equals("羽毛球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        }else  if (list.get(position).getHightName().equals("乒乓球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        }else if (list.get(position).getHightName().equals("台球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        }else if (list.get(position).getHightName().equals("篮球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        }else if (list.get(position).getHightName().equals("足球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        }else if (list.get(position).getHightName().equals("排球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        }else if (list.get(position).getHightName().equals("网球")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        }else if (list.get(position).getHightName().equals("高尔夫")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        viewHolder.name.setText(list.get(position).getNickname());
        viewHolder.jibie.setText(list.get(position).getHightLevel());
        viewHolder.nianling.setText("年龄："+list.get(position).getAge()+"岁");
        viewHolder.tizhong.setText("体重："+list.get(position).getWeight()+"kg");
        viewHolder.shengao.setText("身高："+list.get(position).getTall()+"cm");
        viewHolder.yundong.setText("喜爱运动项目："+list.get(position).getSportidNameStr());
        viewHolder.juli.setText(list.get(position).getUserRage());

        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang,jiahaoyou,sex,qiuImage;
        private TextView name,jibie,nianling,tizhong,shengao,yundong,juli;
        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.fujin_touxiang);
            jiahaoyou = view.findViewById(R.id.fujin_jiahaoyou);
            sex = view.findViewById(R.id.fujin_sex);
            qiuImage = view.findViewById(R.id.fujin_image_qiu);
            name = view.findViewById(R.id.fujin_name);
            jibie = view.findViewById(R.id.fujin_jibie);
            nianling = view.findViewById(R.id.fujin_nianling);
            tizhong = view.findViewById(R.id.fujin_tizhong);
            shengao = view.findViewById(R.id.fujin_shengao);
            yundong = view.findViewById(R.id.fujin_yundong);
            juli = view.findViewById(R.id.fujin_juli);
        }


    }

    private void initDownload(String token,String friendsId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "添加好友" + token);
        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addFriends")
                .addHeader("token",token)
                .addParams("friendsId",friendsId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "添加好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
