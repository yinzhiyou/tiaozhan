package com.example.android.promoter.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.FYSMEntity;
import com.example.android.promoter.Entity.HDXQEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.QianbaoZhifuEntity;
import com.example.android.promoter.Entity.ReserveFYSMEntity;
import com.example.android.promoter.Entity.WeixinZhifuEntity;
import com.example.android.promoter.Entity.ZhifubaoEntity;
import com.example.android.promoter.My.Setup.MyTXMMActivity;
import com.example.android.promoter.My.Setup.SMRZActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.AlipayHelper;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.zhifu.PayFragment;
import com.example.android.promoter.Toos.zhifu.PayPwdView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import okhttp3.Call;

/**
 * 支付页面
 */
public class HomeZhifuActivity extends AppCompatActivity implements View.OnClickListener, PayPwdView.InputCallBack {

    private TextView biaoti, feiyongText, changdiText, dashangText, peilianText, cdfshuming, dsfshuming, plfshuoming;
    private ImageView fanhui, imageWX, imageZFB, imageQB;
    private RelativeLayout queding;
    private LinearLayout wx, zfb, qb, feiyongLayout, dashangLayout, peiliangLayout, cdflayout, dsflayout, plflayout;
    private int tag = 1;
    private AlipayHelper alipayHelper;
    private String tab;
    private String fqtzXiangmuid, fqtzXiangmudaid, moshiString, cgid, token, sex, fangshi, houtaifeiyong, timeStart, placeNun,
            dashangString, zuigao, zuidi, timeRI, timeSHI, shichang, peilianString, json, beizhu, changdifei, hezuofeiyong, hezuo, uuid;
    private String inviteId, team, SecondSportId, startTime, playTime, FirstSportId, zhifuqian, canyurenshu;
    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    private IWXAPI iwxapi; //微信支付api
    private PayFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_zhifu);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        queding = findViewById(R.id.home_zhifu_queren);
        queding.setOnClickListener(this);
        wx = findViewById(R.id.home_zhifu_wx);
        wx.setOnClickListener(this);
        zfb = findViewById(R.id.home_zhifu_zfb);
        zfb.setOnClickListener(this);
        qb = findViewById(R.id.home_zhifu_qb);
        qb.setOnClickListener(this);
        imageWX = findViewById(R.id.home_zhifu_image_wx);
        imageZFB = findViewById(R.id.home_zhifu_image_zfb);
        imageQB = findViewById(R.id.home_zhifu_image_qb);
        feiyongText = findViewById(R.id.home_zhifu_feiyong);
        changdiText = findViewById(R.id.home_zhifu_changdifei);
        dashangText = findViewById(R.id.home_zhifu_dashangfei);
        feiyongLayout = findViewById(R.id.home_zhifu_changdifei_layout);
        dashangLayout = findViewById(R.id.home_zhifu_dashangfei_layout);
        peiliangLayout = findViewById(R.id.home_zhifu_peilianfei_layout);
        peilianText = findViewById(R.id.home_zhifu_peilianfei);
        cdfshuming = findViewById(R.id.zhifu_cdfshuoming);
        dsfshuming = findViewById(R.id.zhifu_dsfshuoming);
        plfshuoming = findViewById(R.id.zhifu_plfshuoming);
        cdflayout = findViewById(R.id.zhifu_cdf);
        dsflayout = findViewById(R.id.zhifu_dsf);
        plflayout = findViewById(R.id.zhifu_plf);

        biaoti.setText("订单支付");
        imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
        alipayHelper = new AlipayHelper(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(HomeZhifuActivity.this, "logintoken", "");


        spUtileFQTZ = new SPUtileFQTZ();
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", "无");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", "无");
        cgid = (String) spUtileFQTZ.get(this, "cgid", "无");
        timeRI = (String) spUtileFQTZ.get(this, "timeRI", "无");
        timeSHI = (String) spUtileFQTZ.get(this, "timeSHI", "无");
        DecimalFormat df = new DecimalFormat("0.00");


        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tag");
        if (tab.equals("1")) {//发起

            fangshi = bundle.getString("fangshi");
            moshiString = bundle.getString("moshiString");
            sex = bundle.getString("sex");
            zuidi = bundle.getString("zuidi");
            zuigao = bundle.getString("zuigao");
            dashangString = bundle.getString("dashangString");
            shichang = bundle.getString("shichang");
            peilianString = bundle.getString("peilianString");
            json = bundle.getString("json");
            beizhu = bundle.getString("beizhu");
            changdifei = bundle.getString("changdifei");
            hezuofeiyong = bundle.getString("hezuofeiyong");
            hezuo = bundle.getString("hezuo");
            houtaifeiyong = bundle.getString("houtaifeiyong");
            canyurenshu = bundle.getString("canyurenshu");
            fqtzXiangmudaid = bundle.getString("fqtzXiangmudaid");
            timeStart = bundle.getString("timeStart");
            placeNun = bundle.getString("placeNun");
            LogU.Ld("1608", "发起" + dashangString + peilianString + changdifei + "后台" + houtaifeiyong);
        } else if (tab.equals("2")) {
            inviteId = bundle.getString("inviteId");
            team = bundle.getString("team");
            SecondSportId = bundle.getString("SecondSportId");
            startTime = bundle.getString("startTime");
            playTime = bundle.getString("playTime");
            FirstSportId = bundle.getString("FirstSportId");
            changdifei = bundle.getString("changdifei");
            dashangString = bundle.getString("dashangString");
            peilianString = bundle.getString("peilianString");
            canyurenshu = bundle.getString("canyurenshu");
            hezuo = bundle.getString("hezuo");
            moshiString = bundle.getString("moshiString");
            houtaifeiyong = bundle.getString("houtaifei");
            fangshi = bundle.getString("fangshi");
            fqtzXiangmudaid = FirstSportId;
//            fqtzXiangmudaid  = moshiString;
//            houtaifeiyong = changdifei;
            hezuofeiyong = changdifei;
            timeStart = bundle.getString("timeStart");
            placeNun = bundle.getString("placeNun");
            LogU.Ld("1608", "加入" + fqtzXiangmudaid + houtaifeiyong + canyurenshu + hezuo);
        } else if (tab.equals("3")) {
            uuid = bundle.getString("uuid");
            xiangqing();

        } else if (tab.equals("4")) {

            cgid = bundle.getString("cgid");
            shichang = bundle.getString("shichang");
            json = bundle.getString("json");
            beizhu = bundle.getString("beizhu");
            changdifei = bundle.getString("changdifei");
            hezuofeiyong = bundle.getString("hezuofeiyong");
            hezuo = bundle.getString("hezuo");
            houtaifeiyong = bundle.getString("houtaifeiyong");
            canyurenshu = bundle.getString("canyurenshu");
            fqtzXiangmudaid = bundle.getString("fqtzXiangmudaid");
            timeStart = bundle.getString("timeStart");
            placeNun = bundle.getString("placeNun");
        }


        if (tab.equals("1")) {
            if (hezuo == null) {
                LogU.Ld("1608", "合作等于null" + dashangString + peilianString + changdifei);
                zhifuqian = (Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei)) + "";
//                feiyongText.setText(df.format((Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei))) + "元");
//                changdiText.setText(df.format(Double.parseDouble(changdifei)) + "元");
//                dashangText.setText(df.format(Double.parseDouble(dashangString)) + "元");
//                peilianText.setText(df.format(Double.parseDouble(peilianString))+"元");
                if (dashangString.equals("0.0")) {
                    dashangLayout.setVisibility(View.GONE);
                    dsflayout.setVisibility(View.GONE);
                }
                if (peilianString.equals("0.0")) {
                    peiliangLayout.setVisibility(View.GONE);
                    plflayout.setVisibility(View.GONE);
                }
//                if (changdifei.equals("0.0")){
//                    feiyongLayout.setVisibility(View.GONE);
//                    cdflayout.setVisibility(View.GONE);
//                }
            } else {
                if (hezuo.equals("1")) {
                    LogU.Ld("1608", "合作" + dashangString + peilianString + changdifei);
                    if (moshiString.equals("3")) {
                        zhifuqian = (Double.parseDouble(hezuofeiyong)) + "";
                    } else {
                        zhifuqian = (Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(hezuofeiyong)) + "";
                    }

//                    feiyongText.setText(df.format(Double.parseDouble(zhifuqian)) + "元");
//                    changdiText.setText(df.format(Double.parseDouble(changdifei)) + "元");
//                    dashangText.setText(df.format(Double.parseDouble(dashangString)) + "元");
                    peilianText.setText(df.format(Double.parseDouble(peilianString)));
                    if (moshiString.equals("1") || moshiString.equals("2") || moshiString.equals("3")) {
                        peiliangLayout.setVisibility(View.GONE);
                        plflayout.setVisibility(View.GONE);
                    } else {
                        dashangLayout.setVisibility(View.GONE);
                        dsflayout.setVisibility(View.GONE);
                        if (peilianString.equals("0")) {
                            peiliangLayout.setVisibility(View.GONE);
                            plflayout.setVisibility(View.GONE);
                        }
                    }

                    if (dashangString.equals("0")) {
                        dashangLayout.setVisibility(View.GONE);
                        dsflayout.setVisibility(View.GONE);
                    }
                } else {
                    LogU.Ld("1608", "非合作" + dashangString + peilianString + changdifei);
                    zhifuqian = (Double.parseDouble(dashangString) + Double.parseDouble(peilianString)) + "";
                    LogU.Ld("1608", "bb" + dashangString + peilianString + changdifei);
//                    feiyongText.setText(df.format((Double.parseDouble(dashangString) + Double.parseDouble(peilianString))) + "元");
//                    feiyongLayout.setVisibility(View.GONE);
//                    dashangText.setText(df.format(Double.parseDouble(dashangString)) + "元");
//                    peilianText.setText(df.format(Double.parseDouble(peilianString)) + "元");
//                    cdflayout.setVisibility(View.GONE);

                    if (moshiString.equals("1") || moshiString.equals("2")) {
                        peiliangLayout.setVisibility(View.GONE);
                        plflayout.setVisibility(View.GONE);

                    } else {

                        dashangLayout.setVisibility(View.GONE);
                        dsflayout.setVisibility(View.GONE);

                    }
                }
            }
        } else if (tab.equals("2")) {
            zhifuqian = (Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei)) + "";
