package com.example.android.tiaozhan.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
import com.example.android.tiaozhan.Adapter.ApingBAdapter;
import com.example.android.tiaozhan.Adapter.AshuBAdapter;
import com.example.android.tiaozhan.Adapter.AyingBAdapter;
import com.example.android.tiaozhan.Adapter.CPOverAdapter;
import com.example.android.tiaozhan.Adapter.HDXQAAdapter;
import com.example.android.tiaozhan.Adapter.HDXQBAdapter;
import com.example.android.tiaozhan.Adapter.HDXQCPAdapter;
import com.example.android.tiaozhan.Adapter.QquanAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Denglu.GRXXActivity;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.Entity.HQQREntity;
import com.example.android.tiaozhan.Entity.InitRefereeEntity;
import com.example.android.tiaozhan.Entity.JiaruEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.JudgerefereeJEntity;
import com.example.android.tiaozhan.Entity.RefereeClaimerEntity;
import com.example.android.tiaozhan.Entity.TheBallEntity;
import com.example.android.tiaozhan.Entity.TishiyuEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.My.Friends.LiaoTianActivity;
import com.example.android.tiaozhan.My.HDJGActivity;
import com.example.android.tiaozhan.My.PingjiaActivity;
import com.example.android.tiaozhan.My.referee.MyCwRefereeActivity;
import com.example.android.tiaozhan.My.referee.RefereePerfectXXActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyGridView;
import com.example.android.tiaozhan.Toos.OnResponseListener;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ShareUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 活动详情
 */
public class HomeHDXQActivity extends BaseActivity implements View.OnClickListener {

    private MyGridView gridViewA, gridViewB, gridViewC, aybgrid, asbgrid, apbgrid, qiquangrid, home_hdxq_cp_grid;
    private TextView biaoti, XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, fangshi, time, timelog, sex, dengji, dashang, beizhu, hdxq_dianji_bm,
            daojishi, fabutime, pipeiText, kaishiText, jieshuText, qxbmText, tuichuText, qiandaoText, quxiaotime, quxiaoyuanyin, hezuo, mingcheng, ayingb,
            ashub, apingb, zhuanhuan, yingText, cdf, dsf, tyjb, zyjb, dsftext, qunliao, tousu, hdxq_cgqd_text, cdh, cgh,tousu4,nl_text,xb,nl,jb,cdf_cp_text,cdf_cp_text2,
            tousubiaoti,tsjgText1,tsjgText2,tsjg_cp1,tsjg_cp2,tsjg_pl1,tsjg_pl2, qiquan, adWin, bdWin, jgsm_text, cp_dj, cp_yd;
    private int tousuTAG = 0;
    private AyingBAdapter adapter1;
    private AshuBAdapter adapter2;
    private ApingBAdapter adapter3;
    private QquanAdapter adapter4;
    private CPOverAdapter adapter5;
    private List<HDXQEntity.DataBean.AwinBuserInfoBean> data1;
    private List<HDXQEntity.DataBean.AloseBuserInfoBean> data2;
    private List<HDXQEntity.DataBean.AdrawBuserInfoBean> data3;
    private List<HDXQEntity.DataBean.GetwaiverInfoBean> data4;

    private LinearLayout cdf_cp;
    private RelativeLayout cdf_wu,cdh_layout;
    private List<HDXQEntity.DataBean.GetRefereeResultBean> data5;

    private ImageView fanhui, fenxiang, fabuzheImage, qiuleiImage, qieleiImage2, zhuangtai, shuaxin, tanhao,  lan_crown, hong_crown;
    private HDXQAAdapter adapter;
    private HDXQBAdapter adapterb;
    private HDXQCPAdapter adapterc;
    private String tab, uuid, XXUuid, timeString, dizhiString, lat, lng, mylat, mylng, jieguoYN, pingjiaYN, jieshuYN, zhuangtaiString, fangshiString, tuisaiYN,
            tiqianYN, tousuString, yousuYESNO, luyin;
    private LinearLayout linearLayout, ditutiaozhuan, pptime, kstime, jstime, qxtime, qxyy, qiandao, jieguox,
            ABlayout, jbhfy, tousuLayout,    zcrs, gzsm, jgsm;


    private RelativeLayout relativeLayout, home_hdxq_cp_yd, hdxq_hdz;
    private List<HDXQEntity.DataBean.TeamABean> data;
    private List<HDXQEntity.DataBean.TeamBBean> datab;
    private List<HDXQEntity.DataBean.TeamCBean> datac;
    private int renshu, qiandaoIF, hezuoString, pingjiaTAG;
    private double qian, changdiInt, peilianInt;
    private String token, inviteId, team, SecondSportId, startTime, playTime, FirstSportId, uid, tag = "0", tagb = "0", tagc = "0", feiyongString,
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

    private String locationString;

    private double mLatitude;
    private double mLongitude;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;
    private ProgressBar bar1, bar2, bar3;
    private LinearLayout weixin, pengyouquan, weibo,tousu_jieguo;
    private Dialog mCameraDialog;

