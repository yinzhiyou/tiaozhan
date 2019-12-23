package com.example.android.promoter.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
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

/**
 *活动详情
 */
public class HomeHDXQActivity extends BaseActivity implements View.OnClickListener {

    private MyGridView gridViewA, gridViewB, aybgrid, asbgrid, apbgrid,qiquangrid;
    private TextView biaoti, XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, fangshi, time, timelog, sex, dengji, dashang, queren, beizhu,
            daojishi, fabutime, pipeiText, kaishiText, jieshuText, qxbmText, tuichuText, qiandaoText, quxiaotime, quxiaoyuanyin, hezuo, mingcheng, ayingb,
            ashub, apingb, zhuanhuan, yingText, cdf, dsf, tyjb, zyjb, dsftext, qunliao, tousu, tousu2,hdxq_cgqd_text,cdh,cgh,
            tousu1, tousu3, tousu4, tousu5, tousu6, tousu7, tousubiaoti, tousuName, tousuTime, cxts, ty, bty, bofang,qiquan,adWin,bdWin,jgsm_text;
    private int tousuTAG = 0;
    private AyingBAdapter adapter1;
    private AshuBAdapter adapter2;
    private ApingBAdapter adapter3;
    private QquanAdapter adapter4;

    private List<HDXQEntity.DataBean.AwinBuserInfoBean> data1;
    private List<HDXQEntity.DataBean.AloseBuserInfoBean> data2;
    private List<HDXQEntity.DataBean.AdrawBuserInfoBean> data3;
    private List<HDXQEntity.DataBean.GetwaiverInfoBean> data4;


    private ImageView fanhui, fenxiang, fabuzheImage, qiuleiImage, qieleiImage2, zhuangtai, shuaxin, tanhao, touxiang,lan_crown,hong_crown;
    private HDXQAAdapter adapter;
    private HDXQBAdapter adapterb;
    private String tab, uuid, timeString, dizhiString, lat, lng, mylat, mylng, jieguoYN, pingjiaYN, jieshuYN, zhuangtaiString, fangshiString, tuisaiYN,
            tiqianYN, tousuString, yousuYESNO, luyin;
    private LinearLayout linearLayout, ditutiaozhuan, pptime, kstime, jstime, qxtime, qxyy, qiandao, jieguo,
            ABlayout, jbhfy, tousuLayout, tousuLayout2, anniuLayout, xsls,zcrs,gzsm,jgsm;
    private RelativeLayout relativeLayout,hdxq_hdz;
    private List<HDXQEntity.DataBean.TeamABean> data;
    private List<HDXQEntity.DataBean.TeamBBean> datab;
    private int renshu, qiandaoIF, hezuoString, pingjiaTAG;
    private double qian, changdiInt, peilianInt;
    private String token, inviteId, team, SecondSportId, startTime, playTime, FirstSportId, uid, tag = "0", tagb = "0", feiyongString,
            moshiString, dashangString, peilianString, timeRI, timeSHI, sexString, yesORnoMy = "0", bieming, qunliaoString, qunliaoname, yhuuid;
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
    //    private GeocodeResultBean resultBean;
//    private List<GeocodeResultBean.GeocodeBean> locationList;
    private String locationString;
    //    private String lon;
//    private String lat;
    private double mLatitude;
    private double mLongitude;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;
    private ProgressBar bar1, bar2, bar3;
    private LinearLayout weixin, pengyouquan, weibo;
    private Dialog mCameraDialog;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_hdxq);
