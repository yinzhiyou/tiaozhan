package com.example.android.promoter.Home;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.YQHYListAdapter;
import com.example.android.promoter.Adapter.YaoqingAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.FujinEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.YaoqingEntidy;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Wonderful.MainJingcaiItemActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *邀请好友
 */
public class YaoqingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView haoyou,fujin,guanzhu,biaoti;
    private ImageView imageViewhaoyou,imageViewfujin,imageViewguanzhu,fanhui;
    private LinearLayout linearLayoutHY,linearLayoutFJ,linearLayoutGZ;
    private PullToRefreshListView listView;
    private YaoqingAdapter adapter;
    private String token,mylat,mylng,teamSex = "2",minlevel="1",maxlevel = "10",sportName = "all",city,area,type="myfriend",sportid = "1",
            team ="1",tab,FirstSportId,SecondSportId,uuid;
    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    private  List<YaoqingEntidy.DataBean.LstBean> data;
    private List<String> listA,listB;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaoqing);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);

        linearLayoutHY = findViewById(R.id.yaoqing_wode);
        linearLayoutHY.setOnClickListener(this);
        linearLayoutFJ = findViewById(R.id.yaoqing_fujin);
        linearLayoutFJ.setOnClickListener(this);
        linearLayoutGZ = findViewById(R.id.yaoqing_guanzhu);
        linearLayoutGZ.setOnClickListener(this);
        spUtileFQTZ = new SPUtileFQTZ();

        haoyou = findViewById(R.id.yaoqing_haoyou_text);
        fujin = findViewById(R.id.yaoqing_fujin_text);
        guanzhu = findViewById(R.id.yaoqing_guanzhu_text);

        imageViewhaoyou = findViewById(R.id.yaoqing_haoyou_image);
        imageViewfujin = findViewById(R.id.yaoqing_fujin_image);
        imageViewguanzhu = findViewById(R.id.yaoqing_guanzhu_image);

        listView = findViewById(R.id.yaoqing_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList<>();


          Bundle bundle = new Bundle();//接收
          bundle = this.getIntent().getExtras();
        tab =  bundle.getString("tab");
        if (tab.equals("1")){
            listA = bundle.getStringArrayList("listTXA");
            listB = bundle.getStringArrayList("listTXB");
        for(int i = 0;i<listA.size();i++){
            LogU.Ld("1608", "是否重复" + listA.get(i));
        }
            adapter = new YaoqingAdapter(this,data,tab,this,listA,listB);
        }else   if (tab.equals("2")){
            listA = bundle.getStringArrayList("listTXA");
            listB = bundle.getStringArrayList("listTXB");
            for(int i = 0;i<listA.size();i++){
                LogU.Ld("1608", "是否重复" + listA.get(i));
            }
            adapter = new YaoqingAdapter(this,data,tab,this,listA,listB);
        }else   if (tab.equals("3")){

            uuid =  bundle.getString("uuid");
            team =  bundle.getString("team");
            SecondSportId =  bundle.getString("SecondSportId");
            FirstSportId =  bundle.getString("FirstSportId");
            adapter = new YaoqingAdapter(this,data,tab,this,uuid,FirstSportId,SecondSportId,team);
        }




//        listView.setAdapter(adapter);


        spUtils = new SPUtils();

        token = (String) spUtils.get(this, "logintoken", "");
        mylat =  (String) spUtils.get(this, "mylat", "");
        mylng =  (String) spUtils.get(this, "mylng", "");
        city =  (String) spUtils.get(this, "city", "");
        area =  (String) spUtils.get(this, "area", "");


        biaoti.setText("邀请好友");
        initDownload(type,page);
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
                        initDownload(type,page);
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
                        initDownload(type,page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    private void initDownload(String type, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "邀请好友--" + token+"city--"+city+"area--"+area+"mylat--"+mylat+"mylng--"+mylng+"type--"+type+"sportid--"+sportid+"teamSex--"+teamSex
        +"minlevel--"+minlevel+"maxlevel--"+maxlevel+"team--"+team);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getInviteFriends")
                .addHeader("token",token)
                .addParams("city",city)
                .addParams("area",area)
                .addParams("mylat",mylat)
                .addParams("mylng",mylng)
                .addParams("type",type)
                .addParams("sportid",sportid)
                .addParams("teamSex",teamSex)
                .addParams("minlevel",minlevel)
                .addParams("maxlevel",maxlevel)
                .addParams("team",team)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "邀请好友" + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "邀请好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            YaoqingEntidy entity = gson.fromJson(result, YaoqingEntidy.class);
                            List<YaoqingEntidy.DataBean.LstBean> list;
                            list = entity.getData().getLst();

//                            data.clear();
//                            data.addAll(list);
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

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(YaoqingActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


//    //创建成功后邀请
//    private void yaoqing(String type) {
////        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "邀请好友--" + token+"city--"+city+"area--"+area+"mylat--"+mylat+"mylng--"+mylng+"type--"+type+"sportid--"+sportid+"teamSex--"+teamSex
//                +"minlevel--"+minlevel+"maxlevel--"+maxlevel+"team--"+team);
//        OkHttpUtils
//                .post()
//                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/InviteFriends")
//                .addHeader("token",token)
//                .addParams("publishId",uuid)
//                .addParams("invitedId",invitedId)
//                .addParams("team",team)
//                .addParams("SecondSportId",SecondSportId)
//                .addParams("FirstSportId",FirstSportId)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        LogU.Ld("1608", "邀请好友" + e);
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "邀请好友" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            YaoqingEntidy entity = gson.fromJson(result, YaoqingEntidy.class);
//                            List<YaoqingEntidy.DataBean.LstBean> list;
//                            list = entity.getData().getLst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(YaoqingActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
////                            if (entity.getMsg().equals("登录超时")){
////                                Intent intent = new Intent();
////                                intent.setClass(getContext(),DengluActivity.class);
////                                startActivity(intent);
////                            }
//                        }
//                    }
//                });
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yaoqing_wode:
                haoyou.setTextColor(Color.parseColor("#D85D27"));
                imageViewhaoyou.setBackgroundColor(Color.parseColor("#D85D27"));
                fujin.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewfujin.setBackgroundColor(Color.parseColor("#ffffff"));
                guanzhu.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewguanzhu.setBackgroundColor(Color.parseColor("#ffffff"));
                initDownload("myfriend",page);
                type = "myfriend";
                break;
            case R.id.yaoqing_fujin:
                fujin.setTextColor(Color.parseColor("#D85D27"));
                imageViewfujin.setBackgroundColor(Color.parseColor("#D85D27"));
                haoyou.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewhaoyou.setBackgroundColor(Color.parseColor("#ffffff"));
                guanzhu.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewguanzhu.setBackgroundColor(Color.parseColor("#ffffff"));
                initDownload("mynearby",page);
                type = "mynearby";
                break;
            case R.id.yaoqing_guanzhu:
                guanzhu.setTextColor(Color.parseColor("#D85D27"));
                imageViewguanzhu.setBackgroundColor(Color.parseColor("#D85D27"));
                haoyou.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewhaoyou.setBackgroundColor(Color.parseColor("#ffffff"));
                fujin.setTextColor(Color.parseColor("#9B9B9B"));
                imageViewfujin.setBackgroundColor(Color.parseColor("#ffffff"));
                initDownload("myfollow",page);
                type = "myfollow";
                break;
            case R.id.fanhui:
                finish();
                spUtileFQTZ.remove(this,"YQtouxiang");
                spUtileFQTZ.remove(this,"YQtab");
                break;
        }
    }
}
