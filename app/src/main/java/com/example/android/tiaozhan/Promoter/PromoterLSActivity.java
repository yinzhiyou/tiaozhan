package com.example.android.tiaozhan.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.tiaozhan.Adapter.PromoterLSAdaoter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.XSLSEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员协商历史
 */
public class PromoterLSActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_ls);
//    }

    private TextView biaoti;
    private ImageView fanhui;
    private ListView listView;
    private PromoterLSAdaoter adapter;
    private String token, uid,re_com_id, nameString, scjiageString, ZMurl, FMurl, TGid, tagCP;
    private SPUtils spUtils;
    private List<XSLSEntity.DataBean> data;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_ls;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        listView = findViewById(R.id.promo_ls_list);
        data = new ArrayList<>();
        adapter = new PromoterLSAdaoter(this, data);
        biaoti.setText("协商历史");
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        listView.setAdapter(adapter);

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tagCP = bundle.getString("tagCP");
        uid = bundle.getString("uuid");
        re_com_id = bundle.getString("re_com_id");
    }

    @Override
    protected void initData() {
        if (tagCP.equals("2")) {
            initRefereeDownload();
        } else if (tagCP.equals("1")){
            initDownload();
        }else {

        }
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConsultativeHistory")
                .addHeader("token", token)
                .addParams("publicUUID", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "协商历史" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            XSLSEntity entity = gson.fromJson(result, XSLSEntity.class);
                            List<XSLSEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);

                            listView.setAdapter(adapter);


                        } else {
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

    //推广员~协商历史（裁判未到场）
    private void initRefereeDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getRefereeConsultativeHistory")
                .addHeader("token", token)
                .addParams("publicUUID", uid)
                .addParams("re_com_id", re_com_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "协商历史裁判" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            XSLSEntity entity = gson.fromJson(result, XSLSEntity.class);
                            List<XSLSEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);

                            listView.setAdapter(adapter);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(PromoterLSActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //推广员~协商历史（陪练未到场）
    private void initSparrDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/")
                .addHeader("token", token)
                .addParams("publicUUID", uid)
                .addParams("re_com_id", re_com_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception getSparrConsultativeHistorye, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "协商历史陪练" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            XSLSEntity entity = gson.fromJson(result, XSLSEntity.class);
                            List<XSLSEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);

                            listView.setAdapter(adapter);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(PromoterLSActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
}
