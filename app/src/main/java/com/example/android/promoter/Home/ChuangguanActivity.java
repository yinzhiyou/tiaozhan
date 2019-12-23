package com.example.android.promoter.Home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.example.android.promoter.Adapter.ChangguanListAdapter;
import com.example.android.promoter.Adapter.DizhiSousuoAdapter;
import com.example.android.promoter.Entity.ChangguanListEntity;
import com.example.android.promoter.Entity.DizhiSousuoEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.ZuobiaoEntity;
import com.example.android.promoter.Promoter.MyPromoterActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyOrientationListener;
import com.example.android.promoter.Toos.PoiOverlay;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zaaach.citypicker.CityPicker;

import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *选择场馆
 */
public class ChuangguanActivity extends AppCompatActivity implements OnGetSuggestionResultListener, View.OnClickListener, OnGetPoiSearchResultListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mlocationClient;
    private MylocationListener mlistener;
    private Context context;
    private ImageView fanhui;
    private String city, area, sportId, province;
    private int page = 1;
    private double mLatitude, mylat, mylong;
    private double mLongitude;
    private int tag = 0;
    private float mCurrentX;
    private SPUtileFQTZ spUtils;
    private ImageButton mGetMylocationBN;
    private List<ChangguanListEntity.DataBean.SitelstBean> data;
    //自定义图标
    private BitmapDescriptor mIconLocation;

    private MyOrientationListener myOrientationListener;
    //定位图层显示方式
    private MyLocationConfiguration.LocationMode locationMode;

    private String fqtzXiangmusportId;
    private ChangguanListAdapter adapter;
    private ListView listViewXiala;
    private PullToRefreshListView listView;
//    public static ChuangguanActivity instance = null;


    // 初始化全局 bitmap 信息，不用时及时 recycle
    BitmapDescriptor bdA;
    Marker marker;

    private TextView chengshi, sousuo;

    private AutoCompleteTextView keyWorldsView = null;
    private SuggestionSearch mSuggestionSearch = null;
    private ArrayAdapter<String> sugAdapter = null;
    private PoiSearch mPoiSearch = null;

    private List<DizhiSousuoEntity.ResultsBean> dataDizhi;
    private DizhiSousuoAdapter adapterDizhi;
    private EditText editText;
    private InfoWindow mInfoWindow;
    private int locType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_chuangguan);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white);
        }*/

        sousuo = findViewById(R.id.changguan_sousuo);
        sousuo.setOnClickListener(this);
        editText = findViewById(R.id.cg_edit);
        listViewXiala = findViewById(R.id.changguan_list);
//        instance = this;
        this.context = this;
        initView();
        /*if (mLatitude == 0) {
            LogU.Ld("1608", "走了");
            initLocation();

        }*/
        initLocation();
        final List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));

        listView = findViewById(R.id.cg_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        chengshi = findViewById(R.id.changguan_chengshi);
        chengshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPicker.from(ChuangguanActivity.this) //activity或者fragment
                        .enableAnimation(true)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
                        .setLocatedCity(new LocatedCity(city, province, null))  //APP自身已定位的城市，传null会自动定位（默认）参数 市  省   地区代码
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
//                                Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
                                chengshi.setText(data.getName());
                                mPoiSearch.searchInCity((new PoiCitySearchOption())
                                        .city(chengshi.getText().toString())
                                        .keyword(chengshi.getText().toString())
                                        .pageNum(0)
                                        .scope(1));
                                zuobiao();

                            }

                            @Override
                            public void onCancel() {
//                                Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLocate() {
                                //定位接口，需要APP自身实现，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //定位完成之后更新数据
//                                        CityPicker.getInstance()
//                                                .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
//                                        CityPicker.setLocatedCity().locateComplete();
                                    }
                                }, 3000);
                            }
                        })
                        .show();

            }
        });


        fanhui = findViewById(R.id.changguan_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spUtils = new SPUtileFQTZ();
        sportId = (String) spUtils.get(this, "fqtzXiangmudasportId", "无");
        fqtzXiangmusportId = (String) spUtils.get(this, "fqtzXiangmusportId", "无");
        data = new ArrayList<>();
        adapter = new ChangguanListAdapter(this, data);
        bdA = BitmapDescriptorFactory
                .fromResource(R.mipmap.dingweidian);

        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

//        keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
        dataDizhi = new ArrayList<>();
        adapterDizhi = new DizhiSousuoAdapter(this, dataDizhi);
//        keyWorldsView.setAdapter(adapterDizhi);
//        keyWorldsView.setAdapter(sugAdapter);
//        keyWorldsView.setThreshold(1);

        /* 当输入关键字变化时，动态更新建议列表 */
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    listViewXiala.setVisibility(View.GONE);
//                    return;
                } else {
                    listViewXiala.setVisibility(View.VISIBLE);
                    LogU.Ld("1608", "定位" + mLatitude + "定位2" + mLongitude);
                    searchMD();
                    hideKeyboard(getWindow().getDecorView());
                    sousuo(cs.toString(), chengshi.getText().toString());



                }


