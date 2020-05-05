package com.example.android.tiaozhan.Home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.OnResponseListener;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;

public class HomeReserveCGDetailsActivity extends BaseActivity implements View.OnClickListener {


    private TextView biaoti, XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, time, timelog,  beizhu,  hezuo, mingcheng,cdh,cgh,reserve_xq_fb_time;
    private TextView  fangshi,  sex, dengji, dashang, queren,shibai,
            daojishi, fabutime, pipeiText, kaishiText, jieshuText, qxbmText, tuichuText, qiandaoText, quxiaotime, quxiaoyuanyin,  ayingb,
            ashub, apingb, zhuanhuan, yingText, cdf, dsf, tyjb, zyjb, dsftext, qunliao, tousu, hdxq_cgqd_text,cg_yd_ts_text,
           qiquan,adWin,bdWin,jgsm_text;
    private int tousuTAG = 0;

    private ImageView fanhui,  fabuzheImage, qiuleiImage, qieleiImage2,zhuangtai,tanhao;

    private String tab, uuid, timeString, dizhiString, lat, lng, mylat, mylng,yesORnoMy,tousuString, yousuYESNO;
    private LinearLayout linearLayout, ditutiaozhuan,tousuLayout,pptime, kstime, jstime, qxtime,qxyy,tousu_jieguo;
    private RelativeLayout hdxq_hdz,cg_yd_qx,ts_layout,relativeLayout,promo_hdxq_dt;

    private String token, inviteId, team, SecondSportId, startTime, playTime, FirstSportId, uid, tag = "0", tagb = "0", feiyongString;
    private SPUtils spUtils;
    //调起导航的uri
    private final String BAIDU_MAP_NAVI_URI = "baidumap://map/navi?query=";
    private final String GAODE_MAP_NAVI_URI = "androidamap://navi?sourceApplication=";
    private final String GOOGLE_MAP_NAVI_URI = "google.navigation:q=";
    //map app包名
    private final String BAIDU_MAP_APP = "com.baidu.BaiduMap";
    private final String GAODE_MAP_APP = "com.autonavi.minimap";
    private final String GOOGLE_MAP_APP = "com.google.android.apps.maps";

    private final String QQ_MAP_URL = "http://apis.map.qq.com/uri/v1/routeplan?type=drive&";
    //高德web服务的临时key(用于地理编码)
    private final String SELF_AMAP_KEY = "your key";
    private final String GEOCODE_HTTP_URL = "http://restapi.amap.com/v3/geocode/geo?";



    private double mLatitude;
    private double mLongitude;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;

    private LinearLayout weixin, pengyouquan, weibo,fb_time;
    private Dialog mCameraDialog;
    private RelativeLayout cdh_layout;


    private boolean isRun = true;
    private OnResponseListener responseListener;

    private HDXQEntity entity;
    private int flg=1,hezuoString;
    private String luyin;
    private Dialog dialog;
    private TextView ds_xz,tsjgText1,tsjgText2;

    @Override
    public int initContentView() {
        return R.layout.activity_home_reserve_cg_details;
    }

