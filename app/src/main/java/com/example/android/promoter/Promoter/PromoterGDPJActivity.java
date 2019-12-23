package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.CGXXListAdapter;
import com.example.android.promoter.Adapter.PromoterPjListAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.CGXXEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterDCLEntity;
import com.example.android.promoter.Entity.PromoterPJListEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PromoterGDPJActivity extends BaseActivity implements View.OnClickListener{
    private ImageView fanhui;
    private PromoterPjListAdapter adapter2;
    private List<PromoterPJListEntity.DataBean.CommentLstBean> data2;
    private PullToRefreshListView listView2;
    private String  uid;
    private int page=1;
    private SPUtils spUtils;
    private String token;
    private TextView biaoti;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_cg_gdpj;
    }

    @Override
    protected void initUIAndListener() {
        spUtils = new SPUtils();
        token = (String) spUtils.get(PromoterGDPJActivity.this, "logintoken", "");
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        listView2 = findViewById(R.id.pro_cg_pj_list);
        listView2.setMode(PullToRefreshBase.Mode.BOTH);
        data2 = new ArrayList<>();
        adapter2 = new PromoterPjListAdapter(this, data2);

        listView2.setAdapter(adapter2);
        Intent extras = getIntent();
        uid = extras.getStringExtra("uid");
    }

    @Override
    protected void initData() {
        biaoti.setText("更多评价");
        init();
        shuaxin();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
        }
    }
    //场馆详细信息
    private void init() {

        LogU.Ld("1608", "场馆详细信息" + uid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSiteCommentsLst")
                .addParams("siteUid", uid)
                .addParams("page", page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "场馆详细信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            PromoterPJListEntity entity = gson.fromJson(result, PromoterPJListEntity.class);


                            List<PromoterPJListEntity.DataBean.CommentLstBean> list = entity.getData().getCommentLst();

                            if (page == 1) {
                                data2.clear();
                                data2.addAll(list);

                                adapter2.notifyDataSetChanged();
                                listView2.onRefreshComplete();
                            } else {
                                data2.addAll(list);
//                                listView.setAdapter(adapter);
                                LogU.Ld("1608","数据"+ EmptyUtils.isListEmpty(list)+ list.size());
                                adapter2.notifyDataSetChanged();
                                listView2.onRefreshComplete();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterGDPJActivity .this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(PromoterGDPJActivity.this, DengluActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
    private void shuaxin() {


        listView2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
        {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView)
            {
                Log.e("TAG", "onPullDownToRefresh");
                //这里写下拉刷新的任务

                page = 1;
                data2.clear();
                LogU.Ld("1608", "下拉" + page+"");

                init();

            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView)
            {
                Log.e("TAG", "onPullUpToRefresh");
                //这里写上拉加载更多的任务
                page++;
                LogU.Ld("1608", "上啦" + page+"");
                init();

            }
        });

    }

}
