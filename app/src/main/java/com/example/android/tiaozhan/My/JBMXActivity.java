package com.example.android.tiaozhan.My;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.JBMXJishuListAdapter;
import com.example.android.tiaozhan.Adapter.TYMingxiAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.TYJBMingxiEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.HorizontalListView;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
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
 * 对手币及技术分
 */
public class JBMXActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView biaoti1, biaoti2,biaoti;
    private ImageView biaotiImage1, biaotiImage2, fanhui;
    private PullToRefreshListView listView;
    private HorizontalListView xiangmuList;
    private TYMingxiAdapter adapter;
    private String token,touxiang,nickname,uuid,sportType ="",tab,sportID = "",goldType = "";

    private SPUtils spUtils;
    private   List<TYJBMingxiEntity.DataBean.GoldLstBean> data;
    private  List<YundongEntity.DataBean> data2;
    private JBMXJishuListAdapter adapter2;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbmx);
        StatusBarUtil.setColor(JBMXActivity.this,getResources().getColor(R.color.white),0);

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.colorWhite);
        }*/
        biaoti1 = findViewById(R.id.jbmx_biaoti1);
        biaoti1.setOnClickListener(this);
        biaoti2 = findViewById(R.id.jbmx_biaoti2);
        biaoti2.setOnClickListener(this);
        biaotiImage1 = findViewById(R.id.jbmx_tab_img1);
        biaotiImage2 = findViewById(R.id.jbmx_tab_img2);
        listView = findViewById(R.id.jbmx_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);

//        adapter = new JBMXListTYAdapter(this);
        xiangmuList = findViewById(R.id.jbmx_xiangmu);
        listView.setAdapter(adapter);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        adapter = new TYMingxiAdapter(this,data);
        listView.setAdapter(adapter);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tab =  bundle.getString("tab");
        if (tab.isEmpty()){
            return;
        }
        if (tab.equals("1")){
            biaoti.setText("对手币明细");
            initDownload("commonCoins","",1);
            goldType = "commonCoins";
            sportID = "";
            biaoti2.setTextColor(getResources().getColor(R.color.colorWhite));
            biaotiImage2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            biaoti1.setTextColor(getResources().getColor(R.color.my_hd_tab));
            biaotiImage1.setBackgroundColor(getResources().getColor(R.color.my_tab));
//                listView.setAdapter(adapter);
            xiangmuList.setVisibility(View.GONE);
        }else{
            biaoti.setText("技术分明细");
            initDownload("techCoins","羽毛球",1);
            goldType = "techCoins";
            sportID = "羽毛球";
            biaoti1.setTextColor(getResources().getColor(R.color.colorWhite));
            biaotiImage1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            biaoti2.setTextColor(getResources().getColor(R.color.my_hd_tab));
            biaotiImage2.setBackgroundColor(getResources().getColor(R.color.my_tab));
            xiangmuList.setVisibility(View.VISIBLE);
        }
        huoquyundong();
        shuaxin();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jbmx_biaoti1:
                sportType = "羽毛球";
                biaoti1.setTextColor(getResources().getColor(R.color.colorWhite));
                biaotiImage1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                biaoti2.setTextColor(getResources().getColor(R.color.my_hd_tab));
                biaotiImage2.setBackgroundColor(getResources().getColor(R.color.my_tab));
                xiangmuList.setVisibility(View.VISIBLE);
                huoquyundong();
                initDownload("techCoins",sportType,1);
                break;
            case R.id.jbmx_biaoti2:
                sportType = "";
                biaoti2.setTextColor(getResources().getColor(R.color.colorWhite));
                biaotiImage2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                biaoti1.setTextColor(getResources().getColor(R.color.my_hd_tab));
                biaotiImage1.setBackgroundColor(getResources().getColor(R.color.my_tab));
//                listView.setAdapter(adapter);
                xiangmuList.setVisibility(View.GONE);
                initDownload("commonCoins",sportType,1);
                break;
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
                        initDownload(goldType,sportID,page);
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
                        initDownload(goldType,sportID,page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    private void initDownload(final String goldType, String sportType, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "金币明细" + token+"--goldType--"+goldType+"--sportType--"+sportType);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserGoldLst")
                .addHeader("token", token)
                .addParams("goldType",goldType)
                .addParams("sportType",sportType)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "金币明细" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            TYJBMingxiEntity entity = gson.fromJson(result, TYJBMingxiEntity.class);
                            List<TYJBMingxiEntity.DataBean.GoldLstBean> list;
                            list = entity.getData().getGoldLst();
//                            data.clear();
//                            data.addAll(list);

                            if (page == 1){
                                data.clear();
                                data.addAll(list);

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
                                    if (goldType.equals("techCoins")){
                                        intent.setClass(JBMXActivity.this, JBMXItemTwoActivity.class);
                                        bundle.putString("uid", data.get(position-1).getUUID());
                                        bundle.putString("sportID",sportID);
                                        intent.putExtras(bundle);

                                        startActivity(intent);

                                    }else{
                                        intent.setClass(JBMXActivity.this, JBMXItemActivity.class);
                                        bundle.putString("uid", data.get(position-1).getUUID());
                                        intent.putExtras(bundle);

                                        startActivity(intent);
                                    }

                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JBMXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //获取所有运动
    private void huoquyundong() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllSports")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "运动项目" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            YundongEntity entity = gson.fromJson(result, YundongEntity.class);
                            List<YundongEntity.DataBean> list;
                            list = entity.getData();
                            data2.clear();
                            data2.addAll(list);
                            adapter2 = new JBMXJishuListAdapter(JBMXActivity.this, data2,"1");
                            xiangmuList.setAdapter(adapter2);

//                            adapter2.setOnItemDeleteClickListener(new JBMXJishuListAdapter.onItemDeleteListener() {
//                                @Override
//                                public void onDeleteClick(int position) {
//
//                                }
//                            });
                            xiangmuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    view.findViewById(R.id.jishu_text).setBackgroundColor(getResources().getColor(R.color.hongse));
                                adapter2.setSelectItem(position);
                                    adapter2.notifyDataSetChanged();
                                    sportID = data2.get(position).getName();
                                    initDownload("techCoins",data2.get(position).getName(),1);
                                }
                            });
//                            //爱好的运动点击监听器
//
//                            gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                    xiangmuText.setText(data.get(position).getName());
//                                    xiangmuString = data.get(position).getName();
//                                    xiangmuID = data.get(position).getId()+"";
//                                    mCameraDialog.dismiss();
//
//
//                                }
//                            });


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JBMXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
