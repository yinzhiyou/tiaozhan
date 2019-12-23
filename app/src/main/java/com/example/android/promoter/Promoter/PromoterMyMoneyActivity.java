package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterMyMoneyEntity;
import com.example.android.promoter.My.MyTIxianActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class PromoterMyMoneyActivity extends BaseActivity implements View.OnClickListener {
    private TextView mx_text,num_text,tixan_text;
    private ImageView fanhui;
    private SPUtils spUtils;
    private String token;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_mymoney;
    }

    @Override
    protected void initUIAndListener() {
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");

        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        mx_text = findViewById(R.id.mx_text);
        mx_text.setOnClickListener(this);
        num_text = findViewById(R.id.num_text);
        tixan_text = findViewById(R.id.tixan_text);
        tixan_text.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        promoterMoney();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.fanhui:

                intent.setClass(PromoterMyMoneyActivity.this,MyPromoterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mx_text:
                promoterMoney();
                intent.setClass(PromoterMyMoneyActivity.this,PromoterMoneyDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tixan_text:
                promoterMoney();
                intent.setClass(PromoterMyMoneyActivity.this,MyTIxianActivity.class);
                intent.putExtra("tgId","1");
                startActivity(intent);
                break;
        }
    }


    //我的钱包
    private void promoterMoney(){
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterMyMoney")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "我的钱包" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Gson gson = new Gson();
                        if (a) {


                            PromoterMyMoneyEntity entity = gson.fromJson(result, PromoterMyMoneyEntity.class);
                            num_text.setText(entity.getData().getMoney()+"");

                        } else {
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterMyMoneyActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(PromoterMyMoneyActivity.this, DengluActivity.class);
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
