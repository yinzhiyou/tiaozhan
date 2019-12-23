package com.example.android.promoter.Home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.promoter.Adapter.FQTZAdapter;
import com.example.android.promoter.Adapter.FQTZBAdapter;
import com.example.android.promoter.Adapter.HDXQAAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.CJHDEntity;
import com.example.android.promoter.Entity.HuodXQEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.ZhifubaoEntity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.AlipayHelper;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.banner.BannerAdapter;
import com.example.android.promoter.Toos.banner.BannerBaseAdapter;
import com.example.android.promoter.Toos.banner.BannerBean;
import com.example.android.promoter.Toos.banner.BannerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnMoreItemPickListener;
import cn.addapp.pickers.picker.LinkagePicker;
import cn.addapp.pickers.picker.SinglePicker;
import okhttp3.Call;

/**
 * 发起挑战
 */
public class FaqiTiaozhanActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, youshangjiao, quxiao, xiangmuText, moshiText, changguanText, fangshiText, sexText,
            jibieText, timeText, shichangText, feiyongText, hezuoText, peilianText, zhuanhuanText;
    private EditText feiyongEdit, beizhuEdit, dashangText,beizhu_edit_partner;
    private ImageView fanhui, dwd, timeyjt;
    private GridView gridViewA, gridViewB;
    private FQTZAdapter adapter;
    private FQTZBAdapter adapterB;
    private LinearLayout xiangmu, xingbie, feiyong, changguan, moshi, dashang, jibie, time, peilian;
    private Dialog mCameraDialog;
    private RelativeLayout jingji, yule, woshi, wozhao, fabu, aa, shumai, sexNan, sexNv, sexBuxian, faqi_tiaozhan_yuding,sc_layout,sc_layout_partner;
    // private Double cdf;
    private XBanner bannerView;


    private String fqtzXiangmu, fqtzXiangmuda, fqtzXiangmuid, fqtzXiangmudaid, moshiString = "1", cgname, cgid, token, sex = "2", fangshi = "1",
            dashangString = "0", peilianString = "0", zuigao = "10", zuidi = "1", touxiangimg, YQuuid, YQdengji, fqtzqiurenshu;
    private String canyurenshu = "2", jingjiYesNo, yuleYesNO, peilianYesNO, moshiTag, moshihao = "1", moshiName, timeRI, timeSHI, YQtouxiang, YQtab = "0",
            shichang, hezuo, FQHDyaoqing, hezuofeiyong;

    /*private String canyurenshu = "2", jingjiYesNo, yuleYesNO, peilianYesNO, moshiTag, moshihao = "1", moshiName, timeRI, timeSHI, YQtouxiang, YQtab = "0",
            shichang, hezuo, FQHDyaoqing, hezuofeiyong;*/

    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    //    private List<Map<String, Object>> dataListA;
//    private String touxiangA[];
//    private String qiuA[];
//    private String dengjiA[];
    private List<String> listTXA;
    private List<String> listQA;
    private List<String> listDJA;
    private List<String> listIDA;
    private List<String> listQB;
    private List<String> listTXB;
    private List<String> listDJB;
    private List<String> listIDB;
