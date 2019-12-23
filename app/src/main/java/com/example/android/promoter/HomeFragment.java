package com.example.android.promoter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.promoter.Adapter.HomeListAdapter;
import com.example.android.promoter.Adapter.HomeShaixuanOne;
import com.example.android.promoter.Adapter.HomeShaixuanTwoAdapter;
import com.example.android.promoter.Adapter.HomeShanxuanThreeAdapter;
import com.example.android.promoter.Adapter.PopAdapter;
import com.example.android.promoter.Adapter.PopAdapterTwo;
import com.example.android.promoter.Adapter.pupadapter.ListDropDownAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.HomeListEntity;
import com.example.android.promoter.Entity.JCXIEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.LunboEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Entity.PanduanQiandanEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.Entity.YundongTwoEntity;
import com.example.android.promoter.Entity.ZuobiaoEntity;
import com.example.android.promoter.Home.FaqiTiaozhanActivity;
import com.example.android.promoter.Home.HZCGActivity;
import com.example.android.promoter.Home.HomeHDXQActivity;
import com.example.android.promoter.Home.HomeSousuoActivity;
import com.example.android.promoter.Home.RenWuActivity;
import com.example.android.promoter.Home.ShopActivity;
import com.example.android.promoter.Promoter.MyPromoterActivity;
import com.example.android.promoter.Promoter.PromoterErrorActivity;
import com.example.android.promoter.Promoter.PromoterONEActivity;
import com.example.android.promoter.Promoter.PromoterTWOActivity;
import com.example.android.promoter.Promoter.TuiguangHomeActivity;
import com.example.android.promoter.Home.XiaoxiActivity;
import com.example.android.promoter.My.MyhuodongActivity;
import com.example.android.promoter.Toos.BaseFragment;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.GlideImageLoader;
import com.example.android.promoter.Toos.HomeDialog;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.pup.ItemClickListener;
import com.example.android.promoter.Toos.pup.SpinerPopWindow;
import com.example.android.promoter.Toos.pup.SpinerPopWindowXM;
import com.example.android.promoter.Toos.time.dialog.SelectDateDialog;
import com.example.android.promoter.Toos.time.dialog.SelectLVDialog;
import com.example.android.promoter.Toos.time.dialog.SelectTimeDialog;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jaeger.library.StatusBarUtil;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;