    @Override
    protected void initUIAndListener() {
        feiyong = findViewById(R.id.home_hdxq_feiyong);
        time = findViewById(R.id.home_hdxq_time);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        timelog = findViewById(R.id.home_hdxq_timelog);
        //场地号
        cdh= findViewById(R.id.home_hdxq_cdh);
        cgh= findViewById(R.id.home_hdxq_cgh);
        beizhu = findViewById(R.id.home_hdxq_beizhu);
        fabuzheImage = findViewById(R.id.home_hdxq_touxiang);
        fabuzheImage.setOnClickListener(this);
        qieleiImage2 = findViewById(R.id.home_hdxq_image_qiu2);
        lv = findViewById(R.id.home_hdxq_lv);
        mingcheng = findViewById(R.id.home_hdxq_mingcheng);
        ditutiaozhuan = findViewById(R.id.home_hdxq_ditutiaozhuan);
        ditutiaozhuan.setOnClickListener(this);
        dizhi = findViewById(R.id.home_hdxq_dizhi);
        qiuleiImage = findViewById(R.id.home_hdxq_image_qiu);
        XMid = findViewById(R.id.home_hdxq_id);
        qiuleiText = findViewById(R.id.home_hdxq_text_qiulei);
        moshi = findViewById(R.id.home_hdxq_moshi);
        name = findViewById(R.id.home_hdxq_name);

        reserve_xq_fb_time = findViewById(R.id.reserve_xq_fb_time);
        hezuo = findViewById(R.id.home_hdxq_hezuo);


        fabutime = findViewById(R.id.home_hdxq_fabutime);
        pipeiText = findViewById(R.id.home_hdxq_pipeitime);
        kaishiText = findViewById(R.id.home_hdxq_kaishitime);
        jieshuText = findViewById(R.id.home_hdxq_jieshutime);
        quxiaotime = findViewById(R.id.home_hdxq_quxiaoshijian);
        quxiaoyuanyin = findViewById(R.id.home_hdxq_quxiaoyuanyin);

        tousu = findViewById(R.id.home_hdxq_tousu);
        tousu.setOnClickListener(this);

        promo_hdxq_dt = findViewById(R.id.promo_hdxq_dt);
        promo_hdxq_dt.setOnClickListener(this);
        tousu_jieguo = findViewById(R.id.tousu_jieguo);
        tsjgText1 = findViewById(R.id.home_xq_tsjg_text1);
        tsjgText2 = findViewById(R.id.home_xq_tsjg_text2);

        tanhao = findViewById(R.id.home_hdxq_tousu_image);

        tousuLayout = findViewById(R.id.home_hdxq_tousu_layout);

        qxyy = findViewById(R.id.hdxq_qxyy);
        relativeLayout = findViewById(R.id.cg_yd_qx);
        relativeLayout.setOnClickListener(this);

        pptime = findViewById(R.id.hdxq_ppsj);
        kstime = findViewById(R.id.hdxq_kssj);
        jstime = findViewById(R.id.hdxq_jssj);
        qxtime = findViewById(R.id.hdxq_qxsj);
        fb_time=findViewById(R.id.hdxq_qxsj);
        zhuangtai = findViewById(R.id.home_hdxq_image_zhuangtai);
        linearLayout = findViewById(R.id.hdxq_fbsj);



        cg_yd_ts_text = findViewById(R.id.cg_yd_ts_text);
        ts_layout = findViewById(R.id.ts_layout);
        shibai = findViewById(R.id.shibai);


        cdh_layout = findViewById(R.id.cdh_layout);
        cdh_layout.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        uid = (String) spUtils.get(this, "uuid", "无");
        mylat = (String) spUtils.get(this, "mylat", "无");
        mylng = (String) spUtils.get(this, "mylng", "无");

        mlocationClient = new LocationClient(this);

        qieleiImage2.setVisibility(View.GONE);
        lv.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        biaoti.setText("活动详情");

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tab");
        uuid = bundle.getString("uuid");
        yesORnoMy = bundle.getString("fabuzhe");
        LogU.Ld("1608", "活动状态" + tab);

        init();
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {


            case R.id.promo_hdxq_dt://新动态
                intent.setClass(this, HomeTouSuActivity.class);
                bundle.putString("uuid", uuid);

                intent.putExtras(bundle);
                startActivity(intent);

                break;

            case R.id.cg_yd_qx://取消预定
                quxiaoCG();
             //   quxiaobaoming();

                break;


            case R.id.home_hdxq_tousu://投诉

                    intent.setClass(this, TousuActivity.class);
                    bundle.putString("uuid", uuid);
                    bundle.putString("cgtag", "1");
                    bundle.putString("hezuo", hezuoString + "");
                    intent.putExtras(bundle);
                    startActivity(intent);

                break;
            case R.id.fanhui:
                if (!EmptyUtils.isStrEmpty(tab)) {
                    if (tab.equals("11")) {
                        intent.setClass(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (tab.equals("0")) {
                        finish();
                    } else {
                        finish();
                    }
                }else {
                    finish();

                }



                break;


            case R.id.cdh_layout:
                cdH();
                break;

            case R.id.home_hdxq_ditutiaozhuan://地图跳转
                if (isApplicationInstall(BAIDU_MAP_APP)) {
//                    goNaviByBaiDuMap(dizhiString);
                    goNaviByBaiDuMap(lat, lng);
                } else if (isApplicationInstall(GAODE_MAP_APP)) {

                }

                break;

        }
    }

    //平台接入
    private void ptjrInit() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PlatformIntervention")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            init();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //同意处理结果
    private void tongyi() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainAgree")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "同意" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
                            init();
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //项目详情
    private void init() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "项目详情" +"我出错了"+e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        LogU.Ld("1608", "场馆详情" +"我出错了"+uuid);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a){
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, HDXQEntity.class);
                            reserve_xq_fb_time.setText(entity.getData().getCreateTime());
                            mingcheng.setText(entity.getData().getSiteName());
                            name.setText(entity.getData().getPublishPlayerName());
                            lv.setText(entity.getData().getHeightLevel()+"");
                            Glide.with(HomeReserveCGDetailsActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);
                            time.setText(entity.getData().getStartDays() + "  " + entity.getData().getStartWeek() + "  " + entity.getData().getStartTimes() + "-" + entity.getData().getEndTimes());
                            timelog.setText(entity.getData().getPlayTime() + "小时");
                            XMid.setText("ID "+entity.getData().getOrderId()+"");
                            qiuleiText.setText(entity.getData().getSportName() + "    " + entity.getData().getSportTypeName());
                            moshi.setText("预定场馆");
                            dizhi.setText(entity.getData().getSiteAddress());
                            dizhiString = entity.getData().getSiteAddress();
                            lat = entity.getData().getSiteLat()+"";
                            lng = entity.getData().getSiteLng()+"";
                           // feiyong.setText(df.format(entity.getData().getSiteMoney()) + "元");
                            feiyong.setText("¥"+entity.getData().getSiteMoney() + "元");
                            String joinEndTime = entity.getData().getJoinEndTime();
                            shibai.setText(entity.getData().getSiteMoneyInfo());
                            hezuoString = entity.getData().getIsCooper();
                            mingcheng.setText(entity.getData().getSiteName());
                            fabutime.setText(entity.getData().getCreateTime()+"");
                            pipeiText.setText(entity.getData().getJoinEndTime()+"");
                            kaishiText.setText(entity.getData().getStartTime()+"");
                            jieshuText.setText(entity.getData().getFinishedTime());
                            quxiaotime.setText(entity.getData().getCancelTime()+"");
                            quxiaoyuanyin.setText(entity.getData().getSuspendReason()+"");
                            beizhu.setText(entity.getData().getComments());
                            if (entity.getData().getIsCooper() == 1) {
                                hezuo.setVisibility(View.VISIBLE);
                            } else {
                                hezuo.setVisibility(View.GONE);
                            }
                            if (entity.getData().getSportName().equals("羽毛球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiuda));
                            } else if (entity.getData().getSportName().equals("乒乓球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangda));
                            } else if (entity.getData().getSportName().equals("台球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiuda));
                            } else if (entity.getData().getSportName().equals("篮球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiuda));
                            } else if (entity.getData().getSportName().equals("足球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiuda));
                            } else if (entity.getData().getSportName().equals("排球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiuda));
                            } else if (entity.getData().getSportName().equals("网球")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiuda));
                            } else if (entity.getData().getSportName().equals("高尔夫")) {
                                qiuleiImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfuda));
                            }

                            if (entity.getData().getVenuenumber().equals("0")){
                                cgh.setVisibility(View.GONE);
                            }else {
                                cgh.setText(entity.getData().getVenuenumber());
                            }
                            if (entity.getData().getVenueid().equals("0")){
                                cdh.setVisibility(View.GONE);
                            }else {
                                cdh.setText(entity.getData().getVenueid());

                            }
                            yousuYESNO = entity.getData().getGetUserComplaint();
                            tousuString = entity.getData().getComplaint();


                            int sparrComplaint = entity.getData().getSparrComplaint();
                            String refereeComplaint = entity.getData().getRefereeComplaint();
                            String complaint = entity.getData().getComplaint();
                            int zyUser = entity.getData().getZyUser();
                            int zyUsers = entity.getData().getZyUsers();
                            int zyUserss = entity.getData().getZyUserss();
                            if (zyUser==0||zyUsers==0||zyUserss==0){
                                tousu.setClickable(true);
                            }else {
                                tousu.setClickable(false);
                            }

                            LogU.Ld("1608","投诉状态"+sparrComplaint+"==="+refereeComplaint+"==="+"==="+complaint);
                            if (sparrComplaint!=4||!complaint.equals("4")||!refereeComplaint.equals("4")) {
                                promo_hdxq_dt.setVisibility(View.VISIBLE);
                                tousu.setText("投诉中");
                            } else {
                                //  promo_hdxq_dt.setVisibility(View.GONE);

                            }

                            if(entity.getData().getEnd()==2){
                                tousuLayout.setVisibility(View.GONE);
                            }
                            if (complaint.equals("1")||refereeComplaint.equals("1")||sparrComplaint==1){
                                tousu_jieguo.setVisibility(View.VISIBLE);
                                tsjgText1.setVisibility(View.GONE);
                                tsjgText2.setVisibility(View.VISIBLE);

                                // tsjgText1.setText(entity.getData().getGetUsersnickname()+":  "+entity.getData().getUnreserved());
                                tsjgText2.setText(entity.getData().getComplaintsResult());
                            }



                            if (entity.getData().getPublicStatus() == 1) {

                               // tousu.setVisibility(View.GONE);

                               // zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pipeizhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);


                            } else if (entity.getData().getPublicStatus() == 2) {
                                tanhao.setVisibility(View.INVISIBLE);

//                                tousu.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);
                              //  zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daichufa));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

                               // relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);

                               // relativeLayout.setClickable(false);
                                shibai.setVisibility(View.GONE);
//                                qiandao.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 3) {




                                tanhao.setVisibility(View.INVISIBLE);

                               // tousu2.setVisibility(View.INVISIBLE);

                                // hdxq_hdz.setVisibility(View.VISIBLE);
                                //  qiandao.setVisibility(View.GONE);
//                                tousu.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);
                                relativeLayout.setClickable(false);
                                shibai.setVisibility(View.GONE);
                                //    zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.huodongzhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

//                                qiandao.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 4) {

                              //  zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);
                                    shibai.setVisibility(View.GONE);
                                } else {
                                    //relativeLayout.setVisibility(View.VISIBLE);

                                }


                            } else if (entity.getData().getPublicStatus() == 5) {
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiwancheng));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

                                //hdxq_hdz.setVisibility(View.VISIBLE);


                                relativeLayout.setVisibility(View.GONE);
                                tousuLayout.setVisibility(View.GONE);
                                shibai.setVisibility(View.GONE);
                            } else if (entity.getData().getPublicStatus() == 6) {
                                tousuLayout.setVisibility(View.GONE);
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);

                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daipingjia));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);


                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);
                                    shibai.setVisibility(View.GONE);
                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);
                                    shibai.setVisibility(View.GONE);
                                }
                                qxbmText.setText("去评价");


                            } else if (entity.getData().getPublicStatus() == 7) {

                                tousuLayout.setVisibility(View.GONE);
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);

                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiquxiao));
                                linearLayout.setVisibility(View.VISIBLE);
                                qxtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                qxyy.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                shibai.setVisibility(View.GONE);

                            } else if (entity.getData().getPublicStatus() == 8) {

                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);

                            //    zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.querenjieshu));

                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }

                            }else if (entity.getData().getPublicStatus() == 9){
                                tanhao.setVisibility(View.INVISIBLE);
                               // tousu4.setVisibility(View.INVISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                shibai.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.icon_tousuzhong));

                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                            }




                        }else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            intent.setClass(HomeReserveCGDetailsActivity.this,DengluActivity.class);
                            startActivity(intent);
                        }

                    }
                });
    }

    //分享
    private void setDialog() {

        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.chenggong_fenxiang, null);
        weixin = root.findViewById(R.id.cg_fenxiang_weixin);
        weixin.setOnClickListener(this);
        pengyouquan = root.findViewById(R.id.cg_fenxiang_pengyouquan);
        pengyouquan.setOnClickListener(this);
        weibo = root.findViewById(R.id.cg_fenxiang_weibo);
        weibo.setOnClickListener(this);


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










    //定位地址
    private void dingwei() {
//        定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动

        mlistener = new MylocationListener();

        //注册监听器
        mlocationClient.registerLocationListener(mlistener);
        //注册监听器
        mlocationClient.registerLocationListener(mlistener);
        //配置定位SDK各配置参数，比如定位模式、定位时间间隔、坐标系类型等
        LocationClientOption mOption = new LocationClientOption();
        //设置坐标类型
        mOption.setCoorType("bd09ll");
        //设置是否需要地址信息，默认为无地址
        mOption.setIsNeedAddress(true);
        //设置是否打开gps进行定位
        mOption.setOpenGps(true);
        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        int span = 1000;
        mOption.setScanSpan(span);
        //设置 LocationClientOption
        mlocationClient.setLocOption(mOption);

    }

    //所有的定位信息都通过接口回调来实现
    public class MylocationListener implements BDLocationListener {
        //定位请求回调接口
        private boolean isFirstIn = true;


        //定位请求回调函数,这里面会得到定位信息
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
            if (isFirstIn) {
                isFirstIn = false;
//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude);

            }


        }

    }

    @Override
    public void onStart() {
        super.onStart();
        //开启定位
//        mBaiduMap.setMyLocationEnabled(true);
        if (!mlocationClient.isStarted()) {
            mlocationClient.start();
        }
//        myOrientationListener.start();
    }

    //取消投诉
    private void quxiaotousu() {
        LogU.Ld("1608", "取消投诉" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/CancellationOfcomplaints")
                .addHeader("token", token)
                .addParams("publicUUID", inviteId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消投诉" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            init();

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //取消报名
    private void quxiaobaoming() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "取消报名---" + token + "---publishcId---" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userCancelActivity")
                .addHeader("token", token)
                .addParams("publishcId", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消报名" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
//                            if (tab.equals("1")) {
                            Intent intent = new Intent();
                            if (tab.equals("11")) {
                                intent.setClass(HomeReserveCGDetailsActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else {
                                finish();
                            }

//                            } else {
//                                init();
//                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            init();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    /**
     * by moos on 2017/09/18
     * func:判断手机是否安装了该应用
     *
     * @param packageName
     * @return
     */
    private boolean isApplicationInstall(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * by moos on 2017/09/18
     * func:调起百度导航
     */
    private void goNaviByBaiDuMap(String lat, String lng) {

        LatLng startLatLng = new LatLng(Double.parseDouble(mylat), Double.parseDouble(mylng));
        LatLng endLatLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        String uri = String.format("baidumap://map/direction?origin=%s,%s&destination=" + "%s,%s&mode=driving&src=com.34xian.demo", startLatLng.latitude, startLatLng.longitude, endLatLng.latitude, endLatLng.longitude);
        Intent intent = new Intent();
        intent.setData(Uri.parse(uri));
        startActivity(intent);



    }

    //弹窗取消报名 发布 预订
    private void quxiaoCG() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);
        ds_xz.setText("您确定取消本次场馆预订么？");

        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quxiaobaoming();
               /* if (qxbmText.getText().toString().equals("取消报名")){
                    quxiaobaoming();
                }else if (qxbmText.getText().toString().equals("取消预订")){
                    ds_xz.setText("您确定取消本次场馆预订么？");
                }else if (qxbmText.getText().toString().equals("取消发布")){
                    ds_xz.setText("您确定取消本次活动发布么？");

                }*/

            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    // 弹窗 场地号
    private void cdH() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        String cdh="每一个数字依次代表每半个小时对应的场地编号，如“场地编号  3,4”，表示第1个半小时在3号场地，第2个半小时在4号场地。";
        int tb = cdh.indexOf("3");
        int tz = cdh.indexOf("”");
        SpannableStringBuilder style_wPL = new SpannableStringBuilder(cdh);
        // StyleSpan styleSpan2 = new StyleSpan(Typeface.BOLD);
        //  style_wPL.setSpan(styleSpan2, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan_style_wPL = new ForegroundColorSpan(Color.parseColor("#030303"));
        ForegroundColorSpan colorSpan_style_wPL2 = new ForegroundColorSpan(Color.parseColor("#D85D27"));
        ForegroundColorSpan colorSpan_style_wPL3 = new ForegroundColorSpan(Color.parseColor("#030303"));

        style_wPL.setSpan(colorSpan_style_wPL, 0, tb, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style_wPL.setSpan(colorSpan_style_wPL2, tb, tz, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style_wPL.setSpan(colorSpan_style_wPL3, tz, cdh.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ds_xz.setText(style_wPL);

        tis_text.setText("场地编号说明");



        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        // 例如：
        //  cc_time=1291778220 ;
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
}
