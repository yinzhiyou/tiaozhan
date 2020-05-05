package com.example.android.tiaozhan.Home;

import android.Manifest;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ShareUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *分享
 */
public class FenXiangActivity extends BaseActivity implements View.OnClickListener{
    private ImageView fanhui;
    private String token, touxiang, nickname, tab, jinbiString;
    private SPUtils spUtils;
    private TextView biaoti;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fen_xiang);
//    }
    private LinearLayout weixin;
    @Override
    public int initContentView() {
        return R.layout.activity_fen_xiang;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        weixin = findViewById(R.id.fenxian_weixin);
        weixin.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

    }

    @Override
    protected void initData() {

        biaoti.setText("邀请好友");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.fenxian_weixin:
                initDownload();
                if(Build.VERSION.SDK_INT>=23){

                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(this,mPermissionList,123);


                }
                ShareUtils.shareWeb(FenXiangActivity.this,"http://www.baidu.com","找运动伙伴，用“找对手”；"
                        , "遇到最好的伙伴，成就更好的自己。", "",R.mipmap.logo, SHARE_MEDIA.WEIXIN
                );

                break;
            case R.id.fenxiang_pengyouquan:
                initDownload();
                if(Build.VERSION.SDK_INT>=23){

                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(this,mPermissionList,123);

                }
                ShareUtils.shareWeb(FenXiangActivity.this,"http://www.baidu.com","找运动伙伴，用“找对手”；"
                        , "遇到最好的伙伴，成就更好的自己。", "", R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                );


                break;
        }
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userShare")
                .addHeader("token", token)
                .addParams("type","apps")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "通用金币" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
//                        }
                    }
                });

    }
}
