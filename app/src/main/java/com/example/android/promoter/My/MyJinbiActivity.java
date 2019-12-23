package com.example.android.promoter.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.GRTXList1Adapter;
import com.example.android.promoter.Adapter.MyJinbiList2Adapter;
import com.example.android.promoter.Adapter.MyJinbiList3Adapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JBSMEntity;
import com.example.android.promoter.Entity.JSJBListEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Home.RenWuActivity;
import com.example.android.promoter.Home.ShopActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的金币
 */
public class MyJinbiActivity extends BaseActivity implements View.OnClickListener {
    private TextView biaoti, tyjb;
    private ImageView fanhui;
    private MyListView listView, listView2, listView3;
    private GRTXList1Adapter adapter;
    private MyJinbiList2Adapter adapter2;
    private MyJinbiList3Adapter adapter3;
    private LinearLayout linearMX;
    private String token;
    private SPUtils spUtils;
    private List<JSJBListEntity.DataBean> data;
    private List<JBSMEntity.DataBean> data2;
    private LinearLayout renwu, ty, tymx, phb, jsmx, jishufen_detail, jishufen_conversion;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_jinbi);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_jinbi;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        listView = findViewById(R.id.my_jinbi_list);
        listView3 = findViewById(R.id.my_jinbi_list3);


        data = new ArrayList<>();
        adapter = new GRTXList1Adapter(this, data);

        listView2 = findViewById(R.id.my_jinbi_list2);
        data2 = new ArrayList<>();
        adapter2 = new MyJinbiList2Adapter(this, data2);
        adapter3 = new MyJinbiList3Adapter(this, data2);
        linearMX = findViewById(R.id.my_jinbi_mx);
        linearMX.setOnClickListener(this);
        tyjb = findViewById(R.id.my_jinbi_tongyong);
        renwu = findViewById(R.id.my_jinbi_renwu);
        renwu.setOnClickListener(this);
        ty = findViewById(R.id.my_jinbi_ty);
        ty.setOnClickListener(this);
        tymx = findViewById(R.id.my_jinbi_tymx);
        tymx.setOnClickListener(this);
        phb = findViewById(R.id.my_jinbi_phb);
        phb.setOnClickListener(this);
        jsmx = findViewById(R.id.my_jinbi_mx);
        jsmx.setOnClickListener(this);

        jishufen_conversion = findViewById(R.id.jishufen_conversion);
        jishufen_conversion.setOnClickListener(this);
        jishufen_detail = findViewById(R.id.jishufen_detail);
        jishufen_detail.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        biaoti.setText("技术分");

//        listView2.setAdapter(adapter2);

        spUtils = new SPUtils();
        token = (String) spUtils.get(MyJinbiActivity.this, "logintoken", "无");
        initDownload();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_jinbi_mx:
                intent.setClass(this, JBMXActivity.class);
                bundle.putString("tab", "2");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_jinbi_renwu:
                intent.setClass(this, RenWuActivity.class);
                startActivity(intent);
                break;
            case R.id.my_jinbi_ty:
                intent.setClass(this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.my_jinbi_tymx:
                intent.setClass(this, JBMXActivity.class);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_jinbi_phb:
                intent.setClass(this, MyPaihang.class);
                startActivity(intent);
                break;
            //技术分排行榜
            case R.id.jishufen_conversion:
                intent.setClass(this, MyPaihang.class);
                startActivity(intent);
                break;
            //技术分获得明细
            case R.id.jishufen_detail:
                intent.setClass(this, JBMXActivity.class);
                bundle.putString("tab", "2");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTechCoins")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "技术金币" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JSJBListEntity entity = gson.fromJson(result, JSJBListEntity.class);
                            List<JSJBListEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);
                            listView.setAdapter(adapter);
                            tyjb();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyJinbiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(MyJinbiActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void tyjb() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCommonCoins")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "通用金币" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
                            tyjb.setText(entity.getData().getCoins() + "个");
                            shuoming();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyJinbiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(MyJinbiActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    private void shuoming() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLevelWinCoins")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "金币说明" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JBSMEntity entity = gson.fromJson(result, JBSMEntity.class);
                            List<JBSMEntity.DataBean> list;
                            list = entity.getData();
                            data2.addAll(list);
                            listView2.setAdapter(adapter2);
                            listView3.setAdapter(adapter3);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyJinbiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(MyJinbiActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

}
