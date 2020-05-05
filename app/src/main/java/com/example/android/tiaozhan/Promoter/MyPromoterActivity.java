package com.example.android.tiaozhan.Promoter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.example.android.tiaozhan.Adapter.MyPromterCGListAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.BiaobiaoEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyPromoterEntity;
import com.example.android.tiaozhan.Entity.PromoterFJCGEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;

/**
 * 推广员首页
 */
public class MyPromoterActivity extends BaseActivity implements View.OnClickListener {

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_promoter);
//    }



    public LocationClient mLocationClient = null;

    private int page = 1;
    private PullToRefreshListView listView;
    private MyPromterCGListAdapter adapter;
    private ImageView fanhui, zhouX, yueX, nianX;
    private LinearLayout gdLayout, jrdd, sydd, ytgLayout, dclLayout,my_promo_clz;
    private RelativeLayout my_qb, xiaox_btn;
    private String token = "", uid, nameString, scjiageString, ZMurl, FMurl, TGid;
    private SPUtils spUtils;
    private TextView idText, shouru, jinri, suoyou, dclshu, ytgshu, zhengque, zhou, yue, nian, qianbao_num, xiaoxi_num,my_promo_clzshu;

    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;        //图例
    private LimitLine limitLine;        //限制线


    private double mLatitude, mylat, mylong;
    private double mLongitude;
    private float mCurrentX;
    private BaiduMap mBaiduMap;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;

    private List<PromoterFJCGEntity.DataBean> data;
    private List<BiaobiaoEntity.DataBean> data2;


    private List<Integer> list = new ArrayList<>(); //数据集合
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合
    private LineChart mChart1;
    private View mViewNeedOffset;
    private RelativeLayout wu;
    private MyPromoterEntity entity;
    private ScrollView scroll_view;
    @Override
    public int initContentView() {
        return R.layout.activity_my_promoter;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initUIAndListener() {

       /* LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (ok) {
            if (Build.VERSION.SDK_INT >= 23) {
                openLocation();
            } else {
                dingwei();
                ;//init为定位方法
            }
        }*/
        StatusBarUtil.setTransparentForImageView(MyPromoterActivity.this, mViewNeedOffset);
        // StateUtils.setRootViewFitsSystemWindows(this,false);

        wu = findViewById(R.id.wu);
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        listView = findViewById(R.id.my_prom_lis);
        fanhui = findViewById(R.id.fanhui);
        gdLayout = findViewById(R.id.my_prom_gd);
        jrdd = findViewById(R.id.my_prom_jrdd);
        jrdd.setOnClickListener(this);
//        sydd = findViewById(R.id.my_prom_sydd);
//        sydd.setOnClickListener(this);
        ytgLayout = findViewById(R.id.my_promo_ytg);
        ytgLayout.setOnClickListener(this);
        dclLayout = findViewById(R.id.my_promo_dcl);
        dclLayout.setOnClickListener(this);
        gdLayout.setOnClickListener(this);
        idText = findViewById(R.id.my_promo_id);
        shouru = findViewById(R.id.my_promo_shouru);
        jinri = findViewById(R.id.my_promo_jinri);
//        suoyou = findViewById(R.id.my_promo_suoyou);
        dclshu = findViewById(R.id.my_promo_dclshu);
        ytgshu = findViewById(R.id.my_promo_ytgshu);
        zhengque = findViewById(R.id.my_promo_zql);
        lineChart = (LineChart) findViewById(R.id.chart);

        zhou = findViewById(R.id.baobiao_zhou);
        zhou.setOnClickListener(this);
        yue = findViewById(R.id.baobiao_yue);
        yue.setOnClickListener(this);
        nian = findViewById(R.id.baobiao_nian);
        nian.setOnClickListener(this);
        zhouX = findViewById(R.id.baobiao_xiang_zhou);
        yueX = findViewById(R.id.baobiao_xiang_yue);
        nianX = findViewById(R.id.baobiao_xiang_nian);
        my_qb = findViewById(R.id.my_qb);
        my_qb.setOnClickListener(this);
        qianbao_num = findViewById(R.id.qianbao_num);

        xiaoxi_num = findViewById(R.id.xiaoxi_num);
        xiaox_btn = findViewById(R.id.xiaox_btn);
        xiaox_btn.setOnClickListener(this);

        my_promo_clz = findViewById(R.id.my_promo_clz);
        my_promo_clz.setOnClickListener(this);

        my_promo_clzshu = findViewById(R.id.my_promo_clzshu);


        data2 = new ArrayList<>();

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   finish();
            }
        });
