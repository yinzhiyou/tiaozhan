package com.example.android.tiaozhan.My;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.MyjingcaiListAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyjingcaiEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Wonderful.JingcaiItemActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的精彩
 */
public class MyJingcaiActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private PullToRefreshListView listView;
    private MyjingcaiListAdapter adapter;
    private SPUtils spUtils;
    private String token;
    private List<MyjingcaiEntity.DataBean.ResLstBean> data;
    private int page = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_jingcai);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_jingcai;
    }

    @Override
    protected void initUIAndListener() {
        listView = findViewById(R.id.my_jingcai_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList<>();
        adapter = new MyjingcaiListAdapter(this,data);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
    }

    @Override
    protected void initData() {

        biaoti.setText("精彩瞬间");
        jingcai(page);
        shuaxin();
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
                        jingcai(page);
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
                        jingcai(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }
    //精彩瞬间
    private void jingcai(final int  page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "个人精彩" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRelation")
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
                        LogU.Ld("1608", "个人精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            MyjingcaiEntity entity = gson.fromJson(result, MyjingcaiEntity.class);
                            final List<MyjingcaiEntity.DataBean.ResLstBean> list;
                            list = entity.getData().getResLst();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);


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
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(MyJingcaiActivity.this, JingcaiItemActivity.class);
                                    bundle.putString("neirong",data.get(position).getComment());
                                    bundle.putString("pinglunshu",data.get(position).getCommentCount()+"");
                                    bundle.putString("dianzanshu",data.get(position).getPraiseCount()+"");
                                    bundle.putString("yue",data.get(position).getMonth());
                                    bundle.putString("ri",data.get(position).getDay());
                                    bundle.putStringArrayList("tupian", (ArrayList<String>) data.get(position).getFullPath());
                                    bundle.putInt("shuliang",data.get(position).getContentCount());
                                    bundle.putInt("xiabiao",position);
                                    bundle.putString("uuid",data.get(position).getUUID());
                                    intent.putExtras(bundle);

                                    startActivity(intent);
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyJingcaiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