import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fj.dropdownmenu.lib.view.DropdownColumnView;
import okhttp3.Call;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private int locType;
    private PopupWindow popupBigPhoto;
    private List<String> listP;
    private ListPopu listPopu;
    private ListPopuJL listPopuJL;
    private View line;
    private LocationClient mlocationClient;
    private MylocationListener mlistener;
    private double mLatitude;
    private double mLongitude;
    private String city, area, uuid, province, content, joinCondition = "";
    private Banner banner;
    private ArrayAdapter arr_adapter;
    private ArrayList data_list;
    private HomeListAdapter adapter;
    private HomeShaixuanOne adapter2;
    private PullToRefreshListView listView;
    private ProgressDialog progressDialog;
    private ListView listViewJC;

    private LinearLayout qiandao, yingjinbi, duihuanSC, wenzhang, hzcg, chengshiLayout;
    private RelativeLayout shaixuan1, shaixuan2, shaixuan3, shaixuan4;
    private boolean tag = true, yuleTAG = true, jingjiTAG = true, wozhaoTAG = true, woshiTAG = true,
            aaTAG = true, shumaiTAG = true, nanTAG = true, nvTAG = true, kbmTag = true, timeTAG = true, minTAG = true, maxTAG = true, oneTAG = true, twoTAG = true, threeTAG = true, fourTAG = true, qbhdTAG = true, bmTAG = true;
    private DrawerLayout drawerLayout;
    private ImageView xiaoxi, faqitiaozhan, huodong, weidu, qiandaoImage, dingbu, kbm;
    private String token, touxiang, nickname;
    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    private List<HomeListEntity.DataBean.ActiveLstBean> data;
    private List<YundongEntity.DataBean> datashai;
    private List<YundongTwoEntity.DataBean> datashaitwo;
    private List<String> mList;
    private List<String> mListtwo;
    private String[] zhineng = {"距离由近到远", "时间由近到远", "级别由高到低", "级别由低到高", "好评优先"};
    private int page = 1;
    private String[] zhuangtai = {"全部", "匹配中", "活动中"};
    private String[] juli = {"全部范围", "1km", "2km", "4km", "10km"};
    private HomeShaixuanTwoAdapter adapter3;
    private HomeShanxuanThreeAdapter adapter4;
    private TextView qiandaoText, chouti_qb_hd_text, chouti_bm_hd_text;

    private TextView chouti_hp_four_text, chouti_hp_one_text, chouti_hp_two_text, chouti_hp_three_text, chouti_js_lv_min_text, chouti_js_lv_max_text, chouti_day_text, chouti_tiem_text;

    private String sxmoshi = "0", sxfeiyong = "0", sxsex = "2", sxzhineng = "0", sxzhuangtai = "0", tuiguang;
    //抽屉布局
    private RelativeLayout yuleLayout, jingjiLayout, wozhaoLayout, woshiLayout, aaLayout, shumaiLayout, nanLayout, nvLayout, quedingLayout;
    private TextView yule, jingji, wozhao, woshi, aa, shumai, nan, nv, queding, chengshiText, sousuo, xiangmuText, zhinengText, zhuangtaiText, shaixuan, tgyText;
    private LinearLayout.LayoutParams layoutParams;
    private int sxhuodong1 = 0, sxhuodong2 = 0, dayTAG = 0;
    /**
     * 顶部固定的TabViewLayout
     */
    private LinearLayout mTopTabViewLayout;
    /**
     * 跟随ScrollView的TabviewLayout
     */
    private LinearLayout mTabViewLayout;
    /**
     * 要悬浮在顶部的View的子View
     */
    private LinearLayout mTopView;
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;
    private XBanner mXBanner;
    private String[] timesString;

    CollapsingToolbarLayout collapsingToolbar;
    DropdownColumnView lvRegion;

    private RelativeLayout chouti_dayx, chouti_time, chouti_cz, chouti_js_lv_max, chouti_js_lv_min, chouti_hp_one, chouti_hp_two, chouti_hp_three, chouti_hp_four, chouti_bm_hd, chouti_qb_hd;
    private SelectDateDialog mSelectDateDialog;
    private String[] lVleArray;
    private int mhour, mminute, lv, lv2, hpTAg = 0, guilTAG = 0, timeTag = 0, jlTAG = 0;
    private boolean hp_one = false, hp_two = false, hp_three = false, hp_four = false;
    private String bmTAg = "joinCondition";
    private List<YundongTwoEntity.DataBean> list;
    private List<YundongEntity.DataBean> list1;
    private String headers[] = {"运动项目", "距离范围", "推荐排序", "筛选"};

    private ListDropDownAdapter ageAdapter;
    private List<View> popupViews = new ArrayList<>();

    private String tuijian[] = {"距离由近到远", "时间由近到远", "级别由高到低", "级别由低到高", "好评优先"};
    private List<String> jlList;
    private List<String> tJList;
    private int acitvitysort = 0;
    private List<YundongEntity.DataBean> sportList;
    private TextView item;
    private List<YundongTwoEntity.DataBean> sPorttwo;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_right;
    private PopAdapter spiner;
    private PopAdapterTwo spinerAdapter;
    private int anInt;
    private YundongEntity entity;
    private String day = "0";
    private String time = "0";
    private LinearLayout layout_right;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        View header2 = View.inflate(getContext(), R.layout.header2, null);
        View header1 = View.inflate(getContext(), R.layout.header1, null);


        mSelectDateDialog = new SelectDateDialog(getActivity());
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.tab_backgroud), 0);
        isPrepared = true;
        setlazyLoad();
        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        token = (String) spUtils.get(getContext(), "logintoken", "");
        tuiguang = (String) spUtils.get(getContext(), "tuiguang", "0");


        banner = header1.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(8000);


        line = header2.findViewById(R.id.line);
        shaixuan1 = header2.findViewById(R.id.home_shaixuan1);
        shaixuan1.setOnClickListener(this);
        shaixuan2 = header2.findViewById(R.id.home_shaixuan2);
        shaixuan2.setOnClickListener(this);
        shaixuan3 = header2.findViewById(R.id.home_shaixuan3);
        shaixuan3.setOnClickListener(this);

        shaixuan4 = header2.findViewById(R.id.home_shaixuan4);
        shaixuan4.setOnClickListener(this);

        dingbu = rootView.findViewById(R.id.home_dingbu);
        dingbu.setOnClickListener(this);


        listView = rootView.findViewById(R.id.home_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        qiandao = header1.findViewById(R.id.home_qiandao);
        qiandao.setOnClickListener(this);
        yingjinbi = header1.findViewById(R.id.home_yingjinbi);
        yingjinbi.setOnClickListener(this);
        duihuanSC = header1.findViewById(R.id.home_duihuanshangcheng);
        duihuanSC.setOnClickListener(this);
        wenzhang = header1.findViewById(R.id.home_wenzhang);
        wenzhang.setOnClickListener(this);
        hzcg = header1.findViewById(R.id.home_hezuo);
        hzcg.setOnClickListener(this);
        tgyText = header1.findViewById(R.id.home_tgy_text);

        LogU.Ld("1608", "推广员" + tuiguang);
       /* if (tuiguang.equals("0")) {
            tgyText.setText("成为推广员");
        } else {
            tgyText.setText("我是推广员");


        }*/

        xiaoxi = rootView.findViewById(R.id.home_xiaoxi);
        xiaoxi.setOnClickListener(this);
        xiaoxi.setVisibility(View.GONE);
        faqitiaozhan = rootView.findViewById(R.id.home_faqitiaozhan);
        faqitiaozhan.setOnClickListener(this);
        huodong = rootView.findViewById(R.id.home_huodong);
        huodong.setOnClickListener(this);
        kbm = rootView.findViewById(R.id.home_kbm);
        kbm.setOnClickListener(this);


        drawerLayout = rootView.findViewById(R.id.drawerlayout);
        drawerLayout.bringToFront();
        layout_right = rootView.findViewById(R.id.layout);

//        //抽屉布局
        yuleLayout = rootView.findViewById(R.id.chouti_yule);
        yuleLayout.setOnClickListener(this);
        jingjiLayout = rootView.findViewById(R.id.chouti_jingji);
        jingjiLayout.setOnClickListener(this);
        wozhaoLayout = rootView.findViewById(R.id.chouti_wozhao);
        wozhaoLayout.setOnClickListener(this);
        woshiLayout = rootView.findViewById(R.id.chouti_woshi);
        woshiLayout.setOnClickListener(this);
        aaLayout = rootView.findViewById(R.id.chouti_aa);
        aaLayout.setOnClickListener(this);
        shumaiLayout = rootView.findViewById(R.id.chouti_shumai);
        shumaiLayout.setOnClickListener(this);
        nanLayout = rootView.findViewById(R.id.chouti_nan);
        nanLayout.setOnClickListener(this);
        nvLayout = rootView.findViewById(R.id.chouti_nv);
        nvLayout.setOnClickListener(this);
        quedingLayout = rootView.findViewById(R.id.chouti_queding);
        quedingLayout.setOnClickListener(this);

        yule = rootView.findViewById(R.id.chouti_yule_text);
        jingji = rootView.findViewById(R.id.chouti_jingji_text);
        wozhao = rootView.findViewById(R.id.chouti_wozhao_text);
        woshi = rootView.findViewById(R.id.chouti_woshi_text);
        aa = rootView.findViewById(R.id.chouti_aa_text);
        shumai = rootView.findViewById(R.id.chouti_shumai_text);
        nan = rootView.findViewById(R.id.chouti_nan_text);
        nv = rootView.findViewById(R.id.chouti_nv_text);

        chouti_day_text = rootView.findViewById(R.id.chouti_day_text);
        chouti_tiem_text = rootView.findViewById(R.id.chouti_tiem_text);
        chouti_dayx = rootView.findViewById(R.id.chouti_day_xx);
        chouti_dayx.setOnClickListener(this);
        chouti_time = rootView.findViewById(R.id.chouti_time);
        chouti_time.setOnClickListener(this);
        chouti_js_lv_min = rootView.findViewById(R.id.chouti_js_lv_min);
        chouti_js_lv_min.setOnClickListener(this);
        chouti_js_lv_max = rootView.findViewById(R.id.chouti_js_lv_max);
        chouti_js_lv_max.setOnClickListener(this);


        chouti_hp_one = rootView.findViewById(R.id.chouti_hp_one);
        chouti_hp_one.setOnClickListener(this);
        chouti_hp_two = rootView.findViewById(R.id.chouti_hp_two);
        chouti_hp_two.setOnClickListener(this);
        chouti_hp_three = rootView.findViewById(R.id.chouti_hp_three);
        chouti_hp_three.setOnClickListener(this);
        chouti_hp_four = rootView.findViewById(R.id.chouti_hp_four);
        chouti_hp_four.setOnClickListener(this);

        chouti_bm_hd = rootView.findViewById(R.id.chouti_bm_hd);
        chouti_bm_hd.setOnClickListener(this);
        chouti_qb_hd = rootView.findViewById(R.id.chouti_qb_hd);
        chouti_qb_hd.setOnClickListener(this);

        chouti_js_lv_min_text = rootView.findViewById(R.id.chouti_js_lv_min_text);
        chouti_js_lv_max_text = rootView.findViewById(R.id.chouti_js_lv_max_text);
        chouti_hp_one_text = rootView.findViewById(R.id.chouti_hp_one_text);
        chouti_hp_two_text = rootView.findViewById(R.id.chouti_hp_two_text);
        chouti_hp_three_text = rootView.findViewById(R.id.chouti_hp_three_text);
        chouti_hp_four_text = rootView.findViewById(R.id.chouti_hp_four_text);
        chouti_bm_hd_text = rootView.findViewById(R.id.chouti_bm_hd_text);
        chouti_qb_hd_text = rootView.findViewById(R.id.chouti_qb_hd_text);

        chouti_cz = rootView.findViewById(R.id.chouti_cz);
        chouti_cz.setOnClickListener(this);

        weidu = rootView.findViewById(R.id.home_xiaoxi_weidu);
        weidu.setVisibility(View.GONE);
        qiandaoText = header1.findViewById(R.id.home_qiandao_text);
        qiandaoImage = header1.findViewById(R.id.home_qiandao_image);
        chengshiText = rootView.findViewById(R.id.home_chengshi);

        chengshiLayout = rootView.findViewById(R.id.home_chengshi_layout);
        chengshiLayout.setOnClickListener(this);

        sousuo = rootView.findViewById(R.id.home_sousuo);
        sousuo.setOnClickListener(this);

        xiangmuText = header2.findViewById(R.id.home_text1);
        zhinengText = header2.findViewById(R.id.home_text2);
        zhuangtaiText = header2.findViewById(R.id.home_text3);
        shaixuan = header2.findViewById(R.id.home_text4);
//            listView.setVisibility(View.GONE);
        datashai = new ArrayList<>();

        data = new ArrayList<>();
        adapter = new HomeListAdapter(getContext(), data);


//        MyScrollView mMyScrollView = rootView.findViewById(R.id.my_scrollview);
        mTabViewLayout = header2.findViewById(R.id.ll_tabView);
        mTopTabViewLayout = rootView.findViewById(R.id.home_tablayout);
        mTopView = header2.findViewById(R.id.home_layout3);
        //滑动监听
//        mMyScrollView.setOnScrollListener(this);
//xbanner
        mXBanner = (XBanner) header1.findViewById(R.id.xbanner);

        listViewJC = listView.getRefreshableView();
        listViewJC.addHeaderView(header1);  //添加头部，会隐藏的部分
        listViewJC.addHeaderView(header2);  //添加头部，ListView条目中的悬浮部分

        listView.setAdapter(adapter);
        listViewJC.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 2) {
//                    mTabViewLayout.setVisibility(View.VISIBLE);
                } else {

//                    mTabViewLayout.setVisibility(View.GONE);
                }
            }
        });
