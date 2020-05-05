package com.example.android.tiaozhan.reserve;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
import com.example.android.tiaozhan.Home.HomeGzsmActivity;
import com.example.android.tiaozhan.Promoter.PromoterCLActivity;
import com.example.android.tiaozhan.Promoter.PromoterLSActivity;
import com.example.android.tiaozhan.Promoter.PromoterSSActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.OnResponseListener;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.luyin.PlaybackDialogFragment;
import com.example.android.tiaozhan.Toos.luyin.RecordingItem;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

public class ReserveCGDetailsActivity extends BaseActivity implements View.OnClickListener {


    private TextView biaoti, tousu1, tousu2, tousu3, tousuName, tousuTime, bofang, tsjgText1, tsjgText2, tousuTitle,tousujl,cdh,cgh,ptjr;
    private TextView  XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, time, timelog, beizhu, hezuo, mingcheng, reserve_xq_fb_time;
    private TextView fangshi, sex, dengji, dashang, queren,bss, ss,
            daojishi, fabutime, pipeiText, kaishiText, jieshuText, qxbmText, tuichuText, qiandaoText, quxiaotime, quxiaoyuanyin, ayingb,
            ashub, apingb, zhuanhuan, yingText, cdf, dsf, tyjb, zyjb, dsftext, qunliao, tousu,  hdxq_cgqd_text, cg_yd_ts_text,
            tousu4,  tousu6, tousu7, tousubiaoti,  cxts, ty, bty,  qiquan, adWin, bdWin, jgsm_text;
    private int tousuTAG = 0;
    private int jieru;
    private ImageView fanhui, fabuzheImage, qiuleiImage, qieleiImage2, zhuangtai, tanhao, touxiang;
    private String token, moshiString, uuid, tousuString, tag, luyin, isHandle, nameString, uid,zhuangtaiString;
    private String tab,timeString, dizhiString, lat, lng, mylat, mylng, yesORnoMy,  yousuYESNO;
    private LinearLayout linearLayout, ditutiaozhuan, tousuLayout, tousuLayout2, anniuLayout, xsls, pptime, kstime, jstime, qxtime, qxyy, relativeLayout;
    private RelativeLayout hdxq_hdz, cg_yd_qx, ts_layout;

    private String inviteId, team, SecondSportId, startTime, playTime, FirstSportId, tagb = "0", feiyongString;
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

    private LinearLayout weixin, pengyouquan, weibo, fb_time, cg_yd_ts;
    private Dialog mCameraDialog;


    private boolean isRun = true;
    private OnResponseListener responseListener;

    private HDXQEntity entity;
    private int flg = 1, hezuoString;