//        adapter = new MyPromterCGListAdapter(this);
//        listView.setAdapter(adapter);


        spUtils = new SPUtils();
        token = (String) spUtils.get(MyPromoterActivity.this, "logintoken", "无");
        scroll_view=findViewById(R.id.scroll_view);
        LogU.Ld("1608", "token+我是" + token);
        data = new ArrayList<>();
        adapter = new MyPromterCGListAdapter(this, data);

        //listView.setScrollview(scroll_view);
       // scroll_view.setListView(listView);
       // listView.setMode(PullToRefreshBase.Mode.BOTH);

        listView.setAdapter(adapter);


        //删除手机信息栏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        //折线名字
        names.add("时间");
        names.add("压强");
        names.add("其他");
        //折线颜色
        colour.add(Color.CYAN);
        colour.add(Color.GREEN);
        colour.add(Color.BLUE);


//                            list.add((int) (Math.random() * 50) + 10);
//                            list.add((int) (Math.random() * 80) + 10);
//                            list.add((int) (Math.random() * 100));
//                            dynamicLineChartManager2.addEntry(list);
//                            list.clear();


        // 设置是否绘制背景
//        mChart.setDrawGridBackground(false);
//        // 设置是否绘制边框
//        mChart.setDrawBorders(false);
//        // 设置是否可以缩放图表
//        mChart.setScaleEnabled(true);
//        // 设置是否可以用手指移动图表
//        mChart.setDragEnabled(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        Matrix matrix = new Matrix();
//        // x轴放大4倍，y不变
//        matrix.postScale(4.0f, 1.0f);
//        // 设置缩放
//        mChart.getViewPortHandler().refresh(matrix, mChart, false);
//        setData(9, 5);
        initChart(lineChart);
//        LineChartBean lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this, "chart.json", LineChartBean.class);
//        List<IncomeBean> list = lineChartBean.getGRID0().getResult().getClientAccumulativeRate();


    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(final List<BiaobiaoEntity.DataBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.0");
        for (int i = 0; i < dataList.size(); i++) {
//            IncomeBean data = dataList.get(i);        /**         * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)         * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示         */
//            Entry entry = new Entry(i, (float) data.getValue());
//            BiaobiaoEntity.DataBean data = dataList.get(i);
            LogU.Ld("1608", Float.parseFloat(dataList.get(i).getMoney()) + "aaaaaaaa");

            Entry entry = new Entry(dataList.get(i).getTime(), Float.parseFloat(df.format(Float.parseFloat(dataList.get(i).getMoney()))));
            entries.add(entry);
        }

        if (dataList.size() > 15) {
            xAxis.setLabelCount(15);
        } else {
            xAxis.setLabelCount(dataList.size());
        }


        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     * * * @param lineDataSet 线条
     * * @param color       线条颜色
     * * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);    //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);    //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {        //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {    /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(true);
        // 是否显示边界
        lineChart.setDrawBorders(true);    //是否可以拖动
        lineChart.setDragEnabled(false);    //是否有触摸事件
        lineChart.setTouchEnabled(true);    //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);


        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方

        Description description = new Description();//

        description.setEnabled(false);
        lineChart.setDescription(description);


        rightYaxis.setDrawGridLines(false);

        leftYAxis.setDrawGridLines(true);
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        rightYaxis.setEnabled(false);

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);    //是否绘制在图表里面
        legend.setDrawInside(false);






    }

