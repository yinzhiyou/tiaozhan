package com.example.android.promoter.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.GRTXGridAdapter;
import com.example.android.promoter.Adapter.GRTXJishuAdapter;
import com.example.android.promoter.Adapter.GRTXList1Adapter;
import com.example.android.promoter.Adapter.GRTXList2Adapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.GRTXEntity;
import com.example.android.promoter.Entity.HaopinglvEntity;
import com.example.android.promoter.Entity.JSJBListEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.LvyuelvEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Entity.YonghuBQEntity;
import com.example.android.promoter.Entity.YonghuxinxiEntidy;
import com.example.android.promoter.My.MyGuanzhuActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.StarBar;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *点击个人头像进入的个人信息
 */
public class HomeGRTXActivity extends BaseActivity implements View.OnClickListener{


    private ImageView fanhui, jiahao,touxiang,jiahaoyouImage,guanzhuImage,xingbie,qiu;
    private LinearLayout xiala,jubao,zhengti,guanzhu,jiahaoyou,jiaheimingdan;
    private boolean tag = true;
    private MyListView listView1,listView2;
    private GRTXList1Adapter adapter1;
    private GRTXList2Adapter adapter2;
    private SPUtils spUtils;
    private String token,uid,haoyouString,dengjiString,touxiangString,uuid;
    private int guanzhuString;
    private StarBar starBar;
    private TextView lvyuelv,name,TYjinbi,dengji,xingbieText,age,shengao,tizhong,xiaiyundong,czd,jiahaoyouText,guanzhuText,tyjb,jianjie,pf;
    private GridView gridView;
    private List<GRTXEntity.DataBean.UserLabelBean> data;
    private  List<GRTXEntity.DataBean.UserTechcoinsBean> data2;
    private GRTXJishuAdapter adapter;
    private GRTXGridAdapter adapter3;
    private   Bundle bundle;
    private RelativeLayout fqtz;
    private SPUtileFQTZ spUtileFQTZ;
    private RelativeLayout beijing;
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

    @Override
    protected void initUIAndListener() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
        beijing=findViewById(R.id.beijing);
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


        name = findViewById(R.id.grtx_name);
        dengji = findViewById(R.id.grtx_dengji);
        xingbieText = findViewById(R.id.grtx_sex_text);
        age = findViewById(R.id.grtx_age);
        shengao = findViewById(R.id.grtx_shengao);
        tizhong = findViewById(R.id.grtx_tizhong);
        xiaiyundong = findViewById(R.id.grtx_xiaiyundong);
        czd = findViewById(R.id.grtx_czd);
        pf=findViewById(R.id.grade);

        data = new ArrayList<>();
        data2 = new ArrayList<>();
        adapter = new GRTXJishuAdapter(this,data2);
        adapter3 = new GRTXGridAdapter(this,data);
        bundle = new Bundle();
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", " ");

