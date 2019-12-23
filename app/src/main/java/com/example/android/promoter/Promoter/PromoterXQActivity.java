package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.ApingBAdapter;
import com.example.android.promoter.Adapter.AshuBAdapter;
import com.example.android.promoter.Adapter.AyingBAdapter;
import com.example.android.promoter.Adapter.HDXQAAdapter;
import com.example.android.promoter.Adapter.HDXQBAdapter;
import com.example.android.promoter.Adapter.QquanAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.HDXQEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Home.HomeGzsmActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyGridView;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.luyin.PlaybackDialogFragment;
import com.example.android.promoter.Toos.luyin.RecordingItem;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员活动详情
 */
public class PromoterXQActivity extends BaseActivity implements View.OnClickListener {

    private List<HDXQEntity.DataBean.TeamABean> data;
    private List<HDXQEntity.DataBean.TeamBBean> datab;
    private HDXQAAdapter adapter;
    private HDXQBAdapter adapterb;
    private MyGridView gridViewA, gridViewB, aybgrid, asbgrid, apbgrid,qiquangrid;
    private TextView biaoti, tousu1, tousu2, tousu3, tousuName, tousuTime, bofang, tsjgText1, tsjgText2, tousuTitle,tousujl,cdh,cgh;
    private ImageView fanhui, qiuleiImage, qieleiImage2, fabuzheImage, touxiang, zhuangtai,lan_crown,hong_crown;
    private TextView ptjr, gd, xsls, bss, ss,qiquan;

    private long mDay = 0, yy = 0;
    private long mHour = 0;
    private long mMin = 0;
    private long mSecond = 0;// 天 ,小时,分钟,秒

    private String token, moshiString, uuid, tousuString, tag, luyin, isHandle, nameString, uid,zhuangtaiString;
    private SPUtils spUtils;

    private TextView XMid, qiuleiText, moshi, name, lv, dizhi, feiyong, fangshi, time, timelog, sex, dengji, dashang, queren, beizhu,
            fabutime, pipeiText, kaishiText, jieshuText, quxiaotime, quxiaoyuanyin, hezuo, mingcheng,adWin,bdWin,jgsm_text,cdf,dsf,dsftext,
            zhuanhuan,tyjb,zyjb, ayingb,ashub,apingb;

    private int jieru;
    private LinearLayout pptime, kstime, jstime, qxtime, anniuLayout, tsjgLayout, tsxq, tousuLayout,fbtime,gzsm,jgsm,zcrs,ABlayout,jbhfy,jieguo,qxyy;
    private RelativeLayout chuli;
    private int renshu;

    private AyingBAdapter adapter1;
    private AshuBAdapter adapter2;
    private ApingBAdapter adapter3;
    private QquanAdapter adapter4;

    private List<HDXQEntity.DataBean.AwinBuserInfoBean> data1;
    private List<HDXQEntity.DataBean.AloseBuserInfoBean> data2;
    private List<HDXQEntity.DataBean.AdrawBuserInfoBean> data3;
    private List<HDXQEntity.DataBean.GetwaiverInfoBean> data4;