//                adapterDizhi.notifyDataSetChanged();
//                adapterDizhi.notifyDataSetInvalidated();
//                /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
//                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
//                        .keyword(cs.toString())
//                        .city(chengshi.getText().toString()));
            }
        });

        shuaxin();
    }

    private void initView() {
        mMapView = findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        //根据给定增量缩放地图级别
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu);
        mGetMylocationBN = (ImageButton) findViewById(R.id.id_bn_getMyLocation);
        mGetMylocationBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = 0;
                getMyLocation();
            }
        });

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
                        LogU.Ld("1608", "下啦" + page + mLatitude + "==="+ mLongitude + "错误码"+locType);

                        if (tag == 1) {
                            mLatitude = mylat;
                            mLongitude = mylong;
                            init(mLatitude + "", mLongitude + "", city, area, page);
                        } else {
                            init(mLatitude + "", mLongitude + "", city, area, page);
                        }

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
                        LogU.Ld("1608", "上啦" + page + mLatitude + "==="+ mLongitude + "错误码"+locType);
                        if (tag == 1) {
                            mLatitude = mylat;
                            mLongitude = mylong;
                            init(mLatitude + "", mLongitude + "", city, area, page);
                        } else {
                            init(mLatitude + "", mLongitude + "", city, area, page);
                        }

//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    //场馆信息
    private void init(String mLatitude, String mLongitude, String city, String area, final int page) {
        LogU.Ld("1608", "场馆信息" + mLatitude + "纬度" + mLongitude + "city" + city + "area" + area + "page" + page);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSiteLst")
                .addParams("mylat", mLatitude + "")
                .addParams("mylng", mLongitude + "")
                .addParams("city", city)
                .addParams("area", area)
                .addParams("sportId", sportId)
                .addParams("page", page + "")
                .addParams("sporttype", fqtzXiangmusportId + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "场馆信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            ChangguanListEntity entity = gson.fromJson(result, ChangguanListEntity.class);
                            List<ChangguanListEntity.DataBean.SitelstBean> list;
                            list = entity.getData().getSitelst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
                            if (page == 1) {
                                data.clear();
                                data.addAll(list);
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }
                            initOverlay();
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(ChuangguanActivity.this, CGXXActivity.class);
                                    bundle.putString("uid", data.get(position - 1).getUid());
                                    bundle.putString("hezuo", data.get(position - 1).getIsCooper() + "");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
//                            listViewshaixuan2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                    //添加数据
////                                    mList = new ArrayList<>();
////                                    for (int i = 0; i < zhineng.length; i++) {
////                                        mList.add(zhineng[i]);
////                                    }
//                                    sxhuodong2 = datashai.get(position).getId() + "";
//                                    initDownload();
//                                    layoutShaixuan.setVisibility(View.GONE);
//                                    tag = true;
////                                    adapter3 = new HomeShaixuanTwoAdapter(getActivity(),mList);
////                                    listViewshaixuan2.setAdapter(adapter3);
//
//                                }
//                            });
//                            adapter3.notifyDataSetChanged();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            data.clear();
//                                listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
                        }
                    }
                });
    }

    //坐标  http://api.map.baidu.com/geocoder?address=地址&output=输出格式类型&key=用户密钥&city=城市名
    private void zuobiao() {
        LogU.Ld("1608", "场馆信息" + sportId);
        OkHttpUtils
                .get()
                .url("http://api.map.baidu.com/geocoder")
                .addParams("address", chengshi.getText().toString())
                .addParams("output", "json")
                .addParams("key", "0xRmvPDRXDbj1Ql18OgKQIZpT6jmCTnH")
                .addParams("city", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "坐标" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        sousuo("国贸","北京市");
//                        if (a) {
                        Gson gson = new Gson();
                        ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);
                        mLatitude = entity.getResult().getLocation().getLat();
                        mLongitude = entity.getResult().getLocation().getLng();
                        area = "";
                        init(mLatitude + "", mLongitude + "", chengshi.getText().toString(), area, page);
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                        }
                    }
                });
    }

    //搜索信息
    private void sousuo(String name, String shi) {
        LogU.Ld("1608", "搜索信息" + name + "市" + shi);
        OkHttpUtils
                .get()
                .url("http://api.map.baidu.com/place/v2/search")
                .addParams("query", name)
                .addParams("page_size", "10")
                .addParams("ak", "2OjUNUyLZzwOCjwpFKLW5ZTFWe8juGsG")
                .addParams("output", "json")
                .addParams("page_num", "0")
                .addParams("scope", "1")
                .addParams("region", shi)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
//                        LogU.Ld("1608", "搜索信息" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Gson gson = new Gson();
                        DizhiSousuoEntity entity = gson.fromJson(result, DizhiSousuoEntity.class);
                        List<DizhiSousuoEntity.ResultsBean> list;
                        list = entity.getResults();
                        dataDizhi.clear();
                        dataDizhi.addAll(list);
                        listViewXiala.setAdapter(adapterDizhi);
                        adapterDizhi.notifyDataSetChanged();
                        adapterDizhi.notifyDataSetInvalidated();
                        listViewXiala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                editText.setText(dataDizhi.get(position).getName());

                                city = dataDizhi.get(position).getCity();
                                area = dataDizhi.get(position).getArea();

                                mylat = dataDizhi.get(position).getLocation().getLat();
                                mylong = dataDizhi.get(position).getLocation().getLng();
                                mLatitude = dataDizhi.get(position).getLocation().getLat();
                                mLongitude = dataDizhi.get(position).getLocation().getLng();
                                LogU.Ld("1608", "定1位" + mLatitude + "定2位" + mLongitude);
                                listViewXiala.setVisibility(View.GONE);
                            }
                        });

