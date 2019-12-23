package com.example.android.promoter.reserve;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.ApingBAdapter;
import com.example.android.promoter.Adapter.AshuBAdapter;
import com.example.android.promoter.Adapter.AyingBAdapter;
import com.example.android.promoter.Adapter.HDXQAAdapter;
import com.example.android.promoter.Adapter.HDXQBAdapter;
import com.example.android.promoter.Adapter.QquanAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.HDXQEntity;
import com.example.android.promoter.Entity.HQQREntity;
import com.example.android.promoter.Entity.JiaruEntity;
import com.example.android.promoter.Entity.JiaruTimeYesorNo;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.TishiyuEntity;
import com.example.android.promoter.Home.HomeGRTXActivity;
import com.example.android.promoter.Home.HomeGzsmActivity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.Home.HomeZhifuActivity;
import com.example.android.promoter.Home.HomeZhifuCGActivity;
import com.example.android.promoter.Home.TousuActivity;
import com.example.android.promoter.Home.YaoqingActivity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.My.Friends.LiaoTianActivity;
import com.example.android.promoter.My.HDJGActivity;
import com.example.android.promoter.My.PingjiaActivity;
import com.example.android.promoter.My.PingjiaTwoActivity;
import com.example.android.promoter.Promoter.PromoterLSActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyGridView;
import com.example.android.promoter.Toos.OnResponseListener;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ShareUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.luyin.PlaybackDialogFragment;
import com.example.android.promoter.Toos.luyin.RecordingItem;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class ReserveCGDetailsActivity extends BaseActivity implements View.OnClickListener {


    private TextView biaoti, XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, time, timelog,  beizhu,  hezuo, mingcheng,cdh,cgh,reserve_xq_fb_time;
    private TextView  fangshi,  sex, dengji, dashang, queren,
            daojishi, fabutime, pipeiText, kaishiText, jieshuText, qxbmText, tuichuText, qiandaoText, quxiaotime, quxiaoyuanyin,  ayingb,
            ashub, apingb, zhuanhuan, yingText, cdf, dsf, tyjb, zyjb, dsftext, qunliao, tousu, tousu2,hdxq_cgqd_text,cg_yd_ts_text,
            tousu1, tousu3, tousu4, tousu5, tousu6, tousu7, tousubiaoti, tousuName, tousuTime, cxts, ty, bty, bofang,qiquan,adWin,bdWin,jgsm_text;
    private int tousuTAG = 0;

    private ImageView fanhui,  fabuzheImage, qiuleiImage, qieleiImage2,zhuangtai,tanhao,touxiang;

    private String tab, uuid, timeString, dizhiString, lat, lng, mylat, mylng,yesORnoMy,tousuString, yousuYESNO;
    private LinearLayout linearLayout, ditutiaozhuan,tousuLayout,tousuLayout2,anniuLayout,xsls,pptime, kstime, jstime, qxtime,qxyy,relativeLayout;
    private RelativeLayout hdxq_hdz,cg_yd_qx,ts_layout;

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

    private LinearLayout weixin, pengyouquan, weibo,fb_time,cg_yd_ts;
    private Dialog mCameraDialog;



    private boolean isRun = true;
    private OnResponseListener responseListener;

    private HDXQEntity entity;
    private int flg=1,hezuoString;


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
        tousu4 = findViewById(R.id.home_hdxq_tousu2);
        tousu1 = findViewById(R.id.promo_hdxq_text1);
        tousu2 = findViewById(R.id.promo_hdxq_text2);
        tousu3 = findViewById(R.id.promo_hdxq_text3);
        tousu5 = findViewById(R.id.promo_hdxq_text4);
        tousu6 = findViewById(R.id.promo_hdxq_text5);
        tousu7 = findViewById(R.id.promo_hdxq_text6);
        tousubiaoti = findViewById(R.id.promo_hdxq_biaoti);
        touxiang = findViewById(R.id.promo_hdxq_touxiang);
        tousuName = findViewById(R.id.promo_hdxq_name);
        tousuTime = findViewById(R.id.promo_hdxq_time);

        tanhao = findViewById(R.id.home_hdxq_tousu_image);
        tousuLayout2 = findViewById(R.id.promo_hdxq_tousu);
        tousuLayout = findViewById(R.id.home_hdxq_tousu_layout);
        anniuLayout = findViewById(R.id.promo_hdxq_anniu);
        touxiang = findViewById(R.id.promo_hdxq_touxiang);
        xsls = findViewById(R.id.promo_hdxq_xsls);
        xsls.setOnClickListener(this);
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
        cg_yd_ts = findViewById(R.id.cg_yd_ts);
        cg_yd_ts.setOnClickListener(this);

        ty = findViewById(R.id.promo_hdxq_ty);
        ty.setOnClickListener(this);
        bty = findViewById(R.id.promo_hdxq_bty);
        bty.setOnClickListener(this);
        bofang = findViewById(R.id.promo_xq_bofang);
        bofang.setOnClickListener(this);
        cg_yd_ts_text = findViewById(R.id.cg_yd_ts_text);
        ts_layout = findViewById(R.id.ts_layout);

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

            case R.id.promo_hdxq_xsls://协商历史
                intent.setClass(this, PromoterLSActivity.class);
                bundle.putString("uuid", uuid);
                intent.putExtras(bundle);
                startActivity(intent);

                break;

            case R.id.promo_hdxq_cxts://撤销投诉
                quxiaotousu();

                break;
            case R.id.cg_yd_qx://取消预定
                quxiaobaoming();

                break;

            case R.id.promo_hdxq_ty:
                tongyi();

                break;
            case R.id.promo_hdxq_bty:
                ptjrInit();
                break;
            case R.id.cg_yd_ts://投诉
                if (tousuTAG == 1) {
                    ToastUitl.longs("等待处理中");
                } else {
                    intent.setClass(this, TousuActivity.class);
                    bundle.putString("uuid", uuid);
                    bundle.putString("hezuo", hezuoString + "");
                    intent.putExtras(bundle);
                    startActivity(intent);

                }


                break;
            case R.id.fanhui:
                if (tab.equals("11")) {
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (tab.equals("0")) {
                    finish();
                } else {
                    finish();
                }


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
                            Glide.with(ReserveCGDetailsActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);
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

                          if (yousuYESNO.equals("1")) {
                                tanhao.setVisibility(View.INVISIBLE);
                                if (tousuString.equals("1")) {
                                    tousuLayout2.setVisibility(View.VISIBLE);

                                    anniuLayout.setVisibility(View.GONE);
                                    xsls.setVisibility(View.VISIBLE);
                                    tousuLayout.setVisibility(View.VISIBLE);
//                                    tousu1.setText("投        诉："+entity.getData().getGetUserscomplaint());
                                    tousu6.setVisibility(View.VISIBLE);
                                    tousu7.setVisibility(View.VISIBLE);
                                    tousu6.setText(entity.getData().getGetUsersnickname()+":"+ entity.getData().getUnreserved());
//                                    tousu6.setText(entity.getData().getComplaintistrue());
                                    tousu7.setText(entity.getData().getDetailed());
                                    tousu1.setVisibility(View.GONE);
                                    tousu2.setVisibility(View.GONE);
                                    tousu3.setVisibility(View.GONE);
                                    tousu4.setVisibility(View.VISIBLE);
                                    tanhao.setVisibility(View.VISIBLE);
                                    tousu.setVisibility(View.INVISIBLE);
                                    tousu5.setVisibility(View.GONE);
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
                                    cxts.setVisibility(View.GONE);
                                    tousu1.setText("反        馈：" + entity.getData().getGetUserscomplaint());
                                    tousu5.setVisibility(View.VISIBLE);
                                    tousu5.setText("结        论：" + entity.getData().getComplaintistrue());
                                    xsls.setVisibility(View.GONE);
                                    tousu.setText("投诉中");
                                    ts_layout.setBackgroundResource(R.drawable.login_rounded_wu);
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
                                    xsls.setVisibility(View.GONE);
                                    ty.setVisibility(View.GONE);
                                    bty.setVisibility(View.GONE);
                                    tousu.setText("投诉中");
                                    ts_layout.setBackgroundResource(R.drawable.login_rounded_wu);
                                    cg_yd_ts_text.setText("投诉中");
                                    tousuTAG = 1;
                                }
//                                    if(tousuString.equals("4")){
//                                    tousuLayout2.setVisibility(View.GONE);
//                                    tanhao.setVisibility(View.VISIBLE);
//                                    tousu.setText("投诉");
//                                    tousuTAG = 0;
//                                }


                            } else if (yousuYESNO.equals("2")) {

                                if (tousuString.equals("1")) {
                                    tousuLayout2.setVisibility(View.VISIBLE);
                                    anniuLayout.setVisibility(View.GONE);
                                    xsls.setVisibility(View.VISIBLE);
                                    tousuLayout.setVisibility(View.VISIBLE);
//                                    tousu1.setText("投        诉："+entity.getData().getGetUserscomplaint());
                                    tousu6.setVisibility(View.VISIBLE);
                                    tousu7.setVisibility(View.VISIBLE);
                                    tousu6.setText(entity.getData().getGetUsersnickname()+":"+ entity.getData().getUnreserved());
//                                    tousu6.setText(entity.getData().getComplaintistrue());
                                    tousu7.setText(entity.getData().getDetailed());
                                    tousu1.setVisibility(View.GONE);
                                    tousu2.setVisibility(View.GONE);
                                    tousu3.setVisibility(View.GONE);
                                    tousu4.setVisibility(View.VISIBLE);
                                    tanhao.setVisibility(View.VISIBLE);
                                    tousu.setVisibility(View.INVISIBLE);
                                    tousu5.setVisibility(View.GONE);
                                    tousuName.setVisibility(View.GONE);
                                    tousuTime.setVisibility(View.GONE);
                                    touxiang.setVisibility(View.GONE);
                                    tousubiaoti.setText("投诉审核结果");
                                } else {
                                    if (tousuString.equals("2")) {
                                        tousu1.setText("反        馈：" + entity.getData().getGetUserscomplaint());
                                    } else {
                                        tousu1.setText("投        诉：" + entity.getData().getGetUserscomplaint());
                                    }

                                    tousuLayout2.setVisibility(View.VISIBLE);
                                    anniuLayout.setVisibility(View.GONE);
                                    tousu.setVisibility(View.VISIBLE);
                                    tousu2.setVisibility(View.GONE);
                                    tanhao.setVisibility(View.INVISIBLE);

                                    tousu.setText("投诉中");
                                    ts_layout.setBackgroundResource(R.drawable.login_rounded_wu);
                                    cg_yd_ts_text.setText("投诉中");
                                    tousuTAG = 1;
                                    xsls.setVisibility(View.GONE);


                                }
                            } else {
                                tousuLayout2.setVisibility(View.GONE);
                                tanhao.setVisibility(View.VISIBLE);
                                tousu4.setVisibility(View.VISIBLE);
                                tousu.setText("投诉");
                                tousuTAG = 0;
                            }


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
                                tanhao.setVisibility(View.INVISIBLE);
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
                                if (!yousuYESNO.equals("3")&&tousuString.equals("1")){
                                    tousuLayout.setVisibility(View.GONE);
                                }



                                tanhao.setVisibility(View.INVISIBLE);
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

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

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




                        }else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            intent.setClass(ReserveCGDetailsActivity.this,DengluActivity.class);
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
                            Toast.makeText(ReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
//                            if (tab.equals("1")) {
                            Intent intent = new Intent();
                            if (tab.equals("11")) {
                                intent.setClass(ReserveCGDetailsActivity.this, MainActivity.class);
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
                            Toast.makeText(ReserveCGDetailsActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
}
