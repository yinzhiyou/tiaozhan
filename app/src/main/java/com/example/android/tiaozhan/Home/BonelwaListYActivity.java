package com.example.android.tiaozhan.Home;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.tiaozhan.Adapter.BonelwaListQAdapter;
import com.example.android.tiaozhan.Adapter.BonelwaListSAdapter;
import com.example.android.tiaozhan.Adapter.BonelwaListYAdapter;
import com.example.android.tiaozhan.Adapter.BonelwaListPAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.HDXQEntity;
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

public class BonelwaListYActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView biaoti;
    private RecyclerView recycle;
    private SPUtils spUtils;
    private String token, uuid, tab;
    private List<HDXQEntity.DataBean.AwinBuserInfoBean> data1;
    private List<HDXQEntity.DataBean.AloseBuserInfoBean> data2;
    private List<HDXQEntity.DataBean.AdrawBuserInfoBean> data3;
    private List<HDXQEntity.DataBean.GetwaiverInfoBean> data4;


    @Override
    public int initContentView() {
        return R.layout.activity_bonelwa_listy;
    }

    @Override
    protected void initUIAndListener() {

        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        recycle = findViewById(R.id.bonelwa_listY);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tagYd");
        uuid = bundle.getString("uuid");

        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        data4 = new ArrayList<>();
    }

    @Override
    protected void initData() {
        if (tab.equals("1")) {
            biaoti.setText("A赢B");
        } else if (tab.equals("2")) {
            biaoti.setText("A输B");
        } else if (tab.equals("0")) {
            biaoti.setText("A平B");
        } else if (tab.equals("3")) {
            biaoti.setText("弃权");
        }

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.addItemDecoration(new DividerItemDecoration(BonelwaListYActivity.this, DividerItemDecoration.VERTICAL));
        loadDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
        }
    }


    //确认结束
    private void loadDate() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "确认结束---" + token + "---publishcId---" + uuid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "确认结束" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HDXQEntity entity = gson.fromJson(result, HDXQEntity.class);


                            if (tab.equals("1")) {
                                if (entity.getData().getAwinBcount() > 0) {
                                    List<HDXQEntity.DataBean.AwinBuserInfoBean> list1;
                                    list1 = entity.getData().getAwinBuserInfo();
                                    data1.clear();
                                    data1.addAll(list1);
                                    BonelwaListYAdapter adapter1 = new BonelwaListYAdapter(R.layout.bonelwa_list_item, list1);
                                    recycle.setAdapter(adapter1);
                                    adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(BonelwaListYActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data1.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });

                                }
                            } else if (tab.equals("2")) {
                                if (entity.getData().getAloseBcount() > 0) {
                                    List<HDXQEntity.DataBean.AloseBuserInfoBean> list2;
                                    list2 = entity.getData().getAloseBuserInfo();
                                    data2.clear();
                                    data2.addAll(list2);
                                    BonelwaListSAdapter adapter2 = new BonelwaListSAdapter(R.layout.bonelwa_list_item, list2);
                                    recycle.setAdapter(adapter2);

                                    adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(BonelwaListYActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data2.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            } else if (tab.equals("0")) {
                                if (entity.getData().getAdrawBcount() > 0) {
                                    List<HDXQEntity.DataBean.AdrawBuserInfoBean> list3;
                                    list3 = entity.getData().getAdrawBcountInfo();
                                    data3.clear();
                                    data3.addAll(list3);
                                    BonelwaListPAdapter adapter3 = new BonelwaListPAdapter(R.layout.bonelwa_list_item, list3);
                                    recycle.setAdapter(adapter3);

                                    adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(BonelwaListYActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data3.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });
                                }
                                ;
                            } else if (tab.equals("3")) {
                                if (entity.getData().getGetwaiver() > 0) {
                                    List<HDXQEntity.DataBean.GetwaiverInfoBean> list4;
                                    list4 = entity.getData().getGetwaiverInfo();
                                    data4.clear();
                                    data4.addAll(list4);
                                    BonelwaListQAdapter adapter4 = new BonelwaListQAdapter(R.layout.bonelwa_list_item, list4);
                                    recycle.setAdapter(adapter4);

                                    adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(BonelwaListYActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data4.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(BonelwaListYActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(BonelwaListYActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
}
