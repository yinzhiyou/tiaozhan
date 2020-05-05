package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.TXMMEntity;
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
 * 设置提现密码2
 */
public class MyTXMMTwoActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti,phone_dsh;
    private ImageView fanhui;
    private String token, touxiang, nickname, uuid;
    private SPUtils spUtils;
    private EditText editTextOne, editTextTwo;
    private RelativeLayout tijiao;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_txmmtwo);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_txmmtwo;
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
        editTextOne = findViewById(R.id.txmm_noe);
        editTextTwo = findViewById(R.id.txmm_two);
        tijiao = findViewById(R.id.my_txmm_tijiao);
        tijiao.setOnClickListener(this);
        phone_dsh = findViewById(R.id.phone_dsh);
        phone_dsh.setOnClickListener(this);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

    }

    @Override
    protected void initData() {
        biaoti.setText("设置提现密码");

    }

    //设置提现密码
    private void init() {

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/setPutMoneyPwd")
                .addHeader("token", token)
                .addParams("putMoneyPwd", editTextOne.getText().toString())
                .addParams("confirmPutMoneyPwd", editTextTwo.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608","======"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "验证码" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if(a){
                            Gson gson = new Gson();
                            TXMMEntity entity = gson.fromJson(result, TXMMEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            Intent intent = new Intent();
                            intent.setClass(MyTXMMTwoActivity.this,MyShezhiActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }else {
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                        }

                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_txmm_tijiao:


                if (editTextOne.getText().toString().equals(editTextTwo.getText().toString())) {
                    if (editTextOne.getText().toString().length()==6&&editTextTwo.getText().toString().length()==6){
                        init();
                    }else {
                        Toast.makeText(this,"请输入六位提现密码",Toast.LENGTH_LONG).show();
                    }

                    LogU.Ld("1608","两次输=====入密码不一致"+editTextOne.getText().toString()+"===="+editTextTwo.getText().toString());
                }else {

                    Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.phone_dsh:

                Intent intent = new Intent();

                intent.setClass(MyTXMMTwoActivity.this, MyAnquanOneActivity.class);
                startActivity(intent);
                break;
        }
    }
}
