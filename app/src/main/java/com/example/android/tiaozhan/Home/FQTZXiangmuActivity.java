package com.example.android.tiaozhan.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.FQTZXiangmulistAdapter;
import com.example.android.tiaozhan.Entity.FQTZXiangmuEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 *发起挑战——运动项目
 */

public class FQTZXiangmuActivity extends BaseActivity {


    private TextView biaoti;
    private ImageView fanhui;
    private ListView listView;
    private  List<FQTZXiangmuEntity.DataBean> data;
    private FQTZXiangmulistAdapter adapter;
    private String yId,zId;
    private SPUtils spUtils;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fqtzxiangmu);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_fqtzxiangmu;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        spUtils = new SPUtils();
        Intent intent=getIntent();
        Bundle bundle = getIntent().getExtras();
        zId = bundle.getString("zId");
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FQTZXiangmuActivity.this,FaqiTiaozhanActivity.class);
                if (zId.equals("0")){
                    intent.putExtra("ydId",0+"");
                }else {
                    intent.putExtra("ydId",1+"");
                }

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        listView = findViewById(R.id.fqtz_xiangmu_list);
        data = new ArrayList<>();
        adapter = new FQTZXiangmulistAdapter(this,data);

    }

    @Override
    protected void initData() {
        biaoti.setText("运动项目");
        huoquyundong();
    }


    private void huoquyundong() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllSportLst")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "全部运动项目数据" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            FQTZXiangmuEntity entity = gson.fromJson(result, FQTZXiangmuEntity.class);
                            List<FQTZXiangmuEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);

                            listView.setAdapter(adapter);



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FQTZXiangmuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
//    //活动分类二级
//    private void huodongtwo(int id) {
//        OkHttpUtils
//                .get()
//                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getDatialSport")
//                .addParams("id",id+"")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "运动项目二级" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//
//                        if (a) {
//                            Gson gson = new Gson();
//                            YundongTwoEntity entity = gson.fromJson(result, YundongTwoEntity.class);
//                            List<YundongTwoEntity.DataBean> list;
//                            list = entity.getData();
//                            data2.addAll(list);
//
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(FQTZXiangmuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }

}
