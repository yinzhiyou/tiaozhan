package com.example.android.promoter.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.DuiShouPaihangAdapter;
import com.example.android.promoter.Adapter.OpponentListAdapter;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.OpponentBiPaihangEntity;
import com.example.android.promoter.Entity.TouxiangEntity;
import com.example.android.promoter.Home.HomeGRTXActivity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.HorizontalListView;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class MyOpponentPaiHangActivity extends AppCompatActivity implements View.OnClickListener {
    private HorizontalListView  listView;
    private List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> data;


    private String diqu[] = {"好友排行","区排行","市排行","省排行","全国排行"};
    private List<String> list;
    private DuiShouPaihangAdapter adapter;

    private String token, touxiang, type = "myFriends", uuid1, uuid2, uuid3;
    private SPUtils spUtils;
    private int sportType = 1;
    private MyListView MylistView;
    private OpponentListAdapter adapter3;
    private ImageView fanhui;
    private LinearLayout paihang1, paihang2, paihang3;
    private ImageView tx1, tx2, tx3,paihang_my_touxiang;
    private TextView name1, name2, name3, qiu1, qiu2, qiu3, top,paihang_my_jinbi,paihang_my_name,paihang_my_text;
    private RelativeLayout gr_paihang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myopponent_paihang);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.hongse),0);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        paihang1 = findViewById(R.id.my_ds_paihang_1);
        paihang2 = findViewById(R.id.my_ds_paihang_2);
        paihang3 = findViewById(R.id.my_ds_paihang_3);


        listView = findViewById(R.id.ds_paihang_diqu);
        MylistView = findViewById(R.id.ds_paihang_list);

        tx1 = findViewById(R.id.my_ds_paihang_tx1);
        tx1.setOnClickListener(this);
        tx2 = findViewById(R.id.my_ds_paihang_tx2);
        tx2.setOnClickListener(this);
        tx3 = findViewById(R.id.my_ds_paihang_tx3);
        tx3.setOnClickListener(this);
        name1 = findViewById(R.id.my_ds_paihang_name1);
        name2 = findViewById(R.id.my_ds_paihang_name2);
        name3 = findViewById(R.id.my_ds_paihang_name3);

        qiu1 = findViewById(R.id.my_ds_paihang_qiu1);
        qiu2 = findViewById(R.id.my_ds_paihang_qiu2);
        qiu3 = findViewById(R.id.my_ds_paihang_qiu3);

        top = findViewById(R.id.ds_paihang_top);
        paihang_my_touxiang = findViewById(R.id.paihang_my_touxiang);

        paihang_my_jinbi=findViewById(R.id.paihang_my_jinbi);
        paihang_my_name=findViewById(R.id.paihang_my_name);

        paihang_my_text= findViewById(R.id.paihang_my_text);
        gr_paihang = findViewById(R.id.gr_paihang);

        fanhui = findViewById(R.id.my_ds_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        data = new ArrayList<>();

        list = new ArrayList<>();
        for (int i = 0; i < diqu.length; i++) {
            list.add(diqu[i]);
        }
        paihang(sportType,type);
        adapter = new DuiShouPaihangAdapter(MyOpponentPaiHangActivity.this, list);
        adapter3 = new OpponentListAdapter(this, data, sportType);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();

                if (list.get(position).equals("好友排行")) {
                    type = "myFriends";

                    paihang(sportType, type);
//                    top.setText("好友排行榜TOP10");
                } else if (list.get(position).equals("全国排行")) {
                    type = "country";
                    diqu(type);
//                    paihang(sportType,type);
//                    top.setText("全国排行榜TOP10");
                } else if (list.get(position).equals("省排行")) {
                    type = "province";
                    diqu(type);
//                    paihang(sportType,type);
//                    top.setText("省排行榜TOP10");
                } else if (list.get(position).equals("市排行")) {
                    type = "city";
                    diqu(type);
//                    paihang(sportType,type);
//                    top.setText("市排行榜TOP10");
                } else if (list.get(position).equals("区排行")) {
                    type = "area";
                    diqu(type);
//                    paihang(sportType,type);
//                    top.setText("区排行榜TOP10");
                }

            }
        });

    }

    //获取地区
    private void diqu(final String type) {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCity")
                .addHeader("token", token)
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取地区" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            TouxiangEntity entity = gson.fromJson(result, TouxiangEntity.class);
                            top.setText(entity.getData() + "排行榜TOP10");
                            paihang(sportType, type);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyOpponentPaiHangActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //获取排行
    private void paihang(final int sportType, String type) {
        LogU.Ld("1608", "技术排行yin" + token + "sportType---" + sportType + "项目" + "类型" + type);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getRivalCurrencyRanking")
                .addHeader("token", token)
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "技术排行" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            OpponentBiPaihangEntity entity = gson.fromJson(result, OpponentBiPaihangEntity.class);
                            List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> list;
                            list = entity.getData().getOtherRankInfo();
                            data.clear();
                            data.addAll(list);
                            adapter3 = new OpponentListAdapter(MyOpponentPaiHangActivity.this, data, sportType);
                            MylistView.setAdapter(adapter3);
                            if (entity.getData().getUserRankInfo().getRank()>11) {
                                gr_paihang.setVisibility(View.VISIBLE);
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getUserRankInfo().getImgURL()).into(paihang_my_touxiang);
                                paihang_my_text.setText(entity.getData().getUserRankInfo().getRank() + "");
                                paihang_my_name.setText(entity.getData().getUserRankInfo().getNickname());
                                paihang_my_jinbi.setText(entity.getData().getUserRankInfo().getTotal());


                            }else {
                                gr_paihang.setVisibility(View.GONE);
                            }

                            MylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(MyOpponentPaiHangActivity.this, HomeGRTXActivity.class);
                                    bundle.putString("uid", data.get(position).getPlayerUUID());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });


                            if (entity.getData().getTopThree().size() == 1) {
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1.setText(entity.getData().getTopThree().get(0).getTotle_temp());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.INVISIBLE);
                                paihang3.setVisibility(View.INVISIBLE);
                            } else if (entity.getData().getTopThree().size() == 2) {
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1.setText(entity.getData().getTopThree().get(0).getTotle_temp());
                                name2.setText(entity.getData().getTopThree().get(1).getNickname());
                                qiu2.setText(entity.getData().getTopThree().get(1).getTotle_temp());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                uuid2 = entity.getData().getTopThree().get(1).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.VISIBLE);
                                paihang3.setVisibility(View.INVISIBLE);
                            } else if (entity.getData().getTopThree().size() == 3) {
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
                                Glide.with(MyOpponentPaiHangActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(2).getImgURL()).into(tx3);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1.setText(entity.getData().getTopThree().get(0).getTotle_temp());

                             //   lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                name2.setText(entity.getData().getTopThree().get(1).getNickname());
                                qiu2.setText(entity.getData().getTopThree().get(1).getTotle_temp());
                               // lv2.setText(entity.getData().getTopThree().get(1).getLevel());
                                name3.setText(entity.getData().getTopThree().get(2).getNickname());
                                qiu3.setText(entity.getData().getTopThree().get(2).getTotle_temp());
                             //   lv3.setText(entity.getData().getTopThree().get(2).getLevel());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                uuid2 = entity.getData().getTopThree().get(1).getPlayerUUID();
                                uuid3 = entity.getData().getTopThree().get(2).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.VISIBLE);
                                paihang3.setVisibility(View.VISIBLE);
                            }


//                            MylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
////                                    adapter2.setSelectItem(position);
////                                    adapter2.notifyDataSetChanged();
////                                    initDownload("techCoins",data2.get(position).getName());
//                                }
//                            });

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyOpponentPaiHangActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.my_ds_paihang_tx1:

                intent.setClass(MyOpponentPaiHangActivity.this, HomeGRTXActivity.class);
                bundle.putString("uid", uuid1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_ds_paihang_tx2:
                intent.setClass(MyOpponentPaiHangActivity.this, HomeGRTXActivity.class);
                bundle.putString("uid", uuid2);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.my_ds_paihang_tx3:
                intent.setClass(MyOpponentPaiHangActivity.this, HomeGRTXActivity.class);
                bundle.putString("uid", uuid3);
                intent.putExtras(bundle);
                startActivity(intent);

                break;

        }
    }
}
