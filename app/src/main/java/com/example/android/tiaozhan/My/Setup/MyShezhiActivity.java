package com.example.android.tiaozhan.My.Setup;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Denglu.GRXXActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 设置
 */
public class MyShezhiActivity extends BaseActivity implements View.OnClickListener{
    private Dialog dialog;
    private TextView ds_xz;
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
                                Bundle bundle = new Bundle();//传值
                                bundle.putString("tag", "1");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            shimingRZ();
                        }
                    }
                });
    }

    private void showAlterDialog() {

        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("您确定要退出登录吗?");


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyShezhiActivity.this,DengluActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    //弹窗实名认证
    private void shimingRZ() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


            ds_xz.setText("您好，您还没有实名认证，请先进行实名认证。");


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(MyShezhiActivity.this,SMRZActivity.class);

                bundle.putString("tab","1");
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