//         layoutParams = (LinearLayout.LayoutParams) listView.getRefreshableViewWrapper().getLayoutParams();
        if (tuiguang.equals("1")) {
            tgyText.setText("我是推广员");
        }
        if (tuiguang.equals("4")) {
            tgyText.setText("我是推广员");
        }


        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                // 打开手势滑动
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                // 关闭手势滑动
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        dingwei();
        shuaxin();
        initValue();
        initValueLv();
        //  xiaLa();
        initData();
        showDialog();

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            mlocationClient.stop();
            Bundle bundle = data.getExtras();
            String city1 = bundle.getString("city");
            area = bundle.getString("area");
            double mLatitude1 = bundle.getDouble("mylat");
            double mLongitude1 = bundle.getDouble("mylong");
            content = bundle.getString("content");

            LogU.Ld("1608", city + "---" + area);
            if (city == null) {
                chengshiText.setText(content);
            } else {
                chengshiText.setText(city);
            }

            sousuo.setText(content);

            initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg);

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//接收
        int id = v.getId();
        if (listPopu != null) {
            zhinengText.setTextColor(getResources().getColor(R.color.huise));
            listPopu.dismiss();
            listPopu = null;

        } else {
            if (id == R.id.home_shaixuan2) {
                //setTextDrawable(R.mipmap.xiajiantou,xiangmuText);
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));
                getList();
            }

        }

        if (listPopuJL != null) {
            zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
            listPopuJL.dismiss();
            listPopuJL = null;

        } else {
            if (id == R.id.home_shaixuan3) {
                //setTextDrawable(R.mipmap.xiajiantou,zhuangtaiText);
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));
                getJLList();

            }

        }
        if (popupBigPhoto != null) {
            LogU.Ld("1608","取消");
            xiangmuText.setTextColor(getResources().getColor(R.color.huise));
            popupBigPhoto.dismiss();
            popupBigPhoto = null;

        } else {
            if (id == R.id.home_shaixuan1) {
                LogU.Ld("1608","取消没");
                xiangmuText.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));
                initPopup();

            }

        }
        switch (v.getId()) {

            case R.id.home_wenzhang:

                if (tuiguang.equals("0")) {
                    intent.setClass(getActivity(), PromoterTWOActivity.class);
                    startActivity(intent);

                }
                if (tuiguang.equals("1")) {
                    tgyText.setText("我是推广员");
                    intent.setClass(getActivity(), MyPromoterActivity.class);
                    startActivity(intent);

                }
                if (tuiguang.equals("2")) {
                    intent.setClass(getActivity(), PromoterErrorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                if (tuiguang.equals("3")) {
                    Toast.makeText(getActivity(), "您还没有身份认证", Toast.LENGTH_SHORT).show();
                    intent.setClass(getActivity(), PromoterONEActivity.class);
                    startActivity(intent);
                }
                if (tuiguang.equals("4")) {

                    Toast.makeText(getActivity(), "该推广员账号被停用", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.home_hezuo:
                intent.setClass(getContext(), HZCGActivity.class);
                startActivity(intent);
//              ToastUitl.longs("暂未开放该功能");
                break;
            case R.id.home_qiandao://签到

                if (token.equals("")) {
                    ToastUitl.longs("您还未登录");
                    intent.setClass(getContext(), DengluActivity.class);
                    startActivity(intent);
                } else {
                    qiandao();
                }
                break;
            case R.id.home_dingbu://
                listViewJC.setSelection(0);
                dingbu.setVisibility(View.GONE);
                break;


            case R.id.home_sousuo://搜索
                intent.setClass(getContext(), HomeSousuoActivity.class);
                bundle.putString("chengshi", chengshiText.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);

                break;
            case R.id.home_chengshi_layout://城市选择
                mlocationClient.start();
//                ToastUitl.longs("aaaaaaaaa");
                final List<HotCity> hotCities = new ArrayList<>();
                hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
                hotCities.add(new HotCity("上海", "上海", "101020100"));
                hotCities.add(new HotCity("广州", "广东", "101280101"));
                hotCities.add(new HotCity("深圳", "广东", "101280601"));
                hotCities.add(new HotCity("杭州", "浙江", "101210101"));

                CityPicker.from((FragmentActivity) getContext()) //activity或者fragment
                        .enableAnimation(false)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
                        .setLocatedCity(new LocatedCity(city, province, null))  //APP自身已定位的城市，传null会自动定位（默认）参数 市  省   地区代码
//                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {

                                LogU.Ld("1608", data.getCode() + "----" + data.getName() + "-----" + data.getPinyin() + "-----" + data.getProvince() + "-------" + data.getSection() + "到达1" + city + "到达2" + province);
                                chengshiText.setText(data.getName());
                                sousuo.setText("");
                                if (city.indexOf(data.getName()) != -1) {
                                    LogU.Ld("1608", "包含");
//                                    mlocationClient.start();
//                                    dingwei();

                                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                                } else {
                                    LogU.Ld("1608", "不包含");
                                    zuobiao(data.getName());
                                }

                            }

                            @Override
                            public void onCancel() {
//                                Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLocate() {
                                //定位接口，需要APP自身实现，这里模拟一下定位
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //定位完成之后更新数据
////                                        CityPicker.getInstance()
////                                                .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
////                                        CityPicker.setLocatedCity().locateComplete();
//                                    }
//                                }, 3000);
                            }
                        })
                        .show();
////


                break;
            case R.id.home_yingjinbi://任务
                if (token.equals("")) {
                    ToastUitl.longs("您还未登录");
                    intent.setClass(getContext(), DengluActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), RenWuActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.home_duihuanshangcheng://商城
                intent.setClass(getContext(), ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.home_shaixuan4:
                shaixuan.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));
                /*if (!drawerLayout.isDrawerOpen(layout_right)) {
                    drawerLayout.openDrawer(layout_right);
                }*/
                 drawerLayout.openDrawer(Gravity.RIGHT);

                LogU.Ld("1608", "抽屉");

                break;
            case R.id.home_xiaoxi://消息
                intent.setClass(getContext(), XiaoxiActivity.class);
                startActivity(intent);
                break;
            case R.id.home_huodong://我的活动
                if (token.equals("")) {
                    ToastUitl.longs("您还未登录");
                    intent.setClass(getContext(), DengluActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyhuodongActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.home_faqitiaozhan://发起挑战


                if (token.equals("")) {
                    ToastUitl.longs("您还未登录");
                    intent.setClass(getContext(), DengluActivity.class);
                    startActivity(intent);
                } else {
                    jiance();
                }


            case R.id.chouti_yule://筛选模式  娱乐
                sxmoshi = "1";
                if (!yuleTAG) {
                    yuleLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    yule.setTextColor(getResources().getColor(R.color.bbbbb));
                    yuleTAG = true;
                    sxmoshi = "0";
                    shumaiLayout.setVisibility(View.VISIBLE);
                } else {
                    yuleLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    yule.setTextColor(getResources().getColor(R.color.my_tab));

                    jingjiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    jingji.setTextColor(getResources().getColor(R.color.bbbbb));
                    woshiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    woshi.setTextColor(getResources().getColor(R.color.bbbbb));
                    wozhaoLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    wozhao.setTextColor(getResources().getColor(R.color.bbbbb));

                    shumaiLayout.setVisibility(View.GONE);
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                    yuleTAG = false;
                    jingjiTAG = true;
                    wozhaoTAG = true;
                    woshiTAG = true;
                }
                break;
            case R.id.chouti_jingji://筛选模式  竞技
                sxmoshi = "2";
                if (!jingjiTAG) {
                    jingjiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    jingji.setTextColor(getResources().getColor(R.color.bbbbb));
                    jingjiTAG = true;
                    sxmoshi = "0";
                    shumaiLayout.setVisibility(View.VISIBLE);
                } else {
                    jingjiLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    jingji.setTextColor(getResources().getColor(R.color.my_tab));

                    yuleLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    yule.setTextColor(getResources().getColor(R.color.bbbbb));
                    woshiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    woshi.setTextColor(getResources().getColor(R.color.bbbbb));
                    wozhaoLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    wozhao.setTextColor(getResources().getColor(R.color.bbbbb));
                    jingjiTAG = false;
                    yuleTAG = true;
                    wozhaoTAG = true;
                    woshiTAG = true;
                    shumaiLayout.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.chouti_wozhao://筛选模式  我找陪练
                sxmoshi = "4";
                if (!woshiTAG) {
                    wozhaoLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    wozhao.setTextColor(getResources().getColor(R.color.bbbbb));
                    woshiTAG = true;
                    sxmoshi = "0";
                    shumaiLayout.setVisibility(View.VISIBLE);
                } else {
                    wozhaoLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    wozhao.setTextColor(getResources().getColor(R.color.my_tab));

                    yuleLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    yule.setTextColor(getResources().getColor(R.color.bbbbb));
                    woshiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    woshi.setTextColor(getResources().getColor(R.color.bbbbb));
                    jingjiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    jingji.setTextColor(getResources().getColor(R.color.bbbbb));
                    woshiTAG = false;
                    jingjiTAG = true;
                    yuleTAG = true;
                    wozhaoTAG = true;

                    shumaiLayout.setVisibility(View.GONE);
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                }
                break;
            case R.id.chouti_woshi://筛选模式 我是陪练
                sxmoshi = "3";
                if (!wozhaoTAG) {
                    woshiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    woshi.setTextColor(getResources().getColor(R.color.bbbbb));
                    wozhaoTAG = true;
                    sxmoshi = "0";
                    shumaiLayout.setVisibility(View.VISIBLE);
                } else {
                    woshiLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    woshi.setTextColor(getResources().getColor(R.color.my_tab));

                    wozhaoLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    wozhao.setTextColor(getResources().getColor(R.color.bbbbb));
                    yuleLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    yule.setTextColor(getResources().getColor(R.color.bbbbb));
                    jingjiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    jingji.setTextColor(getResources().getColor(R.color.bbbbb));
                    wozhaoTAG = false;
                    woshiTAG = true;
                    jingjiTAG = true;
                    yuleTAG = true;
                    shumaiLayout.setVisibility(View.GONE);
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                }
                break;
            case R.id.chouti_aa://费用承担方式 AA
                sxfeiyong = "1";
                if (aaTAG) {
                    aaLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    aa.setTextColor(getResources().getColor(R.color.my_tab));

                    shumaiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    shumai.setTextColor(getResources().getColor(R.color.bbbbb));
                    aaTAG = false;
                    shumaiTAG = true;
                } else {
                    aaLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    aa.setTextColor(getResources().getColor(R.color.bbbbb));

                    aaTAG = true;
                    sxfeiyong = "0";
                }

                break;
            case R.id.chouti_shumai://费用承担方式 输买
                sxfeiyong = "2";
                if (shumaiTAG) {
                    shumaiLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    shumai.setTextColor(getResources().getColor(R.color.my_tab));

                    aaLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    aa.setTextColor(getResources().getColor(R.color.bbbbb));
                    shumaiTAG = false;
                    aaTAG = true;
                } else {
                    shumaiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    shumai.setTextColor(getResources().getColor(R.color.bbbbb));
                    sxfeiyong = "0";
                    shumaiTAG = true;
                    sxmoshi = "0";
                }
                break;
            case R.id.chouti_nan://性别选择 男
                sxsex = "0";
                if (nanTAG) {
                    nanLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    nan.setTextColor(getResources().getColor(R.color.my_tab));

                    nvLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    nv.setTextColor(getResources().getColor(R.color.bbbbb));
                    nanTAG = false;
                    nvTAG = true;
                } else {
                    nanLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    nan.setTextColor(getResources().getColor(R.color.bbbbb));

                    sxsex = "2";
                    nanTAG = true;
                }
                break;
            case R.id.chouti_nv://性别选择 女
                sxsex = "1";
                if (nvTAG) {
                    nvLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                    nv.setTextColor(getResources().getColor(R.color.my_tab));

                    nanLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    nan.setTextColor(getResources().getColor(R.color.bbbbb));
                    nvTAG = false;
                    nanTAG = true;
                } else {
                    nvLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    nv.setTextColor(getResources().getColor(R.color.bbbbb));
                    sxsex = "2";
                    nvTAG = true;
                }
                break;
            case R.id.chouti_queding:
                showDialog();
                if (guilTAG == 1) {

                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, 0 + "", 0 + "", lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    LogU.Ld("1609", "筛选1" + "运动模式" + sxhuodong1 + sxmoshi + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + timesString[mhour] + "时间" + timesString[mminute] + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg);
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    if (timeTAG) {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day + timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    }
                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day + timesString[mhour], day + timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    LogU.Ld("1609", "筛选2" + "运动模式" + sxhuodong1 + sxmoshi + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + day + timesString[mhour] + "时间" + day + timesString[mminute] + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg);
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }

                break;

            case R.id.home_kbm:
                page = 1;
                if (kbmTag) {
                    if (token.equals("")) {
                        ToastUitl.longs("您还未登录");
                        intent.setClass(getContext(), DengluActivity.class);
                        startActivity(intent);
                    } else {
                        kbm.setBackgroundResource(R.mipmap.qbhd);
                        kbmTag = false;
                        joinCondition = "canJoined";

                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    }
                } else {

                    kbm.setBackgroundResource(R.mipmap.kbmhd);
                    kbmTag = true;
                    joinCondition = "";

                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);


                }
                break;

            case R.id.chouti_day_xx:

                timeTAG = false;
                showSelectDateDialog();


                break;
            case R.id.chouti_time:
                if (dayTAG == 0) {
                    Toast.makeText(getActivity(), "请先选择日期", Toast.LENGTH_SHORT).show();
                } else {
                    timeTAG = false;
                    showSelectTimeDialog();
                    dayTAG = 0;
                }

                break;

            case R.id.chouti_js_lv_min:
                showSelectLVDialog();
                break;
            case R.id.chouti_js_lv_max:
                showSelectLVDialog();
                break;

            case R.id.chouti_hp_one:
                if (!hp_one) {
                    hpTAg = 1;
                    chouti_hp_one.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.my_tab));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hp_one = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hpTAg = 0;
                    hp_one = false;
                }


                break;
            case R.id.chouti_hp_two:
                if (!hp_two) {
                    hpTAg = 2;
                    chouti_hp_two.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.my_tab));
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hp_two = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hpTAg = 0;
                    hp_two = false;
                }


                break;

            case R.id.chouti_hp_three:

                if (!hp_three) {
                    hpTAg = 3;
                    chouti_hp_three.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.my_tab));
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hp_three = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hpTAg = 0;
                    hp_three = false;
                }


                break;
            case R.id.chouti_hp_four:
                if (!hp_four) {
                    hpTAg = 4;
                    chouti_hp_four.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.my_tab));
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hp_four = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    hpTAg = 0;
                    hp_four = false;
                }


                break;

            case R.id.chouti_bm_hd:
                bmTAg = "joinCondition";

                chouti_bm_hd.setBackgroundResource(R.drawable.ellipse_home_details);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.my_tab));
                chouti_qb_hd.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.bbbbb));

                break;
            case R.id.chouti_qb_hd:
                bmTAg = "0";
                chouti_qb_hd.setBackgroundResource(R.drawable.ellipse_home_details);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.my_tab));
                chouti_bm_hd.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.bbbbb));
                break;
            case R.id.chouti_cz:
                guilTAG = 1;
                guiL();

                break;
        }

    }

    private void shuaxin() {

        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务
                        dingwei();
                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page + "");
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);