//    }

    private long mDay = 0, yy = 0;
    private long mHour = 0;
    private long mMin = 0;
    private long mSecond = 0;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    private OnResponseListener responseListener;
    @SuppressLint("HandlerLeak")
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();

                daojishi.setText("截止报名倒计时：" + mDay + "天" + mHour + "小时" + mMin + "分" + mSecond + "秒");
                if (mDay < 0 || mHour < 0 || mMin < 0 || mSecond < 0) {
                    daojishi.setText("报名时间截止");
                    isRun = false;
                }
            }
        }
    };
    private HDXQEntity entity;
    private int flg=1;

    @Override
    public int initContentView() {
        return R.layout.activity_home_hdxq;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        fenxiang = findViewById(R.id.fenxiang);
        fenxiang.setOnClickListener(this);
        gridViewA = findViewById(R.id.home_hdxq_grid_a);
        gridViewB = findViewById(R.id.home_hdxq_grid_b);
        data = new ArrayList<>();
        datab = new ArrayList<>();

        fabuzheImage = findViewById(R.id.home_hdxq_touxiang);
        fabuzheImage.setOnClickListener(this);
        linearLayout = findViewById(R.id.hdxq_fbsj);
        relativeLayout = findViewById(R.id.hdxq_qxbm);
        relativeLayout.setOnClickListener(this);


        XMid = findViewById(R.id.home_hdxq_id);
        qiuleiText = findViewById(R.id.home_hdxq_text_qiulei);
        moshi = findViewById(R.id.home_hdxq_moshi);
        name = findViewById(R.id.home_hdxq_name);
        lv = findViewById(R.id.home_hdxq_lv);
        dizhi = findViewById(R.id.home_hdxq_dizhi);
        feiyong = findViewById(R.id.home_hdxq_feiyong);
        fangshi = findViewById(R.id.home_hdxq_fangshi);
        time = findViewById(R.id.home_hdxq_time);
        timelog = findViewById(R.id.home_hdxq_timelog);
        sex = findViewById(R.id.home_hdxq_sex);
        dengji = findViewById(R.id.home_hdxq_dengji);
        dashang = findViewById(R.id.home_hdxq_dashang);
        queren = findViewById(R.id.home_hdxq_queren);
        beizhu = findViewById(R.id.home_hdxq_beizhu);
        qiuleiImage = findViewById(R.id.home_hdxq_image_qiu);
        qieleiImage2 = findViewById(R.id.home_hdxq_image_qiu2);
        daojishi = findViewById(R.id.daojishi);
        zhuangtai = findViewById(R.id.home_hdxq_image_zhuangtai);
        fabutime = findViewById(R.id.home_hdxq_fabutime);
        pipeiText = findViewById(R.id.home_hdxq_pipeitime);
        kaishiText = findViewById(R.id.home_hdxq_kaishitime);
        jieshuText = findViewById(R.id.home_hdxq_jieshutime);
        quxiaotime = findViewById(R.id.home_hdxq_quxiaoshijian);
        quxiaoyuanyin = findViewById(R.id.home_hdxq_quxiaoyuanyin);
        zhuanhuan = findViewById(R.id.home_hdxq_zhuanhuan);
        yingText = findViewById(R.id.home_hdxq_ying);
        ABlayout = findViewById(R.id.home_hdxq_AB);


        ditutiaozhuan = findViewById(R.id.home_hdxq_ditutiaozhuan);
        ditutiaozhuan.setOnClickListener(this);
        shuaxin = findViewById(R.id.hdxq_shuaxin);
        shuaxin.setOnClickListener(this);

        pptime = findViewById(R.id.hdxq_ppsj);
        kstime = findViewById(R.id.hdxq_kssj);
        jstime = findViewById(R.id.hdxq_jssj);
        qxtime = findViewById(R.id.hdxq_qxsj);
        qxyy = findViewById(R.id.hdxq_qxyy);
        qiandao = findViewById(R.id.hdxq_qiandao);
        qiandao.setOnClickListener(this);

        qxbmText = findViewById(R.id.hdxq_qxbm_text);
        tuichuText = findViewById(R.id.hdxq_dianji_zuo);
        tuichuText.setOnClickListener(this);
        qiandaoText = findViewById(R.id.hdxq_dianji_you);
        qiandaoText.setOnClickListener(this);

        hezuo = findViewById(R.id.home_hdxq_hezuo);
        jieguo = findViewById(R.id.home_hdxq_jieguo);
        mingcheng = findViewById(R.id.home_hdxq_mingcheng);

        ayingb = findViewById(R.id.home_hdxq_AyingB);
        ashub = findViewById(R.id.home_hdxq_AshuB);
        apingb = findViewById(R.id.home_hdxq_ApingB);
        aybgrid = findViewById(R.id.home_hdxq_AyingB_grid);
        asbgrid = findViewById(R.id.home_hdxq_AshuB_grid);
        apbgrid = findViewById(R.id.home_hdxq_ApingB_grid);

        jbhfy = findViewById(R.id.home_hdxq_jbhfy);
        cdf = findViewById(R.id.home_hdxq_cdf);
        dsf = findViewById(R.id.home_hdxq_dsf);
        tyjb = findViewById(R.id.home_hdxq_tyjb);
        zyjb = findViewById(R.id.home_hdxq_zyjb);
        dsftext = findViewById(R.id.home_hdxq_dsf_text);

        qunliao = findViewById(R.id.hdxq_qunliao);
        qunliao.setOnClickListener(this);

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
        tousuLayout = findViewById(R.id.home_hdxq_tousu_layout);

        tousuLayout2 = findViewById(R.id.promo_hdxq_tousu);
        cxts = findViewById(R.id.promo_hdxq_cxts);
        cxts.setOnClickListener(this);
        xsls = findViewById(R.id.promo_hdxq_xsls);
        xsls.setOnClickListener(this);
        anniuLayout = findViewById(R.id.promo_hdxq_anniu);
        ty = findViewById(R.id.promo_hdxq_ty);
        ty.setOnClickListener(this);
        bty = findViewById(R.id.promo_hdxq_bty);
        bty.setOnClickListener(this);
        bofang = findViewById(R.id.promo_xq_bofang);
        bofang.setOnClickListener(this);
        zcrs = findViewById(R.id.home_hdxq_zhichirenshu);
        //后加功能AB皇冠
        hong_crown = findViewById(R.id.hong_crown);
        lan_crown = findViewById(R.id.lan_crown);
        //弃权
        qiquan=findViewById(R.id.home_hdxq_qiquan);
        qiquangrid = findViewById(R.id.home_hdxq_qiquan_grid);

        //AB队
        adWin = findViewById(R.id.ADwin);
        bdWin = findViewById(R.id.BDwin);
//        bar1 = findViewById(R.id.home_hdxq_bar1);
//        bar2 = findViewById(R.id.home_hdxq_bar2);
//        bar3 = findViewById(R.id.home_hdxq_bar3);
        //规则说明
        gzsm = findViewById(R.id.home_hdxq_gzsm);
        gzsm.setOnClickListener(this);
        //结果说明
        jgsm=findViewById(R.id.home_hdxq_jgsm);
        jgsm_text=findViewById(R.id.home_hdxq_jgsm_Text);
        //场馆签到活动中
        hdxq_hdz= findViewById(R.id.hdxq_hdz);
        hdxq_cgqd_text= findViewById(R.id.hdxq_cgqd_text);
        //场地号
        cdh= findViewById(R.id.home_hdxq_cdh);
        cgh= findViewById(R.id.home_hdxq_cgh);
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        data4 = new ArrayList<>();
        adapter1 = new AyingBAdapter(this, data1);
        adapter2 = new AshuBAdapter(this, data2);
        adapter3 = new ApingBAdapter(this, data3);
        adapter4 = new QquanAdapter(this, data4);
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
        fenxiang.setVisibility(View.VISIBLE);

//        gridViewB.setAdapter(adapter);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tab");
        uuid = bundle.getString("uuid");
        yesORnoMy = bundle.getString("fabuzhe");
        LogU.Ld("1608", "活动状态" + tab);
        init();
        startRun();

//        if (tab.equals("1")) {//匹配中
////            timeString = bundle.getString("time");
//            linearLayout.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.VISIBLE);
//            yesORnoMy = bundle.getString("fabuzhe");
//            if (yesORnoMy.equals("1")) {
//                qxbmText.setText("取消发布");
//            }
//
//        } else if (tab.equals("2")) {//待出发
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            qiandao.setVisibility(View.VISIBLE);
//
//        } else if (tab.equals("3")) {//活动中
//
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            kstime.setVisibility(View.VISIBLE);
//            qiandao.setVisibility(View.VISIBLE);
//            tuichuText.setText("中途退赛");
//        } else if (tab.equals("4")) {//确认结果
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            kstime.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.VISIBLE);
////            if (moshiString.equals("2")){
////                qxbmText.setText("填写结果");
////            }else{
////                qxbmText.setText("确认结束");
////            }
//
//        } else if (tab.equals("5")) {//已完成
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            kstime.setVisibility(View.VISIBLE);
//            jstime.setVisibility(View.VISIBLE);
////            if (moshiString.equals("2")){
////               jieguo.setVisibility(View.VISIBLE);
////            }else{
////                jieguo.setVisibility(View.GONE);
////            }
//        } else if (tab.equals("6")) {//待评价
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            kstime.setVisibility(View.VISIBLE);
//            jstime.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.VISIBLE);
//            qxbmText.setText("去评价");
//            qiandao.setVisibility(View.GONE);
////            if (moshiString.equals("2")){
////                jieguo.setVisibility(View.VISIBLE);
////            }else{
////                jieguo.setVisibility(View.GONE);
////            }
//        } else if (tab.equals("7")) {//已取消
//            linearLayout.setVisibility(View.VISIBLE);
//            qxtime.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            qxyy.setVisibility(View.VISIBLE);
//        } else if (tab.equals("0")) {//首页进来
//            qiandao.setVisibility(View.GONE);
//            relativeLayout.setVisibility(View.GONE);
//        } else if (tab.equals("8")) {//确认结果
//            linearLayout.setVisibility(View.VISIBLE);
//            pptime.setVisibility(View.VISIBLE);
//            kstime.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.VISIBLE);
//            qxbmText.setText("确认结束");
////            if (moshiString.equals("2")){
////                qxbmText.setText("填写结果");
////            }else{
////                qxbmText.setText("确认结束");
////            }
//
//        }


    }

    private void initDownload(int tag) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirmButton")
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
                        LogU.Ld("1608", "获取确认按钮" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HQQREntity entity = gson.fromJson(result, HQQREntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (yousuYESNO.equals("3")) {
                                relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                if (entity.getData().getType() == 1) {
                                    qxbmText.setText("确认结束");
                                    zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.querenjieshu));
                                } else if (entity.getData().getType() == 0) {
                                    qxbmText.setText("填写比赛结果");
                                    zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                                } else if (entity.getData().getType() == 2) {
                                    relativeLayout.setVisibility(View.GONE);
                                }
                            } else {
                                if (tousuString.equals("1")) {
                                    relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                    if (entity.getData().getType() == 1) {
                                        qxbmText.setText("确认结束");

                                    } else if (entity.getData().getType() == 0) {
                                        qxbmText.setText("填写比赛结果");
                                    } else if (entity.getData().getType() == 2) {
                                        relativeLayout.setVisibility(View.GONE);
                                    }
                                } else {
                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                    if (entity.getData().getType() == 1) {
                                        qxbmText.setText("确认结束");

                                    } else if (entity.getData().getType() == 0) {
                                        qxbmText.setText("填写比赛结果");
                                    } else if (entity.getData().getType() == 2) {
                                        relativeLayout.setVisibility(View.GONE);
                                    }
                                }


                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

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
            case R.id.fenxiang:
                if (Build.VERSION.SDK_INT >= 23) {

                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};

                    ActivityCompat.requestPermissions(this, mPermissionList, 123);

                }
                setDialog();

                break;
            case R.id.hdxq_shuaxin:
                init();
                break;
            case R.id.home_hdxq_tousu://投诉
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
            case R.id.home_hdxq_touxiang:

                intent.setClass(this, HomeGRTXActivity.class);
                bundle.putString("uid", yhuuid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.hdxq_qxbm:

//                intent.setClass(this, PingjiaActivity.class);
//
//                startActivity(intent);

                if (zhuangtaiString.equals("1")) {//匹配中
                    quxiaobaoming();
                } else if (zhuangtaiString.equals("4")) {//确认结果
                    //填写结果
                    if (yousuYESNO.equals("3") || tousuString.equals("1")) {
                        if (jieguoYN.equals("1")) {
                        } else {
                            anniufanhui();
                        }
                    } else {
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }

                } else if (zhuangtaiString.equals("5")) {//已完成


                } else if (zhuangtaiString.equals("6")) {//待评价
//                    if (pingjiaYN.equals("1")) {
//
//                    } else if (qiandaoIF != 1) {
//
//                    } else if (tuisaiYN.equals("2")) {
//
//                    } else if (tiqianYN.equals("2")) {
//
//                    } else {
                    if (pingjiaTAG == 1) {//1次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        bundle.putString("tag", pingjiaTAG+"");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (pingjiaTAG == 2) {//2次
                        intent.setClass(this, PingjiaTwoActivity.class);
                        bundle.putString("uuid", uuid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }  else if (pingjiaTAG == 0) {//1=2次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        bundle.putString("tag", pingjiaTAG+"");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                    }

//                    }


                } else if (zhuangtaiString.equals("7")) {//已取消

                } else if (zhuangtaiString.equals("0")) {//首页进来

                } else if (zhuangtaiString.equals("8")) {//确认结束
                    if (yousuYESNO.equals("3") || tousuString.equals("1")) {
                        if (jieshuYN.equals("1")) {

                        } else {
                            showNormalDialogZong("确认比赛结束?", zhuangtaiString);
//                        querenjieshu();
                        }
                    } else {
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }


                } else if (zhuangtaiString.equals("11")) {
                    quxiaobaoming();
                }
                break;
            case R.id.home_hdxq_ditutiaozhuan://地图跳转
                if (isApplicationInstall(BAIDU_MAP_APP)) {
//                    goNaviByBaiDuMap(dizhiString);
                    goNaviByBaiDuMap(lat, lng);
                } else if (isApplicationInstall(GAODE_MAP_APP)) {

                }

                break;
            case R.id.hdxq_dianji_zuo://提前退出/中途退赛
                if (yousuYESNO.equals("3")) {
                    if (zhuangtaiString.equals("2")) {//待出发
                        tishiyu();


//                    showNormalDialogZong("提前退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。",zhuangtaiString);
//                    quxiaobaoming();
                        tuichuText.setText("提前退出");
                    } else if (zhuangtaiString.equals("3")) {//活动中
                        showNormalDialogZong("中途退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
//                    zhongtutuisai();
                        tuichuText.setText("中途退出");
                    }
                } else {
                    ToastUitl.longs("投诉中，该功能暂时冻结");
                }

                break;
            case R.id.hdxq_hdz://提前退出/中途退赛
                if (flg==1){
                    ToastUitl.longs("签到");
                    mlocationClient.start();
                    dingwei();
                    hdxq_cgqd_text.setText("中途退赛");
                    flg=2;
                }

                if (yousuYESNO.equals("3")) {
                    if (zhuangtaiString.equals("3")) {//活动中
                        showNormalDialogZong("中途退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
//                    zhongtutuisai();
                        tuichuText.setText("中途退赛");
                    }
                } else {
                    ToastUitl.longs("投诉中，该功能暂时冻结");
                }

                break;
            case R.id.hdxq_dianji_you://签到

                if (tab.equals("2")) {//待出发
                    ToastUitl.longs("签到");
                    mlocationClient.start();
                    dingwei();
                } else if (tab.equals("3")) {//活动中
                    ToastUitl.longs("签到");
                    mlocationClient.start();
                    dingwei();
                }
                break;

            case R.id.cg_fenxiang_weixin://分享微信
                fenxiang();
                ShareUtils.shareWeb(HomeHDXQActivity.this, "http://www.baidu.com", "ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN
                );

                break;
            case R.id.cg_fenxiang_pengyouquan://分享朋友圈
                fenxiang();
                ShareUtils.shareWeb(HomeHDXQActivity.this, "http://www.baidu.com", "ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                );


                break;
            case R.id.cg_fenxiang_weibo://分享微博
                ToastUitl.longs("暂未开放");
            case R.id.hdxq_qunliao://群聊
                bieming = (String) spUtils.get(this, "bieming", "");

                EMClient.getInstance().login(bieming, "tz1", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                EMClient.getInstance().groupManager().loadAllGroups();
                                EMClient.getInstance().chatManager().loadAllConversations();
                                try {
                                    EMClient.getInstance().groupManager().joinGroup(qunliaoString);
                                } catch (HyphenateException e) {
                                    e.printStackTrace();
                                }
                                LogU.Ld("1608", "登录聊天服务器成功！");
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值
                                spUtils.put(HomeHDXQActivity.this, "haoyouname", qunliaoname);
                                intent.setClass(HomeHDXQActivity.this, LiaoTianActivity.class);
                                intent.putExtra(EaseConstant.EXTRA_USER_ID, qunliaoString);  //对方账号
//                                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP); //模式
                                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.GroupChat);
                                bundle.putString("type", "2");
                                intent.putExtras(bundle);
                                startActivity(intent);
//                                根据群组ID从服务器获取群组基本信息
//                                EMGroup group = null;
//                                try {
//                                    group = EMClient.getInstance().groupManager().getGroupFromServer(qunliaoString);
//                                } catch (HyphenateException e) {
//                                    e.printStackTrace();
//                                }
//
//                                group.getOwner();//获取群主
//                                List<String> members = group.getMembers();//获取内存中的群成员
//                                List<String> adminList = group.getAdminList();//获取管理员列表
//                                LogU.Ld("1608",members.toString()+adminList.toString());
//

                            }
                        });
                    }


                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        LogU.Ld("1608", "登录聊天服务器失败！");
                    }
                });

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

            case R.id.promo_hdxq_ty:
                tongyi();

                break;
            case R.id.promo_hdxq_bty:
                ptjrInit();
                break;
            case R.id.home_hdxq_gzsm:
                Intent intentgzsm=new Intent();
                intentgzsm.putExtra("uuid",uuid);

                intentgzsm.setClass(HomeHDXQActivity.this,HomeGzsmActivity.class);

                LogU.Le("规则说明",entity.getData().getProfessionalGoldNotes()+entity.getData().getGetRemarks());
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

    private void fenxiang() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userShare")
                .addHeader("token", token)
                .addParams("type", "activity")
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
//                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
//                        }
                    }
                });

    }

    private void anniufanhui() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirmButton")
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
                        LogU.Ld("1608", "获取确认按钮" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HQQREntity entity = gson.fromJson(result, HQQREntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            if (entity.getData().getType() == 0) {
                                intent.setClass(HomeHDXQActivity.this, HDJGActivity.class);
                                bundle.putString("uuid", uuid);
//                        bundle.putString("duiwu", data.get(i).getTeam() + "");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else if (entity.getData().getType() == 1) {
                                querenjieshu();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                                                        if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
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

    //项目详情
    private void init() {
        LogU.Ld("1608", "项目详情" + uuid);

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        responseListener.onFail(e.getMessage());
                        LogU.Ld("1608", "项目详情" +"我出错了"+e.getMessage());
                    }

                    @Override
                    public void  onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        LogU.Ld("1608", "项目详情" +"我出错了"+uuid);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {

                            Gson gson = new Gson();
                            entity = gson.fromJson(result, HDXQEntity.class);
                            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                            LogU.Le("1608", "项目详情yin" + result);
                            try {
                                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                                Date parse = formatter.parse(entity.getData().getJoinEndTime());
                                yy = parse.getTime() - curDate.getTime();
                                mDay = yy / (1000 * 60 * 60 * 24);
                                mHour = (yy % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                                mMin = (yy % (1000 * 60 * 60)) / (1000 * 60);
                                mSecond = (yy % (1000 * 60)) / 1000;
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            luyin = entity.getData().getGetUsersrecording();
                            if (luyin.length() < 1) {
                                bofang.setVisibility(View.GONE);
                            } else {
                                bofang.setVisibility(View.VISIBLE);
                            }

                            pingjiaTAG = entity.getData().getComment();

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
                            Glide.with(HomeHDXQActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getGetUsersimgURL()).into(touxiang);


                            tuisaiYN = entity.getData().getIsQuitInGame() + "";
                            tiqianYN = entity.getData().getIsQuit() + "";
                            jieshuYN = entity.getData().getIsConfirmOver() + "";
                            // LogU.Ld("1608", "开始时间" + entity.getData().getStartTime() + "天" + mDay + "时" + mHour + "分" + mMin + "秒" + mSecond);


                            LogU.Ld("1608", "活动状态" + tab);
                            hezuoString = entity.getData().getIsCooper();
                            if (entity.getData().getIsCooper() == 1) {
                                hezuo.setVisibility(View.VISIBLE);
                            } else {
                                hezuo.setVisibility(View.GONE);
                            }

                            qiandaoIF = entity.getData().getIsSignIn();
                            if (entity.getData().getIsSignIn() == 1) {
                                qiandaoText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                qiandaoText.setText("已签到");
                            }

                            if (entity.getData().getIsCooper() == 1) {
                                cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    退还" + entity.getData().getGetWalletMoney() + "元    实付" + entity.getData().getGetOutMoney() + "元");

                            } else {
                                if (entity.getData().getIsPublisher() == 1) {
                                    cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    收到其他参与方" + entity.getData().getGetWalletMoney() + "元    须向场馆支付" + entity.getData().getGetOutMoney() + "元");

                                } else {
                                    cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    退还" + entity.getData().getGetWalletMoney() + "元    实付" + entity.getData().getGetOutMoney() + "元");

                                }
                            }

                            if (entity.getData().getSportMode() == 1 || entity.getData().getSportMode() == 2) {
                                dsf.setText(entity.getData().getGetTips() + "元");
                            } else {
                                dsf.setText(entity.getData().getGetMoneyPerhour() + "元");
                            }


                            tyjb.setText(entity.getData().getGetCommonCoins() + "枚");
                            zyjb.setText(entity.getData().getGetCoins() + "分");

                            ayingb.setText("A赢B：" + entity.getData().getAwinBcount() + "人");
                            ashub.setText("A输B：" + entity.getData().getAloseBcount() + "人");
                            apingb.setText("A平B：" + entity.getData().getAdrawBcount() + "人");
                            qiquan.setText("弃权： "+ entity.getData().getGetwaiver() + "人");
                            qunliaoString = entity.getData().getGroupId();

                            qunliaoname = entity.getData().getGroup_name();
                            if (entity.getData().getAwinBcount() > 0) {
                                List<HDXQEntity.DataBean.AwinBuserInfoBean> list1;
                                list1 = entity.getData().getAwinBuserInfo();
                                data1.clear();
                                data1.addAll(list1);
                                aybgrid.setAdapter(adapter1);
                                aybgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        if (data1.size() > position) {

                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data1.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                        } else {

                                            jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                        }

                                    }
                                });
                            }
                            if (entity.getData().getAloseBcount() > 0) {
                                List<HDXQEntity.DataBean.AloseBuserInfoBean> list2;
                                list2 = entity.getData().getAloseBuserInfo();
                                data2.clear();
                                data2.addAll(list2);
                                asbgrid.setAdapter(adapter2);
                                asbgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        if (data2.size() > position) {

                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data2.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                        } else {

                                            jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                        }

                                    }
                                });
                            }
                            if (entity.getData().getAdrawBcount() > 0) {
                                List<HDXQEntity.DataBean.AdrawBuserInfoBean> list3;
                                list3 = entity.getData().getAdrawBcountInfo();
                                data3.clear();
                                data3.addAll(list3);
                                apbgrid.setAdapter(adapter3);
                                apbgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        if (data3.size() > position) {

                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data3.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                        } else {

                                            jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                        }

                                    }
                                });
                            }


                            if (entity.getData().getGetwaiver()>0){
                                List<HDXQEntity.DataBean.GetwaiverInfoBean> list4;
                                list4 = entity.getData().getGetwaiverInfo();
                                data4.clear();
                                data4.addAll(list4);
                                qiquangrid.setAdapter(adapter4);
                                qiquangrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        if (data4.size() > position) {

                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data4.get(position).getUuid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                        } else {

                                            jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                        }

                                    }
                                });

                            }

