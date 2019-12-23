package com.example.android.promoter.My.Setup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.HomeShaixuanOne;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.SMRZEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 设置
 */
public class MyShezhiActivity extends BaseActivity implements View.OnClickListener{

    private TextView biaoti;
    private ImageView fanhui;
    private LinearLayout geren,wenti,mima,anquan,yinsi;
    private RelativeLayout tuichu;
    private SPUtils spUtils;
    private String token,touxiang,nickname,uuid;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_shezhi);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_shezhi;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        geren = findViewById(R.id.my_shezhi_geren);
        geren.setOnClickListener(this);
        wenti = findViewById(R.id.my_shezhi_wenti);
        wenti.setOnClickListener(this);
        mima = findViewById(R.id.my_shezhi_mima);
        mima.setOnClickListener(this);
        anquan = findViewById(R.id.my_shezhi_anquan);
        anquan.setOnClickListener(this);
        yinsi = findViewById(R.id.my_shezhi_yinsi);
        yinsi.setOnClickListener(this);
        tuichu = findViewById(R.id.my_shezhi_tuichu);
        tuichu.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        biaoti.setText("设置");
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        uuid = (String) spUtils.get(this, "uuid", "");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()){
            case R.id.my_shezhi_tuichu:
                spUtils.clear(this);

                showAlterDialog();
                break;
            case R.id.my_shezhi_geren:
                intent.setClass(this, GRXXActivity.class);

                bundle.putString("tab","2");
                intent.putExtras(bundle);

                startActivity(intent);
                break;
            case R.id.my_shezhi_wenti:
                init("1");

                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_shezhi_mima:
                init("2");

                break;
            case R.id.my_shezhi_anquan:
                intent.setClass(this, AnquanActivity.class);
                startActivity(intent);
                break;
            case R.id.my_shezhi_yinsi:
                intent.setClass(this, YSSZActivity.class);
                startActivity(intent);
                break;
        }
    }

    //实名验证
    private void init(final String tag) {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            SMRZEntity entity = gson.fromJson(result, SMRZEntity.class);
//                            HomeDialog dialog = new HomeDialog(this);
//                            dialog.show();
//                            name.setText(entity.getData().getPlayerRealName());
//                            sfzString = entity.getData().getPlayerID();
                            Intent intent = new Intent();
                            if (tag.equals("1")){

                                intent.setClass(MyShezhiActivity.this, MyAnquanOneActivity.class);
                                startActivity(intent);
                            }else{
                                intent.setClass(MyShezhiActivity.this, MyTXMMActivity.class);
                                startActivity(intent);
                            }

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs("您还未实名认证");

                        }
                    }
                });
    }

    private void showAlterDialog() {
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(MyShezhiActivity.this);
        alterDiaglog.setIcon(R.mipmap.logo);//图标
        alterDiaglog.setTitle("温馨提示");//文字
        alterDiaglog.setMessage("您确定要退出登录吗?");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent();
                intent.setClass(MyShezhiActivity.this,DengluActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        //显示
        alterDiaglog.show();
    }
}
