package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.XiaoxiXTEntity;
import com.example.android.tiaozhan.Home.ChangguanItem;
import com.example.android.tiaozhan.Home.HomeHDXQActivity;
import com.example.android.tiaozhan.Home.HomeZhifuActivity;
import com.example.android.tiaozhan.Home.XiaoxiItem;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class XiaoxiXTAdapter extends BaseAdapter {
    private Context context;
    private List<XiaoxiXTEntity.DataBean.LstBean> list;
    private String token;
    private SPUtils spUtils;

    public XiaoxiXTAdapter(Context context, List<XiaoxiXTEntity.DataBean.LstBean> list) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.xiaoxi_xt_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if (list.get(position).getIsread() == 1) {
            viewHolder.hongdian.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.hongdian.setVisibility(View.VISIBLE);
        }


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了删除", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).getMsgType().equals("sysmsg")) {
                    Intent intent1 = new Intent();
                    Bundle bundle1 = new Bundle();//传值
                    intent1.setClass(context, XiaoxiItem.class);
                    bundle1.putString("uuid", list.get(position).getUuid());
                    intent1.putExtras(bundle1);
                    context.startActivity(intent1);
                }
                if (list.get(position).getMsgType().equals("veneumsg")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();//传值
                    intent.setClass(context, ChangguanItem.class);
                    bundle.putString("uuid", list.get(position).getUuid());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
                if (list.get(position).getMsgType().equals("Invitesysmsg")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();//传值
                    intent.setClass(context, HomeHDXQActivity.class);
                    bundle.putString("tab", "0");
                    bundle.putString("uuid", list.get(position).getPublicuuid());
                    bundle.putString("XXUuid", list.get(position).getUuid());
                    spUtils.put(context, "XXUuid", list.get(position).getUuid());
                    LogU.Ld("1608", "活动状态" + list.get(position).getAuthoruuid()+list.get(position).getUuid()+list.get(position).getPlayeruuid()+list.get(position).getPublicuuid());
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
                LogU.Ld("1608", "点击了ite" + list.get(position).getMsgType());
            }
        });
        viewHolder.name.setText(list.get(position).getTitle());
        viewHolder.time.setText(list.get(position).getAddTime());
        viewHolder.neirong.setText(list.get(position).getContent());

        if (list.get(position).getMsgType().equals("addfriendsmsg")) {

            if (list.get(position).getHandleResult() == 0) {//未处理
                viewHolder.tongyi.setVisibility(View.VISIBLE);
                viewHolder.hulue.setVisibility(View.VISIBLE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.GONE);

                viewHolder.tongyi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogU.Ld("1608", "点击了确认1");
                        initDownload(list.get(position).getAuthoruuid(), "1");
                        mOnItemDeleteListener.onDeleteClick(position);
//                            xiaoxi(list.get(position).getUuid());
                    }
                });
                viewHolder.hulue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogU.Ld("1608", "点击了忽略1");
                        initDownload(list.get(position).getAuthoruuid(), "2");
                        mOnItemDeleteListener.onDeleteClick(position);
//                            xiaoxi(list.get(position).getUuid());
                    }
                });
            } else if (list.get(position).getHandleResult() == 1) {//同意
                viewHolder.tongyi.setVisibility(View.GONE);
                viewHolder.hulue.setVisibility(View.GONE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.VISIBLE);
                viewHolder.tishi.setText("已同意");
            } else if (list.get(position).getHandleResult() == 2) {//拒绝
                viewHolder.tongyi.setVisibility(View.GONE);
                viewHolder.hulue.setVisibility(View.GONE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.VISIBLE);
                viewHolder.tishi.setText("已拒绝");
            }

        } else if (list.get(position).getMsgType().equals("Invitesysmsg")) {
            if (list.get(position).getHandleResult() == 0) {//未处理
                viewHolder.tongyi.setVisibility(View.VISIBLE);
                viewHolder.hulue.setVisibility(View.VISIBLE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.GONE);

                viewHolder.tongyi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogU.Ld("1608", "点击了确认2");
//                            Intent intent = new Intent();
//                            Bundle bundle = new Bundle();//传值
//                            intent.setClass(context, HomeZhifuActivity.class);
//                            bundle.putString("tag","3");
//                            bundle.putString("uuid",list.get(position).getPublicuuid()+"");
//                            intent.putExtras(bundle);
//                            context.startActivity(intent);
                        xiangqing(list.get(position).getPublicuuid() + "", position);
                        mOnItemDeleteListener.onDeleteClick(position);
//                            xiaoxi(list.get(position).getUuid());
                    }
                });
                viewHolder.hulue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogU.Ld("1608", "点击了忽略2");
                        init(list.get(position).getPublicuuid() + "", "-1",list.get(position).getUuid());
                        mOnItemDeleteListener.onDeleteClick(position);
