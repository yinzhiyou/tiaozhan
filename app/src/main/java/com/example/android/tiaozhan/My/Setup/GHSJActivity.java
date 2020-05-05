package com.example.android.tiaozhan.My.Setup;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;

/**
 * 更换手机号1
 */
public class GHSJActivity extends BaseActivity implements View.OnClickListener {

    private String token;
    private Dialog dialog;
    private TextView ds_xz;
    private TextView biaoti, shoujiText, yan, phone_ds;
    private ImageView fanhui;
    private RelativeLayout xiayibu, yanzheng;

    private MyCountDownTimer myCountDownTimer;
    private String shouji;
    private SPUtils spUtils;
    private EditText yanzhengma;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ghsj);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_ghsj;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        xiayibu = findViewById(R.id.my_ghsj_xiayibu);
        xiayibu.setOnClickListener(this);
        shoujiText = findViewById(R.id.ghsj_shouji);
        yanzheng = findViewById(R.id.yanzheng);
        yanzheng.setOnClickListener(this);
        yanzhengma = findViewById(R.id.ghsj_yanzheng);
        phone_ds = findViewById(R.id.phone_ds);
        phone_ds.setOnClickListener(this);
        yan = findViewById(R.id.ghsj_yan);
        spUtils = new SPUtils();
        shouji = (String) spUtils.get(this, "shouji", "");
        myCountDownTimer = new MyCountDownTimer(60000, 1000);

        token = (String) spUtils.get(this, "logintoken", "");
    }

    @Override
    protected void initData() {
        biaoti.setText("更换手机");


        String phoneNumber = shouji.substring(0, 3) + "****" + shouji.substring(7, shouji.length());
        shoujiText.setText(phoneNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.yanzheng:
                if (EmptyUtils.isStrEmpty(shoujiText.getText().toString())&&!isChinaPhoneLegal(shoujiText.getText().toString())){
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();

                }else {
                    yanzhengma();
                }
                break;
            case R.id.my_ghsj_xiayibu:
               if (EmptyUtils.isStrEmpty(yanzhengma.getText().toString())||yanzhengma.getText().toString().length()<6){
                    Toast.makeText(this, "请填写6位验证码", Toast.LENGTH_SHORT).show();
                }else {
                    yanzheng();
                }
                break;
            case R.id.phone_ds:
                init();

                break;
        }
    }

    //弹窗 手机号丢失

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */


        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您还没有设置安全提示问题，是否去设置？");

        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        icon_que.setText("是");
        icon_close.setText("否");
        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
              //  intent.setClass(this, GRXXActivity.class);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);

                startActivity(intent);*/
                initRZ();
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //弹窗实名认证
    private void shimingRZ() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您好，您还没有实名认证，请先进行实名认证。");


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(GHSJActivity.this, SMRZActivity.class);

                bundle.putString("tab", "1");
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //安全问题
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAccount")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            SMRZEntity entity = gson.fromJson(result, SMRZEntity.class);
//                            HomeDialog dialog = new HomeDialog(this);
//                            dialog.show();
//                            name.setText(entity.getData().getPlayerRealName());
//                            sfzString = entity.getData().getPlayerID();
                            Intent intent = new Intent();

                            intent.setClass(GHSJActivity.this, MyAnquanOneActivity.class);
                            startActivity(intent);


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            showNormalDialog();

                        }
                    }
                });
    }


    //实名验证
    private void initRZ() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            SMRZEntity entity = gson.fromJson(result, SMRZEntity.class);
//                            HomeDialog dialog = new HomeDialog(this);
//                            dialog.show();
//                            name.setText(entity.getData().getPlayerRealName());
//                            sfzString = entity.getData().getPlayerID();
                            Intent intent = new Intent();

                            intent.setClass(GHSJActivity.this, GHSJTwoActivity.class);
                            startActivity(intent);


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            shimingRZ();
                        }
                    }
                });
    }

    private void yanzhengma() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                .addParams("mobile", shouji)
                .addParams("type", "updateTel")
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
                        Toast.makeText(GHSJActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        if (a) {
                            myCountDownTimer.start();
                        } else {

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
                .addParams("code", yanzhengma.getText().toString())
                .addParams("type", "updateTel")
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
                        Toast.makeText(GHSJActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        if (a) {
                            Intent intent = new Intent();
                            intent.setClass(GHSJActivity.this, GHSJThreeActivity.class);
                            startActivity(intent);
                        } else {

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
    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
