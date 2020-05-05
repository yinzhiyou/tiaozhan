package com.example.android.tiaozhan;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.android.tiaozhan.Adapter.FujinListAdapter;
import com.example.android.tiaozhan.Adapter.HomeShaixuanOne;
import com.example.android.tiaozhan.Adapter.HomeShanxuanThreeAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.FujinEntity;
import com.example.android.tiaozhan.Entity.JCXIEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.Home.HomeGRTXActivity;
import com.example.android.tiaozhan.Home.XiaoxiActivity;
import com.example.android.tiaozhan.My.MyhuodongActivity;
import com.example.android.tiaozhan.Nearby.SearchActivity;
import com.example.android.tiaozhan.Toos.BaseFragment;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 *附近伙伴
 */
public class FujinFragment extends BaseFragment implements View.OnClickListener{

    private PullToRefreshListView listView;
    private FujinListAdapter adapter;
    private ImageView sousuo,huodong,xiaoxi,weidu;
    private String token,mylat,mylng,sxrenqun = "2",sxjibie= "0",sxqiu="all";
    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    private List<FujinEntity.DataBean.LstBean> data;
    private int page = 1;
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;


    private RelativeLayout shaixuan1,shaixuan2,shaixuan3;
    private boolean tag = true;
    private LinearLayout layoutShaixuan, sxlayout1, sxlayout2, sxlayout3;
    private MyListView listViewshaixuan1, listViewshaixuan2, listViewshaixuan3;
    private List<String> mList;
    private List<String> zonghelist;
    private String[] renqun = {"全部", "男", "女"};
    private String[] zonghe = {"全部", "1级", "2级","3级","4级","5级","6级","7级","8级","9级","10级"};
    private HomeShanxuanThreeAdapter adapter4;
    private List<YundongEntity.DataBean> datashai;
    private HomeShaixuanOne adapter2;
    private TextView quanbu,xingbieText,xiangmuText,dengjiText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fujin_fragment, container, false);

        listView = rootView.findViewById(R.id.fujin_list);

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        sousuo = rootView.findViewById(R.id.fujin_sousuo);
        sousuo.setOnClickListener(this);

        isPrepared = true;

        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();

        token = (String) spUtils.get(getContext(), "logintoken", "");
        mylat =  (String) spUtileFQTZ.get(getContext(), "mylat1", "");
        mylng =  (String) spUtileFQTZ.get(getContext(), "mylng1", "");

        data = new ArrayList<>();
        adapter = new FujinListAdapter(getContext(),data,token);


        shaixuan1 = rootView.findViewById(R.id.fujin_shaixuan1);
        shaixuan1.setOnClickListener(this);
        shaixuan2 = rootView.findViewById(R.id.fujin_shaixuan2);
        shaixuan2.setOnClickListener(this);
        shaixuan3 = rootView.findViewById(R.id.fujin_shaixuan3);
        shaixuan3.setOnClickListener(this);
        sxlayout1 = rootView.findViewById(R.id.fujin_sxlayout1);
        sxlayout2 = rootView.findViewById(R.id.fujin_sxlayout2);
        sxlayout3 = rootView.findViewById(R.id.fujin_sxlayout3);
        layoutShaixuan = rootView.findViewById(R.id.fujin_shaixuanlayout);
        listViewshaixuan1 = rootView.findViewById(R.id.fujin_sxList1);
        listViewshaixuan2 = rootView.findViewById(R.id.fujin_sxList2);
        listViewshaixuan3 = rootView.findViewById(R.id.fujin_sxList3);
        quanbu = rootView.findViewById(R.id.fujin_sxList1_text);
        quanbu.setOnClickListener(this);
        huodong = rootView.findViewById(R.id.fujin_huodong);
        huodong.setOnClickListener(this);
        xiaoxi = rootView.findViewById(R.id.fujin_xiaoxi);
        xiaoxi.setOnClickListener(this);
        xiaoxi.setVisibility(View.GONE);
        weidu = rootView.findViewById(R.id.fujin_xiaoxi_weidu);
        weidu.setVisibility(View.GONE);
        xingbieText = rootView.findViewById(R.id.fj_xingbie_text);
        xiangmuText = rootView.findViewById(R.id.fj_xiangmu_text);
        dengjiText = rootView.findViewById(R.id.fj_dengji_text);

        datashai = new ArrayList<>();



        setlazyLoad();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fujin_sousuo:

                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fujin_shaixuan1://筛选  性别
                if (tag == true) {
                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();

                    renqun();

                    sxlayout1.setVisibility(View.VISIBLE);
                    sxlayout3.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.INVISIBLE);
                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.fujin_shaixuan2: //筛选  智能排序

                if (tag == true) {
                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();
//                    zhineng();
                    huoquyundong();
                    sxlayout1.setVisibility(View.INVISIBLE);
                    sxlayout3.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.VISIBLE);
                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.fujin_shaixuan3://筛选 状态
                if (tag == true) {

                    layoutShaixuan.setVisibility(View.VISIBLE);
                    layoutShaixuan.bringToFront();
//                    zhuangtai();
                    jibie();
                    sxlayout1.setVisibility(View.INVISIBLE);
                    sxlayout2.setVisibility(View.INVISIBLE);
                    sxlayout3.setVisibility(View.VISIBLE);

                    tag = false;
                } else {
                    layoutShaixuan.setVisibility(View.GONE);
                    tag = true;
                }
                break;
            case R.id.fujin_sxList1_text://筛选 球类全部
                sxqiu =  "all";
                xiangmuText.setText("全部");
                initDownload(1);
                layoutShaixuan.setVisibility(View.GONE);
                break;
            case R.id.fujin_huodong:
                intent.setClass(getContext(), MyhuodongActivity.class);
                startActivity(intent);
                break;
            case R.id.fujin_xiaoxi:
                intent.setClass(getContext(), XiaoxiActivity.class);
                startActivity(intent);
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
        LogU.Ld("1608", "附近好友" + token+"--sex--"+"===="+mylat+"====="+mylng+"==="+sxrenqun+"--level--"+sxjibie+"--sportName--"+sxqiu+"page"+page);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyNearbyLst")
                .addHeader("token",token)
                .addParams("mylat",mylat)
                .addParams("mylng",mylng)
                .addParams("sex",sxrenqun)
                .addParams("level",sxjibie)
                .addParams("sportName",sxqiu)
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "附近好友失败" + e.getMessage());
                      //  ToastUitl.longs("网络繁忙，请稍后再试");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "附近好友" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            FujinEntity entity = gson.fromJson(result, FujinEntity.class);
                            List<FujinEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
