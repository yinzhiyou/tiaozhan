package com.example.android.tiaozhan.Promoter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.example.android.tiaozhan.Adapter.MyPromterCGListAdapter;
import com.example.android.tiaozhan.Entity.PromoterFJCGEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员附近场馆
 */
public class PromoterFJCGActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_fjcg);
//    }

    private PullToRefreshListView listView;
    private MyPromterCGListAdapter adapter;
    private TextView biaoti, wu_tezt;
    private ImageView fanhui;
    private double mLatitude, mylat, mylong;
    private double mLongitude;
    private float mCurrentX;
    private BaiduMap mBaiduMap;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;

    private List<PromoterFJCGEntity.DataBean> data;
    private int page = 1;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_fjcg;
    }

    @Override
    protected void initUIAndListener() {

        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = findViewById(R.id.pro_fjcg_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        wu_tezt = findViewById(R.id.wu_text);
        data = new ArrayList<>();
        adapter = new MyPromterCGListAdapter(this, data);
        listView.setAdapter(adapter);
//        listView.setAdapter(adapter);
        biaoti.setText("附近场馆");
    }

    @Override
    protected void initData() {
        dingwei();
        shuaxin();
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
                        dingwei();
                        initDownload(mLatitude + "", mLongitude + "", page);
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
                        initDownload(mLatitude + "", mLongitude + "", page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    private void initDownload(String mylat, String mylng, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNearbyvenues")
                .addParams("page", page + "")
                .addParams("limit", "")
                .addParams("mylat", mylat)
                .addParams("mylng", mylng)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "附近场馆111" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            PromoterFJCGEntity entity = gson.fromJson(result, PromoterFJCGEntity.class);
                            wu_tezt.setVisibility(View.GONE);
                            List<PromoterFJCGEntity.DataBean> list;
                            list = entity.getData();
//                            data.addAll(list);
                            if (page == 1) {
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                LogU.Ld("1608","数据"+ EmptyUtils.isListEmpty(list)+ list.size());
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值
                                    intent.setClass(PromoterFJCGActivity.this, PromoterFcgXXActivity.class);
                                    bundle.putString("name", data.get(position - 1).getName());
                                    bundle.putString("uid", data.get(position - 1).getUid());
                                    bundle.putString("isHandle", "0");
                                    bundle.putString("tag", "2");
                                    bundle.putDouble("mLatitude",mLatitude);
                                    bundle.putDouble("mLongitude",mLongitude);
                                    intent.putExtras(bundle);

                                    LogU.Ld("1608","坐标传值"+mLatitude+"======="+mLongitude);
                                    startActivity(intent);
                                }
                            });


                        } else {
                            wu_tezt.setVisibility(View.VISIBLE);
                            listView.onRefreshComplete();
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

    //定位地址
    private void dingwei() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        mlocationClient = new LocationClient(this);
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
//        int span = 1000;
//        mOption.setScanSpan(span);
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
//            city = bdLocation.getCity();
//            area = bdLocation.getDistrict();
//            province = bdLocation.getProvince();
//            spUtils.put(getActivity(), "city", city);
//            spUtils.put(getActivity(), "area", area);
            if (isFirstIn) {
                isFirstIn = false;

//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude+"错误"+bdLocation.getLocType());
//                spUtils.put(getActivity(), "mylat", mLatitude);
//                spUtils.put(getActivity(), "mylng", mLongitude);
//                initDownload(page, city, area, mLatitude + "", mLongitude + "",joinCondition);
                initDownload(mLatitude + "", mLongitude + "", 1);
            }

//            chengshiText.setText(city);
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
//        initDownload(page,city,area,mLatitude+"",mLongitude+"",joinCondition);
    }

}
