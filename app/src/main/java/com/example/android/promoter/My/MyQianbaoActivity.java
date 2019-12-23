package com.example.android.promoter.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyQianbaoEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 我的钱包
 */

public class MyQianbaoActivity extends BaseActivity implements View.OnClickListener{


    private TextView biaoti,mingxi,jine;
    private ImageView fanhui;
    private RelativeLayout tixian;
    private String token,touxiang,nickname,uuid;
    private SPUtils spUtils;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_qianbao);
//    }


    @Override
    public int initContentView() {
        return R.layout.activity_my_qianbao;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        mingxi = findViewById(R.id.youshangjiao);
        mingxi.setOnClickListener(this);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        tixian = findViewById(R.id.my_qianbao_tixian);
        tixian.setOnClickListener(this);
        jine = findViewById(R.id.my_qianbao_jine);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
    }

    @Override
    protected void initData() {
        mingxi.setText("明细");
        mingxi.setVisibility(View.VISIBLE);
        biaoti.setText("我的钱包");
        initDownload();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_qianbao_tixian:
                intent.setClass(this,MyTIxianActivity.class);
                intent.putExtra("tgId","2");
                startActivity(intent);
                break;
            case R.id.youshangjiao:
                intent.setClass(this,QBMXActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "钱包" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserMoneyCount")
                .addHeader("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "钱包" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            MyQianbaoEntity entity = gson.fromJson(result, MyQianbaoEntity.class);
                            jine.setText("¥"+entity.getData().getTotal()+"");

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyQianbaoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initDownload();
    }
}