//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page + "");

                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                        dingbu.setVisibility(View.VISIBLE);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }

    //首页列表数据
    private void initDownload(final int page, String city2, int sxhuodong1, int sxhuodong2, String sxmoshi, String sxfeiyong, String sxsex, String day, String time, int lv, int lv2, int range, int praise, int acitvitysort, String mLatitude1, String mLongitude1, String bmTAg) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "首页列表活动=====" + token + "运动项目" + sxhuodong1 + "SportMode+++" + sxmoshi + "娱乐模式payType:" + sxfeiyong + "费用承担方式SportType:" + sxhuodong2 + "性别sex:" + sxsex
                + "智能排序acitvitysort:" + sxzhineng + "筛选状态" + sxzhuangtai + "城市city:" + city + "地区是：" + area + "经度lat：" + mLatitude + "纬度lng：" + mLongitude + "错误码" + locType + "joinCondition" + joinCondition + "startTime:"
                + timesString[mhour] + "endTime:" + timesString[mminute] + "range:" + jlTAG + "praise是：" + hpTAg + "mingrade是：" + lv + "maxgrade是：" + lv2);

        if (timeTAG) {
            timesString[mhour] = "0";
            timesString[mminute] = "0";
            day = "0";
            time = "0";

        }
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getIndexAcitivitylist")
                .addHeader("token", token)
                .addParams("page", page + "")
                .addParams("city", city)
                .addParams("sportid", sxhuodong1 + "") //运动项目
                .addParams("SportType", sxhuodong2 + "")//运动项目二级分类
                .addParams("SportMode", sxmoshi) //运动模式
                .addParams("payType", sxfeiyong) //场地费支付方式
                .addParams("sex", sxsex)//性别
                .addParams("startTime", day)//开始时间
                .addParams("endTime", time)//结束时间
                .addParams("range", jlTAG + "") //范围
                .addParams("praise", hpTAg + "") //报名人的好评
                .addParams("joinCondition", bmTAg)  //活动状态 可报名0 全部1
                .addParams("acitvitysort", acitvitysort + "")//推荐排序
                .addParams("mingrade", lv + "") //报名人最小平均等级
                .addParams("maxgrade", lv2 + "") //报名最大平均等级
                .addParams("lat", mLatitude + "") //经度
                .addParams("lng", mLongitude + "") //纬度
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "首页列表" + result);
                        progressDialog.cancel();
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {

                            Gson gson = new Gson();
                            HomeListEntity entity = gson.fromJson(result, HomeListEntity.class);
                            List<HomeListEntity.DataBean.ActiveLstBean> list;
                            list = entity.getData().getActiveLst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
//                            layoutParams.height = 1450;
//                            listView.getRefreshableViewWrapper().setLayoutParams(layoutParams);
                            listView.setVisibility(View.VISIBLE);
                            if (!EmptyUtils.isEmpty(entity)) {
                                LogU.Le("1608", "是空不" + EmptyUtils.isEmpty(list));
                                if (page == 1) {
                                    data.clear();
                                    data.addAll(list);
//                                    listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    listView.onRefreshComplete();
                                } else {
                                    data.addAll(list);
//                                listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    listView.onRefreshComplete();
                                }
                                listViewJC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if (position <= 2) {

                                        } else {
                                            uuid = data.get(position - 3).getUuid();
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(getContext(), HomeHDXQActivity.class);
                                            bundle.putString("tab", "0");
                                            bundle.putString("uuid", uuid);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }

                                    }
                                });
                                mlocationClient.stop();
                                lunbo();

                            } else {
                                return;
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    //轮播
    private void lunbo() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getIndexBanner")
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
                            final LunboEntity entity = gson.fromJson(result, LunboEntity.class);
                            final List<LunboEntity.DataBean> list;
                            list = entity.getData();
                            final List<String> lists = new ArrayList<>();

                            for (int i = 0; i < list.size(); i++) {
                                lists.add(getResources().getString(R.string.imgurl) + list.get(i).getPicurl());

                            }

                            mXBanner.setData(lists, null);
                            mXBanner.setmAdapter(new XBanner.XBannerAdapter() {
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    Glide.with(getActivity()).load(lists.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into((ImageView) view);
                                }
                            });
                            mXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                                @Override
                                public void onItemClick(XBanner banner, Object model, View view, int position) {
                                    Intent intent = new Intent();

                                    if (list.get(position).getDesc().equals("1")) {
                                        intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                                        spUtileFQTZ.clear(getContext());
                                    } else if (list.get(position).getDesc().equals("2")) {
                                        intent.setClass(getContext(), RenWuActivity.class);
                                    } else if (list.get(position).getDesc().equals("3")) {
                                        intent.setClass(getContext(), MyPromoterActivity.class);
                                    } else if (list.get(position).getDesc().equals("4")) {
                                        intent.setClass(getContext(), HZCGActivity.class);
                                    }

                                    startActivity(intent);
                                }
                            });

