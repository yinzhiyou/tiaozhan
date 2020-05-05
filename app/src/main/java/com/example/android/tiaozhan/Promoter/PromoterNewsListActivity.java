package com.example.android.tiaozhan.Promoter;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.PromoterXiaoxNewListAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.DelpromoterNewsEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PromoterNewsListEntyty;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PromoterNewsListActivity extends BaseActivity implements View.OnClickListener {
    private String token;
    private SPUtils spUtils;
    private int page = 1;
    private TextView biaoti;
    private ImageView fanhui;
    private PullToRefreshListView pro_xiaoxi_list;
    private List<PromoterNewsListEntyty.DataBean> data;
    private PromoterXiaoxNewListAdapter adapter;
    private int positions;


    @Override
    public int initContentView() {
        return R.layout.activity_promoter_newlist;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        pro_xiaoxi_list = findViewById(R.id.pro_xiaoxi_list);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        data = new ArrayList<>();
        adapter = new PromoterXiaoxNewListAdapter(this, data);

    }

    @Override
    protected void initData() {
        pro_xiaoxi_list.setMode(PullToRefreshBase.Mode.BOTH);
       pro_xiaoxi_list.setAdapter(adapter);
        biaoti.setText("消息");
        shuaxin();
        initDownload(page);

        adapter.setOnItemDeleteClickListener(new PromoterXiaoxNewListAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int position) {


                    data.remove(position);
                    delDownload(position);
                    adapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        initDownload(page);
    }

    private void shuaxin() {

        pro_xiaoxi_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
                {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    //删除条目
    private void delDownload(final int positions) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "消息中心" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterNewsDel")
                .addHeader("token", token)
                .addParams("uuid", data.get(positions).getUuid() + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "消息中心" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "消息中心" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            DelpromoterNewsEntity entity = gson.fromJson(result, DelpromoterNewsEntity.class);
                            Toast.makeText(PromoterNewsListActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterNewsListActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(PromoterNewsListActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }




    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "消息中心" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterNewsList")
                .addHeader("token", token)
                .addParams("page", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "消息中心" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "消息中心" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PromoterNewsListEntyty entity = gson.fromJson(result, PromoterNewsListEntyty.class);
                            final List<PromoterNewsListEntyty.DataBean> list;
                            list = entity.getData();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
                            if (page == 1){
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                pro_xiaoxi_list.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                pro_xiaoxi_list.onRefreshComplete();
                            }



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterNewsListActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(PromoterNewsListActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
        }
    }
}
