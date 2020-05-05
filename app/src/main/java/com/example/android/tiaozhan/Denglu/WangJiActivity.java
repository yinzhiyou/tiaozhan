package com.example.android.tiaozhan.Denglu;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.ForGetPwdEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;

/**
 *忘记密码
 */
public class WangJiActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui,main_denglu_yan;
    private TextView textViewTAB,yan;
    private MyCountDownTimer myCountDownTimer;
    private EditText shouji, yanzheng, mima1;
    private RelativeLayout zhuce;
    private boolean isSee = false;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wang_ji);
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_wang_ji;
    }

    @Override
    protected void initUIAndListener() {
        textViewTAB = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        main_denglu_yan=findViewById(R.id.main_denglu_yan);
        main_denglu_yan.setOnClickListener(this);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        yan = findViewById(R.id.wangji_yan);
        yan.setOnClickListener(this);
//        yan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myCountDownTimer.start();
//            }
//        });

        shouji = findViewById(R.id.wangji_shouji);
        mima1 = findViewById(R.id.wangji_mima);

        yanzheng = findViewById(R.id.wangji_yanzheng);

        zhuce = findViewById(R.id.wangji_queding);
        zhuce.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        main_denglu_yan.setImageResource(R.mipmap.yan_close);

        textViewTAB.setText("忘记密码");
        myCountDownTimer = new MyCountDownTimer(60000, 1000);
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        String m1 = mima1.getText().toString();

        /*if ( TextUtils.isEmpty(shouji.getText())||TextUtils.isEmpty(mima1.getText())
                ||TextUtils.isEmpty(yanzheng.getText())){
            Toast.makeText(this, "请把内容填写完整", Toast.LENGTH_SHORT).show();
        }*//*else if(mima1.getText().length()<6) {

            Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
        }*//*else{*/
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/forgetpwd")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("password", mima1.getText().toString())

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
                            Gson gson = new Gson();
                            ForGetPwdEntity entity = gson.fromJson(result, ForGetPwdEntity.class);
                            if (entity.getCode()==2000){
                                ToastUitl.longs(entity.getMsg());
                                Intent intent=new Intent();
                                intent.setClass(WangJiActivity.this,DengluActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                ToastUitl.longs(entity.getMsg());
                            }
//
//                            if (a) {

//                                Gson gson = new Gson();
//                                ZhuceEntity entity = gson.fromJson(result, ZhuceEntity.class);
//                                Toast.makeText(ZhuCeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                                spUtils.put(ZhuCeActivity.this,"logintoken",entity.getData().getToken());
//                                spUtils.put(ZhuCeActivity.this,"uuid",entity.getData().getUuid());
//                                String  b= entity.getData().getUuid().replace("-","");
//                                LogU.Ld("1608", "UUID转换" + b);
//                                JPushInterface.setAlias(ZhuCeActivity.this,b,null);
//                                showNormalDialog();
//                            } else {
//                                Gson gson = new Gson();
//                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                                Toast.makeText(ZhuCeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//
//                            }
                        }
                    });
  //      }
    }

    private void yanzhengma() {
        if(!isChinaPhoneLegal(shouji.getText().toString())||TextUtils.isEmpty(shouji.getText())){
            Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        }else {
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/toSendCode")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("type", "forget")
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
                            Toast.makeText(WangJiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if(a){
                                myCountDownTimer.start();
                            }else {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wangji_yan:
                yanzhengma();
            break;

            case R.id.wangji_queding:
                if(!isChinaPhoneLegal(shouji.getText().toString())||TextUtils.isEmpty(shouji.getText())){
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(yanzheng.getText().toString())||yanzheng.getText().toString().length()<6) {
                    Toast.makeText(this, "请填写6位验证码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mima1.getText().toString())||mima1.getText().toString().length()<6) {
                    Toast.makeText(this, "请填写6位以上的密码", Toast.LENGTH_SHORT).show();
                }else {
                    initDownload();
                }
                break;
            case R.id.main_denglu_yan:
                if (isSee) {
                    mima1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_open);
                    isSee = false;
                } else {
                    mima1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_close);
                    isSee = true;
                }
                break;

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
