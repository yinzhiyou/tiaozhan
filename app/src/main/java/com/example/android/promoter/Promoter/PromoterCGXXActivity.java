package com.example.android.promoter.Promoter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.CGXXListAdapter;
import com.example.android.promoter.Adapter.PromoterDCLAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.CGXXEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterComplainthdlistEntity;
import com.example.android.promoter.Entity.PromoterDCLEntity;
import com.example.android.promoter.Entity.PromoterYTGEntity;
import com.example.android.promoter.Home.CGXXActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MapNaviUtils;

import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.StarBar;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.view.MyScrollView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员场馆信息
 */
public class PromoterCGXXActivity extends BaseActivity implements View.OnClickListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_cgxx);
//    }

    private TextView biaoti, huodong, xinxi, name, dizhi, zoongxing, shezhi, fuwu, jiage, gengduo, dclts, clzts, zhengchang, sport_name, ts_text;
    private ImageView fanhui, cgxx_list_dianhua, wifi, linyu, tcw;
    private StarBar starBar;
    private LinearLayout xinxiLayout, biaotiLayout, gotomap, promo_cgxx_layout;
    private PromoterDCLAdapter adapter;
    private CGXXListAdapter adapter2;
    private List<PromoterComplainthdlistEntity.DataBean> data;
    private List<CGXXEntity.DataBean.CommentsBean> data2;
    private ListView listView2;
    private PullToRefreshListView listView;
    private String token, uid, nameString, scjiageString, ZMurl, FMurl, TGid, tag, isHandle = "0", tag_f;
    private double mLongitude, mLatitude;
    private SPUtils spUtils;
    private LinearLayout pingjia;
    private int page = 1;
    private CGXXEntity entity;
    private XBanner bannerView;
    private MyScrollView my_scrollview;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_cgxx;
    }

    @Override
    protected void initUIAndListener() {
        my_scrollview=findViewById(R.id.my_scrollview);
        gotomap = findViewById(R.id.gotomap);
        gotomap.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        huodong = findViewById(R.id.promo_cgxx_huodong);
        huodong.setOnClickListener(this);
        xinxi = findViewById(R.id.promo_cgxx_xinxi);
        xinxi.setOnClickListener(this);
        xinxiLayout = findViewById(R.id.promo_cgxx_xinxi_layout);
        listView = findViewById(R.id.promo_cgxx_huodong_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
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
        listView2 = findViewById(R.id.cgxx_list);
        promo_cgxx_layout = findViewById(R.id.promo_cgxx_layout);
        biaotiLayout = findViewById(R.id.promo_cgxx_layout2);
        dclts = findViewById(R.id.promo_cgxx_daichuli);
        dclts.setOnClickListener(this);
        clzts = findViewById(R.id.promo_cgxx_chulizhong);
        clzts.setOnClickListener(this);
        zhengchang = findViewById(R.id.promo_cgxx_zhengchang);
        zhengchang.setOnClickListener(this);
        cgxx_list_dianhua = findViewById(R.id.cgxx_list_dianhua);
        cgxx_list_dianhua.setOnClickListener(this);

        wifi = findViewById(R.id.wifi);
        tcw = findViewById(R.id.tcw);
        linyu = findViewById(R.id.linyu);
        ts_text = findViewById(R.id.ts_text);
        bannerView = findViewById(R.id.cgxx_banner);


        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        nameString = bundle.getString("name");
        uid = bundle.getString("uid");
        tag = bundle.getString("tag");
        isHandle = bundle.getString("isHandle");
        mLongitude = bundle.getDouble("mLongitude");
        mLatitude = bundle.getDouble("mLatitude");

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        data2 = new ArrayList<>();
        adapter2 = new CGXXListAdapter(this, data2);

        data = new ArrayList<>();
        adapter = new PromoterDCLAdapter(this, data, "2");
        listView.setAdapter(adapter);

        LogU.Ld("1608", "坐标" + mLatitude + "=======" + mLongitude);
        biaoti.setText(nameString);
        // wifi.setImageResource(R.mipmap.tingchehui);
        my_scrollview.setListView(listView);
    }

    @Override
    protected void initData() {
        if (tag.equals("2")) {
            biaotiLayout.setVisibility(View.GONE);
            ts_text.setVisibility(View.GONE);
            promo_cgxx_layout.setVisibility(View.GONE);
            xinxiLayout.setVisibility(View.VISIBLE);
        }
        // if (tag.equals("2"))

//        if (tag.equals("1")){
//
//            biaotiLayout.setVisibility(View.GONE);
//            listView.setVisibility(View.GONE);
//            xinxiLayout.setVisibility(View.VISIBLE);
//        }else{
//            biaotiLayout.setVisibility(View.VISIBLE);
//            listView.setVisibility(View.VISIBLE);
//            xinxiLayout.setVisibility(View.GONE);
//
//        }
        initDownload(isHandle, page);

        shuaxin();

        if (isHandle.equals("0")) {
            dclts.setTextColor(getResources().getColor(R.color.heise));//字体上色
            clzts.setTextColor(getResources().getColor(R.color.bbbbb));
            zhengchang.setTextColor(getResources().getColor(R.color.bbbbb));

        } else if (isHandle.equals("1")) {
            dclts.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
            clzts.setTextColor(getResources().getColor(R.color.bbbbb));
            zhengchang.setTextColor(getResources().getColor(R.color.heise));
        } else {
            dclts.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
            clzts.setTextColor(getResources().getColor(R.color.heise));
            zhengchang.setTextColor(getResources().getColor(R.color.bbbbb));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();

                break;
            case R.id.promo_cgxx_huodong:
                huodong.setTextColor(getResources().getColor(R.color.heise));//字体上色
                xinxi.setTextColor(getResources().getColor(R.color.bbbbb));
                xinxiLayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                biaotiLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.promo_cgxx_xinxi:
                huodong.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
                xinxi.setTextColor(getResources().getColor(R.color.heise));
                xinxiLayout.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                biaotiLayout.setVisibility(View.GONE);
                break;
            case R.id.promo_cgxx_daichuli:
                if (page!=1){
                    page=1;
                }
                dclts.setTextColor(getResources().getColor(R.color.heise));//字体上色
                clzts.setTextColor(getResources().getColor(R.color.bbbbb));
                zhengchang.setTextColor(getResources().getColor(R.color.bbbbb));
                initDownload("0", page);
                isHandle = "0";
                break;
            case R.id.promo_cgxx_chulizhong:
                if (page!=1){
                    page=1;
                }
                dclts.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
                clzts.setTextColor(getResources().getColor(R.color.heise));
                zhengchang.setTextColor(getResources().getColor(R.color.bbbbb));
                initDownload("2", page);
                isHandle = "2";
                break;
            case R.id.promo_cgxx_zhengchang:
                if (page!=1){
                    page=1;
                }
                dclts.setTextColor(getResources().getColor(R.color.bbbbb));//字体上色
                clzts.setTextColor(getResources().getColor(R.color.bbbbb));
                zhengchang.setTextColor(getResources().getColor(R.color.heise));
                initDownload("1", page);
                isHandle = "1";
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
            case R.id.cgxx_pingjia:
                Intent intent = new Intent();

                intent.setClass(PromoterCGXXActivity.this, PromoterGDPJActivity.class);

                intent.putExtra("uid", uid);
                startActivity(intent);
                break;
        }
    }


    private void shuaxin() {
        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page + "");

                        initDownload(isHandle + "", page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page + "");
                        initDownload(isHandle + "", page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    private void initDownload(final String isHandle, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        LogU.Ld("1608", "待处理投诉活动" + "token  " + token + "uid  " + uid + "isHandle  " + isHandle + "page  " + page);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterComplainthdlist")
                .addHeader("token", token)
                .addParams("siteUid", uid)
                .addParams("isHandle", isHandle)
                .addParams("page", page + "")
                .addParams("limit", "")
                .addParams("orderId", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "待处理投诉活动" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                        Gson gson = new Gson();

                            PromoterComplainthdlistEntity entity = gson.fromJson(result, PromoterComplainthdlistEntity.class);
                            if(entity.getOther()!=null){
                                dclts.setText("待处理投诉"+"("+entity.getOther().getStatus0()+")");
                                clzts.setText("处理中投诉"+"("+entity.getOther().getStatus2()+")");
                                // zc.setText("正常"+"("+entity.getOther().getStatus1()+")");
                            }else {
                                dclts.setText("待处理投诉"+"（"+0+"）");
                                clzts.setText("处理中投诉"+"（"+0+"）");
                                //  zc.setText("正常"+"（"+0+"）");
                            }
                            ts_text.setVisibility(View.GONE);
                            List<PromoterComplainthdlistEntity.DataBean> list;
                            list = entity.getData();
//                            data.clear();
//                            data.addAll(list);

                            if (page == 1) {
                                data.clear();
                                data.addAll(list);
                                //    listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值

                                    intent.setClass(PromoterCGXXActivity.this, PromoterXQActivity.class);

                                    bundle.putString("uuid", data.get(position - 1).getPublicUUid());
                                    bundle.putString("tag", tag);
                                    bundle.putString("isHandle", isHandle);
                                    bundle.putString("uid", uid);
                                    bundle.putString("name", nameString);

                                    intent.putExtras(bundle);

                                    startActivity(intent);
                                }
                            });
//                            Intent intent = new Intent();
//                            intent.setClass(PromoterONEActivity.this,PromoterTWOActivity.class);
//                            startActivity(intent);
                            if(EmptyUtils.isListEmpty(data)){
                                LogU.Ld("1608",""+EmptyUtils.isListEmpty(list)+ data.size()+EmptyUtils.isListEmpty(data));
                                ts_text.setVisibility(View.VISIBLE);
                            }
                            init();
                        } else {
                            data.clear();
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();

                            ts_text.setVisibility(View.VISIBLE);
                            ts_text.setText(entity.getMsg());
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

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

                            String[] filesURL = entity.getData().getFilesURL();
                            final List<String> bannerlist = new ArrayList<>();
                            if (filesURL != null) {
                                for (int i = 0; i < filesURL.length; i++) {
                                    bannerlist.add(getResources().getString(R.string.imgurl) + filesURL[i]);
                                    LogU.Le("1608", "轮播" + filesURL[i]);
                                }
                            }
                            bannerView.setData(bannerlist, null);
                            bannerView.loadImage(new XBanner.XBannerAdapter() {
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    Glide.with(PromoterCGXXActivity.this).load(bannerlist.get(position)).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);
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
                            Toast.makeText(PromoterCGXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

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
                    new AlertDialog.Builder(PromoterCGXXActivity.this)
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
                    MapNaviUtils.openBaiDuNavi(PromoterCGXXActivity.this, 0, 0, null, mLongitude, mLatitude, nameString);
//                            MapUtils.openMap(mContext,"com.baidu.BaiduMap",new LatLng(31.33260715160764,121.54777723124306,"CCB"));
                } else {
                    ToastUitl.longs("您还未安装百度地图！");
                    new AlertDialog.Builder(PromoterCGXXActivity.this)
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
