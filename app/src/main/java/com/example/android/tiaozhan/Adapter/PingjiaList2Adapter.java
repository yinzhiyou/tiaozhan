package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PingjiaListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.StarBar;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class PingjiaList2Adapter extends BaseQuickAdapter<PingjiaListEntity.DataBean.UsersInfoBean, BaseViewHolder> {

    private String uid;
    private List<BiaoqianEntity.DataBean> list3;
    private int num = 5;
    private PingJUsersInfoAdapter mpjUser;
    private RecyclerView recycle;
    private StarBar starBar;

    public PingjiaList2Adapter(int layoutResId, @Nullable List<PingjiaListEntity.DataBean.UsersInfoBean> data, List<BiaoqianEntity.DataBean> list3, String uid) {

        super(layoutResId, data);
        this.list3 = list3;
        this.uid = uid;
    }

    public interface onItemDeleteListener {
//        void onDeleteClick(int i, int b);

        void onDeleteClick(int position);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper,final PingjiaListEntity.DataBean.UsersInfoBean item) {
        helper.setText(R.id.pingjia_item_name, item.getNickname());
        helper.setText(R.id.jg_text, "填写比赛结果：" + item.getResult());
        helper.setText(R.id.pingjia_item_duiwu, item.getTeam());
        ImageView touxiang = helper.getView(R.id.pingjia_item_touxiang);
        starBar = helper.getView(R.id.starBar);
        recycle = helper.getView(R.id.recyclerview);
        Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getImgURL()).into(touxiang);
        helper.addOnClickListener(R.id.starBar);
        starBar.setStarMark(5, 2);
        starBar.setIntegerMark(true);
       // mpjUser = new PingJUsersInfoAdapter(R.layout.adapter_activity_label_content, item.getRes());
        recycle.setLayoutManager(new GridLayoutManager(mContext,3));
       // recycle.setAdapter(mpjUser);
        final LinkLabelAdapter linkLabelAdapter = new LinkLabelAdapter(mContext, list3,helper.getAdapterPosition());

        /*starBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                num = (new Double(mark)).intValue();
                String ss = mark + "";
                huoqubiaoqian(num + "", uid, item.getPlayerUUID());
                starBar.setStarMark(num, 2);
                recycle.setAdapter(linkLabelAdapter);

                linkLabelAdapter.notifyDataSetChanged();
                 helper.setText(R.id.pingjia_list_fenshu,num + "分");
                LogU.Ld("1608", "获取标签" + uid+"===");
           //     mOnItemDeleteListener.onDeleteClick(helper.getAdapterPosition());
            }
        });*/
//
    }

    //获取标签
    private void huoqubiaoqian(String level, final String uuid, String userid) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "获取标签" + level);
        OkHttpUtils
                .get()
                .url(mContext.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLabelInfo")
                .addParams("level", level)
                .addParams("uuid", uuid)
                .addParams("userid", userid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取标签aaaaa" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            BiaoqianEntity entity = gson.fromJson(result, BiaoqianEntity.class);
                            List<BiaoqianEntity.DataBean> list;
                            list = entity.getData();
                            list3.clear();
                            list3.addAll(list);
//                            adapter.notifyDataSetChanged();
                            // linkLabelAdapter.notifyDataSetChanged();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    public void getUUid(String uuid) {
        uid = uuid;
    }


}