//    private List<Map<String, Object>> dataListB;
//    private String touxiangB[];
//    private String qiuB[];

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_faqi_tiaozhan);
//    }
    private JSONArray array1;
    private String tagg = "0";
    private List<String> list;
    private String placeTimeLen;
    private String placeMoney;
    private String placeNun;

    private String tagId1 = "1";
    private String timeStart;
    private View my_look_partner, my_have_partner;
    private LinearLayout home_faqi_xiangmu_partner, home_faqi_changguan_partner, home_faqi_time_partner, home_faqi_feiyong_partner, sport_partner, have_sprot_partner;

    private TextView home_faqi_xiangmu_text_partner, home_faqi_changguan_text_partner, home_faqi_time_text_partner, home_faqi_shichang_text_partner, home_faqi_feiyong_text_partner, have_partner, look_partner, fbu_text;

    @Override
    public int initContentView() {
        return R.layout.activity_faqi_tiaozhan;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        gridViewA = findViewById(R.id.home_faqi_grid_a);
        gridViewB = findViewById(R.id.home_faqi_grid_b);
//        adapter = new HDXQAAdapter(this);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        moshi = findViewById(R.id.home_faqi_moshi);
        moshi.setOnClickListener(this);
        xingbie = findViewById(R.id.home_faqi_xingbie);
        xingbie.setOnClickListener(this);
        feiyong = findViewById(R.id.home_faqi_feiyong);
        feiyong.setOnClickListener(this);
        changguan = findViewById(R.id.home_faqi_changguan);
        changguan.setOnClickListener(this);
        xiangmu = findViewById(R.id.home_faqi_xiangmu);
        xiangmu.setOnClickListener(this);
        fabu = findViewById(R.id.faqi_tiaozhan_fabu);
        fabu.setOnClickListener(this);
        dashang = findViewById(R.id.home_faqi_dashang);
        dashang.setOnClickListener(this);
        jibie = findViewById(R.id.home_faqi_jibie);
        jibie.setOnClickListener(this);
        xiangmuText = findViewById(R.id.home_faqi_xiangmu_text);
        moshiText = findViewById(R.id.home_faqi_moshi_text);
        bannerView = findViewById(R.id.fqtz_banner);
        changguanText = findViewById(R.id.home_faqi_changguan_text);
        fangshiText = findViewById(R.id.home_faqi_fangshi_text);
        sexText = findViewById(R.id.home_faqi_xingbie_text);
        dashangText = findViewById(R.id.home_faqi_dashang_text);
        jibieText = findViewById(R.id.home_faqi_jibie_text);
        time = findViewById(R.id.home_faqi_time);
        time.setOnClickListener(this);
        timeText = findViewById(R.id.home_faqi_time_text);
        timeyjt = findViewById(R.id.home_faqi_time_yjt);
        shichangText = findViewById(R.id.home_faqi_shichang_text);
        feiyongText = findViewById(R.id.home_faqi_feiyong_text);
        feiyongEdit = findViewById(R.id.home_faqi_feiyong_edit);
        hezuoText = findViewById(R.id.home_faqi_hezuo_text);
        peilianText = findViewById(R.id.home_faqi_peilianfei_text);
        peilian = findViewById(R.id.home_faqi_peilianfei);
        zhuanhuanText = findViewById(R.id.home_faqi_zhuanhuan);
        beizhuEdit = findViewById(R.id.home_faqi_beizhu_edit);
        dwd = findViewById(R.id.home_faqi_changguan_dwd);


        home_faqi_xiangmu_partner = findViewById(R.id.home_faqi_xiangmu_partner);
        home_faqi_xiangmu_partner.setOnClickListener(this);
        home_faqi_xiangmu_text_partner = findViewById(R.id.home_faqi_xiangmu_text_partner);

        home_faqi_changguan_partner = findViewById(R.id.home_faqi_changguan_partner);
        home_faqi_changguan_partner.setOnClickListener(this);

        home_faqi_changguan_text_partner = findViewById(R.id.home_faqi_changguan_text_partner);

        home_faqi_time_partner = findViewById(R.id.home_faqi_time_partner);
        home_faqi_time_partner.setOnClickListener(this);
        home_faqi_time_text_partner = findViewById(R.id.home_faqi_time_text_partner);
        home_faqi_shichang_text_partner = findViewById(R.id.home_faqi_shichang_text_partner);

        home_faqi_feiyong_text_partner = findViewById(R.id.home_faqi_feiyong_text_partner);

        sport_partner = findViewById(R.id.sport_partner);
        have_sprot_partner = findViewById(R.id.have_sprot_partner);

        my_have_partner = findViewById(R.id.my_have_partner);
        my_look_partner = findViewById(R.id.my_look_partner);
        look_partner = findViewById(R.id.look_partner);
        look_partner.setOnClickListener(this);
        have_partner = findViewById(R.id.have_partner);
        have_partner.setOnClickListener(this);
        fbu_text = findViewById(R.id.fbu_text);
        faqi_tiaozhan_yuding = findViewById(R.id.faqi_tiaozhan_yuding);
        faqi_tiaozhan_yuding.setOnClickListener(this);
       beizhu_edit_partner = findViewById(R.id.home_faqi_beizhu_edit_partner);
        sc_layout = findViewById(R.id.sc_layout);
        sc_layout_partner = findViewById(R.id.sc_layout_partner);


        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        token = (String) spUtils.get(this, "logintoken", "");
        touxiangimg = (String) spUtils.get(this, "touxiang", "");

        array1 = new JSONArray();


        fqtzXiangmu = (String) spUtileFQTZ.get(this, "fqtzXiangmu", " ");
        fqtzXiangmuda = (String) spUtileFQTZ.get(this, "fqtzXiangmuda", " ");
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", " ");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", " ");
        moshiName = (String) spUtileFQTZ.get(this, "FQHDmoshiName", "娱乐模式");
        moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
        cgname = (String) spUtileFQTZ.get(this, "cgname", " ");
        cgid = (String) spUtileFQTZ.get(this, "cgid", " ");

        canyurenshu = (String) spUtileFQTZ.get(this, "canyurenshu", "1");


        listQA = new ArrayList<>();
        listTXA = new ArrayList<>();
        listDJA = new ArrayList<>();
        listIDA = new ArrayList<>();

        listQB = new ArrayList<>();
        listTXB = new ArrayList<>();
        listDJB = new ArrayList<>();
        listIDB = new ArrayList<>();

    }

    @Override
    protected void initData() {
        biaoti.setText("发布");
        youshangjiao.setText("奖励规则");
        moshiText.setText(moshiName);
        youshangjiao.setVisibility(View.VISIBLE);
        // changguanText.setText(cgname);
        listTXA.add(touxiangimg);
        listQA.add(fqtzXiangmuda);
        listDJA.add("Lv1");

        getHuodBanner();
        Intent dataa = getIntent();
        if (!EmptyUtils.isEmpty(dataa.getStringExtra("dataa"))) {
            timeStart = dataa.getStringExtra("dataa");
            placeTimeLen = dataa.getStringExtra("placeTimeLen");
            placeNun = dataa.getStringExtra("placeNun");
            placeMoney = dataa.getStringExtra("placeMoney");
            timeText.setText(timeStart);
            home_faqi_time_text_partner.setText(timeStart);
            home_faqi_shichang_text_partner.setText(placeTimeLen);
            home_faqi_feiyong_text_partner.setText("￥"+placeMoney);
            sc_layout.setVisibility(View.VISIBLE);
            sc_layout_partner.setVisibility(View.VISIBLE);
            shichangText.setText(placeTimeLen);
            feiyongText.setText("￥"+placeMoney);
            shichang = placeTimeLen.substring(0, placeTimeLen.indexOf("小"));
            //   dwd.setVisibility(View.VISIBLE);
            // hezuoText.setVisibility(View.VISIBLE);
            LogU.Le("1608", "我的" + placeMoney + cgname + timeStart + "场地号" + placeNun);
        } else {
            timeText.setText("");
            shichangText.setText("");
            feiyongText.setText("");
            sc_layout.setVisibility(View.GONE);
            sc_layout_partner.setVisibility(View.GONE);
        }
        if (!EmptyUtils.isStrEmpty(cgname)) {
            changguanText.setText(cgname);
            home_faqi_changguan_text_partner.setText(cgname);
            dwd.setVisibility(View.VISIBLE);
            hezuoText.setVisibility(View.VISIBLE);
        } else {
            changguanText.setText("");
            dwd.setVisibility(View.GONE);
            hezuoText.setVisibility(View.GONE);
        }
        String tagId = getIntent().getStringExtra("tagId");
        String ydId = getIntent().getStringExtra("ydId");
        LogU.Le("1608", "是不是场馆" + tagId + "有的" + tagId);

        if (!EmptyUtils.isStrEmpty(tagId)) {
            if (tagId1.equals(tagId)) {
                changguanText.setText("");
                dwd.setVisibility(View.GONE);
                hezuoText.setVisibility(View.GONE);
            }
        }
        if(!EmptyUtils.isStrEmpty(ydId)){
            if(ydId.equals("1")){
                have_partner.setTextColor(getResources().getColor(R.color.my_tab));
                look_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.VISIBLE);
                sport_partner.setVisibility(View.GONE);
                my_look_partner.setVisibility(View.INVISIBLE);
                my_have_partner.setVisibility(View.VISIBLE);
            }else {
                look_partner.setTextColor(getResources().getColor(R.color.my_tab));
                have_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.GONE);
                sport_partner.setVisibility(View.VISIBLE);
                my_have_partner.setVisibility(View.INVISIBLE);
                my_look_partner.setVisibility(View.VISIBLE);
            }
        }

        LogU.Le("1608", "changguan" + cgname);
        if (moshihao.equals("3") || moshihao.equals("4")) {
            zhuanhuanText.setText("陪练费用");
        } else {
            zhuanhuanText.setText("打赏费用");
        }
