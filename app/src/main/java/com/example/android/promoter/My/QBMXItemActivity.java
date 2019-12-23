package com.example.android.promoter.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyQBMXEntity;
import com.example.android.promoter.Entity.QBMXItemEntity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 钱包明细详情
 */
public class QBMXItemActivity extends BaseActivity {

    private TextView biaoti,chuzhang,jine,leixing,time,shengyu,beizhu,bianhao;
    private ImageView fanhui;
    private String token,touxiang,nickname,uid,uuid;
    private SPUtils spUtils;
    private LinearLayout hdbh;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qbmxitem);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_qbmxitem;
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
        chuzhang = findViewById(R.id.qbmx_list_item_chuzhang);
        jine = findViewById(R.id.qbmx_list_item_jine);
        leixing = findViewById(R.id.qbmx_list_item_leixing);
        time = findViewById(R.id.qbmx_list_item_time);
        shengyu = findViewById(R.id.qbmx_list_item_shengyu);
        beizhu = findViewById(R.id.qbmx_list_item_beizhu);
        bianhao = findViewById(R.id.qbmx_list_item_bianhao);
        hdbh = findViewById(R.id.qbmx_list_item_hdbh);
        hdbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(QBMXItemActivity.this, HomeHDXQActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
    }

    @Override
    protected void initData() {
        biaoti.setText("钱包明细");
        initDownload();
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "钱包明细详情" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMoneyInfo")
                .addHeader("token",token)
                .addParams("uuid",uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "钱包明细详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            QBMXItemEntity entity = gson.fromJson(result, QBMXItemEntity.class);
                            jine.setText(entity.getData().getMoney()+"");
                            time.setText(entity.getData().getRecordDate());
                            beizhu.setText(entity.getData().getRecordReason()+"");
                            shengyu.setText(entity.getData().getTotal()+"");
                            bianhao.setText(entity.getData().getOrderId());
                            uuid = entity.getData().getUuid();
                            if (entity.getData().getInOrOut()==1){
                                leixing.setText("收入");
                                chuzhang.setText("入账金额");
                                jine.setTextColor(getResources().getColor(R.color.hongse));
                            }else {
                                leixing.setText("支出");
                                chuzhang.setText("出账金额");
                                jine.setTextColor(getResources().getColor(R.color.huise));
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(QBMXItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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