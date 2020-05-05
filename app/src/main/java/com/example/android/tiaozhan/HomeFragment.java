package com.example.android.tiaozhan;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.tiaozhan.Adapter.HomeListAdapter;
import com.example.android.tiaozhan.Adapter.HomeShaixuanOne;
import com.example.android.tiaozhan.Adapter.HomeShaixuanTwoAdapter;
import com.example.android.tiaozhan.Adapter.HomeShanxuanThreeAdapter;
import com.example.android.tiaozhan.Adapter.PopAdapter;
import com.example.android.tiaozhan.Adapter.PopAdapterTwo;
import com.example.android.tiaozhan.Adapter.pupadapter.ListDropDownAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Denglu.GRXXActivity;
import com.example.android.tiaozhan.Entity.AllActiveCountEntity;
import com.example.android.tiaozhan.Entity.HomeListEntity;
import com.example.android.tiaozhan.Entity.InitRefereeEntity;
import com.example.android.tiaozhan.Entity.JCXIEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.JudgerefereeJEntity;
import com.example.android.tiaozhan.Entity.LunboEntity;
import com.example.android.tiaozhan.Entity.PanduanQiandanEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.Entity.YundongTwoEntity;
import com.example.android.tiaozhan.Entity.ZuobiaoEntity;
import com.example.android.tiaozhan.Home.FaqiTiaozhanActivity;
import com.example.android.tiaozhan.Home.HZCGActivity;
import com.example.android.tiaozhan.Home.HomeHDXQActivity;
import com.example.android.tiaozhan.Home.HomeSousuoActivity;
import com.example.android.tiaozhan.Home.RenWuActivity;
import com.example.android.tiaozhan.Home.ShopActivity;
import com.example.android.tiaozhan.My.referee.MyCwRefereeActivity;
import com.example.android.tiaozhan.My.referee.RefereePerfectXXActivity;
import com.example.android.tiaozhan.Promoter.MyPromoterActivity;
import com.example.android.tiaozhan.Promoter.PromoterErrorActivity;
import com.example.android.tiaozhan.Promoter.PromoterONEActivity;
import com.example.android.tiaozhan.Promoter.PromoterTWOActivity;
import com.example.android.tiaozhan.Home.XiaoxiActivity;
import com.example.android.tiaozhan.My.MyhuodongActivity;
import com.example.android.tiaozhan.Toos.BaseFragment;
import com.example.android.tiaozhan.Toos.CustomPopwindow;
import com.example.android.tiaozhan.Toos.CustomPopwindowTY;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.GlideImageLoader;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.NetUtil;
import com.example.android.tiaozhan.Toos.NetUtilTwo;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.cityselectordemo.activity.CityListActivity;
import com.example.android.tiaozhan.Toos.mypicker.AgePickerDialog;
import com.example.android.tiaozhan.Toos.mypicker.NLPickerDialog;
import com.example.android.tiaozhan.Toos.pup.SpinerPopWindow;
import com.example.android.tiaozhan.Toos.time.dialog.SelectDateDialog;
import com.example.android.tiaozhan.Toos.time.dialog.SelectTimeDialog;
import com.example.android.tiaozhan.bean.SportEntity;
import com.example.android.tiaozhan.bean.SportTwoEntity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jaeger.library.StatusBarUtil;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;

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
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private List<String> listNL1_xx = new ArrayList<>();
    private List<String> listNL2_xx = new ArrayList<>();

    private List<String> listJB1_xx = new ArrayList<>();
    private List<String> listJB2_xx = new ArrayList<>();


    private static final String[] PERMS_HOME = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int CODE_PERM = 123;
    private int locType;
    private Dialog dialog;
    private TextView ds_xz;
    private PoiSearch mPoiSearch = null;
    private PopupWindow popupBigPhoto, popupBig, popupBigTJ;
    private List<String> listP;

    private ListPopuJL listPopuJL;
    private View line;
    private LocationClient mlocationClient;
    private MylocationListener mlistener;
    private double mLatitude, mLatitude1, mLatitude2;
    private double mLongitude, mLongitude1, mLongitude2;
    private String city, area, uuid, province, content, souTag, joinCondition = "", citys;
    private Banner banner;
    private ArrayAdapter arr_adapter;
    private ArrayList data_list;
    private HomeListAdapter adapter;
    private HomeShaixuanOne adapter2;
    private PullToRefreshListView listView;
    private ProgressDialog progressDialog;
    private ListView listViewJC;
    private boolean cpdTag = false, qbTag = true, kbTag = true;
    private LinearLayout qiandao, yingjinbi, duihuanSC, wenzhang, hzcg, chengshiLayout;
    private RelativeLayout shaixuan1, shaixuan2, shaixuan3, shaixuan4, sousuo_layout;
    private boolean tag = true, qubTag = false, yuleTAG = true, jingjiTAG = true, wozhaoTAG = true, woshiTAG = true,
            aaTAG = true, shumaiTAG = true, nanTAG = true, nvTAG = true, kbmTag = true, timeTAG = true, minTAG = true, maxTAG = true, oneTAG = true, twoTAG = true, threeTAG = true, fourTAG = true, qbhdTAG = true, bmTAG = true;
    private DrawerLayout drawerLayout;
    private ImageView xiaoxi, faqitiaozhan, huodong, weidu, qiandaoImage, dingbu, kbm, huodong_weidu;
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
    private TextView qiandaoText, chouti_qb_hd_text, chouti_bm_hd_text, chouti_cp_hd_text;

    private TextView chouti_hp_four_text, chouti_hp_one_text, chouti_hp_two_text, chouti_hp_three_text, chouti_js_lv_min_text, chouti_js_lv_max_text, chouti_day_text, chouti_tiem_text, nl_min_text, nl_max_text;

    private String sxmoshi = "0", sxfeiyong = "0", sxsex = "2", sxzhineng = "0", sxzhuangtai = "0", tuiguang;
    //抽屉布局
    private ImageView home_image4, home_image1, home_image2, home_image3;
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

    private RelativeLayout chouti_time, chouti_cz, chouti_js_lv_max, chouti_js_lv_min, chouti_hp_one, chouti_hp_two, chouti_hp_three, chouti_hp_four, chouti_bm_hd, chouti_qb_hd, chouti_cp_hd, chouti_day2, chouti_js_nl_min, chouti_js_nl_max;

    private String[] lVleArray;
    private int mhour, mminute, hpTAg = 0, guilTAG = 0, timeTag = 0;
    private boolean hp_one = true, hp_two = true, hp_three = true, hp_four = true;
    private String bmTAg = "canJoined", jlTAG = "0", acitvitysort = "0", souAddress;
    private List<YundongTwoEntity.DataBean> list;
    private List<YundongEntity> list1;
    private String headers[] = {"运动项目", "距离范围", "推荐排序", "筛选"};

    private ListDropDownAdapter ageAdapter;
    private List<View> popupViews = new ArrayList<>();

    private String tuijian[] = {"距离由近到远", "时间由近到远", "级别由高到低", "级别由低到高", "好评优先"};
    private List<String> jlList;
    private List<String> tJList;

    private List<YundongEntity.DataBean> sportList;
    private TextView item, text_shuju, sport_mos;
    private List<SportTwoEntity.ParentBean.ChildBean> sPorttwo;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_right;
    private PopAdapter spiner;
    private PopAdapterTwo spinerAdapter;
    private int anInt;
    private YundongEntity entity;
    private String day = "0", days = "0";
    private String time = "0", times = "0";
    private LinearLayout layout_right;
    private String name;
    private RelativeLayout headerlayout;

    private ImageView but_qfb;
    private long time2;
    private Date parse;
    private Dialog chooseDialog, chooseDialogJB;
    private String Agemin = "0", Agemax = "0", lv = "0", lv2 = "0";
    private CustomPopwindow customPopwindow;

    private CustomPopwindowTY customPopwindowTY;
    private String citysw;
    private String city1;
    private String nameCS;
    private long time1;
    private String lunB;
    private int nowPage;
    private String sport;
    private String sport2;
    private List<SportEntity> sport1;
    private ArrayList<SportTwoEntity.ParentBean> parentBeans;
    private ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansY;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //  chengshiText.setText(city);


        //  spUtileFQTZ.remove(getActivity(),"cityw");
        LogU.Ld("1611", "搜索城市" + citys + "====" + city + souTag);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        View header2 = View.inflate(getContext(), R.layout.header2, null);
        View header1 = View.inflate(getContext(), R.layout.header1, null);

        View header4 = View.inflate(getContext(), R.layout.wu_hd_layout, null);

        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.tab_backgroud), 0);
        isPrepared = true;
      //  setlazyLoad();
        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        token = (String) spUtils.get(getContext(), "logintoken", "");
        tuiguang = (String) spUtils.get(getContext(), "tuiguang", "0");


        banner = header1.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(8000);


        huodong_weidu = rootView.findViewById(R.id.huodong_weidu);
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

        text_shuju = header4.findViewById(R.id.text_shuju);


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
        //  xiaoxi.setVisibility(View.GONE);
        faqitiaozhan = rootView.findViewById(R.id.home_faqitiaozhan);
        faqitiaozhan.setOnClickListener(this);
        huodong = rootView.findViewById(R.id.home_huodong);
        huodong.setOnClickListener(this);
        kbm = rootView.findViewById(R.id.home_kbm);
        kbm.setOnClickListener(this);

        //运动模式
        sport_mos = rootView.findViewById(R.id.sport_mos);
        sport_mos.setOnClickListener(this);

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

        chouti_day_text = rootView.findViewById(R.id.chouti_day2_text);
        chouti_tiem_text = rootView.findViewById(R.id.chouti_tiem_text);


        chouti_day2 = rootView.findViewById(R.id.chouti_day2);
        chouti_day2.setOnClickListener(this);
        chouti_time = rootView.findViewById(R.id.chouti_time);
        chouti_time.setOnClickListener(this);
        chouti_js_lv_min = rootView.findViewById(R.id.chouti_js_lv_min);
        chouti_js_lv_min.setOnClickListener(this);
        chouti_js_lv_max = rootView.findViewById(R.id.chouti_js_lv_max);
        chouti_js_lv_max.setOnClickListener(this);


        chouti_js_nl_min = rootView.findViewById(R.id.chouti_js_nl_min);
        chouti_js_nl_min.setOnClickListener(this);
        chouti_js_nl_max = rootView.findViewById(R.id.chouti_js_nl_max);
        chouti_js_nl_max.setOnClickListener(this);

        nl_min_text = rootView.findViewById(R.id.chouti_js_nl_min_text);
        nl_max_text = rootView.findViewById(R.id.chouti_js_nl_max_text);

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

        chouti_cp_hd = rootView.findViewById(R.id.chouti_cp_hd);
        chouti_cp_hd.setOnClickListener(this);

        chouti_cp_hd_text = rootView.findViewById(R.id.chouti_cp_hd_text);
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

        sousuo_layout = rootView.findViewById(R.id.sousuo_layout);
        sousuo_layout.setOnClickListener(this);
        sousuo = rootView.findViewById(R.id.home_sousuo);


        xiangmuText = header2.findViewById(R.id.home_text1);
        zhinengText = header2.findViewById(R.id.home_text2);
        zhuangtaiText = header2.findViewById(R.id.home_text3);
        shaixuan = header2.findViewById(R.id.home_text4);