        tyjb = findViewById(R.id.grtx_tyjb);
        tyjb.setOnClickListener(this);
        spUtileFQTZ = new SPUtileFQTZ();

    }

    @Override
    protected void initData() {
//        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
//        initDownload();
        init();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.grtx_jiahao:
                if (tag){
                    xiala.setVisibility(View.VISIBLE);
                    tag = false;
                }else{
                    xiala.setVisibility(View.GONE);
                    tag = true;
                }

                break;
            case R.id.home_grtx_jubao:
                Toast.makeText(this, "点击举报", Toast.LENGTH_SHORT).show();

                intent.setClass(this,JuBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.grtx_zhengti:
                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_guanzhu://加关注
                if (guanzhuString==0){
                   guanzhu();//添加关注
                    guanzhuImage.setImageDrawable(getResources().getDrawable(R.mipmap.quxiaoguanzhu));
                    guanzhuText.setText("取消关注");
                    guanzhuString=1;
                    break;
                }

                if (guanzhuString==1){
                    quxiao();//取消关注
                    guanzhuImage.setImageDrawable(getResources().getDrawable(R.mipmap.guanzhu));
                    guanzhuText.setText("关注");
                    guanzhuString=0;
                    break;
                }

                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_jiahaoyou://加好友
                if (haoyouString.equals("0")){
                    initDownload();//添加
                }else{
                    shanchuhaoyou();//删除
                }

                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_jiaheimingdan://加黑名单
                jiahei();
                xiala.setVisibility(View.GONE);
                tag = true;
                break;
            case R.id.grtx_tyjb:

                intent.setClass(this,HomeTYJBActivity.class);
                startActivity(intent);

                break;
            case R.id.grtx_fqtz:

                intent.setClass(this,FaqiTiaozhanActivity.class);

                spUtileFQTZ.put(this, "YQtab","2");
                spUtileFQTZ.put(this, "YQtouxiang",touxiangString);
                spUtileFQTZ.put(this, "YQuuid",uuid);
                spUtileFQTZ.put(this, "YQdengji",dengjiString);
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
                .addHeader("token",token)
                .addParams("friendsId",uid)
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
                            ToastUitl.longs( entity.getMsg());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs( entity.getMsg());
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
                .addHeader("token",token)
                .addParams("uuid",uid)
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
                            ToastUitl.longs( entity.getMsg());
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast toast = Toast.makeText(HomeGRTXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                            ToastUitl.longs( entity.getMsg());
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
                .addHeader("token",token)
                .addParams("CarePlayerUUID",uid)
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
                            ToastUitl.longs( entity.getMsg());
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs( entity.getMsg());
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
                            ToastUitl.longs( entity.getMsg());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs( entity.getMsg());
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
        LogU.Ld("1608", "个人详细信息" + uid+token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserDetailInfo")
                .addHeader("token",token)
                .addParams("uuid",uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "个人详细信息" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            GRTXEntity entity = gson.fromJson(result, GRTXEntity.class);
                            List<GRTXEntity.DataBean.UserLabelBean> list2;
                            list2 = entity.getData().getUserLabel();
                            data.addAll(list2);
                            gridView.setAdapter(adapter3);
                            jianjie.setText(entity.getData().getComment());
                            Glide.with(HomeGRTXActivity.this).load(getResources().getString(R.string.imgurl)+entity.getData().getImgURL()).into(touxiang);
                            touxiangString = entity.getData().getImgURL();
                            uuid = entity.getData().getUuid();
                            dengjiString = entity.getData().getUserHightLevel().getLevel();
                            DecimalFormat df = new DecimalFormat("#.00");
                            TYjinbi.setText( df.format(entity.getData().getCommonCoins())+"");
                            name.setText(entity.getData().getNickname());
                            dengji.setText(entity.getData().getUserHightLevel().getLevel());
                            lvyuelv.setText(entity.getData().getUserPerform());
                            pf.setText(entity.getData().getUserEvaluate() + "分");
                            if (entity.getData().getSex() == 0){
                                xingbieText.setText("男");
                                xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbienan));
                            }else if (entity.getData().getSex() == 0){
                                xingbieText.setText("女");
                                xingbie.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xingbie));
                            }
                            age.setText(entity.getData().getAge()+"");
                            if (entity.getData().getTall()==0){

                            }else{
                                shengao.setText(entity.getData().getTall()+"cm");
                            }

                            if (entity.getData().getWeight() == 0){

                            }else{
                                tizhong.setText(entity.getData().getWeight()+"kg");
                            }

                            xiaiyundong.setText("                                     "+entity.getData().getFaveriteSport());
                            czd.setText(entity.getData().getAddress());
//                            JSJBListEntity entity1 = gson.fromJson(result, JSJBListEntity.class);
                            List<GRTXEntity.DataBean.UserTechcoinsBean> list;
                            list = entity.getData().getUserTechcoins();
                            data2.addAll(list);
                            listView1.setAdapter(adapter);
                            starBar.setStarMark(Float.parseFloat(entity.getData().getUserEvaluate()),1);
                            haoyouString = entity.getData().getIsFriends()+"";
                            guanzhuString = entity.getData().getIsFollow();
                            if (entity.getData().getIsFriends() == 1){
                                    jiahaoyouImage.setImageDrawable(getResources().getDrawable(R.mipmap.shanhaoyou));
                                    jiahaoyouText.setText("删除好友");
                            }

                            

                            if (entity.getData().getUserHightLevel().getSportID() == 1){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiu));
                            }else  if (entity.getData().getUserHightLevel().getSportID() == 2){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 3){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 4){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 5){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 6){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 7){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiu));
                            }else if (entity.getData().getUserHightLevel().getSportID() == 8){
                                qiu.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfu));
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs( entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

}
