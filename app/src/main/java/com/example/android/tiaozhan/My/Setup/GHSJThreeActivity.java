package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.os.CountDownTimer;
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
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *更换手机号3
 */
public class GHSJThreeActivity extends BaseActivity {

    private TextView biaoti,yan;
    private ImageView fanhui;
    private String token,touxiang,nickname,one;
    private SPUtils spUtils;
    private EditText shouji,yanzhengma;
    private MyCountDownTimer myCountDownTimer;
    private RelativeLayout tijiao;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ghsjthree);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_ghsjthree;
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
        shouji = findViewById(R.id.ghsj_three_shouji);
        yanzhengma = findViewById(R.id.ghsj_three_yanzhengma);
        yan = findViewById(R.id.ghsj_yan);
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yanzhengma();
            }
        });

        tijiao = findViewById(R.id.my_ghsj_thrss_tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panduan();
            }
        });
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        myCountDownTimer = new MyCountDownTimer(60000, 1000);

    }

    @Override
    protected void initData() {
        biaoti.setText("更换手机号");
    }

//    updateUserMobile
//修改手机
    private void panduan() {
        LogU.Ld("1608", "修改手机" + token);
    OkHttpUtils
            .post()
            .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/updateUserMobile")
            .addHeader("token", token)
            .addParams("mobile",shouji.getText().toString())
            .addParams("code",yanzhengma.getText().toString())
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    String result = response.toString();
                    LogU.Ld("1608", "修改手机" + result);
                    Boolean a = result.indexOf("2000") != -1;

                    if(a){
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        Toast.makeText(GHSJThreeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(GHSJThreeActivity.this,MyShezhiActivity.class);
                        startActivity(intent);
                    }else {
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        Toast.makeText(GHSJThreeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


}

    private void yanzhengma() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                .addParams("mobile", shouji.getText().toString())
                .addParams("type", "updateTels")
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
                        Toast.makeText(GHSJThreeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        if(a){
                            myCountDownTimer.start();
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