//    /**
//     * 设置模拟数据
//     *
//     * @param count 模拟的个数
//     * @param range 数据的范围
//     */
//    private void setData(int count, float range) {
//
//        ArrayList<Entry> values = new ArrayList<Entry>();
//
//        for (int i = 0; i < count; i++) {
//
//            float val = (float) (Math.random() * range) + 3;
//            values.add(new Entry(i, val));
//        }
//
//        LineDataSet set1;
//
//        if (mChart.getData() != null &&
//                mChart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//        } else {
//            // create a dataset and give it a type
//            set1 = new LineDataSet(values, "DataSet 1");
//
//            // set the line to be drawn like this "- - - - - -"
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
//            set1.setColor(Color.BLACK);
//            set1.setCircleColor(Color.BLACK);
//            set1.setLineWidth(1f);
//            set1.setCircleRadius(3f);
//            set1.setDrawCircleHole(false);
//            set1.setValueTextSize(9f);
//            set1.setDrawFilled(true);
//
//            if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.logo);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }
//
//            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//            dataSets.add(set1); // add the datasets
//
//            // create a data object with the datasets
//            LineData data = new LineData(dataSets);
//
//            // set data
//            mChart.setData(data);
//        }
//    }


    @Override
    protected void initData() {
        initDownload();
        dingwei();

        LogU.Ld("1608","刷新1");
        //  promoterMoney();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()) {
            case R.id.my_prom_gd:

                    intent.setClass(this, PromoterFJCGActivity.class);
                    startActivity(intent);



                break;
//            case R.id.my_prom_sydd:
//                intent.setClass(this,PromterDDActivity.class);
//
////                bundle.putString("TGid",TGid);
////                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
            case R.id.my_prom_jrdd:

                    intent.setClass(this, PromterDDActivity.class);
                    startActivity(intent);


                break;

            case R.id.my_promo_ytg:


                    intent.setClass(this, PromoterYTGActivity.class);
                    startActivity(intent);


                break;

            case R.id.my_promo_dcl:


                    intent.setClass(this, PromoterDCLActivity.class);
                    startActivity(intent);



                break;
            case R.id.baobiao_zhou:
                baobiao("1");
                zhou.setTextColor(getResources().getColor(R.color.hongse));
                yue.setTextColor(getResources().getColor(R.color.bbbbb));
                nian.setTextColor(getResources().getColor(R.color.bbbbb));
                zhouX.setBackgroundColor(getResources().getColor(R.color.hongse));
                yueX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                nianX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                break;
            case R.id.baobiao_yue:
                baobiao("2");
                zhou.setTextColor(getResources().getColor(R.color.bbbbb));
                yue.setTextColor(getResources().getColor(R.color.hongse));
                nian.setTextColor(getResources().getColor(R.color.bbbbb));
                zhouX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                yueX.setBackgroundColor(getResources().getColor(R.color.hongse));
                nianX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                break;
            case R.id.baobiao_nian:
                baobiao("3");
                zhou.setTextColor(getResources().getColor(R.color.bbbbb));
                yue.setTextColor(getResources().getColor(R.color.bbbbb));
                nian.setTextColor(getResources().getColor(R.color.hongse));
                zhouX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                yueX.setBackgroundColor(getResources().getColor(R.color.bbbbb));
                nianX.setBackgroundColor(getResources().getColor(R.color.hongse));
                break;

            case R.id.my_qb:


                    intent.setClass(this, PromoterMyMoneyActivity.class);
                    startActivity(intent);


                break;
            case R.id.xiaox_btn:

                    intent.setClass(this, PromoterNewsListActivity.class);
                    startActivity(intent);


                break;

            case R.id.my_promo_clz:

                    intent.setClass(this, PromoterClzTsActivity.class);
                    startActivity(intent);


                break;

        }
    }


    private void baobiao(String type) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMoneyStatisticalArticle")
                .addHeader("token", token)
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "报表" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {

                            Gson gson = new Gson();
                            BiaobiaoEntity entity = gson.fromJson(result, BiaobiaoEntity.class);
                            List<BiaobiaoEntity.DataBean> list;
                            list = entity.getData();
                            data2.clear();
                            data2.addAll(list);
                            for (int i = 0; i < data2.size(); i++) {
//                                LogU.Ld("1608","走了"+Double.parseDouble(data2.get(i).getMoney()));

//                                Integer.parseInt(data2.get(i).getMoney());
                            }
//
                            showLineChart(data2, "我的收益", Color.CYAN);
                            initChart(lineChart);
                        } else {
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

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterindex")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "推广员我的ID" + result);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, MyPromoterEntity.class);
                            idText.setText("推广ID:" + entity.getData().getPromotId());
                            TGid = entity.getData().getPromotId() + "";
                            shouru.setText(entity.getData().getData1() + "");
                            jinri.setText(entity.getData().getData4() + "");
//                            suoyou.setText(entity.getData().getData3()+"");
                            dclshu.setText(entity.getData().getData5() + "");
                            ytgshu.setText(entity.getData().getData6() + "");
                            zhengque.setText("处理投诉正确率" + entity.getData().getData2() + "%");
                            qianbao_num.setText(entity.getData().getData7() + "");
                            my_promo_clzshu.setText(entity.getData().getData9()+"");
                            if (entity.getData().getData8() > 0) {
                                xiaoxi_num.setVisibility(View.VISIBLE);
                                xiaoxi_num.setText(entity.getData().getData8() + "");
                            }
//                            Intent intent = new Intent();
//                            intent.setClass(PromoterONEActivity.this,PromoterTWOActivity.class);
//                            startActivity(intent);
                            LogU.Ld("1608", "推广员我" + entity.getData().getPromotId() + entity.getData().getData4() + entity.getData().getData5());
                            baobiao("1");
                        }

                        else {
                            Gson gson = new Gson();
                            JiekouSBEntity entityt = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyPromoterActivity.this, entityt.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(MyPromoterActivity.this, DengluActivity.class);
                            startActivity(intent);
                        }
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
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
            bdLocation.getLocType();
