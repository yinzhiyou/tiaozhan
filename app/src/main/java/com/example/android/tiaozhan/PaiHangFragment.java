package com.example.android.tiaozhan;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.DuiShouPaihangAdapter;
import com.example.android.tiaozhan.Adapter.JBMXJishuListAdapter;
import com.example.android.tiaozhan.Adapter.OpponentListAdapter;
import com.example.android.tiaozhan.Adapter.PaihangAdapter;
import com.example.android.tiaozhan.Adapter.PaihangListAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.AllActiveCountEntity;
import com.example.android.tiaozhan.Entity.JCXIEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.OpponentBiPaihangEntity;
import com.example.android.tiaozhan.Entity.PaihangEntity;
import com.example.android.tiaozhan.Entity.TouxiangEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.Home.HomeGRTXActivity;
import com.example.android.tiaozhan.Home.XiaoxiActivity;
import com.example.android.tiaozhan.My.MyhuodongActivity;
import com.example.android.tiaozhan.Toos.BaseFragment;
import com.example.android.tiaozhan.Toos.HorizontalListView;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PaiHangFragment extends BaseFragment implements View.OnClickListener {
    private TextView wofabu, wobaoming;
    private View fb,bm;
    private HorizontalListView xiangmuList,listView;
    private   List<PaihangEntity.DataBean.OtherRankInfoBean> data;
    private  List<YundongEntity.DataBean> data2;
    private JBMXJishuListAdapter adapter2;
    private String diqu[] = {"好友排行","区排行","市排行","省排行","全国排行"};

    private List<String> list;
    private PaihangAdapter adapter;

    private String token,touxiang,typeDS = "area",type = "area",uuid1,uuid2,uuid3;
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


    private HorizontalListView  listView_ds;
    private List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> data_ds;


    private String diquds[] = {"好友排行","区排行","市排行","省排行","全国排行"};

    private List<String> list_ds;
    private DuiShouPaihangAdapter adapter_ds;

    private String uuid1_ds, uuid2_ds, uuid3_ds;

    private MyListView MylistView_ds;
    private OpponentListAdapter adapter3_ds;

    private LinearLayout paihang1_ds, paihang2_ds, paihang3_ds,ds_layout,js_layout;
    private ImageView tx1_ds, tx2_ds, tx3_ds,paihang_my_touxiang_ds,my_hd_home,xiaoxi,weidu;
    private TextView name1_ds, name2_ds, name3_ds, qiu1_ds, qiu2_ds, qiu3_ds, top_ds,paihang_my_jinbi_ds,paihang_my_name_ds,paihang_my_text_ds;
    private RelativeLayout gr_paihang_ds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.paihang_fragment, container, false);

        weidu = rootView.findViewById(R.id.my_xiaoxi_weidu);
        weidu.setVisibility(View.GONE);
        wofabu = rootView.findViewById(R.id.my_hd_wofabu);
        wofabu.setOnClickListener(this);
        wobaoming = rootView.findViewById(R.id.my_hd_wobaoming);
        wobaoming.setOnClickListener(this);
        fb=rootView.findViewById(R.id.my_fabu);
        bm=rootView.findViewById(R.id.my_baoming);
        ds_layout=rootView.findViewById(R.id.ds_layout);
        js_layout=rootView.findViewById(R.id.js_layout);
        my_hd_home=rootView.findViewById(R.id. my_hd_home);
        my_hd_home.setOnClickListener(this);
        xiaoxi=rootView.findViewById(R.id.xiaoxi);
        xiaoxi.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(getActivity(), "logintoken", "");

        paihang1 = rootView.findViewById(R.id.my_paihang_1);
        paihang2 = rootView.findViewById(R.id.my_paihang_2);
        paihang3 = rootView.findViewById(R.id.my_paihang_3);

        xiangmuList = rootView.findViewById(R.id.paihang_xiangmu);
        listView = rootView.findViewById(R.id.paihang_diqu);
        MylistView = rootView.findViewById(R.id.paihang_list);

        tx1 = rootView.findViewById(R.id.my_paihang_tx1);
        tx1.setOnClickListener(this);
        tx2 = rootView.findViewById(R.id.my_paihang_tx2);
        tx2.setOnClickListener(this);
        tx3 = rootView.findViewById(R.id.my_paihang_tx3);
        tx3.setOnClickListener(this);
        name1 = rootView.findViewById(R.id.my_paihang_name1);
        name2 = rootView.findViewById(R.id.my_paihang_name2);
        name3 = rootView.findViewById(R.id.my_paihang_name3);
        lv1 = rootView.findViewById(R.id.my_paihang_dengji1);
        lv2 = rootView.findViewById(R.id.my_paihang_dengji2);
        lv3 = rootView.findViewById(R.id.my_paihang_dengji3);
        qiu1 =rootView.findViewById(R.id.my_paihang_qiu1);
        qiu2 =rootView.findViewById(R.id.my_paihang_qiu2);
        qiu3 =rootView.findViewById(R.id.my_paihang_qiu3);

        top = rootView.findViewById(R.id.paihang_top);

        paihang_my_touxiang = rootView.findViewById(R.id.paihang_my_touxiang);
        paihang_my_dengji=rootView.findViewById(R.id.paihang_my_dengji);
        paihang_my_jinbi=rootView.findViewById(R.id.paihang_my_jinbi);
        paihang_my_name=rootView.findViewById(R.id.paihang_my_name);
        paihang_my_qiu=rootView.findViewById(R.id.paihang_my_qiu);
        paihang_my_text= rootView.findViewById(R.id.paihang_my_text);
        gr_paihang = rootView.findViewById(R.id.gr_paihang);



        data = new ArrayList<>();
        data2 = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0;i<diqu.length;i++){
            list.add(diqu[i]);
        }
        adapter = new PaihangAdapter(getActivity(),list);
        adapter3 = new PaihangListAdapter(getActivity(),data,sportType);
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

        paihang1_ds = rootView.findViewById(R.id.my_ds_paihang_1);
        paihang2_ds = rootView.findViewById(R.id.my_ds_paihang_2);
        paihang3_ds = rootView.findViewById(R.id.my_ds_paihang_3);


        listView_ds = rootView.findViewById(R.id.ds_paihang_diqu);
        MylistView_ds = rootView.findViewById(R.id.ds_paihang_list);

        tx1_ds = rootView.findViewById(R.id.my_ds_paihang_tx1);
        tx1_ds.setOnClickListener(this);
        tx2_ds = rootView.findViewById(R.id.my_ds_paihang_tx2);
        tx2_ds.setOnClickListener(this);
        tx3_ds = rootView.findViewById(R.id.my_ds_paihang_tx3);
        tx3_ds.setOnClickListener(this);
        name1_ds = rootView.findViewById(R.id.my_ds_paihang_name1);
        name2_ds = rootView.findViewById(R.id.my_ds_paihang_name2);
        name3_ds = rootView.findViewById(R.id.my_ds_paihang_name3);

        qiu1_ds = rootView.findViewById(R.id.my_ds_paihang_qiu1);
        qiu2_ds = rootView.findViewById(R.id.my_ds_paihang_qiu2);
        qiu3_ds = rootView.findViewById(R.id.my_ds_paihang_qiu3);

        top_ds = rootView.findViewById(R.id.ds_paihang_top);
        paihang_my_touxiang_ds = rootView.findViewById(R.id.paihang_my_touxiang_js);

        paihang_my_jinbi_ds=rootView.findViewById(R.id.paihang_my_jinbi_js);
        paihang_my_name_ds=rootView.findViewById(R.id.paihang_my_name_js);

        paihang_my_text_ds= rootView.findViewById(R.id.paihang_my_text_js);
        gr_paihang_ds = rootView.findViewById(R.id.gr_paihang_js);


        data_ds = new ArrayList<>();

        list_ds = new ArrayList<>();
        for (int i = 0; i < diquds.length; i++) {
            list_ds.add(diquds[i]);
        }
       // diqu(type);

        adapter_ds = new DuiShouPaihangAdapter(getActivity(), list_ds);
        adapter3_ds = new OpponentListAdapter(getActivity(), data_ds, sportType);
        listView_ds.setAdapter(adapter_ds);
        listView_ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
                adapter_ds.setSelectItem(position);
                adapter_ds.notifyDataSetChanged();

                if (list_ds.get(position).equals("好友排行")) {
                    typeDS = "myFriends";
                    top_ds.setText("好友"+"排行榜TOP10");
                    paihangDS(sportType, typeDS);
//                    top.setText("好友排行榜TOP10");
                } else if (list_ds.get(position).equals("全国排行")) {
                    typeDS = "country";
                    diquDS(typeDS);
//                    paihang(sportType,type);
//                    top.setText("全国排行榜TOP10");
                } else if (list_ds.get(position).equals("省排行")) {
                    typeDS = "province";
                    diquDS(typeDS);
//                    paihang(sportType,type);
//                    top.setText("省排行榜TOP10");
                } else if (list_ds.get(position).equals("市排行")) {
                    typeDS = "city";
                    diquDS(typeDS);
//                    paihang(sportType,type);
//                    top.setText("市排行榜TOP10");
                } else if (list_ds.get(position).equals("区排行")) {
                    typeDS = "area";
                    diquDS(typeDS);
//                    paihang(sportType,type);
//                    top.setText("区排行榜TOP10");
                }

            }
        });


        jiancexiaoxi();
        diqu(type);
        diquDS(typeDS);
        paihangDS(sportType,typeDS);
        huoquyundong();
        return rootView;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {

            case R.id.my_hd_home://活动
                intent.setClass(getActivity(), MyhuodongActivity.class);

                startActivity(intent);
                break;

            case R.id.xiaoxi://消息
                intent.setClass(getActivity(), XiaoxiActivity.class);

                startActivity(intent);
                break;
            case R.id.my_hd_wofabu://对手币
                wofabu.setTextColor(getResources().getColor(R.color.colorWhite));
                wobaoming.setTextColor(getResources().getColor(R.color.my_hd_tab));
                fb.setVisibility(View.VISIBLE);
                bm.setVisibility(View.INVISIBLE);


                ds_layout.setVisibility(View.VISIBLE);
                js_layout.setVisibility(View.GONE);
                paihangDS(sportType,type);
                break;
            case R.id.my_hd_wobaoming://技术分
                wofabu.setTextColor(getResources().getColor(R.color.my_hd_tab));
                wobaoming.setTextColor(getResources().getColor(R.color.colorWhite));
                bm.setVisibility(View.VISIBLE);
                fb.setVisibility(View.INVISIBLE);

                js_layout.setVisibility(View.VISIBLE);
                ds_layout.setVisibility(View.GONE);
                paihang(sportType,type);
                break;
            case R.id.my_paihang_tx1:

                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid",uuid1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_paihang_tx2:
                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid",uuid2);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.my_paihang_tx3:
                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid",uuid3);
                intent.putExtras(bundle);
                startActivity(intent);

                break;

            case R.id.my_ds_paihang_tx1:

                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid", uuid1_ds);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_ds_paihang_tx2:
                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid", uuid2_ds);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.my_ds_paihang_tx3:
                intent.setClass(getActivity(), HomeGRTXActivity.class);
                bundle.putString("uid", uuid3_ds);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
        }
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
                        //    top_ds.setText(entity.getData()+"排行榜TOP10");
                            paihang(sportType,type);
                          //  paihangDS(sportType,type);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                          //  Toast.makeText(getActivity(), entity.getMsg()+"", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //获取地区对手
    private void diquDS(final String type) {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCity")
                .addHeader("token",token)
                .addParams("type",typeDS)
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
                           // top.setText(entity.getData()+"排行榜TOP10");
                            top_ds.setText(entity.getData()+"排行榜TOP10");
                           // paihang(sportType,type);
                            paihangDS(sportType,typeDS);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                          //  Toast.makeText(getActivity(), entity.getMsg()+"", Toast.LENGTH_SHORT).show();

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
                            adapter2 = new JBMXJishuListAdapter(getActivity(), data2,"2");
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
                          //  Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

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
                            userRankInfo = entity.getData().getUserRankInfo();
                            adapter3 = new PaihangListAdapter(getActivity(),data,sportType);
                            MylistView.setAdapter(adapter3);
                            if (entity.getData().getUserRankInfo().getRank()>11){
                                gr_paihang.setVisibility(View.VISIBLE);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getUserRankInfo().getImgURL()).into(paihang_my_touxiang);
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
                                    intent.setClass(getActivity(), HomeGRTXActivity.class);
                                    bundle.putString("uid",data.get(position).getPlayerUUID());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });




                            if (entity.getData().getTopThree().size()==1){
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                name1.setText(entity.getData().getTopThree().get(0).getNickname());
                                lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                uuid1 = entity.getData().getTopThree().get(0).getPlayerUUID();
                                paihang1.setVisibility(View.VISIBLE);
                                paihang2.setVisibility(View.INVISIBLE);
                                paihang3.setVisibility(View.INVISIBLE);
                            }else   if (    entity.getData().getTopThree().size()==2){
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
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
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(0).getImgURL()).into(tx1);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(1).getImgURL()).into(tx2);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl)+entity.getData().getTopThree().get(2).getImgURL()).into(tx3);
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
                          //  Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //获取排行对手币
    private void paihangDS(final int sportType, String type) {
        LogU.Ld("1608", "技术排行yin" + token + "sportType---" + sportType + "项目" + "类型" + type);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getRivalCurrencyRanking")
                .addHeader("token", token)
                .addParams("type", typeDS)
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
                            List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> listds;
                            listds = entity.getData().getOtherRankInfo();
                            data_ds.clear();
                            data_ds.addAll(listds);
                            adapter3_ds = new OpponentListAdapter(getActivity(), data_ds, sportType);
                            MylistView_ds.setAdapter(adapter3_ds);
                            if (entity.getData().getUserRankInfo().getRank()>11) {
                                gr_paihang_ds.setVisibility(View.VISIBLE);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getUserRankInfo().getImgURL()).into(paihang_my_touxiang_ds);
                                paihang_my_text_ds.setText(entity.getData().getUserRankInfo().getRank() + "");
                                paihang_my_name_ds.setText(entity.getData().getUserRankInfo().getNickname());
                                paihang_my_jinbi_ds.setText(entity.getData().getUserRankInfo().getTotal());


                            }else {
                                gr_paihang_ds.setVisibility(View.GONE);
                            }

                            MylistView_ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(getActivity(), HomeGRTXActivity.class);
                                    bundle.putString("uid", data_ds.get(position).getPlayerUUID());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });


                            if (entity.getData().getTopThree().size() == 1) {
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1_ds);
                                name1_ds.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1_ds.setText(entity.getData().getTopThree().get(0).getTotle_temp());
                                uuid1_ds = entity.getData().getTopThree().get(0).getPlayerUUID();
                                paihang1_ds.setVisibility(View.VISIBLE);
                                paihang2_ds.setVisibility(View.INVISIBLE);
                                paihang3_ds.setVisibility(View.INVISIBLE);
                            } else if (entity.getData().getTopThree().size() == 2) {
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1_ds);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(1).getImgURL()).into(tx2_ds);
                                name1_ds.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1_ds.setText(entity.getData().getTopThree().get(0).getTotle_temp());
                                name2_ds.setText(entity.getData().getTopThree().get(1).getNickname());
                                qiu2_ds.setText(entity.getData().getTopThree().get(1).getTotle_temp());
                                uuid1_ds = entity.getData().getTopThree().get(0).getPlayerUUID();
                                uuid2_ds = entity.getData().getTopThree().get(1).getPlayerUUID();
                                paihang1_ds.setVisibility(View.VISIBLE);
                                paihang2_ds.setVisibility(View.VISIBLE);
                                paihang3_ds.setVisibility(View.INVISIBLE);
                            } else if (entity.getData().getTopThree().size() == 3) {
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(0).getImgURL()).into(tx1_ds);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(1).getImgURL()).into(tx2_ds);
                                Glide.with(getActivity()).load(getResources().getString(R.string.imgurl) + entity.getData().getTopThree().get(2).getImgURL()).into(tx3_ds);
                                name1_ds.setText(entity.getData().getTopThree().get(0).getNickname());
                                qiu1_ds.setText(entity.getData().getTopThree().get(0).getTotle_temp());

                                //   lv1.setText(entity.getData().getTopThree().get(0).getLevel());
                                name2_ds.setText(entity.getData().getTopThree().get(1).getNickname());
                                qiu2_ds.setText(entity.getData().getTopThree().get(1).getTotle_temp());
                                // lv2.setText(entity.getData().getTopThree().get(1).getLevel());
                                name3_ds.setText(entity.getData().getTopThree().get(2).getNickname());
                                qiu3_ds.setText(entity.getData().getTopThree().get(2).getTotle_temp());
                                //   lv3.setText(entity.getData().getTopThree().get(2).getLevel());
                                uuid1_ds = entity.getData().getTopThree().get(0).getPlayerUUID();
                                uuid2_ds = entity.getData().getTopThree().get(1).getPlayerUUID();
                                uuid3_ds = entity.getData().getTopThree().get(2).getPlayerUUID();
                                paihang1_ds.setVisibility(View.VISIBLE);
                                paihang2_ds.setVisibility(View.VISIBLE);
                                paihang3_ds.setVisibility(View.VISIBLE);
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
                         //   Toast.makeText(getActivity(), entity.getMsg()+"", Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(getActivity(),DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });
    }

    //检测消息
    private void jiancexiaoxi() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNotReadMessageCount")
                .addHeader("token", token)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JCXIEntity entity = gson.fromJson(result, JCXIEntity.class);
                            if (entity.getData().getNotReadCount() > 0) {
                                weidu.setVisibility(View.VISIBLE);

                            } else {
                                weidu.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                          //  ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }


    //检测活动
    private void jianceHuoDong() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllActiveCount")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                           AllActiveCountEntity entity = gson.fromJson(result, AllActiveCountEntity.class);
                            if (entity.getData().getCount() > 0) {
                               // weidu.setVisibility(View.VISIBLE);

                            } else {
                                //weidu.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }




    @Override
    public void onResume() {
        super.onResume();
        typeDS = "area";
        type = "area";
        diquDS(typeDS);
        diqu(type);

    }
}
