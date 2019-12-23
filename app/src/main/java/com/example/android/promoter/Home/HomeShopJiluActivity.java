package com.example.android.promoter.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.ShopJiluListAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.DuihuanjiluEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.ShopEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 *商品兑换记录
 */
public class HomeShopJiluActivity extends BaseActivity implements View.OnClickListener{

    private PullToRefreshListView listView;
    private TextView  biaoti;
    private ImageView fanhui;
    private ShopJiluListAdapter adapter;
    private String token,uid,nameString,scjiageString;
    private SPUtils spUtils;
    private   List<DuihuanjiluEntity.DataBean.LstBean> data;
    private int page = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_shop_jilu);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_home_shop_jilu;


    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        listView = findViewById(R.id.shop_jilu_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        spUtils = new SPUtils();
        token = (String) spUtils.get(HomeShopJiluActivity.this, "logintoken", "无");

        data = new ArrayList<>();
        adapter = new ShopJiluListAdapter(this,data);
    }

    @Override
    protected void initData() {
        biaoti.setText("兑换记录");
        listView.setAdapter(adapter);
        initDownload(page);
        shuaxin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;

        }
    }

    private void shuaxin() {


        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
                {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }
    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userExchageLogg")
                .addHeader("token",token)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "兑换记录" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            DuihuanjiluEntity entity = gson.fromJson(result, DuihuanjiluEntity.class);
                            List<DuihuanjiluEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
                            if (page == 1){
                                data.clear();
                                data.addAll(list);
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }
//                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Intent intent = new Intent();
//                                    Bundle bundle = new Bundle();//传值
//
//                                    intent.setClass(ShopActivity.this,ShopListActivity.class);
//                                    bundle.putString("uid",data.get(position).getUUID());
//                                    bundle.putString("name",data.get(position).getName());
//                                    bundle.putInt("jiage",data.get(position).getCost());
//                                    bundle.putString("scjiage",data.get(position).getPrice()+"");
//                                    intent.putExtras(bundle);
//                                    startActivity(intent);
//                                }
//                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeShopJiluActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(HomeShopJiluActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

}