//            city = bdLocation.getCity();
//            area = bdLocation.getDistrict();
//            province = bdLocation.getProvince();
//            spUtils.put(getActivity(), "city", city);
//            spUtils.put(getActivity(), "area", area);
            if (isFirstIn) {
                isFirstIn = false;

//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude + "错误码" + bdLocation.getLocType());
//                spUtils.put(getActivity(), "mylat", mLatitude);
//                spUtils.put(getActivity(), "mylng", mLongitude);
//                initDownload(page, city, area, mLatitude + "", mLongitude + "",joinCondition);
                //  initDownload(mLatitude + "", mLongitude + "");
                initDownload(mLatitude + "", mLongitude + "", page);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        token = (String) spUtils.get(MyPromoterActivity.this, "logintoken", "");
        initDownload();
        dingwei();
    }

    private void initDownload(String mylat, String mylng, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNearbyvenues")
                .addParams("page", page + "")
                .addParams("limit", "3")
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
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值
                                    intent.setClass(MyPromoterActivity.this, PromoterFcgXXActivity.class);
                                    bundle.putString("name", data.get(position - 1).getName());
                                    bundle.putString("uid", data.get(position - 1).getUid());
                                    bundle.putString("isHandle", "0");
                                    bundle.putString("tag", "2");
                                    bundle.putDouble("mLatitude", mLatitude);
                                    bundle.putDouble("mLongitude", mLongitude);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });


                        } else {
                            wu.setVisibility(View.VISIBLE);
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


    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        // 有一个为true，就代表定位服务已经打开
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 直接跳转至位置信息设置界面
     */
    private void openLocation() {

            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 3/*自定义的请求码*/);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 3:
                if (resultCode == RESULT_CANCELED) {
                    if (isLocServiceEnable(this)) {
                        // 位置信息已打开，做接下来的操作

                    } else {
                        Toast.makeText(MyPromoterActivity.this, "未开启GPS或定位服务，无法进入",
                                Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                break;
        }
    }





}