    @Override
    public int initContentView() {

        return R.layout.activity_promoter_xq;

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
    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        ptjr = findViewById(R.id.promo_xq_ptjr);
        ptjr.setOnClickListener(this);
        gd = findViewById(R.id.promo_xq_gd);
        gd.setOnClickListener(this);
        xsls = findViewById(R.id.promo_xq_xsls);
        xsls.setOnClickListener(this);
        bss = findViewById(R.id.promo_xq_bss);
        bss.setOnClickListener(this);
        ss = findViewById(R.id.promo_xq_ss);
        ss.setOnClickListener(this);

        tsjgLayout = findViewById(R.id.promo_xq_tsjg);
        tsjgText1 = findViewById(R.id.promo_xq_tsjg_text1);
        tsjgText2 = findViewById(R.id.promo_xq_tsjg_text2);
        tsxq = findViewById(R.id.promo_xq_tsxq);
        tsxq.setOnClickListener(this);
        tousuLayout = findViewById(R.id.promo_xq_tousu);
        tousuTitle = findViewById(R.id.promo_xq_tousu_title);


        mingcheng = findViewById(R.id.home_hdxq_mingcheng);
        qiuleiImage = findViewById(R.id.home_hdxq_image_qiu);
        qieleiImage2 = findViewById(R.id.home_hdxq_image_qiu2);
        fbtime = findViewById(R.id.hdxq_fbsj);

        pptime = findViewById(R.id.hdxq_ppsj);
        kstime = findViewById(R.id.hdxq_kssj);
        jstime = findViewById(R.id.hdxq_jssj);
        qxtime = findViewById(R.id.hdxq_qxsj);
        fabuzheImage = findViewById(R.id.home_hdxq_touxiang);
        fabuzheImage.setOnClickListener(this);
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


        fabutime = findViewById(R.id.home_hdxq_fabutime);
        pipeiText = findViewById(R.id.home_hdxq_pipeitime);
        kaishiText = findViewById(R.id.home_hdxq_kaishitime);
        jieshuText = findViewById(R.id.home_hdxq_jieshutime);
        quxiaotime = findViewById(R.id.home_hdxq_quxiaoshijian);
        quxiaoyuanyin = findViewById(R.id.home_hdxq_quxiaoyuanyin);
        zhuanhuan = findViewById(R.id.home_hdxq_zhuanhuan);

        hezuo = findViewById(R.id.home_hdxq_hezuo);
        cgh = findViewById(R.id.home_hdxq_cgh);
        cdh = findViewById(R.id.home_hdxq_cdh);

        tousu1 = findViewById(R.id.promo_hdxq_text1);
        tousu2 = findViewById(R.id.promo_hdxq_text2);
        tousu3 = findViewById(R.id.promo_hdxq_text3);
        tousujl = findViewById(R.id.promo_hdxq_jl);

        touxiang = findViewById(R.id.promo_xq_touxiang);
        tousuName = findViewById(R.id.promo_xq_name);
        tousuTime = findViewById(R.id.promo_xq_time);
        anniuLayout = findViewById(R.id.promo_xq_anniu);
        chuli = findViewById(R.id.promo_xq_chuli);
        chuli.setOnClickListener(this);
        bofang = findViewById(R.id.promo_xq_bofang);
        bofang.setOnClickListener(this);
        zhuangtai = findViewById(R.id.home_hdxq_image_zhuangtai);
        gridViewA = findViewById(R.id.home_hdxq_grid_a);
        gridViewB = findViewById(R.id.home_hdxq_grid_b);
        data = new ArrayList<>();
        datab = new ArrayList<>();

        jbhfy = findViewById(R.id.home_hdxq_jbhfy);
        cdf = findViewById(R.id.home_hdxq_cdf);
        dsf = findViewById(R.id.home_hdxq_dsf);
        tyjb = findViewById(R.id.home_hdxq_tyjb);
        zyjb = findViewById(R.id.home_hdxq_zyjb);
        dsftext = findViewById(R.id.home_hdxq_dsf_text);

        aybgrid = findViewById(R.id.home_hdxq_AyingB_grid);
        asbgrid = findViewById(R.id.home_hdxq_AshuB_grid);
        apbgrid = findViewById(R.id.home_hdxq_ApingB_grid);
        ayingb = findViewById(R.id.home_hdxq_AyingB);
        ashub = findViewById(R.id.home_hdxq_AshuB);
        apingb = findViewById(R.id.home_hdxq_ApingB);
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        data4 = new ArrayList<>();
        adapter1 = new AyingBAdapter(this, data1);
        adapter2 = new AshuBAdapter(this, data2);
        adapter3 = new ApingBAdapter(this, data3);
        adapter4 = new QquanAdapter(this, data4);
        //取消原因
        qxyy = findViewById(R.id.hdxq_qxyy);
        jieguo = findViewById(R.id.home_hdxq_jieguo);
        //支持人数
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
        ABlayout = findViewById(R.id.home_hdxq_AB);
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
        biaoti.setText("活动详情");

    }

