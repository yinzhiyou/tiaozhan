package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.HQYSEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 隐私设置
 */
public class YSSZActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private LinearLayout heimingdan;
    private Switch switch1, switch2;
    private String token,one;
    private SPUtils spUtils;
    private String fujin,haoyou;
    private boolean fujinYN,haoyouYN;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_yssz);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_yssz;
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
        heimingdan = findViewById(R.id.yssz_heimingdan);
        heimingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(YSSZActivity.this, HMDActivity.class);
                startActivity(intent);
            }
        });
        switch1 = findViewById(R.id.fujin_switch);
        switch2 = findViewById(R.id.haoyou_switch);


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");


switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            fujin = "1";
        }else {
            fujin="0";
        }
        panduan();

    }
});
switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            haoyou = "1";
        }else {
            haoyou="0";
        }
        panduan();
    }
});
        /*switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fujin.equals("1")){
                    fujin = "0";
                    switch1.setChecked(false);
                    panduan();
                }else{
                    fujin = "1";
                    switch1.setChecked(true);
                    panduan();

                }
            }
        });

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haoyou.equals("1")){
                    haoyou = "0";
                    switch2.setChecked(false);
                    panduan();
                }else{
                    haoyou = "1";
                    switch2.setChecked(true);
                    panduan();
                }
            }
        });*/
    }

    @Override
    protected void initData() {
        biaoti.setText("隐私设置");
        huoqu();
    }


    //安全隐私
    private void panduan() {
        LogU.Ld("1608", "修改安全隐私" + fujin+haoyou);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/setUserReceives")
                .addHeader("token", token)
                .addParams("isReceiveNearBy",fujin)
                .addParams("isReceiveFriends",haoyou)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "修改安全隐私" + result);
                        Boolean a = result.indexOf("2000") != -1;

//                        if(a){
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.setClass(GHSJTwoActivity.this,GHSJThreeActivity.class);
//                            startActivity(intent);
//                        }else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
    }


    //获取
    private void huoqu() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserReceives")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "安全隐私" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if(a){
                            Gson gson = new Gson();
                            HQYSEntity entity = gson.fromJson(result, HQYSEntity.class);
                                fujin = entity.getData().getAcceptNearplayerInvite()+"";
                                haoyou = entity.getData().getAcceptFirendInvite()+"";
                            LogU.Ld("1608", "修改安全隐私====" + fujin+haoyou);
                            if (entity.getData().getAcceptNearplayerInvite() ==1){


                                switch1.setChecked(true);
                            }else{

                             //   switch1.setChecked(false);
                            }

                            if (entity.getData().getAcceptFirendInvite() ==1){

                                switch2.setChecked(true);
                            }else{

                             //   switch1.setChecked(false);
                            }
                        }else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}
