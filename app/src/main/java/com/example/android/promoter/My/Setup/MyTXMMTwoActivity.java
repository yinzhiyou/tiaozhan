package com.example.android.promoter.My.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.TXMMEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 设置提现密码2
 */
public class MyTXMMTwoActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti;
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
                init();
                break;

        }
    }
}
