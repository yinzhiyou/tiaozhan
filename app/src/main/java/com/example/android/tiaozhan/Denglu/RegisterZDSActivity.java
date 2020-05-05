package com.example.android.tiaozhan.Denglu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.DengluEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.ZhuceEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
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
public class RegisterZDSActivity extends BaseActivity implements View.OnClickListener {
    private TextView textTab, yan, tiaokuan;
    private ImageView fanhui, yuedu;
    private MyCountDownTimer myCountDownTimer;
    private EditText shouji, yanzheng, mima1, mima2;
    private RelativeLayout zhuce;
    private boolean tab = true;
    private SPUtils spUtils;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zhu_ce);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_register_zds;
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

//        yan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myCountDownTimer.start();
//            }
//        });

        shouji = findViewById(R.id.zhuce_shouji);
        mima1 = findViewById(R.id.zhuce_mima);
        mima2 = findViewById(R.id.zhuce_mima2);
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
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // StatusBarUtil.setStatusBarColor(this, R.color.white);
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white));
            LogU.Le("sou","1111");
        }else {
            //  StatusBarUtil.setTransparent(this);
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white),100);
            LogU.Le("sou","000");
        }

        myCountDownTimer = new MyCountDownTimer(60000, 1000);


    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        String m1 = mima1.getText().toString();
        String m2 = mima2.getText().toString();
        if ( TextUtils.isEmpty(shouji.getText())||TextUtils.isEmpty(mima1.getText())||TextUtils.isEmpty(mima2.getText())
                ||TextUtils.isEmpty(yanzheng.getText())){
            Toast.makeText(this, "请把内容填写完整", Toast.LENGTH_SHORT).show();
        }else if (!m1.equals(m2)){
            Toast.makeText(this, "两次密码请输入一致", Toast.LENGTH_SHORT).show();
        }else if(mima1.getText().length()<6) {

            Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
        }else{
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/register")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("password", mima1.getText().toString())
                    .addParams("password_confirmation", mima2.getText().toString())
                    .addParams("code", yanzheng.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();
                            LogU.Ld("1608", "注册信息" + result);
                            Boolean a = result.indexOf("2000") != -1;

                            if (a) {
                                Gson gson = new Gson();
                                ZhuceEntity entity = gson.fromJson(result, ZhuceEntity.class);
                                Toast.makeText(RegisterZDSActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                spUtils.put(RegisterZDSActivity.this,"logintoken",entity.getData().getToken());
                                spUtils.put(RegisterZDSActivity.this,"uuid",entity.getData().getUuid());
                                spUtils.put(RegisterZDSActivity.this, "shouji",shouji.getText().toString()+"");
                                String  b= entity.getData().getUuid().replace("-","");
                                LogU.Ld("1608", "UUID转换" + b);
                                JPushInterface.setAlias(RegisterZDSActivity.this,b,null);
                                showNormalDialog();
                            } else {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(RegisterZDSActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.zhuce_zhuce:
                if (!tab) {
                    initDownload();
                } else {
                    Toast.makeText(this, "请阅读条款", Toast.LENGTH_SHORT).show();
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
                if (tab) {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.hongdian));
                } else {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.huidian));
                }
                tab = !tab;
                break;
        }
    }

    private void Denglu() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/login")
                .addParams("mobile", shouji.getText().toString())
                .addParams("password", mima1.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "登陆信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            DengluEntity entity = gson.fromJson(result, DengluEntity.class);
                            Intent intent = new Intent();
                            intent.setClass(RegisterZDSActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(RegisterZDSActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void yanzhengma() {
        if(!isChinaPhoneLegal(shouji.getText().toString())){
            Toast.makeText(this, "手机号码有误", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(shouji.getText())){
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(RegisterZDSActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if(a){
                                myCountDownTimer.start();
                            }else {

                            }

                        }
                    });
        }

    }
    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(RegisterZDSActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("\"为了更方便发布和报名活动，\n" +
                "请完善个人信息\"");
        normalDialog.setPositiveButton("先看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        intent.setClass(RegisterZDSActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("去完善",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        intent.setClass(RegisterZDSActivity.this, GRXXActivity.class);
                        bundle.putString("tab","1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
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
