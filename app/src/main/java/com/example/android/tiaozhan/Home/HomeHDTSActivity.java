package com.example.android.tiaozhan.Home;

import android.content.Intent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PromoterHelpCenterEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HomeHDTSActivity extends BaseActivity implements View.OnClickListener {
    private TextView wofabu, wobaoming;
    private TextView biaoti;
    private ImageView fanhui;
    private View fb,bm;
    private String token, type = "publish",type2 = "publish", statusType = "all";
    private SPUtils spUtils;
    private RecyclerView recycle;
    private List<PromoterHelpCenterEntity.DataBean> data;
    private List<PromoterHelpCenterEntity.DataBean> list;
    private SwipeRefreshLayout refreshLayout;
    @Override
    public int initContentView() {
        return R.layout.activity_home_hdts;
    }

    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.my_hd_fanhui);
        fanhui.setOnClickListener(this);

        wofabu = findViewById(R.id.my_hd_wofabu);
        wofabu.setOnClickListener(this);
        wobaoming = findViewById(R.id.my_hd_wobaoming);
        wobaoming.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        recycle = findViewById(R.id.promoter_help_list);
        refreshLayout = findViewById(R.id.swipe_refresh);
        data = new ArrayList<>();
        fb=findViewById(R.id.my_fabu);
        bm=findViewById(R.id.my_baoming);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
    }

    @Override
    protected void initData() {


        biaoti.setText("帮助中心");
        /*loadDate(page);

        //手动刷新 触发SwipeRefreshLayout的下拉刷新方法
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //   isLoadMore = false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //模拟一下网络请求
                        page = 1;
                        loadDate(page);
                        // adapter.setEnableLoadMore(false);
                        //  adapter.loadMoreComplete();
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //    refreshLayout.setEnabled(false);
                        page++;
                        loadDate(page);
                        if (mCurrentCounter >= list.size()) {
                            //数据全部加载完毕
                            adapter.loadMoreEnd();
                            // adapter.loadMoreEnd(true);
                        } else {
                            if (!isErr) {
                                //成功获取更多数据
                                adapter.addData(data);
                                mCurrentCounter = list.size();
                                adapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败 PullToRefreshUseActivity
                                isErr = true;
                                adapter.loadMoreFail();

                            }
                        }
                    }
                }, 1000);

            }
        }, recycle);
*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_hd_fanhui:
                finish();
                break;
            case R.id.my_hd_wofabu://我发布的
                wofabu.setTextColor(getResources().getColor(R.color.colorWhite));
                wobaoming.setTextColor(getResources().getColor(R.color.my_hd_tab));
                fb.setVisibility(View.VISIBLE);
                bm.setVisibility(View.INVISIBLE);
                type = "publish";
                type2 = "publish";
                initDownload(type, statusType, 1);
                break;
            case R.id.my_hd_wobaoming://我报名的
                wofabu.setTextColor(getResources().getColor(R.color.my_hd_tab));
                wobaoming.setTextColor(getResources().getColor(R.color.colorWhite));
                bm.setVisibility(View.VISIBLE);
                fb.setVisibility(View.INVISIBLE);
                type = "join";
                type2 = "join";
                initDownload(type, statusType, 1);
                break;
        }
    }

    //获取数据
    private void initDownload(String type, String statusType, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "我的活动---" + token + "---type---" + type + "---statusType---" + statusType);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyActiveLst")
                .addHeader("token", token)
                .addParams("type", type)
                .addParams("statusType", statusType)
                .addParams("page", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "我的活动" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PromoterHelpCenterEntity promoterHelpCenterEntity = gson.fromJson(result, PromoterHelpCenterEntity.class);
                            list = promoterHelpCenterEntity.getData();
                         //   recycle.setAdapter(adapter);

                            if (page == 1) {
                                data.clear();
                                data.addAll(list);


                            } else {
                                data.addAll(list);

                                LogU.Ld("1608", "数据" + EmptyUtils.isListEmpty(list) + list.size());
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDTSActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(HomeHDTSActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
}
