package com.example.android.promoter.Home;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.OnResponseListener;
import com.example.android.promoter.Toos.ShareUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.WXShare;
import com.example.android.promoter.reserve.ReserveCGDetailsActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *支付成功页面
 */
public class HomeZhifuCGActivity extends BaseActivity implements View.OnClickListener{


    private TextView biaoti,time,textView,tyjb;
    private ImageView fanhui;
    private RelativeLayout fenxiang,xiangqing;

    private WXShare wxShare;
    private String uuid,tag,timeString;
    private   SimpleDateFormat simpleDateFormat;
    private   Date date;
    private LinearLayout weixin,pengyouquan,weibo;
    private Dialog mCameraDialog;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_zhifu_cg);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_home_zhifu_cg;
    }

    @Override
    protected void initUIAndListener() {
        wxShare = new WXShare(this);

        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fenxiang = findViewById(R.id.CG_lijifenxiang);
        xiangqing = findViewById(R.id.CG_xiangqing);
        xiangqing.setOnClickListener(this);
        time = findViewById(R.id.CG_shijian);
        textView = findViewById(R.id.CG_text);
        tyjb = findViewById(R.id.CG_tyjb);
        tyjb.setOnClickListener(this);

         simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
         date = new Date(System.currentTimeMillis());
        timeString = simpleDateFormat.format(date);

    }

    @Override
    protected void initData() {

        fanhui.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        wxShare.setListener(new OnResponseListener() {
            @Override
            public void onSuccess() {
                // 分享成功
                LogU.Ld("1608","分享成功");
            }

            @Override
            public void onCancel() {
                // 分享取消
                LogU.Ld("1608","分享取消");
            }

            @Override
            public void onFail(String message) {
                // 分享失败
                LogU.Ld("1608","分享失败");
            }
        });

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tag =  bundle.getString("tag");
        uuid =  bundle.getString("uuid");

        if (tag.equals("1")){
            biaoti.setText("创建成功");
            textView.setText("创建成功");
            time.setText("创建时间"+timeString);
        }else if(tag.equals("4")){
            biaoti.setText("创建成功");
            textView.setText("创建成功");
            time.setText("创建时间"+timeString);

        }else {
            biaoti.setText("报名成功");
            textView.setText("报名成功");
            time.setText("报名时间"+timeString);
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()){
            case R.id.fanhui:
                intent.setClass(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.CG_lijifenxiang:
                if(Build.VERSION.SDK_INT>=23){

                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};

                    ActivityCompat.requestPermissions(this,mPermissionList,123);

                }
                setDialog();
//                wxShare.share("这是要分享的文字");
                break;
            case R.id.CG_xiangqing:

                if (tag.equals("1")) {
                    intent.setClass(HomeZhifuCGActivity.this, HomeHDXQActivity.class);
                    bundle.putString("tab", "11");
                    bundle.putString("uuid", uuid);
                    bundle.putString("fabuzhe", tag);
//                bundle.putString("time",timeString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if (tag.equals("4")){
                    intent.setClass(HomeZhifuCGActivity.this, ReserveCGDetailsActivity.class);
                    bundle.putString("tab", "11");
                    bundle.putString("uuid", uuid);
                    bundle.putString("fabuzhe", tag);
//                bundle.putString("time",timeString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.cg_fenxiang_weixin:

                ShareUtils.shareWeb(HomeZhifuCGActivity.this,"http://www.baidu.com","ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN
                );

                break;
            case R.id.cg_fenxiang_pengyouquan:

                ShareUtils.shareWeb(HomeZhifuCGActivity.this,"http://www.baidu.com","ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                );


                break;
            case R.id.cg_fenxiang_weibo:
                ToastUitl.longs("暂未开放");
                break;
            case R.id.CG_tyjb:
                intent.setClass(this,HomeTYJBActivity.class);
                startActivity(intent);
                break;
        }
    }

    //分享
    private void setDialog() {

        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.chenggong_fenxiang, null);
        weixin = root.findViewById(R.id.cg_fenxiang_weixin);
        weixin.setOnClickListener(this);
        pengyouquan = root.findViewById(R.id.cg_fenxiang_pengyouquan);
        pengyouquan.setOnClickListener(this);
        weibo = root.findViewById(R.id.cg_fenxiang_weibo);
        weibo.setOnClickListener(this);


        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果返回键按下
            //此处写退向后台的处理
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
    @Override
    protected void onStart() {
        super.onStart();
        wxShare.register();
    }

    @Override
    protected void onDestroy() {
        wxShare.unregister();
        super.onDestroy();
    }


}
