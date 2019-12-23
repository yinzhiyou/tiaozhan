package com.example.android.promoter.My;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.TYJBShopAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Entity.ShopEntity;
import com.example.android.promoter.Home.RenWuActivity;
import com.example.android.promoter.Home.ShopActivity;
import com.example.android.promoter.Home.ShopListActivity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 对手币
 */
public class MyDSBActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView biaoti,gengduo,mingxi,jinbi;
    private ImageView fanhui,qrw;
    private TYJBShopAdapter adapter;
    private GridView gridView;
    private String token, touxiang, nickname, tab, jinbiString;
    private SPUtils spUtils;
    private   List<ShopEntity.DataBean.LstBean> data;
    private LinearLayout duishou_ranking,duishou_win_gold,duishou_conversion,duishou_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),100);

        setContentView(R.layout.activity_my_tyjb);
        LinearLayout viewById = findViewById(R.id.tab_top);
        biaoti = viewById.findViewById(R.id.biaoti);
        biaoti.setText("对手币");
        fanhui = viewById.findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        qrw = findViewById(R.id.tyjb_zrw);
        qrw.setOnClickListener(this);
        gengduo = findViewById(R.id.tyjb_gengduo);
        gengduo.setOnClickListener(this);
        mingxi = findViewById(R.id.tyjb_mx);
        mingxi.setOnClickListener(this);
        jinbi = findViewById(R.id.my_tyjb_jinbi);

        gridView = findViewById(R.id.shop_grid);

        duishou_ranking=findViewById(R.id.duishou_ranking);
        duishou_ranking.setOnClickListener(this);
        duishou_win_gold=findViewById(R.id.duishou_win_gold);
        duishou_win_gold.setOnClickListener(this);
        duishou_conversion= findViewById(R.id.duishou_conversion);
        duishou_conversion.setOnClickListener(this);
        duishou_detail=findViewById(R.id.duishou_detail);
        duishou_detail.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(MyDSBActivity.this, "logintoken", "无");
        data = new ArrayList<>();
        adapter = new TYJBShopAdapter(this,data);
        initDownload();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//传值
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.tyjb_gengduo:
                intent.setClass(this,ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.tyjb_zrw:
                intent.setClass(this,RenWuActivity.class);
                startActivity(intent);

                break;
            case R.id.tyjb_mx:
                intent.setClass(this,JBMXActivity.class);
                bundle.putString("tab","1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
                //对手币排行榜
            case R.id.duishou_ranking:
                intent.setClass(this,MyOpponentPaiHangActivity.class);
                startActivity(intent);

                break;
                //赢对手币
            case R.id.duishou_win_gold:
                intent.setClass(this,RenWuActivity.class);
                startActivity(intent);

                break;
                //兑换商城
            case R.id.duishou_conversion:
                intent.setClass(this,ShopActivity.class);
                startActivity(intent);

                break;
                //对手币获得明细
            case R.id.duishou_detail:
                intent.setClass(this,JBMXActivity.class);
                bundle.putString("tab","1");
                intent.putExtras(bundle);
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
                            DecimalFormat df = new DecimalFormat("#.00");

                            jinbi.setText(  df.format(entity.getData().getCoins())+"");
                            shop();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyDSBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(MyDSBActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }


    private void shop() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getGoodsLst")
                .addHeader("token",token)
                .addParams("category","全部")
                .addParams("page","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "商城列表" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ShopEntity entity = gson.fromJson(result, ShopEntity.class);
                            List<ShopEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
                                data.addAll(list);
                                gridView.setAdapter(adapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值

                                    intent.setClass(MyDSBActivity.this,ShopListActivity.class);
                                    bundle.putString("uid",data.get(position).getUUID());
                                    bundle.putString("name",data.get(position).getName());
                                    bundle.putInt("jiage",data.get(position).getCost());
                                    bundle.putString("scjiage",data.get(position).getPrice()+"");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyDSBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(MyDSBActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }

//                            adapter.notifyDataSetChanged();
//                            gridView.onRefreshComplete();
                        }
                    }
                });

    }
}