//                            data2.addAll(list1);
//                            asbgrid.setAdapter(adapter2);
//
//                            data2.addAll(list1);
//                            apbgrid.setAdapter(adapter3);

                            if (entity.getData().getFinalresult() == 1) {
                                //yingText.setText("赢");
                                adWin.setText("赢");
                                bdWin.setText("输");
                                ABlayout.setVisibility(View.VISIBLE);
                                hong_crown.setVisibility(View.VISIBLE);
                                lan_crown.setVisibility(View.GONE);
                                gzsm.setVisibility(View.VISIBLE);
                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                            } else if (entity.getData().getFinalresult() == 2) {
                                // yingText.setText("输");
                                adWin.setText("输");
                                bdWin.setText("赢");
                                gzsm.setVisibility(View.VISIBLE);
                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                ABlayout.setVisibility(View.VISIBLE);

                                hong_crown.setVisibility(View.GONE);
                                lan_crown.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getFinalresult() == 3) {
                                // yingText.setText("平");
                                adWin.setText("平");
                                bdWin.setText("平");
                                gzsm.setVisibility(View.VISIBLE);
                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                ABlayout.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getFinalresult() == 4) {

                                adWin.setText("输");
                                bdWin.setText("输");
                                gzsm.setVisibility(View.VISIBLE);
                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getFinalresult() == 5) {



                                zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());

                            } else if (entity.getData().getFinalresult() == 0) {


                                zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());

                            }

