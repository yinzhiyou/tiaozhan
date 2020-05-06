package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.SMRZEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 实名认证2
 */
public class SMRZTwoActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private String token,tag;
    private SPUtils spUtils;
    private EditText name,sfz;
    private RelativeLayout queren;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_smrztwo);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_smrztwo;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }
        });
        name = findViewById(R.id.smrz_two_name);
        sfz = findViewById(R.id.smrz_two_sfz);
        queren = findViewById(R.id.smrz_two_queren);
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    initjiaru();

            }
        });
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tag =  bundle.getString("tag");

    }

    @Override
    protected void initData() {

        biaoti.setText("实名认证");

    }

    //实名认证
    private void initjiaru() {
        LogU.Ld("1608", "实名认证" + name.getText().toString()+sfz.getText().toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/realAhtour")
                .addHeader("token", token)
                .addParams("realName", name.getText().toString())
                .addParams("idCardNum", sfz.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608","失败"+e.getMessage());
                        Toast.makeText(SMRZTwoActivity.this, "网络繁忙", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名认证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            final JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(SMRZTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                         //   tag.equals("3") 原先的
                            if (!EmptyUtils.isStrEmpty(tag)){
                                if (tag.equals("1")){
                                    intent.setClass(SMRZTwoActivity.this,MyTXMMTwoActivity.class);
                                    Bundle bundle = new Bundle();//传值
                                    bundle.putString("tag", "3");
                                    intent.putExtras(bundle);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                   // finish();
                                }else{
                                    intent.setClass(SMRZTwoActivity.this,AnquanActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }else {
                                intent.setClass(SMRZTwoActivity.this,AnquanActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }


                            LogU.Ld("1608","验证"+tag+"==="+entity.getMsg());
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(SMRZTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            /*if (entity.getCode()==4004){
                                intent.setClass(SMRZTwoActivity.this,AnquanActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }*/
                            LogU.Ld("1608","验====证"+tag+"===="+entity.getMsg());
                        }
                    }
                });
    }




}
