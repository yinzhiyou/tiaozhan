package com.example.android.promoter.Promoter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.CGXXListAdapter;
import com.example.android.promoter.Adapter.PromoterDCLAdapter;
import com.example.android.promoter.Entity.CGXXEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterDCLEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MapNaviUtils;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.StarBar;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PromoterFcgXXActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti,name, dizhi, zoongxing, shezhi, fuwu, jiage, gengduo,  sport_name;
    private ImageView fanhui, wifi, linyu, tcw,cgxx_list_dianhua;
    private StarBar starBar;
    private LinearLayout  gotomap;

    private List<CGXXEntity.DataBean.CommentsBean> data2;
    private ListView listView2;
    private CGXXListAdapter adapter2;
    private String token, uid, nameString, isHandle = "0";
    private double mLongitude, mLatitude;
    private SPUtils spUtils;
    private LinearLayout pingjia;
    private int page = 1;
    private CGXXEntity entity;
    private XBanner bannerView;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_fcg_xx;
    }

    @Override
    protected void initUIAndListener() {

        gotomap = findViewById(R.id.gotomap);
        gotomap.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);


        sport_name = findViewById(R.id.sport_name);
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
        cgxx_list_dianhua = findViewById(R.id.cgxx_list_dianhua);
        cgxx_list_dianhua.setOnClickListener(this);


        listView2 = findViewById(R.id.cgxx_list);
        data2 = new ArrayList<>();
        adapter2 = new CGXXListAdapter(this, data2);


        wifi = findViewById(R.id.wifi);
        tcw = findViewById(R.id.tcw);
        linyu = findViewById(R.id.linyu);
        bannerView = findViewById(R.id.cgxx_banner);


        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        nameString = bundle.getString("name");
        uid = bundle.getString("uid");

        isHandle = bundle.getString("isHandle");
        mLongitude = bundle.getDouble("mLongitude");
        mLatitude = bundle.getDouble("mLatitude");

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");


        LogU.Ld("1608", "坐标" + mLatitude + "=======" + mLongitude);
        biaoti.setText(nameString);
        // wifi.setImageResource(R.mipmap.tingchehui);
    }

    @Override
    protected void initData() {

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();

                break;



            case R.id.cgxx_list_dianhua:
                if (!EmptyUtils.isStrEmpty(entity.getData().getTelephone())) {
                    showBottomDialog();
                } else {
                    ToastUitl.longs("此场馆没有联系电话!");
                }

                break;
            case R.id.gotomap:
                showMapBottomDialog();

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

                            entity.getData().getFilesURL();
                            String[] filesURL = entity.getData().getFilesURL();
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
                                    Glide.with(PromoterFcgXXActivity.this).load(bannerlist.get(position)).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);
                                }
                            });
                            name.setText(entity.getData().getName());
                            dizhi.setText(entity.getData().getAddress());
//                            spUtils.put(CGXXActivity.this, "cgname", entity.getData().getName());
//                            spUtils.put(CGXXActivity.this, "cgid", uid);
                            zoongxing.setText(entity.getData().getScores() + "分");
                            shezhi.setText("设施:" + entity.getData().getEquscore() + "分");
                            fuwu.setText("环境:" + entity.getData().getEnvscore() + "分");
                            jiage.setText("性价比:" + entity.getData().getXjbScore() + "分");
                            starBar.setStarMark((float) Double.parseDouble(entity.getData().getScores()), 1);
//                            Telephone = entity.getData().getTelephone();
                            sport_name.setText(entity.getData().getSupportSportName());
                            gengduo.setText("更多评价    " + entity.getData().getCommentsCount());

                            if (entity.getData().getSiteInfoext().getParking() == 1) {
                                tcw.setImageResource(R.mipmap.tingchehong);
                            } else {
                                tcw.setImageResource(R.mipmap.tingchehui);
                            }
                            if (entity.getData().getSiteInfoext().getWifi() == 1) {
                                wifi.setImageResource(R.mipmap.wifihong);
                            } else {
                                wifi.setImageResource(R.mipmap.wifihui);
                            }
                            if (entity.getData().getSiteInfoext().getShower() == 1) {
                                linyu.setImageResource(R.mipmap.linyuhong);
                            } else {
                                linyu.setImageResource(R.mipmap.linyuhui);
                            }

                            List<CGXXEntity.DataBean.CommentsBean> list;
                            list = entity.getData().getComments();
                            data2.addAll(list);
                            listView2.setAdapter(adapter2);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterFcgXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_custom_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_phone = dialog.findViewById(R.id.tv_phone);
        tv_phone.setText("呼叫  " + entity.getData().getTelephone());
        dialog.show();

        dialog.findViewById(R.id.tv_phone).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + entity.getData().getTelephone());
                intent.setData(data);
                startActivity(intent);

                dialog.dismiss();
            }
        });


        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    private void showMapBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_map_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        dialog.show();

        dialog.findViewById(R.id.tv_gaode).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (MapNaviUtils.isGdMapInstalled()) {
                    // MapNaviUtils.openGaoDeNavi(PromoterCGXXActivity.this,0,0,null,mLongitude,mLatitude,nameString);
//                            MapUtils.openMap(mContext,"com.autonavi.minimap",new LatLng(31.33260711060764,121.54777721524306,"CCB"));

                    LatLng endPoint = BD2GCJ(new LatLng(mLatitude, mLongitude));//坐标转换
                    StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
                    stringBuffer.append("&lat=").append(endPoint.latitude)
                            .append("&lon=").append(endPoint.longitude).append("&keywords=" + nameString)
                            .append("&dev=").append(0)
                            .append("&style=").append(2);
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
                    intent.setPackage("com.autonavi.minimap");
                    startActivity(intent);


                } else {
                    ToastUitl.longs("您还未安装高德地图！");
                    new AlertDialog.Builder(PromoterFcgXXActivity.this)
                            .setMessage("下载高德地图？")
                            .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MapNaviUtils.DOWNLOAD_GAODE_MAP)));
                                }
                            })

                            .setNegativeButton("取消", null)
                            .show();
                }

                dialog.dismiss();
            }
        });


        dialog.findViewById(R.id.tv_baidu).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (MapNaviUtils.isBaiduMapInstalled()) {
                    MapNaviUtils.openBaiDuNavi(PromoterFcgXXActivity.this, 0, 0, null, mLongitude, mLatitude, nameString);
//                            MapUtils.openMap(mContext,"com.baidu.BaiduMap",new LatLng(31.33260715160764,121.54777723124306,"CCB"));
                } else {
                    ToastUitl.longs("您还未安装百度地图！");
                    new AlertDialog.Builder(PromoterFcgXXActivity.this)
                            .setMessage("下载百度地图？")

                            .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MapNaviUtils.DOWNLOAD_BAIDU_MAP)));
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }


                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    /**
     * BD-09 坐标转换成 GCJ-02 坐标
     */
    public static LatLng BD2GCJ(LatLng bd) {
        double x = bd.longitude - 0.0065, y = bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);

        double lng = z * Math.cos(theta);//lng
        double lat = z * Math.sin(theta);//lat
        return new LatLng(lat, lng);
    }

    /**
     * GCJ-02 坐标转换成 BD-09 坐标
     */
    public static LatLng GCJ2BD(LatLng bd) {
        double x = bd.longitude, y = bd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new LatLng(tempLat, tempLon);
    }


}
