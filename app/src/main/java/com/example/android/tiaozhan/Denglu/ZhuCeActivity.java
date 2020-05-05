package com.example.android.tiaozhan.Denglu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.example.android.tiaozhan.Entity.ZhuceEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.RegexUtils;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;

/**
 *注册
 */
public class ZhuCeActivity extends BaseActivity implements View.OnClickListener {
    private TextView ds_xz;
    private Dialog dialog;
    private TextView textTab, yan, tiaokuan;
    private ImageView fanhui, yuedu,main_denglu_yan;
    private MyCountDownTimer myCountDownTimer;
    private EditText shouji, yanzheng, mima1;
    private RelativeLayout zhuce;
    private boolean tab = true;
    private SPUtils spUtils;
    private boolean isSee = false;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zhu_ce);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    protected void initUIAndListener() {
        textTab = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yan = findViewById(R.id.zhuce_yan);
        yan.setOnClickListener(this);

        main_denglu_yan=findViewById(R.id.main_denglu_yan);
        main_denglu_yan.setOnClickListener(this);
//        yan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myCountDownTimer.start();
//            }
//        });

        shouji = findViewById(R.id.zhuce_shouji);
        mima1 = findViewById(R.id.zhuce_mima);

        yanzheng = findViewById(R.id.zhuce_yanzheng);

        zhuce = findViewById(R.id.zhuce_zhuce);
        zhuce.setOnClickListener(this);
        tiaokuan = findViewById(R.id.zhuce_tiaokuan);
        tiaokuan.setOnClickListener(this);
        yuedu = findViewById(R.id.zhuce_yuedu);
        yuedu.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        textTab.setText("注册");
        main_denglu_yan.setImageResource(R.mipmap.yan_close);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
           // StatusBarUtil.setStatusBarColor(this, R.color.white);

            LogU.Le("sou","1111");
        }else {
          //  StatusBarUtil.setTransparent(this);
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
            LogU.Le("sou","000");
        }

        myCountDownTimer = new MyCountDownTimer(60000, 1000);


    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
            String m1 = mima1.getText().toString();


            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/register")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("password", mima1.getText().toString())

                    .addParams("code", yanzheng.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogU.Ld("1608", "注册信息" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();
                            LogU.Ld("1608", "注册信息" + result);
                            Boolean a = result.indexOf("2000") != -1;

                            if (a) {
                                Gson gson = new Gson();
                                ZhuceEntity entity = gson.fromJson(result, ZhuceEntity.class);
                                Toast.makeText(ZhuCeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                spUtils.put(ZhuCeActivity.this,"logintoken",entity.getData().getToken());
                                spUtils.put(ZhuCeActivity.this,"uuid",entity.getData().getUuid());
                                spUtils.put(ZhuCeActivity.this, "shouji",shouji.getText().toString()+"");
                                String  b= entity.getData().getUuid().replace("-","");
                                LogU.Ld("1608", "UUID转换" + b);
                                JPushInterface.setAlias(ZhuCeActivity.this,b,null);
                              /*  Intent intent=new Intent();
                                intent.setClass(ZhuCeActivity.this, PromoterONEActivity.class);
                                startActivity(intent);*/
                                showNormalDialog();
                            } else {
                                Gson gson = new Gson();
                                LogU.Ld("1608", "UUID转换失败" );
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(ZhuCeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.zhuce_zhuce:
                if (TextUtils.isEmpty(shouji.getText().toString())||!RegexUtils.isMobile(shouji.getText().toString())) {
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(yanzheng.getText().toString())||yanzheng.getText().toString().length()<6) {
                    Toast.makeText(this, "请填写6位验证码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mima1.getText().toString())||mima1.getText().toString().length()<6) {
                    Toast.makeText(this, "请填写6位以上的密码", Toast.LENGTH_SHORT).show();
                }else if (tab) {
                    Toast.makeText(this, "请阅读并勾选“用户协议”", Toast.LENGTH_SHORT).show();

                } else {
                    initDownload();
                }
                break;
            case R.id.zhuce_yan:

                yanzhengma();
                break;
            case R.id.zhuce_tiaokuan:

                intent.setClass(this, TiaoKuanActivity.class);
                startActivity(intent);
                break;
            case R.id.zhuce_yuedu:
                LogU.Ld("1608","点我"+tab);
                if (tab) {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                } else {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                }
                tab = !tab;
                break;

            case R.id.main_denglu_yan:
                if (isSee) {
                    mima1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_open);
                    mima1.setSelection(mima1.getText().length());
                    isSee = false;
                } else {
                    mima1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_close);
                    mima1.setSelection(mima1.getText().length());
                    isSee = true;
                }
                break;
        }
    }



    private void yanzhengma() {
        if(!isChinaPhoneLegal(shouji.getText().toString())&&TextUtils.isEmpty(shouji.getText())){
            Toast.makeText(this, "“请填写正确的手机号", Toast.LENGTH_SHORT).show();
        }else {
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("type", "register")
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
                            Toast.makeText(ZhuCeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if(a){
                                myCountDownTimer.start();
                            }else {

                            }

                        }
                    });
        }

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


            ds_xz.setText("恭喜您注册成功，为了更方便的参加活动，请您完善您的个人信息！");

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
                intent.setClass(ZhuCeActivity.this, GRXXActivity.class);
                bundle.putString("tab","1");
                intent.putExtras(bundle);

                startActivity(intent);

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ZhuCeActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
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
