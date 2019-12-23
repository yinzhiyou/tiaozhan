package com.example.android.promoter.Nearby;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.android.promoter.Adapter.SSFujinAdapter;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.SSFujinEntity;
import com.example.android.promoter.Home.HomeGRTXActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 搜索附近的人
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private LocationClient mlocationClient;
    private MylocationListener mlistener;
    private double mLatitude;
    private double mLongitude;
    private EditText editText;
    private String token, touxiang, nickname, uuid;
    private SPUtils spUtils;
    private List<SSFujinEntity.DataBean.LstBean> data;
    private SSFujinAdapter adapter;
    private PullToRefreshListView listView;
    private ImageView imageView,fanhui;
    private TextView textView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initUIAndListener() {
        editText = findViewById(R.id.fujin_sousuo);
        listView = findViewById(R.id.ss_fujin_list);
        textView = findViewById(R.id.ss_fujin_text);
        imageView = findViewById(R.id.ss_fujin_image);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        data = new ArrayList<>();
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        adapter = new SSFujinAdapter(this, data, token);
    }

    @Override
    protected void initData() {
        //为EditText设置监听，注意监听类型为TextWatcher
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                toast("您输入的数据为："+s.toString());
                if (s.toString().length()>=11){
                    initjiaru(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        dingwei();
    }

    //搜索
    private void initjiaru(String term) {
        LogU.Ld("1608", "搜索" + term);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/searchUser")
                .addParams("term", term)
                .addParams("lat", mLatitude + "")
                .addParams("lng", mLongitude + "")
                .addParams("page", 1 + "")

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "搜索" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            textView.setVisibility(View.GONE);
                            imageView.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            Gson gson = new Gson();
                            SSFujinEntity entity = gson.fromJson(result, SSFujinEntity.class);
                            List<SSFujinEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
                            data.clear();
                            data.addAll(list);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(SearchActivity.this, HomeGRTXActivity.class);
                                    bundle.putString("uid",data.get(position-1).getUuid());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(SearchActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
        int span = 1000;
        mOption.setScanSpan(span);
        //设置 LocationClientOption
        mlocationClient.setLocOption(mOption);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
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
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();

            if (isFirstIn) {
                isFirstIn = false;

//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude);

//                initDownload(page);
            }


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
    }

}