    private RelativeLayout chuli;
    @Override
    public int initContentView() {
        return R.layout.activity_reserve_cg_details;
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
        cdh = findViewById(R.id.home_hdxq_cdh);
        cgh = findViewById(R.id.home_hdxq_cgh);
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
        tousu4 = findViewById(R.id.home_hdxq_tousu2);
        tousu1 = findViewById(R.id.promo_hdxq_text1);
        tousu2 = findViewById(R.id.promo_hdxq_text2);
        tousu3 = findViewById(R.id.promo_hdxq_text3);

      //  tousu6 = findViewById(R.id.promo_hdxq_text5);
       // tousu7 = findViewById(R.id.promo_hdxq_text6);
        tousubiaoti = findViewById(R.id.promo_hdxq_biaoti);

        tousujl = findViewById(R.id.promo_hdxq_jl);

        touxiang = findViewById(R.id.promo_xq_touxiang);
        tousuName = findViewById(R.id.promo_xq_name);
        tousuTime = findViewById(R.id.promo_xq_time);


        tousuLayout2 = findViewById(R.id.promo_hdxq_tousu);
        tousuLayout = findViewById(R.id.home_hdxq_tousu_layout);
        anniuLayout = findViewById(R.id.promo_xq_anniu);


        qxyy = findViewById(R.id.hdxq_qxyy);
        relativeLayout = findViewById(R.id.cg_yd_qx);
        relativeLayout.setOnClickListener(this);

        pptime = findViewById(R.id.hdxq_ppsj);
        kstime = findViewById(R.id.hdxq_kssj);
        jstime = findViewById(R.id.hdxq_jssj);
        qxtime = findViewById(R.id.hdxq_qxsj);
        fb_time = findViewById(R.id.hdxq_qxsj);
        zhuangtai = findViewById(R.id.home_hdxq_image_zhuangtai);
        linearLayout = findViewById(R.id.hdxq_fbsj);
        cg_yd_ts = findViewById(R.id.cg_yd_ts);
        cg_yd_ts.setOnClickListener(this);


        bofang = findViewById(R.id.promo_xq_bofang);
        bofang.setOnClickListener(this);
        cg_yd_ts_text = findViewById(R.id.cg_yd_ts_text);
        ts_layout = findViewById(R.id.ts_layout);

        chuli = findViewById(R.id.promo_xq_chuli);
        chuli.setOnClickListener(this);
        ptjr = findViewById(R.id.promo_xq_ptjr);
        ptjr.setOnClickListener(this);

        bss = findViewById(R.id.promo_xq_bss);
        bss.setOnClickListener(this);
        ss = findViewById(R.id.promo_xq_ss);
        ss.setOnClickListener(this);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid = bundle.getString("uuid");
        tag = bundle.getString("tag");
        isHandle = bundle.getString("isHandle");
        nameString = bundle.getString("name");
        uid = bundle.getString("uid");
        if (tag.equals("2")) {
            anniuLayout.setVisibility(View.GONE);
            chuli.setVisibility(View.VISIBLE);
            tousu2.setVisibility(View.GONE);
        } else {
            chuli.setVisibility(View.GONE);
            if (isHandle.equals("0")) {
                anniuLayout.setVisibility(View.VISIBLE);

            } else {
                anniuLayout.setVisibility(View.GONE);

                tousu2.setVisibility(View.GONE);
            }
        }


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
        Bundle bundle = new Bundle();//传值
        switch (v.getId()) {

            case R.id.promo_xq_tsxq:
                intent.setClass(this, PromoterLSActivity.class);
                bundle.putString("uuid", uuid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.fanhui:
                finish();
                break;
//            case R.id.promo_xq_ptjr:
//                ptjrInit();
//                break;
            case R.id.promo_xq_gd:
                ptjr.setVisibility(View.VISIBLE);
                break;
            case R.id.promo_xq_xsls:
                intent.setClass(this, PromoterLSActivity.class);

                bundle.putString("uuid", uuid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.promo_xq_bss:
                if (jieru == 1) {
                    ToastUitl.longs("平台已介入");
                } else {
                    intent.setClass(this, PromoterSSActivity.class);
                    bundle.putString("tab", "0");

                    bundle.putString("uuid", uuid);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.promo_xq_ss:
                if (jieru == 1) {
                    ToastUitl.longs("平台已介入");
                } else {
                    intent.setClass(this, PromoterSSActivity.class);
                    bundle.putString("tab", "1");

                    bundle.putString("uuid", uuid);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                break;

            case R.id.promo_xq_chuli:
                intent.setClass(this, PromoterCLActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tag", tag);
                bundle.putString("uid", uid);
                bundle.putString("name", nameString);
                bundle.putString("isHandle", isHandle);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.promo_xq_bofang://播放
                RecordingItem recordingItem = new RecordingItem();
////                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
////                final String filePath = sharePreferences.getString("audio_path", "");
////                long elpased = sharePreferences.getLong("elpased", 0);

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(getResources().getString(R.string.imgurl) + luyin);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                long time = mediaPlayer.getDuration();
                LogU.Ld("1608", luyin + "------" + time);
                recordingItem.setFilePath(getResources().getString(R.string.imgurl) + luyin);
                recordingItem.setLength((int) time);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
//                MediaPlayer player  =   new MediaPlayer();
//                String  path   = getResources().getString(R.string.imgurl)+luyin;
//                try {
//                    player.setDataSource(path);
//                    player.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//
//                player.start();
                break;

            case R.id.home_hdxq_gzsm:
                Intent intentgzsm=new Intent();
                intentgzsm.putExtra("uuid",uuid);

                intentgzsm.setClass(ReserveCGDetailsActivity.this,HomeGzsmActivity.class);


                startActivity(intentgzsm);
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
                        LogU.Ld("1608", "项目详情" + "我出错了" + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        LogU.Ld("1608", "场馆详情" + "我出错了" + uuid);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, HDXQEntity.class);
                            reserve_xq_fb_time.setText(entity.getData().getCreateTime());
                            mingcheng.setText(entity.getData().getSiteName());
                            name.setText(entity.getData().getPublishPlayerName());
                            lv.setText(entity.getData().getHeightLevel() + "");
                            Glide.with(ReserveCGDetailsActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);
                            time.setText(entity.getData().getStartDays() + "  " + entity.getData().getStartWeek() + "  " + entity.getData().getStartTimes() + "-" + entity.getData().getEndTimes());
                            timelog.setText(entity.getData().getPlayTime() + "小时");
                            XMid.setText("ID " + entity.getData().getOrderId() + "");
                            qiuleiText.setText(entity.getData().getSportName() + "    " + entity.getData().getSportTypeName());
                            moshi.setText("预定场馆");
                            dizhi.setText(entity.getData().getSiteAddress());
                            dizhiString = entity.getData().getSiteAddress();
                            lat = entity.getData().getSiteLat() + "";
                            lng = entity.getData().getSiteLng() + "";
                            // feiyong.setText(df.format(entity.getData().getSiteMoney()) + "元");
                            feiyong.setText("¥" + entity.getData().getSiteMoney() + "元");
                            hezuoString = entity.getData().getIsCooper();
                            mingcheng.setText(entity.getData().getSiteName());
                            fabutime.setText(entity.getData().getCreateTime() + "");
                            pipeiText.setText(entity.getData().getJoinEndTime() + "");
                            kaishiText.setText(entity.getData().getStartTime() + "");
                            jieshuText.setText(entity.getData().getFinishedTime());
                            quxiaotime.setText(entity.getData().getCancelTime() + "");
                            quxiaoyuanyin.setText(entity.getData().getSuspendReason() + "");
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

                            if (entity.getData().getVenuenumber().equals("0")) {
                                cgh.setVisibility(View.GONE);
                            } else {
                                cgh.setText(entity.getData().getVenuenumber());
                            }
                            if (entity.getData().getVenueid().equals("0")) {
                                cdh.setVisibility(View.GONE);
                            } else {
                                cdh.setText(entity.getData().getVenueid());

                            }
                            yousuYESNO = entity.getData().getGetUserComplaint();
                            tousuString = entity.getData().getComplaint();



                            if (tousuString.equals("1")) {
                                tousuLayout2.setVisibility(View.VISIBLE);

                                anniuLayout.setVisibility(View.GONE);

                                tousuLayout.setVisibility(View.VISIBLE);
//                                    tousu1.setText("投        诉："+entity.getData().getGetUserscomplaint());
                                tousu6.setVisibility(View.VISIBLE);
                                tousu7.setVisibility(View.VISIBLE);
                                tousu6.setText(entity.getData().getGetUsersnickname() + ":" + entity.getData().getUnreserved());
//                                    tousu6.setText(entity.getData().getComplaintistrue());
                                tousu7.setText(entity.getData().getDetailed());
                                tousu1.setVisibility(View.GONE);
                                tousu2.setVisibility(View.GONE);
                                tousu3.setVisibility(View.GONE);
                                tousu4.setVisibility(View.VISIBLE);

                                tousu.setVisibility(View.INVISIBLE);

                                tousuName.setVisibility(View.GONE);
                                tousuTime.setVisibility(View.GONE);
                                touxiang.setVisibility(View.GONE);
                                tousubiaoti.setText("投诉审核结果");

                            } else if (tousuString.equals("2")) {

                                if (entity.getData().getGetUsersComplainIssystem() == 1) {
                                    anniuLayout.setVisibility(View.GONE);
                                } else {
                                    anniuLayout.setVisibility(View.VISIBLE);
                                }
                                tousuLayout2.setVisibility(View.VISIBLE);

                                tousu2.setVisibility(View.GONE);
                              //  cxts.setVisibility(View.GONE);
                                tousu1.setText("反        馈：" + entity.getData().getGetUserscomplaint());


                                tousu.setText("投诉中");
                                ts_layout.setBackgroundResource(R.drawable.tuichu_cg);
                                cg_yd_ts_text.setText("投诉中");
                                tousuTAG = 1;
                            } else if (tousuString.equals("3")) {
                                tousuLayout2.setVisibility(View.VISIBLE);
                                tousu2.setVisibility(View.GONE);
                                anniuLayout.setVisibility(View.GONE);
                                tousu1.setText("投        诉：" + entity.getData().getGetUserscomplaint());
                            } else {
                                tousu1.setText("投        诉：" + entity.getData().getGetUserscomplaint());
                                tousuLayout2.setVisibility(View.VISIBLE);
                                anniuLayout.setVisibility(View.VISIBLE);

                                tousu.setText("投诉中");
                                ts_layout.setBackgroundResource(R.drawable.tuichu_cg);
                                cg_yd_ts_text.setText("投诉中");
                                tousuTAG = 1;
                            }
//                                    if(tousuString.equals("4")){
//                                    tousuLayout2.setVisibility(View.GONE);
//                                    tanhao.setVisibility(View.VISIBLE);
//                                    tousu.setText("投诉");
//                                    tousuTAG = 0;
//                                }




                            tousujl.setText("结论：" + entity.getData().getComplaintistrue());
                            tousu3.setText("详细说明：" + entity.getData().getGetUsersComplainEnd());
                            tousuName.setText(entity.getData().getGetUsersnickname());
                            tousuTime.setText(entity.getData().getGetUserscaddTime());
                            Glide.with(ReserveCGDetailsActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getGetUsersimgURL()).into(touxiang);

                            if (entity.getData().getPublicStatus() == 1) {
                                tousuLayout.setVisibility(View.GONE);
                                tousu.setVisibility(View.GONE);
                                tousu2.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pipeizhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);


                            } else if (entity.getData().getPublicStatus() == 2) {

                                tousu4.setVisibility(View.INVISIBLE);
                                tousu2.setVisibility(View.INVISIBLE);
//                                tousu.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daichufa));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                cg_yd_ts.setVisibility(View.VISIBLE);


//                                qiandao.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 3) {
                                if (!yousuYESNO.equals("3") && tousuString.equals("1")) {
                                    tousuLayout.setVisibility(View.GONE);
                                }



                                tousu4.setVisibility(View.INVISIBLE);
                                tousu2.setVisibility(View.INVISIBLE);

                                // hdxq_hdz.setVisibility(View.VISIBLE);
                                //  qiandao.setVisibility(View.GONE);
//                                tousu.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.huodongzhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                cg_yd_ts.setVisibility(View.VISIBLE);
//                                qiandao.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 4) {

                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);

                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }


                            } else if (entity.getData().getPublicStatus() == 5) {

                                tousu.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiwancheng));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

                                //hdxq_hdz.setVisibility(View.VISIBLE);


                                relativeLayout.setVisibility(View.GONE);
                                tousuLayout.setVisibility(View.GONE);

                            } else if (entity.getData().getPublicStatus() == 6) {
                                tousuLayout.setVisibility(View.GONE);

                                tousu.setVisibility(View.GONE);

                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daipingjia));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);


                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }
                                qxbmText.setText("去评价");


                            } else if (entity.getData().getPublicStatus() == 7) {

                                tousuLayout.setVisibility(View.GONE);

                                tousu.setVisibility(View.GONE);

                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiquxiao));
                                linearLayout.setVisibility(View.VISIBLE);
                                qxtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                qxyy.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);


                            } else if (entity.getData().getPublicStatus() == 8) {

                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.querenjieshu));

                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }

                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(ReserveCGDetailsActivity.this, DengluActivity.class);
                            startActivity(intent);
                        }

                    }
                });
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
}
