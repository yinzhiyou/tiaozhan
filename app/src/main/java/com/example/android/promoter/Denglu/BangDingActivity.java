package com.example.android.promoter.Denglu;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.BangDingEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;


/**
 *绑定手机号
 */

public class BangDingActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti,yan;
    private ImageView fanhui;
    private String uid;
    private EditText shouji,yanzheng;
    private RelativeLayout bangding,yanzhengRL;
    private  MyCountDownTimer myCountDownTimer;
    private SPUtils spUtils;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bang_ding);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_bang_ding;
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
        shouji = findViewById(R.id.bangding_shouji);
        yanzheng = findViewById(R.id.bangding_yanzheng);
        bangding =findViewById(R.id.bangding_queding);
        bangding.setOnClickListener(this);
        yan = findViewById(R.id.bangding_yan);
        yanzhengRL = findViewById(R.id.yanzheng);
        yanzhengRL.setOnClickListener(this);
        spUtils = new SPUtils();

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
    }

    @Override
    protected void initData() {
        biaoti.setText("绑定手机号");
        myCountDownTimer = new MyCountDownTimer(60000, 1000);
    }

    private void bangding() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/bindmobile")
                .addParams("mobile", shouji.getText().toString())
                .addParams("code",yanzheng.getText().toString())
                .addParams("wechatid",uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "绑定手机" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            BangDingEntity entity = gson.fromJson(result, BangDingEntity.class);
                            spUtils.put(BangDingActivity.this, "logintoken", entity.getData().getToken());
                            spUtils.put(BangDingActivity.this, "uuid", entity.getData().getUuid());
                            spUtils.put(BangDingActivity.this, "touxiang", entity.getData().getImgURL());
                            spUtils.put(BangDingActivity.this, "nickname", entity.getData().getNickname());
                            spUtils.put(BangDingActivity.this, "shouji",shouji.getText().toString());
                            Intent intent = new Intent();
                            intent.setClass(BangDingActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Intent intent = new Intent();
//                            Bundle bundle = new Bundle();//传值
//                            intent.setClass(DengluActivity.this, GRXXActivity.class);
//                            bundle.putString("tab","1");
//                            intent.putExtras(bundle);
//                            startActivity(intent);

                            Toast.makeText(BangDingActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bangding_queding:

                bangding();
                break;
            case R.id.yanzheng:
                yanzhengma();
                break;

        }
    }
    private void yanzhengma() {
        LogU.Ld("1608","aaaaa");
        if(!isChinaPhoneLegal(shouji.getText().toString())){
            Toast.makeText(this, "手机号码有误", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(shouji.getText())){
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
        }else {
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("type", "bindmobile")
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

                            if(a){
                                myCountDownTimer.start();
                            }else {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                ToastUitl.longs(entity.getMsg());
                            }

                        }
                    });
        }

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
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
