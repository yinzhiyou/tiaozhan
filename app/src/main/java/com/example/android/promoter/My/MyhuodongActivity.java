package com.example.android.promoter.My;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.android.promoter.Adapter.MyHdAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.HDjianceEntity;
import com.example.android.promoter.Entity.HQQREntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyHDEntity;
import com.example.android.promoter.Entity.TishiyuEntity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.reserve.ReserveCGDetailsActivity;
import com.google.gson.Gson;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的活动
 */
public class MyhuodongActivity extends BaseActivity implements View.OnClickListener {
    private ImageView fanhui,ppzImage,dcfImage,hdzImage,dqrImage,dpjImage,ywcImage,tousuImage,quanbuImage;
    private PullToRefreshListView listView;
    private MyHdAdapter adapter;
    private TextView wofabu, wobaoming;
    private String token, type = "publish",type2 = "publish", statusType = "all";
    private SPUtils spUtils;
    private List<MyHDEntity.DataBean.PublicLstBean> data;
    private LinearLayout pipeizhong, daichufa, huodongzhong, jieguo, daipingjia, yiwancheng, quanbu,tousu;
    private int page = 1;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_myhuodong);
//    }
    private double mLatitude;
    private double mLongitude;
    private MylocationListener mlistener;
    private LocationClient mlocationClient;
    private View fb,bm;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6;
    private RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayout6;
    @Override
    public int initContentView() {


        return R.layout.activity_myhuodong;
    }

    @Override
    protected void initUIAndListener() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.tab_backgroud),0);

        fanhui = findViewById(R.id.my_hd_fanhui);
        fanhui.setOnClickListener(this);
        listView = findViewById(R.id.my_hd_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        wofabu = findViewById(R.id.my_hd_wofabu);
        wofabu.setOnClickListener(this);
        wobaoming = findViewById(R.id.my_hd_wobaoming);
        wobaoming.setOnClickListener(this);
        pipeizhong = findViewById(R.id.my_hd_pipeizhong);
        pipeizhong.setOnClickListener(this);
        daichufa = findViewById(R.id.my_hd_daichufa);
        daichufa.setOnClickListener(this);
        huodongzhong = findViewById(R.id.my_hd_huodongzhong);
        huodongzhong.setOnClickListener(this);
        jieguo = findViewById(R.id.my_hd_jieguo);
        jieguo.setOnClickListener(this);
        daipingjia = findViewById(R.id.my_hd_daipingjia);
        daipingjia.setOnClickListener(this);
        yiwancheng = findViewById(R.id.my_hd_yiwancheng);
        yiwancheng.setOnClickListener(this);
        quanbu = findViewById(R.id.my_hd_quanbu);
        quanbu.setOnClickListener(this);
        quanbu = findViewById(R.id.my_hd_tousu);
        quanbu.setOnClickListener(this);

        ppzImage = findViewById(R.id.my_hd_ppz_image);
        dcfImage = findViewById(R.id.my_hd_dcf_image);
        hdzImage = findViewById(R.id.my_hd_hdz_image);
        dqrImage = findViewById(R.id.my_hd_dqr_image);
        dpjImage = findViewById(R.id.my_hd_dpj_image);
        ywcImage = findViewById(R.id.my_hd_ywc_image);
        tousuImage = findViewById(R.id.my_hd_tousu_image);
        quanbuImage = findViewById(R.id.my_hd_quanbu_image);

        textView1 = findViewById(R.id.my_hd_text1);
        textView2 = findViewById(R.id.my_hd_text2);
        textView3 = findViewById(R.id.my_hd_text3);
        textView4 = findViewById(R.id.my_hd_text4);
        textView5 = findViewById(R.id.my_hd_text5);
        textView6 = findViewById(R.id.my_hd_text6);
        relativeLayout1 = findViewById(R.id.my_hd_relat1);
        relativeLayout2 = findViewById(R.id.my_hd_relat2);
        relativeLayout3 = findViewById(R.id.my_hd_relat3);
        relativeLayout4 = findViewById(R.id.my_hd_relat4);
        relativeLayout5 = findViewById(R.id.my_hd_relat5);
        relativeLayout6 = findViewById(R.id.my_hd_relat6);

        fb=findViewById(R.id.my_fabu);
        bm=findViewById(R.id.my_baoming);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        data = new ArrayList<>();
        adapter = new MyHdAdapter(this, data);
        listView.setAdapter(adapter);
        mlocationClient = new LocationClient(this);
    }

    @Override
    protected void initData() {


        initDownload("publish", "all", 1);
        shuaxin();
        adapter.setOnItemDeleteClickListener(new MyHdAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i,int tag) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值

                if (data.get(i).getPublicStatus() == 1) {
                    if (data.get(i).getIsPublisher() == 1) {
                        if (data.get(i).getReserve().equals("1")){
                            showNormalDialog(i, "您确定取消预订场馆吗？", "1");
                        }else {
                            showNormalDialog(i, "您确定取消发布吗？", "1");
                        }
                    } else {
                        showNormalDialog(i, "您确定取消报名吗？", "1");
                    }

                } else if (data.get(i).getPublicStatus() == 2) {
//                    showNormalDialog(i, "您确定提前退出吗？", "2");
                    if (tag == 1){
//                        showNormalDialog(i, "您确定提前退出吗？", "2");

                        if (data.get(i).getComplaint() == 4||data.get(i).getComplaint() == 3||data.get(i).getComplaint() == 1){
                            tishiyu(data.get(i).getUuid());
                        }else{
                            ToastUitl.longs("投诉中，该功能暂时冻结");
                        }

                    }else{
                        ToastUitl.longs("签到");
                        mlocationClient.start();
                        dingwei(i);

                    }
//                    qiandao();


                } else if (data.get(i).getPublicStatus() == 3) {
                    if (tag == 1){

                        if (data.get(i).getComplaint() == 4||data.get(i).getComplaint() == 3||data.get(i).getComplaint() == 1){
                            showNormalDialog(i, "您确定提前退赛吗？", "3");
                        }else{
                            ToastUitl.longs("投诉中，该功能暂时冻结");
                        }
                }else{
                    ToastUitl.longs("签到");
                    mlocationClient.start();
                    dingwei(i);

                }

                } else if (data.get(i).getPublicStatus() == 4) {
                    if (data.get(i).getSportMode() == 2) {
                        //填写结果


                        if (data.get(i).getComplaint() == 4||data.get(i).getComplaint() == 3||data.get(i).getComplaint() == 1){
                            if (data.get(i).getIsConfirmResult()==1){
                            }else{
                                anniufanhui(data.get(i).getUuid());
                            }
                        }else{
                            ToastUitl.longs("投诉中，该功能暂时冻结");
                        }
                    } else {
                        //确认结束
                    }
                }else if (data.get(i).getPublicStatus() == 8) {

                    if (data.get(i).getComplaint() == 4||data.get(i).getComplaint() == 3||data.get(i).getComplaint() == 1){
                        if (data.get(i).getIsConfirmOver()==1){
                        }else{
                            showNormalDialog(i, "确认比赛结束？", "8");
                        }
                    }else{
                        ToastUitl.longs("投诉中，该功能暂时冻结");
                    }

                }
                if (data.get(i).getPublicStatus() == 6) {//去评价

                    LogU.Ld("1608", data.get(i).getComment()+"到底是多少");
                    if (data.get(i).getComment() == 1) {//1次
                        intent.setClass(MyhuodongActivity.this, PingjiaActivity.class);
                        bundle.putString("uuid", data.get(i).getUuid());
                        bundle.putString("tag", data.get(i).getComment()+"");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (data.get(i).getComment() == 2) {//2次
                        intent.setClass(MyhuodongActivity.this, PingjiaTwoActivity.class);
                        bundle.putString("uuid", data.get(i).getUuid());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }  else if (data.get(i).getComment() == 0) {//1=2次
                        intent.setClass(MyhuodongActivity.this, PingjiaActivity.class);
                        bundle.putString("uuid", data.get(i).getUuid());
                        bundle.putString("tag", data.get(i).getComment()+"");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                    }

//                    if (data.get(i).getIsComment()==1){
//
//                    }else  if (data.get(i).getIsQuit()==2){
//
//                    }else  if (data.get(i).getIsQuitInGame()==2){
//
//                    }else  if (data.get(i).getIsSignIn()==0){
//
//                    }else{
//                        intent.setClass(MyhuodongActivity.this, PingjiaActivity.class);
//                        bundle.putString("uuid", data.get(i).getUuid());
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                    }



                }

            }
        });

