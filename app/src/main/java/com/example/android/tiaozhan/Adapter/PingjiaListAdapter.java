package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PingjiaListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.StarBar;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;

public class PingjiaListAdapter extends BaseAdapter {
    private Context context;
    private List<PingjiaListEntity.DataBean.UsersInfoBean> list;
    // private List<PingjiaListEntity.DataBean.LabelBean> list2;
    private List<BiaoqianEntity.DataBean> list3=new ArrayList<>();

    private PingjiaBiaoqianAdapter adapter;
    private int c;
    private PingJUsersInfoAdapter mpjUser;
    private JsCallBack jsCallBack;
    private String uid,sportId;
    private int num = 5;
private int positon;
    public static Set<Integer> positionSet = new HashSet<>();

    public PingjiaListAdapter(Context context, List<PingjiaListEntity.DataBean.UsersInfoBean> list,  String uid) {
        this.context = context;
        this.list = list;
        this.list3 = list3;
        this.uid = uid;

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

    public void setSelectItem(int c) {

        this.c = c;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
       this.positon=position;
//        adapter = new PingjiaBiaoqianAdapter(context, list3);
//        adapter = new PingjiaBiaoqianAdapter(context, list2);

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.pingjia_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//            viewHolder.gridView.setAdapter(adapter);
        viewHolder.starBar.setStarMark(5, 2);
       viewHolder.starBar.setIntegerMark(true);

        viewHolder.name.setText(list.get(position).getNickname());
        LogU.Ld("1609","运动模式"+sportId);
        if (!EmptyUtils.isStrEmpty(sportId)) {
            if (sportId.equals("2")) {
                viewHolder.jg_text.setVisibility(View.VISIBLE);
                viewHolder.jg_text.setText("填写比赛结果：" + list.get(position).getResult());
            } else {
                viewHolder.jg_text.setVisibility(View.GONE);
            }
        }else {
            viewHolder.jg_text.setVisibility(View.GONE);
        }

        viewHolder.duiwu.setText(list.get(position).getTeam());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
//        viewHolder.gridView.setAdapter(adapter);
//        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        final GridLayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = viewHolder.recyclerView.getAdapter().getItemViewType(position);//获得返回值
                if (type == 99) {
                    return mLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        viewHolder.recyclerView.setLayoutManager(mLayoutManager);

        // mpjUser = new PingJUsersInfoAdapter(R.layout.adapter_activity_label_content,list.get(position).getRes());
        PingJiaBiaoQAdapter mPingJia=new PingJiaBiaoQAdapter(context,list.get(position).getRes(),position);
          viewHolder.recyclerView.setAdapter(mPingJia);
      final   LinkLabelAdapter linkLabelAdapter=new LinkLabelAdapter(context,list3,position);
     // final   PingJBiaoQianAdapter linkLabelAdapter=new PingJBiaoQianAdapter(R.layout.adapter_activity_label_content,list3);
        LogU.Ld("1608", "条目===" + list.get(position).getPlayerUUID());
      //  viewHolder.starBar.setOnStarChangeListener(null);
        viewHolder.starBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {

                num = (new Double(mark)).intValue();
                String ss = mark + "";
                if (viewHolder.fenshu.getText().toString().equals(num + "分")) {

                } else {
                    LogU.Ld("1608", "foalst" + viewHolder.fenshu.getText().toString() + mark);
                    viewHolder.fenshu.setText(num + "分");
                    jsCallBack.callBack(num);

                    OkHttpUtils
                            .get()
                            .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLabelInfo")
                            .addParams("level", num+"")
                            .addParams("uuid", uid)
                            .addParams("userid", list.get(position).getPlayerUUID())
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
                                            viewHolder.recyclerView.setAdapter(linkLabelAdapter);

//                            adapter.notifyDataSetChanged();
                                        //   linkLabelAdapter.notifyItemChanged(positon);
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




                mOnItemDeleteListener.onDeleteClick(position);


            }
        });

        // mpjUser.notifyDataSetChanged();

        return convertView;
    }


    public class ViewHolder {
        //        private GridView gridView;
        private ImageView touxiang;
        private TextView name, duiwu, fenshu, jg_text;
        private StarBar starBar;
        private RecyclerView recyclerView;

        public ViewHolder(View view) {
//            gridView = view.findViewById(R.id.pingjia_grid);
            touxiang = view.findViewById(R.id.pingjia_item_touxiang);
            name = view.findViewById(R.id.pingjia_item_name);
            duiwu = view.findViewById(R.id.pingjia_item_duiwu);
            starBar = view.findViewById(R.id.starBar);
            fenshu = view.findViewById(R.id.pingjia_list_fenshu);
            recyclerView = view.findViewById(R.id.recyclerview);
            jg_text = view.findViewById(R.id.jg_text);
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


    //获取标签
    private void huoqubiaoqian(String level, final String uuid, String userid,final LinkLabelAdapter linkLabelAdapter) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "获取标签" + level);
        OkHttpUtils
                .get()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLabelInfo")
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
                          //   linkLabelAdapter.notifyItemChanged(positon);
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
    public interface JsCallBack {
        void callBack( int spId);
    }
    public void setJsCallBack(JsCallBack jsCallBack) {
        this.jsCallBack = jsCallBack;
    }
    public void getUUid(String uuid) {
        uid = uuid;
    }

    public void getSport(String sportId){
        this.sportId=sportId;
    }
}