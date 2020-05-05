package com.example.android.tiaozhan.Home;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.WordEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.OnResponseListener;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ShareUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.WXShare;
import com.google.gson.Gson;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;

/**
 * 支付成功页面
 */
public class HomeZhifuCGActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dialog;
    private TextView biaoti, time, textView, tyjb, huod_xz,huod_xz_cg,fenx;
    private ImageView fanhui;
    private RelativeLayout fenxiang, xiangqing;
    private TextView icon_close;
    private WXShare wxShare;
    private String uuid, tag, timeString, token, cp_fy = "0", moshiString, shichang;
    private SimpleDateFormat simpleDateFormat;
    private Date date;
    private LinearLayout weixin, pengyouquan, weibo, ds_layout, pl_layout;
    private Dialog mCameraDialog;
    private SPUtils spUtils;
    private int Identification,status=1;
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
        huod_xz_cg = findViewById(R.id.huod_xz_cg);
        huod_xz = findViewById(R.id.huod_xz);
        huod_xz.setOnClickListener(this);

        fenx = findViewById(R.id.fenx);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//获取当前时间
        date = new Date(System.currentTimeMillis());
        timeString = simpleDateFormat.format(date);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

    }

    @Override
    protected void initData() {

        fanhui.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        wxShare.setListener(new OnResponseListener() {
            @Override
            public void onSuccess() {
                // 分享成功
                LogU.Ld("1608", "分享成功");
            }

            @Override
            public void onCancel() {
                // 分享取消
                LogU.Ld("1608", "分享取消");
            }

            @Override
            public void onFail(String message) {
                // 分享失败
                LogU.Ld("1608", "分享失败");
            }
        });

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tag = bundle.getString("tag");
        uuid = bundle.getString("uuid");
        moshiString = bundle.getString("moshiString");
        shichang = bundle.getString("shichang");
        cp_fy = bundle.getString("cp_fy");
        // 报名 tag=2 裁判报名 tag=3
        LogU.Ld("1608", "创建成功" + tag + "====" + uuid + "==" + shichang);
        if (tag.equals("1")) {
            biaoti.setText("创建成功");
            textView.setText("创建成功");
            time.setText("创建时间:" + timeString);
            Identification = 1;
        } else if (tag.equals("4")) {//预定场馆
            biaoti.setText("预订成功");
            textView.setText("预订成功");
            time.setText("创建时间:" + timeString);
            status=2;
             Identification=1;
        } else {
            biaoti.setText("报名成功");
            textView.setText("报名成功");
            time.setText("报名时间:" + timeString);
            if (!EmptyUtils.isStrEmpty(cp_fy)) {
                Identification = 3;
            } else {
                Identification = 2;
            }

        }


        LogU.Ld("1608", "创建成功=======" + tag + "====" + uuid + "======" + Identification + "====" + cp_fy);
        initDownload();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()) {
            case R.id.fanhui:
                intent.setClass(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.CG_lijifenxiang:
                if (Build.VERSION.SDK_INT >= 23) {

                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};

                    ActivityCompat.requestPermissions(this, mPermissionList, 123);

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
                } else if (tag.equals("2")) {
                    intent.setClass(HomeZhifuCGActivity.this, HomeHDXQActivity.class);
                    bundle.putString("tab", "11");
                    bundle.putString("uuid", uuid);
                    bundle.putString("fabuzhe", tag);
//                bundle.putString("time",timeString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (tag.equals("3")) {
                    intent.setClass(HomeZhifuCGActivity.this, HomeHDXQActivity.class);
                    bundle.putString("tab", "11");
                    bundle.putString("uuid", uuid);
                    bundle.putString("fabuzhe", tag);
//                bundle.putString("time",timeString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (tag.equals("4")) {
                    intent.setClass(HomeZhifuCGActivity.this, HomeReserveCGDetailsActivity.class);
                    bundle.putString("tab", "11");
                    bundle.putString("uuid", uuid);
                    bundle.putString("fabuzhe", tag);
//                bundle.putString("time",timeString);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.cg_fenxiang_weixin:

                ShareUtils.shareWeb(HomeZhifuCGActivity.this, "http://www.baidu.com", "ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN
                );

                break;
            case R.id.cg_fenxiang_pengyouquan:

                ShareUtils.shareWeb(HomeZhifuCGActivity.this, "http://www.baidu.com", "ss"
                        , "ss", "ss", R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                );


                break;
            case R.id.cg_fenxiang_weibo:
                ToastUitl.longs("暂未开放");
                break;
            case R.id.CG_tyjb:
                intent.setClass(this, HomeTYJBActivity.class);
                startActivity(intent);
                break;
            case R.id.huod_xz:
                if (tag.equals("3") || tag.equals("4")) {
                    return;
                } else {
                    chooserGZ();
                }
              /*  if (moshiString.equals("预订场馆")){

                }else {
                    chooserGZ();
                    ToastUitl.longs("jfdskfjsldjf");
                }*/

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

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "支付成功" + token + "==" + uuid + "===" + Identification + "===" + cp_fy);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getwWord")
                .addHeader("token", token)
                .addParams("inviteId", uuid)
                .addParams("Identification", Identification + "")
                .addParams("referee", cp_fy + "")
                .addParams("status", status + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "支付成功" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            WordEntity entity = gson.fromJson(result, WordEntity.class);
                            String comm = entity.getData().getComm();
                            LogU.Ld("1608", "裁判支付成功" + comm);

                            fenx.setText(entity.getData().getComms());
                            if (tag.equals("4")) {
                                huod_xz_cg.setText(comm);
                                huod_xz_cg.setVisibility(View.VISIBLE);
                                huod_xz.setVisibility(View.GONE);
                            }else
                            if (moshiString.equals("3") || moshiString.equals("4")) {
                                int tb = comm.indexOf("规");
                                int te = comm.indexOf("则") + 5;
                            /*String text_b = comm.substring(0, tb);
                            String text_m = comm.substring(tb, te);
                            String text_e = comm.substring(te, comm.length());*/
                                SpannableStringBuilder style = new SpannableStringBuilder(comm);
                                StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                                style.setSpan(styleSpan, tb, te, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                style.setSpan(new ForegroundColorSpan(Color.BLUE), tb, te, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                huod_xz.setText(style);
                            }
                            else if (Identification == 3) {
                                huod_xz.setText(comm);
                            } else {
                                int tb = comm.indexOf("规");
                                int te = comm.indexOf("则") + 4;
                            /*String text_b = comm.substring(0, tb);
                            String text_m = comm.substring(tb, te);
                            String text_e = comm.substring(te, comm.length());*/
                                SpannableStringBuilder style = new SpannableStringBuilder(comm);
                                StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                                style.setSpan(styleSpan, tb, te, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                style.setSpan(new ForegroundColorSpan(Color.BLUE), tb, te, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                huod_xz.setText(style);
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeZhifuCGActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            /*if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(HomeZhifuCGActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }*/
                        }
                    }
                });

    }

    //弹窗
    private void chooserGZ() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.fragment_hou_xz, null);
        icon_close = sport.findViewById(R.id.icon_close1);
        ds_layout = sport.findViewById(R.id.ds_layout);
        pl_layout = sport.findViewById(R.id.pl_layout);
        TextView pl_xz_1 = sport.findViewById(R.id.pl_xz_1);
        TextView pl_xz_2 = sport.findViewById(R.id.pl_xz_2);
        TextView ds_xz_1 = sport.findViewById(R.id.ds_xz_1);
        TextView ds_xz_2 = sport.findViewById(R.id.ds_xz_2);
        LogU.Ld("1608", "时长===" + shichang);
        if (moshiString.equals("1") || moshiString.equals("2")) {

            if (shichang.equals("0.5")) {
                ds_xz_1.setVisibility(View.VISIBLE);
                ds_xz_2.setVisibility(View.GONE);
            } else {
                ds_xz_1.setVisibility(View.GONE);
                ds_xz_2.setVisibility(View.VISIBLE);
            }

            ds_layout.setVisibility(View.VISIBLE);
            pl_layout.setVisibility(View.GONE);

        } else {
            if (shichang.equals("0.5")) {
                pl_xz_1.setVisibility(View.VISIBLE);
                pl_xz_2.setVisibility(View.GONE);
            } else {
                pl_xz_1.setVisibility(View.GONE);
                pl_xz_2.setVisibility(View.VISIBLE);
            }
            ds_layout.setVisibility(View.GONE);
            pl_layout.setVisibility(View.VISIBLE);
        }
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
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