//                            lists.add("http://seopic.699pic.com/photo/50076/8023.jpg_wh1200.jpg");
                            //                          banner.setImages(lists);
                            //                        banner.start();

                       /*    banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    Intent intent = new Intent();

                                    if (list.get(position).getDesc().equals("1")){
                                        intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                                        spUtileFQTZ.clear(getContext());
                                    }else  if(list.get(position).getDesc().equals("2")){
                                        intent.setClass(getContext(), RenWuActivity.class);
                                    }else  if(list.get(position).getDesc().equals("3")){
                                        intent.setClass(getContext(), MyPromoterActivity.class);
                                    }else  if(list.get(position).getDesc().equals("4")){
                                        intent.setClass(getContext(), HZCGActivity.class);
                                    }

                                    startActivity(intent);
                                }
                            }).start();*/
                            jiancexiaoxi();
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //活动筛选数据
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
                            entity = gson.fromJson(result, YundongEntity.class);
                            List<YundongEntity.DataBean> list1;
                            list1 = entity.getData();
                            sportList.clear();
                            sportList.addAll(list1);
                            recyclerView.setAdapter(spiner);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //签到
    private void qiandao() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userSignIn")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "签到" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            HomeDialog dialog = new HomeDialog(getContext(),"55");
//                            dialog.show();
//                            panduan();
                            initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

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
//                            jinbi.setText("通用金币" + entity.getData().getCoins() + "个");
                            HomeDialog dialog = new HomeDialog(getContext(), entity.getData().getCoins() + "");
                            dialog.show();
                            panduan();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {
                                Intent intent = new Intent();
                                intent.setClass(getContext(), DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //检测消息
    private void jiancexiaoxi() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNotReadMessageCount")
                .addHeader("token", token)
                .addParams("msgCate", "systems ")
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
                            if (entity.getData().getNotReadCount() > 0) {
//                                weidu.setVisibility(View.VISIBLE);

                            } else {
                                weidu.setVisibility(View.GONE);
                            }
                            panduan();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs(entity.getMsg());

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
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
                            intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                            spUtileFQTZ.clear(getContext());
                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (entity.getMsg().equals("登录超时")) {
                                ToastUitl.longs("您还未登录");
                                Intent intent = new Intent();
                                intent.setClass(getContext(), DengluActivity.class);
                                startActivity(intent);
                            } else {
                                showNormalDialog();
                            }


                        }
                    }
                });

    }

    //判断签到
    private void panduan() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "判断签到" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserIsSign")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "判断签到" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PanduanQiandanEntity entity = gson.fromJson(result, PanduanQiandanEntity.class);

                            if (entity.getData().getIsSign() == 1) {
                                //已签到
                                qiandaoImage.setBackgroundResource(R.mipmap.yiqiandao);
                                qiandaoText.setText("已签到");
                            } else {
                                qiandaoImage.setBackgroundResource(R.mipmap.qiandao);
                                qiandaoText.setText("签到");
                            }

                        } else {

                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getActivity(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //坐标  http://api.map.baidu.com/geocoder?address=地址&output=输出格式类型&key=用户密钥&city=城市名
    private void zuobiao(final String address) {
        LogU.Ld("1608", "场馆信息");
        OkHttpUtils
                .get()
                .url("http://api.map.baidu.com/geocoder")
                .addParams("address", address)
                .addParams("output", "json")
                .addParams("key", "0xRmvPDRXDbj1Ql18OgKQIZpT6jmCTnH")
                .addParams("city", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "坐标" + result);
                        Boolean a = result.indexOf("2000") != -1;
//                        sousuo("国贸","北京市");
//                        if (a) {
                        Gson gson = new Gson();
                        ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);
                        //  initDownload(page, address, 0, 0, 0, 0, 0, entity.getResult().getLocation().getLat() + "", entity.getResult().getLocation().getLng() + "", joinCondition);
                        initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, entity.getResult().getLocation().getLat() + "", entity.getResult().getLocation().getLng() + "", bmTAg);

//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                        }
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

    //定位地址
    private void dingwei() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        mlocationClient = new LocationClient(getActivity());
        mlistener = new MylocationListener();

        //注册监听器
        mlocationClient.registerLocationListener(mlistener);
        //注册监听器
//        mlocationClient.registerLocationListener(mlistener);
        //配置定位SDK各配置参数，比如定位模式、定位时间间隔、坐标系类型等
        LocationClientOption mOption = new LocationClientOption();
        //设置坐标类型
        mOption.setCoorType("bd09ll");
        //设置是否需要地址信息，默认为无地址
        mOption.setIsNeedAddress(true);
        //设置是否打开gps进行定位
        mOption.setOpenGps(true);

        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
        int span = 100000;
        mOption.setScanSpan(span);
        //设置 LocationClientOption
        mlocationClient.setLocOption(mOption);

    }


