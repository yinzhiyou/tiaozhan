package com.example.android.tiaozhan.My;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.YHKTXEntity;
import com.example.android.tiaozhan.Promoter.PromoterMyMoneyActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 申请提现成功
 */
public class TXCGActivity extends BaseActivity {

    private TextView biaoti,riqi,yhk,jine,time;
    private ImageView fanhui;
    private RelativeLayout chenggong;
    private String token,uuid,tab,zId;
    private SPUtils spUtils;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_txcg);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_txcg;
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
        chenggong = findViewById(R.id.txcg_wancheng);
        chenggong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (zId.equals("1")){
                    intent.setClass(TXCGActivity.this,MyQianbaoActivity.class);
                }else if (zId.equals("2")){
                    intent.setClass(TXCGActivity.this,PromoterMyMoneyActivity.class);
                }

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        riqi = findViewById(R.id.txcg_riqi);
        yhk = findViewById(R.id.txcg_yh);
        jine = findViewById(R.id.txcg_jine);
        time = findViewById(R.id.txcg_time);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
        tab =  bundle.getString("tab");
        zId =  bundle.getString("zId");
        LogU.Ld("1608","提现"+zId);
    }

    @Override
    protected void initData() {
        biaoti.setText("申请成功");
        bangdingwx();

    }

//    putSuccessInfo

    //提现成功
    private void bangdingwx() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "提现成功" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/putSuccessInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("type",tab)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提现成功" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            YHKTXEntity entity = gson.fromJson(result, YHKTXEntity.class);
                            riqi.setText(entity.getData().getNotice());
                            yhk.setText(entity.getData().getBankName()+entity.getData().getBankCardNum());
                            jine.setText(entity.getData().getAmount());
                            time.setText(entity.getData().getRequestDate());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialog();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
