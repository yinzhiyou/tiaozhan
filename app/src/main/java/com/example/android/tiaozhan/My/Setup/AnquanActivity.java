package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *安全设置
 */
public class AnquanActivity extends BaseActivity implements View.OnClickListener{

    private TextView biaoti;
    private ImageView fanhui;
    private LinearLayout xiugai,genghuan,shiming;
    private SPUtils spUtils;
    private String token;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_anquan);
//    }


    @Override
    public int initContentView() {
        return R.layout.activity_anquan;

    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        xiugai = findViewById(R.id.anquan_xiugai);
        xiugai.setOnClickListener(this);
        genghuan = findViewById(R.id.anquan_genghuan);
        genghuan.setOnClickListener(this);
        shiming = findViewById(R.id.anquan_shiming);
        shiming.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(AnquanActivity.this, "logintoken", "");

    }

    @Override
    protected void initData() {
        biaoti.setText("安全设置");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()){
            case R.id.fanhui:
            finish();
            break;
            case R.id.anquan_xiugai:
                intent.setClass(this,MyXGMMActivity.class);
                startActivity(intent);
                break;
            case R.id.anquan_genghuan:
                intent.setClass(this,GHSJActivity.class);
                startActivity(intent);
                break;
            case R.id.anquan_shiming:

                init();
                break;


        }

    }

    //实名验证
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token",token)
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
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        if (a) {
                          //  ToastUitl.longs("您已经实名认证过，不可重复认证");

                            intent.setClass(AnquanActivity.this,SMRZTwoActivity.class);
                            bundle.putString("tag","5");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {

                            intent.setClass(AnquanActivity.this,SMRZActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            bundle.putString("tab","1");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                });
    }
}
