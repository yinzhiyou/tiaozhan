package com.example.android.tiaozhan.Home;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Denglu.GRXXActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyTYJBEntity;
import com.example.android.tiaozhan.Entity.PanduanQiandanEntity;
import com.example.android.tiaozhan.My.JBMXActivity;
import com.example.android.tiaozhan.My.YJFKActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.NetUtil;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Wonderful.JCFBActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;

import okhttp3.Call;

/**
 *任务界面
 */
public class RenWuActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, jinbi, jinbida, qiandaoText,tyjb;
    private ImageView fanhui;
    private RelativeLayout fenxian1, fenxiang2, qufabu, quxinzeng, qugenggai, fabujingcai, qiandaoLayout,renwu_qufankui;
    private String token, touxiang, nickname, tab, jinbiString;
    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ren_wu);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_ren_wu;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
//        fenxian1 = findViewById(R.id.renwu_fenxiang1);
//        fenxian1.setOnClickListener(this);
        fenxiang2 = findViewById(R.id.renwu_fenxiang2);
        fenxiang2.setOnClickListener(this);
        qufabu = findViewById(R.id.renwu_qufabu);
        qufabu.setOnClickListener(this);
        quxinzeng = findViewById(R.id.renwu_quxinzeng);
        quxinzeng.setOnClickListener(this);
        qugenggai = findViewById(R.id.renwu_qugengzheng);
        qugenggai.setOnClickListener(this);
        fabujingcai = findViewById(R.id.renwu_fabujingcai);
        fabujingcai.setOnClickListener(this);
        jinbi = findViewById(R.id.renwu_jinbi);
        jinbida = findViewById(R.id.renwu_jinbida);
        qiandaoLayout = findViewById(R.id.renwu_qiandao_layout);
        qiandaoLayout.setOnClickListener(this);
        qiandaoText = findViewById(R.id.renwu_qiandao_text);
        tyjb = findViewById(R.id.youshangjiao);
        tyjb.setOnClickListener(this);
        renwu_qufankui = findViewById(R.id.renwu_qufankui);
        renwu_qufankui.setOnClickListener(this);
        tyjb.setVisibility(View.VISIBLE);


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        spUtileFQTZ = new SPUtileFQTZ();
        initDownload();
        panduan();
    }

    @Override
    protected void initData() {
        biaoti.setText("任务列表");
        tyjb.setText("对手币明细");
    }

    @Override
    public void onClick(View v) {
        NetUtil.getNetWorkStart(this);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
//            case R.id.renwu_fenxiang1://分享活动
//                intent.setClass(this, MyhuodongActivity.class);
//                startActivity(intent);
//                break;
            case R.id.renwu_fenxiang2:
                intent.setClass(this, FenXiangActivity.class);
                startActivity(intent);
                break;
            case R.id.renwu_qufabu://发起挑战
                jiance("1");
//                 intent.setClass(this,FaqiTiaozhanActivity.class);
//                 startActivity(intent);
                break;
            case R.id.renwu_quxinzeng:
                intent.setClass(this, CGGZActivity.class);

                bundle.putString("tag","1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.renwu_qugengzheng:
                intent.setClass(this, CGGZActivity.class);
                bundle.putString("tag","2");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.renwu_fabujingcai:
                jiance("2");
//                 intent.setClass(this,JCFBActivity.class);
//                 startActivity(intent);
                break;
            case R.id.renwu_qiandao_layout:
                if (!EmptyUtils.isStrEmpty(tab)) {
                    if (tab.equals("1")) {
                        ToastUitl.longs("今天已签到");
                    } else {
                        qiandao();
                    }
                }
                break;
            case R.id.youshangjiao:
                intent.setClass(this, JBMXActivity.class);
                bundle.putString("tab","1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.renwu_qufankui:
                intent.setClass(this,YJFKActivity.class);

                startActivity(intent);
                break;
        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCommonCoins")
                .addHeader("token", token)
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
                        if (a) {
                            Gson gson = new Gson();
                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
                            DecimalFormat df = new DecimalFormat("0.00");
                            jinbi.setText("对手币" +  df.format(entity.getData().getCoins()) + "个");
                            jinbida.setText(df.format(entity.getData().getCoins()) + "");
                            jinbiString = entity.getData().getCoins() + "";

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(RenWuActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //判断签到
    private void panduan() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "判断签到" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserIsSign")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "判断签到" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PanduanQiandanEntity entity = gson.fromJson(result, PanduanQiandanEntity.class);
                            tab = entity.getData().getIsSign() + "";
                            LogU.Ld("1608", "判断===签到" + entity.getData().getIsSign());

                            if (entity.getData().getIsSign() == 1) {
                                //已签到
                                LogU.Ld("1608", "判断===签到" + entity.getData().getIsSign());
                              //  qiandaoLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                qiandaoLayout.setBackgroundResource(R.drawable.renwu_button);
                                qiandaoText.setText("已签到");
                            } else {
                                qiandaoLayout.setBackgroundResource(R.drawable.login_rounded_corners);
                                qiandaoText.setText("签到");
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(RenWuActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //签到
    private void qiandao() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userSignIn")
                .addHeader("token", token)
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
//                        HomeDialog dialog = new HomeDialog(RenWuActivity.this,jinbiString);
//                        dialog.show();
                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            initDownload();
//                            HomeDialog dialog = new HomeDialog(RenWuActivity.this, jinbiString);
//                            dialog.show();
                            qiandaoText.setText("已签到");
                            qiandaoLayout.setBackgroundResource(R.drawable.renwu_button);
                            ToastUitl.longs("签到成功");
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }


    //检测信息是否完善
    private void jiance(final String tag) {
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
                            Bundle bundle = new Bundle();//接收
                            if (tag.equals("1")) {
                                intent.setClass(RenWuActivity.this, FaqiTiaozhanActivity.class);
                                bundle.putString("tagTZ", "1");
                                intent.putExtras(bundle);
                                spUtileFQTZ.clear(RenWuActivity.this);
                                startActivity(intent);
                            } else {
                                intent.setClass(RenWuActivity.this, JCFBActivity.class);
                                startActivity(intent);
                            }


//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            intent.setClass(getContext(), FaqiTiaozhanActivity.class);
//                            spUtileFQTZ.clear(getContext());
//                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            showNormalDialog();

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
                new AlertDialog.Builder(RenWuActivity.this);
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
                        intent.setClass(RenWuActivity.this, GRXXActivity.class);
                        bundle.putString("tab", "1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDownload();
        }

    }
