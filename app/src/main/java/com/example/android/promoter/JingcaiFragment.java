package com.example.android.promoter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.HomeShaixuanOne;
import com.example.android.promoter.Adapter.HomeShanxuanThreeAdapter;
import com.example.android.promoter.Adapter.JingcaiListAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.JCXIEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.JingcaiEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.Home.XiaoxiActivity;
import com.example.android.promoter.My.MyhuodongActivity;
import com.example.android.promoter.Toos.BaseFragment;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Wonderful.JCFBActivity;
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
 * 精彩瞬间
 */
public class

JingcaiFragment extends BaseFragment implements View.OnClickListener{
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;
    private PullToRefreshListView listView;
    private JingcaiListAdapter adapter;
    private List<JingcaiEntity.DataBean.LstBean> data;
    private ImageView fabu,huodong,xiaoxi,weidu;
    private int page = 1;
    private String token,sxrenqun = "2",sxzonghe = "all",sxqiu ="全部",uuid;
    private SPUtils spUtils;
    private RelativeLayout shaixuan1,shaixuan2,shaixuan3;
    private boolean tag = true;
    private LinearLayout  layoutShaixuan, sxlayout1, sxlayout2, sxlayout3;
    private MyListView listViewshaixuan1, listViewshaixuan2, listViewshaixuan3;
    private List<String> mList;
    private List<String> zonghelist;
    private String[] renqun = {"全部", "男", "女"};
    private String[] zonghe = { "最热发现", "最新发现"};
    private HomeShanxuanThreeAdapter adapter4;
    private List<YundongEntity.DataBean> datashai;
    private HomeShaixuanOne adapter2;
    private TextView quanbu,renqunText,xiangmuText,zongheText;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.jingcai_fragment, container, false);
        spUtils = new SPUtils();
        token = (String) spUtils.get(getContext(), "logintoken", "");


        isPrepared = true;
        setlazyLoad();

        listView = rootView.findViewById(R.id.jingcai_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList<>();
        adapter = new JingcaiListAdapter(getContext(),data,token);
        fabu = rootView.findViewById(R.id.jingcai_fabu);
        fabu.setOnClickListener(this);

        shaixuan1 = rootView.findViewById(R.id.jingcai_shaixuan1);
        shaixuan1.setOnClickListener(this);
        shaixuan2 = rootView.findViewById(R.id.jingcai_shaixuan2);
        shaixuan2.setOnClickListener(this);
        shaixuan3 = rootView.findViewById(R.id.jingcai_shaixuan3);
        shaixuan3.setOnClickListener(this);
        sxlayout1 = rootView.findViewById(R.id.jingcai_sxlayout1);
        sxlayout2 = rootView.findViewById(R.id.jingcai_sxlayout2);
        sxlayout3 = rootView.findViewById(R.id.jingcai_sxlayout3);
        layoutShaixuan = rootView.findViewById(R.id.jingcai_shaixuanlayout);
        listViewshaixuan1 = rootView.findViewById(R.id.jingcai_sxList1);
        listViewshaixuan2 = rootView.findViewById(R.id.jingcai_sxList2);
        listViewshaixuan3 = rootView.findViewById(R.id.jingcai_sxList3);
        quanbu = rootView.findViewById(R.id.jingcai_sxList1_text);
        quanbu.setOnClickListener(this);
        huodong = rootView.findViewById(R.id.jingcai_huodong);
        huodong.setOnClickListener(this);
        xiaoxi = rootView.findViewById(R.id.jingcai_xiaoxi);
        xiaoxi.setOnClickListener(this);
        renqunText = rootView.findViewById(R.id.jingcai_renqun);
        xiangmuText = rootView.findViewById(R.id.jingcai_xiangmu);
        zongheText = rootView.findViewById(R.id.jingcai_zonghe);
        weidu = rootView.findViewById(R.id.jingcai_xiaoxi_weidu);

        datashai = new ArrayList<>();

        listView.setAdapter(adapter);

        jingcai(page);

        shuaxin();

        adapter.setOnItemDeleteClickListener(new JingcaiListAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int position) {
                jingcai(data.get(position).getUUID());

//                jingcai(page);
            }


        });
        return rootView;

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
    private void jingcai(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "token" + token+"---sportName---"+sxqiu+"--sex--"+sxrenqun+"---sorts---"+sxzonghe);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getWonderfulLst")
                .addHeader("token",token)
                .addParams("sportName",sxqiu)
                .addParams("sex",sxrenqun)
                .addParams("sorts",sxzonghe)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "精彩瞬间" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JingcaiEntity entity = gson.fromJson(result, JingcaiEntity.class);
                            final List<JingcaiEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);

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
                                    if (token.length()<5){
                                        intent.setClass(getActivity(), DengluActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getActivity(), "请先登陆", Toast.LENGTH_SHORT).show();
                                    }else {
                                        intent.setClass(getContext(), MainJingcaiItemActivity.class);
                                        bundle.putString("uuid", data.get(position - 1).getUUID() + "");
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                }
                            });



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //精彩瞬间
    private void jingcai(String wonderfulId) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "攒点"+token+wonderfulId);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/praiseWonderful")
                .addHeader("token",token)
                .addParams("wonderfulId",wonderfulId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "点赞" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            jingcai(page);
//                            adapter.notifyDataSetChanged();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }



    //检测信息是否完善
    private void jiance() {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserPerfectInfo")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检验资料" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), JCFBActivity.class);
                            startActivity(intent);
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            intent.setClass(getContext(), FaqiTiaozhanActivity.class);
//                            spUtileFQTZ.clear(getContext());
//                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(getContext(),DengluActivity.class);
                                startActivity(intent);
                            }else{
                                showNormalDialog();
                            }


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
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("报名和发布活动前，请完善信息");
        normalDialog.setPositiveButton("先看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), MainActivity.class);
//                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("去完善",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        intent.setClass(getContext(), GRXXActivity.class);
                        bundle.putString("tab", "1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();
    }
    /**
     * 加载数据的方法，只要保证isPrepared和isVisible都为true的时候才往下执行开始加载数据
     */
    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if(!isPrepared || !isVisible) {
            return;
        }
        //TODO 此处填充view中各个控件的数据
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.jingcai_fabu://发布任务
                jiance();
//                intent.setClass(getActivity(), JCFBActivity.class);
//                startActivity(intent);

                break;
            case R.id.jingcai_shaixuan1://筛选  运动球类

                if (tag == true) {
                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();

                    huoquyundong();

                    sxlayout1.setVisibility(View.VISIBLE);
                    sxlayout3.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.INVISIBLE);
                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.jingcai_shaixuan2: //筛选  智能排序

                if (tag == true) {
                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();
//                    zhineng();
                    renqun();
                    sxlayout1.setVisibility(View.INVISIBLE);
                    sxlayout3.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.VISIBLE);
                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.jingcai_shaixuan3://筛选 状态
                if (tag == true) {

                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();
//                    zhuangtai();
                    zonghe();
                    sxlayout1.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.INVISIBLE);
                    sxlayout3.setVisibility(View.VISIBLE);

                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.jingcai_sxList1_text://筛选 球类全部
                sxqiu =  "全部";
                jingcai(page);
                layoutShaixuan.setVisibility(View.GONE);
                break;
            case R.id.jingcai_huodong://
                intent.setClass(getContext(), MyhuodongActivity.class);
                startActivity(intent);
                break;
            case R.id.jingcai_xiaoxi://
                intent.setClass(getContext(), XiaoxiActivity.class);
                startActivity(intent);
             break;
        }
    }
    //精彩筛选数据
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
                            final YundongEntity entity = gson.fromJson(result, YundongEntity.class);
                            List<YundongEntity.DataBean> list;
                            list = entity.getData();
                            datashai.clear();
                            datashai.addAll(list);

                            adapter2 = new HomeShaixuanOne(getContext(), datashai,"2");
                            listViewshaixuan1.setAdapter(adapter2);
                            listViewshaixuan1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    //添加数据
