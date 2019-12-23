package com.example.android.promoter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;


import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.android.promoter.Adapter.HomeFragmentAdapter;
import com.example.android.promoter.Adapter.HomeTanAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.HomeTanEntity;
import com.example.android.promoter.Entity.JCXIEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Home.FaqiTiaozhanActivity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.My.HDJGActivity;
import com.example.android.promoter.My.PingjiaTwoActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.NetUtil;
import com.example.android.promoter.Toos.NoScrollViewPager;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private NoScrollViewPager viewPager;
    private RadioGroup radioGroup;
    private HomeFragmentAdapter adapter;
    private SPUtils spUtils;
    private String token = "";
    private Dialog mCameraDialog;
    private ImageView imageView,hongdian;
    private   List<HomeTanEntity.DataBean> data;
    private HomeTanAdapter adapter2;
    private ListView listView;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;
    private double mLatitude;
    private double mLongitude;
    private SPUtileFQTZ spUtileFQTZ;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限            //开启定位权限,200是标识码
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
//        } else {
//            startLocaion();//开始定位
//            Toast.makeText(MainActivity.this,"已开启定位权限",Toast.LENGTH_LONG).show();        }
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_LOGS, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.SET_DEBUG_APP, android.Manifest.permission.SYSTEM_ALERT_WINDOW, android.Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(MainActivity.this,mPermissionList,123);
        }

      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        viewPager = findViewById(R.id.homeViewPager);
        radioGroup =  findViewById(R.id.main_group);
        hongdian = findViewById(R.id.main_hongdian);
        data = new ArrayList<>();
        adapter2 = new HomeTanAdapter(this,data);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", " ");
        mlocationClient = new LocationClient(this);

        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);

//        adapter2.setOnItemDeleteClickListener(new HomeTanAdapter.onItemDeleteListener() {
//            @Override
//            public void onDeleteClick(int i, int tag) {
//                LogU.Ld("1608","点击了");
//                if (tag == 1){
//                    querenjieshu(data.get(i).getUuid());
//                }else if(tag == 2){
//                    dingwei(i);
//                }else if(tag == 3){
//                    Intent intent = new Intent();
//                    Bundle bundle = new Bundle();//传值
//                    intent.setClass(MainActivity.this, HDJGActivity.class);
//                    bundle.putString("uuid", data.get(i).getUuid());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            }
//        });
        init();
    }




    private void init() {

        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new FujinFragment());
//        list.add(new JingcaiFragment());
        list.add(new XiaoxiFragment());
        list.add(new MyFragment());
        adapter = new HomeFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        spUtileFQTZ = new SPUtileFQTZ();

findViewById(R.id.main_button_release).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int netWorkStart = NetUtil.getNetWorkStart(MainActivity.this);
        LogU.Ld("1608","网络"+netWorkStart);
        if (netWorkStart!=1) {
            if (token.equals("无")) {
                ToastUitl.longs("您还未登录");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DengluActivity.class);
                startActivity(intent);
            } else {
                jiance();
            }
        }
    }
});
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_button1:
                        viewPager.setCurrentItem(0,false);
                        NetUtil.getNetWorkStart(MainActivity.this);
                        StatusBarUtil.setColor(MainActivity.this,getResources().getColor(R.color.tab_backgroud),0);
                        break;
                    case R.id.main_button2:
//                        if(shouji.equals("无")){
//                            Intent intent = new Intent();
//                            intent.setClass(MainActivity.this,DengluActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }else{
                       NetUtil.getNetWorkStart(MainActivity.this);

                            viewPager.setCurrentItem(1,false);
                        StatusBarUtil.setColor(MainActivity.this,getResources().getColor(R.color.white),0);
                        break;
                    case R.id.main_button3:
                        viewPager.setCurrentItem(2,false);
                        NetUtil.getNetWorkStart(MainActivity.this);
                        StatusBarUtil.setColor(MainActivity.this,getResources().getColor(R.color.white),0);
                        break;

                    case R.id.main_button4:
                        LogU.Ld("1608", "Main+++" + token);
                        NetUtil.getNetWorkStart(MainActivity.this);
                        if(token.equals("无")){
                            Intent intent = new Intent();
                        intent.setClass(MainActivity.this,DengluActivity.class);
                        startActivity(intent);
                        finish();
                        }else{
                            viewPager.setCurrentItem(3,false);
                            StatusBarUtil.setColor(MainActivity.this,getResources().getColor(R.color.hongse),0);
                        }
//                        viewPager.setCurrentItem(3,false);

                        break;

                }

            }
        });



        jiancexiaoxi();

    }
    //检测消息
    private void jiancexiaoxi() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNotReadMessageCount")
                .addHeader("token",token)
                .addParams("msgCate","systems " )
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JCXIEntity entity = gson.fromJson(result, JCXIEntity.class);
                            if (entity.getData().getNotReadCount()>0){
                                hongdian.setVisibility(View.VISIBLE);

                            }else {
                                hongdian.setVisibility(View.GONE);
                            }

                            initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }
    //确认结束
    private void querenjieshu(String publishcId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "确认结束---" + token + "---publishcId---" + publishcId);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirm")
                .addHeader("token", token)
                .addParams("uuid", publishcId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "确认结束" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "确认结束成功", Toast.LENGTH_SHORT).show();
                            initDownload();
