package com.example.android.tiaozhan.My;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
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
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyOrientationListener;
import com.example.android.tiaozhan.Toos.PoiOverlay;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图
 */
public class DituActivity extends AppCompatActivity implements OnGetGeoCoderResultListener, OnGetPoiSearchResultListener, OnGetSuggestionResultListener, View.OnClickListener {
    private TextView sousuo, wancheng,csText;
    private MapView mMapView = null;
    private LinearLayout chengshiLayout;
    private    List<HotCity> hotCities;
    private BaiduMap mBaiduMap;
    private ImageView fanhui;
    private String lat, log, city, district, province,address,sematicDescription;
    private int bj = 2;
    private String tag = "0";
    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    /**
     * 当前地点击点
     */
    private LatLng currentPt;

    private String touchType;
    /**
     * 用于显示地图状态的面板
     */
    private TextView mStateBar;

    BitmapDescriptor bdA;


    private LocationClient mlocationClient;
    private MylocationListener mlistener;
    private Context context;

    private double mLatitude;
    private double mLongitude;
    private float mCurrentX;

    private ImageButton mGetMylocationBN;

    //自定义图标
    private BitmapDescriptor mIconLocation;

    private MyOrientationListener myOrientationListener;
    //定位图层显示方式
    private MyLocationConfiguration.LocationMode locationMode;

    private AutoCompleteTextView keyWorldsView = null;
    private SuggestionSearch mSuggestionSearch = null;
    private ArrayAdapter<String> sugAdapter = null;

    private PoiSearch mPoiSearch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_ditu);
        this.context = this;


        initView();
        initLocation();
        initListener();
    }

    private void initView() {
        sousuo = findViewById(R.id.ditu_sousuo);
        sousuo.setOnClickListener(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mStateBar = (TextView) findViewById(R.id.state);
        fanhui = findViewById(R.id.changguan_fanhui);
        fanhui.setOnClickListener(this);
        wancheng = findViewById(R.id.ditu_wancheng);
        wancheng.setOnClickListener(this);
        chengshiLayout = findViewById(R.id.ditu_chengshi);
        csText = findViewById(R.id.ditu_chengshi_text);
        chengshiLayout.setOnClickListener(this);

        mBaiduMap = mMapView.getMap();
        //根据给定增量缩放地图级别
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu);
        mGetMylocationBN = (ImageButton) findViewById(R.id.id_bn_getMyLocation);
        mGetMylocationBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyLocation();
            }
        });

        bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
        keyWorldsView.setAdapter(sugAdapter);
        keyWorldsView.setThreshold(1);
        /* 当输入关键字变化时，动态更新建议列表 */
        keyWorldsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                LogU.Ld("1608","地图"+bj);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                LogU.Ld("1608","地图"+bj);
            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    LogU.Ld("1608","地图"+bj);
                    if (cs.length() <= 0) {

                    }else{
                        if (bj == 2){
                        /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
                        mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                                .keyword(cs.toString())
                                .city("北京"));
                        }else{
                            bj =2;
                        }

                    }






            }
        });



       hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
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
//        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
//            if (info.address != null) {
//                suggest.add(info.key + info.district+info);
//            }
//        }
        sugAdapter = new ArrayAdapter<>(DituActivity.this, android.R.layout.simple_dropdown_item_1line,
                suggest);

        keyWorldsView.setAdapter(sugAdapter);
        sugAdapter.notifyDataSetChanged();
        LogU.Ld("1608", suggest.toString());


    }

    /**
     * 获取POI搜索结果，包括searchInCity，searchNearby，searchInBound返回的搜索结果
     *
     * @param result Poi检索结果，包括城市检索，周边检索，区域检索
     */
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(DituActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
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
            Toast.makeText(DituActivity.this, strInfo, Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(DituActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
            return;
        }

        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions()
                .position(result.getLocation())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)));

        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result.getLocation()));
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude,
                result.getLocation().longitude);

        Toast.makeText(DituActivity.this, strInfo, Toast.LENGTH_LONG).show();

        LogU.Ld("1608", "onGetGeoCodeResult = " + result.toString());

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(DituActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
            return;
        }

        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions()
                .position(result.getLocation())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka)));

        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result.getLocation()));

        Toast.makeText(DituActivity.this, result.getAddress() + result.getSematicDescription() + " adcode: " + result.getAdcode(), Toast.LENGTH_LONG).show();
        keyWorldsView.setText(result.getAddress() + result.getSematicDescription());

        lat = result.getLocation().latitude + "";
        log = result.getLocation().longitude + "";
        city = result.getAddressDetail().city;
        district = result.getAddressDetail().district;
        province = result.getAddressDetail().province;
        address = result.getAddress();
        sematicDescription = result.getSematicDescription();
        LogU.Ld("1608", "ReverseGeoCodeResult = " + result.toString());
        LogU.Ld("1608", "经纬度 = " + result.getLocation().latitude + "经纬度" + result.getLocation().longitude+city+district+province);
       bj = 1;
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

    //点击监听器
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ditu_sousuo:
//                ToastUitl.longs("点击了");
//                searchType = 1;