//                        if (a) {
//                            Gson gson = new Gson();
//                            ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);
//
//
//                        } else {
////                            Gson gson = new Gson();
////                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
////                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
    }

    private void initLocation() {
        locationMode = MyLocationConfiguration.LocationMode.NORMAL;

        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        mlocationClient = new LocationClient(this);
        mlistener = new MylocationListener();

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

        //初始化图标,BitmapDescriptorFactory是bitmap 描述信息工厂类.
        mIconLocation = BitmapDescriptorFactory
                .fromResource(R.mipmap.weizhi2);


        myOrientationListener = new MyOrientationListener(context);
        //通过接口回调来实现实时方向的改变
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mlocationClient.isStarted()) {
            mlocationClient.start();
        }
        myOrientationListener.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mlocationClient.stop();
        myOrientationListener.stop();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    public void getMyLocation() {
        LatLng latLng = new LatLng(mLatitude, mLongitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);
    }


    /**
     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
     *
     * @param res Sug检索结果
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult res) {
        if (res == null || res.getAllSuggestions() == null) {
            return;
        }

        List<String> suggest = new ArrayList<>();
        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
            if (info.key != null) {
                suggest.add(info.key);
            }
        }

        sugAdapter = new ArrayAdapter<>(ChuangguanActivity.this, android.R.layout.simple_dropdown_item_1line,
                suggest);
        keyWorldsView.setAdapter(sugAdapter);
        sugAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changguan_sousuo://点击搜索
//                String keystr = keyWorldsView.getText().toString();

                mPoiSearch.searchInCity((new PoiCitySearchOption())
                        .city(chengshi.getText().toString())
                        .keyword(editText.getText().toString())
                        .pageNum(0)
                        .scope(1));
//                mBaiduMap.clear();
                mLatitude = mylat;
                mLongitude = mylong;
                LogU.Ld("1608", "定3位" + mLatitude + "定4位" + mylat);
                init(mLatitude + "", mLongitude + "", city, area, 1);
                tag = 1;
                break;

        }
    }

    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(ChuangguanActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
            return;
        }

        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            mBaiduMap.clear();
            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result);
            overlay.addToMap();
            overlay.zoomToSpan();
            mBaiduMap.clear();
            return;
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";

            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }

            strInfo += "找到结果";
            Toast.makeText(ChuangguanActivity.this, strInfo, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }


    private class MyPoiOverlay extends PoiOverlay {
        MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption()).poiUid(poi.uid));
            // }
            return true;
        }
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.id_map_common:
//                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//                break;
//            case R.id.id_map_site:
//                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//                break;
//            case R.id.id_map_traffic:
//                if(mBaiduMap.isTrafficEnabled())
//                {
//                    mBaiduMap.setTrafficEnabled(false);
//                    item.setTitle("实时交通(off)");
//                }else
//                {
//                    mBaiduMap.setTrafficEnabled(true);
//                    item.setTitle("实时交通(on)");
//                }
//                break;
//            case R.id.id_map_mlocation:
//                getMyLocation();
//                break;
//            case R.id.id_map_model_common:
//                //普通模式
//                locationMode= MyLocationConfiguration.LocationMode.NORMAL;
//                break;
//            case R.id.id_map_model_following:
//                //跟随模式
//                locationMode= MyLocationConfiguration.LocationMode.FOLLOWING;
//                break;
//            case R.id.id_map_model_compass:
//                //罗盘模式
//                locationMode= MyLocationConfiguration.LocationMode.COMPASS;
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    //所有的定位信息都通过接口回调来实现
    public class MylocationListener implements BDLocationListener {
        //定位请求回调接口
        private boolean isFirstIn = true;

        //定位请求回调函数,这里面会得到定位信息
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //BDLocation 回调的百度坐标类，内部封装了如经纬度、半径等属性信息
            //MyLocationData 定位数据,定位数据建造器
            /*
             * 可以通过BDLocation配置如下参数
             * 1.accuracy 定位精度
             * 2.latitude 百度纬度坐标
             * 3.longitude 百度经度坐标
             * 4.satellitesNum GPS定位时卫星数目 getSatelliteNumber() gps定位结果时，获取gps锁定用的卫星数
             * 5.speed GPS定位时速度 getSpeed()获取速度，仅gps定位结果时有速度信息，单位公里/小时，默认值0.0f
             * 6.direction GPS定位时方向角度
             * */
