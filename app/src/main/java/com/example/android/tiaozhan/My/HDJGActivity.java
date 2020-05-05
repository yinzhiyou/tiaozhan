package com.example.android.tiaozhan.My;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 填写比赛结果
 */
public class HDJGActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout ying, shu, ping, queren;
    private TextView yingText, shuText, pingText,biaoti;
    private String token, touxiang, nickname, uuid,duiwu, gameRes = "1";
    private SPUtils spUtils;
    private ImageView fanhui;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hdjg);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_hdjg;
    }

    @Override
    protected void initUIAndListener() {
        ying = findViewById(R.id.hdjg_ying);
        ying.setOnClickListener(this);
        shu = findViewById(R.id.hdjg_shu);
        shu.setOnClickListener(this);
        ping = findViewById(R.id.hdjg_ping);
        ping.setOnClickListener(this);
        queren = findViewById(R.id.hdjg_queding);
        queren.setOnClickListener(this);
        yingText = findViewById(R.id.hdjg_ying_text);
        shuText = findViewById(R.id.hdjg_shu_text);
        pingText = findViewById(R.id.hdjg_ping_text);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
//        duiwu =  bundle.getString("duiwu");

    }

    @Override
    protected void initData() {
            biaoti.setText("填写比赛结果");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hdjg_ying:
                ying.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                yingText.setTextColor(getResources().getColor(R.color.colorWhite));
                shu.setBackgroundResource(R.drawable.shuying_4);
                shuText.setTextColor(getResources().getColor(R.color.my_tab));
                ping.setBackgroundResource(R.drawable.shuying_4);
                pingText.setTextColor(getResources().getColor(R.color.my_tab));
                gameRes = "1";
                break;
            case R.id.hdjg_shu:
                shu.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                shuText.setTextColor(getResources().getColor(R.color.colorWhite));
                ying.setBackgroundResource(R.drawable.shuying_4);
                yingText.setTextColor(getResources().getColor(R.color.my_tab));
                ping.setBackgroundResource(R.drawable.shuying_4);
                pingText.setTextColor(getResources().getColor(R.color.my_tab));
                gameRes = "2";
                break;
            case R.id.hdjg_ping:
                ping.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                pingText.setTextColor(getResources().getColor(R.color.colorWhite));
                shu.setBackgroundResource(R.drawable.shuying_4);
                shuText.setTextColor(getResources().getColor(R.color.my_tab));
                ying.setBackgroundResource(R.drawable.shuying_4);
                yingText.setTextColor(getResources().getColor(R.color.my_tab));
                gameRes = "3";
                break;
            case R.id.hdjg_queding:
                huoquyundong();
                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }

    //提交结果
    private void huoquyundong() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userConfirmGameRes")
                .addHeader("token", token)
                .addParams("publicuuid", uuid)
                .addParams("gameRes", gameRes)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提交结果" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            YundongEntity entity = gson.fromJson(result, YundongEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            /*Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(HDJGActivity.this,PingjiaActivity.class);
                            bundle.putString("uuid",uuid);
                            bundle.putString("tag","9");
                            intent.putExtras(bundle);

                            startActivity(intent);*/
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                        }
                    }
                });
    }
}
