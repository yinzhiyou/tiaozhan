package com.example.android.tiaozhan.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.PromoterDCLAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PromoterComplainthdlistEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.reserve.ReserveCGDetailsActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员待处理投诉
 */
public class PromoterDCLActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_dcl);
//    }

    private TextView biaoti;
    private ImageView fanhui;
    private PullToRefreshListView listView;
    private PromoterDCLAdapter adapter;
    private String token,uid,nameString,scjiageString,ZMurl,FMurl,TGid;
    private SPUtils spUtils;
    private  List<PromoterComplainthdlistEntity.DataBean> data;
    private int page = 1;
    private LinearLayout zanwu_d;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_dcl;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zanwu_d = findViewById(R.id.zanwu_d);
        listView = findViewById(R.id.promo_dcl_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        biaoti.setText("待处理投诉活动");
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");

        data = new ArrayList<>();
        adapter = new PromoterDCLAdapter(this,data,"1");
        listView.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        initDownload(1);
        shuaxin();
    }

    private void shuaxin() {
        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
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

                    }
                });

    }

    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterComplainthdlist")
                .addHeader("token",token)
                .addParams("isHandle","0")
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "待处理投诉活动" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                          final   PromoterComplainthdlistEntity entity = gson.fromJson(result, PromoterComplainthdlistEntity.class);
                            zanwu_d.setVisibility(View.GONE);
                            List<PromoterComplainthdlistEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);
                            if (page == 1){
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }


                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值
                                    if (entity.getData().get(position-1).getReserve()==1){
                                        intent.setClass(PromoterDCLActivity.this, ReserveCGDetailsActivity.class);

                                        bundle.putString("uuid",data.get(position-1).getPublicUUid());
                                        bundle.putString("tag","1");
                                        bundle.putString("isHandle","0");
                                        intent.putExtras(bundle);

                                        startActivity(intent);
                                    }else {
                                        intent.setClass(PromoterDCLActivity.this, PromoterXQActivity.class);

                                        bundle.putString("uuid",data.get(position-1).getPublicUUid());
                                        bundle.putString("tag","1");
                                        bundle.putString("isHandle","0");
                                        intent.putExtras(bundle);

                                        startActivity(intent);
                                    }
                                }
                            });

                            if(EmptyUtils.isListEmpty(data)){
                                LogU.Ld("1608",""+EmptyUtils.isListEmpty(list)+ data.size()+EmptyUtils.isListEmpty(data));
                                zanwu_d.setVisibility(View.VISIBLE);
                            }
                        } else {

                            listView.onRefreshComplete();
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterDCLActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(PromoterDCLActivity.this, DengluActivity.class);
                            startActivity(intent);
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
}
