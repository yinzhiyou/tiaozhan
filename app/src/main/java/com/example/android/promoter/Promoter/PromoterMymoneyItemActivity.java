package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterMoneyDetailsEntity;
import com.example.android.promoter.Entity.QBMXItemEntity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.My.QBMXItemActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PromoterMymoneyItemActivity extends BaseActivity {
    private TextView biaoti,chuzhang,jine,leixing,time,shengyu,beizhu,bianhao,qbmx_list_item_huod,qbmx_list_item_hd_time,qbmx_list_item_cg_name;
    private ImageView fanhui;
    private String token,touxiang,nickname,uid,uuid;
    private SPUtils spUtils;
    private LinearLayout hdbh;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_mymoney_item;
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
        chuzhang = findViewById(R.id.qbmx_list_item_chuzhang);
        jine = findViewById(R.id.qbmx_list_item_jine);
        leixing = findViewById(R.id.qbmx_list_item_leixing);
        time = findViewById(R.id.qbmx_list_item_time);
        shengyu = findViewById(R.id.qbmx_list_item_shengyu);
        beizhu = findViewById(R.id.qbmx_list_item_beizhu);
        bianhao = findViewById(R.id.qbmx_list_item_bianhao);
        hdbh = findViewById(R.id.qbmx_list_item_hdbh);

        qbmx_list_item_huod = findViewById(R.id.qbmx_list_item_huod);
        qbmx_list_item_hd_time = findViewById(R.id.qbmx_list_item_hd_time);
        qbmx_list_item_cg_name = findViewById(R.id.qbmx_list_item_cg_name);

        /*hdbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(PromoterMymoneyItemActivity.this, HomeHDXQActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });*/

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
    }

    @Override
    protected void initData() {
        biaoti.setText("钱包明细");
        initDownload();
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "钱包明细详情" + token+"======"+uid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterMoneyDetails")
                .addHeader("token",token)
                .addParams("walletuuid",uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "钱包明细详情" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "钱包明细详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PromoterMoneyDetailsEntity entity = gson.fromJson(result, PromoterMoneyDetailsEntity.class);
                            jine.setText(entity.getData().getMoney()+"");
                            time.setText(entity.getData().getRecordDate());
                            beizhu.setText(entity.getData().getRecordReason()+"");
                            shengyu.setText(entity.getData().getCurrentmoney()+"");
                            qbmx_list_item_huod.setText(entity.getData().getPublic_orderId()+"");
                            qbmx_list_item_hd_time.setText(entity.getData().getPublic_time()+"");
                            qbmx_list_item_cg_name.setText(entity.getData().getSitename()+"");

                            if (entity.getData().getInOrOut()==1){
                                leixing.setText("收入");
                                chuzhang.setText("入账金额");
                                jine.setTextColor(getResources().getColor(R.color.hongse));
                            }else {
                                leixing.setText("支出");
                                chuzhang.setText("出账金额");
                                jine.setTextColor(getResources().getColor(R.color.huise));
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterMymoneyItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
