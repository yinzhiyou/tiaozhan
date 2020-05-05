package com.example.android.tiaozhan.Denglu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.android.tiaozhan.Entity.BangDingEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.TouxiangEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;


/**
 *绑定手机号
 */

public class BangDingActivity extends BaseActivity implements View.OnClickListener {
    private Dialog dialog;
    private TextView ds_xz;
    private TextView biaoti,yan;
    private ImageView fanhui;
    private String uid;
    private EditText shouji,yanzheng;
    private RelativeLayout bangding,yanzhengRL;
    private  MyCountDownTimer myCountDownTimer;
    private SPUtils spUtils;
    private BangDingEntity entity;
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
                            entity = gson.fromJson(result, BangDingEntity.class);
                            touxiangupost();
                            spUtils.put(BangDingActivity.this, "logintoken", entity.getData().getToken());
                            spUtils.put(BangDingActivity.this, "uuid", entity.getData().getUuid());
                            spUtils.put(BangDingActivity.this, "touxiang", entity.getData().getImgURL());
                            spUtils.put(BangDingActivity.this, "nickname", entity.getData().getNickname());
                            spUtils.put(BangDingActivity.this, "shouji",shouji.getText().toString());

                            if (!EmptyUtils.isStrEmpty(entity.getData().getContent())) {
                                fanHUI();
                            }else {

                                if (entity.getData().getPerfect().equals("2")) {
                                    showNormalDialog();
                                } else {
                                    Intent intent = new Intent();
                                    intent.setClass(BangDingActivity.this, MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            }
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

                            if (entity.getMsg().equals("手机号已被绑定，请更换其他手机号")){

                            }


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
    // 弹窗返回手机号码
    private void fanHUI() {


        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quding_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);
        ds_xz.setText(entity.getData().getContent());

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

                dialog.dismiss();
                showNormalDialog();
                //finish();
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


    //上传照片
    public void touxiangupost() {

        // ss = GetFile.getFile(bitmap, 1, 2 + "").toString();
        File img = new File(entity.getData().getImgURL());
       /* try {
            Log.d("1608", bitmap + "----------分1割----------"+img);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
      //  Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadHeaderImg")
                .addFile("img", "multipart/form-data.png", img)
                .addHeader("token", entity.getData().getToken())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", result);
                        Boolean a = result.indexOf("获取成功") != -1;
                        Gson gson = new Gson();
                        TouxiangEntity entity = gson.fromJson(result, TouxiangEntity.class);
//                        Glide.with(GRXXActivity.this).load("http://192.168.0.203/tzOne/public/"+entity.getData()).into(touxiang);
                        // spUtils.put(GRXXActivity.this, "touxiang", entity.getData());
                        // Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData()).into(touxiang);

                    }
                });

    }


    //弹窗取消报名 发布 预订
    private void showNormalDialog() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_zhuce_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("为了更方便的参加活动，请您完善您的个人信息！");

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

        icon_que.setText("去完善");
        icon_close.setText("先看看");

        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(BangDingActivity.this, GRXXActivity.class);
                bundle.putString("tab","1");


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);

                startActivity(intent);

                dialog.dismiss();
                finish();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BangDingActivity.this, MainActivity.class);


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });

    }

}