//        dingwei(0);
    }
    //提示语
    private void tishiyu(final String uuid) {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTips")
                .addHeader("token", token)
                .addParams("uuid",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提示语" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            TishiyuEntity entity = gson.fromJson(result, TishiyuEntity.class);
                            showNormalDialogTQ(entity.getData().getTips(),entity.getData().getSite(),uuid);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialog();

                        }
                    }
                });

    }

    //提前退出
    private void tiqiantuichu( String tab, String uuid) {
        LogU.Ld("1608", "提前退出");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getmessage")
                .addHeader("token", token)
                .addParams("uuid",uuid)
                .addParams("type",tab)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提前退出" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            initDownload(type, statusType, page);
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialogZong("提前退出，您会被记违约次数一次，专用金币按输扣除并均分至其他未退出方！您应得的打赏费退还至发布者，您预付的场地费支付给场馆方。",zhuangtaiString);

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            showNormalDialog();

                        }
                    }
                });

    }

    private void showNormalDialogTQ(String biaoti, final String biaoti2, final String uuid) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyhuodongActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
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
                        if (biaoti2.length()<1){
                            tiqiantuichu("3",uuid);
                        }else{
                            showNormalDialogTQ2(biaoti2,uuid);
                        }
                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showNormalDialogTQ2(String biaoti, final String uuid) {

        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyhuodongActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
        normalDialog.setPositiveButton("预留",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        tiqiantuichu("1",uuid);
                    }
                });
        normalDialog.setNegativeButton("未预留",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                        tiqiantuichu("2",uuid);

                    }
                });
        // 显示

        normalDialog.show();
    }

    private void anniufanhui(final String publishcId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirmButton")
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
                        LogU.Ld("1608", "获取确认按钮" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HQQREntity entity = gson.fromJson(result, HQQREntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            if (entity.getData().getType() == 0) {
                                intent.setClass(MyhuodongActivity.this, HDJGActivity.class);
                                bundle.putString("uuid", publishcId);
//                        bundle.putString("duiwu", data.get(i).getTeam() + "");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }else   if (entity.getData().getType() == 1){
                                querenjieshu(publishcId);
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                                                        if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_hd_fanhui:
                finish();
                break;
            case R.id.my_hd_wofabu://我发布的
                wofabu.setTextColor(getResources().getColor(R.color.colorWhite));
                wobaoming.setTextColor(getResources().getColor(R.color.my_hd_tab));
                fb.setVisibility(View.VISIBLE);
                bm.setVisibility(View.INVISIBLE);
                type = "publish";
                type2 = "publish";
                initDownload(type, statusType, 1);
                break;
            case R.id.my_hd_wobaoming://我报名的
                wofabu.setTextColor(getResources().getColor(R.color.my_hd_tab));
                wobaoming.setTextColor(getResources().getColor(R.color.colorWhite));
                bm.setVisibility(View.VISIBLE);
                fb.setVisibility(View.INVISIBLE);
                type = "join";
                type2 = "join";
                initDownload(type, statusType, 1);
                break;
            case R.id.my_hd_pipeizhong://匹配中
                statusType = "matching";
                initDownload(type, "matching", 1);
                ppzImage.setBackgroundResource(R.mipmap.pipeizhong2);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_daichufa://待出发
                statusType = "watinggo";
                initDownload(type, "watinggo", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.daichufa2);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_huodongzhong://活动中
                statusType = "activitying";
                initDownload(type, "activitying", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.huodongzhong2);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_jieguo://结果
                statusType = "confirm";
                initDownload(type, "confirm", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguohong);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_daipingjia://待评价
                statusType = "comment";
                initDownload(type, "comment", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.daipingjia2);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_yiwancheng://已完成
                statusType = "finishd";
                initDownload(type, "finishd", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.yiwancheng2);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
            case R.id.my_hd_quanbu://全部
                statusType = "all";
                initDownload(type, "all", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongkong);
                quanbuImage.setBackgroundResource(R.mipmap.quanbushi);

                break;
            case R.id.my_hd_tousu://全部
                statusType = "Complaints";
                initDownload(type, "Complaints", 1);
                ppzImage.setBackgroundResource(R.mipmap.mypipeizhong);
                dcfImage.setBackgroundResource(R.mipmap.mydaichufa);
                hdzImage.setBackgroundResource(R.mipmap.myhuodongzhong);
                dqrImage.setBackgroundResource(R.mipmap.myjieguo);
                dpjImage.setBackgroundResource(R.mipmap.mypingjia);
                ywcImage.setBackgroundResource(R.mipmap.myhuodong);
                tousuImage.setBackgroundResource(R.mipmap.tousuzhongshi);
                quanbuImage.setBackgroundResource(R.mipmap.quanbukong);
                break;
        }
    }

    //获取数据
    private void initDownload(String type, String statusType, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "我的活动---" + token + "---type---" + type + "---statusType---" + statusType);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyActiveLst")
                .addHeader("token", token)
                .addParams("type", type)
                .addParams("statusType", statusType)
                .addParams("page", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "我的活动" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            MyHDEntity entity = gson.fromJson(result, MyHDEntity.class);
                            List<MyHDEntity.DataBean.PublicLstBean> list;
                            list = entity.getData().getPublicLst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);


                            if (page == 1) {
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(MyhuodongActivity.this, HomeHDXQActivity.class);
                                    bundle.putString("tame", data.get(position-1).getIsPublisher()+"");
                                    if (data.get(position-1).getPublicStatus() == 1) {
                                        bundle.putString("tab", "1");
                                        bundle.putString("fabuzhe", data.get(position-1).getIsPublisher()+"");
                                    }else if(data.get(position-1).getPublicStatus() == 2){
                                        bundle.putString("tab", "2");
                                    }else if(data.get(position-1).getPublicStatus() == 3){
                                        bundle.putString("tab", "3");
                                    }else if(data.get(position-1).getPublicStatus() == 4){
                                        bundle.putString("tab", "4");
                                    }else if(data.get(position-1).getPublicStatus() == 5){//已完成
                                        bundle.putString("tab", "5");
                                    }else if(data.get(position-1).getPublicStatus() == 6){
                                        bundle.putString("tab", "6");
                                    }else if(data.get(position-1).getPublicStatus() == 7){
                                        bundle.putString("tab", "7");
                                    }else if(data.get(position-1).getPublicStatus() == 8){
                                        bundle.putString("tab", "8");
                                    }

                                    bundle.putString("uuid", data.get(position - 1).getUuid());
                                    intent.putExtras(bundle);
                                    if (data.get(position-1).getReserve().equals("1")){
                                        intent.setClass(MyhuodongActivity.this, ReserveCGDetailsActivity.class);
                                    }
                                    startActivity(intent);

                                }
                            });


                            shuliang();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(MyhuodongActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    private void shuaxin() {


        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page + "");
                        initDownload(type, statusType, page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page + "");
                        initDownload(type, statusType, page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    //取消报名
    private void quxiaobaoming(String publishcId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "取消报名---" + token + "---publishcId---" + publishcId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userCancelActivity")
                .addHeader("token", token)
                .addParams("publishcId", publishcId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消报名" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MyhuodongActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                            initDownload(type, statusType, page);
                        } else {

                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //中途退赛
    private void zhongtutuisai(String publishcId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "中途退赛---" + token + "---publishcId---" + publishcId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userMidwaySignOut")
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
                        LogU.Ld("1608", "中途退赛" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MyhuodongActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                            initDownload(type, statusType, page);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //确认结束
    private void querenjieshu(final String publishcId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "确认结束---" + token + "---publishcId---" + publishcId);
//        OkHttpUtils
//                .post()
//                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/confirmActivityOver")
//                .addHeader("token", token)
//                .addParams("uuid", publishcId)
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
//                        LogU.Ld("1608", "确认结束" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
////                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            Toast.makeText(MyhuodongActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
//                            initDownload(type, statusType, page);
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
//                    }
//                });
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
                            Toast.makeText(MyhuodongActivity.this, "确认结束成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(MyhuodongActivity.this,PingjiaActivity.class);
                            bundle.putString("uuid",publishcId);
                            bundle.putString("tag","9");
                            intent.putExtras(bundle);

                            startActivity(intent);


                            initDownload(type, statusType, page);
                        } else {
                            initDownload(type, statusType, page);
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            initDownload(type, statusType, page);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    //活动数量
    private void shuliang() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "活动数量---" + token + "---publishcId---" );
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActiveCount")
                .addHeader("token", token)
               .addParams("type",type2)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "活动数量" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                             HDjianceEntity entity = gson.fromJson(result, HDjianceEntity.class);

                             if (entity.getData().getMatching()>0){
                                 textView1.setText(entity.getData().getMatching()+"");
                                 relativeLayout1.setVisibility(View.VISIBLE);
                             }else {
                                 relativeLayout1.setVisibility(View.INVISIBLE);

                             }

                                 if (entity.getData().getWaiting()>0){
                                     textView2.setText(entity.getData().getWaiting()+"");
                                     relativeLayout2.setVisibility(View.VISIBLE);

                             }   else {
                                     relativeLayout2.setVisibility(View.INVISIBLE);

                                 }


                                 if (entity.getData().getActiviting()>0){
                                 textView3.setText(entity.getData().getActiviting()+"");
                                 relativeLayout3.setVisibility(View.VISIBLE);
                             }  else {
                                     relativeLayout3.setVisibility(View.INVISIBLE);

                                 }


                                 if (entity.getData().getConfirming()>0){
                                 textView4.setText(entity.getData().getConfirming()+"");
                                 relativeLayout4.setVisibility(View.VISIBLE);
                             }  else {
                                     relativeLayout4.setVisibility(View.INVISIBLE);

                                 }


                                 if (entity.getData().getAssessing()>0){
                                 textView5.setText(entity.getData().getAssessing()+"");
                                 relativeLayout5.setVisibility(View.VISIBLE);
                             }else {
                                     relativeLayout5.setVisibility(View.INVISIBLE);

                                 }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyhuodongActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    private void showNormalDialog(final int i, String biaoti, final String tag) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MyhuodongActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(biaoti);
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
                        if (tag.equals("1")) {
                            quxiaobaoming(data.get(i).getUuid());
                        } else if (tag.equals("2")) {
                            quxiaobaoming(data.get(i).getUuid());
                        } else if (tag.equals("3")) {
                            zhongtutuisai(data.get(i).getUuid());
                        } else if (tag.equals("8")) {
                            querenjieshu(data.get(i).getUuid());
                        }

                    }
                });
        // 显示

        normalDialog.show();
    }

    //定位地址
    private void dingwei(int i) {
//        定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动

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
        initDownload(type, statusType, page);
    }
}