//                            data.clear();
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
                                    Intent intent =new Intent();
                                    Bundle bundle = new Bundle();
                                    if (token.length()<5){
                                        intent.setClass(getActivity(), DengluActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getActivity(), "请先登陆", Toast.LENGTH_SHORT).show();
                                    }else {
                                        intent.setClass(getActivity(), HomeGRTXActivity.class);
                                        bundle.putString("uid",data.get(position-1).getUuid());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }


                                }
                            });
                            jiancexiaoxi();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
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
                            listViewshaixuan2.setAdapter(adapter2);
                            listViewshaixuan2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                    sxqiu =  datashai.get(position).getName();
                                    page = 1;
                                    initDownload(1);
                                    layoutShaixuan.setVisibility(View.GONE);
                                    xiangmuText.setText(datashai.get(position).getName());
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
                .addParams("msgCate","systems" )
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
//                            jiancehuodong();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

//    //检测活动
//    private void jiancehuodong() {
//        OkHttpUtils
//                .get()
//                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActiveCount")
//                .addHeader("token",token)
//                .addParams("type","publish" )
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "检测活动" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//
//                        if (a) {
//                            Gson gson = new Gson();
//                            HDjianceEntity entity = gson.fromJson(result, HDjianceEntity.class);
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs(entity.getMsg());
//
//                        }
//                    }
//                });
//    }
    //筛选 人群
    public void renqun() {
        //添加数据
        mList = new ArrayList<>();
        for (int i = 0; i < renqun.length; i++) {
            mList.add(renqun[i]);
        }
        adapter4 = new HomeShanxuanThreeAdapter(getActivity(), mList);
        listViewshaixuan1.setAdapter(adapter4);
        listViewshaixuan1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xingbieText.setText(mList.get(position));
                if (mList.get(position).equals("全部")){
                    sxrenqun =  "2";
                }else  if (mList.get(position).equals("男")){
                    sxrenqun =  "0";
                }else  if (mList.get(position).equals("女")){
                    sxrenqun =  "1";
                }
                page = 1;
                initDownload(1);
                layoutShaixuan.setVisibility(View.GONE);
                tag = true;
            }
        });

    }
    //筛选 等级
    public void jibie() {
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
                dengjiText.setText(zonghelist.get(position));
                sxjibie = position+"";

                if (zonghelist.get(position).equals("all")){
                    sxjibie = "0";
                }
                page = 1;
                initDownload(1);
                layoutShaixuan.setVisibility(View.GONE);
                tag = true;
            }
        });

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
        token = (String) spUtils.get(getContext(), "logintoken", "");
        mylat =  (String) spUtileFQTZ.get(getContext(), "mylat1", "");
        mylng =  (String) spUtileFQTZ.get(getContext(), "mylng1", "");

        initDownload(1);
        shuaxin();
    }

}
