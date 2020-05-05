package com.example.android.tiaozhan.Promoter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.YHKAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JianceWXEntity;
import com.example.android.tiaozhan.Entity.JianceZFBEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PromoterMyMoneyEntity;
import com.example.android.tiaozhan.Entity.TXEntity;
import com.example.android.tiaozhan.Entity.YHKEntity;
import com.example.android.tiaozhan.My.TJYHKActivity;
import com.example.android.tiaozhan.My.TXCGActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.zhifu.PayFragment;
import com.example.android.tiaozhan.Toos.zhifu.PayPwdView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class PromoterTXianActivity extends AppCompatActivity implements View.OnClickListener, PayPwdView.InputCallBack {

    private TextView biaoti, jiaka, yue, quanbu;
    private ImageView fanhui;
    private RelativeLayout tixian;
    private ImageView imageWX, imageZFB;
    private int tag, xiabiao;
    private LinearLayout wx, zfb;
    private String token, touxiang, nickname, uuid,  zfbid, wxid,yueString;
    private SPUtils spUtils;
    private EditText editText;
    private ListView yhk;
    private List<YHKEntity.DataBean> data;
    private YHKAdapter adapter;
    private PayFragment fragment;
    private String tgId;
    private double money,srje;


    // private double ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tixian);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        tixian = findViewById(R.id.my_tixian_tx);
        tixian.setOnClickListener(this);
        jiaka = findViewById(R.id.my_tixian_jiaka);
        jiaka.setOnClickListener(this);
        imageWX = findViewById(R.id.my_tixian_weixin);
//        imageWX.setOnClickListener(this);
        imageZFB = findViewById(R.id.my_tixian_zhifubao);
//        imageZFB.setOnClickListener(this);
        wx = findViewById(R.id.my_tixian_layout_wx);
        wx.setOnClickListener(this);
        zfb = findViewById(R.id.my_tixian_layout_zfb);
        zfb.setOnClickListener(this);
        editText = findViewById(R.id.my_tixian_jine);
        yhk = findViewById(R.id.tixian_yhk);
        yue = findViewById(R.id.my_tixian_yue);
        quanbu = findViewById(R.id.my_tixian_quanbu);
        quanbu.setOnClickListener(this);

        data = new ArrayList<>();
        adapter = new YHKAdapter(this, data);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        Intent intent = getIntent();
        tgId = intent.getStringExtra("tgId");

        biaoti.setText("提现");
        initDownload();
    }

//    @Override
//    public int initContentView() {
//        return R.layout.activity_my_tixian;
//
//
//    }
//
//    @Override
//    protected void initUIAndListener() {
//        biaoti = findViewById(R.id.biaoti);
//        fanhui = findViewById(R.id.fanhui);
//        fanhui.setOnClickListener(this);
//        tixian = findViewById(R.id.my_tixian_tx);
//        tixian.setOnClickListener(this);
//        jiaka = findViewById(R.id.my_tixian_jiaka);
//        jiaka.setOnClickListener(this);
//        imageWX = findViewById(R.id.my_tixian_weixin);
////        imageWX.setOnClickListener(this);
//        imageZFB = findViewById(R.id.my_tixian_zhifubao);
////        imageZFB.setOnClickListener(this);
//        wx = findViewById(R.id.my_tixian_layout_wx);
//        wx.setOnClickListener(this);
//        zfb = findViewById(R.id.my_tixian_layout_zfb);
//        zfb.setOnClickListener(this);
//        editText = findViewById(R.id.my_tixian_jine);
//        yhk = findViewById(R.id.tixian_yhk);
//        data = new ArrayList<>();
//        adapter = new YHKAdapter(this,data);
//
//        spUtils = new SPUtils();
//        token = (String) spUtils.get(this, "logintoken", "");
//
//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(true);
//        UMShareAPI.get(this).setShareConfig(config);
//    }
//
//    @Override
//    protected void initData() {
//        biaoti.setText("提现");
//        initDownload();
//    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.my_tixian_tx:
                String s = editText.getText().toString();

                if (TextUtils.isEmpty(s)) {
                    ToastUitl.longs("请输入金额");
                } else {
                    srje = Double.parseDouble(editText.getText().toString());
                    if (srje>money){
                        ToastUitl.longs("用户余额不足");
                    }else {
                        if (tag == 1) {
                            jiancewx();
                        } else if (tag == 2) {
                            jiance();
                        } else {
                            if (data.size() < 1) {
                                ToastUitl.longs("您还未绑定银行卡");
                            } else {
                                // tixian("bankCard", "", "", data.get(xiabiao).getBankCardNum());
                                Bundle bundle = new Bundle();
                                bundle.putString(PayFragment.EXTRA_CONTENT, "¥ " + editText.getText().toString());

                                fragment = new PayFragment();
                                fragment.setArguments(bundle);
                                fragment.setPaySuccessCallBack(PromoterTXianActivity.this);
                                fragment.show(getSupportFragmentManager(), "Pay");
                                fragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
                                    @Override
                                    public void onInputFinish(String result) {
                                        LogU.Ld("1608", "密码" + result);
                                        //判断密码 阴
                                        panduanmima(result);

                                    }
                                });

                            }

                        }
                    }
                }


