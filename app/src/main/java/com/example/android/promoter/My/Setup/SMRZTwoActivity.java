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
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
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
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名认证" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            final JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(SMRZTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            if (tag.equals("3")){
                                intent.setClass(SMRZTwoActivity.this,MyTXMMActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                            }else{
                                intent.setClass(SMRZTwoActivity.this,AnquanActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(SMRZTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