//        gridViewA.setAdapter(adapter);
//        gridViewB.setAdapter(adapter);
//        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent();
//                intent.setClass(FaqiTiaozhanActivity.this,YaoqingActivity.class);
//                startActivity(intent);
//            }
//        });

        qiuleishuju();


        initjihe();

    }


    private void initjihe() {

//        FQHDyaoqing = (String) spUtileFQTZ.get(this, "FQHDyaoqing", "");
//        if (FQHDyaoqing.length()>3){
//            JSONArray  array1 = JSONArray.fromObject();
//
//        }
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();


        if (YQtab.equals("1")) {
            LogU.Ld("1608", "走了1  返回" + YQtab);

            listTXA.add(YQtouxiang);
            listQA.add(fqtzXiangmuda);
            listDJA.add(YQdengji);
            listIDA.add(YQuuid);
            try {
                object2.put("team", "1");
                object2.put("uuid", YQuuid);
                array1.put(object2);
            } catch (Exception e) {


            }
        } else if (YQtab.equals("2")) {
            LogU.Ld("1608", "走了2  返回" + YQtab);

            listTXB.add(YQtouxiang);
            listQB.add(fqtzXiangmuda);
            listDJB.add(YQdengji);
            listIDB.add(YQuuid);
            try {
                object2.put("team", "2");
                object2.put("uuid", YQuuid);
                array1.put(object2);
            } catch (Exception e) {


            }
        }


//        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDyaoqing",  array1.toString());
//        array1.put(object3);
        System.out.println(array1.toString());
        LogU.Ld("1608", "邀请人拼接json------" + array1.toString());
//        qiuB[0] = "羽毛球";

    }

    //发起挑战
    private void init() {
        if (array1.toString().length() < 10) {
            FQHDyaoqing = "";
        } else {
            FQHDyaoqing = array1.toString();
        }
        /*String changdifei;
        if (TextUtils.isEmpty(feiyongEdit.getText())) {
            changdifei = "0";
        } else {
            changdifei = feiyongEdit.getText().toString() + "";
        }*/
        /*LogU.Ld("1608", "token" + token + "一级运动ID" + fqtzXiangmudaid + "二级运动ID" + fqtzXiangmuid + "运动模式" + moshihao
                + "场馆ID" + cgid + "场地费支付方式" + fangshi + "成员性别" + sex + "开始时间" + timeRI + timeSHI + "打赏费" + dashangString
                + "陪练费" + peilianString + "场地费" + feiyongEdit.getText().toString()+"时长" + shichang+"邀请" + FQHDyaoqing );*/
//        array1.toString()
        LogU.Le("1608", "我的参数" + placeMoney + cgname + timeStart + "场地号" + placeNun + "场馆" + cgid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAddActivity")
                .addHeader("token", token)
                .addParams("sportid", fqtzXiangmudaid + "")//一级运动ID
                .addParams("sportType", fqtzXiangmuid + "")//二级运动ID
                .addParams("SportMode", moshihao)// 运动模式 1娱乐 2竞技 3陪练 4找陪练
                .addParams("siteUid", cgid)//场馆ID
                .addParams("StartTime", timeStart)//开始时间
                .addParams("PlayTime", placeTimeLen)//时长
                .addParams("SiteMoney", placeMoney + "")//场地费
                .addParams("PaySiteMoneyType", fangshi)//场地费支付方式
                .addParams("teamSex", sex) //成员性别
                .addParams("LevelMin", zuidi)//成员最低技术等级
                .addParams("LevelMax", zuigao)//成员最高技术等级
                .addParams("Tips", dashangString)//打赏费
                .addParams("comments", beizhuEdit.getText().toString())// 备注
                .addParams("member", FQHDyaoqing)//发布前邀请的人 可为空 如果有 则为json数据 如[{"team":"1","uuid":"b60d8e06-1ff3-f048-d42f-49f42b7f0e2b"},{"team":"2","uuid":"99f24ba3-4e4f-ab35-d546-369ffa453884"}]
                .addParams("MoneyPerhour", peilianString)//陪练费
                .addParams("payType", "")
                .addParams("venueid", placeNun + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战阴" + response);
                        LogU.Le("1608", "我的参数" + placeMoney + cgname + timeStart + "场地号" + placeNun);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();//传值
                            Intent intent = new Intent();
                            intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuCGActivity.class);
                            bundle.putString("tag", "1");
                            bundle.putString("uuid", entity.getData().getUuid());
                            intent.putExtras(bundle);
                            startActivity(intent);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                        }
                    }
                });
    }


    //球类数据
    private void qiuleishuju() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllSportLst")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "球类数据" + result);
                        Boolean a = result.indexOf("2000") != -1;

