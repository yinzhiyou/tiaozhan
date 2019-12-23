package com.example.android.promoter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Promoter.MyPromoterActivity;
import com.example.android.promoter.Promoter.TuiguangHomeActivity;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by 72909 on 2018/5/11.
 */

public class Main extends Activity {

    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int PRIVATE_CODE = 1315;//开启GPS权限
    private LocationManager lm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//设置当前视图为main
        com.jaeger.library.StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
        final Intent it = new Intent(this, MainActivity.class); //下一步转向Mainctivity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it); //执行意图
                finish();
            }
        };

        timer.schedule(task, 1000 * 2); //3秒后跳转，这里根据自己需要设定时间
//        banben();
    }

//    //获取版本
//    public void banben(){
//        Log.d("1608","版本走了");
//        OkHttpUtils
//                .post()
//                .url("http://app.gelabang.com/glb/api/carousel/banben2")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        Log.d("1608","版本+"+result);
//                        Boolean a = result.indexOf("1") != -1;
//                        if(!a){
//
//                        }else {
//                            Gson gson = new Gson();
//                            BanbenEntity entity = gson.fromJson(result,BanbenEntity.class);
//                            String ss = entity.getData().get(0).getBanben();
//                            try {
//                                if(!ss.equals(getVersionName())){
//                                    showNormalDialog(entity.getData().get(0).getTishi());
//
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                });
//    }
//
//
//    //版本号
//    private String getVersionName() throws Exception
//    {
//        // 获取packagemanager的实例
//        PackageManager packageManager = this.getPackageManager();
//        // getPackageName()是你当前类的包名，0代表是获取版本信息
//        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
//        String version = packInfo.versionName;
//        return version;
//    }
//
//    //跳转商城
//    public void launchAppDetail(String appPkg, String marketPkg) {
//        try {
//            if (TextUtils.isEmpty(appPkg)) return;
//            Uri uri = Uri.parse("market://details?id=" + appPkg);
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            if (!TextUtils.isEmpty(marketPkg)) {
//                intent.setPackage(marketPkg);
//            }
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showNormalDialog(String a){
//        /* @setIcon 设置对话框图标
//         * @setTitle 设置对话框标题
//         * @setMessage 设置对话框消息提示
//         * setXXX方法返回Dialog对象，因此可以链式设置属性
//         */
//        final AlertDialog.Builder normalDialog =
//                new AlertDialog.Builder(Main.this);
//        normalDialog.setIcon(R.mipmap.log);
//        normalDialog.setTitle("温馨提示");
//        normalDialog.setMessage(a);
//        normalDialog.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //...To-do
////                        com.qihoo.appstore
////                        launchAppDetail("com.gelabang.gelabang","");
//                        goToMarket(Main.this,"com.gelabang.gelabang");
//                    }
//                });
//        normalDialog.setNegativeButton("关闭",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //...To-do
//                    }
//                });
//        // 显示
//
//        normalDialog.show();
//    }
//
//    public static void goToMarket(Context context, String packageName) {
//        Uri uri = Uri.parse("market://details?id=" + packageName);
//        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//        try {
//            context.startActivity(goToMarket);
//        } catch (ActivityNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public void showGPSContacts() {

        //得到系统的位置服务，判断GPS是否激活
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, LOCATIONGPS, BAIDU_READ_PHONE_STATE);
                } else {
                   // dingwei();//有权限，进行相应的处理
                }
            } else {
               // dingwei();//有权限，进行相应的处理
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, PRIVATE_CODE);
        }
    }

    /**
     * Android6.0申请权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
//             requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                //如果用户取消，permissions可能为null.
                if (grantResults[0] == PERMISSION_GRANTED && grantResults.length > 0) { //有权限
                    // 获取到权限，作相应处理
                   // dingwei();//有权限，进行相应的处理
                } else {
                    /*
                     * 无权限
                     * */

                    Toast.makeText(this, "你未开启定位权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }
    }

    static final String[] LOCATIONGPS = new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
}
