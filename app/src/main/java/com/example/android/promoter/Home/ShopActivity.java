package com.example.android.promoter.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.ShopListAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JSJBListEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.LunboEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Entity.ShopEntity;
import com.example.android.promoter.Entity.ShopItemEntity;
import com.example.android.promoter.Entity.ShopLunboEntity;
import com.example.android.promoter.My.MyJinbiActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.GlideImageLoader;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyGridView;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *兑换商城
 */
public class ShopActivity extends BaseActivity implements View.OnClickListener{
    private Banner banner;
    private ShopListAdapter adapter;
    private MyGridView gridView;
    private ImageView fanhui;
    private TextView duihuanjilu,biaoti,jinbi;
    private String token,category = "全部";
    private SPUtils spUtils;
    private   List<ShopEntity.DataBean.LstBean> data;
    private int page = 1;
    private LinearLayout quanbu,lanqiu,yumaoqiu,wangqiu,pingpangqiu,zuqiu,taiqiu,gaoerfu,paiqiu,qita,layout;
    private GridView listViewJC;
    private PullToRefreshScrollView scrollView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initUIAndListener() {
        banner = findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
//        List<String> lists = new ArrayList<>();
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        banner.setImages(lists);
//        banner.start();

        gridView = findViewById(R.id.shop_grid);
//        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList<>();
        adapter = new ShopListAdapter(this,data);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        duihuanjilu = findViewById(R.id.youshangjiao);
        duihuanjilu.setOnClickListener(this);
        duihuanjilu.setVisibility(View.VISIBLE);
        biaoti = findViewById(R.id.biaoti);
        quanbu = findViewById(R.id.shop_quanbu);
        quanbu.setOnClickListener(this);
        lanqiu = findViewById(R.id.shop_lanqiu);
        lanqiu.setOnClickListener(this);
        yumaoqiu = findViewById(R.id.shop_yumaoqiu);
        yumaoqiu.setOnClickListener(this);
        wangqiu = findViewById(R.id.shop_wangqiu);
        wangqiu.setOnClickListener(this);
        pingpangqiu = findViewById(R.id.shop_pingpangqiu);
        pingpangqiu.setOnClickListener(this);
        zuqiu = findViewById(R.id.shop_zuqiu);
        zuqiu.setOnClickListener(this);
        taiqiu = findViewById(R.id.shop_taiqiu);
        taiqiu.setOnClickListener(this);
        gaoerfu = findViewById(R.id.shop_gaoerfu);
        gaoerfu.setOnClickListener(this);
        paiqiu = findViewById(R.id.shop_paiqiu);
        paiqiu.setOnClickListener(this);
        qita = findViewById(R.id.shop_qita);
        qita.setOnClickListener(this);
        jinbi = findViewById(R.id.shop_jinbi);

        layout = findViewById(R.id.shop_layout);
        scrollView = findViewById(R.id.shop_scroll);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);


    }

    @Override
    protected void initData() {

        biaoti.setText("金币商城");
        spUtils = new SPUtils();
        token = (String) spUtils.get(ShopActivity.this, "logintoken", " ");
        duihuanjilu.setText("兑换记录");
        initDownload(page);

        shuaxin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.youshangjiao:
                Intent intent = new Intent();
                intent.setClass(this,HomeShopJiluActivity.class);
                startActivity(intent);

                break;
            case R.id.shop_quanbu:
                category = "全部";
                initDownload(1);
                break;
            case R.id.shop_lanqiu:
                category = "篮球";
                initDownload(1);
                break;
            case R.id.shop_yumaoqiu:
                category = "羽毛球";
                initDownload(1);
                break;
            case R.id.shop_wangqiu:
                category = "网球";
                initDownload(1);
                break;
            case R.id.shop_pingpangqiu:
                category = "乒乓球";
                initDownload(1);
                break;
            case R.id.shop_zuqiu:
                category = "足球";
                initDownload(1);
                break;
            case R.id.shop_taiqiu:
                category = "台球";
                initDownload(1);
                break;
            case R.id.shop_gaoerfu:
                category = "高尔夫";
                initDownload(1);
                break;

            case R.id.shop_paiqiu:
                category = "排球";
                initDownload(1);
                break;
            case R.id.shop_qita:
                category = "其他";
                initDownload(1);
                break;
        }
    }
    private void shuaxin() {

        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
           @Override
           public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
               page = 1;
               data.clear();
               LogU.Ld("1608", "下拉" + page+"");
               initDownload(page);
           }

           @Override
           public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
               page++;
               LogU.Ld("1608", "上啦" + page+"");
               initDownload(page);
           }
       });

    }
    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getGoodsLst")
                .addHeader("token",token)
                .addParams("category",category)
                .addParams("page",page+"")
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

                            if (page == 1){
                                data.clear();
                                data.addAll(list);
                                gridView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                scrollView.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                scrollView.onRefreshComplete();

                            }

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值

                                    intent.setClass(ShopActivity.this,ShopListActivity.class);
                                    bundle.putString("uid",data.get(position).getUUID());
                                    bundle.putString("name",data.get(position).getName());
                                    bundle.putInt("jiage",data.get(position).getCost());
                                    bundle.putString("scjiage",data.get(position).getPrice()+"");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ShopActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(ShopActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                            data.clear();
                            adapter.notifyDataSetChanged();
                            scrollView.onRefreshComplete();
                        }
                    }
                });

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
                            jinbi.setText("当前对手币" +  df.format(entity.getData().getCoins()) + "枚快去兑换商品吧");
                            guanggao();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ShopActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(ShopActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }


    private void guanggao() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "轮播" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getGoodsBanner")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "轮播" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ShopLunboEntity entity = gson.fromJson(result, ShopLunboEntity.class);
                            final List<ShopLunboEntity.DataBean> list;
                            list = entity.getData();

                            List<String> lists = new ArrayList<>();

                            for (int i =0; i<list.size();i++){
                                lists.add(getResources().getString(R.string.imgurl)+list.get(i).getPicurl());
                                LogU.Ld("1608","商城轮播"+list.get(i).getPicurl());
                            }
                            banner.setImages(lists);
                            banner.start();


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(ShopActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(ShopActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