//            feiyongText.setText(df.format((Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei))) + "元");
//            changdiText.setText(df.format(Double.parseDouble(changdifei)) + "元");
//            dashangText.setText(df.format(Double.parseDouble(dashangString)) + "元");
//            peilianText.setText(df.format(Double.parseDouble(peilianString))+"元");
            if (dashangString.equals("0.0")) {
                dashangLayout.setVisibility(View.GONE);
                dsflayout.setVisibility(View.GONE);
            }
            if (peilianString.equals("0.0")) {
                peiliangLayout.setVisibility(View.GONE);
                plflayout.setVisibility(View.GONE);
            }
//            if (changdifei.equals("0.0")){
//                feiyongLayout.setVisibility(View.GONE);
//                cdflayout.setVisibility(View.GONE);
//            }

        } else if (tab.equals("4")) {
            if (hezuo == null) {
                LogU.Ld("1608", "合作等于null" + dashangString + peilianString + changdifei);
                zhifuqian = (Double.parseDouble(changdifei)) + "";

                peiliangLayout.setVisibility(View.GONE);
                plflayout.setVisibility(View.GONE);
                dashangLayout.setVisibility(View.GONE);
                dsflayout.setVisibility(View.GONE);

            } else {
                if (hezuo.equals("1")) {
                    LogU.Ld("1608", "合作" + dashangString + peilianString + changdifei);

                    zhifuqian = (Double.parseDouble(changdifei)) + "";
                    dashangLayout.setVisibility(View.GONE);
                    dsflayout.setVisibility(View.GONE);
                    peiliangLayout.setVisibility(View.GONE);
                    plflayout.setVisibility(View.GONE);
                }
            }

        }
        if (tab.equals("4")){
            shuomingCG();
        }else {
            shuoming();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.home_zhifu_queren:

                if (tag == 1) {
                    Toast.makeText(this, "点击了微信支付", Toast.LENGTH_SHORT).show();

                    if (tab.equals("1")) {
                        init("wechatpay");

                    } else {
                        initjiaru("wechatpay");
                    }
                    if (tab.equals("4")) {
                        initReserve("wechatpay");
                    }

                } else if (tag == 2) {
                    Toast.makeText(this, "点击了支付宝支付", Toast.LENGTH_SHORT).show();

                    if (tab.equals("1")) {
                        init("alipay");
                    } else if (tab.equals("2")) {
                        initjiaru("alipay");
                    } else if (tab.equals("3")) {
                        beiyaoqing("alipay");
                    } else if (tab.equals("4")) {
                        initReserve("alipay");
                    }
                } else if (tag == 3) {

//                    Toast.makeText(this, "点击了钱包支付", Toast.LENGTH_SHORT).show();
//
//                    if (tab.equals("1")){
//                        init("balance");
//                    }else{
//                        initjiaru("balance");
//                    }
                    yesnomima();


//                    Intent intent = new Intent();
//                    intent.setClass(this, HomeZhifuCGActivity.class);
//                    startActivity(intent);
                }

                break;
            case R.id.home_zhifu_wx:
                imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                imageQB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                tag = 1;
                break;
            case R.id.home_zhifu_zfb:
                imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                imageQB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                tag = 2;
                break;
            case R.id.home_zhifu_qb:
                imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                imageQB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                tag = 3;
                break;
        }
    }

    //预定场馆
    private void initReserve(final String payType) {


        LogU.Ld("1608", "发起挑战token" + token + "一级运动ID" + fqtzXiangmudaid + "二级运动ID" + fqtzXiangmuid + "运动模式" + moshiString
                + "场馆ID" + cgid + "场地费支付方式" + fangshi + "成员性别" + sex + "开始时间" + timeRI + timeSHI + "场地费" + houtaifeiyong);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userReserveVenue")
                .addHeader("token", token)
                .addParams("sportid", fqtzXiangmudaid + "")//一级运动ID
                .addParams("siteUid", cgid)//场馆ID
                .addParams("StartTime", timeStart)//开始时间
                .addParams("PlayTime", shichang)//时长
                .addParams("SiteMoney", changdifei)//场地费
                .addParams("comments", beizhu+"")// 备注
                .addParams("payType", payType)
                .addParams("sportType", fqtzXiangmuid + "")//二级运动ID
                .addParams("venueid", placeNun + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            if (payType.equals("alipay")) {
                                Gson gson = new Gson();
                                final ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                alipayHelper.pay(entity.getData().getSign_data());
                                alipayHelper.setOnAlipayResultListener(new AlipayHelper.OnAlipayResultListener() {
                                    @Override
                                    public void alipaySucess() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();//传值

                                        intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                        bundle.putString("tag", "4");
                                        bundle.putString("uuid", entity.getData().getUuid());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                        finish();
                                    }

                                    @Override
                                    public void alipayCancel() {
                                    }

                                    @Override
                                    public void alipayFailed() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else if (payType.equals("wechatpay")) {
                                Gson gson = new Gson();
                                WeixinZhifuEntity entity = gson.fromJson(result, WeixinZhifuEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                LogU.Ld("1608", "微信走一走" + entity.getMsg());
//                              String  qianming =  "appid="+entity.getData().getSign_data().getAppid()+"&noncestr="+entity.getData().getSign_data().getNoncestr()+
//                                        "&package=Sign=WXPay"+"&partnerid="+entity.getData().getSign_data().getPartnerid()+"&prepayid="+ entity.getData().getSign_data().getPrepayid() +"&timestamp="+entity.getData().getSign_data().getTimestamp()+"&key=haoxiafaliupinguanghaojiayuehjz3";
//                                String jiami = md5(qianming);
//                                String daxie= jiami.trim().toUpperCase();
                                toWXPay(entity.getData().getSign_data().getAppid(), entity.getData().getSign_data().getPartnerid(),
                                        entity.getData().getSign_data().getPrepayid(), entity.getData().getSign_data().getNoncestr(),
                                        entity.getData().getSign_data().getTimestamp() + "", entity.getData().getSign_data().getSign(), entity.getData().getSign_data().getPackageX());
                            }
                            if (payType.equals("balance")) {
                                Gson gson = new Gson();
                                QianbaoZhifuEntity entity = gson.fromJson(result, QianbaoZhifuEntity.class);

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值

                                intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                bundle.putString("tag", "4");
                                bundle.putString("uuid", entity.getData().getUuid());
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //发起挑战
    private void init(final String payType) {
        String FQHDyaoqing, cdf;
        if (json.length() < 10) {
            FQHDyaoqing = "";
        } else {
            FQHDyaoqing = json;
        }

        if (hezuo.equals("1")) {

            cdf = zhifuqian;
        } else {
            cdf = zhifuqian;
        }
        LogU.Ld("1608", "发起挑战token" + token + "一级运动ID" + fqtzXiangmudaid + "二级运动ID" + fqtzXiangmuid + "运动模式" + moshiString
                + "场馆ID" + cgid + "场地费支付方式" + fangshi + "成员性别" + sex + "开始时间" + timeRI + timeSHI + "场地费" + houtaifeiyong);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAddActivity")
                .addHeader("token", token)
                .addParams("sportid", fqtzXiangmudaid + "")//一级运动ID
                .addParams("sportType", fqtzXiangmuid + "")//二级运动ID
                .addParams("SportMode", moshiString)// 运动模式 1娱乐 2竞技 3陪练 4找陪练
                .addParams("siteUid", cgid)//场馆ID
                .addParams("StartTime", timeStart)//开始时间
                .addParams("PlayTime", shichang)//时长
                .addParams("SiteMoney", houtaifeiyong)//场地费
                .addParams("PaySiteMoneyType", fangshi)//场地费支付方式
                .addParams("teamSex", sex) //成员性别
                .addParams("LevelMin", zuidi)//成员最低技术等级
                .addParams("LevelMax", zuigao)//成员最高技术等级
                .addParams("Tips", dashangString)//打赏费
                .addParams("comments", beizhu)// 备注
                .addParams("member", FQHDyaoqing)//发布前邀请的人 可为空 如果有 则为json数据 如[{"team":"1","uuid":"b60d8e06-1ff3-f048-d42f-49f42b7f0e2b"},{"team":"2","uuid":"99f24ba3-4e4f-ab35-d546-369ffa453884"}]
                .addParams("MoneyPerhour", peilianString)//陪练费
                .addParams("payType", payType)
                .addParams("venueid", placeNun + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            if (payType.equals("alipay")) {
                                Gson gson = new Gson();
                                final ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                alipayHelper.pay(entity.getData().getSign_data());
                                alipayHelper.setOnAlipayResultListener(new AlipayHelper.OnAlipayResultListener() {
                                    @Override
                                    public void alipaySucess() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();//传值

                                        intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                        bundle.putString("tag", "1");
                                        bundle.putString("uuid", entity.getData().getUuid());
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                        finish();
                                    }

                                    @Override
                                    public void alipayCancel() {
                                    }

                                    @Override
                                    public void alipayFailed() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else if (payType.equals("wechatpay")) {
                                Gson gson = new Gson();
                                WeixinZhifuEntity entity = gson.fromJson(result, WeixinZhifuEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                LogU.Ld("1608", "微信走一走" + entity.getMsg());
//                              String  qianming =  "appid="+entity.getData().getSign_data().getAppid()+"&noncestr="+entity.getData().getSign_data().getNoncestr()+
//                                        "&package=Sign=WXPay"+"&partnerid="+entity.getData().getSign_data().getPartnerid()+"&prepayid="+ entity.getData().getSign_data().getPrepayid() +"&timestamp="+entity.getData().getSign_data().getTimestamp()+"&key=haoxiafaliupinguanghaojiayuehjz3";
//                                String jiami = md5(qianming);
//                                String daxie= jiami.trim().toUpperCase();
                                toWXPay(entity.getData().getSign_data().getAppid(), entity.getData().getSign_data().getPartnerid(),
                                        entity.getData().getSign_data().getPrepayid(), entity.getData().getSign_data().getNoncestr(),
                                        entity.getData().getSign_data().getTimestamp() + "", entity.getData().getSign_data().getSign(), entity.getData().getSign_data().getPackageX());
                            }
                            if (payType.equals("balance")) {
                                Gson gson = new Gson();
                                QianbaoZhifuEntity entity = gson.fromJson(result, QianbaoZhifuEntity.class);

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值

                                intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                bundle.putString("tag", "1");
                                bundle.putString("uuid", entity.getData().getUuid());
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //加入活动
    private void initjiaru(final String payType) {
        LogU.Ld("1608", "加入活动" + token + "---inviteId---" + inviteId + "---team---" + team + "---SecondSportId---" + SecondSportId
                + "---startTime---" + startTime + "---playTime---" + playTime + "---FirstSportId---" + FirstSportId);
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
                .addParams("payType", payType)
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
                            if (payType.equals("alipay")) {
                                Gson gson = new Gson();
                                ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                alipayHelper.pay(entity.getData().getSign_data());
                                alipayHelper.setOnAlipayResultListener(new AlipayHelper.OnAlipayResultListener() {
                                    @Override
                                    public void alipaySucess() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();//传值

                                        intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                        bundle.putString("tag", "2");
                                        bundle.putString("uuid", inviteId);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void alipayCancel() {
                                    }

                                    @Override
                                    public void alipayFailed() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else if (payType.equals("wechatpay")) {
                                Gson gson = new Gson();
                                WeixinZhifuEntity entity = gson.fromJson(result, WeixinZhifuEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                LogU.Ld("1608", "微信走一走" + entity.getMsg());
//                              String  qianming =  "appid="+entity.getData().getSign_data().getAppid()+"&noncestr="+entity.getData().getSign_data().getNoncestr()+
//                                        "&package=Sign=WXPay"+"&partnerid="+entity.getData().getSign_data().getPartnerid()+"&prepayid="+ entity.getData().getSign_data().getPrepayid() +"&timestamp="+entity.getData().getSign_data().getTimestamp()+"&key=haoxiafaliupinguanghaojiayuehjz3";
//                                String jiami = md5(qianming);
//                                String daxie= jiami.trim().toUpperCase();
                                toWXPay(entity.getData().getSign_data().getAppid(), entity.getData().getSign_data().getPartnerid(),
                                        entity.getData().getSign_data().getPrepayid(), entity.getData().getSign_data().getNoncestr(),
                                        entity.getData().getSign_data().getTimestamp() + "", entity.getData().getSign_data().getSign(), entity.getData().getSign_data().getPackageX());
                            } else if (payType.equals("balance")) {
                                Gson gson = new Gson();
                                QianbaoZhifuEntity entity = gson.fromJson(result, QianbaoZhifuEntity.class);

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值

                                intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                bundle.putString("tag", "2");
                                bundle.putString("uuid", entity.getData().getUuid());
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            showNormalDialog("\"为了更方便发布和报名活动，\n" +
//                                    "请完善个人信息\"","1");

                        }

                    }
                });
    }

    //判断密码是否正确
    private void panduanmima(String mima) {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkPutMoneyPwdIsTrue")
                .addHeader("token", token)
                .addParams("password", mima)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "密码判断" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            if (tab.equals("1")) {
                                init("balance");

                            } else if (tab.equals("2")) {
                                initjiaru("balance");

                            } else if (tab.equals("3")) {
                                beiyaoqing("balance");

                            }else if (tab.equals("4")){
                                initReserve("balance" );
                            }
                            fragment.dismiss();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            fragment.dismiss();
                        }

                    }
                });
    }
//    checkIsPutMoneyPwd

    //费用说明
    private void shuoming() {
        LogU.Ld("1608", "费用说明" + moshiString + fqtzXiangmudaid + houtaifeiyong + canyurenshu + hezuo + fangshi + peilianString + dashangString);
        String fabu;
        if (tab.equals("1") || tab.equals("4")) {
            fabu = "1";
        } else {
            fabu = "0";
        }

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getElplainInfo")
                .addParams("sportModes", moshiString)
                .addParams("siteMoney", houtaifeiyong)
                .addParams("number", canyurenshu)
                .addParams("isCooper", hezuo)
                .addParams("PayMoneyType", fangshi)
                .addParams("isPublisher", fabu)
                .addParams("sportid", fqtzXiangmudaid)
                .addParams("Accompany", peilianString)
                .addParams("Reward", dashangString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "费用说明" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            FYSMEntity entity = gson.fromJson(result, FYSMEntity.class);
                            if (moshiString.equals("1") || moshiString.equals("2")) {
                                plflayout.setVisibility(View.GONE);
                            } else {
                                dsflayout.setVisibility(View.GONE);
                            }

                            cdfshuming.setText(entity.getData().getSiteMoneyInfo());
                            dsfshuming.setText(entity.getData().getTipsMoney());
                            plfshuoming.setText(entity.getData().getMoneyPerhour());
                            feiyongText.setText(entity.getData().getTotal() + "元");
                            changdiText.setText(entity.getData().getField() + "元");
                            dashangText.setText(entity.getData().getReward() + "元");
                            peilianText.setText(entity.getData().getAccompany() + "元");
                            zhifuqian = entity.getData().getTotal() + "";
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            showNormalDialog("您未设置钱包密码，现在去设置吗？", "2");

                        }

                    }
                });
    }

    //费用说明预定场馆
    private void shuomingCG() {
        LogU.Ld("1608", "场馆费用说明" + fqtzXiangmudaid + "二级运动"+fqtzXiangmuid + cgid +"开始时间"+ timeStart + "时长" + shichang + changdifei );
        String fabu;
        if (tab.equals("1") || tab.equals("4")) {
            fabu = "1";
        } else {
            fabu = "0";
        }

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getOrders")
                .addParams("sportid", fqtzXiangmudaid)
                .addParams("sportType",fqtzXiangmuid )
                .addParams("siteUid", cgid)
                .addParams("StartTime", timeStart)
                .addParams("PlayTime", shichang)
                .addParams("siteMoney", changdifei)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "费用说明" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ReserveFYSMEntity entity = gson.fromJson(result, ReserveFYSMEntity.class);
                            cdfshuming.setText(entity.getData().getSiteMoneyInfo());
                            feiyongText.setText(entity.getData().getTotal() + "元");
                            changdiText.setText(entity.getData().getField() + "元");
                            zhifuqian = entity.getData().getTotal() + "";
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            showNormalDialog("您未设置钱包密码，现在去设置吗？", "2");

                        }

                    }
                });
    }



    //判断密码是否设置
    private void yesnomima() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkIsPutMoneyPwd")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "判断密码是否设置" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString(PayFragment.EXTRA_CONTENT, "¥ " + zhifuqian);

                            fragment = new PayFragment();
                            fragment.setArguments(bundle);
                            fragment.setPaySuccessCallBack(HomeZhifuActivity.this);
                            fragment.show(getSupportFragmentManager(), "Pay");
                            fragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
                                @Override
                                public void onInputFinish(String result) {
                                    LogU.Ld("1608", "密码" + result);
                                    panduanmima(result);
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            showNormalDialog("您未设置钱包密码，现在去设置吗？", "2");
                            init();
                        }

                    }
                });
    }

    //实名验证
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            SMRZEntity entity = gson.fromJson(result, SMRZEntity.class);
//                            HomeDialog dialog = new HomeDialog(this);
//                            dialog.show();
//                            name.setText(entity.getData().getPlayerRealName());
//                            sfzString = entity.getData().getPlayerID();
//                            Intent intent = new Intent();
//                            if (tag.equals("1")){
//
//                                intent.setClass(MyShezhiActivity.this, MyAnquanOneActivity.class);
//                                startActivity(intent);
//                            }else{
//                                intent.setClass(MyShezhiActivity.this, MyTXMMActivity.class);
//                                startActivity(intent);
//                            }
                            showNormalDialog("您未设置提现密码，现在去设置吗？", "2");
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs("您还未实名认证");
                            showNormalDialog("您还未实名认证，现在去设置吗？", "3");

                        }
                    }
                });
    }

    /**
     * 调起微信支付的方法
     **/
    private void toWXPay(final String appid, final String partnerId, final String prepayId, final String nonceStr, final String timeStamp, final String sign, final String packageValue) {
        iwxapi = WXAPIFactory.createWXAPI(this, null); //初始化微信api
        iwxapi.registerApp("wx60e2e2539670b0e5"); //注册appid appid可以在开发平台获取
        LogU.Ld("1608", "微信走一走2" + appid + "---" + partnerId + "---" + prepayId + "---" + nonceStr + "---" + timeStamp + "---" + sign + "---" + packageValue);
        Runnable payRunnable = new Runnable() {
            //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq();
                //调起微信APP的对象 //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = appid;
                request.partnerId = partnerId;
                request.prepayId = prepayId;
                request.packageValue = packageValue;
                request.nonceStr = nonceStr;
                request.timeStamp = timeStamp;
                request.sign = sign;
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    //加密
    public static String md5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    @Override
    public void onInputFinish(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }


    private void showNormalDialog(String biaoti, final String tag) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(HomeZhifuActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
        normalDialog.setPositiveButton("选择其它支付方式",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        finish();
                    }
                });
        normalDialog.setNegativeButton("去设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        if (tag.equals("1")) {
                            intent.setClass(HomeZhifuActivity.this, GRXXActivity.class);
                            bundle.putString("tab", "2");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else if (tag.equals("2")) {
                            intent.setClass(HomeZhifuActivity.this, MyTXMMActivity.class);
                            startActivity(intent);
                        } else if (tag.equals("3")) {
                            intent.setClass(HomeZhifuActivity.this, SMRZActivity.class);
                            bundle.putString("tag", tag);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    }
                });
        // 显示

        normalDialog.show();
    }


    //项目详情
    private void xiangqing() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "项目详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            final HDXQEntity entity = gson.fromJson(result, HDXQEntity.class);

                            moshiString = entity.getData().getSportMode() + "";
                            houtaifeiyong = entity.getData().getSiteMoney() + "";
                            canyurenshu = entity.getData().getNeedNumber() + "";
                            hezuo = entity.getData().getIsCooper() + "";
                            fangshi = entity.getData().getPaySiteMoneyType() + "";
                            fqtzXiangmudaid = entity.getData().getSportName();
                            peilianString = entity.getData().getMoneyPerhour() + "";
                            dashangString = entity.getData().getTips() + "";

                            dashangString = entity.getData().getTips() + "";
                            peilianString = entity.getData().getMoneyPerhour() + "";
                            changdifei = entity.getData().getSiteMoney() + "";