//                String citystr = editCity.getText().toString();
                String keystr = keyWorldsView.getText().toString();

                mPoiSearch.searchInCity((new PoiCitySearchOption())
                        .city("北京")
                        .keyword(keystr)
                        .pageNum(0)
                        .scope(1));


                break;

            case R.id.changguan_fanhui:
                finish();
                break;
            case R.id.ditu_wancheng:
                if (lat==null){
                    ToastUitl.longs("请点击地图选择地址");
                }else{


                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
//                intent.setClass(this, CGGZActivity.class);
//                bundle.putString("tag", "2");
                bundle.putString("lat", lat);
                bundle.putString("log", log);
                bundle.putString("city", city);
                bundle.putString("district", district);
                bundle.putString("province", province);
                bundle.putString("address", address);
                bundle.putString("sematicDescription", sematicDescription);
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();
                }
                break;

            case R.id.ditu_chengshi:
                LogU.Ld("1608","是否进入1"+city+province+hotCities.toString());
                CityPicker.from(DituActivity.this) //activity或者fragment
                        .enableAnimation(true)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
                        .setLocatedCity(new LocatedCity(city, province, null))  //APP自身已定位的城市，传null会自动定位（默认）参数 市  省   地区代码
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
                                csText.setText(data.getName());
                                mPoiSearch.searchInCity((new PoiCitySearchOption())
                                        .city(csText.getText().toString())
                                        .keyword(csText.getText().toString())
                                        .pageNum(0)
                                        .scope(1));
                                LogU.Ld("1608","是否进入2");
//                                zuobiao();
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
                break;
        }
    }


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
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
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
                city = bdLocation.getCity();
                province = bdLocation.getProvince();
                Toast.makeText(context, bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
            }


        }
    }

    /**
     * 对地图事件的消息响应
     */
    private void initListener() {
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {

            @Override
            public void onTouch(MotionEvent event) {

            }
        });


        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 单击地图
             */
            public void onMapClick(LatLng point) {
                touchType = "单击地图";
                currentPt = point;
                updateMapState();
                bj =1;
            }

            /**
             * 单击地图中的POI点
             */
            public boolean onMapPoiClick(MapPoi poi) {
                touchType = "单击POI点";
                currentPt = poi.getPosition();
                updateMapState();
                return false;
            }
        });
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            /**
             * 长按地图
             */
            public void onMapLongClick(LatLng point) {
                touchType = "长按";
                currentPt = point;
                updateMapState();
            }
        });
        mBaiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            /**
             * 双击地图
             */
            public void onMapDoubleClick(LatLng point) {
                touchType = "双击";
//                currentPt = point;
//                updateMapState();
            }
        });
//
//        /**
//         * 地图状态发生变化
//         */
//        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
//            public void onMapStatusChangeStart(MapStatus status) {
//                updateMapState();
//            }
//
//            @Override
//            public void onMapStatusChangeStart(MapStatus status, int reason) {
//
//            }
//
//            public void onMapStatusChangeFinish(MapStatus status) {
//                updateMapState();
//            }
//
//            public void onMapStatusChange(MapStatus status) {
//                updateMapState();
//            }
//        });
//        zoomButton = (Button) findViewById(R.id.zoombutton);
//        rotateButton = (Button) findViewById(R.id.rotatebutton);
//        overlookButton = (Button) findViewById(R.id.overlookbutton);
//        animateStatus = (Button) findViewById(R.id.updatestatus);
//        saveScreenButton = (Button) findViewById(R.id.savescreen);
//        OnClickListener onClickListener = new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (view.equals(zoomButton)) {
//                    perfomZoom();
//                } else if (view.equals(rotateButton)) {
//                    perfomRotate();
//                } else if (view.equals(overlookButton)) {
//                    perfomOverlook();
//                } else if (view.equals(animateStatus)) {
//                    perfomAll();
//                } else if (view.equals(saveScreenButton)) {
//                    // 截图，在SnapshotReadyCallback中保存图片到 sd 卡
//                    mBaiduMap.snapshot(new SnapshotReadyCallback() {
//                        public void onSnapshotReady(Bitmap snapshot) {
//                            File file = new File("/mnt/sdcard/test.png");
//                            FileOutputStream out;
//                            try {
//                                out = new FileOutputStream(file);
//                                if (snapshot.compress(
//                                        Bitmap.CompressFormat.PNG, 100, out)) {
//                                    out.flush();
//                                    out.close();
//                                }
//                                Toast.makeText(MapControlDemo.this,
//                                        "屏幕截图成功，图片存在: " + file.toString(),
//                                        Toast.LENGTH_SHORT).show();
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                    Toast.makeText(MapControlDemo.this, "正在截取屏幕图片...",
//                            Toast.LENGTH_SHORT).show();
//
//                }
//                updateMapState();
//            }
//
//        };
//        zoomButton.setOnClickListener(onClickListener);
//        rotateButton.setOnClickListener(onClickListener);
//        overlookButton.setOnClickListener(onClickListener);
//        saveScreenButton.setOnClickListener(onClickListener);
//        animateStatus.setOnClickListener(onClickListener);
    }
