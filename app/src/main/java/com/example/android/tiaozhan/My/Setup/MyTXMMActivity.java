package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 设置提现密码1
 */
public class MyTXMMActivity extends BaseActivity implements View.OnClickListener{

    private TextView biaoti,yan,shoujiText;
    private ImageView fanhui;
    private RelativeLayout xiayibu;

    private MyCountDownTimer myCountDownTimer;
    private String shouji,tag;
    private SPUtils spUtils;
    private EditText yanzhengma;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_txmm);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_txmm;


    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        xiayibu = findViewById(R.id.my_txmm_xiayibu);
        xiayibu.setOnClickListener(this);
        yan = findViewById(R.id.txmm_yan);
        yan.setOnClickListener(this);
        yanzhengma = findViewById(R.id.txmm_yanzheng);

        myCountDownTimer = new MyCountDownTimer(60000, 1000);
        shoujiText = findViewById(R.id.txmm_shouji);
        spUtils = new SPUtils();
        shouji = (String) spUtils.get(this, "shouji", "");


    }

    @Override
    protected void initData() {
        biaoti.setText("设置提现密码");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tag =  bundle.getString("tag");
        String phoneNumber = shouji.substring(0, 3) + "****" + shouji.substring(7, shouji.length());
        shoujiText.setText(phoneNumber);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new  Intent();
        switch (v.getId()){
            case R.id.fanhui:

                if (!EmptyUtils.isStrEmpty(tag)){
                    if (tag.equals(3)) {
                        intent.setClass(MyTXMMActivity.this, SMRZTwoActivity.class);
                        Bundle bundle = new Bundle();//传值
                        bundle.putString("tag", "3");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    finish();
                }else {
                    finish();
                }

                break;
            case R.id.my_txmm_xiayibu:
                yanzheng();
                break;
            case R.id.txmm_yan:
                yanzhengma();
                break;
        }
    }


    private void yanzhengma() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                .addParams("mobile", shouji)
                .addParams("type", "realAuthor")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "验证码" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        Toast.makeText(MyTXMMActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        if(a){
                            myCountDownTimer.start();
                        }else {

                        }

                    }
                });


    }
    //验证验证码是否正确
    private void yanzheng() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkCodeIsTrue")
                .addParams("mobile", shouji)
                .addParams("code",yanzhengma.getText().toString())
                .addParams("type", "realAuthor")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "验证码" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        Toast.makeText(MyTXMMActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        if(a){
                            Intent intent = new Intent();
                            intent.setClass(MyTXMMActivity.this,MyTXMMTwoActivity.class);
                            startActivity(intent);
                        }else {

                        }

                    }
                });


    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            yan.setClickable(false);
            yan.setText(l / 1000 + "s");
            Log.d("1608", l / 1000 + "s");

        }

        @Override
        public void onFinish() {
            //重新给Button设置文字
            yan.setText("重新获取验证码");
            //设置可点击
            yan.setClickable(true);
        }
    }
}