//            listView.setVisibility(View.GONE);
        home_image1 = header2.findViewById(R.id.home_image1);
        home_image2 = header2.findViewById(R.id.home_image2);
        home_image3 = header2.findViewById(R.id.home_image3);
        home_image4 = header2.findViewById(R.id.home_image4);

        datashai = new ArrayList<>();

        data = new ArrayList<>();
        adapter = new HomeListAdapter(getContext(), data);

        sport1 = new ArrayList<>();
        SportEntity sportEntity1 = new SportEntity(1, "羽毛球");
        SportEntity sportEntity2 = new SportEntity(2, "乒乓球");
        SportEntity sportEntity3 = new SportEntity(3, "台球");
        SportEntity sportEntity4 = new SportEntity(4, "篮球");
        SportEntity sportEntity5 = new SportEntity(5, "足球");
        SportEntity sportEntity6 = new SportEntity(6, "排球");
        SportEntity sportEntity7 = new SportEntity(7, "网球");
        SportEntity sportEntity8 = new SportEntity(8, "高尔夫");
        sport1.add(sportEntity1);
        sport1.add(sportEntity2);
        sport1.add(sportEntity3);
        sport1.add(sportEntity4);
        sport1.add(sportEntity5);
        sport1.add(sportEntity6);
        sport1.add(sportEntity7);
        sport1.add(sportEntity8);
        List<SportTwoEntity> sportTwo = new ArrayList<>();
        String title1[] = {"单打", "双打"};//羽毛球
        String title2[] = {"单打", "双打"};//乒乓球
        String title3[] = {"中式黑八", "美式9球", "斯诺克"}; //台球
        String title4[] = {"5V5", "3V3"};// 篮球
        String title5[] = {"11人制", "8人制", "7人制", "5人制"};// 足球
        String title6[] = {"6V6"};//排球
        String title7[] = {"单打", "双打"};//网球
        String title8[] = {"比杆赛", "比洞赛"};//高尔夫
        parentBeans = new ArrayList<>();

        childBeansY = new ArrayList<>();
        childBeansY.add(new SportTwoEntity.ParentBean.ChildBean(4, 2, "单打", 1, 1));
        childBeansY.add(new SportTwoEntity.ParentBean.ChildBean(5, 2, "双打", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("羽毛球", 1, childBeansY));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansP = new ArrayList<>();
        childBeansP.add(new SportTwoEntity.ParentBean.ChildBean(6, 2, "单打", 1, 1));
        childBeansP.add(new SportTwoEntity.ParentBean.ChildBean(7, 2, "双打", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("乒乓球", 2, childBeansP));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansT = new ArrayList<>();
        childBeansT.add(new SportTwoEntity.ParentBean.ChildBean(1, 2, "中式黑八", 1, 1));

        childBeansT.add(new SportTwoEntity.ParentBean.ChildBean(2, 2, "美式9球", 1, 1));
        childBeansT.add(new SportTwoEntity.ParentBean.ChildBean(3, 2, "斯诺克", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("台球", 3, childBeansT));
        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansL = new ArrayList<>();
        childBeansL.add(new SportTwoEntity.ParentBean.ChildBean(10, 2, "5V5", 1, 1));
        childBeansL.add(new SportTwoEntity.ParentBean.ChildBean(11, 2, "3V3", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("篮球", 4, childBeansL));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansZ = new ArrayList<>();

        childBeansZ.add(new SportTwoEntity.ParentBean.ChildBean(13, 2, "11人制", 1, 1));
        childBeansZ.add(new SportTwoEntity.ParentBean.ChildBean(14, 2, "8人制", 1, 1));

        childBeansZ.add(new SportTwoEntity.ParentBean.ChildBean(15, 2, "7人制", 1, 1));
        childBeansZ.add(new SportTwoEntity.ParentBean.ChildBean(16, 2, "5人制", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("足球", 5, childBeansZ));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansPQ = new ArrayList<>();
        childBeansPQ.add(new SportTwoEntity.ParentBean.ChildBean(12, 2, "6V6", 1, 1));
        parentBeans.add(new SportTwoEntity.ParentBean("排球", 6, childBeansPQ));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansW = new ArrayList<>();
        childBeansW.add(new SportTwoEntity.ParentBean.ChildBean(8, 2, "单打", 1, 1));
        childBeansW.add(new SportTwoEntity.ParentBean.ChildBean(9, 2, "双打", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("网球", 7, childBeansW));

        ArrayList<SportTwoEntity.ParentBean.ChildBean> childBeansG = new ArrayList<>();
        childBeansG.add(new SportTwoEntity.ParentBean.ChildBean(17, 2, "比杆赛", 1, 1));
        childBeansG.add(new SportTwoEntity.ParentBean.ChildBean(18, 2, "比洞赛", 2, 2));
        parentBeans.add(new SportTwoEntity.ParentBean("高尔夫", 8, childBeansG));

//        MyScrollView mMyScrollView = rootView.findViewById(R.id.my_scrollview);
        mTabViewLayout = header2.findViewById(R.id.ll_tabView);
        mTopTabViewLayout = rootView.findViewById(R.id.home_tablayout);
        mTopView = header2.findViewById(R.id.home_layout3);
        headerlayout = header4.findViewById(R.id.headerlayout);
        but_qfb = header4.findViewById(R.id.but_qfb);
        text_shuju.setText("抱歉，您可以放宽筛选条件试试！\n 或自己发布活动吧，报名条件您说了算！\n 还可多得5枚对手币！");
        //滑动监听
//        mMyScrollView.setOnScrollListener(this);
//xbanner

        // 初始化搜索模块，注册搜索事件监听
        //     mPoiSearch = PoiSearch.newInstance();
        //   mPoiSearch.setOnGetPoiSearchResultListener((OnGetPoiSearchResultListener) getActivity());
        mXBanner = (XBanner) header1.findViewById(R.id.xbanner);
        dayTAG = 0;
        listViewJC = listView.getRefreshableView();
        listViewJC.setDividerHeight(0);
        listViewJC.addHeaderView(header1);  //添加头部，会隐藏的部分
        listViewJC.addHeaderView(header2);  //添加头部，ListView条目中的悬浮部分
        listViewJC.addHeaderView(header4);
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

       /* if (day.equals("0")){
            //  days=day+" "+timesString[mhour];
            days="0";
            times="0";
        }else {
            days=day+" "+timesString[mhour];
            times=day+" "+timesString[mminute];
        }*/

        listNL1_xx.add("不限");
        for (int i = 1; i <= 99; i++) {
            String str = "";
            str = i + "";
            listNL1_xx.add(str);
        }

        listNL2_xx.add("不限");
        for (int i = 1; i <= 99; i++) {
            String str = "";
            str = i + "";
            listNL2_xx.add(str);
        }

        listJB1_xx.add("不限");
        for (int i = 1; i <= 10; i++) {
            String str = "";
            str = i + "";
            listJB1_xx.add(str);
        }
        listJB2_xx.add("不限");
        for (int i = 1; i <= 10; i++) {
            String str = "";
            str = i + "";
            listJB2_xx.add(str);
        }
        dingwei();
        LogU.Ld("1610", "首页" + token + "===" + page + "==" + city + "===" + sxhuodong1 + "==" + sxhuodong2 + "===" + sxmoshi + "===" + sxfeiyong + "===" + sxsex + "====" + days + "====" + times + "===" + lv + "====" + "lv2" + "===" + jlTAG + "===" + hpTAg + "====" + acitvitysort + "===" + mLatitude + "===" + mLongitude + "====" + bmTAg + "====" + Agemin + "====" + Agemax);
        shuaxin();
        initValue();
        initValueLv();
        //  xiaLa();
        initData();
        showDialog();


        lunbo();


        jianceHuoDong();
        LogU.Ld("1609", "筛选3" + "===" + token + "城市" + city + "运动模式" + sxhuodong1 + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + day + "====" + timesString[mhour] + "时间" + time + "====" + timesString[mminute] + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg + "==" + mLatitude + "===" + mLongitude);

        setlazyLoad();

        return rootView;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();//接收
        int id = v.getId();
        if (popupBigTJ != null) {
            zhinengText.setTextColor(getResources().getColor(R.color.huise));
            home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

            popupBigTJ.dismiss();
            popupBigTJ = null;

        } else {
            if (id == R.id.home_shaixuan2) {
                //setTextDrawable(R.mipmap.xiajiantou,xiangmuText);
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));

                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_shang_yellow));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image4.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                //  home_image4,home_image1,home_image2,home_image3
                getList();
            }

        }

        if (popupBig != null) {
            zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
            home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

            popupBig.dismiss();
            popupBig = null;

        } else {
            if (id == R.id.home_shaixuan3) {
                //setTextDrawable(R.mipmap.xiajiantou,zhuangtaiText);
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));

                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_shang_yellow));
                home_image4.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                // getJLList();
                getJL();
            }

        }
        if (popupBigPhoto != null) {
            LogU.Ld("1608", "取消");
            xiangmuText.setTextColor(getResources().getColor(R.color.huise));
            home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

            popupBigPhoto.dismiss();
            popupBigPhoto = null;

        } else {
            if (id == R.id.home_shaixuan1) {
                LogU.Ld("1608", "取消没");
                xiangmuText.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                shaixuan.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));

                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_shang_yellow));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image4.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                initPopup(getActivity());

            }

        }
        switch (v.getId()) {

            case R.id.home_wenzhang:
                if (NetUtil.getNetWorkStart(getActivity()) != 1) {
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
                    //  qiandao();
                }
                break;
            case R.id.home_dingbu://
                listViewJC.setSelection(0);
                dingbu.setVisibility(View.GONE);
                break;


            case R.id.sousuo_layout://搜索
                intent.setClass(getContext(), HomeSousuoActivity.class);
                bundle.putString("chengshi", chengshiText.getText().toString());
                intent.putExtras(bundle);
                // startActivity(intent);
                startActivityForResult(intent, 0);

                break;
            case R.id.home_chengshi_layout://城市选择
                mlocationClient.start();
//                ToastUitl.longs("aaaaaaaaa");
                //Intent intent = new Intent(getActivity(),CityListActivity.class);

                intent.setClass(getActivity(), CityListActivity.class);
                startActivityForResult(intent, 201);

               /* final List<HotCity> hotCities = new ArrayList<>();
                hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
                hotCities.add(new HotCity("上海", "上海", "101020100"));
                hotCities.add(new HotCity("广州", "广东", "101280101"));
                hotCities.add(new HotCity("深圳", "广东", "101280601"));
                hotCities.add(new HotCity("杭州", "浙江", "101210101"));


                CityPicker.from((FragmentActivity) getContext()) //activity或者fragment
                        .enableAnimation(false)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
                        .setLocatedCity(new LocatedCity(city, province, null))
                        // .setLocatedCity(new LocatedCity("当前位置", province, null))//APP自身已定位的城市，传null会自动定位（默认）参数 市  省   地区代码
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {

                             *//*   mPoiSearch.searchInCity((new PoiCitySearchOption())
                                        .city(chengshiText.getText().toString())
                                        .keyword(chengshiText.getText().toString())
                                        .pageNum(0)
                                        .scope(1));*//*
                                LogU.Ld("1608", data.getCode() + "----" + data.getName() + "-----" + data.getPinyin() + "-----" + data.getProvince() + "-------" + data.getSection() + "到达1" + city + "到达2" + province);

                                chengshiText.setText(data.getName());
                                spUtils.put(getActivity(), "city", data.getName());
                                //  sousuo.setText("");
                                LogU.Ld("1608", "包含" + data.getName() + city);
                                if (!EmptyUtils.isStrEmpty(city)) {
                                    if (city.indexOf(data.getName()) != -1) {
                                        LogU.Ld("1608", "包含");
//                                    mlocationClient.start();
//                                    dingwei();
                                        initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);


                                    } else {
                                        LogU.Ld("1608", "不包含");
                                        zuobiao(data.getName());
                                    }
                                } else {
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
                        .show();*/
////


                break;
            case R.id.home_yingjinbi://任务
                NetUtil.getNetWorkStart(getActivity());
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
                NetUtil.getNetWorkStart(getActivity());
                intent.setClass(getContext(), ShopActivity.class);
                startActivity(intent);

                break;
            case R.id.home_shaixuan4:
                shaixuan.setTextColor(getResources().getColor(R.color.hongse));
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                zhinengText.setTextColor(getResources().getColor(R.color.huise));

                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));
                home_image4.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));

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
                break;
            case R.id.sport_mos://运动模式

                yaoQXZ_y();

                break;
            case R.id.chouti_yule://筛选模式  娱乐

                if (yuleTAG) {
                    yuleLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    yule.setTextColor(getResources().getColor(R.color.white));
                    yuleTAG = !yuleTAG;

                    if (cpdTag) {
                        bmTAg = "canJoined";

                        spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);
                        chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
                        chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                        chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));
                    }
                    sxmoshi = "1";
                    spUtileFQTZ.put(getActivity(), "MoS", sxmoshi);
                    jingjiTAG = true;
                    wozhaoTAG = true;
                    woshiTAG = true;
                    sxfeiyong = "0";
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    // ;
                    shumaiLayout.setVisibility(View.INVISIBLE);
                    chouti_cp_hd.setVisibility(View.INVISIBLE);
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                } else {
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    sxmoshi = "0";
                    spUtileFQTZ.remove(getActivity(), "MoS");
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));

                    shumaiLayout.setVisibility(View.VISIBLE);
                    chouti_cp_hd.setVisibility(View.VISIBLE);
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                    yuleTAG = !yuleTAG;
                   /* jingjiTAG = false;
                    wozhaoTAG = false;
                    woshiTAG = false;*/
                }
                break;
            case R.id.chouti_jingji://筛选模式  竞技

                if (jingjiTAG) {

                    jingjiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    jingji.setTextColor(getResources().getColor(R.color.white));
                    sxmoshi = "2";
                    spUtileFQTZ.put(getActivity(), "MoS", sxmoshi);
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                    jingjiTAG = !jingjiTAG;
                    yuleTAG = true;
                    wozhaoTAG = true;
                    woshiTAG = true;
                    //
                    shumaiLayout.setVisibility(View.VISIBLE);
                    chouti_cp_hd.setVisibility(View.VISIBLE);
                } else {
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    sxmoshi = "0";
                    spUtileFQTZ.remove(getActivity(), "MoS");
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                    jingjiTAG = !jingjiTAG;
                    /*yuleTAG = false;
                    wozhaoTAG = false;
                    woshiTAG = false;*/

                    shumaiLayout.setVisibility(View.VISIBLE);
                    chouti_cp_hd.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.chouti_wozhao://筛选模式  我找陪练

                if (wozhaoTAG) {

                    wozhaoLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    wozhao.setTextColor(getResources().getColor(R.color.white));
                    wozhaoTAG = !wozhaoTAG;
                    sxfeiyong = "0";
                    sxmoshi = "4";
                    spUtileFQTZ.put(getActivity(), "MoS", sxmoshi);
                    if (cpdTag) {
                        bmTAg = "canJoined";

                        spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);
                        chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
                        chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                        chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));
                    }
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    woshiTAG = true;
                    jingjiTAG = true;
                    yuleTAG = true;
                    chouti_cp_hd.setVisibility(View.INVISIBLE);
                    shumaiLayout.setVisibility(View.INVISIBLE);
                } else {
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    sxmoshi = "0";
                    spUtileFQTZ.remove(getActivity(), "MoS");
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                   /* woshiTAG = false;
                    jingjiTAG = false;
                    yuleTAG = false;*/
                    wozhaoTAG = !wozhaoTAG;
                    chouti_cp_hd.setVisibility(View.VISIBLE);
                    shumaiLayout.setVisibility(View.VISIBLE);
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                }
                break;
            case R.id.chouti_woshi://筛选模式 我是陪练

                if (woshiTAG) {

                    woshiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    woshi.setTextColor(getResources().getColor(R.color.white));
                    woshiTAG = !woshiTAG;
                    sxfeiyong = "0";
                    sxmoshi = "3";
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    spUtileFQTZ.put(getActivity(), "MoS", sxmoshi);
                    if (cpdTag) {
                        bmTAg = "canJoined";

                        spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);
                        chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
                        chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                        chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                        chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));
                    }
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoTAG = true;
                    jingjiTAG = true;
                    yuleTAG = true;
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));

                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                    chouti_cp_hd.setVisibility(View.INVISIBLE);
                    shumaiLayout.setVisibility(View.INVISIBLE);
                } else {
                    jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
                    jingji.setTextColor(getResources().getColor(R.color.huise));
                    sxmoshi = "0";
                    spUtileFQTZ.remove(getActivity(), "MoS");
                    yuleLayout.setBackgroundResource(R.drawable.mos_bg);
                    yule.setTextColor(getResources().getColor(R.color.huise));
                    woshiLayout.setBackgroundResource(R.drawable.mos_bg);
                    woshi.setTextColor(getResources().getColor(R.color.huise));
                    wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
                    wozhao.setTextColor(getResources().getColor(R.color.huise));
                    woshiTAG = !woshiTAG;
                   /* wozhaoTAG = false;
                    jingjiTAG = false;
                    yuleTAG = false;*/
                    shumaiLayout.setVisibility(View.VISIBLE);
                    chouti_cp_hd.setVisibility(View.VISIBLE);
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    if (sxfeiyong.equals("2")) {
                        sxfeiyong = "0";
                    }
                }
                break;
            case R.id.chouti_aa://费用承担方式 AA

                if (aaTAG) {
                    aaLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    aa.setTextColor(getResources().getColor(R.color.white));
                    sxfeiyong = "1";
                    spUtileFQTZ.put(getActivity(), "FeiY", sxfeiyong);
                    shumaiLayout.setBackgroundResource(R.drawable.mos_bg);
                    shumai.setTextColor(getResources().getColor(R.color.huise));
                    aaTAG = false;
                    shumaiTAG = true;
                } else {
                    aaLayout.setBackgroundResource(R.drawable.mos_bg);
                    aa.setTextColor(getResources().getColor(R.color.huise));

                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    aaTAG = true;
                    sxfeiyong = "0";
                }

                break;
            case R.id.chouti_shumai://费用承担方式 输买

                if (shumaiTAG) {

                    shumaiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    shumai.setTextColor(getResources().getColor(R.color.white));
                    sxfeiyong = "2";
                    spUtileFQTZ.put(getActivity(), "FeiY", sxfeiyong);

                    aaLayout.setBackgroundResource(R.drawable.mos_bg);
                    aa.setTextColor(getResources().getColor(R.color.huise));
                    shumaiTAG = false;
                    aaTAG = true;
                } else {
                    shumaiLayout.setBackgroundResource(R.drawable.mos_bg);
                    shumai.setTextColor(getResources().getColor(R.color.huise));
                    sxfeiyong = "0";
                    spUtileFQTZ.remove(getActivity(), "FeiY");
                    shumaiTAG = true;
                    // sxmoshi = "0";
                }
                break;
            case R.id.chouti_nan://性别选择 男

                if (nanTAG) {
                    nanLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    nan.setTextColor(getResources().getColor(R.color.white));
                    sxsex = "0";
                    spUtileFQTZ.put(getActivity(), "SeX", sxsex);
                    nvLayout.setBackgroundResource(R.drawable.mos_bg);
                    nv.setTextColor(getResources().getColor(R.color.huise));
                    nanTAG = false;
                    nvTAG = true;
                } else {
                    nanLayout.setBackgroundResource(R.drawable.mos_bg);
                    nan.setTextColor(getResources().getColor(R.color.huise));

                    spUtileFQTZ.remove(getActivity(), "SeX");
                    sxsex = "2";
                    nanTAG = true;
                }
                break;
            case R.id.chouti_nv://性别选择 女

                if (nvTAG) {

                    nvLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    nv.setTextColor(getResources().getColor(R.color.white));
                    sxsex = "1";
                    spUtileFQTZ.put(getActivity(), "SeX", sxsex);
                    nanLayout.setBackgroundResource(R.drawable.mos_bg);
                    nan.setTextColor(getResources().getColor(R.color.huise));
                    nvTAG = false;
                    nanTAG = true;
                } else {
                    nvLayout.setBackgroundResource(R.drawable.mos_bg);
                    nv.setTextColor(getResources().getColor(R.color.huise));
                    sxsex = "2";
                    spUtileFQTZ.remove(getActivity(), "SeX");
                    nvTAG = true;
                }
                break;
            case R.id.chouti_queding:
                showDialog();
                if (guilTAG == 1) {
                    LogU.Ld("1609", "筛选1" + "运动模式" + sxhuodong1 + sxmoshi + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + day + "====" + timesString[mhour] + "时间" + time + "====" + timesString[mminute] + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg);
                    LogU.Ld("1610", "首===页" + token + "===" + page + "==" + city + "===" + sxhuodong1 + "==" + sxhuodong2 + "===" + sxmoshi + "===" + sxfeiyong + "===" + sxsex + "====" + days + "====" + times + "===" + lv + "====" + "lv2" + "===" + jlTAG + "===" + hpTAg + "====" + acitvitysort + "===" + mLatitude + "===" + mLongitude + "====" + bmTAg + "====" + Agemin + "====" + Agemax);
                    //  initDownload(page, citys, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg,Agemin,Agemax);
                    page = 1;
                    data.clear();


                    if (!EmptyUtils.isStrEmpty(souTag)) {
                        if (souTag.equals("2")) {
                            initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                        } else if (souTag.equals("1")) {
                            if (city.equals(nameCS)) {
                                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                            } else {
                                initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                            }
                        } else {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        }
                    } else {
                        initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                    // initDownload(page, city, 0, 0, "0", "0", sxsex, 0 + "", 0 + "", 0, 0, jlTAG, hpTAg, 0, mLatitude + "", mLongitude + "", bmTAg);
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                   /* if (timeTAG) {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day + timesString[mhour], timesString[mminute], lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    }*/
                    LogU.Ld("1609", "days时间" + "===" + day + "=======" + days + "====" + times);

                    day = chouti_day_text.getText().toString();
                    if (day.equals("选择日期")) {
                        //  days=day+" "+timesString[mhour];
                        days = "0";
                        times = "0";
                    } else {
                        time = chouti_tiem_text.getText().toString();
                        if (time.equals("选择时间")) {

                            days = day + " " + "00:00";
                            times = day + " " + "24:00";
                            LogU.Ld("1609", "筛选选择时间" + "===" + token + "日期" + days + "====" + timesString[mhour] + "时间" + times + "====" + timesString[mminute]);
                            // initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                        } else {
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                                String s1 = timesString[mhour].toString();
                                String s2 = timesString[mminute].toString();
                                //  String format_h = dateFormat.format(s1);
                                // String format_t = dateFormat.format(s2);

                                Date parse1 = dateFormat.parse(s1);
                                Date parse2 = dateFormat.parse(s2);
                                long time3 = parse1.getTime();
                                long time4 = parse2.getTime();
                                if (time3 > time4) {
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(parse);
                                    c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

                                    Date tomorrow = c.getTime();
                                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

                                    days = day + " " + timesString[mhour];
                                    times = dateFormat1.format(tomorrow) + " " + timesString[mminute];
                                    LogU.Ld("1609", "days时间" + days + "====" + times);
                                } else {
                                    days = day + " " + timesString[mhour];
                                    times = day + " " + timesString[mminute];
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                    page = 1;
                    data.clear();


                    if (!EmptyUtils.isStrEmpty(souTag)) {
                        if (souTag.equals("2")) {
                            initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                        } else if (souTag.equals("1")) {
                            if (city.equals(nameCS)) {
                                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                            } else {
                                initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                            }
                        } else {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        }
                    } else {
                        initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                    //   initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                    LogU.Ld("1609", "筛==选2" + "===" + token + "城市" + city + "运动模式" + sxhuodong1 + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + days + "时间" + times + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg + "==" + mLatitude + "===" + mLongitude);

                    //  LogU.Ld("1609", "筛选2"+"==="+token +"城市"+city+ "运动模式" + sxhuodong1  + "====" + sxhuodong2 + "运动模式" + sxmoshi + "费用承担方式" + sxfeiyong + "性别" + sxsex + "日期" + day+"====" + timesString[mhour] + "时间" + time+"====" + timesString[mminute] + "技术等级低" + lv + "技术等级高" + lv2 + "好评分数" + hpTAg + "活动" + bmTAg+"=="+mLatitude+"==="+mLongitude);
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
                        if (!EmptyUtils.isStrEmpty(souTag)) {
                            if (souTag.equals("2")) {
                                initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                            } else if (souTag.equals("1")) {
                                initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                            } else {
                                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                            }
                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        }
                    }
                } else {

                    kbm.setBackgroundResource(R.mipmap.kbmhd);
                    kbmTag = true;
                    joinCondition = "";
                    //  initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);

                    if (!EmptyUtils.isStrEmpty(souTag)) {
                        if (souTag.equals("2")) {
                            initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                        } else if (souTag.equals("1")) {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        } else {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        }
                    } else {
                        initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                }
                break;


            case R.id.chouti_day2:

                showSelectDateDialog();
                //timeTAG = false;

                break;
            case R.id.chouti_time:
                if (dayTAG == 0) {
                    showSelectDateDialog();
                   /* if(!chouti_tiem_text.getText().toString().equals("选择时间")){

                    }*/

                    //   timeTAG = false;
                    //  Toast.makeText(getActivity(), "请先选择日期", Toast.LENGTH_SHORT).show();
                } else {
                    // timeTAG = false;
                    showSelectTimeDialog();

                }

                break;
            case R.id.chouti_js_nl_min:
                showChooseDialog(listNL1_xx, listNL2_xx);
                break;
            case R.id.chouti_js_nl_max:
                showChooseDialog(listNL1_xx, listNL2_xx);
                break;


            case R.id.chouti_js_lv_min:
                showChooseDialogNL(listJB1_xx, listJB2_xx);
                // showSelectLVDialog();
                break;
            case R.id.chouti_js_lv_max:
                showChooseDialogNL(listJB1_xx, listJB2_xx);
                // showSelectLVDialog();
                break;

            case R.id.chouti_hp_one:
                if (hp_one) {
                    hpTAg = 1;
                    spUtileFQTZ.put(getActivity(), "HaoP", hpTAg + "");
                    chouti_hp_one.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.white));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hp_one = !hp_one;
                    hp_two = true;
                    hp_three = true;
                    hp_four = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hpTAg = 0;
                    spUtileFQTZ.remove(getActivity(), "HaoP");
                    hp_one = !hp_one;
                }


                break;
            case R.id.chouti_hp_two:
                if (hp_two) {
                    hpTAg = 2;
                    spUtileFQTZ.put(getActivity(), "HaoP", hpTAg + "");

                    chouti_hp_two.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.white));
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hp_two = !hp_two;
                    hp_one = true;
                    hp_three = true;
                    hp_four = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hpTAg = 0;
                    spUtileFQTZ.remove(getActivity(), "HaoP");
                    hp_two = !hp_two;
                }


                break;

            case R.id.chouti_hp_three:

                if (hp_three) {
                    hpTAg = 3;

                    spUtileFQTZ.put(getActivity(), "HaoP", hpTAg + "");
                    chouti_hp_three.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.white));
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hp_three = !hp_three;
                    hp_one = true;
                    hp_two = true;
                    hp_four = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hpTAg = 0;
                    spUtileFQTZ.remove(getActivity(), "HaoP");
                    hp_three = !hp_three;
                }


                break;
            case R.id.chouti_hp_four:
                if (hp_four) {
                    hpTAg = 4;
                    spUtileFQTZ.put(getActivity(), "HaoP", hpTAg + "");

                    chouti_hp_four.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.white));
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    hp_four = !hp_four;
                    hp_one = true;
                    hp_two = true;
                    hp_three = true;
                } else {
                    chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_one_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_two_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_three_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
                    chouti_hp_four_text.setTextColor(getResources().getColor(R.color.heise));
                    hpTAg = 0;
                    spUtileFQTZ.remove(getActivity(), "HaoP");
                    hp_four = !hp_four;
                }


                break;

            case R.id.chouti_bm_hd:
                bmTAg = "canJoined";
                cpdTag = false;
                spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);
                chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
                chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));
                break;
            case R.id.chouti_qb_hd:
                bmTAg = "0";
                spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);


                cpdTag = false;

                chouti_qb_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.white));
                chouti_bm_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.heise));
                chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));

                break;

            case R.id.chouti_cp_hd:
                bmTAg = "referee";
                spUtileFQTZ.put(getActivity(), "BaoM", bmTAg);
                // if (cpdTag) {
                chouti_cp_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.white));
                chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                chouti_bm_hd.setBackgroundResource(R.drawable.mos_bg);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.heise));
                cpdTag = true;
                //qbTag = true;
                //kbTag = true;
                /*} else {
                    cpdTag = !cpdTag;
                    chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
                    chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
                    chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_bm_hd.setBackgroundResource(R.drawable.mos_bg);
                    chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.heise));
                }*/

                break;

            case R.id.chouti_cz:
                guilTAG = 1;
                guiL();

                break;

            case R.id.ll_select01:
                //do something
                jlTAG = "0";
                spUtileFQTZ.put(getActivity(), "JLZ", "0");
                zhuangtaiText.setText("全部范围");
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                page = 1;
                data.clear();
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select02:
                //do something
                jlTAG = "1";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "JLZ", "1");
                zhuangtaiText.setText("1km");
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select03:
                //do something
                jlTAG = "2";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "JLZ", "2");
                zhuangtaiText.setText("2km");
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select04:
                //do something
                jlTAG = "4";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "JLZ", "4");
                zhuangtaiText.setText("4km");
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select05:
                //do something
                jlTAG = "10";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "JLZ", "10");
                zhuangtaiText.setText("10km");
                zhuangtaiText.setTextColor(getResources().getColor(R.color.hongse));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;

            case R.id.ll_select01_ty:
                //do something
                acitvitysort = "0";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "TJP", "0");
                zhinengText.setText("距离由近到远");
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));

                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select02_ty:
                //do something
                acitvitysort = "1";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "TJP", "1");
                zhinengText.setText("时间由近到远");
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select03_ty:
                //do something
                acitvitysort = "2";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "TJP", "2");
                zhinengText.setText("级别由高到低");
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select04_ty:
                //do something
                acitvitysort = "3";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "TJP", "3");
                zhinengText.setText("级别由低到高");
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                break;
            case R.id.ll_select05_ty:
                //do something
                acitvitysort = "4";
                page = 1;
                data.clear();
                spUtileFQTZ.put(getActivity(), "TJP", "4");
                zhinengText.setText("好评优先");
                zhinengText.setTextColor(getResources().getColor(R.color.hongse));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
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

                        LogU.Ld("1608","刷新"+NetUtil.getNetWorkStart(getActivity()));
                        if (NetUtilTwo.getNetWorkStart(getActivity())!=1) {
                            page = 1;
                            data.clear();
                            // dingwei();
                            LogU.Ld("1608", "下拉" + page + "");
                            //   initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                            if (!EmptyUtils.isStrEmpty(souTag)) {
                                if (souTag.equals("2")) {
                                    initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                                } else if (souTag.equals("1")) {
                                    if (city.equals(nameCS)) {
                                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                                    } else {
                                        initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                                    }
                                } else {
                                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                                }
                            } else {
                                initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                            }
                        }
                        listView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listView.onRefreshComplete();
                            }
                        }, 3000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        if (NetUtilTwo.getNetWorkStart(getActivity())!=1) {
                            nowPage++;
                            page = nowPage;
                            LogU.Ld("1608", "上啦" + nowPage + "");
                            //dingwei();
                            //    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                            if (!EmptyUtils.isStrEmpty(souTag)) {
                                if (souTag.equals("2")) {
                                    initDownload(nowPage, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                                } else if (souTag.equals("1")) {
                                    initDownload(nowPage, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                                } else {
                                    initDownload(nowPage, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                                }
                            } else {
                                initDownload(nowPage, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                            }
                            dingbu.setVisibility(View.VISIBLE);

                        }
                        listView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listView.onRefreshComplete();

                            }
                        }, 3000);

                    }
                });

    }

    //首页列表数据
    private void initDownload(final int page, String city2, int sxhuodong1, int sxhuodong2, String sxmoshi, String sxfeiyong, String sxsex, String day, String time, String lv, String lv2, String range, int praise, String acitvitysort, String mLatitude1, String mLongitude1, String bmTAg, String Agemin, String Agemax) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
      /*  LogU.Ld("1608", "首页列表活动=====" + token + "运动项目" + sxhuodong1 + "SportMode+++" + sxmoshi + "娱乐模式payType:" + sxfeiyong + "费用承担方式SportType:" + sxhuodong2 + "性别sex:" + sxsex
                + "智能排序acitvitysort:" + acitvitysort + "筛选状态" + sxzhuangtai + "城市city:" + city + "地区是：" + area + "经度lat：" + mLatitude + "纬度lng：" + mLongitude + "错误码" + locType + "joinCondition" + joinCondition + "报名状态" + bmTAg + "startTime:"
                + days + "endTime:" + times + "range:" + jlTAG + "praise是：" + hpTAg + "mingrade是：" + lv + "maxgrade是：" + lv2);
*/
        LogU.Ld("1608", "首页" + token + "===" + page + "==" + city + "=====" + city2 + "===" + sxhuodong1 + "==" + sxhuodong2 + "===" + sxmoshi + "===" + sxfeiyong + "===" + sxsex + "====" + days + "====" + times + "===" + lv + "====" + "==" + lv2 + "===" + jlTAG + "===" + hpTAg + "====" + acitvitysort + "===" + mLatitude1 + "===" + mLongitude1 + "====" + bmTAg + "====" + Agemin + "====" + Agemax);
        LogU.Ld("1608", "首===页" + day + "===" + time);

        /*if (timeTAG) {
            timesString[mhour] = "0";
            timesString[mminute] = "0";
            day = "0";
            time = "0";

        }*/
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getIndexAcitivitylist")
                /* .addHeader("token", token)
                 .addParams("page", page + "")
                 .addParams("city", city)
                 .addParams("sportid", sxhuodong1 + "") //运动项目
                 .addParams("SportType",  sxhuodong2+"")//运动项目二级分类
                 .addParams("SportMode", sxmoshi) //运动模式
                 .addParams("payType", sxfeiyong) //场地费支付方式
                 .addParams("sex", sxsex)//性别
                 .addParams("startTime", days)//开始时间
                 .addParams("endTime", times)//结束时间
                 .addParams("range", jlTAG + "") //范围
                 .addParams("praise", hpTAg + "") //报名人的好评
                 .addParams("joinCondition", bmTAg)  //活动状态 可报名0 全部1
                 .addParams("acitvitysort", acitvitysort + "")//推荐排序
                 .addParams("mingrade", lv + "") //报名人最小平均等级
                 .addParams("maxgrade", lv2 + "") //报名最大平均等级
                 .addParams("lat", mLatitude + "") //经度
                 .addParams("lng", mLongitude + "") //纬度
                 .addParams("Agemin", Agemin + "") //最小年龄
                 .addParams("Agemax", Agemax + "") //最大年龄*/

                .addHeader("token", token)
                .addParams("page", page + "")
                .addParams("city", city2)
                .addParams("sportid", sxhuodong1 + "") //运动项目
                .addParams("SportType", sxhuodong2 + "")//运动项目二级分类
                .addParams("SportMode", sxmoshi) //运动模式
                .addParams("payType", sxfeiyong) //场地费支付方式
                .addParams("sex", sxsex)//性别
                .addParams("startTime", day)//开始时间
                .addParams("endTime", time)//结束时间
                .addParams("range", range + "") //范围
                .addParams("praise", praise + "") //报名人的好评
                .addParams("joinCondition", bmTAg)  //活动状态 可报名0 全部1
                .addParams("acitvitysort", acitvitysort + "")//推荐排序
                .addParams("mingrade", lv + "") //报名人最小平均等级
                .addParams("maxgrade", lv2 + "") //报名最大平均等级
                .addParams("lat", mLatitude1 + "") //经度
                .addParams("lng", mLongitude1 + "") //纬度
                .addParams("Agemin", Agemin + "") //最小年龄
                .addParams("Agemax", Agemax + "") //最大年龄
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        progressDialog.cancel();
                        //  ToastUitl.longs("网络繁忙，请刷新！");
                        LogU.Ld("1611", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "首页列表" + result);
                        SPUtils.put(getActivity(), "HomeFrag.getIndexAcitivitylist", result);
                        progressDialog.cancel();
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {

                            guilTAG = 0;
                            Gson gson = new Gson();
                            HomeListEntity entity = gson.fromJson(result, HomeListEntity.class);
                            final List<HomeListEntity.DataBean.ActiveLstBean> list;
                            list = entity.getData().getActiveLst();

                            nowPage = entity.getData().getNowPage();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
//                            layoutParams.height = 1450;
//                            listView.getRefreshableViewWrapper().setLayoutParams(layoutParams);
                            //listView.setVisibility(View.VISIBLE);
                            if (!EmptyUtils.isEmpty(entity)) {
                                LogU.Le("1618", "是空不=======" + list + "====" + nowPage);


                                if (page == 1) {
                                    data.clear();
                                    data.addAll(list);
//                                    listView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    listView.onRefreshComplete();
                                } else {
                                    data.addAll(list);
//                                listView.setAdapter(adapter);
                                    LogU.Le("1608", "是空不=======" + list + "====" + data);

                                    adapter.notifyDataSetChanged();
                                    listView.onRefreshComplete();
                                }

                                listViewJC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        if (position <= 3) {

                                        } else {
                                            // LogU.Ld("1608","======="+uuid);
                                            NetUtil.getNetWorkStart(getActivity());
                                            uuid = data.get(position - 4).getUuid();
                                            //  LogU.Ld("1608","1问题点我"+data.get(position ).getUuid());
                                           /* if (!EmptyUtils.isStrEmpty(data.get(position-3).getUuid())){
                                                LogU.Ld("1608","2问题点我"+data.get(position-3).getUuid());
                                            }*/

                                            LogU.Ld("1608", "3问题点我" + data.get(position - 4).getNickname());

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

                                if (!EmptyUtils.isEmpty(data)) {
                                    headerlayout.setVisibility(View.GONE);

                                } else {
                                    //   Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                                    headerlayout.setVisibility(View.VISIBLE);
                                }
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
        LogU.Ld("1608", "首页列表+++++++=");

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
                        SPUtils.put(getActivity(), "HomeFrag.lunB", result);

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
                                    Bundle bundle = new Bundle();//接收
                                    if (list.get(position).getDesc().equals("1")) {
                                        intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                                        bundle.putString("tagTZ", "1");
                                        spUtileFQTZ.clear(getContext());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    } else if (list.get(position).getDesc().equals("2")) {
                                        intent.setClass(getContext(), RenWuActivity.class);
                                        startActivity(intent);
                                    } else if (list.get(position).getDesc().equals("3")) {
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
                                    } else if (list.get(position).getDesc().equals("4")) {
                                        intent.setClass(getContext(), HZCGActivity.class);
                                        startActivity(intent);
                                    } else if (list.get(position).getDesc().equals("5")) {

                                        initReferee();

                                    }


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
                            //  initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            //     ToastUitl.longs(entity.getMsg());

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
                //   .addParams("msgCate", "systems ")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        // progressDialog.cancel();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JCXIEntity entity = gson.fromJson(result, JCXIEntity.class);
                            if (entity.getData().getNotReadCount() > 0) {
                                weidu.setVisibility(View.VISIBLE);

                            } else {
                                weidu.setVisibility(View.GONE);
                            }
                            //  panduan();
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
                            Bundle bundle = new Bundle();//接收
//                            intent.setClass(DengluActivity.this, MainActivity.class);
//                            startActivity(intent);
                            intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                            bundle.putString("tagTZ", "1");
                            intent.putExtras(bundle);
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

                        boolean jsonValid = isJSONValid(result);
                        if (jsonValid) {
                            Gson gson = new Gson();
                            ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);

                            mLatitude1 = entity.getResult().getLocation().getLat();
                            mLongitude1 = entity.getResult().getLocation().getLng();
                            spUtileFQTZ.put(getActivity(), "mylat", mLatitude1 + "");
                            spUtileFQTZ.put(getActivity(), "mylng", mLongitude1 + "");
                            LogU.Ld("1608", "城市" + chengshiText.getText().toString() + "====" + nameCS);
                            //  initDownload(page, address, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);

                        }
                        //  initDownload(page, address, 0, 0, 0, 0, 0, entity.getResult().getLocation().getLat() + "", entity.getResult().getLocation().getLng() + "", joinCondition);

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


        dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);


        ds_xz.setText("为了您更方便发布和报名活动，请完善个人信息!");

        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        icon_que.setText("去完善");
        icon_close.setText("先看看");
        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(getContext(), GRXXActivity.class);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);

                startActivity(intent);

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /* private void showNormalDialog() {
     *//* @setIcon 设置对话框图标
     * @setTitle 设置对话框标题
     * @setMessage 设置对话框消息提示
     * setXXX方法返回Dialog对象，因此可以链式设置属性
     *//*
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
    }*/

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
        int span = 3000;
        mOption.setScanSpan(span);
        //设置 LocationClientOption
        mlocationClient.setLocOption(mOption);
        mlocationClient.start();
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
            if (bdLocation == null) {
                chengshiText.setText("定位失败");
                progressDialog.cancel();
                return;
            }
            //  LogU.Ld("1611", "定位失败"+bdLocation+"========"+mlocationClient.isStarted()+"===="+bdLocation.getCity()+"==="+isFirstIn);

            if (bdLocation.getCity() != null) {

                mLatitude = bdLocation.getLatitude();
                mLongitude = bdLocation.getLongitude();
                city = bdLocation.getCity().replace("市", "");
                area = bdLocation.getDistrict();
                province = bdLocation.getProvince();
                locType = bdLocation.getLocType();


            }
            //  spUtils.put(getActivity(), "city", city);
            spUtils.put(getActivity(), "area", area);
            //  LogU.Ld("1608", "城市" + city + "纬度" + mLongitude + "错误码" + bdLocation.getLocType());
            if (isFirstIn) {

                if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
                    //  Toast.makeText(getActivity(), bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
                    // Toast.makeText(getActivity(), bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
                    // Toast.makeText(getActivity(), bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(getActivity(), "定位错误，请选择城市", Toast.LENGTH_SHORT).show();
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(getActivity(), "网络错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(getActivity(), "手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }

                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        String mLati = (String) spUtileFQTZ.get(getContext(), "mylat", "");
                        String mLong = (String) spUtileFQTZ.get(getContext(), "mylng", "");
                        if (!EmptyUtils.isStrEmpty(mLati) && !EmptyUtils.isStrEmpty(mLong)) {
                            mLatitude = Double.parseDouble(mLati);
                            mLongitude = Double.parseDouble(mLong);
                        }
                        LogU.Ld("1608", "城==9991==市======" + mLati + "====" + mLong + "======" + souTag + "=====" + mLongitude);

                    } else {
                        spUtileFQTZ.remove(getActivity(), "mylat");
                        spUtileFQTZ.remove(getActivity(), "mylng");
                    }
                }

                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("1")) {
                        chengshiText.setText(nameCS);
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                            LogU.Ld("1611", "定位1=====失败" + bdLocation + "========" + bdLocation.getCoorType() + "====" + bdLocation.getCity() + "===" + isFirstIn);

                        } else {
                            initDownload(page, nameCS, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                            LogU.Ld("1608", "定位1=====" + mLatitude1 + "========" + mLongitude1);

                        }
                        LogU.Ld("1608", "城市搜索=====" + souTag + "===" + city + "经度" + mLatitude + "纬度" + mLongitude + "地区" + bdLocation.getAddrStr() + "大大大");

                    } else if (souTag.equals("2")) {
                        chengshiText.setText(city1);
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                        LogU.Ld("1608", "城市2=====" + city + "经度" + mLatitude1 + "纬度" + mLongitude1 + "地区");

                    } else {
                        chengshiText.setText(nameCS);
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                        LogU.Ld("1608", "城==99==91==市============" + mLatitude + "=====" + mLongitude);

                    }

                } else {
                    LogU.Ld("1608", "城市3=====" + city + "经度" + mLatitude + "纬度" + mLongitude + "地区" + bdLocation.getAddrStr() + "大大大");

                    if (!EmptyUtils.isStrEmpty(citysw)) {
                        chengshiText.setText(citysw);
                        initDownload(page, citysw, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                        LogU.Ld("1608", "城市5=====" + city + "经度" + mLatitude + "纬度" + mLongitude + "地区" + bdLocation.getAddrStr() + "大大大");

                    } else {
                        LogU.Ld("1608", "城市4=====" + city + "经度" + mLatitude + "纬度" + mLongitude + "地区" + bdLocation.getAddrStr() + "大大大");

                        if (!EmptyUtils.isStrEmpty(city)) {
                            chengshiText.setText(city);
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                        } else {
                            chengshiText.setText("定位失败");
                        }
                    }

                }
                isFirstIn = false;
            } else {
                // chengshiText.setText("定位中...");
                if (EmptyUtils.isStrEmpty(chengshiText.getText().toString()) && EmptyUtils.isStrEmpty(citysw)) {
                    initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                    mlocationClient.stop();
                }
            }
            if (mlocationClient.isStarted()) {
                // 获得位置之后停止定位
                LogU.Ld("1611", "定位==停止==失败" + bdLocation + "========" + bdLocation.getCoorType() + "====" + bdLocation.getCity() + "===" + isFirstIn);

                mlocationClient.stop();
            }

            //   mlocationClient.stop();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 201 && resultCode == RESULT_OK) {
            nameCS = data.getStringExtra("CityName");
            if (city.equals(nameCS)) {
                initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

            } else {
                zuobiao(nameCS);
            }

            page = 1;
            spUtileFQTZ.remove(getActivity(), "souAddress");
            sousuo.setText("请输入地址");
            chengshiText.setText(nameCS);
            souTag = data.getStringExtra("CityTag");
            LogU.Ld("1608", "搜索数据" + souTag);

        }
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mlocationClient.stop();
            Bundle bundle = data.getExtras();
            city1 = bundle.getString("city");
            area = bundle.getString("area");
            mLatitude2 = bundle.getDouble("mylat");
            mLongitude2 = bundle.getDouble("mylong");


            content = bundle.getString("content");
            spUtileFQTZ.put(getActivity(), "souAddress", content);
            souTag = bundle.getString("CityTag");
            LogU.Ld("1608", "搜索==数据" + souTag + "====" + mLatitude1 + "====" + mLongitude1);

            page = 1;
            sousuo.setText(content);
            initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);

        }

    }


    @Override
    public void onStop() {
        super.onStop();
        //停止定位
        //  mBaiduMap.setMyLocationEnabled(false);
        mlocationClient.stop();
        //   myOrientationListener.stop();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开启定位
//        mBaiduMap.setMyLocationEnabled(true);
        if (!mlocationClient.isStarted()) {
            mlocationClient.start();
        }
        // bmTAg = "canJoined";

        LogU.Ld("1608", "城市第一次" + chengshiText.getText().toString() + "=====" + city + "===" + citys);
        // citysw = (String) spUtils.get(getContext(), "cityw", "");

        //  citys = (String) spUtils.get(getContext(), "city", "");
       /* if(!EmptyUtils.isStrEmpty(citys)){
            chengshiText.setText(citys);
            initDownload(page, citys, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg,Agemin,Agemax);

        }else {
            chengshiText.setText(city);
            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg,Agemin,Agemax);

        }*/
//        myOrientationListener.start();
        //   initDownload(page, city, 0,0, mLatitude + "", mLongitude + "", joinCondition);
        jianceHuoDong();
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


    // 日期
    private void showSelectDateDialog() {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int days = c.get(Calendar.DAY_OF_MONTH);
        SelectDateDialog mSelectDateDialog = new SelectDateDialog(getActivity(), new SelectDateDialog.OnClickListener() {
            @Override
            public boolean onSure(int mYear, int mMonth, int mDay, long time) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());

                LogU.Ld("1609", "时间" + mYear + "月" + mMonth + "天" + mDay + "时" + time);
                try {
                    String format = dateFormat.format(time);
                    String format1 = dateFormat.format(date);
                    parse = dateFormat.parse(format);
                    Date parse1 = dateFormat.parse(format1);
                    time2 = parse.getTime();
                    time1 = parse1.getTime();
                    LogU.Ld("1609", "时间" + time1 + "月" + time2);
                    if (time2 >= time1) {
                        LogU.Ld("1609", "时间" + mYear + "月" + mMonth + "天" + mDay + "时" + time);
                        chouti_day_text.setText(dateFormat.format(time));
                        day = chouti_day_text.getText().toString();
                        LogU.Ld("1609", "时间" + day + "====" + time);
                        chouti_day2.setBackgroundResource(R.drawable.mos_bg);
                        chouti_day_text.setTextColor(getResources().getColor(R.color.heise));
                        dayTAG = 1;
                    } else {
                        Toast.makeText(getActivity(), "时间选择错误", Toast.LENGTH_LONG).show();
                        return true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // chouti_day_text.setText(dateFormat.format(time));
                //  day = chouti_day_text.getText().toString();
                LogU.Ld("1609", "时间" + day + time);
                //  chouti_day2.setBackgroundResource(R.drawable.ellipse_home_details);
                //  chouti_day_text.setTextColor(getResources().getColor(R.color.my_tab));

                return false;
            }

            @Override
            public boolean onCancel() {
                chouti_day2.setBackgroundResource(R.drawable.mos_bg);
                chouti_day_text.setTextColor(getResources().getColor(R.color.bbbbb));
                chouti_day_text.setText("选择日期");
                day = "0";
                spUtileFQTZ.remove(getActivity(), "day_s");
                return false;
            }
        });

        mSelectDateDialog.show(year, month, days);
    }


    private void showSelectTimeDialog() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        //  Date format = dateFormat.parse(calendar.getTime());


        SelectTimeDialog mSelectTimeDialog = new SelectTimeDialog(getActivity(), new SelectTimeDialog.OnClickListener() {
            @Override
            public boolean onSure(int hour, int minute, int setTimeType) {

                //  SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                //  Date date = new Date(System.currentTimeMillis());
                if (time2 == time1) {
                    String s1 = timesString[hour];
                    String s = timesString[minute];
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");

                    Date now = null;
                    Date beginTime = null;
                    Date endTime = null;

                    try {
                        now = df.parse(df.format(new Date()));
                        beginTime = df.parse(s1);
                        endTime = df.parse(s);


                        boolean effectiveDate = isEffectiveDate(now, beginTime, endTime);
                        LogU.Ld("1608", "时间选择" + now + "====" + beginTime + "====" + endTime + "===" + effectiveDate);
                        if (effectiveDate) {
                            //    Toast.makeText(getActivity(), "当前时间在范围内", Toast.LENGTH_LONG).show();
                            mhour = hour;
                            mminute = minute;
                            chouti_tiem_text.setText(timesString[hour] + "-" + timesString[minute]);


                            time = chouti_tiem_text.getText().toString();
                            chouti_time.setBackgroundResource(R.drawable.mos_bg);
                            chouti_tiem_text.setTextColor(getResources().getColor(R.color.heise));

                        } else {
                            Toast.makeText(getActivity(), "时间选择错误", Toast.LENGTH_LONG).show();

                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                } else if (time2 > time1) {
                    mhour = hour;
                    mminute = minute;
                    chouti_tiem_text.setText(timesString[hour] + "-" + timesString[minute]);


                    time = chouti_tiem_text.getText().toString();
                    chouti_time.setBackgroundResource(R.drawable.mos_bg);
                    chouti_tiem_text.setTextColor(getResources().getColor(R.color.heise));

                }

                return false;
            }

            @Override
            public boolean onCancel() {

                chouti_tiem_text.setText("选择时间");
                chouti_time.setBackgroundResource(R.drawable.mos_bg);
                chouti_tiem_text.setTextColor(getResources().getColor(R.color.bbbbb));
                spUtileFQTZ.remove(getActivity(), "day_s");
                // time="24:00";
                return false;
            }
        });

        mSelectTimeDialog.show(mhour, mminute, 1);
    }

    private void initValue() {
        timesString = getResources().getStringArray(R.array.times);
    }

    private void initValueLv() {
        lVleArray = getResources().getStringArray(R.array.Lvle);
    }


    public void guiL() {

        spUtileFQTZ.remove(getActivity(), "day_s");
        spUtileFQTZ.remove(getActivity(), "time_s");
        spUtileFQTZ.remove(getActivity(), "MoS");
        spUtileFQTZ.remove(getActivity(), "FeiY");
        spUtileFQTZ.remove(getActivity(), "SeX");
        spUtileFQTZ.remove(getActivity(), "HaoP");
        spUtileFQTZ.remove(getActivity(), "AgeminS");
        spUtileFQTZ.remove(getActivity(), "AgemaxS");
        spUtileFQTZ.remove(getActivity(), "NLnumS");
        spUtileFQTZ.remove(getActivity(), "NLnumS1");
        spUtileFQTZ.remove(getActivity(), "BaoM");
        spUtileFQTZ.remove(getActivity(), "JBnumS");
        spUtileFQTZ.remove(getActivity(), "JBnumS1");
        spUtileFQTZ.remove(getActivity(), "JBminS");
        spUtileFQTZ.remove(getActivity(), "JBmaxS");
        chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
        chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
        chouti_qb_hd.setBackgroundResource(R.drawable.mos_bg);
        chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.huise));
        chouti_cp_hd.setBackgroundResource(R.drawable.mos_bg);
        chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.huise));
        bmTAg = "canJoined";
        chouti_hp_one.setBackgroundResource(R.drawable.mos_bg);
        chouti_hp_one_text.setTextColor(getResources().getColor(R.color.huise));
        chouti_hp_two.setBackgroundResource(R.drawable.mos_bg);
        chouti_hp_two_text.setTextColor(getResources().getColor(R.color.huise));
        chouti_hp_three.setBackgroundResource(R.drawable.mos_bg);
        chouti_hp_three_text.setTextColor(getResources().getColor(R.color.huise));
        chouti_hp_four.setBackgroundResource(R.drawable.mos_bg);
        chouti_hp_four_text.setTextColor(getResources().getColor(R.color.huise));
        hpTAg = 0;

        yuleLayout.setBackgroundResource(R.drawable.mos_bg);
        yule.setTextColor(getResources().getColor(R.color.huise));
        jingjiLayout.setBackgroundResource(R.drawable.mos_bg);
        jingji.setTextColor(getResources().getColor(R.color.huise));
        woshiLayout.setBackgroundResource(R.drawable.mos_bg);
        woshi.setTextColor(getResources().getColor(R.color.huise));
        wozhaoLayout.setBackgroundResource(R.drawable.mos_bg);
        wozhao.setTextColor(getResources().getColor(R.color.huise));
        sxmoshi = "0";

        aaLayout.setBackgroundResource(R.drawable.mos_bg);
        aa.setTextColor(getResources().getColor(R.color.huise));
        shumaiLayout.setBackgroundResource(R.drawable.mos_bg);
        shumai.setTextColor(getResources().getColor(R.color.huise));
        shumai.setVisibility(View.VISIBLE);
        sxfeiyong = "0";

        nanLayout.setBackgroundResource(R.drawable.mos_bg);
        nan.setTextColor(getResources().getColor(R.color.huise));
        nvLayout.setBackgroundResource(R.drawable.mos_bg);
        nv.setTextColor(getResources().getColor(R.color.huise));
        sxsex = "2";

        chouti_tiem_text.setText("选择时间");
        chouti_time.setBackgroundResource(R.drawable.mos_bg);
        chouti_tiem_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_day2.setBackgroundResource(R.drawable.mos_bg);
        chouti_day_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_day_text.setText("选择日期");
        // timesString[mhour] = "";
        // timesString[mminute] = "";

        days = "0";
        times = "0";
        chouti_js_lv_min_text.setText("最低等级");
        chouti_js_lv_max_text.setText("最高等级");
        chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
        chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
        chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
        //  lVleArray[lv] = "0";
        // lVleArray[lv2] = "0";

        lv = "0";
        lv2 = "0";

        Agemin = "0";
        Agemax = "0";
        nl_min_text.setText("最低年龄");
        nl_max_text.setText("最高年龄");
        chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
        chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
        nl_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
        nl_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
        chouti_cp_hd.setVisibility(View.VISIBLE);
        shumaiLayout.setVisibility(View.VISIBLE);
    }


    private void getList() {

        View view = getLayoutInflater().inflate(R.layout.select_popwindow_ty, null);
        if (popupBigTJ == null) {
            popupBigTJ = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);

        }

        //设置PopupWindow宽高
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupBigTJ.setWidth(display.getWidth());
        popupBigTJ.setHeight(display.getHeight());
        popupBigTJ.setOutsideTouchable(true);
        // popupBigPhoto.setFocusable(true);
        // popupBigPhoto.setContentView(view);
        //设置背景
        ColorDrawable dw = new ColorDrawable(0x60000000);
        popupBigTJ.setBackgroundDrawable(dw);
        //  backgroundAlpha(getActivity(),0.5f);//0.0-1.0
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            line.getGlobalVisibleRect(rect);
            int h = line.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            popupBigTJ.setHeight(h);
        }
        if (!popupBigTJ.isShowing()) {
            popupBigTJ.showAsDropDown(line);
        } else {
            popupBigTJ.dismiss();
        }


        LinearLayout ll_select01 = (LinearLayout) view.findViewById(R.id.ll_select01_ty);
        ll_select01.setOnClickListener(this);
        LinearLayout ll_select02 = (LinearLayout) view.findViewById(R.id.ll_select02_ty);
        ll_select02.setOnClickListener(this);
        LinearLayout ll_select03 = (LinearLayout) view.findViewById(R.id.ll_select03_ty);
        ll_select03.setOnClickListener(this);
        LinearLayout ll_select04 = (LinearLayout) view.findViewById(R.id.ll_select04_ty);
        ll_select04.setOnClickListener(this);
        LinearLayout ll_select05 = (LinearLayout) view.findViewById(R.id.ll_select05_ty);
        ll_select05.setOnClickListener(this);
        final LinearLayout big_layout = view.findViewById(R.id.ll_select);

        big_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhinengText.setTextColor(getResources().getColor(R.color.huise));
                home_image2.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

                popupBigTJ.dismiss();
                popupBigTJ = null;
            }
        });
    }


    //距离范围
    private void getJL() {
        View view = getLayoutInflater().inflate(R.layout.select_popwindow, null);
        if (popupBig == null) {
            popupBig = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);

        }

        //设置PopupWindow宽高
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupBig.setWidth(display.getWidth());
        popupBig.setHeight(display.getHeight());
        popupBig.setOutsideTouchable(true);
        // popupBigPhoto.setFocusable(true);
        // popupBigPhoto.setContentView(view);
        //设置背景
        ColorDrawable dw = new ColorDrawable(0x60000000);
        popupBig.setBackgroundDrawable(dw);
        //  backgroundAlpha(getActivity(),0.5f);//0.0-1.0
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            line.getGlobalVisibleRect(rect);
            int h = line.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            popupBig.setHeight(h);
        }
        if (!popupBig.isShowing()) {
            popupBig.showAsDropDown(line);
        } else {
            popupBig.dismiss();
        }


        LinearLayout ll_select01 = (LinearLayout) view.findViewById(R.id.ll_select01);
        ll_select01.setOnClickListener(this);
        LinearLayout ll_select02 = (LinearLayout) view.findViewById(R.id.ll_select02);
        ll_select02.setOnClickListener(this);
        LinearLayout ll_select03 = (LinearLayout) view.findViewById(R.id.ll_select03);
        ll_select03.setOnClickListener(this);
        LinearLayout ll_select04 = (LinearLayout) view.findViewById(R.id.ll_select04);
        ll_select04.setOnClickListener(this);
        LinearLayout ll_select05 = (LinearLayout) view.findViewById(R.id.ll_select05);
        ll_select05.setOnClickListener(this);
        final LinearLayout big_layout = view.findViewById(R.id.ll_select);

        big_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuangtaiText.setTextColor(getResources().getColor(R.color.huise));
                home_image3.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

                popupBig.dismiss();
                popupBig = null;
            }
        });


    }


    class ListPopuJL extends SpinerPopWindow<String> {

        public ListPopuJL(Context context, List<String> list, int resId, View.OnClickListener itemsOnClick) {
            super(context, list, resId, itemsOnClick);
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
    private void initPopup(Activity context) {
        View view = getLayoutInflater().inflate(R.layout.spiner_window_layout_xm, null);
        if (popupBigPhoto == null) {
            popupBigPhoto = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);

        }


        //设置PopupWindow宽高
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupBigPhoto.setWidth(display.getWidth());
        popupBigPhoto.setHeight(display.getHeight());
        popupBigPhoto.setOutsideTouchable(true);
        // popupBigPhoto.setFocusable(true);
        // popupBigPhoto.setContentView(view);
        //设置背景
        ColorDrawable dw = new ColorDrawable(0x60000000);
        popupBigPhoto.setBackgroundDrawable(dw);
        //  backgroundAlpha(getActivity(),0.5f);//0.0-1.0
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

        final RelativeLayout big_layout = view.findViewById(R.id.big_layout);

        final LinearLayout riagt_out = view.findViewById(R.id.riagt_out);
        final TextView textall = view.findViewById(R.id.text_all);
        final TextView text_all_two = view.findViewById(R.id.text_all_two);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_left);
        recyclerView_right = (RecyclerView) view.findViewById(R.id.recycler_view_right);
        big_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiangmuText.setTextColor(getResources().getColor(R.color.huise));
                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.xiajiantou));

                popupBigPhoto.dismiss();
                popupBigPhoto = null;
            }
        });
        /*if (qubTag) {
            textall.setTextColor(getResources().getColor(R.color.hongse));
        }*/
        textall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sxhuodong1 = 0;
                page = 1;
                data.clear();
                spUtileFQTZ.remove(getActivity(), "sxhuodong1");
                spUtileFQTZ.remove(getActivity(), "sxhuodong2");
                //  initDownload(1, city, 0, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day, time, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));

                xiangmuText.setText("全部");
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                qubTag = true;

                popupBigPhoto.dismiss();
                popupBigPhoto = null;
            }
        });

        text_all_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // initDownload(page, chengshiText.getText().toString(), sxhuodong1, 0, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);
                sxhuodong2 = 0;
                spUtileFQTZ.remove(getActivity(), "sxhuodong2");
                // xiangmuText.setText("全部");
                page = 1;
                data.clear();
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));

                LogU.Ld("1609", "" + sxhuodong1 + "===" + sxhuodong2);
                popupBigPhoto.dismiss();
                popupBigPhoto = null;
            }
        });
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // recyclerView_right.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        spiner = new PopAdapter(R.layout.spiner_windowxm_layout_item, parentBeans);
        recyclerView_right.setLayoutManager(new LinearLayoutManager(getActivity()));
        spinerAdapter = new PopAdapterTwo(R.layout.spiner_window_layout_xm_item, (ArrayList<SportTwoEntity.ParentBean.ChildBean>) sPorttwo);
        recyclerView.setAdapter(spiner);


        spiner.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                anInt = sport1.get(position).getId();
              //  entity.getData().get(position).setSelect(true);
                name = sport1.get(position).getName();
                sxhuodong1 = sport1.get(position).getId();
                textall.setTextColor(getResources().getColor(R.color.huise));
                qubTag = false;
                page = 1;
                riagt_out.setVisibility(View.VISIBLE);
                spUtileFQTZ.put(getActivity(), "sxhuodong1", sxhuodong1);

                spiner.selectedItemPosition(position);
                xiangmuText.setText(parentBeans.get(position).getParentName());
                LogU.Ld("1608", "运动项目" + sxhuodong1 + "==" + anInt);

                spiner.notifyDataSetChanged();
                sPorttwo.clear();
                sPorttwo.addAll(parentBeans.get(position).getData());
                LogU.Ld("1608", "运动项目" + position + "==" + parentBeans.get(position).getData()+parentBeans.size());
                recyclerView_right.setAdapter(spinerAdapter);
                spinerAdapter.notifyDataSetChanged();

            }
        });

        spinerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // sxhuodong1 = sportList.get(position).getId();
                sxhuodong2 = sPorttwo.get(position).getId();
                xiangmuText.setText(name + sPorttwo.get(position).getName());
                spUtileFQTZ.put(getActivity(), "sxhuodong2", sxhuodong2);
                // initDownload(1, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, day, time, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                LogU.Ld("16008", "运动==项目" + sxhuodong1 + "==" + sxhuodong2 + "==" );
                page = 1;
                data.clear();
                //  initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                if (!EmptyUtils.isStrEmpty(souTag)) {
                    if (souTag.equals("2")) {
                        initDownload(page, city1, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude2 + "", mLongitude2 + "", bmTAg, Agemin, Agemax);
                    } else if (souTag.equals("1")) {
                        if (city.equals(nameCS)) {
                            initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        } else {
                            initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude1 + "", mLongitude1 + "", bmTAg, Agemin, Agemax);
                        }
                    } else {
                        initDownload(page, city, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                    }
                } else {
                    initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                }
                home_image1.setBackgroundDrawable(getResources().getDrawable(R.mipmap.home_xia_yellow));

                popupBigPhoto.dismiss();
                popupBigPhoto = null;
            }
        });
    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
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

    //是不是裁判
    private void initReferee() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + "--" + token + "--");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/Referee")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {

                    private int status;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "认证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            InitRefereeEntity entity = gson.fromJson(result, InitRefereeEntity.class);
                            status = entity.getData().getStatus();
                            if (status == 1) {
                                intent.setClass(getContext(), RefereePerfectXXActivity.class);
                                startActivity(intent);
                            } else {
                                initSFZ();

                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {

                                intent.setClass(getContext(), DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }


    //实名认证
    private void initSFZ() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" + "--" + token + "--");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/judgereferee")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {

                    private String playerID;
                    private String playerRealName;
                    private String number;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "认证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            JudgerefereeJEntity entity = gson.fromJson(result, JudgerefereeJEntity.class);
                            number = entity.getData().getPlayerAppPhoneNumber();
                            playerRealName = entity.getData().getPlayerRealName();
                            playerID = entity.getData().getPlayerID();
                            intent.setClass(getContext(), MyCwRefereeActivity.class);


                            intent.putExtra("number", number);
                            intent.putExtra("playerRealName", playerRealName);
                            intent.putExtra("playerID", playerID);
                            startActivity(intent);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")) {

                                intent.setClass(getContext(), DengluActivity.class);
                                startActivity(intent);
                            } else if (entity.getMsg().equals("没有实名认证")) {
                                intent.setClass(getContext(), MyCwRefereeActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    //检测活动
    private void jianceHuoDong() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllActiveCount")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //  progressDialog.cancel();
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;


                        if (a) {
                            Gson gson = new Gson();
                            AllActiveCountEntity entity = gson.fromJson(result, AllActiveCountEntity.class);
                            if (entity.getData().getCount() > 0) {
                                huodong_weidu.setVisibility(View.VISIBLE);

                            } else {
                                huodong_weidu.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            //  ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

    /**
     * chooseDialog  选择年龄
     */
    private void showChooseDialog(List<String> mlist, List<String> mlist1) {

        AgePickerDialog.Builder builder = new AgePickerDialog.Builder(getActivity());

        String NLnum = (String) spUtileFQTZ.get(getActivity(), "NLnumS", "0");
        String NLnum1 = (String) spUtileFQTZ.get(getActivity(), "NLnumS1", "0");

        int nu = 0, nu1 = 0;
        try {

            if (!EmptyUtils.isStrEmpty(NLnum)) {
                nu = Integer.valueOf(NLnum).intValue();
            }
            if (!EmptyUtils.isStrEmpty(NLnum1)) {
                nu1 = Integer.valueOf(NLnum1).intValue();
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();

        }
        chooseDialog = builder.setData(mlist, mlist1).setSelection(nu, nu1).setTitle("重置")
                .setOnDataSelectedListener(new AgePickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position, String itemValue1, int position1) {

                        LogU.Ld("1608", "选择年龄" + position + "==" + position1);


                        if (position == 0 && position1 == 0) {
                            Agemin = "1";
                            Agemax = "99";
                            nl_min_text.setText(Agemin);
                            nl_max_text.setText(Agemax);
                            chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
                            nl_min_text.setTextColor(getResources().getColor(R.color.huise));
                            nl_max_text.setTextColor(getResources().getColor(R.color.huise));
                            spUtileFQTZ.put(getActivity(), "AgeminS", Agemin);
                            spUtileFQTZ.put(getActivity(), "AgemaxS", Agemax);
                            spUtileFQTZ.put(getActivity(), "NLnumS", position + "");
                            spUtileFQTZ.put(getActivity(), "NLnumS1", position1 + "");
                        } else {
                            if (position == 0 || position1 == 0) {
                                if (position == 0 && position1 != 0) {
                                    Agemin = "1";
                                    Agemax = itemValue1;
                                    nl_min_text.setText(Agemin);
                                    nl_max_text.setText(itemValue1);
                                    chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
                                    nl_min_text.setTextColor(getResources().getColor(R.color.huise));
                                    nl_max_text.setTextColor(getResources().getColor(R.color.huise));
                                    spUtileFQTZ.put(getActivity(), "AgeminS", Agemin);
                                    spUtileFQTZ.put(getActivity(), "AgemaxS", Agemax);
                                    spUtileFQTZ.put(getActivity(), "NLnumS", position + "");
                                    spUtileFQTZ.put(getActivity(), "NLnumS1", position1 + "");
                                }

                                if (position != 0 && position1 == 0) {
                                    Agemin = itemValue;
                                    Agemax = "99";
                                    nl_min_text.setText(Agemin);
                                    nl_max_text.setText(Agemax);
                                    chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
                                    nl_min_text.setTextColor(getResources().getColor(R.color.huise));
                                    nl_max_text.setTextColor(getResources().getColor(R.color.huise));
                                    spUtileFQTZ.put(getActivity(), "AgeminS", Agemin);
                                    spUtileFQTZ.put(getActivity(), "AgemaxS", Agemax);
                                    spUtileFQTZ.put(getActivity(), "NLnumS", position + "");
                                    spUtileFQTZ.put(getActivity(), "NLnumS1", position1 + "");
                                }
                                //  nial_text.setText(itemValue + "-" + itemValue1);
                            } else {
                                if (position >= position1) {
                                    Toast.makeText(getActivity(), "年龄下限须＜年龄上限", Toast.LENGTH_LONG).show();

                                } else {
                                    Agemin = itemValue;
                                    Agemax = itemValue1;
                                    nl_min_text.setText(itemValue);
                                    nl_max_text.setText(itemValue1);
                                    chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
                                    nl_min_text.setTextColor(getResources().getColor(R.color.huise));
                                    nl_max_text.setTextColor(getResources().getColor(R.color.huise));
                                    spUtileFQTZ.put(getActivity(), "AgeminS", Agemin);
                                    spUtileFQTZ.put(getActivity(), "AgemaxS", Agemax);
                                    spUtileFQTZ.put(getActivity(), "NLnumS", position + "");
                                    spUtileFQTZ.put(getActivity(), "NLnumS1", position1 + "");
                                }
                            }
                        }


                    }

                    @Override
                    public void onCancel() {
                        nl_min_text.setText("最低年龄");
                        nl_max_text.setText("最高年龄");
                        Agemin = "1";
                        Agemax = "99";
                        spUtileFQTZ.remove(getActivity(), "AgeminS");
                        spUtileFQTZ.remove(getActivity(), "AgemaxS");
                        spUtileFQTZ.remove(getActivity(), "NLnumS");
                        spUtileFQTZ.remove(getActivity(), "NLnumS1");
                        chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
                        chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
                        nl_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
                        nl_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    }
                }).create();

        chooseDialog.show();

    }

/*

    private void showSelectLVDialog() {
        SelectLVDialog mSelectLvDialog = new SelectLVDialog(getActivity(), new SelectLVDialog.OnClickListener() {
            @Override
            public boolean onSure(int hour, int minute, int setTimeType) {

                if (hour == 0 && minute == 0) {
                    lv = 1;
                    lv2 = 10;
                    chouti_js_lv_min_text.setText("1");
                    chouti_js_lv_max_text.setText("10");
                    chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                    chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                    chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                    chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));

                } else {

                    if (hour == 0 || minute == 0) {
                        if (hour == 0 && minute != 0) {
                            lv = 1;
                            lv2 = minute;
                            chouti_js_lv_min_text.setText("1");
                            chouti_js_lv_max_text.setText(lVleArray[minute]);
                            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));

                        }

                        if (hour != 0 && minute == 0) {
                            lv = hour;
                            lv2 = 10;
                            chouti_js_lv_min_text.setText(lVleArray[hour]);
                            chouti_js_lv_max_text.setText("10");
                            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));

                        }
                        //  nial_text.setText(itemValue + "-" + itemValue1);
                    } else {
                        if (hour >= minute) {

                            Toast.makeText(getActivity(), "最高级别不能低于最低级别", Toast.LENGTH_LONG).show();
                        } else {
                            lv = hour;
                            lv2 = minute;
                            chouti_js_lv_min_text.setText(lVleArray[hour]);
                            chouti_js_lv_max_text.setText(lVleArray[minute]);
                            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));
                            Toast.makeText(getActivity(), lVleArray[hour] + "-" + lVleArray[minute], Toast.LENGTH_LONG).show();
                        }
                    }

                }

                spUtileFQTZ.put(getActivity(),"JbMin",lv+"");
                spUtileFQTZ.put(getActivity(),"JbMin",lv+"");
                return false;
            }

            @Override
            public boolean onCancel() {
                lv = 1;
                lv2 = 10;
                chouti_js_lv_min_text.setText("最低等级");
                chouti_js_lv_max_text.setText("最高等级");

                chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
                chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
                return false;
            }
        });
        mSelectLvDialog.show(lVleArray[lv], lVleArray[lv2], 1);
    }
*/


    /**
     * chooseDialog  选择级别
     */
    private void showChooseDialogNL(List<String> mlist, List<String> mlist1) {

        NLPickerDialog.Builder builder = new NLPickerDialog.Builder(getActivity());

        String JBnumS = (String) spUtileFQTZ.get(getActivity(), "JBnumSS", "0");
        String JBnumS1 = (String) spUtileFQTZ.get(getActivity(), "JBnumSS1", "0");

        int nu = 0, nu1 = 0;
        try {

            if (!EmptyUtils.isStrEmpty(JBnumS)) {
                nu = Integer.valueOf(JBnumS).intValue();
            }
            if (!EmptyUtils.isStrEmpty(JBnumS1)) {
                nu1 = Integer.valueOf(JBnumS1).intValue();
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();

        }
        chooseDialogJB = builder.setData(mlist, mlist1).setSelection(nu, nu1).setTitle("重置")
                .setOnDataSelectedListener(new NLPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position, String itemValue1, int position1) {

                        LogU.Ld("1608", "选择年龄" + position + "==" + position1);


                        if (position == 0 && position1 == 0) {
                            lv = "1";
                            lv2 = "10";
                            chouti_js_lv_min_text.setText("1");
                            chouti_js_lv_max_text.setText("10");
                            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));

                            spUtileFQTZ.put(getActivity(), "JBminS", lv);
                            spUtileFQTZ.put(getActivity(), "JBmaxS", lv2);

                            spUtileFQTZ.put(getActivity(), "JBnumSS", position + "");
                            spUtileFQTZ.put(getActivity(), "JBnumSS1", position1 + "");

                        } else {
                            if (position == 0 || position1 == 0) {
                                if (position == 0 && position1 != 0) {
                                    lv = "1";
                                    lv2 = itemValue1;
                                    chouti_js_lv_min_text.setText("1");
                                    chouti_js_lv_max_text.setText(lv2 + "");
                                    chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                                    chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));
                                    spUtileFQTZ.put(getActivity(), "JBminS", lv);
                                    spUtileFQTZ.put(getActivity(), "JBmaxS", lv2);

                                    spUtileFQTZ.put(getActivity(), "JBnumSS", position + "");
                                    spUtileFQTZ.put(getActivity(), "JBnumSS1", position1 + "");
                                }

                                if (position != 0 && position1 == 0) {
                                    lv = itemValue;
                                    lv2 = "10";
                                    chouti_js_lv_min_text.setText(lv + "");
                                    chouti_js_lv_max_text.setText("10");
                                    chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                                    chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));
                                    spUtileFQTZ.put(getActivity(), "JBminS", lv);
                                    spUtileFQTZ.put(getActivity(), "JBmaxS", lv2);

                                    spUtileFQTZ.put(getActivity(), "JBnumSS", position + "");
                                    spUtileFQTZ.put(getActivity(), "JBnumSS1", position1 + "");
                                }
                                //  nial_text.setText(itemValue + "-" + itemValue1);
                            } else {
                                if (position >= position1) {
                                    Toast.makeText(getActivity(), "技术级别下限须＜技术级别上限", Toast.LENGTH_LONG).show();

                                } else {
                                    lv = itemValue;
                                    lv2 = itemValue1;
                                    chouti_js_lv_min_text.setText(lv + "");
                                    chouti_js_lv_max_text.setText(lv2 + "");
                                    chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                                    chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.heise));
                                    chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.heise));
                                    spUtileFQTZ.put(getActivity(), "JBminS", lv);
                                    spUtileFQTZ.put(getActivity(), "JBmaxS", lv2);

                                    spUtileFQTZ.put(getActivity(), "JBnumSS", position + "");
                                    spUtileFQTZ.put(getActivity(), "JBnumSS1", position1 + "");
                                }
                            }
                        }


                    }

                    @Override
                    public void onCancel() {

                        spUtileFQTZ.remove(getActivity(), "JBminS");
                        spUtileFQTZ.remove(getActivity(), "JBmaxS");
                        spUtileFQTZ.remove(getActivity(), "JBnumS");
                        spUtileFQTZ.remove(getActivity(), "JBnumS1");
                        lv = "1";
                        lv2 = "10";
                        chouti_js_lv_min_text.setText("最低等级");
                        chouti_js_lv_max_text.setText("最高等级");

                        chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
                        chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
                        chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
                        chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
                    }
                }).create();

        chooseDialogJB.show();

    }

    public final static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void onResume() {
        dingwei();

        String getIndexAcitivitylist = (String) SPUtils.get(getActivity(), "HomeFrag.getIndexAcitivitylist", "");
        lunB = (String) SPUtils.get(getActivity(), "HomeFrag.lunB", "");
        sport = (String) SPUtils.get(getActivity(), "HomeFrag.sport", "");
        sport2 = (String) SPUtils.get(getActivity(), "HomeFrag.sport2", "");
        LogU.Ld("1609", "缓存" + "===" + token + "城市" + "====");

        if (!EmptyUtils.isStrEmpty(lunB)) {
            Gson gson = new Gson();
            final LunboEntity entity = gson.fromJson(lunB, LunboEntity.class);
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
                    Bundle bundle = new Bundle();//接收
                    if (list.get(position).getDesc().equals("1")) {
                        intent.setClass(getContext(), FaqiTiaozhanActivity.class);
                        bundle.putString("tagTZ", "1");
                        spUtileFQTZ.clear(getContext());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (list.get(position).getDesc().equals("2")) {
                        intent.setClass(getContext(), RenWuActivity.class);
                        startActivity(intent);
                    } else if (list.get(position).getDesc().equals("3")) {
                        intent.setClass(getContext(), MyPromoterActivity.class);
                        startActivity(intent);
                    } else if (list.get(position).getDesc().equals("4")) {
                        intent.setClass(getContext(), HZCGActivity.class);
                        startActivity(intent);
                    } else if (list.get(position).getDesc().equals("5")) {

                        initReferee();

                    }


                }
            });
        }
        if (NetUtilTwo.getNetWorkStart(getActivity()) == 1) {
            LogU.Ld("1608", "轮播图" + lunB);

                ToastUitl.longs("定位失败");

            progressDialog.cancel();


            if (!EmptyUtils.isStrEmpty(getIndexAcitivitylist)) {
                Gson gson = new Gson();
                HomeListEntity entity = gson.fromJson(getIndexAcitivitylist, HomeListEntity.class);
                final List<HomeListEntity.DataBean.ActiveLstBean> list;
                list = entity.getData().getActiveLst();

                nowPage = entity.getData().getNowPage();

                if (!EmptyUtils.isEmpty(entity)) {
                    LogU.Le("1618", "是空不=======" + list + "====" + nowPage);

                    if (page == 1) {
                        data.clear();
                        data.addAll(list);
//                                    listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    } else {
                        data.addAll(list);
//                                listView.setAdapter(adapter);
                        LogU.Le("1608", "是空不=======" + list + "====" + data);

                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }

                    listViewJC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (position <= 3) {

                            } else {
                                // LogU.Ld("1608","======="+uuid);
                                uuid = data.get(position - 4).getUuid();
                                //  LogU.Ld("1608","1问题点我"+data.get(position ).getUuid());
                                           /* if (!EmptyUtils.isStrEmpty(data.get(position-3).getUuid())){
                                                LogU.Ld("1608","2问题点我"+data.get(position-3).getUuid());
                                            }*/

                                LogU.Ld("1608", "3问题点我" + data.get(position - 4).getNickname());

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

                    if (!EmptyUtils.isEmpty(data)) {
                        headerlayout.setVisibility(View.GONE);

                    } else {
                        //   Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        headerlayout.setVisibility(View.VISIBLE);
                    }
                }
            }


        }

        citysw = (String) spUtils.get(getContext(), "cityw", "");
        String day_s = (String) spUtileFQTZ.get(getContext(), "day_s", "选择日期");
        String time_s = (String) spUtileFQTZ.get(getContext(), "time_s", "选择时间");
        String MoS = (String) spUtileFQTZ.get(getContext(), "MoS", "0");
        String FeiY = (String) spUtileFQTZ.get(getContext(), "FeiY", "0");
        String SeX = (String) spUtileFQTZ.get(getContext(), "SeX", "2");
        String HaoP = (String) spUtileFQTZ.get(getContext(), "HaoP", "0");
        Agemin = (String) spUtileFQTZ.get(getActivity(), "AgeminS", "最低年龄");
        Agemax = (String) spUtileFQTZ.get(getActivity(), "AgemaxS", "最高年龄");

        lv = (String) spUtileFQTZ.get(getActivity(), "JBminS", "最低等级");
        lv2 = (String) spUtileFQTZ.get(getActivity(), "JBmaxS", "最高等级");
        String BaoM = (String) spUtileFQTZ.get(getActivity(), "BaoM", "canJoined");
        String XM = (String) spUtileFQTZ.get(getActivity(), "XM", "运动项目");
        sxhuodong1 = (int) spUtileFQTZ.get(getActivity(), "sxhuodong1", 0);
        sxhuodong2 = (int) spUtileFQTZ.get(getActivity(), "sxhuodong2", 0);

        souAddress = (String) spUtileFQTZ.get(getActivity(), "souAddress", "");


        jlTAG = (String) spUtileFQTZ.get(getActivity(), "JLZ", "距离范围");
        acitvitysort = (String) spUtileFQTZ.get(getActivity(), "TJP", "推荐排序");
        LogU.Ld("1608", "城==999==市======" + BaoM + "==" + MoS + "===" + FeiY + "===" + SeX + "===" + HaoP + "===" + Agemin + "===" + Agemax + "===" + lv + "===" + lv2 + "===" + XM + "===" + sxhuodong1 + "===" + sxhuodong2 + "===" + jlTAG);
        LogU.Ld("1608", "城==8888==市======" + day_s + "===" + time_s);


        if (!EmptyUtils.isStrEmpty(souAddress)) {
            sousuo.setText(souAddress);
        }
        if (day_s.equals("选择日期")) {
            chouti_day_text.setText("选择日期");

        } else {
            chouti_day2.setBackgroundResource(R.drawable.mos_bg);
            chouti_day_text.setTextColor(getResources().getColor(R.color.heise));
            dayTAG = 1;
            chouti_day_text.setText(day_s);
        }

        if (time_s.equals("选择时间")) {
            chouti_tiem_text.setText("选择时间");

        } else {
            chouti_tiem_text.setText(time_s);
            chouti_time.setBackgroundResource(R.drawable.mos_bg);
            chouti_tiem_text.setTextColor(getResources().getColor(R.color.heise));
        }
        xiangmuText.setText(XM);

        if (!EmptyUtils.isStrEmpty(acitvitysort)) {
            if (acitvitysort.equals("推荐排序")) {
                acitvitysort = "0";
                zhinengText.setText("推荐排序");
            } else if (acitvitysort.equals("0")) {
                zhinengText.setText("距离由近到远");
            } else if (acitvitysort.equals("1")) {
                zhinengText.setText("时间由近到远");
            } else if (acitvitysort.equals("2")) {
                zhinengText.setText("级别由高到低");
            } else if (acitvitysort.equals("3")) {
                zhinengText.setText("级别由低到高");
            } else if (acitvitysort.equals("4")) {
                zhinengText.setText("好评优先");
            }
        }

        if (!EmptyUtils.isStrEmpty(jlTAG)) {
            if (jlTAG.equals("距离范围")) {
                jlTAG = "0";
                zhuangtaiText.setText("距离范围");
            } else if (jlTAG.equals("0")) {
                zhuangtaiText.setText("全部距离");
            } else if (jlTAG.equals("1")) {
                zhuangtaiText.setText("1km");
            } else if (jlTAG.equals("2")) {
                zhuangtaiText.setText("2km");
            } else if (jlTAG.equals("4")) {
                zhuangtaiText.setText("4km");
            } else if (jlTAG.equals("10")) {
                zhuangtaiText.setText("10km");
            }

        }
        if (!EmptyUtils.isStrEmpty(BaoM)) {
            if (BaoM.equals("canJoined")) {
                chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));
                bmTAg = "canJoined";

            } else if (BaoM.equals("0")) {
                bmTAg = "0";
                chouti_qb_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_qb_hd_text.setTextColor(getResources().getColor(R.color.white));
            } else if (BaoM.equals("referee")) {
                bmTAg = "referee";
                chouti_cp_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                chouti_cp_hd_text.setTextColor(getResources().getColor(R.color.white));
            }
        } else {
            bmTAg = "canJoined";
            chouti_bm_hd.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            chouti_bm_hd_text.setTextColor(getResources().getColor(R.color.white));

        }
        if (Agemin.equals("最低年龄") && Agemax.equals("最高年龄")) {
            nl_min_text.setText(Agemin);
            nl_max_text.setText(Agemax);
            Agemin = "1";
            Agemax = "99";
            chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
            nl_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
            nl_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
        } else {
            nl_min_text.setText(Agemin);
            nl_max_text.setText(Agemax);
            chouti_js_nl_min.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_nl_max.setBackgroundResource(R.drawable.mos_bg);
            nl_min_text.setTextColor(getResources().getColor(R.color.huise));
            nl_max_text.setTextColor(getResources().getColor(R.color.huise));
        }


        if (lv.equals("最低等级") && lv2.equals("最高等级")) {
            chouti_js_lv_min_text.setText(lv);
            chouti_js_lv_max_text.setText(lv2);
            lv = "1";
            lv2 = "10";
            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.bbbbb));
            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.bbbbb));
        } else {
            chouti_js_lv_min_text.setText(lv);
            chouti_js_lv_max_text.setText(lv2);
            chouti_js_lv_min.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_lv_max.setBackgroundResource(R.drawable.mos_bg);
            chouti_js_lv_min_text.setTextColor(getResources().getColor(R.color.huise));
            chouti_js_lv_max_text.setTextColor(getResources().getColor(R.color.huise));
        }

        LogU.Ld("1608", "城==44==市==============" + city + "==66=" + citys + "=88==" + citysw);
        /*if (!EmptyUtils.isStrEmpty(citysw)) {
            chengshiText.setText(citysw);
            initDownload(page, citysw, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

        }*/
        if (MoS.equals("1")) {
            sxmoshi = "1";
            yuleLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            yule.setTextColor(getResources().getColor(R.color.white));
            shumaiLayout.setVisibility(View.INVISIBLE);
            chouti_cp_hd.setVisibility(View.GONE);
        } else if (MoS.equals("2")) {
            sxmoshi = "2";
            shumaiLayout.setVisibility(View.VISIBLE);
            chouti_cp_hd.setVisibility(View.VISIBLE);
            jingjiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            jingji.setTextColor(getResources().getColor(R.color.white));
        } else if (MoS.equals("3")) {
            sxmoshi = "3";
            shumaiLayout.setVisibility(View.INVISIBLE);
            chouti_cp_hd.setVisibility(View.GONE);
            woshiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            woshi.setTextColor(getResources().getColor(R.color.white));
        } else if (MoS.equals("4")) {
            sxmoshi = "4";
            shumaiLayout.setVisibility(View.INVISIBLE);
            chouti_cp_hd.setVisibility(View.GONE);
            wozhaoLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            wozhao.setTextColor(getResources().getColor(R.color.white));
        }
        if (FeiY.equals("1")) {
            sxfeiyong = "1";
            aaLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            aa.setTextColor(getResources().getColor(R.color.white));

        } else if (FeiY.equals("2")) {
            sxfeiyong = "2";
            shumaiLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            shumai.setTextColor(getResources().getColor(R.color.white));
        } else {
            sxfeiyong = "0";
        }

        if (SeX.equals("0")) {

            sxsex = "0";
            nanLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            nan.setTextColor(getResources().getColor(R.color.white));

        } else if (SeX.equals("1")) {
            sxsex = "1";
            nvLayout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            nv.setTextColor(getResources().getColor(R.color.white));
        }

        // initDownload(page, content, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

        if (HaoP.equals("1")) {
            hpTAg = 1;
            chouti_hp_one.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            chouti_hp_one_text.setTextColor(getResources().getColor(R.color.white));
        } else if (HaoP.equals("2")) {
            hpTAg = 2;
            chouti_hp_two.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            chouti_hp_two_text.setTextColor(getResources().getColor(R.color.white));
        } else if (HaoP.equals("3")) {
            hpTAg = 3;
            chouti_hp_three.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            chouti_hp_three_text.setTextColor(getResources().getColor(R.color.white));
        } else if (HaoP.equals("4")) {
            hpTAg = 4;
            chouti_hp_four.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
            chouti_hp_four_text.setTextColor(getResources().getColor(R.color.white));
        }


        super.onResume();
        LogU.Ld("1608", "=======城市==============" + city + citys);

    }

    @Override
    public void onPause() {
        String s = chengshiText.getText().toString();
        String xm = xiangmuText.getText().toString();
        String day_s = chouti_day_text.getText().toString();
        String time_s = chouti_tiem_text.getText().toString();
        spUtileFQTZ.put(getContext(), "day_s", day_s);
        spUtileFQTZ.put(getContext(), "time_s", time_s);
        spUtileFQTZ.put(getContext(), "XM", xm);
        spUtils.put(getContext(), "cityw", s);
        spUtileFQTZ.put(getActivity(), "mylat1", mLatitude + "");
        spUtileFQTZ.put(getActivity(), "mylng1", mLongitude + "");
        LogU.Ld("1608", "城====市==============" + city + "===" + s + "=====" + citys);
        super.onPause();

    }

    @AfterPermissionGranted(CODE_PERM)
    private void getPermission() {
        if (hasGetPermission()) {

        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "需要设备的存储权限",
                    CODE_PERM,
                    PERMS_HOME);
        }
    }

    private boolean hasGetPermission() {
        return EasyPermissions.hasPermissions(getActivity(), PERMS_HOME);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限设置")
                    .setRationale("需要设备的存储权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("是")
                    .setNegativeButton("否").build().show();
        }
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if ((date.after(begin) && (date.before(end) || end.before(begin))) || (begin.after(date) && (begin.before(end) || end.before(begin)))) {
            return true;
        } else {
            return false;
        }
    }


    //弹窗运动
    private void yaoQXZ_y() {
        dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        LinearLayout sport;
        sport = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.pop_sport_layout, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);

        TextView yul = sport.findViewById(R.id.yul);
        TextView ji_j = sport.findViewById(R.id.ji_j);
        TextView pl = sport.findViewById(R.id.pl);
        TextView zpl = sport.findViewById(R.id.zpl);
        TextView tis_text = sport.findViewById(R.id.tis_text);
        String yuL = "娱乐模式：无输赢，场地费所有人AA承担；";
        String jingJ = "竞技模式：有输赢，场地费所有人AA承担或输方买单；有技术分输赢，可以聘请裁判。";

        String wPL = "我是陪练：无输赢，场地费双方AA承担，发布者是陪练，报名者是练习方。练习方支付陪练费给陪练方。技术级别4级及以上才可成为该运动项目的陪练。";
        String zPL = "我找陪练：无输赢，场地费双方AA承担，发布者是练习方，报名者是陪练。练习方支付陪练费给陪练方。技术级别4级及以上才可成为该运动项目的陪练。";

        SpannableStringBuilder style = new SpannableStringBuilder(yuL);
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        style.setSpan(styleSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#030303"));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#4A4949"));
        style.setSpan(colorSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(colorSpan2, 5, yuL.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // style.setSpan(new ForegroundColorSpan(Color.parseColor("#030303")), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        yul.setText(style);

        SpannableStringBuilder style_jingJ = new SpannableStringBuilder(jingJ);
        StyleSpan styleSpan1 = new StyleSpan(Typeface.BOLD);
        style_jingJ.setSpan(styleSpan1, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan_style_jingJ = new ForegroundColorSpan(Color.parseColor("#030303"));
        ForegroundColorSpan colorSpan_style_jingJ2 = new ForegroundColorSpan(Color.parseColor("#4A4949"));
        style_jingJ.setSpan(colorSpan_style_jingJ, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style_jingJ.setSpan(colorSpan_style_jingJ2, 5, jingJ.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ji_j.setText(style_jingJ);

        // int tb = wPL.indexOf("技");
        SpannableStringBuilder style_wPL = new SpannableStringBuilder(wPL);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.BOLD);
        style_wPL.setSpan(styleSpan2, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan_style_wPL = new ForegroundColorSpan(Color.parseColor("#030303"));
        ForegroundColorSpan colorSpan_style_wPL2 = new ForegroundColorSpan(Color.parseColor("#4A4949"));
        ForegroundColorSpan colorSpan_style_wPL3 = new ForegroundColorSpan(Color.parseColor("#FFE4162F"));

        style_wPL.setSpan(colorSpan_style_wPL, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style_wPL.setSpan(colorSpan_style_wPL2, 5, wPL.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // style_wPL.setSpan(colorSpan_style_wPL3, tb, wPL.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        pl.setText(style_wPL);

        //  int tz = zPL.indexOf("技");
        SpannableStringBuilder style_zPL = new SpannableStringBuilder(zPL);
        StyleSpan styleSpan3 = new StyleSpan(Typeface.BOLD);
        style_zPL.setSpan(styleSpan3, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan_style_zPL = new ForegroundColorSpan(Color.parseColor("#030303"));
        ForegroundColorSpan colorSpan_style_zPL2 = new ForegroundColorSpan(Color.parseColor("#4A4949"));
        ForegroundColorSpan colorSpan_style_zPL3 = new ForegroundColorSpan(Color.parseColor("#FFE4162F"));
        style_zPL.setSpan(colorSpan_style_zPL, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style_zPL.setSpan(colorSpan_style_zPL2, 5, zPL.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        // style_zPL.setSpan(colorSpan_style_zPL3, tz, zPL.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        zpl.setText(style_zPL);
        //  TextView ds_xz = sport.findViewById(R.id.ds_xz);


        tis_text.setText("运动模式说明");
        // ds_xz.setVisibility(View.GONE);
        // sport_lay.setVisibility(View.VISIBLE);


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        dialog.show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
