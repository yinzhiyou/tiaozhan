package com.example.android.tiaozhan.Home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.ShopListItemAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyTYJBEntity;
import com.example.android.tiaozhan.Entity.ShopItemEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.GlideImageLoader;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *商品详情
 */
public class ShopListActivity extends BaseActivity implements View.OnClickListener{

    private ListView listView;
    private ShopListItemAdapter adapter;
    private TextView biaoti,name,jiage,scjiage,shuliang,jinbi;
    private ImageView fanhui,jian,jia;
    private Banner banner;
    private String token,uid,nameString,scjiageString;
    private SPUtils spUtils;
    private RelativeLayout duihuan,lianjie;
    private int shu = 1,jiageString;
    private ListView listViewJC;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_list);
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_shop_list;
    }

    @Override
    protected void initUIAndListener() {
        View header = View.inflate(this, R.layout.shop_list_head, null);
        listView = findViewById(R.id.shop_list_list);
//        listView.setMode(PullToRefreshBase.Mode.BOTH);


        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        banner = header.findViewById(R.id.shop_list_button);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(8000);
        name = header.findViewById(R.id.shop_xq_name);
        jiage = header.findViewById(R.id.shop_xq_jiage);
        scjiage = header.findViewById(R.id.shop_xq_shichangjiage);
        lianjie = header.findViewById(R.id.shop_xq_lianjie);
        jinbi = header.findViewById(R.id.shop_xq_jinbi);


        duihuan = findViewById(R.id.shop_item_duihuan);
        duihuan.setOnClickListener(this);
        jian = findViewById(R.id.shop_item_jian);
        jian.setOnClickListener(this);
        jia = findViewById(R.id.shop_item_jia);
        jia.setOnClickListener(this);
        shuliang = findViewById(R.id.shop_item_shuliang);




        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        spUtils = new SPUtils();
        token = (String) spUtils.get(ShopListActivity.this, "logintoken", "无");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
        nameString =  bundle.getString("name");
        jiageString =  bundle.getInt("jiage");
        scjiageString =  bundle.getString("scjiage");

//        listViewJC = listView.getRefreshableView();
        listView.addHeaderView(header);



    }

    @Override
    protected void initData() {
        listView.setAdapter(adapter);
//        setListViewHeightBasedOnChildren(listView);
        biaoti.setText("商品详情");
//        List<String> lists = new ArrayList<>();
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        lists.add("http://app.gelabang.com/uploads/20180523/c017e7d52c43ab03588de8ce5319a9cf.jpg");
//        banner.setImages(lists);
//        banner.start();

        name.setText(nameString);
        jiage.setText(jiageString+"");
        scjiage.setText(scjiageString);
        scjiage.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        initDownload();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.shop_item_jian:
                if (shu <=1){

                }else{
                    shu--;
                }

                shuliang.setText(shu+"");
                break;
            case R.id.shop_item_jia:
                shu++;
                shuliang.setText(shu+"");
                break;
            case R.id.shop_item_duihuan:
            Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
            intent.setClass(this,TianjiaDizhiActivity.class);

                bundle.putString("uid",uid);
                bundle.putInt("jiage",jiageString);
                bundle.putInt("shu",shu);
                intent.putExtras(bundle);
            startActivity(intent);

                break;
            case R.id.shop_xq_lianjie:
                ToastUitl.longs("功能暂未开通");
                break;
        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getGoodsInfo")
                .addHeader("token",token)
                .addParams("uid",uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "商城详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ShopItemEntity entity = gson.fromJson(result, ShopItemEntity.class);
                            adapter = new ShopListItemAdapter(ShopListActivity.this,entity.getData().getImgurl());
                            listView.setAdapter(adapter);

                            List<String> lists = new ArrayList<>();

                            for (int i =0; i<entity.getData().getImgurl().size();i++){
                                lists.add(getResources().getString(R.string.imgurl)+entity.getData().getImgurl().get(i));
                                LogU.Ld("1608","商城轮播"+entity.getData().getImgurl().get(i));
                            }
                            banner.setImages(lists);
                            banner.start();

//                            lists.add("http://seopic.699pic.com/photo/50076/8023.jpg_wh1200.jpg");

//                            List<ShopItemEntity.DataBean.LstBean> list;
//                            list = entity.getData().getLst();
//                            data.addAll(list);
//                            gridView.setAdapter(adapter);
//                            gridView.setAdapter(adapter);
//                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Intent intent = new Intent();
//                                    intent.setClass(ShopActivity.this,ShopListActivity.class);
//                                    startActivity(intent);
//                                }
//                            });
                            tongyong();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ShopListActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(ShopListActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
    private void tongyong() {
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
                            jinbi.setText("当前对手币" +  df.format(entity.getData().getCoins()) + "枚");

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(ShopListActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(ShopListActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
    public void setListViewHeightBasedOnChildren(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0); // 计算子项View 的宽高

            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }
}
