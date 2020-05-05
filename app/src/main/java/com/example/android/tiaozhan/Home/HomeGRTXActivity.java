package com.example.android.tiaozhan.Home;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.GRTXGridAdapter;
import com.example.android.tiaozhan.Adapter.GRTXJishuAdapter;
import com.example.android.tiaozhan.Adapter.GRTXList1Adapter;
import com.example.android.tiaozhan.Adapter.GRTXList2Adapter;
import com.example.android.tiaozhan.Entity.GRTXEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.NetUtil;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.StarBar;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.fuyin.adapter.HomeIndexAdapter;
import com.example.android.tiaozhan.Toos.fuyin.base.BaseHelper;
import com.example.android.tiaozhan.Toos.fuyin.constans.P;
import com.example.android.tiaozhan.Toos.fuyin.interfaces.OnItemPictureClickListener;
import com.example.android.tiaozhan.Toos.fuyin.model.Girl;
import com.example.android.tiaozhan.Toos.fuyin.ui.ImagePreviewActivity;
import com.example.android.tiaozhan.Toos.fuyin.utils.Utils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 点击个人头像进入的个人信息
 */
public class HomeGRTXActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dialog;
    private TextView ds_xz;
    private RecyclerView recyclerView;
    private HomeIndexAdapter homeIndexAdapter;
    private List<String> imageList;
    private List<Girl> girlList;
    private Bundle mReenterState;
    private int itemPosition;

    private TextView have_partner, look_partner, fbu_text;
    private View my_look_partner, my_have_partner;
    private ImageView fanhui, jiahao, touxiang, jiahaoyouImage, guanzhuImage, xingbie, qiu;
    private LinearLayout xiala, jubao, zhengti, guanzhu, jiahaoyou, jiaheimingdan, geren_zl, zp_layout, faqi_layout;
    private boolean tag = true;
    private MyListView listView1, listView2;
    private GRTXList1Adapter adapter1;
    private GRTXList2Adapter adapter2;
    private SPUtils spUtils;
    private String token, uid, haoyouString, dengjiString, touxiangString, uuid, grId;
    private int guanzhuString;
    private StarBar starBar;
    private TextView lvyuelv, name, TYjinbi, dengji, xingbieText, age, shengao, tizhong, xiaiyundong, czd, jiahaoyouText, guanzhuText, tyjb, jianjie, pf;
    private GridView gridView;
    private List<GRTXEntity.DataBean.UserLabelBean> data;
    private List<GRTXEntity.DataBean.UserTechcoinsBean> data2;
    private GRTXJishuAdapter adapter;
    private GRTXGridAdapter adapter3;
    private Bundle bundle;
    private RelativeLayout fqtz;
    private SPUtileFQTZ spUtileFQTZ;
    private RelativeLayout beijing;
    private List<String> personalImgurl;
    private View bj_line;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_grtx);
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_home_grtx;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initUIAndListener() {
        StatusBarUtil.setColor(HomeGRTXActivity.this, getResources().getColor(R.color.my_tab), 0);

        //  StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
        //  geren_zl = findViewById(R.id.geren_zl);
        geren_zl = findViewById(R.id.geren_zl);

        my_have_partner = findViewById(R.id.my_have_partner);
        my_look_partner = findViewById(R.id.my_look_partner);
        look_partner = findViewById(R.id.look_partner);
        look_partner.setOnClickListener(this);
        have_partner = findViewById(R.id.have_partner);
        have_partner.setOnClickListener(this);
        fbu_text = findViewById(R.id.fbu_text);

        beijing = findViewById(R.id.beijing);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        jiahao = findViewById(R.id.grtx_jiahao);
        jiahao.setOnClickListener(this);
        xiala = findViewById(R.id.grtx_xiala);
        listView1 = findViewById(R.id.grtx_list1);
        listView2 = findViewById(R.id.grtx_list2);
//        adapter1 = new GRTXList1Adapter(this);
        adapter2 = new GRTXList2Adapter(this);
        jubao = findViewById(R.id.home_grtx_jubao);
        jubao.setOnClickListener(this);
        touxiang = findViewById(R.id.grtx_touxiang);
        starBar = findViewById(R.id.starBar);
        lvyuelv = findViewById(R.id.grtx_lvyuelv);
        gridView = findViewById(R.id.grtx_gridview);
        zhengti = findViewById(R.id.grtx_zhengti);
        zhengti.setOnClickListener(this);
        guanzhu = findViewById(R.id.grtx_guanzhu);
        guanzhu.setOnClickListener(this);
        jiahaoyou = findViewById(R.id.grtx_jiahaoyou);
        jiahaoyou.setOnClickListener(this);
        jiaheimingdan = findViewById(R.id.grtx_jiaheimingdan);
        jiaheimingdan.setOnClickListener(this);
        jiahaoyouImage = findViewById(R.id.grtx_jiahaoyou_image);
        jiahaoyouText = findViewById(R.id.grtx_jiahaoyou_text);
        guanzhuImage = findViewById(R.id.grtx_guanzhu_image);
        guanzhuText = findViewById(R.id.grtx_guanzhu_text);
        TYjinbi = findViewById(R.id.grtx_jinbi);
        xingbie = findViewById(R.id.grtx_xingbie);
        qiu = findViewById(R.id.grtx_qiu);
        jianjie = findViewById(R.id.grtx_jianjie);
        fqtz = findViewById(R.id.grtx_fqtz);
        fqtz.setOnClickListener(this);
        zp_layout = findViewById(R.id.zp_layout);
        faqi_layout = findViewById(R.id.faqi_layout);


        name = findViewById(R.id.grtx_name);
        dengji = findViewById(R.id.grtx_dengji);
        xingbieText = findViewById(R.id.grtx_sex_text);
        age = findViewById(R.id.grtx_age);
        shengao = findViewById(R.id.grtx_shengao);
        tizhong = findViewById(R.id.grtx_tizhong);
        xiaiyundong = findViewById(R.id.grtx_xiaiyundong);
        czd = findViewById(R.id.grtx_czd);
        pf = findViewById(R.id.grade);
        bj_line = findViewById(R.id.bj_line);

        data = new ArrayList<>();
        data2 = new ArrayList<>();
        adapter = new GRTXJishuAdapter(this, data2);
        adapter3 = new GRTXGridAdapter(this, data);
        bundle = new Bundle();
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", " ");
        grId = (String) spUtils.get(this, "uuid", " ");
        tyjb = findViewById(R.id.grtx_tyjb);
        tyjb.setOnClickListener(this);
        spUtileFQTZ = new SPUtileFQTZ();

        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    protected void initData() {
//        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        bundle = this.getIntent().getExtras();

        uid = bundle.getString("uid");
//        initDownload();
        init();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {

            case R.id.look_partner://个人资料
                look_partner.setTextColor(getResources().getColor(R.color.my_tab));

                look_partner.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                geren_zl.setVisibility(View.VISIBLE);
                zp_layout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                //取消加粗
                have_partner.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                have_partner.setTextColor(getResources().getColor(R.color.huise));

                my_have_partner.setVisibility(View.INVISIBLE);
                my_look_partner.setVisibility(View.VISIBLE);

                break;
            case R.id.have_partner://相册
                geren_zl.setVisibility(View.GONE);
                if (!EmptyUtils.isListEmpty(personalImgurl)) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    zp_layout.setVisibility(View.VISIBLE);
                }
                if (grId.equals(uuid)) {
                    bj_line.setVisibility(View.GONE);

                }
                //
                have_partner.setTextColor(getResources().getColor(R.color.my_tab));

                look_partner.setTextColor(getResources().getColor(R.color.huise));

                have_partner.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                //取消加粗
                look_partner.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                my_look_partner.setVisibility(View.INVISIBLE);
                my_have_partner.setVisibility(View.VISIBLE);

                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.grtx_jiahao:
                if (tag) {
                    xiala.setVisibility(View.VISIBLE);
                    tag = false;
                } else {
                    xiala.setVisibility(View.GONE);
                    tag = true;
                }

                break;
            case R.id.home_grtx_jubao:
                Toast.makeText(this, "点击举报", Toast.LENGTH_SHORT).show();

                intent.setClass(this, JuBaoActivity.class);
                bundle.putString("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_zhengti:
                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_guanzhu://加关注
                if (guanzhuString == 0) {
                    guanzhu();//添加关注
                    guanzhuImage.setImageDrawable(getResources().getDrawable(R.mipmap.quxiaoguanzhu));
                    guanzhuText.setText("取消关注");
                    guanzhuString = 1;

                }

                if (guanzhuString == 1) {
                    quxiao();//取消关注
                    guanzhuImage.setImageDrawable(getResources().getDrawable(R.mipmap.guanzhu));
                    guanzhuText.setText("关注");
                    guanzhuString = 0;

                }

                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_jiahaoyou://加好友
                if (haoyouString.equals("0")) {
                    initDownload();//添加
                } else {
                    shanchuhaoyou();//删除
                }

                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_jiaheimingdan://加黑名单
                quxiaoCG();

                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_tyjb:

                intent.setClass(this, HomeTYJBActivity.class);
                startActivity(intent);

                break;
            case R.id.grtx_fqtz:

                intent.setClass(this, FaqiTiaozhanActivity.class);

                spUtileFQTZ.put(this, "YQtab", "2");
                spUtileFQTZ.put(this, "YQtouxiang", touxiangString);
                spUtileFQTZ.put(this, "YQuuid", uuid);
                spUtileFQTZ.put(this, "YQdengji", dengjiString);
                bundle.putString("tagTZ", "1");
                intent.putExtras(bundle);
                startActivity(intent);

                break;

        }
    }

    //添加好友
    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "添加好友" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addFriends")
                .addHeader("token", token)
                .addParams("friendsId", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "添加好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs(entity.getMsg());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs(entity.getMsg());
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //删除好友
    private void shanchuhaoyou() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "删除好友" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/delUserFriends")
                .addHeader("token", token)
                .addParams("uuid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "删除好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs(entity.getMsg());
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs(entity.getMsg());
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //关注
    private void guanzhu() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "关注" + uid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAddFollow")
                .addHeader("token", token)
                .addParams("CarePlayerUUID", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "关注" + result);
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
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void quxiao() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "取消关注" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/cancelFollow")
                .addHeader("token", token)
                .addParams("followUUID", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消关注" + result);
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
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //加入黑名单
    private void jiahei() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "加入黑名单" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addBlackLst")
                .addHeader("token", token)
                .addParams("uuid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "加入黑名单" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs( entity.getMsg());
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs( entity.getMsg());
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
                    }
                });

    }

    //基本信息
    private void init() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "个人详细信息" + uid + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserDetailInfo")
                .addHeader("token", token)
                .addParams("uuid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "个人详细信息" + result);
                        SPUtils.put(HomeGRTXActivity.this, "HomeGRTXActivity.JBXX", result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            GRTXEntity entity = gson.fromJson(result, GRTXEntity.class);
                            List<GRTXEntity.DataBean.UserLabelBean> list2;
                            list2 = entity.getData().getUserLabel();
                            data.addAll(list2);
                            gridView.setAdapter(adapter3);
                            touxiangString = entity.getData().getImgURL();
                            personalImgurl = entity.getData().getPersonalImgurl();
                            initShareElement();

                            initData1();
                            doLogic();
                            if (!EmptyUtils.isListEmpty(personalImgurl)) {
                                for (int i = 0; i < personalImgurl.size(); i++) {
                                    imageList.add(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                                    LogU.Ld("1608", "照片" + getResources().getString(R.string.imgurl) + personalImgurl.get(i) + "===" + imageList);
                                }

                            }
                            LogU.Ld("1608", "照片" + "===" + imageList + "===" + personalImgurl + "====" + !EmptyUtils.isListEmpty(personalImgurl));

                            if (!EmptyUtils.isStrEmpty(touxiangString)) {
                                String substring = touxiangString.substring(0, 4);
                                if (substring.equals("http")) {
                                    Glide.with(HomeGRTXActivity.this).load(entity.getData().getImgURL()).into(touxiang);

                                } else {
                                    Glide.with(HomeGRTXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getImgURL()).into(touxiang);
                                }
                            }


                            uuid = entity.getData().getUuid();
                            LogU.Ld("1608", "uuid" + "===" + uuid + "====" + uid);
                            if (grId.equals(uuid)) {
                                faqi_layout.setVisibility(View.GONE);
                                jiahao.setVisibility(View.GONE);
                            } else {
                                faqi_layout.setVisibility(View.VISIBLE);
                                jiahao.setVisibility(View.VISIBLE);
                            }
                            dengjiString = entity.getData().getUserHightLevel().getLevel();
                            DecimalFormat df = new DecimalFormat("#.00");
                            if (!EmptyUtils.isStrEmpty(entity.getData().getCommonCoins())) {
                                DecimalFormat format = new DecimalFormat("0.00");
                                String aformat = format.format(new BigDecimal(entity.getData().getCommonCoins()));
                                //    String format = String.format("%.2f", entity.getData().getCommonCoins());
                                TYjinbi.setText(aformat);
                            } else {
                                TYjinbi.setText("0.00");
                            }
                            //  String.format("%.2f", entity.getData().getCommonCoins());

                            name.setText(entity.getData().getNickname());
                            dengji.setText(entity.getData().getUserHightLevel().getLevel());
                            lvyuelv.setText(entity.getData().getUserPerform());
                            if (!EmptyUtils.isStrEmpty(entity.getData().getUserEvaluate())) {
                                DecimalFormat format1 = new DecimalFormat("0.0");
                                String aformat1 = format1.format(new BigDecimal(entity.getData().getUserEvaluate()));
                                pf.setText(aformat1);
                            }


                            if (entity.getData().getSex() == 0) {
                                xingbieText.setText("男");
                                xingbie.setVisibility(View.VISIBLE);
                                xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbienan));
                            } else if (entity.getData().getSex() == 1) {
                                xingbieText.setText("女");
                                xingbie.setVisibility(View.VISIBLE);
                                xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbie));
                            } else {
                                xingbieText.setText("未选择");
                                xingbie.setVisibility(View.GONE);
                            }
                            if (entity.getData().getAge().equals("0")) {
                                age.setText("未选择");
                            } else {
                                age.setText(entity.getData().getAge() + "");
                            }
                            if (EmptyUtils.isStrEmpty(entity.getData().getTall()) || entity.getData().getTall().equals("0")) {
                                shengao.setText("未填写");
                            } else {
                                shengao.setText(entity.getData().getTall() + "cm");
                            }

                            if (EmptyUtils.isStrEmpty(entity.getData().getWeight()) || entity.getData().getWeight().equals("0")) {
                                tizhong.setText("未填写");
                            } else {
                                tizhong.setText(entity.getData().getWeight() + "kg");
                            }

                            if (!EmptyUtils.isStrEmpty(entity.getData().getFaveriteSport())) {
                                String gRJs = "技术水平自我评定:   " + entity.getData().getFaveriteSport();
                                SpannableStringBuilder style_zPL = new SpannableStringBuilder(gRJs);

                                ForegroundColorSpan colorSpan_style_zPL = new ForegroundColorSpan(Color.parseColor("#9B9B9B"));
                                ForegroundColorSpan colorSpan_style_zPL2 = new ForegroundColorSpan(Color.parseColor("#333333"));
                                style_zPL.setSpan(colorSpan_style_zPL, 0, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                style_zPL.setSpan(colorSpan_style_zPL2, 9, gRJs.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                                xiaiyundong.setText(style_zPL);
                            } else {
                                xiaiyundong.setText("技术水平自我评定:   " + "拿不出手");
                            }
                            if (!EmptyUtils.isStrEmpty(entity.getData().getAddress())) {
                                czd.setText(entity.getData().getAddress());
                            } else {
                                czd.setText("未选择");
                            }
                            if (!EmptyUtils.isStrEmpty(entity.getData().getComment())) {

                                String gRjianJ = "个人简介:   " + entity.getData().getComment();
                                SpannableStringBuilder style_zPL = new SpannableStringBuilder(gRjianJ);

                                ForegroundColorSpan colorSpan_style_zPL = new ForegroundColorSpan(Color.parseColor("#9B9B9B"));
                                ForegroundColorSpan colorSpan_style_zPL2 = new ForegroundColorSpan(Color.parseColor("#333333"));
                                style_zPL.setSpan(colorSpan_style_zPL, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                style_zPL.setSpan(colorSpan_style_zPL2, 5, gRjianJ.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                                jianjie.setText(style_zPL);
                            } else {
                                jianjie.setText("个人简介:   " + "无言以对");
                            }
//                            JSJBListEntity entity1 = gson.fromJson(result, JSJBListEntity.class);
                            List<GRTXEntity.DataBean.UserTechcoinsBean> list;
                            list = entity.getData().getUserTechcoins();
                            data2.addAll(list);
                            listView1.setAdapter(adapter);
                            starBar.setStarMark(Float.parseFloat(entity.getData().getUserEvaluate()), 1);
                            haoyouString = entity.getData().getIsFriends() + "";
                            guanzhuString = entity.getData().getIsFollow();
                            if (entity.getData().getIsFriends() == 1) {
                                jiahaoyouImage.setImageDrawable(getResources().getDrawable(R.mipmap.shanhaoyou));
                                jiahaoyouText.setText("删除好友");
                            }


                            if (entity.getData().getUserHightLevel().getSportID() == 1) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 2) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 3) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 4) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 5) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 6) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 7) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiu));
                            } else if (entity.getData().getUserHightLevel().getSportID() == 8) {
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfu));
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    private void doLogic() {
        BaseHelper.setLinearLayoutManagerVertical(this, recyclerView, homeIndexAdapter);
    }

    private void initData1() {
        imageList = new ArrayList<>();
       /* imageList.add("http://cuimg.zuyushop.com/cuxiaoPic/201511/2015110010091817554.jpg");
        imageList.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJlbKx2qIGStWAAePuU7wk_cAALHzQF9mKIAB4_R763.jpg");
        imageList.add("http://img1.imgtn.bdimg.com/it/u=3356331771,2093090619&fm=214&gp=0.jpg");
        imageList.add("http://img5.duitang.com/uploads/item/201405/12/20140512000053_axANX.thumb.700_0.jpeg");

       imageList.add("http://pic2.16pic.com/00/54/76/16pic_5476585_b.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170711/3f177d2be18143a48a9af1217e669855_th.png");
        imageList.add("http://img4.duitang.com/uploads/item/201509/26/20150926014223_BW8EG.jpeg");
        imageList.add("http://mvimg10.meitudata.com/569b9090af0526344.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170703/c8c1818222a547f78585f9b357c93613_th.png");
*/

        LogU.Ld("1608", "照片" + "===" + imageList + "===" + personalImgurl + "====" + !EmptyUtils.isListEmpty(personalImgurl));

        girlList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Girl girl = new Girl();
            girl.setName("梁超杰第" + (i + 1) + "条说说!");
            girl.setImageList(imageList);
            girlList.add(girl);
        }

        homeIndexAdapter = new HomeIndexAdapter(this, girlList, new OnItemPictureClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemPictureClick(int item, int position, String url, List<String> urlList, ImageView imageView) {
                itemPosition = item;
                Intent intent = new Intent(HomeGRTXActivity.this, ImagePreviewActivity.class);
                intent.putStringArrayListExtra("imageList", (ArrayList<String>) urlList);
                intent.putExtra(P.START_ITEM_POSITION, itemPosition);
                intent.putExtra(P.START_IAMGE_POSITION, position);
                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(HomeGRTXActivity.this, imageView, imageView.getTransitionName());
                startActivity(intent, compat.toBundle());
            }
        });
    }

    private List<String> wrapList(int position, List<String> imageList) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            if (i > position) {
                break;
            } else {
                stringList.add(imageList.get(i));
            }
        }
        return stringList;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initShareElement() {

        setExitSharedElementCallback(mCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
        mReenterState = new Bundle(data.getExtras());
        int startingPosition = mReenterState.getInt(P.CURRENT_ITEM_POSITION);
        if (startingPosition != itemPosition) {//如果不是同一个item就滚动到指定的item
            recyclerView.scrollToPosition(itemPosition);
        }
        postponeEnterTransition();
        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                recyclerView.requestLayout();
                startPostponedEnterTransition();
                return true;
            }
        });
    }


    private final SharedElementCallback mCallback = new SharedElementCallback() {

        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mReenterState != null) {
                //从别的界面返回当前界面
                int startingPosition = mReenterState.getInt(P.START_IAMGE_POSITION);
                int currentPosition = mReenterState.getInt(P.CURRENT_IAMGE_POSITION);
                if (startingPosition != currentPosition) {//如果不是之前的那张图片就切换到指定的图片
                    String newTransitionName = Utils.getNameByPosition(itemPosition, currentPosition);
                    View view = recyclerView.findViewWithTag(newTransitionName);
                    if (view != null) {
                        names.clear();
                        names.add(newTransitionName);
                        sharedElements.clear();
                        sharedElements.put(newTransitionName, view);
                    }
                }
                mReenterState = null;
            }
        }
    };

    @Override
    protected void onResume() {
        String jBXX = (String) SPUtils.get(HomeGRTXActivity.this, "HomeGRTXActivity.JBXX", "");
        if (NetUtil.getNetWorkStart(HomeGRTXActivity.this) == 1) {
            if (!EmptyUtils.isStrEmpty(jBXX)) {
                Gson gson = new Gson();
                GRTXEntity entity = gson.fromJson(jBXX, GRTXEntity.class);
                List<GRTXEntity.DataBean.UserLabelBean> list2;
                list2 = entity.getData().getUserLabel();
                data.addAll(list2);
                gridView.setAdapter(adapter3);
                touxiangString = entity.getData().getImgURL();
                personalImgurl = entity.getData().getPersonalImgurl();
                initShareElement();

                initData1();
                doLogic();
                if (!EmptyUtils.isListEmpty(personalImgurl)) {
                    for (int i = 0; i < personalImgurl.size(); i++) {
                        imageList.add(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                        LogU.Ld("1608", "照片" + getResources().getString(R.string.imgurl) + personalImgurl.get(i) + "===" + imageList);
                    }

                }
                LogU.Ld("1608", "照片" + "===" + imageList + "===" + personalImgurl + "====" + !EmptyUtils.isListEmpty(personalImgurl));

                if (!EmptyUtils.isStrEmpty(touxiangString)) {
                    String substring = touxiangString.substring(0, 4);
                    if (substring.equals("http")) {
                        Glide.with(HomeGRTXActivity.this).load(entity.getData().getImgURL()).into(touxiang);

                    } else {
                        Glide.with(HomeGRTXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getImgURL()).into(touxiang);
                    }
                }


                uuid = entity.getData().getUuid();
                LogU.Ld("1608", "uuid" + "===" + uuid + "====" + uid);
                if (grId.equals(uuid)) {
                    faqi_layout.setVisibility(View.GONE);
                } else {
                    faqi_layout.setVisibility(View.VISIBLE);
                }
                dengjiString = entity.getData().getUserHightLevel().getLevel();
                DecimalFormat df = new DecimalFormat("#.00");
                if (!EmptyUtils.isStrEmpty(entity.getData().getCommonCoins())) {
                    DecimalFormat format = new DecimalFormat("0.00");
                    String aformat = format.format(new BigDecimal(entity.getData().getCommonCoins()));
                    //    String format = String.format("%.2f", entity.getData().getCommonCoins());
                    TYjinbi.setText(aformat);
                } else {
                    TYjinbi.setText("0.00");
                }
                //  String.format("%.2f", entity.getData().getCommonCoins());

                name.setText(entity.getData().getNickname());
                dengji.setText(entity.getData().getUserHightLevel().getLevel());
                lvyuelv.setText(entity.getData().getUserPerform());
                if (!EmptyUtils.isStrEmpty(entity.getData().getUserEvaluate())) {
                    DecimalFormat format1 = new DecimalFormat("0.0");
                    String aformat1 = format1.format(new BigDecimal(entity.getData().getUserEvaluate()));
                    pf.setText(aformat1);
                }
                if (entity.getData().getSex() == 0) {
                    xingbieText.setText("男");
                    xingbie.setVisibility(View.VISIBLE);
                    xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbienan));
                } else if (entity.getData().getSex() == 1) {
                    xingbieText.setText("女");
                    xingbie.setVisibility(View.VISIBLE);
                    xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbie));
                } else {
                    xingbieText.setText("未选择");
                    xingbie.setVisibility(View.GONE);
                }
                if (entity.getData().getAge().equals("0")) {
                    age.setText("未选择");
                } else {
                    age.setText(entity.getData().getAge() + "");
                }
                if (EmptyUtils.isStrEmpty(entity.getData().getTall()) || entity.getData().getTall().equals("0")) {
                    shengao.setText("未填写");
                } else {
                    shengao.setText(entity.getData().getTall() + "cm");
                }

                if (EmptyUtils.isStrEmpty(entity.getData().getWeight()) || entity.getData().getWeight().equals("0")) {
                    tizhong.setText("未填写");
                } else {
                    tizhong.setText(entity.getData().getWeight() + "kg");
                }

                if (!EmptyUtils.isStrEmpty(entity.getData().getFaveriteSport())) {
                    String gRJs = "技术水平自我评定:   " + entity.getData().getFaveriteSport();
                    SpannableStringBuilder style_zPL = new SpannableStringBuilder(gRJs);

                    ForegroundColorSpan colorSpan_style_zPL = new ForegroundColorSpan(Color.parseColor("#9B9B9B"));
                    ForegroundColorSpan colorSpan_style_zPL2 = new ForegroundColorSpan(Color.parseColor("#333333"));
                    style_zPL.setSpan(colorSpan_style_zPL, 0, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    style_zPL.setSpan(colorSpan_style_zPL2, 9, gRJs.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                    xiaiyundong.setText(style_zPL);
                } else {
                    xiaiyundong.setText("技术水平自我评定:   " + "拿不出手");
                }
                if (!EmptyUtils.isStrEmpty(entity.getData().getAddress())) {
                    czd.setText(entity.getData().getAddress());
                } else {
                    czd.setText("未选择");
                }
                if (!EmptyUtils.isStrEmpty(entity.getData().getComment())) {

                    String gRjianJ = "个人简介:   " + entity.getData().getComment();
                    SpannableStringBuilder style_zPL = new SpannableStringBuilder(gRjianJ);

                    ForegroundColorSpan colorSpan_style_zPL = new ForegroundColorSpan(Color.parseColor("#9B9B9B"));
                    ForegroundColorSpan colorSpan_style_zPL2 = new ForegroundColorSpan(Color.parseColor("#333333"));
                    style_zPL.setSpan(colorSpan_style_zPL, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    style_zPL.setSpan(colorSpan_style_zPL2, 5, gRjianJ.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                    jianjie.setText(style_zPL);
                } else {
                    jianjie.setText("个人简介:   " + "无言以对");
                }
//                            JSJBListEntity entity1 = gson.fromJson(result, JSJBListEntity.class);
                List<GRTXEntity.DataBean.UserTechcoinsBean> list;
                list = entity.getData().getUserTechcoins();
                data2.addAll(list);
                listView1.setAdapter(adapter);
                starBar.setStarMark(Float.parseFloat(entity.getData().getUserEvaluate()), 1);
                haoyouString = entity.getData().getIsFriends() + "";
                guanzhuString = entity.getData().getIsFollow();
                if (entity.getData().getIsFriends() == 1) {
                    jiahaoyouImage.setImageDrawable(getResources().getDrawable(R.mipmap.shanhaoyou));
                    jiahaoyouText.setText("删除好友");
                }


                if (entity.getData().getUserHightLevel().getSportID() == 1) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 2) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 3) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 4) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 5) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 6) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 7) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiu));
                } else if (entity.getData().getUserHightLevel().getSportID() == 8) {
                    qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfu));
                }

            }
        }
        super.onResume();
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


        ds_xz.setText("加入黑名单，你将不再收到对方的消息和活动通知，并且不再看到对方动态更新");

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
                jiahei();
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

}
