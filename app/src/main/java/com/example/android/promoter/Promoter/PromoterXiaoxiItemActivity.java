package com.example.android.promoter.Promoter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Entity.PromoterNewsReadEntity;
import com.example.android.promoter.Entity.XiaoxiItemEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PromoterXiaoxiItemActivity extends BaseActivity {
    private TextView biaoti,time,neirong;
    private ImageView fanhui;

    private String token, touxiang, nickname, uuid;
    private SPUtils spUtils;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_xiaox_item;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        time = findViewById(R.id.pro_xiaoxi_item_time);
        neirong = findViewById(R.id.pro_xiaoxi_item_neirong);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
    }

    @Override
    protected void initData() {
        biaoti.setText("消息详情");
        xiaoxi();
    }


    //消息详情
    private void xiaoxi() {

        OkHttpUtils
                .post()
                .url("https://app.tiaozhanmeiyitian.com/api" + "/getPromoterNewsRead")
                .addHeader("token",token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "消息详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PromoterNewsReadEntity entity = gson.fromJson(result, PromoterNewsReadEntity.class);
                            time.setText(entity.getData().getIntime());
                            neirong.setText(entity.getData().getContent());
                            entity.getData().setIsred(1);

                        } else {

                        }
                    }
                });

    }
}