//                        if (a) {
//                            Gson gson = new Gson();
//                            CJHDEntity entity = gson.fromJson(result, CJHDEntity.class);
//                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.setClass(FaqiTiaozhanActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {

//            case R.id.home_faqi_dashang://打赏
//                ArrayList<String> list = new ArrayList<>();
//                for (int i = 0; i < 90; i++) {
//                    String s = "";
////                    if(i<10){
////                        s = "0"+i;
////                    }else{
//                    s = i + "";
////                    }
//                    list.add(s);
//                }
////        String[] ss = (String[]) list.toArray();
//                SinglePicker<String> picker = new SinglePicker<>(this, list);
//                picker.setCanLoop(false);//不禁用循环
//                picker.setLineVisible(true);
//                picker.setTextSize(18);
//                picker.setSelectedIndex(8);
//                picker.setWheelModeEnable(false);
//                //启用权重 setWeightWidth 才起作用
//                picker.setLabel("元");
//                picker.setWeightEnable(true);
//                picker.setWeightWidth(1);
//                picker.setSelectedTextColor(Color.GREEN);//前四位值是透明度
//                picker.setUnSelectedTextColor(Color.RED);
//                picker.setSelectedTextColor(0xFF999999);//中间滚动项文字颜色
//                picker.setUnSelectedTextColor(0xFF999999);//中间滚动项文字颜色
//
////                picker.setOnSingleWheelListener(new OnSingleWheelListener() {//滑动监听
////                    @Override
////                    public void onWheeled(int index, String item) {
//////                        Toast.makeText(FaqiTiaozhanActivity.this, "index=" + index + ", item=" + item, Toast.LENGTH_SHORT).show();
////                        dashangText.setText(index+"元/人aaaa");
////                    }
////                });
//                picker.setOnItemPickListener(new OnItemPickListener<String>() {
//                    @Override
//                    public void onItemPicked(int index, String item) {
////                        Toast.makeText(FaqiTiaozhanActivity.this, "index=" + index + ", item=" + item, Toast.LENGTH_SHORT).show();
//                        dashangText.setText(item + "元");
//                        dashangString = item;
//                    }
//                });
//                picker.show();
//                break;
            case R.id.home_faqi_jibie://级别
                LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

                    @Override
                    public boolean isOnlyTwo() {
                        return true;
                    }

                    @Override
                    public List<String> provideFirstData() {
                        ArrayList<String> firstList = new ArrayList<>();
                        if (moshiText.getText().toString().equals("我找陪练")) {
                            for (int i = 4; i <= 10; i++) {
                                String str = "";
                                str = i + "";
                                firstList.add(str);
                            }
                        } else {
                            for (int i = 1; i <= 10; i++) {
                                String str = "";
                                str = i + "";
                                firstList.add(str);
                            }
                        }


                        return firstList;
                    }

                    @Override
                    public List<String> provideSecondData(int firstIndex) {
                        ArrayList<String> secondList = new ArrayList<>();
                        if (moshiText.getText().toString().equals("我找陪练")) {
                            for (int i = 4; i <= 10; i++) {
                                String str = "";

                                str = i + "";
                                secondList.add(str);
                            }
                        } else {
                            for (int i = 1; i <= 10; i++) {
                                String str = "";

                                str = i + "";
                                secondList.add(str);
                            }
                        }


                        return secondList;
                    }

                    @Override
                    public List<String> provideThirdData(int firstIndex, int secondIndex) {
                        return null;
                    }

                };
                LinkagePicker picker1 = new LinkagePicker(this, provider);
                picker1.setCanLoop(false);
                picker1.setLabel("—", " ");
                picker1.setSelectedIndex(0, 8);
                //picker.setSelectedItem("12", "9");
                picker1.setOnMoreItemPickListener(new OnMoreItemPickListener<String>() {

                    @Override
                    public void onItemPicked(String first, String second, String third) {
                        if (Integer.parseInt(first) > Integer.parseInt(second)) {
                            ToastUitl.longs("高级别不能低于低级别");
                        } else {
                            Toast.makeText(FaqiTiaozhanActivity.this, (first + "-" + second + "-" + third), Toast.LENGTH_SHORT).show();
                            zuidi = first;
                            zuigao = second;
                            jibieText.setText(first + "级—" + second + "级");
                        }

                    }
                });
                picker1.show();

                break;
            case R.id.fanhui://返回
                finish();
                break;
            case R.id.youshangjiao:

                intent.setClass(this, JLGZActivity.class);
                startActivity(intent);
                break;
            case R.id.home_faqi_moshi://模式选择
                setDialog(R.layout.fqtz_moshi, "1");
                fangshi = "1";
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "1");
                fangshiText.setText("AA");
                break;
            case R.id.home_faqi_xingbie://性别选择
                setDialog(R.layout.fqtz_xingbie, "3");
                break;
            case R.id.home_faqi_feiyong://费用方式选择

                if (moshiText.getText().toString().equals("竞技模式")) {
                    setDialog(R.layout.fqtz_feiyong, "2");
                } else {

                }
                break;