//                            mCameraDialog.dismiss();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });
    }

    //签到
    private void qiandao(String publishcId,String lat,String lng) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "签到---" + token + "---publishcId---" + publishcId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userArrivalSignin")
                .addHeader("token", token)
                .addParams("publicUid", publishcId)
                .addParams("lat", lat)
                .addParams("lng", lng)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "签到" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            mCameraDialog.dismiss();
                            mlocationClient.stop();
                            initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }

                        }
                    }
                });

    }

    //弹窗
    private void initDownload() {
//        mCameraDialog.dismiss();
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "弹窗" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/Statistics")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "弹窗" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            HomeTanEntity entity = gson.fromJson(result, HomeTanEntity.class);
                            List<HomeTanEntity.DataBean> list;
                            list = entity.getData();
                            data.clear();
                            data.addAll(list);
                            adapter2.notifyDataSetChanged();

                            if (entity.getMsg().equals("没有消息")){
//                                mCameraDialog.dismiss();
                                if (!mCameraDialog.isShowing()){
//                                    mCameraDialog.show();
                                    LogU.Ld("1608","正在显示2"+mCameraDialog.isShowing());
                                }else{
                                    mCameraDialog.dismiss();
                                }
                            }else{
                                setDialog();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
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
    private void dingwei(int i) {
//        定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LogU.Ld("1608","进来了1");
        mlistener = new MylocationListener(i);

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
    //所有的定位信息都通过接口回调来实现
    public class MylocationListener implements BDLocationListener {

        //定位请求回调接口
        private boolean isFirstIn = true;
        int i;
        public  MylocationListener(int i){
            this.i = i;
        }

        //定位请求回调函数,这里面会得到定位信息
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            LogU.Ld("1608","进来了2");
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
            if (isFirstIn) {
                isFirstIn = false;
//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude);
                qiandao(data.get(i).getUuid(),mLatitude+"",mLongitude+"");
            }


        }

    }
    //性别选择
    private void setDialog() {


        LinearLayout root;
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.home_tan, null);
        imageView = root.findViewById(R.id.home_tan_image);
        listView = root.findViewById(R.id.home_tan_list);
        listView.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(MainActivity.this, HomeHDXQActivity.class);
                bundle.putString("uuid", data.get(position).getUuid());
                if (data.get(position).getPublicStatus() == 1) {
                    bundle.putString("tab", "1");
                }else if(data.get(position).getPublicStatus() == 2){
                    bundle.putString("tab", "2");
                }else if(data.get(position).getPublicStatus() == 3){
                    bundle.putString("tab", "3");
                }else if(data.get(position).getPublicStatus() == 4){
                    bundle.putString("tab", "4");
                }else if(data.get(position).getPublicStatus() == 5){//已完成
                    bundle.putString("tab", "5");
                }else if(data.get(position).getPublicStatus() == 6){
                    bundle.putString("tab", "6");
                }else if(data.get(position).getPublicStatus() == 7){
                    bundle.putString("tab", "7");
                }else if(data.get(position).getPublicStatus() == 8){
                    bundle.putString("tab", "8");
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });

        adapter2.setOnItemDeleteClickListener(new HomeTanAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i, int tag,int b) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                LogU.Ld("1608","点击了");
                if (tag == 1){
                    querenjieshu(data.get(i).getUuid());
                }else if(tag == 2){
                    mlocationClient.start();
                    dingwei(i);
                }else if(tag == 0){

                    intent.setClass(MainActivity.this, HDJGActivity.class);
                    bundle.putString("uuid", data.get(i).getUuid());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(tag == 4){
                    intent.setClass(MainActivity.this, PingjiaTwoActivity.class);
                    bundle.putString("uuid", data.get(i).getUuid());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(tag == 3){
                    if (b == 2){
                        ptjrInit( data.get(i).getUuid());
                    }else{
                        tongyi( data.get(i).getUuid());
                    }
                }
            }
        });


        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        LogU.Ld("1608","正在显示1"+mCameraDialog.isShowing());
        if (!mCameraDialog.isShowing()){
            mCameraDialog.show();
            LogU.Ld("1608","正在显示2"+mCameraDialog.isShowing());
        }

    }

    //平台接入
    private void ptjrInit(String uuid) {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PlatformIntervention")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            init();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    //同意处理结果
    private void tongyi(String uuid) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainAgree")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "同意" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
                            init();
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    // 用来计算返回键的点击间隔时间
     private long exitTime = 0;
     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
                 if (keyCode == KeyEvent.KEYCODE_BACK
                         && event.getAction() == KeyEvent.ACTION_DOWN) {
                         if ((System.currentTimeMillis() - exitTime) > 2000) {
                                 //弹出提示，可以有多种方式
                                 Toast.makeText(getApplicationContext(), "再按一次离开挑战", Toast.LENGTH_SHORT).show();
                                 exitTime = System.currentTimeMillis();
                             } else {
                                 finish();
                             }
                         return true;
                     }

                 return super.onKeyDown(keyCode, event);
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


    @Override
    protected void onRestart() {
        super.onRestart();
        initDownload();
    }

    //检测信息是否完善
    private void jiance() {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserPerfectInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检验资料" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Intent intent = new Intent();
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
                            intent.setClass(MainActivity.this, FaqiTiaozhanActivity.class);
                           // spUtileFQTZ.clear(MainActivity.this);
                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (entity.getMsg().equals("登录超时")){
                                ToastUitl.longs("您还未登录");
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }else{
                                showNormalDialog();
                            }


                        }
                    }
                });

    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("报名和发布活动前，请完善信息");
        normalDialog.setPositiveButton("先看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("去完善",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        intent.setClass(MainActivity.this, GRXXActivity.class);
                        bundle.putString("tab", "1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }


}