//                            bar1.setProgress(entity.getData().getAwinBcount());
//                            bar2.setProgress(entity.getData().getAloseBcount());
//                            bar3.setProgress(entity.getData().getAdrawBcount());

                            mingcheng.setText(entity.getData().getSiteName());
                            fabutime.setText(entity.getData().getCreateTime()+"");
                            pipeiText.setText(entity.getData().getJoinEndTime()+"");
                            kaishiText.setText(entity.getData().getStartTime()+"");
                            jieshuText.setText(entity.getData().getFinishedTime());
                            quxiaotime.setText(entity.getData().getCancelTime()+"");
                            quxiaoyuanyin.setText(entity.getData().getSuspendReason()+"");
                            //jieshuText.setText(entity.getData().getFinishedTime()+"");


                            timeRI = entity.getData().getStartDays();
                            timeSHI = entity.getData().getStartTimes();
                            sexString = entity.getData().getOpponentsSex() + "";
                            XMid.setText(entity.getData().getOrderId()+"");
                            qiuleiText.setText(entity.getData().getSportName() + "    " + entity.getData().getSportTypeName());
                            zhuangtaiString = entity.getData().getPublicStatus() + "";

                            if (entity.getData().getSportMode() == 1) {
                                dsftext.setText("打赏费    ");
                                moshi.setText("娱乐模式");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            } else if (entity.getData().getSportMode() == 2) {
                                dsftext.setText("打赏费    ");
                                moshi.setText("竞技模式");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 5 || entity.getData().getPublicStatus() == 6) {
                                    jieguo.setVisibility(View.VISIBLE);
                                } else if (entity.getData().getPublicStatus() == 4) {

                                    if (entity.getData().getIsConfirmResult() == 1) {
                                        relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                        qxbmText.setText("已填写结果");
                                    } else {
                                        initDownload(1);
//                                        qxbmText.setText("填写结果");
                                    }

                                }

                            } else if (entity.getData().getSportMode() == 3) {
                                dsftext.setText("陪练费    ");
                                moshi.setText("发布者是陪练");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            } else if (entity.getData().getSportMode() == 4) {
                                dsftext.setText("陪练费    ");
                                moshi.setText("发布者找陪练");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            }
                            DecimalFormat df = new DecimalFormat("0.00");

                            name.setText(entity.getData().getPublishPlayerName());
                            lv.setText(entity.getData().getHeightLevel()+"");
                            Glide.with(HomeHDXQActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);
                            dizhi.setText(entity.getData().getSiteAddress());
                            dizhiString = entity.getData().getSiteAddress();
                            lat = entity.getData().getSiteLat()+"";
                            lng = entity.getData().getSiteLng()+"";
                            feiyong.setText("¥"+entity.getData().getSiteMoney() + "元");
                            feiyongString = entity.getData().getSiteMoney() + "";
                            changdiInt = entity.getData().getSiteMoney();



                            inviteId = entity.getData().getUuid();
                            SecondSportId = entity.getData().getSportType() + "";
                            startTime = entity.getData().getStartTime();
                            playTime = entity.getData().getPlayTime() + "";
                            FirstSportId = entity.getData().getSportId() + "";
                            if (entity.getData().getPaySiteMoneyType() == 1) {
                                fangshi.setText("AA");
                                fangshiString = "1";
                            } else if (entity.getData().getPaySiteMoneyType() == 2) {
                                fangshi.setText("输者买单");
                                fangshiString = "2";
                            }
                            time.setText(entity.getData().getStartDays() + "  " + entity.getData().getStartWeek() + "  " + entity.getData().getStartTimes() + "-" + entity.getData().getEndTimes());
                            timelog.setText(entity.getData().getPlayTime() + "小时");


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
                            LogU.Ld("1608","场地号"+entity.getData().getVenueid());
                            if (entity.getData().getOpponentsSex().equals("0")) {
                                sex.setText("男");
                            } else if (entity.getData().getOpponentsSex().equals("1")) {
                                sex.setText("女");
                            } else {
                                sex.setText("不限");
                            }
                            dengji.setText(entity.getData().getOpponentsLevelMin() + "-" + entity.getData().getOpponentsLevelMax());

                            if (entity.getData().getSportMode() == 3 || entity.getData().getSportMode() == 4) {
                                dashang.setText(df.format(entity.getData().getMoneyPerhour()) + "元");
                                zhuanhuan.setText("陪练费用");
                            } else {
//                                dashang.setText(entity.getData().getTips()/(entity.getData().getNeedNumber()-1) + "元/人");
                                if (entity.getData().getIsPublisher() == 0) {
                                    dashang.setText(entity.getData().getTips() + "元/人");

                                } else {
                                    dashang.setText(entity.getData().getTips() + "元");

                                }

                            }

                            dashangString = entity.getData().getTips() + "";
                            peilianString = entity.getData().getMoneyPerhour() + "";
                            peilianInt = entity.getData().getMoneyPerhour();
                            queren.setText(entity.getData().getLackNumber() + "人");
                            beizhu.setText(entity.getData().getComments());
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


                            if (entity.getData().getHeightLevelName().equals("羽毛球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiu));
                            } else if (entity.getData().getHeightLevelName().equals("乒乓球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangqiu));
                            } else if (entity.getData().getHeightLevelName().equals("台球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiu));
                            } else if (entity.getData().getHeightLevelName().equals("篮球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiu));
                            } else if (entity.getData().getHeightLevelName().equals("足球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiu));
                            } else if (entity.getData().getHeightLevelName().equals("排球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiu));
                            } else if (entity.getData().getHeightLevelName().equals("网球")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiu));
                            } else if (entity.getData().getHeightLevelName().equals("高尔夫")) {
                                qieleiImage2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfu));
                            }


                            if (entity.getData().getPublicStatus() == 1) {
                                tousuLayout.setVisibility(View.GONE);
                                tousu.setVisibility(View.GONE);
                                tousu2.setVisibility(View.GONE);
                                qiandao.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pipeizhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                if (tab.equals("0")) {
                                    relativeLayout.setVisibility(View.GONE);
                                } else {
//                                    if (tab.equals("11")) {
                                    if (entity.getData().getIsPublisher() == 1) {
                                        qxbmText.setText("取消发布");
                                    } else {
                                        qxbmText.setText("取消报名");
                                    }
//                                    }
                                    relativeLayout.setVisibility(View.VISIBLE);
                                }


                            } else if (entity.getData().getPublicStatus() == 2) {
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu4.setVisibility(View.INVISIBLE);
                                tousu2.setVisibility(View.INVISIBLE);
//                                tousu.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daichufa));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                if (tab.equals("0") || tab.equals("11")) {

                                    qiandao.setVisibility(View.GONE);
                                } else {

                                    qiandao.setVisibility(View.VISIBLE);
                                }

                                if (tousuString.equals("1") || tousuString.equals("3") || tousuString.equals("4")) {
                                    if (entity.getData().getIsQuit() == 2) {
                                        tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                        qiandaoText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                    } else {
                                        tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.hongse));

                                    }
                                } else {
                                    tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                }

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
                                //  hdxq_cgqd_text.setText("中途退赛");
                                tuichuText.setText("中途退出");
                                if (tousuString.equals("1") || tousuString.equals("3") || tousuString.equals("4")) {
                                    if (entity.getData().getIsQuitInGame() == 2 || entity.getData().getIsQuit() == 2) {
                                        tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                        qiandaoText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                        qiandaoText.setVisibility(View.GONE);
                                    } else {
                                        tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.hongse));
                                    }
                                } else {
                                    tuichuText.setVisibility(View.GONE);
                                    tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.bbbbb));
                                }


                                if (tab.equals("0") || tab.equals("11")) {

                                    qiandao.setVisibility(View.GONE);
                                } else {

                                    qiandao.setVisibility(View.VISIBLE);
                                }