//                                feiyongText.setText((Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei)) + "元");

                            if (entity.getData().getSportMode() == 3) {
//                                feiyongText.setText((Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei)) + "元");
                                dashangLayout.setVisibility(View.GONE);
                                dsflayout.setVisibility(View.GONE);
                                zhifuqian = ((Double.parseDouble(dashangString) + Double.parseDouble(peilianString) + Double.parseDouble(changdifei)) + "");
                            } else {
//                                feiyongText.setText( Double.parseDouble(changdifei) + "元");
                                dashangLayout.setVisibility(View.GONE);
                                peiliangLayout.setVisibility(View.GONE);
                                dsflayout.setVisibility(View.GONE);
                                plflayout.setVisibility(View.GONE);
                                zhifuqian = (Double.parseDouble(changdifei)) + "";
                            }

                            DecimalFormat df = new DecimalFormat("0.00");
                            if (entity.getData().getNeedNumber() > 4) {
                                //   zhifuqian =  df.format(entity.getData().getSiteMoney()/entity.getData().getNeedNumber())+"";
//                                feiyongText.setText( zhifuqian + "元");

//                                changdiText.setText(zhifuqian + "元");
                            } else {
//                                changdiText.setText(changdifei + "元");
//                                dashangText.setText(dashangString + "元");
//                                peilianText.setText(peilianString+"元");
                            }


                            inviteId = entity.getData().getUuid();
                            team = "2";
                            SecondSportId = entity.getData().getSportType() + "";
                            startTime = entity.getData().getStartTime();
                            playTime = entity.getData().getPlayTime() + "";
                            FirstSportId = entity.getData().getSportId() + "";
                            shuoming();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                        }
                    }
                });
    }

    //活动邀请
    private void beiyaoqing(final String payType) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "活动邀请" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/invitedUserHandle")
                .addHeader("token", token)
                .addParams("publishId", inviteId)
                .addParams("checkType", "1")
                .addParams("payType", payType)
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
                            if (payType.equals("alipay")) {
                                Gson gson = new Gson();
                                ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                alipayHelper.pay(entity.getData().getSign_data());
                                alipayHelper.setOnAlipayResultListener(new AlipayHelper.OnAlipayResultListener() {
                                    @Override
                                    public void alipaySucess() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();//传值

                                        intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                        bundle.putString("tag", "2");
                                        bundle.putString("uuid", inviteId);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void alipayCancel() {
                                    }

                                    @Override
                                    public void alipayFailed() {
                                        Toast.makeText(HomeZhifuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else if (payType.equals("wechatpay")) {
                                Gson gson = new Gson();
                                WeixinZhifuEntity entity = gson.fromJson(result, WeixinZhifuEntity.class);
                                Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                LogU.Ld("1608", "微信走一走" + entity.getMsg());
//                              String  qianming =  "appid="+entity.getData().getSign_data().getAppid()+"&noncestr="+entity.getData().getSign_data().getNoncestr()+
//                                        "&package=Sign=WXPay"+"&partnerid="+entity.getData().getSign_data().getPartnerid()+"&prepayid="+ entity.getData().getSign_data().getPrepayid() +"&timestamp="+entity.getData().getSign_data().getTimestamp()+"&key=haoxiafaliupinguanghaojiayuehjz3";
//                                String jiami = md5(qianming);
//                                String daxie= jiami.trim().toUpperCase();
                                toWXPay(entity.getData().getSign_data().getAppid(), entity.getData().getSign_data().getPartnerid(),
                                        entity.getData().getSign_data().getPrepayid(), entity.getData().getSign_data().getNoncestr(),
                                        entity.getData().getSign_data().getTimestamp() + "", entity.getData().getSign_data().getSign(), entity.getData().getSign_data().getPackageX());
                            } else if (payType.equals("balance")) {
                                Gson gson = new Gson();
                                QianbaoZhifuEntity entity = gson.fromJson(result, QianbaoZhifuEntity.class);

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();//传值
                                intent.setClass(HomeZhifuActivity.this, HomeZhifuCGActivity.class);
                                bundle.putString("tag", "2");
                                bundle.putString("uuid", entity.getData().getUuid());
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });
    }
}