//                intent.setClass(this,TXCGActivity.class);
//                startActivity(intent);
                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_tixian_jiaka:
                init();

                break;

            case R.id.my_tixian_layout_wx:
                imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                tag = 1;
                adapter.setSelectItem(100);
                adapter.notifyDataSetChanged();
                break;
            case R.id.my_tixian_layout_zfb:
                imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                tag = 2;
                adapter.setSelectItem(100);
                adapter.notifyDataSetChanged();
                break;
            case R.id.my_tixian_quanbu:

                    editText.setText(money+"");


                break;
        }
    }


    //实名信息
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token", token)
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

                            Intent intent = new Intent();
                            intent.setClass(PromoterTXianActivity.this, TJYHKActivity.class);
                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            LogU.Ld("1608", "实名信息" + entity.getMsg());
                            //ToastUitl.longs("您还未实名认证");
                        }
                    }
                });
    }


//推广员钱包

    private void yueTG() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterMyMoney")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "我的钱包" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            PromoterMyMoneyEntity entity = gson.fromJson(result, PromoterMyMoneyEntity.class);
                            NumberFormat nf = new DecimalFormat("#,###.##");
                            money = entity.getData().getMoney();
                            yueString = nf.format(money);

                            yue.setText("当前钱包余额" + yueString + "元");


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
        LogU.Ld("1608", "银行卡" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserBankInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "银行卡" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            YHKEntity entity = gson.fromJson(result, YHKEntity.class);
                            List<YHKEntity.DataBean> list;
                            list = entity.getData();
                            data.clear();
                            data.addAll(list);
                            yhk.setAdapter(adapter);
                            yhk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    xiabiao = position;
                                    adapter.setSelectItem(position);
                                    adapter.notifyDataSetChanged();
                                    imageWX.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                                    imageZFB.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                                    tag = 3;
                                }
                            });
                            //  yue();
                            yueTG();


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(PromoterTXianActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //检测支付宝
    private void jiance() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "检测支付宝" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserBindAlipay")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测支付宝" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            final JianceZFBEntity entity = gson.fromJson(result, JianceZFBEntity.class);
                            zfbid = entity.getData().getZfbID();
                            Bundle bundle = new Bundle();
                            bundle.putString(PayFragment.EXTRA_CONTENT, "¥ " + editText.getText().toString());

                            PayFragment fragment = new PayFragment();
                            fragment.setArguments(bundle);
                            fragment.setPaySuccessCallBack(PromoterTXianActivity.this);
                            fragment.show(getSupportFragmentManager(), "Pay");
                            fragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
                                @Override
                                public void onInputFinish(String result) {
                                    LogU.Ld("1608", "密码" + result);
                                    panduanmima(result);

                                }
                            });

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            showNormalDialog();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //判断密码是否正确
    private void panduanmima(String mima) {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkPutMoneyPwdIsTrue")
                .addHeader("token", token)
                .addParams("password", mima)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "密码判断" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            Toast.makeText(PromoterTXianActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            if (tag == 1) {

                                tixianPromoterWithdrawal("wechat", wxid);
                            } else if (tag == 2) {
                               //tixian("alipay", "", zfbid, "");
                            } else {
                              //  tixian("bankCard", "", "", data.get(xiabiao).getBankCardNum());
                                tixianPromoterWithdrawal("bankCard", data.get(xiabiao).getBankCardNum() + "");
                            }
                            fragment.dismiss();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromoterTXianActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            fragment.dismiss();
                        }

                    }
                });
    }

    //检测微信
    private void jiancewx() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "检测微信" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserBindWechat")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测微信" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JianceWXEntity entity = gson.fromJson(result, JianceWXEntity.class);
                            wxid = entity.getData().getWechartID();

                            Bundle bundle = new Bundle();
                            bundle.putString(PayFragment.EXTRA_CONTENT, "¥ " + editText.getText().toString());

                            PayFragment fragment = new PayFragment();
                            fragment.setArguments(bundle);
                            fragment.setPaySuccessCallBack(PromoterTXianActivity.this);
                            fragment.show(getSupportFragmentManager(), "Pay");
                            fragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
                                @Override
                                public void onInputFinish(String result) {
                                    LogU.Ld("1608", "密码" + result);
                                    panduanmima(result);

                                }
                            });


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            authorization(SHARE_MEDIA.WEIXIN);
//                            showNormalDialog();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //绑定
    private void bangding(String AlipayAccount) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "绑定" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/bindAlipayAccount")
                .addHeader("token", token)
                .addParams("AlipayAccount", AlipayAccount)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "绑定" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            tixian("alipay", "", "", "");
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            showNormalDialog();

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //绑定微信
    private void bangdingwx(final String weichatId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "绑定微信" + weichatId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/bindWechatAccount")
                .addHeader("token", token)
                .addParams("weichatId", weichatId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "绑定微信" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            jiancewx();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialog();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }




    //推广员提现

    private void tixianPromoterWithdrawal(final String putType, String bankCardNum) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "提现" + token + "putMoney" + editText.getText().toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PromoterWithdrawal")
                .addHeader("token", token)
                .addParams("putMoney", editText.getText().toString())
                .addParams("putType", putType)
                .addParams("bankCardNum", bankCardNum)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提现" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Boolean b = result.indexOf("4001") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            TXEntity entity = gson.fromJson(result, TXEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            if (putType.equals("bankCard")) {
                                intent.setClass(PromoterTXianActivity.this, TXCGActivity.class);
                                bundle.putString("uuid", entity.getData().getUuid());
                                bundle.putString("tab", "3");
                                bundle.putString("zId", "2");
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            } else if (putType.equals("wechat")) {
                                intent.setClass(PromoterTXianActivity.this, TXCGActivity.class);
                                bundle.putString("uuid", entity.getData().getUuid());
                                bundle.putString("tab", "1");
                                bundle.putString("zId", "2");
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            } else if (putType.equals("alipay")) {
                                intent.setClass(PromoterTXianActivity.this, TXCGActivity.class);
                                bundle.putString("uuid", entity.getData().getUuid());
                                bundle.putString("tab", "2");
                                bundle.putString("zId", "2");
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUitl.longs("提现失败");
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                        if (b) {
                            Intent intent = new Intent();
                            intent.setClass(PromoterTXianActivity.this, DengluActivity.class);
                            startActivity(intent);
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
        final EditText et = new EditText(this);

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(PromoterTXianActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("请输入绑定支付宝账号");
        normalDialog.setView(et);
        normalDialog.setPositiveButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        normalDialog.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        ToastUitl.longs(et.getText().toString());
                        bangding(et.getText().toString());
                    }
                });
        // 显示
        normalDialog.show();
    }

    //授权
    private void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d("1608", "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d("1608", "onComplete " + "授权完成");

                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");

                Toast.makeText(getApplicationContext(), "name=" + name + ",gender=" + gender, Toast.LENGTH_SHORT).show();
                bangdingwx(openid);
                //拿到信息去请求登录接口。。。
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.d("1608", "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.d("1608", "onCancel " + "授权取消");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onInputFinish(String result) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initDownload();
    }
}
