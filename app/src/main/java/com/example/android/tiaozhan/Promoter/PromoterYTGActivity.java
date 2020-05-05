package com.example.android.tiaozhan.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.PromoYTGAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PromoterYTGEntity;
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
/**
 * 推广员以推广场馆活动
 */
public class PromoterYTGActivity extends BaseActivity implements View.OnClickListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_ytg);
//    }

    private TextView biaoti,dcl,clz,zc;
    private ImageView fanhui;
    private PullToRefreshListView listView;
    private PromoYTGAdapter adapter;
    private String token,uid,nameString,scjiageString,ZMurl,FMurl,isHandle = "0";
    private SPUtils spUtils;
    private  List<PromoterYTGEntity.DataBean> data;
    private int page = 1;
    private PromoterYTGEntity entity;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_ytg;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        listView = findViewById(R.id.promo_ytg_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        dcl = findViewById(R.id.promo_ytg_dcl);
        dcl.setOnClickListener(this);
        clz = findViewById(R.id.promo_ytg_clz);
        clz.setOnClickListener(this);
        zc = findViewById(R.id.promo_ytg_zc);
        zc.setOnClickListener(this);

        data = new ArrayList<>();
        adapter = new PromoYTGAdapter(this,data);
        listView.setAdapter(adapter);


        biaoti.setText("已推广运营场馆");
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");

    }

    @Override
    protected void initData() {

        initDownload("0",1);
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

                        initDownload(isHandle,page);

                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page+"");
                        initDownload(isHandle,page);

                    }
                });

    }


    private void initDownload(final String isHandle, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        LogU.Ld("1608", "已推广运营场馆" + page+"isHandle"+isHandle);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterComplaintcglist")
                .addHeader("token",token)
                .addParams("isHandle",isHandle)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "已推广运营场馆" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, PromoterYTGEntity.class);

                            if(entity.getOther()!=null){
                                dcl.setText("待处理投诉"+"("+entity.getOther().getStatus0()+")");
                                clz.setText("处理中投诉"+"("+entity.getOther().getStatus2()+")");
                                zc.setText("正常"+"("+entity.getOther().getStatus1()+")");
                            }else {
                                dcl.setText("待处理投诉"+"（"+0+"）");
                                clz.setText("处理中投诉"+"（"+0+"）");
                                zc.setText("正常"+"（"+0+"）");
                            }
                            List<PromoterYTGEntity.DataBean> list;
                            list = entity.getData();
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
                                    intent.setClass(PromoterYTGActivity.this,PromoterCGXXActivity.class);
                                    bundle.putString("name",data.get(position-1).getCg_name());
                                    bundle.putString("uid",data.get(position-1).getSiteUid());
                                    bundle.putString("tag","1");
                                  //  bundle.putString("tag_f","2");
                                    bundle.putString("isHandle",isHandle);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            });
//                            Intent intent = new Intent();
//                            intent.setClass(PromoterONEActivity.this,PromoterTWOActivity.class);
//                            startActivity(intent);
                        } else {
                            listView.onRefreshComplete();
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterYTGActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(PromoterYTGActivity.this, DengluActivity.class);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.promo_ytg_dcl:
                dcl.setTextColor(getResources().getColor(R.color.heise));//字体上色
                clz.setTextColor(getResources().getColor(R.color.bbbbb));
                zc.setTextColor(getResources().getColor(R.color.bbbbb));
                initDownload("0",1);
                isHandle = "0";
                break;
            case R.id.promo_ytg_clz:
                dcl.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
                clz.setTextColor(getResources().getColor(R.color.heise));
                zc.setTextColor(getResources().getColor(R.color.bbbbb));
                initDownload("2",1);
                isHandle = "2";
                break;
            case R.id.promo_ytg_zc:
                dcl.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
                clz.setTextColor(getResources().getColor(R.color.bbbbb));
                zc.setTextColor(getResources().getColor(R.color.heise));
                initDownload("1",1);
                isHandle = "1";
                break;
        }
    }
}
