package com.example.android.promoter.Home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.CGXXListAdapter;
import com.example.android.promoter.Entity.CGXXEntity;
import com.example.android.promoter.Entity.ChangguanListEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.StarBar;
import com.example.android.promoter.Toos.banner.BannerAdapter;
import com.example.android.promoter.Toos.banner.BannerBaseAdapter;
import com.example.android.promoter.Toos.banner.BannerBean;
import com.example.android.promoter.Toos.banner.BannerView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *场馆信息
 */
public class CGXXActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, youshangjiao, name, dizhi, zoongxing, shezhi, fuwu, jiage, gengduo,ydxm;
    private ImageView fanhui, dianhua;
    private CGXXListAdapter adapter;
    private MyListView listView;
    private RelativeLayout yuding;
    private XBanner bannerView;

    private StarBar starBar;

    private Bundle bundle;
    private String uid, cgname, hezuo, Telephone;
    private SPUtileFQTZ spUtils;
    private List<CGXXEntity.DataBean.CommentsBean> data;
    private LinearLayout pingjia;
    private CGXXEntity entity;



    @Override
    public int initContentView() {
        return R.layout.activity_cgxx;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        data = new ArrayList<>();
        adapter = new CGXXListAdapter(this, data);
        listView = findViewById(R.id.cgxx_list);
        dianhua = findViewById(R.id.cgxx_list_dianhua);
        dianhua.setOnClickListener(this);
        yuding = findViewById(R.id.cgxx_yuding);
        yuding.setOnClickListener(this);
        bannerView = findViewById(R.id.cgxx_banner);
        name = findViewById(R.id.cgxx_name);
        dizhi = findViewById(R.id.cgxx_dizhi);
        zoongxing = findViewById(R.id.cgxx_xing);
        shezhi = findViewById(R.id.cgxx_sheshi);
        fuwu = findViewById(R.id.cgxx_fuwu);
        jiage = findViewById(R.id.cgxx_jiage);
        starBar = findViewById(R.id.cgxx_star);
        gengduo = findViewById(R.id.cgxx_gengduo);
        pingjia = findViewById(R.id.cgxx_pingjia);
        pingjia.setOnClickListener(this);
        ydxm = findViewById(R.id.cgxx_ydxm);

        bundle = new Bundle();
        spUtils = new SPUtileFQTZ();

    }

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        uid = bundle.getString("uid");
        hezuo = bundle.getString("hezuo");
        spUtils.put(this, "hezuo", hezuo + "");
        biaoti.setText("场馆信息");
        youshangjiao.setText("更正");
        youshangjiao.setVisibility(View.VISIBLE);

        init();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.youshangjiao:
                intent.setClass(this, CGGZActivity.class);
                bundle.putString("tag", "3");
                bundle.putString("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.cgxx_list_dianhua:


                if (Telephone.contains(",")) {

                    showListDialog(Telephone.split(","));
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + Telephone);
                    intent1.setData(data);
                    startActivity(intent1);
                }
                break;
            case R.id.cgxx_yuding://预定跳转

                if (hezuo.equals("1")) {
                    intent.setClass(this, StartTimeActivity.class);
                    startActivity(intent);
                } else {


                    showNormalDialog(Telephone);

                }


                break;
            case R.id.cgxx_pingjia:
                intent.setClass(this,CGPJActivity.class);
                startActivity(intent);


                break;
        }
    }

    //场馆详细信息
    private void init() {

        LogU.Ld("1608", "场馆详细信息" + uid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSiteInfo")
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "场馆详细信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, CGXXEntity.class);
                            final String[] filesURL = entity.getData().getFilesURL();
                            LogU.Le("1608","轮播"+entity.getData().getFilesURL());
                            final List<String> bannerlist=new ArrayList<>();
                            if (filesURL!=null){
                                for(int i=0;i<filesURL.length;i++){
                                    bannerlist.add(getResources().getString(R.string.imgurl) + filesURL[i]);
                                    LogU.Le("1608","轮播"+filesURL[i]);
                                }
                            }
                            bannerView.setData(bannerlist,null);
                            bannerView.loadImage(new XBanner.XBannerAdapter() {
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    Glide.with(CGXXActivity.this).load(bannerlist.get(position)).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);
                                }
                            });
                            name.setText(entity.getData().getName());
                            dizhi.setText(entity.getData().getAddress());
                            spUtils.put(CGXXActivity.this, "cgname", entity.getData().getName());
                            spUtils.put(CGXXActivity.this, "cgid", uid);
                            zoongxing.setText(entity.getData().getScores() + "分");
                            shezhi.setText("设施:" + entity.getData().getEquscore() + "分");
                            fuwu.setText("环境:" + entity.getData().getEnvscore() + "分");
                            jiage.setText("性价比:" + entity.getData().getXjbScore() + "分");
                            starBar.setStarMark((float) Double.parseDouble(entity.getData().getScores()),1);
                            Telephone = entity.getData().getTelephone();
                            gengduo.setText("更多评价    " + entity.getData().getCommentsCount());
                            ydxm.setText(entity.getData().getSupportSportName());
                            List<CGXXEntity.DataBean.CommentsBean> list;
                            list = entity.getData().getComments();
                            data.addAll(list);
                            listView.setAdapter(adapter);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(CGXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    private void showNormalDialog(final String telephone) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("该场馆为未合作场馆，请与场馆方联系，确定可选时间和场地费，谢谢");
        normalDialog.setPositiveButton("现在联系",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                        if (Telephone.contains(",")) {

                            showListDialog(Telephone.split(","));
                        } else {
                            Intent intent1 = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + telephone);
                            intent1.setData(data);
                            startActivity(intent1);
                        }
                    }
                });
        normalDialog.setNegativeButton("已联系",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();

                        intent.setClass(CGXXActivity.this, StartTimeActivity.class);
                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }

    private void showListDialog(String[] length) {
        final String[] items = length;
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(CGXXActivity.this);
        listDialog.setTitle("请选择你要拨打的电话");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + items[which]);
                intent1.setData(data);
                startActivity(intent1);
            }
        });
        listDialog.show();
    }
}
