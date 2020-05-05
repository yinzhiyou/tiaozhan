package com.example.android.tiaozhan.My;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.JBMXJishuListAdapter;
import com.example.android.tiaozhan.Adapter.PaihangAdapter;
import com.example.android.tiaozhan.Adapter.PaihangListAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PaihangEntity;
import com.example.android.tiaozhan.Entity.TouxiangEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.Home.HomeGRTXActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.HorizontalListView;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的排行
 */
public class MyPaihang extends AppCompatActivity implements View.OnClickListener {
    private HorizontalListView xiangmuList,listView;
    private   List<PaihangEntity.DataBean.OtherRankInfoBean> data;
    private  List<YundongEntity.DataBean> data2;
    private JBMXJishuListAdapter adapter2;
    private String diqu[] = {"好友排行","区排行","市排行","省排行","全国排行"};

    private List<String> list;
    private PaihangAdapter adapter;

    private String token,touxiang,type = "area",uuid1,uuid2,uuid3;
    private SPUtils spUtils;
    private int sportType=1;
    private MyListView MylistView;
    private PaihangListAdapter adapter3;
    private ImageView fanhui;
    private LinearLayout paihang1,paihang2,paihang3;
    private ImageView tx1,tx2,tx3;
    private TextView name1,name2,name3,lv1,lv2,lv3,qiu1,qiu2,qiu3,top,paihang_my_dengji,paihang_my_jinbi,paihang_my_name,paihang_my_qiu,paihang_my_text;
    private ImageView paihang_my_touxiang;
    private PaihangEntity.DataBean.UserRankInfoBean userRankInfo;
    private RelativeLayout gr_paihang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_paihang);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.hongse),0);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        paihang1 = findViewById(R.id.my_paihang_1);
        paihang2 = findViewById(R.id.my_paihang_2);
        paihang3 = findViewById(R.id.my_paihang_3);

        xiangmuList = findViewById(R.id.paihang_xiangmu);
        listView = findViewById(R.id.paihang_diqu);
        MylistView = findViewById(R.id.paihang_list);

        tx1 = findViewById(R.id.my_paihang_tx1);
        tx1.setOnClickListener(this);
        tx2 = findViewById(R.id.my_paihang_tx2);
        tx2.setOnClickListener(this);
        tx3 = findViewById(R.id.my_paihang_tx3);
        tx3.setOnClickListener(this);
        name1 = findViewById(R.id.my_paihang_name1);
        name2 = findViewById(R.id.my_paihang_name2);
        name3 = findViewById(R.id.my_paihang_name3);
        lv1 = findViewById(R.id.my_paihang_dengji1);
        lv2 = findViewById(R.id.my_paihang_dengji2);
        lv3 = findViewById(R.id.my_paihang_dengji3);
        qiu1 =findViewById(R.id.my_paihang_qiu1);
        qiu2 =findViewById(R.id.my_paihang_qiu2);
        qiu3 =findViewById(R.id.my_paihang_qiu3);

        top = findViewById(R.id.paihang_top);

        paihang_my_touxiang = findViewById(R.id.paihang_my_touxiang);
        paihang_my_dengji=findViewById(R.id.paihang_my_dengji);
        paihang_my_jinbi=findViewById(R.id.paihang_my_jinbi);
        paihang_my_name=findViewById(R.id.paihang_my_name);
        paihang_my_qiu=findViewById(R.id.paihang_my_qiu);
        paihang_my_text= findViewById(R.id.paihang_my_text);
        gr_paihang = findViewById(R.id.gr_paihang);

        fanhui = findViewById(R.id.my_hd_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        data = new ArrayList<>();
        data2 = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0;i<diqu.length;i++){
            list.add(diqu[i]);
        }
        adapter = new PaihangAdapter(this,list);
        adapter3 = new PaihangListAdapter(this,data,sportType);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();

                if (list.get(position).equals("好友排行")){
                    type = "myFriends";
                    top.setText("好友"+"排行榜TOP10");
                    paihang(sportType,type);

//                    top.setText("好友排行榜TOP10");
                }else  if (list.get(position).equals("全国排行")){
                    type = "country";
                    diqu(type);
//                    paihang(sportType,type);
//                    top.setText("全国排行榜TOP10");
                }else if (list.get(position).equals("省排行")){

                    type = "province";
                    diqu(type);

//                    paihang(sportType,type);
//                    top.setText("省排行榜TOP10");
                }else if (list.get(position).equals("市排行")){
                    type = "city";
                    diqu(type);

//                    paihang(sportType,type);
//                    top.setText("市排行榜TOP10");
                }else if (list.get(position).equals("区排行")){
                    type = "area";
                    diqu(type);

//                    paihang(sportType,type);
//                    top.setText("区排行榜TOP10");
                }

            }
        });
        diqu(type);
        huoquyundong();
    }

    //获取地区
    private void diqu(final String type) {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCity")
                .addHeader("token",token)
                .addParams("type",type)
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
                            top.setText(entity.getData()+"排行榜TOP10");

                            paihang(sportType,type);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyPaihang.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //获取所有运动
    private void huoquyundong() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllSports")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "运动项目" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            YundongEntity entity = gson.fromJson(result, YundongEntity.class);
                            List<YundongEntity.DataBean> list;
                            list = entity.getData();
                            data2.clear();
                            data2.addAll(list);
                            adapter2 = new JBMXJishuListAdapter(MyPaihang.this, data2,"2");
                            xiangmuList.setAdapter(adapter2);

                            xiangmuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
                                    adapter2.setSelectItem(position);
                                    adapter2.notifyDataSetChanged();
                                    paihang(data2.get(position).getId(),type);
                                    sportType = data2.get(position).getId();
                                    qiu1.setText(data2.get(position).getName());
                                    qiu2.setText(data2.get(position).getName());
                                    qiu3.setText(data2.get(position).getName());
                                }
                            });
                            paihang(sportType,type);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyPaihang.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //获取排行
    private void paihang(final int sportType, String type) {
        LogU.Ld("1608", "技术排行yin" + token+"sportType---"+sportType+"项目"+"类型"+type);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getRanking")
                .addHeader("token",token)
                .addParams("sportid",sportType + "")
                .addParams("type",type)
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
                            PaihangEntity entity = gson.fromJson(result, PaihangEntity.class);
                            List<PaihangEntity.DataBean.OtherRankInfoBean> list;
                            list = entity.getData().getOtherRankInfo();
                            SPUtils spUtils=new SPUtils();
                            data.clear();
                            data.addAll(list);
                            MyPaihang.this.userRankInfo = entity.getData().getUserRankInfo();
                            adapter3 = new PaihangListAdapter(MyPaihang.this,data,sportType);
                            MylistView.setAdapter(adapter3);
                            if (entity.getData().getUserRankInfo().getRank()>11){
                                gr_paihang.setVisibility(View.VISIBLE);
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getUserRankInfo().getImgURL()).into(paihang_my_touxiang);
                                paihang_my_text.setText(entity.getData().getUserRankInfo().getRank()+"");
                                paihang_my_name.setText(entity.getData().getUserRankInfo().getNickname());
                                paihang_my_jinbi.setText(entity.getData().getUserRankInfo().getTotal());
                                paihang_my_dengji.setText(entity.getData().getUserRankInfo().getLevel());

                                switch (sportType){
                                    case 1:
                                        paihang_my_qiu.setText("羽毛球");
                                        break;
                                    case 2:
                                        paihang_my_qiu.setText("乒乓球");
                                        break;
                                    case 3:
                                        paihang_my_qiu.setText("台球");
                                        break;
                                    case 4:
                                        paihang_my_qiu.setText("篮球");
                                        break;
                                    case 5:
                                        paihang_my_qiu.setText("足球");
                                        break;
                                    case 6:
                                        paihang_my_qiu.setText("排球");
                                        break;
                                    case 7:
                                        paihang_my_qiu.setText("网球");
                                        break;
                                    case 8:
                                        paihang_my_qiu.setText("高尔夫");
                                        break;
                                }
                                LogU.Le("1068","我第"+entity.getData().getUserRankInfo().getRank());
                            }else {
                                gr_paihang.setVisibility(View.GONE);
                            }

                            MylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(MyPaihang.this, HomeGRTXActivity.class);
                                    bundle.putString("uid",data.get(position).getPlayerUUID());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });




                            if (entity.getData().getTopThree().size()==1){
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.INVISIBLE);
                                paihang3.setVisibility(View.INVISIBLE);
                            }else   if (    entity.getData().getTopThree().size()==2){
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                name2.setText(entity.getData().getTopThree().get(1).getNickname());
                                lv2.setText(entity.getData().getTopThree().get(1).getLevel());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                uuid2 = entity.getData().getTopThree().get(1).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.VISIBLE);
                                paihang3.setVisibility(View.INVISIBLE);
                            }else  if (    entity.getData().getTopThree().size()==3){
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
                                Glide.with(MyPaihang.this).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(2).getImgURL()).into(tx3);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                name2.setText(entity.getData().getTopThree().get(1).getNickname());
                                lv2.setText(entity.getData().getTopThree().get(1).getLevel());
                                name3.setText(entity.getData().getTopThree().get(2).getNickname());
                                lv3.setText(entity.getData().getTopThree().get(2).getLevel());
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
                            Toast.makeText(MyPaihang.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.my_paihang_tx1:

                intent.setClass(MyPaihang.this, HomeGRTXActivity.class);
                bundle.putString("uid",uuid1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_paihang_tx2:
                intent.setClass(MyPaihang.this, HomeGRTXActivity.class);
                bundle.putString("uid",uuid2);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.my_paihang_tx3:
                intent.setClass(MyPaihang.this, HomeGRTXActivity.class);
                bundle.putString("uid",uuid3);
                intent.putExtras(bundle);
                startActivity(intent);

                break;

        }
    }
}