//                                qiandao.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 4) {
//                                if (yousuYESNO.equals("3")) {
//                                    tousu4.setVisibility(View.VISIBLE);
//                                    tanhao.setVisibility(View.VISIBLE);
//                                } else {
//                                    tousu4.setVisibility(View.INVISIBLE);
//                                    tanhao.setVisibility(View.INVISIBLE);
//                                }

//                                tanhao.setVisibility(View.VISIBLE);
//                                tousu.setVisibility(View.VISIBLE);
//                                tousu2.setVisibility(View.VISIBLE);
//                                qiandao.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);

                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);
                                    qiandao.setVisibility(View.GONE);
                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);
                                    qiandao.setVisibility(View.GONE);
                                }

//                                if(yousuYESNO.equals("3")){
                                if (moshiString.equals("2")) {
                                    if (entity.getData().getIsConfirmResult() == 1) {
                                        jieguoYN = entity.getData().getIsConfirmResult() + "";
                                        relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                        qxbmText.setText("已填写结果");
                                    } else {
                                        jieguoYN = entity.getData().getIsConfirmResult() + "";
//                                        qxbmText.setText("填写结果");
                                        initDownload(1);
                                    }
                                } else {
                                    initDownload(2);
//                                    if (entity.getData().getIsConfirmOver() == 1){
//                                        qxbmText.setText("已确认结束");
//                                    }else{
//                                    qxbmText.setText("确认结束");
//                                    }

                                }


