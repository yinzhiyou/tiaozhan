package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.promoter.Adapter.MyPromterCGListAdapter;
import com.example.android.promoter.Adapter.ProMoneyDetailAdapter;
import com.example.android.promoter.Entity.ProMoneyDetailEntity;
import com.example.android.promoter.Entity.PromoterFJCGEntity;
import com.example.android.promoter.Entity.PromoterMyMoneyEntity;
import com.example.android.promoter.My.QBMXActivity;
import com.example.android.promoter.My.QBMXItemActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PromoterMoneyDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView fanhui;
    private TextView biaoti;
    private PullToRefreshListView listview;
    private int page = 1;
    private List<ProMoneyDetailEntity.DataBean> data;
    private SPUtils spUtils;
    private String token;
    private ProMoneyDetailAdapter adapter;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_money_detail;
    }

    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        listview = findViewById(R.id.pro_money_detail_list);


    }

    @Override
    protected void initData() {
        biaoti.setText("钱包明细");
        spUtils = new SPUtils();
        token = (String) spUtils.get(PromoterMoneyDetailActivity.this, "logintoken", "");
        LogU.Ld("1608","token我是"+token);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList();
        adapter = new ProMoneyDetailAdapter(this, data);
        listview.setAdapter(adapter);
        initDownload(page);
        shuaxin();
    }

    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs)+ "/getPromoterMoneyList")
                .addParams("page", page + "")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    LogU.Ld("1608","什么枪口"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "钱包列表" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            ProMoneyDetailEntity entity = gson.fromJson(result, ProMoneyDetailEntity.class);
                            List<ProMoneyDetailEntity.DataBean> list;
                            list = entity.getData();
//                            data.addAll(list);
                            if (page == 1) {
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                listview.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listview.onRefreshComplete();
                            }

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(PromoterMoneyDetailActivity.this,PromoterMymoneyItemActivity.class);
                                    bundle.putString("uid",data.get(position-1).getUUID());
                                    intent.putExtras(bundle);

                                    startActivity(intent);
                                }
                            });


                        } else {
                            listview.onRefreshComplete();
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fanhui:
                Intent intent = new Intent();
                intent.setClass(PromoterMoneyDetailActivity.this, PromoterMyMoneyActivity.class);
                startActivity(intent);
                finish();
                break;

        }

    }

    private void shuaxin() {
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                //这里写下拉刷新的任务

                page = 1;
                data.clear();
                LogU.Ld("1608", "下拉" + page + "");
                initDownload( page);
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                //这里写上拉加载更多的任务
                page++;
                initDownload(page);
            }
        });

    }

}
