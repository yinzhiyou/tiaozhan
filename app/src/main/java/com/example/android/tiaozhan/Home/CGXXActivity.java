package com.example.android.tiaozhan.Home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.CGXXListAdapter;
import com.example.android.tiaozhan.Entity.CGXXEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Promoter.PromoterGDPJActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.StarBar;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *场馆信息
 */
public class CGXXActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, youshangjiao, name, dizhi, zoongxing, shezhi, fuwu, jiage, gengduo,ydxm;
    private ImageView fanhui, dianhua;
    private CGXXListAdapter adapter;
    private MyListView listView;
    private RelativeLayout yuding;
    private XBanner bannerView;

    private StarBar starBar;
    private final String BAIDU_MAP_APP = "com.baidu.BaiduMap";
    private final String GAODE_MAP_APP = "com.autonavi.minimap";
    private Bundle bundle;
    private String uid, cgname, hezuo, Telephone;
    private SPUtileFQTZ spUtils;
    private List<CGXXEntity.DataBean.CommentsBean> data;
    private LinearLayout pingjia,address_layout;
    private CGXXEntity entity;
    private String yId;
    private String mylat;
    private String mylng;


    @Override
    public int initContentView() {
        return R.layout.activity_cgxx;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        data = new ArrayList<>();
        adapter = new CGXXListAdapter(this, data);
        listView = findViewById(R.id.cgxx_list);
        dianhua = findViewById(R.id.cgxx_list_dianhua);
        dianhua.setOnClickListener(this);
        yuding = findViewById(R.id.cgxx_yuding);
        yuding.setOnClickListener(this);
        address_layout = findViewById(R.id.address_layout);
        address_layout.setOnClickListener(this);

        bannerView = findViewById(R.id.cgxx_banner);
        name = findViewById(R.id.cgxx_name);
        dizhi = findViewById(R.id.cgxx_dizhi);
        zoongxing = findViewById(R.id.cgxx_xing);
        shezhi = findViewById(R.id.cgxx_sheshi);
        fuwu = findViewById(R.id.cgxx_fuwu);
        jiage = findViewById(R.id.cgxx_jiage);
        starBar = findViewById(R.id.cgxx_star);
        gengduo = findViewById(R.id.cgxx_gengduo);
        pingjia = findViewById(R.id.cgxx_pingjia);
        pingjia.setOnClickListener(this);
        ydxm = findViewById(R.id.cgxx_ydxm);

        bundle = new Bundle();
        spUtils = new SPUtileFQTZ();

    }

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        uid = bundle.getString("uid");
        hezuo = bundle.getString("hezuo");
        yId = bundle.getString("yId");
        spUtils.put(this, "hezuo", hezuo + "");
        biaoti.setText("场馆信息");
        youshangjiao.setText("更正");
        youshangjiao.setVisibility(View.VISIBLE);

        init();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.youshangjiao:
                intent.setClass(this, CGGZActivity.class);
                bundle.putString("tag", "3");
                bundle.putString("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.cgxx_list_dianhua:


                if (Telephone.contains(",")) {

                    showListDialog(Telephone.split(","));
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + Telephone);
                    intent1.setData(data);
                    startActivity(intent1);
                }
                break;
            case R.id.cgxx_yuding://预定跳转

                if (hezuo.equals("1")) {
                    intent.setClass(this, StartTimeActivity.class);
                    bundle.putString("yId", yId);
                    intent.putExtra("uid", uid);
                    intent.putExtra("wuid", uid);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {


                    showNormalDialog(Telephone);

                }


                break;
            case R.id.cgxx_pingjia:
                intent.setClass(this,PromoterGDPJActivity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);

                break;

            case R.id.address_layout:

                if (isApplicationInstall(BAIDU_MAP_APP)) {
//                    goNaviByBaiDuMap(dizhiString);
                    goNaviByBaiDuMap(mylat, mylng);
                } else if (isApplicationInstall(GAODE_MAP_APP)) {

                }

                break;
        }
    }

    //场馆详细信息
    private void init() {

        LogU.Ld("1608", "场馆详细信息" + uid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSiteInfo")
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "场馆详细信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, CGXXEntity.class);
                            final String[] filesURL = entity.getData().getFilesURL();
                            LogU.Le("1608","轮播"+entity.getData().getFilesURL());
                            final List<String> bannerlist=new ArrayList<>();
                            if (filesURL!=null){
                                for(int i=0;i<filesURL.length;i++){
                                    bannerlist.add(getResources().getString(R.string.imgurl) + filesURL[i]);
                                    LogU.Le("1608","轮播"+filesURL[i]);
                                }
                            }
                            bannerView.setData(bannerlist,null);
                            bannerView.loadImage(new XBanner.XBannerAdapter() {
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    Glide.with(CGXXActivity.this).load(bannerlist.get(position)).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);
                                }
                            });
                            name.setText(entity.getData().getName());
                            dizhi.setText(entity.getData().getAddress());
                            mylng = entity.getData().getLng();
                            mylat = entity.getData().getLat();

                            spUtils.put(CGXXActivity.this, "cgname", entity.getData().getName());
                            spUtils.put(CGXXActivity.this, "cgid", uid);
                            zoongxing.setText(entity.getData().getScores() + "分");
                            shezhi.setText("设施:" + entity.getData().getEquscore() + "分");
                            fuwu.setText("服务:" + entity.getData().getEnvscore() + "分");
                            jiage.setText("价格:" + entity.getData().getXjbScore() + "分");
                            starBar.setStarMark((float) Double.parseDouble(entity.getData().getScores()),1);
                            Telephone = entity.getData().getTelephone();
                            gengduo.setText("网友点评    " +"("+ entity.getData().getCommentsCount()+")");
                            ydxm.setText(entity.getData().getSupportSportName());
                            List<CGXXEntity.DataBean.CommentsBean> list;
                            list = entity.getData().getComments();
                            data.addAll(list);
                            listView.setAdapter(adapter);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(CGXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    private void showNormalDialog(final String telephone) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.logo);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("该场馆为未合作场馆，请与场馆方联系，确定可选时间和场地费，谢谢");
        normalDialog.setPositiveButton("现在联系",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                        if (Telephone.contains(",")) {

                            showListDialog(Telephone.split(","));
                        } else {
                            Intent intent1 = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + telephone);
                            intent1.setData(data);
                            startActivity(intent1);
                        }
                    }
                });
        normalDialog.setNegativeButton("已联系",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();

                        intent.setClass(CGXXActivity.this, StartTimeActivity.class);
                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showListDialog(String[] length) {
        final String[] items = length;
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(CGXXActivity.this);
        listDialog.setTitle("请选择你要拨打的电话");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + items[which]);
                intent1.setData(data);
                startActivity(intent1);
            }
        });
        listDialog.show();
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