//                                if (entity.getData().getIsConfirmOver() == 1) {
//                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                    qxbmText.setText("已确认结束");
//                                    jieshuYN = entity.getData().getIsConfirmOver() + "";
//                                } else {
//                                    qxbmText.setText("确认结束");
//                                    jieshuYN = entity.getData().getIsConfirmOver() + "";
//                                }
//                                }else{
//                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                }


                            } else if (entity.getData().getPublicStatus() == 5) {
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiwancheng));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);
                                hdxq_hdz.setVisibility(View.VISIBLE);

                                qiandao.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.GONE);
                                tousuLayout.setVisibility(View.GONE);

                            } else if (entity.getData().getPublicStatus() == 6) {
                                tousuLayout.setVisibility(View.GONE);
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                qiandao.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daipingjia));
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
//                                relativeLayout.setVisibility(View.VISIBLE);
                                qiandao.setVisibility(View.GONE);
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);

                                if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);

                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }
                                qxbmText.setText("去评价");

//                                if (entity.getData().getIsComment() == 1) {
//                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                    qxbmText.setText("已评价");
//                                    pingjiaYN = entity.getData().getIsComment() + "";
//                                } else {
//                                    if (entity.getData().getIsSignIn() != 1) {
//                                        relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                        qxbmText.setText("去评价");
//                                        pingjiaYN = entity.getData().getIsComment() + "";
//                                    } else {
//                                        if (entity.getData().getIsQuitInGame() == 2) {
//                                            relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                        }
//                                        qxbmText.setText("去评价");
//                                        pingjiaYN = entity.getData().getIsComment() + "";
//                                    }
//
//                                }

                                if (pingjiaTAG == 1) {
                                    relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                    qxbmText.setText("去评价");

                                } else  if (pingjiaTAG == 2) {
                                    relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                    qxbmText.setText("评价比赛结果");

                                } else if (pingjiaTAG == 0) {
                                    relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                    qxbmText.setText("去评价");

                                } else if (pingjiaTAG == 3) {
                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                    qxbmText.setText("已评价");

                                }else{
                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                    qxbmText.setText("去评价");
                                }
                            } else if (entity.getData().getPublicStatus() == 7) {
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);
                                // jgsm.setVisibility(View.VISIBLE);
                                tousuLayout.setVisibility(View.GONE);
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                qiandao.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiquxiao));
                                linearLayout.setVisibility(View.VISIBLE);
                                qxtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                qxyy.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);


                            } else if (entity.getData().getPublicStatus() == 8) {
//                                if (yousuYESNO.equals("3")) {
//                                    tousu4.setVisibility(View.VISIBLE);
//                                    tanhao.setVisibility(View.VISIBLE);
//                                } else {
//                                    tousu4.setVisibility(View.INVISIBLE);
//                                    tanhao.setVisibility(View.INVISIBLE);
//                                }

//                                tousu.setVisibility(View.VISIBLE);
                                qiandao.setVisibility(View.GONE);
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

//                                if(yousuYESNO.equals("3")){
                                if (entity.getData().getIsConfirmOver() == 1) {
                                    relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                    qxbmText.setText("已确认结束");
                                    jieshuYN = entity.getData().getIsConfirmOver() + "";
                                } else {
//                                        qxbmText.setText("确认结束");
//                                        jieshuYN = entity.getData().getIsConfirmOver() + "";
//                                        relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                    initDownload(2);
                                }

//                                }else{
//                                    if (entity.getData().getIsConfirmOver() == 1) {
//                                        relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                        qxbmText.setText("已确认结束");
//                                        jieshuYN = entity.getData().getIsConfirmOver() + "";
//                                    } else {
//                                        qxbmText.setText("确认结束");
//                                        relativeLayout.setBackgroundResource(R.drawable.renwu_button);
//                                        jieshuYN = entity.getData().getIsConfirmOver() + "";
//                                    }
//
//
//                                }


                            }


                            renshu = entity.getData().getNeedNumber() / 2;
                            LogU.Ld("1608", "AB队人数" + renshu);
                            List<HDXQEntity.DataBean.TeamABean> list;
                            list = entity.getData().getTeamA();
                            data.clear();
                            data.addAll(list);

                            List<HDXQEntity.DataBean.TeamBBean> listb;
                            listb = entity.getData().getTeamB();
                            datab.clear();
                            datab.addAll(listb);

                            LogU.Ld("1608", tag + "data大小" + data.size());
                            for (int i = 0; i < data.size(); i++) {
                                /*if(datab.get(i).getUuid().isEmpty()){
                                    LogU.Le("1068","yin数据为空"+datab.get(i).getUuid());
                                    return;
                                }*/


                                if (data.get(i).getUuid().equals(uid)) {
                                    tag = "1";
                                    break;
                                } else {
                                    tag = "0";
                                }
                            }
                            LogU.Ld("1608", tagb + "datab大小" + data.size());
                            if (datab.size() == 0) {
                                tagb = "0";
                            } else {


                                for (int i = 0; i < datab.size(); i++) {
                                    if(datab.get(i).getUuid()==null||datab.get(i).getUuid()==""){
                                        LogU.Le("1068","数据为空"+datab.get(i).getUuid());
                                        return;

                                    }

                                    if (datab.get(i).getUuid().equals(uid)) {
                                        tagb = "1";
                                        break;
                                    } else {
                                        tagb = "0";
                                    }
                                }
                            }
                            LogU.Ld("1608", tag + tagb);
                            if (tag.equals("1") || tagb.equals("1")) {
                                tag = "1";

                            } else {
                                tag = "0";
                            }

                            if (tag.equals("1")) {
                                if (entity.getData().getNeedNumber() > 2) {
                                    qunliao.setVisibility(View.VISIBLE);
                                } else {
                                    qunliao.setVisibility(View.GONE);
                                }
                            } else {
                                qunliao.setVisibility(View.GONE);
                            }

                            adapter = new HDXQAAdapter(HomeHDXQActivity.this, data, renshu, tag, moshiString, zhuangtaiString, uid);
                            gridViewA.setAdapter(adapter);
                            if (entity.getData().getPublicStatus() == 7) {

                            } else {

                                yhuuid = data.get(0).getUuid();
                                gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        LogU.Ld("1608", "A队点击" + position + data.size());
                                        if (tag.equals("1")) {
//                                            Toast.makeText(HomeHDXQActivity.this, "邀请好友", Toast.LENGTH_SHORT).show();
                                            if (data.size() > position) {
                                                if (data.get(position).getIsSeat() == 1) {

                                                    if (uid.equals(data.get(position).getInvitedByPlayerUUID())) {
                                                        showNormalDialogQX(data.get(position).getUuid(), data.get(position).getNickname());
                                                    } else if (data.get(position).getInvitedByPlayerUUID().equals(uid) && data.get(position).getResult() == 0) {
                                                        showNormalDialogTY(data.get(position).getInvitedByUserName());
                                                    } else {
                                                        intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                        bundle.putString("uid", data.get(position).getUuid() + "");
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);
                                                    }


                                                } else {
                                                    intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                    bundle.putString("uid", data.get(position).getUuid() + "");
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }

                                            } else {
//                                                initDownload(1, "A");
//                                            showNormalDialog(1);
                                                intent.setClass(HomeHDXQActivity.this, YaoqingActivity.class);
                                                bundle.putString("tab", "3");
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("team", "1");
                                                bundle.putString("SecondSportId", SecondSportId);
                                                bundle.putString("FirstSportId", FirstSportId);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        } else {
                                            if (data.size() > position) {

                                                intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                bundle.putString("uid", data.get(position).getUuid() + "");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            } else {

                                                jiance("1");
//                                                initDownload(1, "A");
//                                            showNormalDialog(1);
                                            }

//                                    initjiaru(1);
//                                    startActivity(intent);
                                        }
                                    }
                                });
                            }
                            adapterb = new HDXQBAdapter(HomeHDXQActivity.this, datab, renshu, tag, moshiString, zhuangtaiString, uid);
                            gridViewB.setAdapter(adapterb);
                            if (entity.getData().getPublicStatus() == 7) {

                            } else {

                                gridViewB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
//                                    intent.setClass(HomeHDXQActivity.this,HomeZhifuActivity.class);
                                        if (tag.equals("1")) {
//                                            Toast.makeText(HomeHDXQActivity.this, "邀请好友", Toast.LENGTH_SHORT).show();
                                            if (datab.size() > position) {
                                                if (datab.get(position).getIsSeat() == 1) {
                                                    LogU.Ld("1608", uuid + "--------" + datab.get(position).getInvitedByPlayerUUID());
                                                    if (uid.equals(datab.get(position).getInvitedByPlayerUUID())) {
                                                        showNormalDialogQX(datab.get(position).getUuid(), datab.get(position).getNickname());
                                                    } else if (datab.get(position).getInvitedByPlayerUUID().equals(uid) && datab.get(position).getResult() == 0) {
                                                        showNormalDialogTY(datab.get(position).getInvitedByUserName());
                                                    } else {
                                                        intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                        bundle.putString("uid", datab.get(position).getUuid() + "");
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);
                                                    }


                                                } else {
                                                    intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                    bundle.putString("uid", datab.get(position).getUuid() + "");
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }
                                            } else {
//                                                initDownload(1, "A");
//                                            showNormalDialog(1);
                                                intent.setClass(HomeHDXQActivity.this, YaoqingActivity.class);
                                                bundle.putString("tab", "3");
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("team", "2");
                                                bundle.putString("SecondSportId", SecondSportId);
                                                bundle.putString("FirstSportId", FirstSportId);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        } else {

                                            if (datab.size() > position) {

                                                intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                bundle.putString("uid", datab.get(position).getUuid() + "");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            } else {

                                                jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                            }

//                                    initjiaru(2);
//                                    startActivity(intent);
                                        }
                                    }
                                });

                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }

                        }
                    }
                });
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

    //取消邀请
    private void quxiaoyaoqing(String uuid) {
        LogU.Ld("1608", "取消邀请" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userCancelInvite")
                .addHeader("token", token)
                .addParams("publicUUID", inviteId)
                .addParams("playerUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消邀请" + result);
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

    //加入活动
    private void initjiaru(int team) {
        LogU.Ld("1608", "加入活动" + token + "inviteId" + inviteId + "team" + team + "SecondSportId" + SecondSportId + "startTime" + startTime + "playTime" + playTime + "FirstSportId" + FirstSportId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userSignUp")
                .addHeader("token", token)
                .addParams("inviteId", inviteId)
                .addParams("team", team + "")
                .addParams("SecondSportId", SecondSportId)
                .addParams("startTime", startTime)
                .addParams("playTime", playTime)
                .addParams("FirstSportId", FirstSportId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "加入活动" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            final JiaruEntity entity = gson.fromJson(result, JiaruEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值

                            intent.setClass(HomeHDXQActivity.this, HomeZhifuCGActivity.class);
                            bundle.putString("tag", "2");
                            bundle.putString("uuid", inviteId);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //确认结束
    private void querenjieshu() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "确认结束---" + token + "---publishcId---" + uuid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirm")
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
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MyhuodongActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(HomeHDXQActivity.this,PingjiaActivity.class);
                            bundle.putString("uuid",uuid);
                            bundle.putString("tag","9");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            init();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
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
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(HomeHDXQActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
//                            if (tab.equals("1")) {
                            Intent intent = new Intent();
                            if (tab.equals("11")) {
                                intent.setClass(HomeHDXQActivity.this, MainActivity.class);
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
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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


    //中途退赛
    private void zhongtutuisai() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "中途退赛---" + token + "---publishcId---" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userMidwaySignOut")
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
                        LogU.Ld("1608", "中途退赛" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(HomeHDXQActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
                            init();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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

    //签到
    private void qiandao(String lat, String lng) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "签到---" + token + "---publishcId---" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userArrivalSignin")
                .addHeader("token", token)
                .addParams("publicUid", uuid)
                .addParams("lat", lat)
                .addParams("lng", lng)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "签到" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(HomeHDXQActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
                            init();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //检测信息是否完善
    private void jiance(final String tag) {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserPerfectInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检验资料" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Intent intent = new Intent();
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);

                            if (tag.equals("1")) {
                                initDownload(1, "A");
                            } else {
                                initDownload(2, "B");
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            showNormalDialog();

                        }
                    }
                });

    }


    //提示语
    private void tishiyu() {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTips")
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
                        LogU.Ld("1608", "提示语" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            TishiyuEntity entity = gson.fromJson(result, TishiyuEntity.class);
                            showNormalDialogTQ(entity.getData().getTips(), entity.getData().getSite());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialog();

                        }
                    }
                });

    }

    //提前退出
    private void tiqiantuichu(String type) {
        LogU.Ld("1608", "提前退出");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getmessage")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提前退出" + result);
                        Boolean a = result.indexOf("2000") != -1;

//                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialogZong("提前退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。",zhuangtaiString);
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
////                            showNormalDialog();
//
//                        }
                    }
                });

    }

    private void showNormalDialogTQ(String biaoti, final String biaoti2) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
        normalDialog.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        normalDialog.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        if (biaoti2.length() < 1) {
                            tiqiantuichu("3");
                        } else {
                            showNormalDialogTQ2(biaoti2);
                        }
                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showNormalDialogTQ2(String biaoti) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
        normalDialog.setPositiveButton("预留",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        tiqiantuichu("1");
                    }
                });
        normalDialog.setNegativeButton("未预留",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                        tiqiantuichu("2");

                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showNormalDialogZong(String biaoti, final String tag) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
        normalDialog.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        normalDialog.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        if (tag.equals("1")) {
                            quxiaobaoming();
                        } else if (tag.equals("2")) {
                            quxiaobaoming();
//                            tiqiantuichu();
                        } else if (tag.equals("3")) {
                            zhongtutuisai();
                        } else if (tag.equals("8")) {
                            querenjieshu();
                        }

                    }
                });
        // 显示

        normalDialog.show();
    }


    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("报名和发布活动前，请完善信息");
        normalDialog.setPositiveButton("先看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("去完善",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        intent.setClass(HomeHDXQActivity.this, GRXXActivity.class);
                        bundle.putString("tab", "1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showNormalDialogQX(final String uuid, String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("您确定取消对“" + name + "”的邀请吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        quxiaoyaoqing(uuid);

                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        init();

                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showNormalDialogTY(String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("您接受“" + name + "”对您的邀请吗？？");
        normalDialog.setPositiveButton("接受",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        if (changdiInt > 0) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                            bundle.putString("tag", "3");
                            bundle.putString("uuid", uuid + "");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (moshiString.equals("4") && peilianInt > 0) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                            bundle.putString("tag", "3");
                            bundle.putString("uuid", uuid + "");
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            init("1");
                        }


                    }
                });
        normalDialog.setNegativeButton("拒绝",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                        init("-1");
                    }
                });
        // 显示

        normalDialog.show();
    }

    //活动邀请
    private void init(String confirmRes) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "被邀请用户进行操作" + token + "--publishId--" + uuid + "--confirmRes--" + confirmRes);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/invitedUserHandle")
                .addHeader("token", token)
                .addParams("publishId", uuid)
                .addParams("checkType", confirmRes)
                .addParams("payType", "alipay")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "活动邀请" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {

                            init();
                        } else {
                            init();
                        }
                    }
                });

    }

    private void showNormalDialog(final int i, String dui) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("您确定加入" + dui + "队吗?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        DecimalFormat df = new DecimalFormat("0.00");
                        DecimalFormat ef = new DecimalFormat("0.0000");
                        if (renshu > 2) {
//                            qian = Double.valueOf(feiyongString) / (renshu * 2);
                            qian = Double.valueOf(feiyongString) / (renshu * 2);
                            if (Double.valueOf(df.format(Double.valueOf(feiyongString))) < Double.valueOf(ef.format(Double.valueOf(feiyongString)))) {
                                qian = ((Double.valueOf(feiyongString) / (renshu * 2)) * 100 + 1) / 100;
                            }
                        } else {
                            qian = Double.valueOf(feiyongString);

                        }

                        if (i == 1) {
                            if (feiyongString.equals("0.0")) {//场地费为0
                                if (moshiString.equals("4") && !peilianString.equals("0")) {//加入项目发布者我是陪练，且陪练费不为0
                                    //跳支付

                                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "2");
                                    bundle.putString("inviteId", inviteId);
                                    bundle.putString("team", 1 + "");
                                    bundle.putString("SecondSportId", SecondSportId);
                                    bundle.putString("startTime", startTime);
                                    bundle.putString("playTime", playTime);
                                    bundle.putString("FirstSportId", FirstSportId);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("peilianString", peilianString);
                                    bundle.putString("changdifei", feiyongString);
                                    bundle.putString("fangshi", fangshiString);

                                    bundle.putString("canyurenshu", (renshu * 2) + "");
                                    bundle.putString("hezuo", hezuoString + "");
                                    bundle.putString("moshiString", moshiString + "");
                                    intent.putExtras(bundle);

                                    startActivity(intent);
                                } else {
                                    //不跳
                                    initjiaru(1);
                                }

                            } else {//跳支付


                                dashangString = "0.0";
                                intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                                bundle.putString("tag", "2");
                                bundle.putString("inviteId", inviteId);
                                bundle.putString("team", 1 + "");
                                bundle.putString("SecondSportId", SecondSportId);
                                bundle.putString("startTime", startTime);
                                bundle.putString("playTime", playTime);
                                bundle.putString("FirstSportId", FirstSportId);
                                bundle.putString("dashangString", dashangString);
                                bundle.putString("peilianString", peilianString);
                                bundle.putString("changdifei", df.format(qian) + "");
                                bundle.putString("houtaifei", feiyongString);
                                bundle.putString("fangshi", fangshiString);

                                bundle.putString("canyurenshu", (renshu * 2) + "");
                                bundle.putString("hezuo", hezuoString + "");
                                bundle.putString("moshiString", moshiString + "");
                                intent.putExtras(bundle);

                                startActivity(intent);

                            }
                            //分割线
                        } else {
                            if (feiyongString.equals("0.0")) {//场地费为0
                                if (moshiString.equals("3") && !peilianString.equals("0.0")) {//加入项目发布者我是陪练，且陪练费不为0
                                    //跳支付
                                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "2");
                                    bundle.putString("inviteId", inviteId);
                                    bundle.putString("team", 2 + "");
                                    bundle.putString("SecondSportId", SecondSportId);
                                    bundle.putString("startTime", startTime);
                                    bundle.putString("playTime", playTime);
                                    bundle.putString("FirstSportId", FirstSportId);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("peilianString", peilianString);
                                    bundle.putString("changdifei", feiyongString);
                                    bundle.putString("houtaifei", feiyongString);
                                    bundle.putString("canyurenshu", (renshu * 2) + "");
                                    bundle.putString("hezuo", hezuoString + "");
                                    bundle.putString("moshiString", moshiString + "");
                                    bundle.putString("fangshi", fangshiString);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                } else {
                                    //不跳
                                    initjiaru(2);
                                }

                            } else {//跳支付

                                if (moshiString.equals("4")) {
                                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "2");
                                    bundle.putString("inviteId", inviteId);
                                    bundle.putString("team", 2 + "");
                                    bundle.putString("SecondSportId", SecondSportId);
                                    bundle.putString("startTime", startTime);
                                    bundle.putString("playTime", playTime);
                                    bundle.putString("FirstSportId", FirstSportId);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("peilianString", "0.0");
                                    bundle.putString("changdifei", df.format(qian) + "");
                                    bundle.putString("houtaifei", feiyongString);
                                    bundle.putString("canyurenshu", (renshu * 2) + "");
                                    bundle.putString("hezuo", hezuoString + "");
                                    bundle.putString("moshiString", moshiString + "");
                                    bundle.putString("fangshi", fangshiString);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                } else {
                                    dashangString = "0.0";
                                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                                    bundle.putString("tag", "2");
                                    bundle.putString("inviteId", inviteId);
                                    bundle.putString("team", 2 + "");
                                    bundle.putString("SecondSportId", SecondSportId);
                                    bundle.putString("startTime", startTime);
                                    bundle.putString("playTime", playTime);
                                    bundle.putString("FirstSportId", FirstSportId);
                                    bundle.putString("dashangString", dashangString);
                                    bundle.putString("peilianString", peilianString);
                                    bundle.putString("changdifei", df.format(qian) + "");
                                    bundle.putString("houtaifei", feiyongString);
                                    bundle.putString("canyurenshu", (renshu * 2) + "");
                                    bundle.putString("hezuo", hezuoString + "");
                                    bundle.putString("moshiString", moshiString + "");
                                    bundle.putString("fangshi", fangshiString);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }


                            }
                        }

                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();

    }

    private void initDownload(final int duiwu, final String dui) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间判断token--" + token + "invitedId--" + uid + "startTime--" + timeRI + " --" + timeSHI + "FirstSportId--" + FirstSportId
                + "SecondSportId--" + SecondSportId + "teamSex" + sexString);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkActiveTime")
                .addHeader("token", token)
                .addParams("invitedId", uid)
                .addParams("startTime", timeRI + " " + timeSHI)
                .addParams("FirstSportId", FirstSportId)
                .addParams("playTime", playTime)
                .addParams("SecondSportId", SecondSportId)
                .addParams("teamSex", sexString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogU.Ld("1608", "邀请好友" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "时间判断" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiaruTimeYesorNo entity = gson.fromJson(result, JiaruTimeYesorNo.class);

                            showNormalDialog(duiwu, dui);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

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
                qiandao(mLatitude + "", mLongitude + "");
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

    @Override
    protected void onResume() {
        super.onResume();

        init();
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }
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

//        Intent intent = new Intent();
//        intent.setData(Uri.parse(BAIDU_MAP_NAVI_URI + address));
//        startActivity(intent);

    }

}