    private RelativeLayout cp_layout, promo_hdxq_dt, daojishi_layout;
    private long mDay = 0, yy = 0;
    private long mHour = 0;
    private long mMin = 0;
    private long mSecond = 0;// 天 ,小时,分钟,秒
    private boolean isRun = true, isOver = false;
    private int money4;
    private int money5;
    private int money6;
    private int money7;
    private int money8;
    private int money10;
    private int money9,plnum=0;
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
                    isOver = true;


                }
            }
        }
    };
    private HDXQEntity entity;
    private int flg = 1;
    private String cp_fy;
    private String luyin_cp;
    private String isHandle;
    private String refereeisHandle;
    private String complaint_cp;
    private int sportMode;
    private int refereeNumber;

    private ImageView icon_yd_y, icon_yd_s, icon_yd_p, icon_yd_q;
    private double cpz;
    private int number;
    private Dialog dialog, dialogCP;
    private TextView ds_xz;
    private RefereeClaimerEntity refereeentity;

    private int stasut=4;
    private String ageMin;
    private String ageMaX;
    private DecimalFormat df;
    private String cityName;
    private String nameSport;
    private int sportId;
    private String city;

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
        gridViewC = findViewById(R.id.home_hdxq_grid_c);
        data = new ArrayList<>();
        datab = new ArrayList<>();
        datac = new ArrayList<>();
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
        zhuanhuan.setOnClickListener(this);
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


        qxbmText = findViewById(R.id.hdxq_qxbm_text);
        tuichuText = findViewById(R.id.hdxq_dianji_zuo);
        tuichuText.setOnClickListener(this);
        qiandaoText = findViewById(R.id.hdxq_dianji_you);
        qiandaoText.setOnClickListener(this);

        hezuo = findViewById(R.id.home_hdxq_hezuo);
        jieguox = findViewById(R.id.home_hdxq_jieguo1);
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
        //tousu1 = findViewById(R.id.promo_hdxq_text1);


        tousubiaoti = findViewById(R.id.promo_hdxq_biaoti);





        tsjgText1 = findViewById(R.id.home_xq_tsjg_text1);
        tsjgText2 = findViewById(R.id.home_xq_tsjg_text2);


        tanhao = findViewById(R.id.home_hdxq_tousu_image);
        tousuLayout = findViewById(R.id.home_hdxq_tousu_layout);


        zcrs = findViewById(R.id.home_hdxq_zhichirenshu);
        //后加功能AB皇冠
        hong_crown = findViewById(R.id.hong_crown);
        lan_crown = findViewById(R.id.lan_crown);
        //弃权
        qiquan = findViewById(R.id.home_hdxq_qiquan);
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
        jgsm = findViewById(R.id.home_hdxq_jgsm);
        jgsm_text = findViewById(R.id.home_hdxq_jgsm_Text);

        //裁判比赛结果填写
        home_hdxq_cp_grid = findViewById(R.id.home_hdxq_cp_grid);

        //场馆签到活动中
        hdxq_hdz = findViewById(R.id.hdxq_hdz);
        hdxq_hdz.setOnClickListener(this);
        hdxq_cgqd_text = findViewById(R.id.hdxq_cgqd_text);
        //场地号
        cdh = findViewById(R.id.home_hdxq_cdh);
        cgh = findViewById(R.id.home_hdxq_cgh);
        //裁判
        cp_yd = findViewById(R.id.home_hdxq_cq_yd);
        cp_dj = findViewById(R.id.home_hdxq_cp_dj);
        cp_layout = findViewById(R.id.cp_layout);
        cdf_cp = findViewById(R.id.cdf_cp);
        cdf_wu = findViewById(R.id.cdf_wu);
        cdf_cp_text = findViewById(R.id.cdf_cp_text);
        cdf_cp_text2 = findViewById(R.id.cdf_cp_text2);
        cdh_layout = findViewById(R.id.cdh_layout);
        cdh_layout.setOnClickListener(this);
        //更多
        icon_yd_y = findViewById(R.id.icon_yd_y);
        icon_yd_y.setOnClickListener(this);

        icon_yd_s = findViewById(R.id.icon_yd_s);
        icon_yd_s.setOnClickListener(this);

        icon_yd_p = findViewById(R.id.icon_yd_p);
        icon_yd_p.setOnClickListener(this);

        icon_yd_q = findViewById(R.id.icon_yd_q);
        icon_yd_q.setOnClickListener(this);

        //投诉动态  promo_hdxq_dt
        promo_hdxq_dt = findViewById(R.id.promo_hdxq_dt);
        promo_hdxq_dt.setOnClickListener(this);
        tousu_jieguo = findViewById(R.id.tousu_jieguo);

        //倒计时
        daojishi_layout = findViewById(R.id.daojishi_layout);


        // 年龄
        nl_text = findViewById(R.id.nl_text);

        //性别
        xb = findViewById(R.id.xb);
        xb.setOnClickListener(this);
        nl = findViewById(R.id.nl);
        nl.setOnClickListener(this);
        jb = findViewById(R.id.jb);
        jb.setOnClickListener(this);


        hdxq_dianji_bm = findViewById(R.id.hdxq_dianji_bm);
        hdxq_dianji_bm.setOnClickListener(this);

        home_hdxq_cp_yd = findViewById(R.id.cp_yd);


        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        data4 = new ArrayList<>();
        data5 = new ArrayList<>();
        adapter1 = new AyingBAdapter(this, data1);
        adapter2 = new AshuBAdapter(this, data2);
        adapter3 = new ApingBAdapter(this, data3);
        adapter4 = new QquanAdapter(this, data4);

        adapter5 = new CPOverAdapter(this, data5);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        uid = (String) spUtils.get(this, "uuid", "无");
        mylat = (String) spUtils.get(this, "mylat", "无");
        mylng = (String) spUtils.get(this, "mylng", "无");
        XXUuid = (String) spUtils.get(this, "XXUuid", "无");
        mlocationClient = new LocationClient(this);

        qieleiImage2.setVisibility(View.GONE);
        lv.setVisibility(View.GONE);

    }


    @Override
    protected void initData() {
        LogU.Ld("1609", "报名截止时间走没走");
        if (isOver) {
            LogU.Ld("1609", "报名截止时间走没走");
        }
        biaoti.setText("活动详情");
        fenxiang.setVisibility(View.VISIBLE);

//        gridViewB.setAdapter(adapter);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tab");
        uuid = bundle.getString("uuid");
        XXUuid = bundle.getString("XXUuid");
        yesORnoMy = bundle.getString("fabuzhe");
        LogU.Ld("1608", "活动状态" + tab + uuid);
        init();

        startRun();


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
                                    qxbmText.setText("填写结果");
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
                                        qxbmText.setText("填写结果");
                                    } else if (entity.getData().getType() == 2) {
                                        relativeLayout.setVisibility(View.GONE);
                                    }
                                } else {
                                    relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);
                                    if (entity.getData().getType() == 1) {
                                        qxbmText.setText("确认结束");

                                    } else if (entity.getData().getType() == 0) {
                                        qxbmText.setText("填写结果");
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


            case R.id.xb://
                yaoQXZ_xb();
                break;
            case R.id.nl://
                yaoQXZ_nl();

                break;
            case R.id.jb://
                yaoQXZ_js();

                break;

            case R.id.cdh_layout:
                cdH();
                break;
            case R.id.home_hdxq_zhuanhuan://
                if (zhuanhuan.getText().toString().equals("打赏费用")){
                    yaoQXZ_ds();
                }else {
                    getTheBall();
                    yaoQXZ_pl();
                }

                break;

            case R.id.promo_hdxq_dt://新动态
                intent.setClass(this, HomeTouSuActivity.class);
                bundle.putString("uuid", uuid);

                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.icon_yd_y://更多赢
                intent.setClass(this, BonelwaListYActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tagYd", "1");
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.icon_yd_s://更多输
                intent.setClass(this, BonelwaListYActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tagYd", "2");
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.icon_yd_p://更多平
                intent.setClass(this, BonelwaListYActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tagYd", "0");
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.icon_yd_q://更多弃权

                intent.setClass(this, BonelwaListYActivity.class);
                bundle.putString("uuid", uuid);
                bundle.putString("tagYd", "3");
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
                    } else if (tab.equals("12")) {
                        intent.setClass(this, XiaoxiActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }
                }else {
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


                LogU.Ld("1609", "tousu" + tousuString + complaint_cp + "===" + sportMode + "=====" + refereeNumber);
                if (sportMode == 2 && refereeNumber > 0) {
                    if ((tousuString.equals("0") || tousuString.equals("1") || tousuString.equals("2")) && (complaint_cp.equals("0") || complaint_cp.equals("1") || complaint_cp.equals("2"))) {
                        return;
                    } else {
                        intent.setClass(this, TousuActivity.class);
                        bundle.putString("uuid", uuid);
                        bundle.putString("pltag", "2");
                        bundle.putString("hezuo", hezuoString + "");
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                } else {
                    LogU.Ld("1608", "投诉状态" + tousuString + "===" + complaint_cp + "模式" + moshiString);
                    if (tousuString.equals("0") || tousuString.equals("1") || tousuString.equals("2")) {
                        return;
                    } else {
                        if (sportMode == 4||sportMode==3) {
                            intent.setClass(this, TousuActivity.class);
                            bundle.putString("uuid", uuid);
                            bundle.putString("pltag", "4");
                            bundle.putString("hezuo", hezuoString + "");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            intent.setClass(this, TousuActivity.class);
                            bundle.putString("uuid", uuid);
                            bundle.putString("pltag", "1");
                            bundle.putString("hezuo", hezuoString + "");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    }
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
                    quxiaoCG();

                } else if (zhuangtaiString.equals("4")) {//确认结果
                    //填写结果
                   /* if (jieguoYN.equals("1")) {
                    } else {*/
                    anniufanhui();
                    //  }
                    /*if (yousuYESNO.equals("3") || tousuString.equals("1")) {
                        if (jieguoYN.equals("1")) {
                        } else {
                            anniufanhui();
                        }
                    } else {
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }*/

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
                        bundle.putString("tag", pingjiaTAG + "");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (pingjiaTAG == 2) {//2次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (pingjiaTAG == 0) {//1=2次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        bundle.putString("tag", pingjiaTAG + "");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                    }

//                    }


                } else if (zhuangtaiString.equals("7")) {//已取消

                } else if (zhuangtaiString.equals("0")) {//首页进来

                } else if (zhuangtaiString.equals("8")) {//确认结束
                    if (jieshuYN.equals("1")) {

                    } else {
                        showNormalDialogZong("确认比赛结束?", zhuangtaiString);
//                        querenjieshu();
                    }


                } else if (zhuangtaiString.equals("11")) {
                    quxiaoCG();
                    // quxiaobaoming();
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

                if (zhuangtaiString.equals("2")) {//待出发
                    tishiyu();


//                    showNormalDialogZong("提前退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。",zhuangtaiString);
//                    quxiaobaoming();
                    tuichuText.setText("提前退出");
                } else if (zhuangtaiString.equals("3")) {//活动中
                    showNormalDialogZong("中途退出，您会被记违约次数一次，技术分按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
//                    zhongtutuisai();
                    tuichuText.setText("中途退出");
                }
               /* if (yousuYESNO.equals("3")) {
                    if (zhuangtaiString.equals("2")) {//待出发
                        tishiyu();


//                    showNormalDialogZong("提前退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。",zhuangtaiString);
//                    quxiaobaoming();
                        tuichuText.setText("提前退出");
                    } else if (zhuangtaiString.equals("3")) {//活动中
                        showNormalDialogZong("中途退出，您会被记违约次数一次，对手币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
//                    zhongtutuisai();
                        tuichuText.setText("中途退出");
                    }
                } else {
                    ToastUitl.longs("投诉中，该功能暂时冻结");
                }*/

                break;
            case R.id.hdxq_hdz://提前退出/中途退赛
                LogU.Ld("1608","点击hdxq_hdz签到");
                if (flg == 1) {
                    showNormalDialogZong("中途退出，您会被记违约次数一次，技术分按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
                    /*if (yousuYESNO.equals("3")) {
                        if (zhuangtaiString.equals("3")) {//活动中
                            showNormalDialogZong("中途退出，您会被记违约次数一次，技术分按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);
//                    zhongtutuisai();

                        }
                    } else {
                    if (tousuString.equals("1")||tousuString.equals("3")){
                        showNormalDialogZong("中途退出，您会被记违约次数一次，技术分按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。", zhuangtaiString);

                    }else {

                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }
                    }*/


                } else if (flg == 2) {
                    mlocationClient.start();
                    dingwei();

                }


                break;


            case R.id.hdxq_dianji_you://签到

                LogU.Ld("1608","点击hdxq_dianji_you签到"+tab);
                if (tab.equals("2")) {//待出发

                    mlocationClient.start();
                    dingwei();

                } else if (tab.equals("3")) {//活动中

                    mlocationClient.start();
                    dingwei();

                } else if (tab.equals("1")) {//活动中

                    mlocationClient.start();
                    dingwei();

                }else {
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

           /* case R.id.promo_xq_bofang://播放
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
*/


            case R.id.hdxq_dianji_bm:

                if (zhuangtaiString.equals("1")) {//匹配中
                    //  quxiaobaoming();
                    quxiaoCG();
                } else if (zhuangtaiString.equals("4")) {//确认结果
                    //填写结果
                    if (jieguoYN.equals("1")) {
                    } else {
                        anniufanhui();
                    }
                   /* if (yousuYESNO.equals("3") || tousuString.equals("1")) {
                        if (jieguoYN.equals("1")) {
                        } else {
                            anniufanhui();
                        }
                    } else {
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }*/

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
                        bundle.putString("tag", pingjiaTAG + "");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (pingjiaTAG == 2) {//2次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (pingjiaTAG == 0) {//1=2次
                        intent.setClass(this, PingjiaActivity.class);
                        bundle.putString("uuid", uuid);
                        bundle.putString("tag", pingjiaTAG + "");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                    }

//                    }


                } else if (zhuangtaiString.equals("7")) {//已取消

                } else if (zhuangtaiString.equals("0")) {//首页进来

                } else if (zhuangtaiString.equals("8")) {//确认结束

                    if (jieshuYN.equals("1")) {

                    } else {
                        showNormalDialogZong("确认比赛结束?", zhuangtaiString);
//                        querenjieshu();
                    }
                   /* if (yousuYESNO.equals("3") || tousuString.equals("1")) {
                        if (jieshuYN.equals("1")) {

                        } else {
                            showNormalDialogZong("确认比赛结束?", zhuangtaiString);
//                        querenjieshu();
                        }
                    } else {
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }*/


                } else if (zhuangtaiString.equals("11")) {
                    //   quxiaobaoming();
                    quxiaoCG();
                }
                break;
            case R.id.home_hdxq_gzsm:
                Intent intentgzsm = new Intent();
                intentgzsm.putExtra("uuid", uuid);

                intentgzsm.setClass(HomeHDXQActivity.this, HomeGzsmActivity.class);

                LogU.Le("规则说明", entity.getData().getProfessionalGoldNotes() + entity.getData().getGetRemarks());
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


    //同意处理结果 裁判
    private void tongyiCP() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereeComplainAgree")
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
        LogU.Ld("1608", "项目详情" + "====" + token + "===" + uuid);

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //      responseListener.onFail(e.getMessage());
                      //  ToastUitl.longs("失败原因"+e.getMessage());
                        LogU.Ld("1608", "项目详情===" + "我出错了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        LogU.Ld("1608", "项目详情" + "我出错了" + uuid);
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


                            sportId = entity.getData().getSportId();
                            String sportName = entity.getData().getSportName();

                            city = entity.getData().getSitecity();
                            getTheBall();
                            luyin = entity.getData().getGetUsersrecording();
                            luyin_cp = entity.getData().getGetRefereerecording();




                            pingjiaTAG = entity.getData().getComment();

                            yousuYESNO = entity.getData().getGetUserComplaint();
                            tousuString = entity.getData().getComplaint();
                            complaint_cp = entity.getData().getRefereeComplaint();
                            isHandle = entity.getData().getIsHandle();
                            refereeisHandle = entity.getData().getRefereeisHandle();
                            int getRefereeComplaint = entity.getData().getGetRefereeComplaint();

                            List<HDXQEntity.DataBean.GetRefereeBean> getReferee = entity.getData().getGetReferee();


                            if (!yousuYESNO.equals("3") || getRefereeComplaint != 3) {
                                hdxq_hdz.setBackgroundResource(R.drawable.tuichu_cg);

                            }
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
                            LogU.Ld("1609", "投诉" + tousuString + yousuYESNO);

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
                                qiandaoText.setClickable(false);
                            }

                            if (entity.getData().getIsCooper() == 1) {
                                // cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    退还" + entity.getData().getGetWalletMoney() + "元    实付" + entity.getData().getGetOutMoney() + "元");
                                cdf.setText(entity.getData().getGetlist());
                            } else {
                                if (entity.getData().getIsPublisher() == 1) {
                                    // cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    收到其他参与方" + entity.getData().getGetWalletMoney() + "元    须向场馆支付" + entity.getData().getGetOutMoney() + "元");
                                    cdf.setText(entity.getData().getGetlist());
                                } else {
                                    //  cdf.setText("预付" + entity.getData().getGetSiteMoney() + "元    退还" + entity.getData().getGetWalletMoney() + "元    实付" + entity.getData().getGetOutMoney() + "元");
                                    cdf.setText(entity.getData().getGetlist());
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
                            qiquan.setText("弃权： " + entity.getData().getGetwaiver() + "人");
                            qunliaoString = entity.getData().getGroupId();
                            LogU.Ld("1610", "支持人数" + data1.size() + "===" + data2.size() + "===" + data3.size() + "===" + data4.size());
                            qunliaoname = entity.getData().getGroup_name();
                            if (entity.getData().getAwinBcount() > 0) {
                                List<HDXQEntity.DataBean.AwinBuserInfoBean> list1;
                                list1 = entity.getData().getAwinBuserInfo();
                                data1.clear();
                                data1.addAll(list1);
                                aybgrid.setAdapter(adapter1);

                                if (data1.size() >= 5) {
                                    LogU.Ld("1610", "支持人数" + data1.size());
                                    icon_yd_y.setVisibility(View.VISIBLE);
                                } else {
                                    LogU.Ld("1610", "支持==人数" + data1.size());
                                    icon_yd_y.setVisibility(View.GONE);
                                }
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
                                if (data2.size() >= 5) {
                                    icon_yd_s.setVisibility(View.VISIBLE);
                                } else {
                                    icon_yd_s.setVisibility(View.GONE);
                                }
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
                                if (data3.size() >= 5) {
                                    icon_yd_p.setVisibility(View.VISIBLE);
                                } else {
                                    icon_yd_p.setVisibility(View.GONE);
                                }
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


                            if (entity.getData().getGetwaiver() > 0) {
                                List<HDXQEntity.DataBean.GetwaiverInfoBean> list4;
                                list4 = entity.getData().getGetwaiverInfo();
                                data4.clear();
                                data4.addAll(list4);
                                qiquangrid.setAdapter(adapter4);
                                if (data4.size() >= 5) {
                                    icon_yd_q.setVisibility(View.VISIBLE);
                                } else {
                                    icon_yd_q.setVisibility(View.GONE);
                                }
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


                            if (number > 0) {

                                List<HDXQEntity.DataBean.GetRefereeResultBean> getRefereeResult = entity.getData().getGetRefereeResult();
                                data5.clear();
                                data5.addAll(getRefereeResult);
                                home_hdxq_cp_grid.setAdapter(adapter5);
                                adapter5.setStaust(entity.getData().getPublicStatus());
                                home_hdxq_cp_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        if (data5.size() > position) {

                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                            bundle.putString("uid", data5.get(position).getPlayeruid() + "");
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                        } else {

                                            jiance("2");
//                                                initDownload(2, "B");
//                                            showNormalDialog(2);
                                        }

                                    }
                                });
                                //   refereeResult();

                            }//                            data2.addAll(list1);
//                            asbgrid.setAdapter(adapter2);
//
//                            data2.addAll(list1);
//                            apbgrid.setAdapter(adapter3);

                            LogU.Ld("1606", "这是什么" + entity.getData().getFinalresult());
                            if (entity.getData().getFinalresult() == 1) {
                                //yingText.setText("赢");
                                adWin.setText("赢");
                                bdWin.setText("输");
                                ABlayout.setVisibility(View.VISIBLE);
                                hong_crown.setVisibility(View.VISIBLE);
                                lan_crown.setVisibility(View.GONE);

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                            } else if (entity.getData().getFinalresult() == 2) {
                                // yingText.setText("输");
                                adWin.setText("输");
                                bdWin.setText("赢");
                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                ABlayout.setVisibility(View.VISIBLE);

                                hong_crown.setVisibility(View.GONE);
                                lan_crown.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getFinalresult() == 3) {
                                // yingText.setText("平");
                                adWin.setText("平");
                                bdWin.setText("平");

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                ABlayout.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getFinalresult() == 4) {

                                adWin.setText("输");
                                bdWin.setText("输");

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());
                                //  zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getFinalresult() == 5) {


                                //zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());

                            } else if (entity.getData().getFinalresult() == 0) {


                                // zcrs.setVisibility(View.GONE);
                                ABlayout.setVisibility(View.VISIBLE);

                                jgsm.setVisibility(View.VISIBLE);
                                jgsm_text.setText(entity.getData().getGetexplain());

                            }

//                            bar1.setProgress(entity.getData().getAwinBcount());
//                            bar2.setProgress(entity.getData().getAloseBcount());
//                            bar3.setProgress(entity.getData().getAdrawBcount());

                            mingcheng.setText(entity.getData().getSiteName());
                            fabutime.setText(entity.getData().getCreateTime() + "");
                            pipeiText.setText(entity.getData().getJoinEndTime() + "");
                            kaishiText.setText(entity.getData().getStartTime() + "");
                            jieshuText.setText(entity.getData().getFinishedTime());
                            quxiaotime.setText(entity.getData().getCancelTime() + "");
                            quxiaoyuanyin.setText(entity.getData().getSuspendReason() + "");
                            //jieshuText.setText(entity.getData().getFinishedTime()+"");


                            timeRI = entity.getData().getStartDays();
                            timeSHI = entity.getData().getStartTimes();
                            sexString = entity.getData().getOpponentsSex() + "";
                            XMid.setText(entity.getData().getOrderId() + "");
                            qiuleiText.setText(entity.getData().getSportName() + "    " + entity.getData().getSportTypeName());
                            zhuangtaiString = entity.getData().getPublicStatus() + "";
                            sportMode = entity.getData().getSportMode();

                            LogU.Ld("1606", "竞技模式=======" + entity.getData().getPublicStatus() + "====" + entity.getData().getSportMode());
                            if (entity.getData().getSportMode() == 1) {
                                dsftext.setText("打赏费    ");
                                moshi.setText("娱乐模式");
                                home_hdxq_cp_yd.setVisibility(View.GONE);
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            } else if (entity.getData().getSportMode() == 2) {
                                dsftext.setText("打赏费    ");
                                moshi.setText("竞技模式");
                                home_hdxq_cp_yd.setVisibility(View.VISIBLE);
                                moshiString = entity.getData().getSportMode() + "";

                                if (entity.getData().getPublicStatus() == 5 || entity.getData().getPublicStatus() == 6) {
                                    //jieguo.setVisibility(View.VISIBLE);

                                    jieguox.setVisibility(View.VISIBLE);
                                    LogU.Ld("1606", "=====竞技模式=======" + entity.getData().getPublicStatus());
                                } else if (entity.getData().getPublicStatus() == 4) {

                                    if (entity.getData().getIsSignIn() == 0 || entity.getData().getIsQuit() == 2 || entity.getData().getIsQuitInGame() == 2) {
                                        tanhao.setVisibility(View.GONE);
                                        tousu4.setVisibility(View.GONE);
                                        relativeLayout.setVisibility(View.GONE);
                                        LogU.Ld("1608", "隐藏没");
                                    } else {
                                        if (entity.getData().getIsConfirmResult() == 1) {
                                            LogU.Ld("1608", "隐藏没===");
                                            relativeLayout.setBackgroundResource(R.drawable.renwu_button);
                                            qxbmText.setText("已填写");
                                            tanhao.setVisibility(View.GONE);
                                            tousu4.setVisibility(View.GONE);
                                        } else {
                                            //   initDownload(1);
//                                        qxbmText.setText("填写结果");
                                        }
                                    }
                                }

                            } else if (entity.getData().getSportMode() == 3) {
                                dsftext.setText("陪练费    ");
                                moshi.setText("发布者是陪练");
                                home_hdxq_cp_yd.setVisibility(View.GONE);
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            } else if (entity.getData().getSportMode() == 4) {
                                dsftext.setText("陪练费    ");
                                moshi.setText("发布者找陪练");
                                home_hdxq_cp_yd.setVisibility(View.GONE);
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {
                                    qxbmText.setText("确认结束");
                                }
                            } else {

                                // jieguox.setVisibility(View.GONE);
                            }
                            DecimalFormat df = new DecimalFormat("0.00");

                            name.setText(entity.getData().getPublishPlayerName());
                            lv.setText(entity.getData().getHeightLevel() + "");
                            if (!HomeHDXQActivity.this.isFinishing()) {
                                Glide.with(HomeHDXQActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);

                            }
                            dizhi.setText(entity.getData().getSiteAddress());
                            dizhiString = entity.getData().getSiteAddress();
                            lat = entity.getData().getSiteLat() + "";
                            lng = entity.getData().getSiteLng() + "";
                            double siteMoney = entity.getData().getSiteMoney();
                            double refereeFee = entity.getData().getRefereeFee();
                            String format = String.format("%.2f", siteMoney);
                            String refereeFeeMoney = String.format("%.2f", refereeFee);
                            if (refereeFeeMoney.equals("0.00")) {
                                cdf_wu.setVisibility(View.VISIBLE);
                                cdf_cp.setVisibility(View.GONE);
                                //场地费用
                                feiyong.setText("¥"+format);
                            } else {
                                cdf_cp.setVisibility(View.VISIBLE);
                                cdf_wu.setVisibility(View.GONE);

                                cdf_cp_text.setText("¥"+format );
                                cdf_cp_text2.setText("¥" + refereeFeeMoney);
                            }

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
                                fangshi.setText("输方买单");
                                fangshiString = "2";
                            }
                            time.setText(entity.getData().getStartDays() + "  " + entity.getData().getStartWeek() + "  " + entity.getData().getStartTimes() + "-" + entity.getData().getEndTimes());
                            timelog.setText(entity.getData().getPlayTime() + "小时");
                            //  cp_dj.setText();
                            // cp_yd.setText();
                            ageMin = entity.getData().getAgemin();
                            ageMaX = entity.getData().getAgemax();
                            /*if (entity.getData().getAgemin().equals("0")){
                                ageMin ="不限";
                            }
                            if (entity.getData().getAgemin().equals("0")){
                                ageMaX ="不限";
                            }
                            if (ageMin.equals("不限")&&ageMaX.equals("不限")){
                                nl_text.setText("不限");
                            }else {
                                nl_text.setText(ageMin+"-"+ageMaX);
                            }*/
                            nl_text.setText(entity.getData().getExplain());
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
                            LogU.Ld("1608", "场地号" + entity.getData().getVenueid());
                            if (entity.getData().getOpponentsSex().equals("0")) {
                                sex.setText("男");
                            } else if (entity.getData().getOpponentsSex().equals("1")) {
                                sex.setText("女");
                            } else {
                                sex.setText("不限");
                            }
                          //  dengji.setText(entity.getData().getOpponentsLevelMin() + "-" + entity.getData().getOpponentsLevelMax());
                            dengji.setText(entity.getData().getLeveexplain());
                            if (entity.getData().getSportMode() == 3 || entity.getData().getSportMode() == 4) {
                                dashang.setText("¥"+df.format(entity.getData().getMoneyPerhour())+ "/人");
                                zhuanhuan.setText("陪练费用");
                            } else {
//                                dashang.setText(entity.getData().getTips()/(entity.getData().getNeedNumber()-1) + "元/人");
                                if (entity.getData().getIsPublisher() == 0) {
                                    dashang.setText("¥"+df.format(entity.getData().getTips())+ "/人");
                                    LogU.Ld("1608","打赏费用"+entity.getData().getTips());
                                } else {
                                    dashang.setText("¥"+df.format(entity.getData().getTips())+ "/人");
                                    //dashang.setText("¥"+String.format("%.2f", entity.getData().getTips()));

                                }

                            }

                            dashangString = entity.getData().getTips() + "";
                            peilianString = entity.getData().getMoneyPerhour() + "";
                            peilianInt = entity.getData().getMoneyPerhour();

                            cp_fy = entity.getData().getRefereeFee() + "";
                            double deserved = entity.getData().getDeserved();
                            number = entity.getData().getRefereeNumber();
                            cpz = Double.parseDouble(cp_fy);
                            if (refereeNumber > 0) {
                                home_hdxq_cp_yd.setVisibility(View.VISIBLE);
                                cp_layout.setVisibility(View.VISIBLE);
                            } else {
                                home_hdxq_cp_yd.setVisibility(View.GONE);
                                cp_layout.setVisibility(View.GONE);
                            }
                            String format1 = String.format("%.2f", cpz / number);

                            LogU.Ld("1608", "裁判应得" + "====" + cp_fy + "===" + cpz + "===" + number + "===" + format1);

                            cp_yd.setText("¥"+String.format("%.2f", deserved ) + "/人");
                            cp_dj.setText(entity.getData().getRefereegrade());
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

                              //  tousu2.setVisibility(View.GONE);
                                qiandao.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pipeizhong));
                                linearLayout.setVisibility(View.VISIBLE);
                                hdxq_hdz.setVisibility(View.GONE);
                                LogU.Ld("1608", "签到====状态" + entity.getData().getSigninYes());
                                if (tab.equals("0")) {
                                    relativeLayout.setVisibility(View.GONE);
                                } else {
//                                    if (tab.equals("11")) {

                                    if (entity.getData().getSigninYes() == 1) {
                                        relativeLayout.setVisibility(View.GONE);
                                        qiandao.setVisibility(View.VISIBLE);
                                        tuichuText.setVisibility(View.GONE);
                                        hdxq_dianji_bm.setVisibility(View.VISIBLE);
                                        if (entity.getData().getIsPublisher() == 1) {
                                            hdxq_dianji_bm.setText("取消发布");
                                        } else {
                                            hdxq_dianji_bm.setText("取消报名");
                                        }
                                    } else {
                                        if (entity.getData().getIsPublisher() == 1) {
                                            qxbmText.setText("取消发布");
                                        } else {
                                            qxbmText.setText("取消报名");
                                        }
//                                    }
                                        relativeLayout.setVisibility(View.VISIBLE);
                                        qiandao.setVisibility(View.GONE);
                                    }
                                }


                            } else if (entity.getData().getPublicStatus() == 2) {

                                tanhao.setVisibility(View.INVISIBLE);
                                tousu4.setVisibility(View.INVISIBLE);


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
                                        tuichuText.setClickable(false);
                                        qiandaoText.setClickable(false);
                                    } else {
                                        tuichuText.setBackgroundColor(HomeHDXQActivity.this.getResources().getColor(R.color.hongse));

                                    }
                                } else {

                                }


                            } else if (entity.getData().getPublicStatus() == 3) {
                                if (!yousuYESNO.equals("3") && tousuString.equals("1")) {
                                    tousuLayout.setVisibility(View.GONE);
                                }


                                tanhao.setVisibility(View.INVISIBLE);
                                tousu4.setVisibility(View.INVISIBLE);

                                relativeLayout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.huodongzhong));

                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                qiandao.setVisibility(View.GONE);
                                hdxq_hdz.setVisibility(View.VISIBLE);

                                if (entity.getData().getIsSignIn() == 1) {
                                    if (entity.getData().getIsQuit() == 2) {
                                        hdxq_hdz.setVisibility(View.GONE);
                                    } else {
                                        if (entity.getData().getIsQuitInGame() == 2) {
                                            hdxq_hdz.setVisibility(View.GONE);
                                        } else {
                                            hdxq_hdz.setBackgroundResource(R.drawable.login_rounded_corners);
                                            hdxq_cgqd_text.setText("中途退出");
                                            flg = 1;

                                        }
                                    }

                                } else {

                                    if (entity.getData().getIsQuit() == 2) {
                                        hdxq_hdz.setVisibility(View.GONE);
                                    } else {
                                        hdxq_cgqd_text.setText("场馆签到");
                                        hdxq_hdz.setBackgroundResource(R.drawable.login_rounded_corners);
                                        flg = 2;

                                    }
                                }

                                    if (entity.getData().getEnd() == 2) {

                                        daojishi_layout.setVisibility(View.GONE);
                                        hdxq_hdz.setVisibility(View.GONE);
                                        qiandao.setVisibility(View.GONE);
                                        relativeLayout.setVisibility(View.GONE);

                                    }



                            } else if (entity.getData().getPublicStatus() == 4) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                                tousuLayout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                /*if (tab.equals("0") || tab.equals("11")) {
                                    relativeLayout.setVisibility(View.GONE);
                                    qiandao.setVisibility(View.GONE);
                                } else {
                                    relativeLayout.setVisibility(View.VISIBLE);
                                    qxbmText.setText("填写结果");
                                    relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);
                                    qiandao.setVisibility(View.GONE);
                                }*/


                                if (moshiString.equals("2")) {

                                    if (entity.getData().getIsSignIn() == 0 || entity.getData().getIsQuit() == 2 || entity.getData().getIsQuitInGame() == 2) {
                                        tanhao.setVisibility(View.GONE);
                                        tousu4.setVisibility(View.GONE);
                                        relativeLayout.setVisibility(View.GONE);
                                        tousuLayout.setVisibility(View.GONE);
                                    } else {
                                        if (entity.getData().getIsConfirmResult() == 2) {
                                            relativeLayout.setVisibility(View.GONE);
                                            tousuLayout.setVisibility(View.GONE);
                                            tanhao.setVisibility(View.GONE);
                                            tousu4.setVisibility(View.GONE);
                                        } else if (entity.getData().getIsConfirmResult() == 1) {
                                            relativeLayout.setVisibility(View.VISIBLE);
                                            jieguoYN = entity.getData().getIsConfirmResult() + "";
                                            relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);
                                            qxbmText.setText("已填写");
                                            relativeLayout.setClickable(false);
                                            tanhao.setVisibility(View.GONE);
                                            tousu4.setVisibility(View.GONE);
                                            tousuLayout.setVisibility(View.GONE);
                                        } else {
                                            relativeLayout.setVisibility(View.VISIBLE);
                                            tousuLayout.setVisibility(View.VISIBLE);
                                            tousu.setVisibility(View.GONE);
                                            tousu4.setVisibility(View.VISIBLE);
                                            tousu4.setText("请及时填写比赛结果,填写时间为48小时,该时段如未操作,视为放弃。");
                                            qxbmText.setText("填写结果");
                                            relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);

                                        }
                                    }

                                } else {


                                }


                            } else if (entity.getData().getPublicStatus() == 5) {
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiwancheng));
                                daojishi_layout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);
                                hdxq_hdz.setVisibility(View.GONE);

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

                                qiandao.setVisibility(View.GONE);
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);

                                    LogU.Ld("1608", "评价====" + entity.getData().getComment());
                                    if (entity.getData().getComment() == 1) {
                                        relativeLayout.setBackgroundResource(R.drawable.tuichu_cg);
                                        relativeLayout.setVisibility(View.VISIBLE);
                                        qxbmText.setText("已评价");
                                        relativeLayout.setClickable(false);

                                    } else if (entity.getData().getComment() == 0) {
                                        relativeLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                        qxbmText.setText("待评价");
                                        relativeLayout.setVisibility(View.VISIBLE);
                                    } else {
                                        relativeLayout.setVisibility(View.GONE);
                                    }



                            } else if (entity.getData().getPublicStatus() == 7) {
                                jbhfy.setVisibility(View.VISIBLE);
                                gzsm.setVisibility(View.VISIBLE);
                                tousuLayout.setVisibility(View.GONE);
                                tanhao.setVisibility(View.INVISIBLE);
                                tousu.setVisibility(View.GONE);
                                qiandao.setVisibility(View.GONE);
                                daojishi_layout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiquxiao));
                                linearLayout.setVisibility(View.VISIBLE);
                                qxtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                qxyy.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);


                            }else if (entity.getData().getPublicStatus() == 9) {

                                tanhao.setVisibility(View.INVISIBLE);
                                tousu4.setVisibility(View.INVISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.icon_tousuzhong));

                                linearLayout.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                qiandao.setVisibility(View.GONE);
                                hdxq_hdz.setVisibility(View.VISIBLE);
                                if (entity.getData().getEnd()==2){
                                    daojishi_layout.setVisibility(View.GONE);
                                    hdxq_hdz.setVisibility(View.GONE);
                                    qiandao.setVisibility(View.GONE);
                                    relativeLayout.setVisibility(View.GONE);
                                }else {
                                    if (entity.getData().getIsSignIn() == 1) {
                                        if (entity.getData().getIsQuit() == 2) {
                                            hdxq_hdz.setVisibility(View.GONE);
                                        } else {
                                            if (entity.getData().getIsQuitInGame() == 2) {
                                                hdxq_hdz.setVisibility(View.GONE);
                                            } else {
                                                hdxq_hdz.setBackgroundResource(R.drawable.login_rounded_corners);
                                                hdxq_cgqd_text.setText("中途退出");
                                                flg = 1;
                                            }
                                        }

                                    } else {

                                            if (entity.getData().getIsQuit() == 2) {
                                                hdxq_hdz.setVisibility(View.GONE);
                                            } else {
                                                hdxq_cgqd_text.setText("场馆签到");
                                                hdxq_hdz.setBackgroundResource(R.drawable.login_rounded_corners);
                                                flg = 2;
                                            }

                                    }
                                }



                                LogU.Ld("1608", "投诉" + tousuString + "==" + entity.getData().getIsQuitInGame() + "==" + entity.getData().getIsQuit());



                            }


                            LogU.Ld("1608", "AB队人数时间结束了" + entity.getData().getEnd());
                            renshu = entity.getData().getNeedNumber() / 2;
                            refereeNumber = entity.getData().getRefereeNumber();
                            LogU.Ld("1608", "AB队人数" + renshu);
                            List<HDXQEntity.DataBean.TeamABean> list;
                            list = entity.getData().getTeamA();
                            data.clear();
                            data.addAll(list);

                            List<HDXQEntity.DataBean.TeamBBean> listb;
                            listb = entity.getData().getTeamB();
                            datab.clear();
                            datab.addAll(listb);

                            List<HDXQEntity.DataBean.TeamCBean> listc;
                            listc = entity.getData().getTeamC();
                            datac.clear();
                            datac.addAll(listc);


                            LogU.Ld("1608", tag + "data大小" + data.size());
                            for (int i = 0; i < data.size(); i++) {

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
                                    if (datab.get(i).getUuid() == null || datab.get(i).getUuid() == "") {
                                        LogU.Le("1068", "数据为空" + datab.get(i).getUuid());
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
                            LogU.Ld("1608", tagb + "datac大小" + datac.size());
                            if (datac.size() == 0) {
                                tagc = "0";
                            } else {


                                for (int i = 0; i < datac.size(); i++) {
                                    if (datac.get(i).getUuid() == null || datac.get(i).getUuid() == "") {
                                        LogU.Le("1068", "数据为空" + datab.get(i).getUuid());
                                        return;

                                    }

                                    if (datac.get(i).getUuid().equals(uid)) {
                                        tagc = "1";
                                        break;
                                    } else {
                                        tagc = "0";
                                    }
                                }
                            }


                            LogU.Ld("1608", tag + tagb);
                            if (tag.equals("1") || tagb.equals("1") || tagc.equals("1")) {
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
                                        if (!EmptyUtils.isStrEmpty(token)) {
                                            Bundle bundle = new Bundle();

                                            if (tag.equals("1")) {
//                                            Toast.makeText(HomeHDXQActivity.this, "邀请好友", Toast.LENGTH_SHORT).show();
                                                if (data.size() > position) {
                                                    LogU.Ld("1608", "A队点击" + position + data.size() + data.get(position).getIsSeat() + "===" + data.get(position).getInvitedByPlayerUUID() + "===" + data.get(position).getResult());


                                                    if (uid.equals(data.get(position).getInvitedByPlayerUUID() + "")) {
                                                        if (data.get(position).getIsSeat() == 1) {
                                                            showNormalDialogQX(data.get(position).getUuid(), data.get(position).getNickname());
                                                        } else {
                                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                            bundle.putString("uid", data.get(position).getUuid() + "");
                                                            intent.putExtras(bundle);
                                                            startActivity(intent);
                                                        }
                                                    } else {
                                                        LogU.Ld("1608", "A队===点击" + data.get(position).getUuid() + "====" + data.get(position).getResult());

                                                        if (uid.equals(data.get(position).getUuid())) {
                                                            if (data.get(position).getIsSeat() == 1) {
                                                                if (!uid.equals(data.get(position).getInvitedByPlayerUUID()) && !data.get(position).getInvitedByPlayerUUID().equals("0") && data.get(position).getResult() != 1) {
                                                                    LogU.Ld("1608", "A队===点击" + data.get(position).getUuid() + "====" + data.get(position).getResult() + "=====" + data.get(position).getIsSeat());
                                                                    showNormalDialogTY(data.get(position).getInvitedByUserName());
                                                                } else {
                                                                    intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                                    bundle.putString("uid", data.get(position).getUuid() + "");
                                                                    intent.putExtras(bundle);
                                                                    startActivity(intent);
                                                                }
                                                            }
                                                        } else {
                                                            intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                            bundle.putString("uid", data.get(position).getUuid() + "");
                                                            intent.putExtras(bundle);
                                                            startActivity(intent);
                                                        }
                                                    }


                                                } else {

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

                                                }

                                            }
                                        }else {
                                            //  Intent intent = new Intent();
                                            intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                            startActivity(intent);
                                        }

                                    }
                                });
                            }
                            adapterb = new HDXQBAdapter(HomeHDXQActivity.this, datab, renshu, tag, moshiString, zhuangtaiString, uid);
                            gridViewB.setAdapter(adapterb);

                            adapterc = new HDXQCPAdapter(HomeHDXQActivity.this, datac, refereeNumber, tag, moshiString, zhuangtaiString, uid);
                            gridViewC.setAdapter(adapterc);

                            //裁判点击
                            if (entity.getData().getPublicStatus() == 7) {

                            } else {

                                gridViewC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        LogU.Ld("1608", "C队点击" + "tag===" + tag + "===" + position + data.size());

                                        if (tag.equals("1")) {

                                            if (datac.size() > position) {
                                                LogU.Ld("1608", "C队点击" + position + data.size() + "是否站位" + datac.get(position).getIsSeat() + "=uid=" + uid + "====" + datac.get(position).getInvitedByPlayerUUID() + datac.get(position).getResult() + "===" + datac.get(position).getInvitedByPlayerUUID());

                                                if (datac.get(position).getIsSeat() == 1) {
                                                    if (uid.equals(datac.get(position).getInvitedByPlayerUUID())) {
                                                        showNormalDialogQX(datac.get(position).getUuid(), datac.get(position).getNickname());
                                                    } else if (!datac.get(position).getInvitedByPlayerUUID().equals(uid) && datac.get(position).getResult() == 0) {
                                                        showNormalDialogTY(datac.get(position).getInvitedByUserName());
                                                    } else {
                                                        intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                        bundle.putString("uid", datac.get(position).getUuid() + "");
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);
                                                    }

                                                } else {
                                                    intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                    bundle.putString("uid", datac.get(position).getUuid() + "");
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }
                                            }
                                        } else {

                                            if (datac.size() > position) {

                                                intent.setClass(HomeHDXQActivity.this, HomeGRTXActivity.class);
                                                bundle.putString("uid", datac.get(position).getUuid() + "");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            } else {
                                                LogU.Ld("1608","用户退出"+token);
                                                if (!EmptyUtils.isStrEmpty(token)) {
                                                    jianceCP("3");
                                                }else {
                                                  //  Intent intent = new Intent();
                                                    intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                                    startActivity(intent);
                                                }

                                            }

                                        }
                                    }
                                });

                            }


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
                                                    LogU.Ld("1608", uuid + "--------" + datab.get(position).getInvitedByPlayerUUID() + "===" + datab.get(position).getResult() + "=====" + uid);
                                                    if (uid.equals(datab.get(position).getInvitedByPlayerUUID())) {
                                                        showNormalDialogQX(datab.get(position).getUuid(), datab.get(position).getNickname());
                                                    } else if (!datab.get(position).getInvitedByPlayerUUID().equals(uid) && datab.get(position).getResult() == 0) {
                                                        showNormalDialogTY(datab.get(position).getInvitedByUserName());
                                                    } else {
                                                        LogU.Ld("1608", "邀请--------" + datab.get(position).getInvitedByPlayerUUID());
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
                                                LogU.Ld("1608","用户退出"+token);
                                                if (!EmptyUtils.isStrEmpty(token)) {
                                                    jiance("2");
                                                }else {
                                                    intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                                    startActivity(intent);
                                                }
                                            }


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


    //取消投诉裁判
    private void quxiaotousuCP() {
        LogU.Ld("1608", "取消投诉" + token + "====" + inviteId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereeCancellationOfcomplaint")
                .addHeader("token", token)
                .addParams("publicUUID", inviteId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    //    ToastUitl.longs("失败原因"+e.getMessage());
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
                    //    ToastUitl.longs("失败原因"+e.getMessage());
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
                .addParams("payType", "balance")
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
                            intent.setClass(HomeHDXQActivity.this, PingjiaActivity.class);
                            bundle.putString("uuid", uuid);
                            bundle.putString("tag", "9");
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
                    //    ToastUitl.longs("失败原因"+e.getMessage());
                        LogU.Ld("1608", "取消报名失败" + e.getMessage());
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
                            //                          Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();


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
                            qiandaoCG();
                            hdxq_cgqd_text.setText("中途退出");
                            flg = 1;
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


    //检测信息是否完善
    private void jianceCP(final String tag) {
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
                            //      JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Intent intent = new Intent();
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
                            initDownloadCP(tag);
                            //   showCPDialog();


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
                        if (a) {
                            init();
                            //    tuichuText.setClickable(false);
                            //  qiandaoText.setClickable(false);
                        }

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

        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);
        ds_xz.setText("");

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

                if (biaoti2.length() < 1) {
                    tiqiantuichu("3");
                } else {
                    showNormalDialogTQ2(biaoti2);
                };

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private void showNormalDialogTQ2(String biaoti) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo);
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
        normalDialog.setIcon(R.mipmap.logo);
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
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("为了您更方便发布和报名活动，请您完善您的个人信息");

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

        icon_que.setText("去完善");
        icon_close.setText("先看看");
        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(HomeHDXQActivity.this, GRXXActivity.class);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);

                startActivity(intent);

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

       /* final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeHDXQActivity.this);
        normalDialog.setIcon(R.mipmap.logo);
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

        normalDialog.show();*/
    }

    private void showNormalDialogQX(final String uuid, String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */




        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您确定取消邀请"+ name + "参加此活动么?");

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

                quxiaoyaoqing(uuid);
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void showNormalDialogTY(String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */

        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您接受“" + name + "”对您的邀请吗？");

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

                if (changdiInt > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();//传值
                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                    bundle.putString("tag", "3");
                    bundle.putString("uuid", uuid + "");
                    bundle.putString("XXUuid", XXUuid + "");
                    bundle.putString("inviteId", inviteId);
                    // bundle.putString("team", 1 + "");
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
                    bundle.putString("cp_fy", cp_fy);
                    bundle.putString("cp_rs", refereeNumber+"");
                    bundle.putString("shichang", entity.getData().getPlayTime());

                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (moshiString.equals("4") && peilianInt > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();//传值
                    intent.setClass(HomeHDXQActivity.this, HomeZhifuActivity.class);
                    bundle.putString("tag", "3");
                    bundle.putString("uuid", uuid + "");
                    bundle.putString("inviteId", inviteId);
                    // bundle.putString("team", 1 + "");
                    bundle.putString("SecondSportId", SecondSportId);
                    bundle.putString("startTime", startTime);
                    bundle.putString("playTime", playTime);
                    bundle.putString("FirstSportId", FirstSportId);
                    bundle.putString("dashangString", dashangString);
                    bundle.putString("peilianString", peilianString);
                    bundle.putString("changdifei", feiyongString);
                    bundle.putString("fangshi", fangshiString);
                    bundle.putString("cp_rs", refereeNumber+"");
                    bundle.putString("canyurenshu", (renshu * 2) + "");
                    bundle.putString("hezuo", hezuoString + "");
                    bundle.putString("moshiString", moshiString + "");
                    bundle.putString("cp_fy", cp_fy);
                    bundle.putString("shichang", entity.getData().getPlayTime());

                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    init("1");
                }

                dialog.dismiss();
            }
        });
        icon_close.setText("拒绝");
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init("-1");
                dialog.dismiss();
            }
        });


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
                .addParams("uuid", XXUuid)
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


        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        if (entity.getData().getSportMode() == 3){
            LogU.Ld("1608","队伍"+i);
            ds_xz.setText("您确定报名练习吗?");
        }else if (entity.getData().getSportMode() == 4){
            ds_xz.setText("您确定报名陪练方吗?");
        }else {
            ds_xz.setText("您确定加入"+dui+"队吗?");
        }


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


                df = new DecimalFormat("0.00");
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

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                if (i == 1) {
                    if (feiyongString.equals("0.0")) {//场地费为0
                        if (moshiString.equals("4") && !peilianString.equals("0.0")) {//加入项目发布者我是陪练，且陪练费不为0
                            //跳支付
                            cp_fy = "0";
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
                            bundle.putString("cp_fy", cp_fy);
                            bundle.putString("cp_rs", refereeNumber+"");
                            bundle.putString("shichang", entity.getData().getPlayTime());
                            intent.putExtras(bundle);

                            LogU.Ld("1608","裁判人数"+refereeNumber);
                            startActivity(intent);
                        } else {
                            //不跳
                            initjiaru(1);
                        }

                    } else {//跳支付

                        LogU.Ld("1608", "报名走哪" + entity.getData().getRefereeFee()+"==="+cp_fy);
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
                        bundle.putString("cp_fy", entity.getData().getRefereeFee() + "");
                        bundle.putString("cp_dj", entity.getData().getRefereegrade());
                        //  bundle.putString("cp_rs", entity.getData().getRefereeFee());
                        bundle.putString("canyurenshu", (renshu * 2) + "");
                        bundle.putString("hezuo", hezuoString + "");
                        bundle.putString("moshiString", moshiString + "");
                        bundle.putString("cp_rs", refereeNumber+"");
                        bundle.putString("shichang", entity.getData().getPlayTime());
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                    //分割线
                } else {
                    LogU.Ld("1608", "报名走哪1" + entity.getData().getRefereeFee());
                    if (feiyongString.equals("0.0")) {//场地费为0
                        if (moshiString.equals("3") && !peilianString.equals("0.0")) {//加入项目发布者我是陪练，且陪练费不为0
                            //跳支付
                            cp_fy = "0";
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
                            bundle.putString("cp_fy", cp_fy);
                            bundle.putString("shichang", entity.getData().getPlayTime());
                            bundle.putString("cp_rs", refereeNumber+"");
                            intent.putExtras(bundle);
                            LogU.Ld("1608", "报名走哪2" + entity.getData().getRefereeFee());
                            startActivity(intent);
                        } else {
                            //不跳
                            LogU.Ld("1608", "报名走哪3" + entity.getData().getRefereeFee());
                            initjiaru(2);
                        }

                    } else {//跳支付
                        LogU.Ld("1608", "报名走哪4" + entity.getData().getRefereeFee());
                        if (moshiString.equals("4")) {
                            cp_fy = "0";
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
                            bundle.putString("cp_fy", cp_fy);
                            bundle.putString("number", number+"");
                            bundle.putString("cp_rs", refereeNumber+"");
                            bundle.putString("shichang", entity.getData().getPlayTime());
                            intent.putExtras(bundle);
                            LogU.Ld("1608", "报名走哪5" + entity.getData().getRefereeFee()+"==="+cp_fy);
                            startActivity(intent);
                        } else {
                            LogU.Ld("1608", "报名走哪6" + entity.getData().getRefereeFee());
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
                            bundle.putString("cp_rs", refereeNumber+"");
                            bundle.putString("cp_fy", entity.getData().getRefereeFee() + "");
                            bundle.putString("cp_dj", entity.getData().getRefereegrade());
                            bundle.putString("shichang", entity.getData().getPlayTime());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    }
                }
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }


    private void showCPDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */


        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您确定报名裁判吗?");

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

                addReferees();
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



    }

    private void initDownload(final int duiwu, final String dui) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间判断token--" + token + "invitedId--" + uid + "startTime--" + timeRI + " --" + timeSHI + "FirstSportId--" + FirstSportId
                + "SecondSportId--" + SecondSportId + "teamSex" + sexString);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition ")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("mark",  "1")
                .addParams("team", team + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {

                            showNormalDialog(duiwu, dui);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
/*

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

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
*/

    }


    private void initDownloadCP(String tea) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间判断token--" + token + "==="+uuid+"==="+1+"==="+tea);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition ")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("mark",  "1")
                .addParams("team", tea + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;
                        Boolean b = result.indexOf("4003") != -1;
                        Boolean c = result.indexOf("4005") != -1;

                        if (a) {
                            showCPDialog();


                        } else {
                            Gson gson = new Gson();
                          // JiekouSBEntity refereeentity = gson.fromJson(result, JiekouSBEntity.class);
                          //  refereeentity= gson.fromJson(result, JiekouSBEntity.class);
                            // refereeentity = gson.fromJson(result, RefereeClaimerEntity.class);
                            if (b){
                                JiekouSBEntity refereeentity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(HomeHDXQActivity.this, refereeentity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            if (c){
                                refereeentity = gson.fromJson(result, RefereeClaimerEntity.class);
                                refereeZC();
                            }

                           // Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }

    //报名裁判

    private void addReferees() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "报名裁判--" + token + "invitedId--" + uid + "startTime--" + timeRI + " --" + timeSHI + "FirstSportId--" + FirstSportId
                + "SecondSportId--" + SecondSportId + "teamSex" + sexString);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addReferees")
                .addHeader("token", token)
                .addParams("inviteId", uuid)
                .addParams("FirstSportId", FirstSportId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                   //     ToastUitl.longs("失败原因"+e.getMessage());
                        LogU.Ld("1608", "报名裁判失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "报名裁判" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                           // RefereeClaimerEntity entity = gson.fromJson(result, RefereeClaimerEntity.class);

                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();

                            intent.setClass(HomeHDXQActivity.this, HomeZhifuCGActivity.class);
                            bundle.putString("tag", 3 + "");
                            bundle.putString("uuid", uuid);
                            bundle.putString("cp_fy", String.format("%.2f", cpz ) + "");
                            bundle.putString("shichang", entity.getData().getPlayTime());
                            bundle.putString("moshiString", sportMode + "");
                            bundle.putString("cpBMtag", "1");
                            bundle.putString("cp_rs", refereeNumber+"");
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            Gson gson = new Gson();
                            //   JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            RefereeClaimerEntity     refereeentity = gson.fromJson(result, RefereeClaimerEntity.class);
                         //   Toast.makeText(HomeHDXQActivity.this, refereeentity.getMsg(), Toast.LENGTH_SHORT).show();

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
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude + bdLocation.getLocType());
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
        if (isOver) {
            LogU.Ld("1609", "报名截止时间=====");
        }
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
                if (isOver) {
                    LogU.Ld("1609", "报名截止时间++++++");
                }
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

        LatLng startLatLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        LatLng endLatLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        String uri = String.format("baidumap://map/direction?origin=%s,%s&destination=" + "%s,%s&mode=driving&src=com.34xian.demo", startLatLng.latitude, startLatLng.longitude, endLatLng.latitude, endLatLng.longitude);
        Intent intent = new Intent();
        intent.setData(Uri.parse(uri));
        startActivity(intent);

//        Intent intent = new Intent();
//        intent.setData(Uri.parse(BAIDU_MAP_NAVI_URI + address));
//        startActivity(intent);

    }

    //推广员~平台介入（裁判未到场）
    private void ptjrRefereeInit() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereePlatformIntervention")
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


    //弹窗签到
    private void qiandaoCG() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_qiaodao_layout, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close);

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

        if (qxbmText.getText().toString().equals("取消报名")) {
            ds_xz.setText("您确定取消本次报名么？");
        } else if (qxbmText.getText().toString().equals("取消预订")) {
            ds_xz.setText("您确定取消本次场馆预订么？");
        } else if (qxbmText.getText().toString().equals("取消发布")) {
            ds_xz.setText("您确定取消本次活动发布么？");

        } else if (hdxq_dianji_bm.getText().toString().equals("取消发布")) {
            ds_xz.setText("您确定取消本次活动发布么？");

        } else if (hdxq_dianji_bm.getText().toString().equals("取消报名")) {
            ds_xz.setText("您确定取消本次报名么？");

        }
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
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    /*//是否有活动
    public void getjudgeTime() {
        //LogU.Ld("1608", "是否有活动" +token+"==="+ timeStart+"===="+shichang);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getjudgeTime")
                .addParams("token", token)
                //   .addParams("startTime", timeStart)
                //   .addParams("PlayTime", shichang + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
*/
    //弹窗是否是裁判
    private void refereeZC() {
        dialogCP = new Dialog(this, R.style.BottomDialog);
        dialogCP.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);
        icon_que.setText("成为裁判");
        icon_close.setText("确定");
        ds_xz = sport.findViewById(R.id.ds_xz);
        ds_xz.setText(refereeentity.getMsg());

        dialogCP.setContentView(sport);
        dialogCP.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialogCP.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialogCP.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (refereeentity.getData().getStatus() == 2) {
                    Intent intent = new Intent();
                    intent.setClass(HomeHDXQActivity.this, RefereePerfectXXActivity.class);
                    startActivity(intent);
                } else {
                    initSFZ();

                }
                //initReferee();


            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCP.dismiss();
            }
        });

    }


    //是不是裁判
    private void initReferee() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + "--" + token + "--");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/Referee")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {

                    private int status;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "认证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            InitRefereeEntity entity = gson.fromJson(result, InitRefereeEntity.class);
                            status = entity.getData().getStatus();
                            if (status == 1) {
                                intent.setClass(HomeHDXQActivity.this, RefereePerfectXXActivity.class);
                                startActivity(intent);
                            } else {
                                initSFZ();

                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {

                                intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //实名认证
    private void initSFZ() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + "--" + token + "--");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/judgereferee")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {

                    private String playerID;
                    private String playerRealName;
                    private String number;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "认证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            JudgerefereeJEntity entity = gson.fromJson(result, JudgerefereeJEntity.class);
                            number = entity.getData().getPlayerAppPhoneNumber();
                            playerRealName = entity.getData().getPlayerRealName();
                            playerID = entity.getData().getPlayerID();
                            intent.setClass(HomeHDXQActivity.this, MyCwRefereeActivity.class);


                            intent.putExtra("number",number);
                            intent.putExtra("playerRealName",playerRealName);
                            intent.putExtra("playerID",playerID);
                            startActivity(intent);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {

                                intent.setClass(HomeHDXQActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }else if (entity.getMsg().equals("没有实名认证")){
                                intent.setClass(HomeHDXQActivity.this, MyCwRefereeActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }


    //是否有活动
    public void getCondition() {
      //  LogU.Ld("1608", "是否有活动" + token + "===" + timeStart + "====" + shichang+"==="+dashangString+"==="+peilianString);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition ")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .addParams("mark",  "1")
                .addParams("team", team + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    // 弹窗性别
    private void yaoQXZ_xb() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);

        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);


        tis_text.setText("性别要求说明");
        ds_xz.setText("发布者对报名者的性别要求。如有裁判，裁判不受该性别要求限制。");


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

    //弹窗 年龄
    private void yaoQXZ_nl() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        tis_text.setText("年龄要求说明");
        ds_xz.setText("发布者对报名者的年龄要求。如有裁判，裁判不受该年龄要求限制。");


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

    // 弹窗 技术
    private void yaoQXZ_js() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        tis_text.setText("技术等级说明");
        ds_xz.setText("发布者对报名者该运动项目的技术级别要求。用户的某运动项目的技术级别由技术分决定，用户在竞技模式活动结束后会输赢技术分。如有裁判，裁判不受该要求限制。");


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
    // 弹窗 打赏
    private void yaoQXZ_ds() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);


        tis_text.setText("打赏费用说明");
        ds_xz.setText("发布者打赏给报名者的费用，报名者均分该费用。会提高活动匹配成功率。");


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
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

    // 弹窗 陪练
    private void yaoQXZ_pl() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);
        TextView ds_xz_pl1 = sport.findViewById(R.id.ds_xz_pl1);
        TextView ds_xz_pl2 = sport.findViewById(R.id.ds_xz_pl2);
        tis_text.setText("陪练费用说明");

        ds_xz.setText("1)练习方支付给陪练方的费用;");
        ds_xz_pl1.setText("2)陪练费用=陪练单价*时间+场地费/2;");
        ds_xz_pl2.setText("3)陪练单价（"+cityName+nameSport+"陪练，平台建议单价）:\n" +
                "4级:"+money4+"元/小时 5级:"+money5+"元/小时\n" +
                "6级:"+money6+"元/小时 7级:"+money7+"元/小时\n" +
                "8级:"+money8+"元/小时 9级:"+money9+"元/小时\n" +
                "10级:"+money10+"元/小时");

        //  ds_xz_pl2.setText("陪练单价（北京市羽毛球陪练，平台建议单价）：4级：40元/小时   5级：50元/小时 6级：60元/小时   7级：70元/小时 8级：80元/小时   9级：90元/小时 10级：100元/小时" );
            ds_xz_pl1.setVisibility(View.VISIBLE);
            ds_xz_pl2.setVisibility(View.VISIBLE);

        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
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

    //陪练球等级和钱（建议价格）
    public void getTheBall() {
          LogU.Ld("1608", "是否有活动" + token + "===" + sportId + "====" + city);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTheBall")

                .addParams("SportId", sportId+"")
                .addParams("CityName", city)

                .build()
                .execute(new StringCallback() {



                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "陪练球等级和钱" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();

                            TheBallEntity entity = gson.fromJson(result, TheBallEntity.class);
                            cityName = entity.getData().getCityName();
                            nameSport = entity.getData().getName();


                            List<TheBallEntity.DataBean.CommBean>    comm = entity.getData().getComm();

                            LogU.Ld("1608","数据"+ comm + comm.toString());
                            if (!EmptyUtils.isListEmpty(comm)){
                                money4 = entity.getData().getComm().get(0).getMoney();
                                money5 = entity.getData().getComm().get(1).getMoney();
                                money6 = entity.getData().getComm().get(2).getMoney();
                                money7 = entity.getData().getComm().get(3).getMoney();
                                money8 = entity.getData().getComm().get(4).getMoney();
                                money9 = entity.getData().getComm().get(5).getMoney();
                                money10 = entity.getData().getComm().get(6).getMoney();
                            }



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


}
