package com.example.android.promoter.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.BiaoqianEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PingjiaListEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.StarBar;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PingjiaListAdapter extends BaseAdapter {
    private Context context;
    private List<PingjiaListEntity.DataBean.UsersInfoBean> list;
//    private List<PingjiaListEntity.DataBean.LabelBean> list2;
    private List<BiaoqianEntity.DataBean> list3;

    private PingjiaBiaoqianAdapter adapter;
    private int c;

    public PingjiaListAdapter(Context context, List<PingjiaListEntity.DataBean.UsersInfoBean> list,List<BiaoqianEntity.DataBean> list3) {
        this.context = context;
        this.list = list;
        this.list3 = list3;

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
//        viewHolder.starBar.setStarMark(5);
        viewHolder.starBar.setIntegerMark(true);
        viewHolder.name.setText(list.get(position).getNickname());
        viewHolder.duiwu.setText(list.get(position).getTeam());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
//        viewHolder.gridView.setAdapter(adapter);
//        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        final GridLayoutManager mLayoutManager = new GridLayoutManager(context, 4);
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
         final LinkLabelAdapter linkLabelAdapter = new LinkLabelAdapter(context, list3, position);
        viewHolder.recyclerView.setAdapter(linkLabelAdapter);

        viewHolder.starBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                viewHolder.recyclerView.setAdapter(linkLabelAdapter);
                String ss = mark+"";
                if (viewHolder.fenshu.getText().toString().equals(ss)){
                    LogU.Ld("1608", "ture"+viewHolder.fenshu.getText().toString()+mark);
                }else{
                    LogU.Ld("1608", "foalst"+viewHolder.fenshu.getText().toString()+mark);
                    huoqubiaoqian(mark+"",linkLabelAdapter);
                }
                viewHolder.fenshu.setText(mark + "");
                mOnItemDeleteListener.onDeleteClick(position);
//                linkLabelAdapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }


    public class ViewHolder {
        //        private GridView gridView;
        private ImageView touxiang;
        private TextView name, duiwu, fenshu;
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
    private void huoqubiaoqian(String level, final LinkLabelAdapter linkLabelAdapter) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "获取标签" + level);
        OkHttpUtils
                .get()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLabelInfo")
                .addParams("level", level)
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
                            linkLabelAdapter.notifyDataSetChanged();
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

}