//                            xiaoxi(list.get(position).getUuid());
                    }
                });
            } else if (list.get(position).getHandleResult() == 1) {//同意
                viewHolder.tongyi.setVisibility(View.GONE);
                viewHolder.hulue.setVisibility(View.GONE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.VISIBLE);
                viewHolder.tishi.setText("已同意");
            } else if (list.get(position).getHandleResult() == 2) {//拒绝
                viewHolder.tongyi.setVisibility(View.GONE);
                viewHolder.hulue.setVisibility(View.GONE);
                viewHolder.touxiang.setVisibility(View.VISIBLE);
                Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getAuthorimgURL()).into(viewHolder.touxiang);
                viewHolder.tishi.setVisibility(View.VISIBLE);
                viewHolder.tishi.setText("已拒绝");
            }

        } else {
            viewHolder.tishi.setVisibility(View.GONE);
            viewHolder.tongyi.setVisibility(View.GONE);
            viewHolder.hulue.setVisibility(View.GONE);
            viewHolder.touxiang.setVisibility(View.GONE);
        }
        return convertView;
    }


    public class ViewHolder {
        private TextView btnDelete, name, time, neirong, tishi;
        private LinearLayout layout;
        private Button tongyi, hulue;
        private ImageView touxiang, hongdian;

        public ViewHolder(View view) {

            btnDelete = view.findViewById(R.id.btnDelete);
            layout = view.findViewById(R.id.xiaoxi_xt_layout);
            name = view.findViewById(R.id.xiaoxi_name);
            time = view.findViewById(R.id.xiaoxi_time);
            neirong = view.findViewById(R.id.xiaoxi_neirong);
            tongyi = view.findViewById(R.id.xiaoxi_tongyi);
            hulue = view.findViewById(R.id.xiaoxi_hulue);
            touxiang = view.findViewById(R.id.xiaoxi_touxiang);
            tishi = view.findViewById(R.id.xiaoxi_tishi);
            hongdian = view.findViewById(R.id.xiaoxi_hongdian);
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

    private void initDownload(String playeruid, String confirmRes) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "申请好友" + token + "--playeruid--" + playeruid + "--confirmRes--" + confirmRes);
        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/confirmAddFriend")
                .addHeader("token", token)
                .addParams("playeruid", playeruid)
                .addParams("confirmRes", confirmRes)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "申请好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            XiaoxiXTEntity entity = gson.fromJson(result, XiaoxiXTEntity.class);
//                            List<XiaoxiXTEntity.DataBean.LstBean> list;
//                            list = entity.getData().getLst();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
//
////                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                                @Override
////                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                    Intent intent = new Intent();
////                                    Bundle bundle = new Bundle();
////                                    intent.setClass(QBMXActivity.this, QBMXItemActivity.class);
////                                    bundle.putString("uid", data.get(position).getUUID());
////                                    intent.putExtras(bundle);
////
////                                    startActivity(intent);
////                                }
////                            });
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(XiaoxiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
                    }
                });

    }

    //活动邀请
    private void init(String publishId, String confirmRes,String XXuuid) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "被邀请用户进行操作" + token + "--publishId--" + publishId + "--confirmRes--" + confirmRes);
        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/invitedUserHandle")
                .addHeader("token", token)
                .addParams("publishId", publishId)
                .addParams("checkType", confirmRes)
                .addParams("payType", "balance")
                .addParams("uuid", XXuuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "活动邀请" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            XiaoxiXTEntity entity = gson.fromJson(result, XiaoxiXTEntity.class);
//                            List<XiaoxiXTEntity.DataBean.LstBean> list;
//                            list = entity.getData().getLst();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
//
////                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                                @Override
////                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                    Intent intent = new Intent();
////                                    Bundle bundle = new Bundle();
////                                    intent.setClass(QBMXActivity.this, QBMXItemActivity.class);
////                                    bundle.putString("uid", data.get(position).getUUID());
////                                    intent.putExtras(bundle);
////
////                                    startActivity(intent);
////                                }
////                            });
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(XiaoxiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
                    }
                });

    }


    //项目详情
    private void xiangqing(String uuid, final int position) {
        LogU.Ld("1608", "项目详情" + uuid);
        OkHttpUtils
                .get()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            final HDXQEntity entity = gson.fromJson(result, HDXQEntity.class);
                            if (entity.getData().getSiteMoney() == 0) {

                                init(list.get(position).getPublicuuid() + "", "1",list.get(position).getUuid());

                            } else {
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值
                                intent.setClass(context, HomeZhifuActivity.class);
                                bundle.putString("tag", "3");
                                bundle.putString("uuid", list.get(position).getPublicuuid() + "");
                                bundle.putString("cp_fy",entity.getData().getRefereeFee()+ "");
                                bundle.putString("XXUuid", list.get(position).getUuid());

                                intent.putExtras(bundle);
                                context.startActivity(intent);

                            }
//                            changdifei = entity.getData().getSiteMoney() + "";
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                        }
                    }
                });
    }

    //消息详情
    private void xiaoxi(String uuid) {

        OkHttpUtils
                .get()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMessageInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("msgCate", "systems")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "消息详情" + result);
                        Boolean a = result.indexOf("2000") != -1;

                    }
                });

    }
}