//            mylat = bdLocation.getLatitude();
//            mylong = bdLocation.getLongitude();
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
            locType = bdLocation.getLocType();
            MyLocationData data = new MyLocationData.Builder()
                    .direction(mCurrentX)//设定图标方向
                    .accuracy(bdLocation.getRadius())//getRadius 获取定位精度,默认值0.0f
                    .latitude(mLatitude)//百度纬度坐标
                    .longitude(mLongitude)//百度经度坐标
                    .build();
            //设置定位数据, 只有先允许定位图层后设置数据才会生效，参见 setMyLocationEnabled(boolean)
            mBaiduMap.setMyLocationData(data);
            //配置定位图层显示方式,三个参数的构造器
            /*
             * 1.定位图层显示模式
             * 2.是否允许显示方向信息
             * 3.用户自定义定位图标
             *
             * */
            MyLocationConfiguration configuration
                    = new MyLocationConfiguration(locationMode, true, mIconLocation);
            //设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效，参见 setMyLocationEnabled(boolean)
            mBaiduMap.setMyLocationConfigeration(configuration);
            //判断是否为第一次定位,是的话需要定位到用户当前位置
            if (isFirstIn) {
                //地理坐标基本数据结构
                LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                //描述地图状态将要发生的变化,通过当前经纬度来使地图显示到该位置
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                //改变地图状态
                mBaiduMap.setMapStatus(msu);
                isFirstIn = false;
                Toast.makeText(context, bdLocation.getAddrStr() + "大大大" + bdLocation.getCity() + bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude + "地区" + bdLocation.getAddrStr() + "大大大" + bdLocation.getProvince() + bdLocation.getCity() + bdLocation.getDistrict());
                city = bdLocation.getCity();
                province = bdLocation.getProvince();
                area = bdLocation.getDistrict();
                init(mLatitude + "", mLongitude + "", city, area, page);
//                init(mylat + "", mylong + "", city, area,page);
                chengshi.setText(city);
            }
        }
    }

    public void initOverlay() {
        MarkerOptions ooA = null;
        for (int i = 0; i < data.size(); i++) {
            LatLng llA = new LatLng(Double.parseDouble(data.get(i).getLat()), Double.parseDouble(data.get(i).getLng()));
            ooA = new MarkerOptions().position(llA).icon(bdA)
                    .zIndex(9).draggable(true);
            ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
            marker = (Marker) (mBaiduMap.addOverlay(ooA));
            marker.setTitle(data.get(i).getName());
        }
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                Button button = new Button(getApplicationContext());
                button.setBackgroundResource(R.drawable.popup);
                button.setText(marker.getTitle());

//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
                LatLng ll = marker.getPosition();
                mInfoWindow = new InfoWindow(button, ll, -100);

//使InfoWindow生效
                mBaiduMap.showInfoWindow(mInfoWindow);

                return true;
            }
        });

    }

    public void searchMD(){
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(chengshi.getText().toString())
                .keyword(editText.getText().toString())
                .pageNum(0)
                .scope(1));
//                mBaiduMap.clear();
        mLatitude = mylat;
        mLongitude = mylong;
        LogU.Ld("1608", "定3位" + mLatitude + "定4位" + mylat);
        init(mLatitude + "", mLongitude + "", city, area, 1);
        tag = 1;
    }
    /**
     * 得到当前软键盘的高度
     *
     * @return 软键盘的高度
     */
    public int getCurrentSoftInputHeight() {
        final View decorView = getWindow().getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        // 获取屏幕的高度(包括状态栏，导航栏)
        int screenHeight = decorView.getRootView().getHeight();
        int keySoftHeight = screenHeight - rect.bottom;
        return keySoftHeight;
    }
    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