//                                    mList = new ArrayList<>();
//                                    for (int i = 0; i < zhineng.length; i++) {
//                                        mList.add(zhineng[i]);
//                                    }
                                    tag = true;
//                                    sxhuodong1 = datashai.get(position).getId() + "";
//                                    huodongtwo(datashai.get(position).getId());
//                                    textViewSXT.setVisibility(View.VISIBLE);
//                                    sxlayout2.setVisibility(View.VISIBLE);

//                                    adapter3 = new HomeShaixuanTwoAdapter(getActivity(),mList);
//                                        listViewshaixuan2.setAdapter(adapter3);
                                    xiangmuText.setText(  datashai.get(position).getName());
                                   sxqiu =  datashai.get(position).getName();
                                    jingcai(page);
                                    layoutShaixuan.setVisibility(View.GONE);
                                }
                            });
                            adapter2.notifyDataSetChanged();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //检测消息
    private void jiancexiaoxi() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNotReadMessageCount")
                .addHeader("token",token)
                .addParams("msgCate","systems " )
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JCXIEntity entity = gson.fromJson(result, JCXIEntity.class);
                            if (entity.getData().getNotReadCount()>0){
                                weidu.setVisibility(View.VISIBLE);

                            }else {
                                weidu.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }
    //筛选 人群
    public void renqun() {
        //添加数据
        mList = new ArrayList<>();
        for (int i = 0; i < renqun.length; i++) {
            mList.add(renqun[i]);
        }
        adapter4 = new HomeShanxuanThreeAdapter(getActivity(), mList);
        listViewshaixuan2.setAdapter(adapter4);
        listViewshaixuan2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    renqunText.setText(mList.get(position));
                if (mList.get(position).equals("全部")){
                    sxrenqun =  "2";
                }else  if (mList.get(position).equals("男")){
                    sxrenqun =  "0";
                }else  if (mList.get(position).equals("女")){
                    sxrenqun =  "1";
                }
                jingcai(page);
                layoutShaixuan.setVisibility(View.GONE);
                tag = true;
            }
        });

    }
    //筛选 综合
    public void zonghe() {
        //添加数据
        zonghelist = new ArrayList<>();
        for (int i = 0; i < zonghe.length; i++) {
            zonghelist.add(zonghe[i]);
        }
        adapter4 = new HomeShanxuanThreeAdapter(getActivity(), zonghelist);
        listViewshaixuan3.setAdapter(adapter4);
        listViewshaixuan3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                zongheText.setText(zonghelist.get(position));
                if (zonghelist.get(position).equals("最热发现")){
                    sxzonghe = "hot";
                }else {
                    sxzonghe = "all";
                }
                jingcai(page);
                layoutShaixuan.setVisibility(View.GONE);
                tag = true;
            }
        });

    }

//    @Override
//    public void onRestart() {
//        super.onRestart();
//        jingcai( page);
//    }

    @Override
    public void onStart() {
        super.onStart();
        jingcai( page);
    }
}


