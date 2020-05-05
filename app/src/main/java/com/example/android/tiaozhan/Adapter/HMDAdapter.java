package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HMDEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class HMDAdapter extends BaseAdapter {
    private Context context;
    private List<HMDEntity.DataBean.LstBean> list;
    private String token,touxiang,nickname,uuid;
    private SPUtils spUtils;
    public  HMDAdapter(Context context,List<HMDEntity.DataBean.LstBean> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        spUtils = new SPUtils();
        token = (String) spUtils.get(context, "logintoken", "");
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.hmd_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.name.setText(list.get(position).getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getHeaderImg()).into(viewHolder.touxiang);

        viewHolder.yichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init(list.get(position).getShieldPlayerUUID());
                mOnItemDeleteListener.onDeleteClick(position);

            }
        });
        return convertView;
    }


    public class ViewHolder {
        private TextView name,yichu;
        private ImageView touxiang;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.hmd_name);
            touxiang = view.findViewById(R.id.hmd_touxiang);
            yichu = view.findViewById(R.id.hmd_yichu);
        }
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


    //移除黑名单
    private void init(String uuid) {
        LogU.Ld("1608", "移除黑名单");
        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/moveBlackLst")
                .addHeader("token", token)
                .addParams("uuid",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "移除黑名单" + result);
                        Boolean a = result.indexOf("2000") != -1;

                    }
                });

    }
}