    @Override
    protected void initData() {
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

                intentgzsm.setClass(PromoterXQActivity.this,HomeGzsmActivity.class);


                startActivity(intentgzsm);
                break;
        }
    }


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
        LogU.Ld("1608", "项目详情" + uuid + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityInfo")
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
                        LogU.Ld("1608", "项目详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            final HDXQEntity entity = gson.fromJson(result, HDXQEntity.class);
                            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
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

                            if (entity.getData().getComplaint().equals("2")) {
//                                if (entity.getData().getGetUsersComplainIssystem()==1){
//                                    touxiang.setBackgroundResource(R.mipmap.pingtai);
//                                }else if (entity.getData().getGetUsersComplainIssystem()==0){
//                                    touxiang.setBackgroundResource(R.mipmap.cctgy);
//                                }
                                tousu1.setText("反        馈：" + entity.getData().getGetUserscomplaint());

                                tousu2.setVisibility(View.GONE);
                                chuli.setVisibility(View.GONE);

                            } else if (entity.getData().getComplaint().equals("0")) {
                                tousu1.setText("投        诉：" + entity.getData().getGetUserscomplaint());

                                if (tag.equals("1")) {
                                    chuli.setVisibility(View.GONE);
                                } else {
                                    chuli.setVisibility(View.VISIBLE);
                                }

                            } else if (entity.getData().getComplaint().equals("1")) {
                                tousuLayout.setVisibility(View.GONE);
                                tsjgLayout.setVisibility(View.VISIBLE);
                                //ToDo

                                tousuTitle.setText("投诉审核结果");
                                tsxq.setVisibility(View.VISIBLE);
                            } else {

//                                if (entity.getData().getGetUsersComplainIssystem() == 1) {
//                                    touxiang.setBackgroundResource(R.mipmap.pingtai);
//                                } else {
//                                    touxiang.setBackgroundResource(R.mipmap.cctgy);
//                                }
//                                    tousu1.setText("反        馈：" + entity.getData().getGetUserscomplaint());
                                chuli.setVisibility(View.GONE);
                            }

                            tsjgText1.setText(entity.getData().getGetUsersnickname()+":  "+entity.getData().getUnreserved());
                            tsjgText2.setText(entity.getData().getDetailed());
                            tousu3.setText("详细说明：" + entity.getData().getGetUsersComplainEnd());
                            tousujl.setText("结论：" + entity.getData().getComplaintistrue());
                            tousuName.setText(entity.getData().getGetUsersnickname());
                            tousuTime.setText(entity.getData().getGetUserscaddTime());


                            Glide.with(PromoterXQActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getGetUsersimgURL()).into(touxiang);

                            jieru = entity.getData().getGetUsersComplainIssystem();
                            if (entity.getData().getGetUsersComplainIssystem() == 1) {
                                tousu2.setVisibility(View.GONE);
                                ss.setTextColor(getResources().getColor(R.color.login_forget));//字体上色
                                ss.setBackgroundResource(R.drawable.hei_kuang);//修改背景
                            } else {
//                                tousu2.setVisibility(View.VISIBLE);
                            }
                            LogU.Ld("1608", "开始时间" + entity.getData().getStartTime() + "天" + mDay + "时" + mHour + "分" + mMin + "秒" + mSecond);


                            if (entity.getData().getIsCooper() == 1) {
                                hezuo.setVisibility(View.VISIBLE);
                            } else {
                                hezuo.setVisibility(View.GONE);
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

                            LogU.Ld("1608",""+datab.size()+"==+++++"+renshu+"=="+"====="+moshiString+"======="+zhuangtaiString+"======="+uid);
                            mingcheng.setText(entity.getData().getSiteName());
                            fabutime.setText(entity.getData().getCreateTime());
                            pipeiText.setText(entity.getData().getJoinEndTime());
                            kaishiText.setText(entity.getData().getStartTime());
                            quxiaotime.setText(entity.getData().getCancelTime());
                            quxiaoyuanyin.setText(entity.getData().getSuspendReason());
                            jieshuText.setText(entity.getData().getFinishedTime());
                            renshu = entity.getData().getNeedNumber() / 2;
                            zhuangtaiString = entity.getData().getPublicStatus() + "";

                            LogU.Ld("1608", "AB队人数" + renshu);
                           List<HDXQEntity.DataBean.TeamABean> list;
                            list = entity.getData().getTeamA();
                            if(!EmptyUtils.isListEmpty(list)) {
                                data.clear();
                                data.addAll(list);

                                gridViewA.setAdapter(adapter);

                            }
                            LogU.Ld("1608","有人没"+list.size());
                            List<HDXQEntity.DataBean.TeamBBean> listb;
                            listb = entity.getData().getTeamB();
                            if (!EmptyUtils.isListEmpty(listb)) {
                                datab.clear();
                                datab.addAll(listb);


                            }
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

                            LogU.Ld("1608","有人没"+listb.size());
                            XMid.setText(entity.getData().getOrderId());
                            qiuleiText.setText(entity.getData().getSportName() + "    " + entity.getData().getSportTypeName());

                            if (entity.getData().getSportMode() == 1) {
                                moshi.setText("娱乐模式");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {

                                }
                            } else if (entity.getData().getSportMode() == 2) {
                                moshi.setText("竞技模式");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 5 || entity.getData().getPublicStatus() == 6) {
                                    jieguo.setVisibility(View.VISIBLE);
                                } else if (entity.getData().getPublicStatus() == 4) {

                                }

                            } else if (entity.getData().getSportMode() == 3) {
                                moshi.setText("发布者是陪练");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {

                                }
                            } else if (entity.getData().getSportMode() == 4) {
                                moshi.setText("发布者找陪练");
                                moshiString = entity.getData().getSportMode() + "";
                                if (entity.getData().getPublicStatus() == 4) {

                                }
                            }
                            DecimalFormat df = new DecimalFormat("0.00");

                            name.setText(entity.getData().getPublishPlayerName());
                            lv.setText(entity.getData().getHeightLevel());
                            Glide.with(PromoterXQActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getPublishPlayerImg()).into(fabuzheImage);
                            dizhi.setText(entity.getData().getSiteAddress());

                            feiyong.setText(df.format(entity.getData().getSiteMoney()) + "元");


                            if (entity.getData().getPaySiteMoneyType() == 1) {
                                fangshi.setText("AA");
                            } else if (entity.getData().getPaySiteMoneyType() == 2) {
                                fangshi.setText("输者买单");
                            }
                            time.setText(entity.getData().getStartDays() + "    " + entity.getData().getStartWeek() + "  " + entity.getData().getStartTimes() + "-" + entity.getData().getEndTimes());
                            timelog.setText(entity.getData().getPlayTime() + "小时");
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
                                if (entity.getData().getIsPublisher() == 0) {
                                    dashang.setText(df.format(entity.getData().getTips()) + "元/人");

                                } else {
                                    dashang.setText(df.format(entity.getData().getTips()) + "元");

                                }

                            }


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
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pipeizhong));
                            } else if (entity.getData().getPublicStatus() == 2) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daichufa));
                            } else if (entity.getData().getPublicStatus() == 3) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.huodongzhong));
                            } else if (entity.getData().getPublicStatus() == 4) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.tianxiejieguo));
                            } else if (entity.getData().getPublicStatus() == 5) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiwancheng));
                            } else if (entity.getData().getPublicStatus() == 6) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.daipingjia));
                            } else if (entity.getData().getPublicStatus() == 7) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yiquxiao));
                            } else if (entity.getData().getPublicStatus() == 8) {
                                zhuangtai.setBackgroundDrawable(getResources().getDrawable(R.mipmap.querenjieshu));
                            }

                            if (entity.getData().getIsHandle().equals("0")){
                                fbtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                                tousujl.setVisibility(View.GONE);
                            }else if(entity.getData().getIsHandle().equals("1")){
                                tousu2.setVisibility(View.GONE);
                                anniuLayout.setVisibility(View.GONE);
                                fbtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                            }else if(entity.getData().getIsHandle().equals("2")){
                                tousu2.setVisibility(View.GONE);
                                anniuLayout.setVisibility(View.GONE);
                                fbtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

                            }else if(entity.getData().getIsHandle().equals("3")){
                                tousu2.setVisibility(View.GONE);
                                anniuLayout.setVisibility(View.GONE);
                                fbtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);

                            }else if(entity.getData().getIsHandle().equals("4")){

                                fbtime.setVisibility(View.VISIBLE);
                                pptime.setVisibility(View.VISIBLE);
                                kstime.setVisibility(View.VISIBLE);
                                jstime.setVisibility(View.VISIBLE);
                            }

                           if (entity.getData().getPublicStatus() == 1) {

                            } else if (entity.getData().getPublicStatus() == 2) {

                            } else if (entity.getData().getPublicStatus() == 3) {

                            } else if (entity.getData().getPublicStatus() == 4) {

                            } else if (entity.getData().getPublicStatus() == 5) {

                               jbhfy.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getPublicStatus() == 6) {

                               jbhfy.setVisibility(View.VISIBLE);

                            } else if (entity.getData().getPublicStatus() == 7) {

                               qxyy.setVisibility(View.VISIBLE);
                               jbhfy.setVisibility(View.VISIBLE);
                            } else if (entity.getData().getPublicStatus() == 8) {


                               jbhfy.setVisibility(View.VISIBLE);
                            }

                            adapter = new HDXQAAdapter(PromoterXQActivity.this, data, renshu, tag, moshiString, zhuangtaiString, uid);
                            adapterb = new HDXQBAdapter(PromoterXQActivity.this, datab, renshu, tag, moshiString, zhuangtaiString, uid);
                            gridViewA.setAdapter(adapter);
                            gridViewB.setAdapter(adapterb);
                            LogU.Ld("1608",""+datab+"=="+renshu+"=="+"====="+moshiString+"======="+zhuangtaiString+"======="+uid);
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

                            if (entity.getData().getAwinBcount() > 0) {
                                List<HDXQEntity.DataBean.AwinBuserInfoBean> list1;
                                list1 = entity.getData().getAwinBuserInfo();
                                if(!EmptyUtils.isListEmpty(list1)){
                                data1.clear();
                                data1.addAll(list1);
                                aybgrid.setAdapter(adapter1);
                                }
                            }
                            if (entity.getData().getAloseBcount() > 0) {
                                List<HDXQEntity.DataBean.AloseBuserInfoBean> list2;
                                list2 = entity.getData().getAloseBuserInfo();
                                data2.clear();
                                data2.addAll(list2);
                                asbgrid.setAdapter(adapter2);

                            }
                            if (entity.getData().getAdrawBcount() > 0) {
                                List<HDXQEntity.DataBean.AdrawBuserInfoBean> list3;
                                list3 = entity.getData().getAdrawBcountInfo();
                                data3.clear();
                                data3.addAll(list3);
                                apbgrid.setAdapter(adapter3);

                            }


                            if (entity.getData().getGetwaiver()>0){
                                List<HDXQEntity.DataBean.GetwaiverInfoBean> list4;
                                list4 = entity.getData().getGetwaiverInfo();
                                data4.clear();
                                data4.addAll(list4);
                                qiquangrid.setAdapter(adapter4);


                            }

//                            data2.addAll(list1);
//                            asbgrid.setAdapter(adapter2);
//
//                            data2.addAll(list1);
//                            apbgrid.setAdapter(adapter3);
                            LogU.Ld("1608","输赢+++"+entity.getData().getFinalresult());
                            if (entity.getData().getFinalresult() == 1) {
                                //yingText.setText("赢");
                                LogU.Ld("1608","输赢"+entity.getData().getFinalresult());
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


                        } else

                        {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            Intent intent=new Intent();
                            intent.setClass(PromoterXQActivity.this,DengluActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }




}