//            case R.id.fqtz_dialog_quxiao://窗口取消
//                mCameraDialog.dismiss();
//                break;
            case R.id.fqtz_ms_yule:
                if (yuleYesNO.equals("1")) {
//                    moshiString = "1";
                    mCameraDialog.dismiss();
                    moshiText.setText("娱乐模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "娱乐模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "1");
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("打赏费用");
                    adapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_jingji:
//                jingji.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                if (jingjiYesNo.equals("1")) {
//                    moshiString = "2";
                    mCameraDialog.dismiss();
                    moshiText.setText("竞技模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "竞技模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "2");
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("打赏费用");
                    adapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_woshi:
                if (fqtzqiurenshu.equals("2")) {
//                    moshiString = "3";
                    mCameraDialog.dismiss();
                    moshiText.setText("我是陪练");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "我是陪练");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "3");
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("陪练费用");
                    adapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_wozhao:
                if (fqtzqiurenshu.equals("2")) {
//                    moshiString = "4";
                    mCameraDialog.dismiss();
                    moshiText.setText("我找陪练");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "我找陪练");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "4");
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("陪练费用");
                    adapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fatz_feiyong_aa://费用模式 AA
                fangshi = "1";
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "1");
                mCameraDialog.dismiss();
                fangshiText.setText("AA");
                break;
            case R.id.fatz_feiyong_shumai:
                fangshi = "2";
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "2");
                mCameraDialog.dismiss();
                fangshiText.setText("谁输谁买单");
                break;
            case R.id.fqtz_xingbie_nan:
                sex = "0";
                mCameraDialog.dismiss();
                sexText.setText("男");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "0");
                break;
            case R.id.fqtz_xingbie_nv:
                sex = "1";
                mCameraDialog.dismiss();
                sexText.setText("女");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "1");
                break;
            case R.id.fqtz_xingbie_buxian:
                sex = "2";
                mCameraDialog.dismiss();
                sexText.setText("不限");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "2");
                break;
            case R.id.home_faqi_changguan://场馆跳转
                if (fqtzXiangmu.equals(" ")) {
                    Toast.makeText(this, "请先选择项目", Toast.LENGTH_SHORT).show();
                } else {
                    intent.setClass(this, ChuangguanActivity.class);
                    intent.putExtra("zId","0");
                    startActivity(intent);
                }

                break;
            case R.id.home_faqi_xiangmu://项目选择

                intent.setClass(this, FQTZXiangmuActivity.class);
                bundle.putString("tab", fqtzXiangmudaid);
                bundle.putString("zId","0");
                spUtils.put(this, "zId", "0");
                intent.putExtras(bundle);
                startActivity(intent);
                break;


            case R.id.faqi_tiaozhan_fabu://发布跳转
                LogU.Le("1608", "我的参数" + placeMoney + cgname + timeStart + "场地号" + placeNun + cgid);
                DecimalFormat df = new DecimalFormat("0.00");
                DecimalFormat ef = new DecimalFormat("0.0000");
                if (TextUtils.isEmpty(dashangText.getText())) {
                    dashangString = "0";
                } else {
                    dashangString = dashangText.getText().toString() + "";
                }

                moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
                if (cgid.equals(" ")) {
                    Toast.makeText(this, "请选择场馆", Toast.LENGTH_SHORT).show();
                } else if ((moshihao.equals("3") || moshihao.equals("4")) && TextUtils.isEmpty(dashangText.getText())) {
                    Toast.makeText(this, "请填写陪练费用", Toast.LENGTH_SHORT).show();
                } else if (timeText.getText().toString() == "" || timeText.getText().toString() == null) {
                    ToastUitl.longs("请填写时间");
                    return;
                } else {
                    if (hezuo.equals("1")) {
                        //合作 一定跳支付
                        if (moshihao.equals("3") || moshihao.equals("4")) {
//                        if (!dashangString.equals(peilianString)){
                            peilianString = dashangString;
                            dashangString = "0";
//                        }
                        } else {
                            peilianString = "0";
                        }


                        /*if (Integer.parseInt(canyurenshu) > 4) {
                            cdf = Double.valueOf(hezuofeiyong) / Integer.parseInt(canyurenshu);
                            LogU.Ld("1608", "场地费dada" + Double.valueOf(df.format(cdf)) + "    " + Double.valueOf(ef.format(cdf)));
                            if (Double.valueOf(df.format(cdf)) < Double.valueOf(ef.format(cdf))) {
                                cdf = ((Double.valueOf(hezuofeiyong) / Integer.parseInt(canyurenshu)) * 100 + 1) / 100;
                            }

                        } else {
                            cdf = Double.valueOf(hezuofeiyong);
                        }*/


                        LogU.Ld("1608", "场地费" + placeMoney + "    " + placeMoney.toString().length());

                        intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                        bundle.putString("tag", "1");
                        bundle.putString("fangshi", fangshi);
                        bundle.putString("moshiString", moshihao);
                        bundle.putString("sex", sex);
                        bundle.putString("zuidi", zuidi);
                        bundle.putString("zuigao", zuigao);
                        bundle.putString("dashangString", dashangString);
                        bundle.putString("shichang", shichang + "");
                        bundle.putString("peilianString", peilianString);
                        bundle.putString("json", array1.toString());
                        bundle.putString("beizhu", beizhuEdit.getText().toString() + "");
                        /*bundle.putString("changdifei", df.format(cdf) + "");
                        bundle.putString("hezuofeiyong", df.format(cdf) + "");*/
                        bundle.putString("changdifei", placeMoney + "");
                        bundle.putString("hezuofeiyong", placeMoney + "");
                        bundle.putString("hezuo", hezuo);
                        bundle.putString("houtaifeiyong", placeMoney);
                        bundle.putString("canyurenshu", canyurenshu);
                        bundle.putString("fqtzXiangmudaid", fqtzXiangmudaid);
                        bundle.putString("timeStart", timeStart);
                        bundle.putString("placeNun", placeNun);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    } else {
                        if (TextUtils.isEmpty(feiyongEdit.getText())) {
                            ToastUitl.longs("请填写场地费");
                        } else {

                            String changdifei;
                            if (TextUtils.isEmpty(feiyongEdit.getText())) {
                                changdifei = "0";
                            } else {
                                changdifei = feiyongEdit.getText().toString() + "";
                            }


                            //非合作 不跳
                            if (moshihao.equals("1") || moshihao.equals("2")) {//娱乐模式or竞技模式

                                peilianString = "0";
                                if (TextUtils.isEmpty(feiyongEdit.getText())) {
                                    hezuofeiyong = "0";
                                } else {
                                    hezuofeiyong = feiyongEdit.getText().toString();
                                }

                                if (dashangString.equals("0")) {// 打赏

                                    init();
                                } else {//有打赏
                                    intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "1");
                                    bundle.putString("fangshi", fangshi);
                                    bundle.putString("moshiString", moshihao);
                                    bundle.putString("sex", sex);
                                    bundle.putString("zuidi", zuidi);
                                    bundle.putString("zuigao", zuigao);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("shichang", shichang + "");
                                    bundle.putString("peilianString", peilianString);
                                    bundle.putString("json", array1.toString());
                                    bundle.putString("beizhu", beizhuEdit.getText().toString() + "");
                                    bundle.putString("changdifei", placeMoney);
                                    bundle.putString("hezuo", hezuo);
                                    bundle.putString("houtaifeiyong", placeMoney);
                                    bundle.putString("canyurenshu", canyurenshu);
                                    bundle.putString("fqtzXiangmudaid", fqtzXiangmudaid);
                                    bundle.putString("timeStart", timeStart);
                                    bundle.putString("placeNun", placeNun);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            } else if (moshihao.equals("3")) {//我是陪练
//                        if (moshihao.equals("3") || moshihao.equals("4")) {
//                            if (!dashangString.equals(peilianString)){
                                peilianString = dashangString;
                                dashangString = "0";
//                            }
//                        }
                                init();
                            } else if (moshihao.equals("4")) {//我找陪练
//                        if (moshihao.equals("3") || moshihao.equals("4")) {
//                            if (!dashangString.equals(peilianString)){
                                peilianString = dashangString;
                                dashangString = "0";
//                            }else{
//                                dashangString = "0";
//                            }
//                        }

                                LogU.Ld("1608", "zhezhezhe" + peilianString);
                                if (peilianString.equals("0")) {//没有陪练费
                                    init();
                                } else {//有陪练费
                                    if (TextUtils.isEmpty(feiyongEdit.getText())) {
                                        hezuofeiyong = "0";
                                    } else {
                                        hezuofeiyong = feiyongEdit.getText().toString();
                                    }

                                    intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "1");
                                    bundle.putString("fangshi", fangshi);
                                    bundle.putString("moshiString", moshihao);
                                    bundle.putString("sex", sex);
                                    bundle.putString("zuidi", zuidi);
                                    bundle.putString("zuigao", zuigao);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("shichang", shichang + "");
                                    bundle.putString("peilianString", peilianString);
                                    bundle.putString("json", array1.toString());
                                    bundle.putString("beizhu", beizhuEdit.getText().toString() + "");
                                    bundle.putString("changdifei", placeMoney);
                                    bundle.putString("hezuo", hezuo);
                                    bundle.putString("houtaifeiyong", placeMoney);
                                    bundle.putString("canyurenshu", canyurenshu);
                                    bundle.putString("fqtzXiangmudaid", fqtzXiangmudaid);

                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            }
                        }
                    }
                }

                break;
            case R.id.home_faqi_time://时间
                //    if (timeText.getText().toString().length()<5){

                //  }else{
                String s = changguanText.getText().toString();
                LogU.Le("1608", "sdfsd" + s + "值" + EmptyUtils.isStrEmpty(xiangmuText.getText().toString()) + xiangmuText.getText().toString());
                if (fqtzXiangmu.equals(" ")) {
                    ToastUitl.longs("请先选择运动项目");
                } else if (!EmptyUtils.isStrEmpty(changguanText.getText().toString())) {
                    intent.setClass(FaqiTiaozhanActivity.this, StartTimeActivity.class);
                    intent.putExtra("zId","0");
                    startActivity(intent);
                } else {
                    ToastUitl.longs("请先选择场馆");
                }

                //  }

                break;


            case R.id.home_faqi_xiangmu_partner://伙伴运动项目
                int yd=0;
                intent.setClass(this, FQTZXiangmuActivity.class);
                bundle.putString("tab", fqtzXiangmudaid);
                spUtils.put(this, "yId", "1");
                bundle.putString("zId","1");
                intent.putExtras(bundle);
                bundle.putString("zId","1");
                startActivity(intent);
                break;

            case R.id.home_faqi_changguan_partner://伙伴运动场馆
                if (fqtzXiangmu.equals(" ")) {
                    Toast.makeText(this, "请先选择项目", Toast.LENGTH_SHORT).show();
                } else {
                    intent.setClass(this, ChuangguanActivity.class);
                    spUtils.put(this, "yId", "1");
                    startActivity(intent);
                }

                break;

            case R.id.home_faqi_time_partner://伙伴运动开始时间
                String ss = changguanText.getText().toString();
                LogU.Le("1608", "sdfsd" + ss + "值" + EmptyUtils.isStrEmpty(changguanText.getText().toString()));
                if (fqtzXiangmu.equals(" ")) {
                    Toast.makeText(this, "请先选择项目", Toast.LENGTH_SHORT).show();
                } else if (!EmptyUtils.isStrEmpty(changguanText.getText().toString())) {
                    intent.setClass(FaqiTiaozhanActivity.this, StartTimeActivity.class);
                    spUtils.put(this, "yId", "1");
                    startActivity(intent);
                } else {
                    ToastUitl.longs("请先选择场馆");
                }


                break;



            case R.id.look_partner://找运动伙伴
                look_partner.setTextColor(getResources().getColor(R.color.my_tab));
                have_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.GONE);
                sport_partner.setVisibility(View.VISIBLE);
                my_have_partner.setVisibility(View.INVISIBLE);
                my_look_partner.setVisibility(View.VISIBLE);

                break;
            case R.id.have_partner://我有运动伙伴
                have_partner.setTextColor(getResources().getColor(R.color.my_tab));
                look_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.VISIBLE);
                sport_partner.setVisibility(View.GONE);
                my_look_partner.setVisibility(View.INVISIBLE);
                my_have_partner.setVisibility(View.VISIBLE);
                break;

            case R.id.faqi_tiaozhan_yuding://预定场馆


                //  moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
                if (cgid.equals(" ")) {
                    Toast.makeText(this, "请先选择场馆", Toast.LENGTH_SHORT).show();
                } else if ((fqtzXiangmu.equals(" ") || moshihao.equals("4")) && TextUtils.isEmpty(dashangText.getText())) {
                    Toast.makeText(this, "请先选择项目", Toast.LENGTH_SHORT).show();
                } else if (timeText.getText().toString() == "" || timeText.getText().toString() == null) {
                    ToastUitl.longs("请填写时间");
                    return;
                } else {
                    if (hezuo.equals("1")) {
                        //合作 一定跳支付
                        if (moshihao.equals("3") || moshihao.equals("4")) {
//                        if (!dashangString.equals(peilianString)){
                            peilianString = dashangString;
                            dashangString = "0";
//                        }
                        } else {
                            peilianString = "0";
                        }

                        LogU.Ld("1608", "场地费" + placeMoney + "    " + placeMoney.toString().length());

                        intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                        bundle.putString("tag", "4");

                        bundle.putString("moshiString", moshihao);

                        bundle.putString("shichang", shichang + "");
                        bundle.putString("hezuo", hezuo);
                        bundle.putString("beizhu", beizhu_edit_partner.getText().toString() + "");
                        bundle.putString("json", array1.toString());
                        bundle.putString("changdifei", placeMoney + "");

                        bundle.putString("fqtzXiangmudaid", fqtzXiangmudaid);
                        bundle.putString("timeStart", timeStart);
                        bundle.putString("placeNun", placeNun);
                        bundle.putString("cgid", cgid);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                }

                break;
        }
    }



   /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里要通过请求码来判断数据的来源
        switch (requestCode) {
            case 1:
                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("data");
                    LogU.Le("1608","zhi"+getData);
                   timeText.setText(getData);
                }else {
                    timeText.setText("");
                }
                break;
            */

    /**
     * 这里可以有多个requestcode
     **//*
            default:
        }
    }*/
    private void setDialog(int layout, String tab) {


        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
//        mCameraDialog.setCanceledOnTouchOutside(false);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                layout, null);
        if (tab.equals("1")) {
            moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
            jingji = root.findViewById(R.id.fqtz_ms_jingji);
            jingji.setOnClickListener(this);
            yule = root.findViewById(R.id.fqtz_ms_yule);
            yule.setOnClickListener(this);
            woshi = root.findViewById(R.id.fqtz_ms_woshi);
            woshi.setOnClickListener(this);
            wozhao = root.findViewById(R.id.fqtz_ms_wozhao);
            wozhao.setOnClickListener(this);


            if (moshihao.equals("1")) {
                yule.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (moshihao.equals("2")) {
                jingji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (moshihao.equals("3")) {
                woshi.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (moshihao.equals("4")) {
                wozhao.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            }

            if (yuleYesNO.equals("0")) {
                yule.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            } else if (jingjiYesNo.equals("0")) {
                jingji.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            }
            if (peilianYesNO.equals("0")) {
                woshi.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                wozhao.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            }
            LogU.Ld("1608", "fqtzqiurenshu" + fqtzqiurenshu);
            if (!fqtzqiurenshu.equals("2")) {
                woshi.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                wozhao.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            }
        } else if (tab.equals("2")) {
            fangshi = (String) spUtileFQTZ.get(this, "FQHDfangshi", "1");

            aa = root.findViewById(R.id.fatz_feiyong_aa);
            aa.setOnClickListener(this);
            shumai = root.findViewById(R.id.fatz_feiyong_shumai);
            shumai.setOnClickListener(this);
            if (fangshi.equals("1")) {
                aa.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (fangshi.equals("2")) {
                shumai.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            }


        } else if (tab.equals("3")) {

            sex = (String) spUtileFQTZ.get(this, "FQHDsex", "2");
            sexNan = root.findViewById(R.id.fqtz_xingbie_nan);
            sexNan.setOnClickListener(this);
            sexNv = root.findViewById(R.id.fqtz_xingbie_nv);
            sexNv.setOnClickListener(this);
            sexBuxian = root.findViewById(R.id.fqtz_xingbie_buxian);
            sexBuxian.setOnClickListener(this);

            if (sex.equals("0")) {
                sexNan.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (sex.equals("1")) {
                sexNv.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            } else if (sex.equals("2")) {
                sexBuxian.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            }
        }


//        quxiao = root.findViewById(R.id.fqtz_dialog_quxiao);
//        quxiao.setOnClickListener(this);
//        初始化视图
//        root.findViewById(R.id.dialog_item_button1);
//        root.findViewById(R.id.dialog_item_button2);
//
//        root.findViewById(R.id.btn_confirm).setOnClickListener(this);
//        mViewProvince = (WheelView) root.findViewById(R.id.id_province);
//        mViewCity = (WheelView) root.findViewById(R.id.id_city);
//        mViewDistrict = (WheelView) root.findViewById(R.id.id_district);
//        mBtnConfirm = (Button) root.findViewById(R.id.btn_confirm);
//        mBtnConfirm.setOnClickListener(this);
//        setUpListener();
//        setUpData();

        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();


    }


    @Override
    protected void onStart() {
        fqtzXiangmu = (String) spUtileFQTZ.get(this, "fqtzXiangmu", " ");
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", " ");
        fqtzXiangmuda = (String) spUtileFQTZ.get(this, "fqtzXiangmuda", " ");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", " ");
        fqtzqiurenshu = (String) spUtileFQTZ.get(this, "fqtzqiurenshu", "2");
        canyurenshu = (String) spUtileFQTZ.get(this, "canyurenshu", "1");
        jingjiYesNo = (String) spUtileFQTZ.get(this, "jingjiYesNo", " ");
        yuleYesNO = (String) spUtileFQTZ.get(this, "yuleYesNO", " ");
        peilianYesNO = (String) spUtileFQTZ.get(this, "peilianYesNO", " ");
        moshiTag = (String) spUtileFQTZ.get(this, "moshiTag", " ");
        timeRI = (String) spUtileFQTZ.get(this, "timeRI", " ");
        timeSHI = (String) spUtileFQTZ.get(this, "timeSHI", " ");
        YQtouxiang = (String) spUtileFQTZ.get(this, "YQtouxiang", " ");
        YQtab = (String) spUtileFQTZ.get(this, "YQtab", " ");
        //shichang = (String) spUtileFQTZ.get(this, "shichang", " ");
        hezuo = (String) spUtileFQTZ.get(this, "hezuo", "0");
        YQuuid = (String) spUtileFQTZ.get(this, "YQuuid", " ");
        YQdengji = (String) spUtileFQTZ.get(this, "YQdengji", " ");
        // hezuofeiyong = (String) spUtileFQTZ.get(this, "hezuofeiyong", "");
        fangshi = (String) spUtileFQTZ.get(this, "FQHDfangshi", "1");
        //cgname = (String) spUtileFQTZ.get(this, "cgname", " ");
        cgid = (String) spUtileFQTZ.get(this, "cgid", " ");


       /* if (!shichang.equals(" ")) {
            shichangText.setText(shichang + "小时");
        }*/

        if (moshiTag.equals("1")) {
            moshiText.setText("娱乐模式");
//            moshihao = "1";
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "1");
            spUtileFQTZ.remove(this, "moshiTag");
            LogU.Ld("1608", "走了标记" + moshiTag);

        } else {

        }
        LogU.Ld("1608", "合作" + hezuo);
        if (hezuo.equals("1")) {
            //合作
            //hezuoText.setVisibility(View.VISIBLE);
            feiyongText.setVisibility(View.VISIBLE);
            feiyongEdit.setVisibility(View.GONE);
            feiyongText.setText(placeMoney);
            // dwd.setVisibility(View.VISIBLE);
        } else if (hezuo.equals("0") && !cgname.equals(" ")) {
            //非合作
            // dwd.setVisibility(View.VISIBLE);
            hezuoText.setVisibility(View.VISIBLE);
            feiyongText.setVisibility(View.GONE);
            feiyongEdit.setVisibility(View.VISIBLE);
//            hezuoText.setVisibility(View.GONE);
            hezuoText.setText("(非合作)");
        }

        xiangmuText.setText(fqtzXiangmuda + "  " + fqtzXiangmu);
        home_faqi_xiangmu_text_partner.setText(fqtzXiangmuda + "  " + fqtzXiangmu);

        // changguanText.setText(cgname);
        LogU.Ld("1608", "fqtzXiangmudaid" + fqtzXiangmudaid + "fqtzXiangmuid" + fqtzXiangmuid + "标记" + moshiTag + "邀请头像" + YQtouxiang);

        adapter = new FQTZAdapter(this, listTXA, listQA, listDJA, Integer.parseInt(canyurenshu) / 2);
        adapterB = new FQTZBAdapter(this, listTXB, listQB, listDJB, Integer.parseInt(canyurenshu) / 2);

        initjihe();

        gridViewA.setAdapter(adapter);
        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值

                if (listQA.size() > position) {

                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, YaoqingActivity.class);
                    bundle.putString("tab", "1");
                    bundle.putStringArrayList("listTXA", (ArrayList<String>) listTXA);
                    bundle.putStringArrayList("listTXB", (ArrayList<String>) listTXB);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        gridViewB.setAdapter(adapterB);
        gridViewB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                LogU.Ld("1608", "点击B队" + listQB.size() + "----" + "----" + position);
                if (listQB.size() > position) {

                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, YaoqingActivity.class);
                    bundle.putString("tab", "2");
                    bundle.putStringArrayList("listTXA", (ArrayList<String>) listTXA);
                    bundle.putStringArrayList("listTXB", (ArrayList<String>) listTXB);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        adapter.notifyDataSetChanged();
        adapterB.notifyDataSetChanged();



       /* if (timeText.getText().toString().length()<5){
            timeyjt.setVisibility(View.INVISIBLE);
        }else{
            timeyjt.setVisibility(View.INVISIBLE);
        }*/


        super.onStart();
    }

    public void getHuodBanner() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityBanner")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            HuodXQEntity entity = gson.fromJson(result, HuodXQEntity.class);
                            if (!EmptyUtils.isEmpty(entity)) {
                                list = new ArrayList<>();
                                for (int i = 0; i < entity.getData().size(); i++) {
                                    list.add(getResources().getString(R.string.imgurl) + entity.getData().get(i).getPicurl());
                                }
                                bannerView.setData(list, null);
                                bannerView.loadImage(new XBanner.XBannerAdapter() {
                                    @Override
                                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                                        Glide.with(FaqiTiaozhanActivity.this).load(list.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);

                                    }
                                });

                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}