//    @Override
//    public void onScroll(int scrollY) {
//        int mHeight = mTabViewLayout.getTop();
//
//        //判断滑动距离scrollY是否大于0，因为大于0的时候就是可以滑动了，此时mTabViewLayout.getTop()才能取到值。
//        if (scrollY > 0 && scrollY >= mHeight) {
//            if (mTopView.getParent() != mTopTabViewLayout) {
//                mTabViewLayout.removeView(mTopView);
//                mTopTabViewLayout.addView(mTopView);
//            }
//
//        } else {
//            if (mTopView.getParent() != mTabViewLayout) {
//                mTopTabViewLayout.removeView(mTopView);
//
//                mTabViewLayout.addView(mTopView);
//
//            }
//
//        }
//    }


    //所有的定位信息都通过接口回调来实现
    public class MylocationListener implements BDLocationListener {
        //定位请求回调接口
        private boolean isFirstIn = true;


        //定位请求回调函数,这里面会得到定位信息
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();
            city = bdLocation.getCity();
            area = bdLocation.getDistrict();
            province = bdLocation.getProvince();
            locType = bdLocation.getLocType();
            spUtils.put(getActivity(), "city", city);
            spUtils.put(getActivity(), "area", area);
            LogU.Ld("1608", "城市" + city + "纬度" + mLongitude + "错误码" + bdLocation.getLocType());
            if (isFirstIn) {
                isFirstIn = false;

//                Toast.makeText(getActivity(), bdLocation.getAddrStr()+"大大大"+bdLocation.getCity()+bdLocation.getDistrict(), Toast.LENGTH_SHORT).show();
                LogU.Ld("1608", "经度" + mLatitude + "纬度" + mLongitude);
                spUtils.put(getActivity(), "mylat", mLatitude);
                spUtils.put(getActivity(), "mylng", mLongitude);
                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

            }

            chengshiText.setText(city);

            mlocationClient.stop();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开启定位
//        mBaiduMap.setMyLocationEnabled(true);
        if (!mlocationClient.isStarted()) {
            mlocationClient.start();
        }

//        myOrientationListener.start();
        //   initDownload(page, city, 0,0, mLatitude + "", mLongitude + "", joinCondition);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        dingwei();
        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day, time, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

    }

    /**
     * 加载数据的方法，只要保证isPrepared和isVisible都为true的时候才往下执行开始加载数据
     */
    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if (!isPrepared || !isVisible) {
            return;
        }
        //TODO 此处填充view中各个控件的数据
    }

    private void showSelectDateDialog() {

        mSelectDateDialog.setOnClickListener(new SelectDateDialog.OnClickListener() {
            @Override
            public boolean onSure(int mYear, int mMonth, int mDay, long time) {


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());

                LogU.Ld("1609", "时间" + mYear + "月" + mMonth + "天" + mDay + "时" + time);
                try {
                    String format = dateFormat.format(time);
                    String format1 = dateFormat.format(date);
                    Date parse = dateFormat.parse(format);
                    Date parse1 = dateFormat.parse(format1);
                    long time2 = parse.getTime();
                    long time1 = parse1.getTime();
                    LogU.Ld("1609", "时间" + time1 + "月" + time2);
                    if (time2 >= time1) {
                        LogU.Ld("1609", "时间" + mYear + "月" + mMonth + "天" + mDay + "时" + time);
                        chouti_day_text.setText(dateFormat.format(time));
                        day = chouti_day_text.getText().toString();
                        LogU.Ld("1609", "时间" + day + time);
                        chouti_dayx.setBackgroundResource(R.drawable.ellipse_home_details);
                        chouti_day_text.setTextColor(getResources().getColor(R.color.my_tab));
                        dayTAG = 1;
                    } else {
                        Toast.makeText(getActivity(), "时间选择错误", Toast.LENGTH_LONG).show();
                        return true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                chouti_day_text.setText(dateFormat.format(time));
                day = chouti_day_text.getText().toString();
                LogU.Ld("1609", "时间" + day + time);
                chouti_dayx.setBackgroundResource(R.drawable.ellipse_home_details);
                chouti_day_text.setTextColor(getResources().getColor(R.color.my_tab));

                return false;
            }

            @Override
            public boolean onCancel() {
                chouti_dayx.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_day_text.setTextColor(getResources().getColor(R.color.bbbbb));
                chouti_day_text.setText("选择日期");
                day = "0";
                return false;
            }
        });
        mSelectDateDialog.show(2019, 11, 17);
    }

    private void showSelectTimeDialog() {
        SelectTimeDialog mSelectTimeDialog = new SelectTimeDialog(getActivity(), new SelectTimeDialog.OnClickListener() {
            @Override
            public boolean onSure(int hour, int minute, int setTimeType) {
                mhour = hour;
                mminute = minute;
                chouti_tiem_text.setText(timesString[hour] + "-" + timesString[minute]);
                time = chouti_tiem_text.getText().toString();
                LogU.Ld("1609", "时间" + time);
                chouti_time.setBackgroundResource(R.drawable.ellipse_home_details);
                chouti_tiem_text.setTextColor(getResources().getColor(R.color.my_tab));

                return false;
            }

            @Override
            public boolean onCancel() {
                chouti_tiem_text.setText("选择时间");
                chouti_time.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_tiem_text.setTextColor(getResources().getColor(R.color.bbbbb));

                return false;
            }
        });
        mSelectTimeDialog.show(timesString[mhour], timesString[mminute], 1);
    }

    private void initValue() {
        timesString = getResources().getStringArray(R.array.times);
    }

    private void initValueLv() {
        lVleArray = getResources().getStringArray(R.array.Lvle);
    }

    private void showSelectLVDialog() {
        SelectLVDialog mSelectLvDialog = new SelectLVDialog(getActivity(), new SelectLVDialog.OnClickListener() {
            @Override
            public boolean onSure(int hour, int minute, int setTimeType) {
                lv = hour;
                lv2 = minute;
                if (hour != 0 || minute != 0) {
                    if (hour > minute) {
                        Toast.makeText(getActivity(), "最高级别不能低于最低级别", Toast.LENGTH_LONG).show();
                    } else {
                        chouti_js_lv_min_text.setText(lVleArray[hour] + "级");
                        chouti_js_lv_max_text.setText(lVleArray[minute] + "级");
                        chouti_js_lv_min.setBackgroundResource(R.drawable.ellipse_home_details);
                        chouti_js_lv_max.setBackgroundResource(R.drawable.ellipse_home_details);
                        chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.my_tab));
                        chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.my_tab));
                        Toast.makeText(getActivity(), lVleArray[hour] + "-" + lVleArray[minute], Toast.LENGTH_LONG).show();
                    }
                } else {
                    chouti_js_lv_min_text.setText(lVleArray[hour]);
                    chouti_js_lv_max_text.setText(lVleArray[minute]);
                    chouti_js_lv_min.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_js_lv_max.setBackgroundResource(R.drawable.ellipse_home_details);
                    chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.my_tab));
                    chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.my_tab));
                    Toast.makeText(getActivity(), lVleArray[hour] + "-" + lVleArray[minute], Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onCancel() {
                chouti_js_lv_min_text.setText("不限");
                chouti_js_lv_max_text.setText("不限");
                chouti_js_lv_min.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_js_lv_max.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
                chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
                return false;
            }
        });
        mSelectLvDialog.show(lVleArray[lv], lVleArray[lv2], 1);
    }

    public void guiL() {

        chouti_bm_hd.setBackgroundResource(R.drawable.ellipse_home_details);
        chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.my_tab));
        chouti_qb_hd.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.bbbbb));
        bmTAg = "joinCondition";
        chouti_hp_one.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_hp_one_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_hp_two.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_hp_two_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_hp_three.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_hp_three_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_hp_four.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_hp_four_text.setTextColor(getResources().getColor(R.color.bbbbb));
        hpTAg = 0;

        yuleLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        yule.setTextColor(getResources().getColor(R.color.bbbbb));
        jingjiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        jingji.setTextColor(getResources().getColor(R.color.bbbbb));
        woshiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        woshi.setTextColor(getResources().getColor(R.color.bbbbb));
        wozhaoLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        wozhao.setTextColor(getResources().getColor(R.color.bbbbb));
        sxmoshi = "0";

        aaLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        aa.setTextColor(getResources().getColor(R.color.bbbbb));
        shumaiLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        shumai.setTextColor(getResources().getColor(R.color.bbbbb));
        sxfeiyong = "0";

        nanLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        nan.setTextColor(getResources().getColor(R.color.bbbbb));
        nvLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        nv.setTextColor(getResources().getColor(R.color.bbbbb));
        sxsex = "2";

        chouti_tiem_text.setText("选择时间");
        chouti_time.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_tiem_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_dayx.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_day_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_day_text.setText("选择日期");
        timesString[mhour] = "0";
        timesString[mminute] = "0";

        chouti_js_lv_min_text.setText("不限");
        chouti_js_lv_max_text.setText("不限");
        chouti_js_lv_min.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_js_lv_max.setBackgroundResource(R.drawable.xiugai_huodong_layout);
        chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
        lVleArray[lv] = "0";
        lVleArray[lv2] = "0";
    }


    private void getList() {
        listPopu = new ListPopu(getActivity(), tJList, R.layout.spiner_window_layout_item);
        //设置PopWindow显示的位置
        listPopu.showPopupWindow(line);
        //条目点击监听 推荐排序
        listPopu.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Object obj, int position) {

                if (tJList.get(position).equals("距离由近到远")) {
                    acitvitysort = 0;
                }
                if (tJList.get(position).equals("时间由近到远")) {
                    acitvitysort = 1;
                }
                if (tJList.get(position).equals("级别由高到低")) {
                    acitvitysort = 2;
                }
                if (tJList.get(position).equals("级别由低到高")) {
                    acitvitysort = 3;
                }
                if (tJList.get(position).equals("好评优先")) {
                    acitvitysort = 4;
                }

                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                Toast.makeText(getActivity(), acitvitysort + "===" + tJList.get(position), Toast.LENGTH_LONG).show();
                listPopu.dismiss();
                listPopu = null;
            }
        });
        //监听PopupWindow消失
        listPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //   setTextDrawable(R.mipmap.xiajiantou,xiangmuText);
                //  setTextDrawable(R.mipmap.xiajiantou,zhinengText);
                //setTextDrawable(R.mipmap.xiajiantou,home_text3);
            }
        });

    }

    //距离范围
    private void getJLList() {

        listPopuJL = new ListPopuJL(getActivity(), jlList, R.layout.spiner_window_layout_item);
        listPopuJL.showPopupWindow(line);

        //距离范围
        listPopuJL.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(Object obj, int position) {

                if (jlList.get(position).equals("1km")) {
                    jlTAG = 1;
                }
                if (jlList.get(position).equals("2km")) {
                    jlTAG = 2;
                }
                if (jlList.get(position).equals("4km")) {
                    jlTAG = 4;
                }
                if (jlList.get(position).equals("10km")) {
                    jlTAG = 10;
                }
                initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);


                Toast.makeText(getActivity(), hpTAg + "===" + jlList.get(position), Toast.LENGTH_LONG).show();
                listPopuJL.dismiss();
                listPopuJL = null;
            }
        });
        //监听PopupWindow消失
        listPopuJL.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //   setTextDrawable(R.mipmap.xiajiantou,xiangmuText);
                //  setTextDrawable(R.mipmap.xiajiantou,zhinengText);
                //setTextDrawable(R.mipmap.xiajiantou,home_text3);
            }
        });
    }


    class ListPopu extends SpinerPopWindow<String> {

        public ListPopu(Context context, List<String> list, int resId) {
            super(context, list, resId);
        }

        @Override
        public void setData(SpinerAdapter.SpinerHolder holder, int position) {
            TextView item = holder.getView(R.id.item);
            String s = tJList.get(position);
            item.setText(s);


        }
    }

    class ListPopuJL extends SpinerPopWindow<String> {

        public ListPopuJL(Context context, List<String> list, int resId) {
            super(context, list, resId);
        }

        @Override
        public void setData(SpinerAdapter.SpinerHolder holder, int position) {
            TextView item = holder.getView(R.id.item);
            String jul = jlList.get(position);
            item.setText(jul);

        }
    }


    /**
     * 设置textview右边显示图片
     */
    private void setTextDrawable(int imageid, TextView tv) {
        Drawable drawable = getResources().getDrawable(imageid);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 初始化数据 距离范围
     */
    private void initData() {
        //添加数据
        jlList = new ArrayList<>();
        for (int i = 0; i < juli.length; i++) {
            jlList.add(juli[i]);
        }
        tJList = new ArrayList<>();
        for (int i = 0; i < tuijian.length; i++) {
            tJList.add(tuijian[i]);
        }
        sportList = new ArrayList<>();
        sPorttwo = new ArrayList<>();

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
                            List<YundongEntity.DataBean> list1;
                            list1 = entity.getData();
                            sportList.clear();
                            sportList.addAll(list1);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View view = getLayoutInflater().inflate(R.layout.spiner_window_layout_xm, null);
        if (popupBigPhoto == null) {
            popupBigPhoto = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
            popupBigPhoto.setOutsideTouchable(true);

        }

        //设置PopupWindow宽高
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupBigPhoto.setWidth(display.getWidth());
        popupBigPhoto.setHeight(display.getHeight());
        //设置背景
        ColorDrawable dw = new ColorDrawable(0x60000000);
        popupBigPhoto.setBackgroundDrawable(dw);
        popupBigPhoto.setOutsideTouchable(true);
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            line.getGlobalVisibleRect(rect);
            int h = line.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            popupBigPhoto.setHeight(h);
        }
        if (!popupBigPhoto.isShowing()) {
            popupBigPhoto.showAsDropDown(line);
        } else {
            popupBigPhoto.dismiss();
        }

        TextView textall = view.findViewById(R.id.text_all);
        TextView text_all_two = view.findViewById(R.id.text_all_two);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_left);
        recyclerView_right = (RecyclerView) view.findViewById(R.id.recycler_view_right);
        textall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                popupBigPhoto.dismiss();
            }
        });

        text_all_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                popupBigPhoto.dismiss();
            }
        });
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_right.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        spiner = new PopAdapter(R.layout.spiner_windowxm_layout_item, sportList);
        spinerAdapter = new PopAdapterTwo(R.layout.spiner_window_layout_xm_item, sPorttwo);

        huoquyundong();


        spiner.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                anInt = sportList.get(position).getId();
                entity.getData().get(position).setSelect(true);

                LogU.Ld("1608", "运动项目" + position);
                OkHttpUtils
                        .get()
                        .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getDatialSport")
                        .addParams("id", +sportList.get(position).getId() + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                String result = response.toString();
                                LogU.Ld("1608", "运动项目二级" + result);
                                Boolean a = result.indexOf("2000") != -1;

                                if (a) {
                                    Gson gson = new Gson();
                                    YundongTwoEntity entity = gson.fromJson(result, YundongTwoEntity.class);

                                    list = entity.getData();
                                    sPorttwo.clear();
                                    sPorttwo.addAll(list);
                                    recyclerView_right.setAdapter(spinerAdapter);
                                    spinerAdapter.notifyDataSetChanged();
                                    LogU.Ld("1608", "点击运动" + sPorttwo.toString());

                                } else {
                                    Gson gson = new Gson();
                                    JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                    Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

        spinerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sxhuodong1 = sportList.get(position).getId();
                sxhuodong2 = sPorttwo.get(position).getId();
                initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);


                popupBigPhoto.dismiss();
            }
        });
    }

    //进度条
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        progressDialog.setMessage("加载中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