//
//    /**
//     * 处理缩放 sdk 缩放级别范围： [3.0,19.0]
//     */
//    private void perfomZoom() {
//        EditText t = (EditText) findViewById(R.id.zoomlevel);
//        try {
//            float zoomLevel = Float.parseFloat(t.getText().toString());
//            MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(zoomLevel);
//            mBaiduMap.animateMapStatus(u);
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "请输入正确的缩放级别", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * 处理旋转 旋转角范围： -180 ~ 180 , 单位：度 逆时针旋转
//     */
//    private void perfomRotate() {
//        EditText t = (EditText) findViewById(R.id.rotateangle);
//        try {
//            int rotateAngle = Integer.parseInt(t.getText().toString());
//            MapStatus ms = new MapStatus.Builder(mBaiduMap.getMapStatus()).rotate(rotateAngle).build();
//            MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
//            mBaiduMap.animateMapStatus(u);
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "请输入正确的旋转角度", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * 处理俯视 俯角范围： -45 ~ 0 , 单位： 度
//     */
//    private void perfomOverlook() {
//        EditText t = (EditText) findViewById(R.id.overlookangle);
//        try {
//            int overlookAngle = Integer.parseInt(t.getText().toString());
//            MapStatus ms = new MapStatus.Builder(mBaiduMap.getMapStatus()).overlook(overlookAngle).build();
//            MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
//            mBaiduMap.animateMapStatus(u);
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "请输入正确的俯角", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * 处理所有参数，改变地图状态
//     */
//    private void perfomAll() {
//        EditText etOverlookAngle = (EditText) findViewById(R.id.overlookangle);
//        EditText etZoomLevel = (EditText) findViewById(R.id.zoomlevel);
//        EditText etRotateAngle = (EditText) findViewById(R.id.rotateangle);
//        try {
//            float zoomLevel = Float.parseFloat(etZoomLevel.getText().toString());
//            int rotateAngle = Integer.parseInt(etRotateAngle.getText().toString());
//            int overlookAngle = Integer.parseInt(etOverlookAngle.getText().toString());
//            MapStatus ms = new MapStatus.Builder(mBaiduMap.getMapStatus())
//                    .rotate(rotateAngle).zoom(zoomLevel).overlook(overlookAngle).build();
//            MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
//            mBaiduMap.animateMapStatus(u);
//
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "请输入正确参数，旋转角和俯角需为整数", Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * 更新地图状态显示面板
     */
    private void updateMapState() {
        if (mStateBar == null) {
            return;
        }
        String state = "";
        if (currentPt == null) {
            state = "点击、长按、双击地图以获取经纬度和地图状态";
        } else {
            state = String.format(touchType + ",当前经度： %f 当前纬度：%f",
                    currentPt.longitude, currentPt.latitude);
            MarkerOptions ooA = new MarkerOptions().position(currentPt).icon(bdA);
//            MarkerOptions ooA = new MarkerOptions().position(currentPt);
            mBaiduMap.clear();
            mBaiduMap.addOverlay(ooA);
        }
        state += "\n";
        MapStatus ms = mBaiduMap.getMapStatus();
        state += String.format(
                "zoom=%.1f rotate=%d overlook=%d",
                ms.zoom, (int) ms.rotate, (int) ms.overlook);
        mStateBar.setText(state);
        //下面是传入对应的经纬度
        int version = 0;
        LatLng ptCenter = new LatLng(currentPt.latitude, currentPt.longitude);
        // 反Geo搜索
//        if(cb.isChecked()){
//            version=1;
//        }
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter).newVersion(version).radius(500));
    }


}
