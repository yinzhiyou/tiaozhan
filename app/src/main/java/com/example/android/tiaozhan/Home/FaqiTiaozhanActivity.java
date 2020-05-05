package com.example.android.tiaozhan.Home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.tiaozhan.Adapter.FQTZAdapter;
import com.example.android.tiaozhan.Adapter.FQTZBAdapter;
import com.example.android.tiaozhan.Adapter.FQTZCPAdapter;
import com.example.android.tiaozhan.Adapter.FQYQAdapter;
import com.example.android.tiaozhan.Adapter.HDXQAAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.AccmoneyEntity;
import com.example.android.tiaozhan.Entity.FQTZEntity;
import com.example.android.tiaozhan.Entity.HuodXQEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.LevesEntity;
import com.example.android.tiaozhan.Entity.RefereeMoneyEntity;
import com.example.android.tiaozhan.Entity.SportLeveEntity;
import com.example.android.tiaozhan.Entity.TheBallEntity;
import com.example.android.tiaozhan.Entity.ZhifubaoEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.Utils;
import com.example.android.tiaozhan.Toos.mypicker.DataPickerDialog;
import com.example.android.tiaozhan.Toos.mypicker.FQLVPickerDialog;
import com.example.android.tiaozhan.Toos.time.dialog.SelectGEFDialog;
import com.example.android.tiaozhan.Toos.time.dialog.SelectNumDialog;
import com.example.android.tiaozhan.Toos.time.dialog.SelectResereeLVDialog;
import com.example.android.tiaozhan.Toos.time.view.PickValueView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 发起挑战
 */
public class FaqiTiaozhanActivity extends BaseActivity implements View.OnClickListener {

    private Dialog chooseDialog;
    private TextView mTextView;
    private List<String> listNL1 = new ArrayList<>();
    private List<String> listNL2 = new ArrayList<>();
    private List<String> listJB1 = new ArrayList<>();
    private List<String> listJB2 = new ArrayList<>();
    private List<String> listPL1 = new ArrayList<>();
    private List<String> listPL2 = new ArrayList<>();
    private TextView biaoti, youshangjiao, quxiao, xiangmuText, moshiText, changguanText, fangshiText, sexText, nial_text, yul_text, ji_text, wpl_text, zpl_text,
            jibieText, timeText, shichangText, feiyongText, peilianText, zhuanhuanText, feiyong_cp, cdf_cp_text, bux, nan, nv;
    private EditText beizhuEdit, dashangText, peilText, beizhu_edit_partner;
    private ImageView fanhui, dwd, timeyjt, youjiantou_aa, cGdwd;
    private GridView gridViewA, gridViewB, gridViewC;
    private FQTZAdapter adapter;
    private FQTZBAdapter adapterB;
    private FQTZCPAdapter adapterC;
    private Dialog dialog;
    private LinearLayout sc_layout, peilian, sc_layout_partner;
    private RelativeLayout xingbie, xiangmu, jibie, nial_layout, home_faqi_changguan_partner, moshi;
    private Dialog mCameraDialog;
    private RelativeLayout jingji, dashang, yule, woshi, wozhao, fabu, aa, shumai, sexNan, sexNv, sexBuxian, faqi_tiaozhan_yuding, changguan, time, cp_layout;
    // private Double cdf;
    private XBanner bannerView;

    private int dianTag = 1;

    private String fqtzXiangmu, wyhbXiangmu, fqtzXiangmuda, fqtzXiangmuid, wfqtzXiangmuid, fqtzXiangmudaid, wfqtzXiangmudaid, moshiString = "1", cgname, cgid, wcgid, city, token, sex = "2", fangs = "1", cpLV, cpNUM, cp_Ggef, jB, nL, cp_NUM, refereeLV, ref_GLV,
            dashangString = "0.0", peilianString = "0.0", zuigao = "10", zuidi = "1", touxiangimg, YQuuid, YQdengji, fqtzqiurenshu;
    private String canyurenshu = "2", jingjiYesNo, yuleYesNO, peilianYesNO, moshiTag, moshihao = "1", moshiName, timeRI, timeSHI, YQtouxiang, YQtab = "0", YQname,
            shichang, hezuo, whezuo, FQHDyaoqing, hezuofeiyong, cgname2, Agemin = "1", Agemax = "99", JBmin = "1", JBmax = "10", teamSex = "2", xm_name, zXM_name,beiz,beiz_partner;

    /*private String canyurenshu = "2", jingjiYesNo, yuleYesNO, peilianYesNO, moshiTag, moshihao = "1", moshiName, timeRI, timeSHI, YQtouxiang, YQtab = "0",
            shichang, hezuo, FQHDyaoqing, hezuofeiyong;*/

    private SPUtils spUtils;
    private SPUtileFQTZ spUtileFQTZ;
    //    private List<Map<String, Object>> dataListA;
//    private String touxiangA[];
//    private String qiuA[];
//    private String dengjiA[];

    private List<String> listTXA;
    private List<String> listQA;
    private List<String> listDJA;
    private List<String> listIDA;
    private List<String> listQB;
    private List<String> listTXB;
    private List<String> listDJB;
    private List<String> listIDB;
    private List<FQTZEntity> listF;

    private HDXQAAdapter adapterA;
//    private List<Map<String, Object>> dataListB;
//    private String touxiangB[];
//    private String qiuB[];

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_faqi_tiaozhan);
//    }
    private JSONArray array1;
    private String tagg = "0";
    private List<String> list;
    private String placeTimeLen;
    private String placeMoney;
    private String placeNun;

    private String tagId1 = "1";
    private String timeStart, tagTZ;
    private View my_look_partner, my_have_partner;
    private LinearLayout home_faqi_xiangmu_partner, home_faqi_feiyong_partner, sport_partner, have_sprot_partner, jingj_layout;

    private TextView home_faqi_xiangmu_text_partner, home_faqi_changguan_text_partner, home_faqi_time_text_partner, home_faqi_shichang_text_partner, home_faqi_feiyong_text_partner, have_partner, look_partner, fbu_text, referee_num, cdf_cp, cdf, referee_lv_text, aA, shM;
    private String[] reseree_num;
    private String[] reseree_lv, gef_lv;
    private int num = 0, lv = 0, glv = 0, zhbTag = 1;

    private String selectedStr;
    private String[] valueStr;
    private PickValueView pickString, picks_lv;
    private String tuijian[] = {"距离由近到远", "时间由近到远", "级别由高到低", "级别由低到高", "好评优先"};
    private String cp_fy = "0.0";
    private String select_lv;

    private TextView sport_text_text, lv_text_text, home_faqi_nl_text, sex_text_text;
    private RelativeLayout home_cp, home_faqi_time_partner, feiyong, referee_lv, sport_text, lv_text, home_faqi_nl, sex_text;
    private Boolean isHoudong = false;
    private String datayu;
    private String level;
    private FQYQAdapter fqyqAdapter;
    private FQTZEntity mfqtian;
    private String slv;

    private int pTag = 0;
    private Intent dataa;
    private int jb_int;
    private String cityName, dTag = "0";
    private String nameSport;
    private String jiage;
    private int money4;
    private int money5;
    private int money6;
    private int money7;
    private int money8;
    private int money10;
    private int money9, plnum = 0;
    private List<TheBallEntity.DataBean.CommBean> comm;
    private String nage, yname, wname, znage, zdasportId, wdasportId, wcgname, zcgname, zcgname2;

    private String NLnum, NLnum1, JBnum, JBnum1;

    @Override
    public int initContentView() {
        return R.layout.activity_faqi_tiaozhan;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        gridViewA = findViewById(R.id.home_faqi_grid_a);
        gridViewB = findViewById(R.id.home_faqi_grid_b);
        gridViewC = findViewById(R.id.home_hdxq_grid_c);
//        adapter = new HDXQAAdapter(this);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        moshi = findViewById(R.id.home_faqi_moshi);
        moshi.setOnClickListener(this);
        xingbie = findViewById(R.id.home_faqi_xingbie);
        xingbie.setOnClickListener(this);
        feiyong = findViewById(R.id.home_faqi_feiyong);
        feiyong.setOnClickListener(this);
        changguan = findViewById(R.id.home_faqi_changguan);
        changguan.setOnClickListener(this);
        xiangmu = findViewById(R.id.home_faqi_xiangmu);
        xiangmu.setOnClickListener(this);
        fabu = findViewById(R.id.faqi_tiaozhan_fabu);
        fabu.setOnClickListener(this);
        dashang = findViewById(R.id.home_faqi_dashang);
        dashang.setOnClickListener(this);
        jibie = findViewById(R.id.home_faqi_jibie);
        jibie.setOnClickListener(this);
        xiangmuText = findViewById(R.id.home_faqi_xiangmu_text);
        moshiText = findViewById(R.id.home_faqi_moshi_text);
        bannerView = findViewById(R.id.fqtz_banner);
        changguanText = findViewById(R.id.home_faqi_changguan_text);
        fangshiText = findViewById(R.id.home_faqi_fangshi_text);
        sexText = findViewById(R.id.home_faqi_xingbie_text);
        dashangText = findViewById(R.id.home_faqi_dashang_text);
        youjiantou_aa = findViewById(R.id.youjiantou_aa);

        peilText = findViewById(R.id.home_faqi_peilian_text);
        jibieText = findViewById(R.id.home_faqi_jibie_text);
        time = findViewById(R.id.home_faqi_time);
        time.setOnClickListener(this);
        timeText = findViewById(R.id.home_faqi_time_text);
        timeyjt = findViewById(R.id.home_faqi_time_yjt);
        shichangText = findViewById(R.id.home_faqi_shichang_text);
        feiyongText = findViewById(R.id.home_faqi_feiyong_text);


        peilianText = findViewById(R.id.home_faqi_peilianfei_text);
        peilian = findViewById(R.id.home_faqi_peilianfei);
        zhuanhuanText = findViewById(R.id.home_faqi_zhuanhuan);
        zhuanhuanText.setOnClickListener(this);
        beizhuEdit = findViewById(R.id.home_faqi_beizhu_edit);
        dwd = findViewById(R.id.home_faqi_changguan_dwd);

        cGdwd = findViewById(R.id.home_faqi_changguan_dwd_partner);

        home_faqi_xiangmu_partner = findViewById(R.id.home_faqi_xiangmu_partner);
        home_faqi_xiangmu_partner.setOnClickListener(this);
        home_faqi_xiangmu_text_partner = findViewById(R.id.home_faqi_xiangmu_text_partner);

        home_faqi_changguan_partner = findViewById(R.id.home_faqi_changguan_partner);
        home_faqi_changguan_partner.setOnClickListener(this);

        home_faqi_changguan_text_partner = findViewById(R.id.home_faqi_changguan_text_partner);

        home_faqi_time_partner = findViewById(R.id.home_faqi_time_partner);
        home_faqi_time_partner.setOnClickListener(this);
        home_faqi_time_text_partner = findViewById(R.id.home_faqi_time_text_partner);
        home_faqi_shichang_text_partner = findViewById(R.id.home_faqi_shichang_text_partner);

        home_faqi_feiyong_text_partner = findViewById(R.id.home_faqi_feiyong_text_partner);

        sport_partner = findViewById(R.id.sport_partner);
        have_sprot_partner = findViewById(R.id.have_sprot_partner);

        my_have_partner = findViewById(R.id.my_have_partner);
        my_look_partner = findViewById(R.id.my_look_partner);
        look_partner = findViewById(R.id.look_partner);
        look_partner.setOnClickListener(this);
        have_partner = findViewById(R.id.have_partner);
        have_partner.setOnClickListener(this);
        fbu_text = findViewById(R.id.fbu_text);
        faqi_tiaozhan_yuding = findViewById(R.id.faqi_tiaozhan_yuding);
        faqi_tiaozhan_yuding.setOnClickListener(this);
        beizhu_edit_partner = findViewById(R.id.home_faqi_beizhu_edit_partner);
        sc_layout = findViewById(R.id.sc_layout);
        sc_layout_partner = findViewById(R.id.sc_layout_partner);
        //裁判 home_faqi_referee_text  home_faqi_referee_lv_text home_faqi_feiyong_cp
        jingj_layout = findViewById(R.id.jingj_layout);

        cp_layout = findViewById(R.id.cp_layout);

        cdf_cp_text = findViewById(R.id.cdf_cp_text);
        feiyong_cp = findViewById(R.id.cdf_cp_text2);
        home_cp = findViewById(R.id.home_cp);
        referee_num = findViewById(R.id.home_faqi_referee_text);
        referee_num.setOnClickListener(this);
        referee_lv_text = findViewById(R.id.home_faqi_referee_lv_text);
        referee_lv = findViewById(R.id.home_faqi_referee_lv);
        referee_lv.setOnClickListener(this);

        cdf = findViewById(R.id.cdf);
        cdf_cp = findViewById(R.id.cdf_cp);

        //年龄
        nial_layout = findViewById(R.id.home_faqi_nianl);
        nial_layout.setOnClickListener(this);
        nial_text = findViewById(R.id.home_faqi_nial_text);


        //模式弹窗
        sport_text = findViewById(R.id.sport_text);
        sport_text.setOnClickListener(this);

        sport_text_text = findViewById(R.id.sport_text_text);
        lv_text = findViewById(R.id.lv_text);
        lv_text.setOnClickListener(this);
        lv_text_text = findViewById(R.id.lv_text_text);
        home_faqi_nl = findViewById(R.id.home_faqi_nl);
        home_faqi_nl.setOnClickListener(this);
        home_faqi_nl_text = findViewById(R.id.home_faqi_nl_text);
        sex_text = findViewById(R.id.sex_text);
        sex_text.setOnClickListener(this);
        sex_text_text = findViewById(R.id.sex_text_text);

        //a队 b队 a_duib

        spUtils = new SPUtils();
        spUtileFQTZ = new SPUtileFQTZ();
        token = (String) spUtils.get(this, "logintoken", "");


        // lv = (int) spUtils.get(this, "item_lv", null);
        touxiangimg = (String) spUtils.get(this, "touxiang", "");

        array1 = new JSONArray();

        whezuo = (String) spUtileFQTZ.get(this, "whezuo", "1");

        fqtzXiangmu = (String) spUtileFQTZ.get(this, "fqtzXiangmu", " ");
        fqtzXiangmuda = (String) spUtileFQTZ.get(this, "fqtzXiangmuda", " ");
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", " ");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", " ");

        wfqtzXiangmudaid = (String) spUtileFQTZ.get(this, "wfqtzXiangmudasportId", " ");
        wcgid = (String) spUtileFQTZ.get(this, "wcgid", " ");
        wfqtzXiangmuid = (String) spUtileFQTZ.get(this, "wfqtzXiangmusportId", "");
        moshiName = (String) spUtileFQTZ.get(this, "FQHDmoshiName", "娱乐模式");
        moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
        cgname = (String) spUtileFQTZ.get(this, "cgnameN", " ");
        wcgname = (String) spUtileFQTZ.get(this, "wcgnameN", " ");
        dTag = (String) spUtileFQTZ.get(this, "dianTag", "0");
        JBnum = (String) spUtileFQTZ.get(this, "JBnum", "0");
        JBnum1 = (String) spUtileFQTZ.get(this, "JBnum1", "0");


        NLnum = (String) spUtileFQTZ.get(this, "NLnum", "0");
        NLnum1 = (String) spUtileFQTZ.get(this, "NLnum1", "0");

        cgid = (String) spUtileFQTZ.get(this, "cgid2", " ");
        city = (String) spUtileFQTZ.get(this, "city", " ");
        cgname2 = (String) spUtileFQTZ.get(this, "cgname2", " ");
        //hezuo = (String) spUtileFQTZ.get(this, "hezuo2", " ");

        LogU.Ld("1618", "场馆名称" + wcgname + "==" + cgid + "==" + wcgid + "==" + cgname + "===" + cgname2);
        wyhbXiangmu = (String) spUtils.get(this, "wyhbXiangmu", " ");

        canyurenshu = (String) spUtileFQTZ.get(this, "canyurenshu", "1");
        fangs = (String) spUtileFQTZ.get(this, "FQHDfangshi", "1");

        sex = (String) spUtileFQTZ.get(this, "FQHDsex", "2");
        nL = (String) spUtileFQTZ.get(this, "nL", "");
        jB = (String) spUtileFQTZ.get(this, "jB", "");


        Agemin = (String) spUtileFQTZ.get(this, "Agemin", "1");
        Agemax = (String) spUtileFQTZ.get(this, "Agemax", "99");
        JBmin = (String) spUtileFQTZ.get(this, "JBmin", "1");
        JBmax = (String) spUtileFQTZ.get(this, "JBmax", "10");


        xm_name = (String) spUtileFQTZ.get(this, "xmName", "请选择");
        zXM_name = (String) spUtileFQTZ.get(this, "zXMname", "请选择");
        // zcgname = (String) spUtileFQTZ.get(this, "zcgname", "请选择");
        //  wcgname = (String) spUtileFQTZ.get(this, "wcgname", "请选择");

        LogU.Ld("1618", "场馆======名称" + wcgname);
        LogU.Ld("1608", "级11111别====" + dTag);
        if (!EmptyUtils.isStrEmpty(xm_name)) {
            xiangmuText.setText(xm_name);

        } else {
            xiangmuText.setText("请选择");
        }


        if (!EmptyUtils.isStrEmpty(zXM_name)) {
            home_faqi_xiangmu_text_partner.setText(zXM_name);

        } else {
            xiangmuText.setText("请选择");
        }

        if (!EmptyUtils.isStrEmpty(cgname)) {
            changguanText.setText(cgname);
            dwd.setVisibility(View.VISIBLE);

        } else {
            changguanText.setText("请选择");
            dwd.setVisibility(View.GONE);

        }

        LogU.Ld("1608", "moshiname" + moshiName);
        if (!EmptyUtils.isStrEmpty(moshiName)) {


            if (dTag.equals("0")) {
                moshiText.setText("请选择");
            } else {
                moshiText.setText(moshiName);
            }
        } else {
            moshiText.setText("请选择");
        }

        if (!EmptyUtils.isStrEmpty(wcgname)) {

            home_faqi_changguan_text_partner.setText(wcgname);

            cGdwd.setVisibility(View.VISIBLE);
        } else {
            home_faqi_changguan_text_partner.setText("请选择");
            cGdwd.setVisibility(View.GONE);
        }
        if (!EmptyUtils.isStrEmpty(nL)) {
            nial_text.setText(nL);

        } else {
            nial_text.setText("不限");
        }

        LogU.Ld("1608", "我找陪练" + moshiText.getText().toString() + "===" + moshiName);
        if (moshiName.equals("我找陪练")) {

            if (!EmptyUtils.isStrEmpty(jB)) {
                if (jB.equals("不限")) {
                    JBmin = "4";
                    JBmax = "10";
                    jibieText.setText("4-10级");
                } else {
                    jibieText.setText(jB);
                }

            } else {
                JBmin = "4";
                JBmax = "10";
                jibieText.setText("4-10级");
            }

        } else {

            if (!EmptyUtils.isStrEmpty(jB)) {
                jibieText.setText(jB);

            } else {
                JBmin = "1";
                JBmax = "10";
                jibieText.setText("不限");
            }
        }


        if (!EmptyUtils.isStrEmpty(sex)) {
            if (sex.equals("0")) {
                sexText.setText("男");
            } else if (sex.equals("1")) {
                sexText.setText("女");
            } else if (sex.equals("2")) {
                sexText.setText("不限");
            }
        } else {
            sexText.setText("不限");
        }

        LogU.Ld("1608", "场地费" + moshihao + "  走到这  " + moshiName + "裁判费" + cp_fy);
        listQA = new ArrayList<>();
        listTXA = new ArrayList<>();
        listDJA = new ArrayList<>();
        listIDA = new ArrayList<>();
        mfqtian = new FQTZEntity();

        listF = new ArrayList<>();
        mfqtian.setImgURL(touxiangimg);
        mfqtian.setHeightLevelName(fqtzXiangmuda);
        mfqtian.setHeightLevel(YQdengji);
        mfqtian.setUuid(YQuuid);
        listF.add(mfqtian);
        listQB = new ArrayList<>();
        listTXB = new ArrayList<>();
        listDJB = new ArrayList<>();
        listIDB = new ArrayList<>();


        reseree_num = getResources().getStringArray(R.array.reseree_num);
        reseree_lv = getResources().getStringArray(R.array.reseree_lv);
        gef_lv = getResources().getStringArray(R.array.gef_lv);


        LogU.Ld("1608", "模式" + moshiString);
        // String[] data = getResources().getStringArray(R.array.list);
        //  ArrayList<String> firstList = new ArrayList<>();
        listNL1.add("不限");
        for (int i = 1; i <= 99; i++) {
            String str = "";
            str = i + "";
            listNL1.add(str);
        }
        listNL2.add("不限");
        for (int i = 1; i <= 99; i++) {
            String str = "";
            str = i + "";
            listNL2.add(str);
        }

        listJB1.add("不限");
        for (int i = 1; i <= 10; i++) {
            String str = "";
            str = i + "";
            listJB1.add(str);
        }
        listJB2.add("不限");
        for (int i = 1; i <= 10; i++) {
            String str = "";
            str = i + "";
            listJB2.add(str);
        }


        for (int i = 4; i <= 10; i++) {
            String str = "";
            str = i + "";
            listPL1.add(str);
        }

        for (int i = 4; i <= 10; i++) {
            String str = "";
            str = i + "";
            listPL2.add(str);
        }
        getSportLevel();
        if (!EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(fqtzXiangmudaid)) {
            getTheBall();
        }

    }

    @Override
    protected void initData() {
        biaoti.setText("发布");
        youshangjiao.setText("奖励规则");
        // moshiText.setText(moshiName);
        youshangjiao.setVisibility(View.VISIBLE);
        // changguanText.setText(cgname);
        spUtileFQTZ.remove(this, "YQtouxiang");
        spUtileFQTZ.remove(this, "YQtab");
        listTXA.add(touxiangimg);
        listQA.add(fqtzXiangmuda);

        listDJA.add(level);
        LogU.Ld("1609", "球类项目" + fqtzXiangmuda + listQA.size() + "===" + listQA + "===" + level);
        LogU.Ld("1609", "1609" + num + referee_lv_text.getText().toString());


       /* dashangText.setOnTouchListener(new View.OnTouchListener()
        {

            public boolean onTouch(View v, MotionEvent event)
            {
                int inputType = dashangText.getInputType();
                dashangText.setInputType(InputType.TYPE_NULL);// 让系统键盘不弹出
                payDS();
                dashangText.setInputType(inputType);
                return false;
            }
        });*/

        //限制的位数
        final int digits = 2;

        dashangText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + digits + 1);
                        dashangText.setText(s);
                        dashangText.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    dashangText.setText(s);
                    dashangText.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        dashangText.setText(s.subSequence(0, 1));
                        dashangText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        peilText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + digits + 1);
                        dashangText.setText(s);
                        dashangText.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    dashangText.setText(s);
                    dashangText.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        dashangText.setText(s.subSequence(0, 1));
                        dashangText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String tagId = getIntent().getStringExtra("tagId");
        String ydId = getIntent().getStringExtra("ydId");
        LogU.Le("1608", "是不是场馆" + tagId + "有的" + tagId);

        if (!EmptyUtils.isStrEmpty(tagId)) {
            if (tagId1.equals(tagId)) {
                changguanText.setText("");
                home_faqi_changguan_text_partner.setText("");
                dwd.setVisibility(View.GONE);
                cGdwd.setVisibility(View.GONE);
            }
        }
        if (!EmptyUtils.isStrEmpty(ydId)) {
            if (ydId.equals("1")) {
                nage = getIntent().getStringExtra("wnage");
                wname = getIntent().getStringExtra("wname");
                wdasportId = getIntent().getStringExtra("wdasportId");
                wcgname = getIntent().getStringExtra("wcgname");
                have_partner.setTextColor(getResources().getColor(R.color.my_tab));
                look_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.VISIBLE);
                sport_partner.setVisibility(View.GONE);
                my_look_partner.setVisibility(View.INVISIBLE);
                my_have_partner.setVisibility(View.VISIBLE);
            } else if (ydId.equals("2")) {
                znage = getIntent().getStringExtra("znage");
                yname = getIntent().getStringExtra("zname");
                zdasportId = getIntent().getStringExtra("zdasportId");
                zcgname = getIntent().getStringExtra("zcgname");
                look_partner.setTextColor(getResources().getColor(R.color.my_tab));
                have_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.GONE);
                sport_partner.setVisibility(View.VISIBLE);
                my_have_partner.setVisibility(View.INVISIBLE);
                my_look_partner.setVisibility(View.VISIBLE);
            }
        }
        dataa = getIntent();


        if (!EmptyUtils.isEmpty(dataa.getStringExtra("dataa"))) {
            timeStart = dataa.getStringExtra("dataa");
            placeTimeLen = dataa.getStringExtra("placeTimeLen");
            placeNun = dataa.getStringExtra("placeNun");
            placeMoney = dataa.getStringExtra("placeMoney");
            datayu = dataa.getStringExtra("datayu");
            zcgname2 = dataa.getStringExtra("zcgname2");

            //   LogU.Ld("1608","点我了=====时间"+substring+"====="+time1+"===="+strTime);
            timeText.setText(datayu);
            home_faqi_time_text_partner.setText(datayu);
            home_faqi_shichang_text_partner.setText(placeTimeLen);

            sc_layout.setVisibility(View.VISIBLE);
            sc_layout_partner.setVisibility(View.VISIBLE);
            shichangText.setText(placeTimeLen);
            LogU.Ld("1608", "项目" + placeTimeLen);
            double v = Double.parseDouble(placeMoney);
            String format = String.format("%.2f", v);
            feiyongText.setText("¥" + format);

            cdf_cp_text.setText("¥" + format);
            home_faqi_feiyong_text_partner.setText("¥" + format + " ");
            shichang = placeTimeLen.substring(0, placeTimeLen.indexOf("小"));
            //   dwd.setVisibility(View.VISIBLE);
            // hezuoText.setVisibility(View.VISIBLE);


            if (moshiText.getText().toString().equals("竞技模式")) {
                /*if (!EmptyUtils.isStrEmpty(referee_num.getText().toString())   && !EmptyUtils.isStrEmpty(referee_lv_text.getText().toString()) && !EmptyUtils.isStrEmpty(shichang)) {
                    getCaipanf(r_num, r_lv, fqtzXiangmudaid, shichang);
                }*/
                if (!EmptyUtils.isStrEmpty(cp_fy)) {
                    if (cp_fy.equals("0.0") || cp_fy.equals("0")) {
                        feiyong_cp.setText("¥" + "0.00");
                    } else {
                        feiyong_cp.setText("¥" + cp_fy);
                    }
                }


            }

            LogU.Le("1608", "我的" + placeMoney + cgname + timeStart + "场地号" + placeNun + cp_fy + placeTimeLen + shichang);
        } else {
            timeText.setText("");
            shichangText.setText("");
            feiyongText.setText("");
            cdf_cp_text.setText("");
            // sc_layout.setVisibility(View.GONE);
            // sc_layout_partner.setVisibility(View.GONE);
            LogU.Ld("1608", "项目====" + placeTimeLen);
        }

        LogU.Ld("1608", "changguan=====" + zcgname + "=======" + wcgname + "===" + zcgname2);


        /*if (!EmptyUtils.isStrEmpty(zcgname)) {
            changguanText.setText(zcgname);
            dwd.setVisibility(View.VISIBLE);
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "zcgname", zcgname);

        } else {
            changguanText.setText("请选择");
            dwd.setVisibility(View.GONE);

        }*/

        /*if (!EmptyUtils.isStrEmpty(wcgname)) {

            home_faqi_changguan_text_partner.setText(wcgname);

            cGdwd.setVisibility(View.VISIBLE);
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "wcgname", wcgname);

        } else {
            home_faqi_changguan_text_partner.setText("请选择");
            cGdwd.setVisibility(View.GONE);
        }*/

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tagTZ = bundle.getString("tagTZ");
        LogU.Ld("1608", "项目" + cgname);
        cpNUM = (String) spUtileFQTZ.get(this, "cpNUM", "");
        cp_NUM = (String) spUtileFQTZ.get(this, "cp_NUM", "");
        cpLV = (String) spUtileFQTZ.get(this, "cpLV", "");
        refereeLV = (String) spUtileFQTZ.get(this, "refereeLV", "");
        ref_GLV = (String) spUtileFQTZ.get(this, "ref_GLV", "");
        beiz = (String) spUtileFQTZ.get(this, "beiz", "");
        beiz_partner = (String) spUtileFQTZ.get(this, "beiz_partner", "");

        cp_Ggef = (String) spUtileFQTZ.get(this, "cp_Ggef", "");
        if (!EmptyUtils.isStrEmpty(tagTZ)) {
            if (tagTZ.equals("1")) {
                spUtileFQTZ.remove(this, "cp_NUM");
                spUtileFQTZ.remove(this, "cpLV");
                spUtileFQTZ.remove(this, "cp_Ggef");

                spUtileFQTZ.remove(this, "beiz");
                spUtileFQTZ.remove(this, "beiz_partner");
                if (fqtzXiangmuda.equals("高尔夫")) {
                    referee_lv_text.setText("初级");
                } else {
                    referee_lv_text.setText("三级");
                }
                referee_num.setText("0人");


            }
        } else {
            if (!EmptyUtils.isStrEmpty(cp_NUM)) {
                referee_num.setText(cp_NUM + "人");
                int i = Integer.parseInt(cp_NUM);
                num = i;
            } else {
                referee_num.setText("0人");
            }

            if (fqtzXiangmuda.equals("高尔夫")) {
                if (!EmptyUtils.isStrEmpty(cp_Ggef) && !EmptyUtils.isStrEmpty(ref_GLV)) {
                    referee_lv_text.setText(cp_Ggef);
                    int i = Integer.parseInt(ref_GLV);
                    LogU.Ld("1608", "高尔夫等级" + i + "====" + ref_GLV);
                    lv = i;
                } else {
                    referee_lv_text.setText("初级");

                }
            } else {
                if (!EmptyUtils.isStrEmpty(cpLV) && !EmptyUtils.isStrEmpty(refereeLV)) {
                    referee_lv_text.setText(cpLV);
                    int i = Integer.parseInt(refereeLV);
                    lv = i;
                } else {
                    referee_lv_text.setText("三级");
                }
            }


            beizhuEdit.setText(beiz);
            beizhu_edit_partner.setText(beiz_partner);
            LogU.Ld("1608", "高尔夫等级" + "====" + ref_GLV + fqtzXiangmuda + "====" + fqtzXiangmudaid);
            String s = referee_lv_text.getText().toString();
            getCaipanf(cp_NUM + "", s, fqtzXiangmudaid, shichang);
            LogU.Ld("1608", "===费用====" + cp_NUM + "====" + s + "====" + fqtzXiangmudaid + "====" + shichang);

        }


        if (moshiName.equals("竞技模式")) {
            zhuanhuanText.setText("打赏费用");

            cp_layout.setVisibility(View.VISIBLE);
            home_cp.setVisibility(View.VISIBLE);
            cdf_cp.setVisibility(View.VISIBLE);
            youjiantou_aa.setVisibility(View.VISIBLE);
            peilText.setVisibility(View.GONE);
            dashangText.setVisibility(View.VISIBLE);

            pTag = 0;
            if (referee_num.getText().toString().equals("0人")) {
                jingj_layout.setVisibility(View.GONE);
                cdf.setVisibility(View.VISIBLE);
            } else {
                jingj_layout.setVisibility(View.VISIBLE);
                cdf.setVisibility(View.GONE);
            }
            if (!EmptyUtils.isStrEmpty(referee_num.getText().toString())) {
                if (referee_num.getText().toString().equals("0人")) {
                    cp_layout.setVisibility(View.GONE);
                } else {
                    cp_layout.setVisibility(View.VISIBLE);
                }
            } else {
                cp_layout.setVisibility(View.GONE);
            }

            if (!EmptyUtils.isStrEmpty(fangs)) {
                if (fangs.equals("1")) {
                    fangshiText.setText("AA");
                    //   aa.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                } else if (fangs.equals("2")) {
                    fangshiText.setText("输方买单");
                    //  shumai.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                }
            } else {
                fangs = "1";
                fangshiText.setText("AA");
            }
        } else if (moshiName.equals("娱乐模式")) {
            zhuanhuanText.setText("打赏费用");
            spUtileFQTZ.remove(this, "FQHDfangshi");
            fangs = "1";

            fangshiText.setText("AA");
            peilText.setVisibility(View.GONE);
            dashangText.setVisibility(View.VISIBLE);
            home_cp.setVisibility(View.GONE);
            cdf.setVisibility(View.VISIBLE);
            jingj_layout.setVisibility(View.GONE);
            cdf_cp.setVisibility(View.GONE);
            cp_layout.setVisibility(View.GONE);
            cp_fy = "0.0";
            num = 0;
            pTag = 0;

        } else if (moshiName.equals("我找陪练")) {
            zhuanhuanText.setText("陪练费用");
            spUtileFQTZ.remove(this, "FQHDfangshi");
            fangs = "1";
            fangshiText.setText("AA");
            peilText.setVisibility(View.VISIBLE);
            dashangText.setVisibility(View.GONE);
            home_cp.setVisibility(View.GONE);
            cdf.setVisibility(View.VISIBLE);
            cdf_cp.setVisibility(View.GONE);
            cp_layout.setVisibility(View.GONE);
            jingj_layout.setVisibility(View.GONE);
            cp_fy = "0.0";
            num = 0;

            pTag = 1;
            if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(placeMoney)) {
                getAccmoney(JBmin, fqtzXiangmudaid, city, shichang, placeMoney);
            }
        } else if (moshiName.equals("我是陪练")) {
            zhuanhuanText.setText("陪练费用");
            spUtileFQTZ.remove(this, "FQHDfangshi");
            fangs = "1";
            fangshiText.setText("AA");
            peilText.setVisibility(View.VISIBLE);
            dashangText.setVisibility(View.GONE);
            home_cp.setVisibility(View.GONE);
            cdf.setVisibility(View.VISIBLE);
            cdf_cp.setVisibility(View.GONE);
            cp_layout.setVisibility(View.GONE);
            jingj_layout.setVisibility(View.GONE);
            cp_fy = "0.0";
            num = 0;

            pTag = 0;
            if (!EmptyUtils.isStrEmpty(placeTimeLen) && !EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(placeMoney)) {
                getLeves();


            }
        } else {
            jingj_layout.setVisibility(View.VISIBLE);
            home_cp.setVisibility(View.GONE);
            cdf.setVisibility(View.VISIBLE);
            cdf_cp.setVisibility(View.GONE);
            cp_layout.setVisibility(View.GONE);
            cp_fy = "0.0";
        }

        Integer value[] = new Integer[20];
        for (int i = 0; i < value.length; i++) {
            value[i] = i + 1;
        }

        //    pickString.setValueData(value, value[1]);
        getHuodBanner();


        if (zhbTag == 1) {
           /* if (fqtzXiangmu.equals("中式黑八")) {
                xiangmuText.setText("中式黑八");

            } else if (fqtzXiangmu.equals("美式9球")) {
                xiangmuText.setText("美式9球");

            } else if (fqtzXiangmu.equals("斯诺克")) {
                xiangmuText.setText("斯诺克");

            } else if (fqtzXiangmu.equals("比杆赛")) {
                xiangmuText.setText(fqtzXiangmuda + "比杆");

            } else if (fqtzXiangmu.equals("比洞赛")) {
                xiangmuText.setText(fqtzXiangmuda + "比洞");

            } else {
                if (!fqtzXiangmu.equals("练习")) {
                    xiangmuText.setText(fqtzXiangmuda + fqtzXiangmu);
                } else {
                    xiangmuText.setText("");
                }

            }*/
            // xiangmuText.setText(wname+nage);
        } else if (zhbTag == 2) {
            // home_faqi_xiangmu_text_partner.setText(yname + "  " + nage);

            //  spUtileFQTZ.put(FaqiTiaozhanActivity.this, "zXM_name", zXM_name);
        }
        if (!EmptyUtils.isStrEmpty(yname) && !EmptyUtils.isStrEmpty(znage)) {
            xiangmuText.setText(yname + znage);
            String xm_name = xiangmuText.getText().toString();
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "xmName", xiangmuText.getText().toString());
        } else {
            xiangmuText.setText("请选择");
        }
        if (!EmptyUtils.isStrEmpty(wname) && !EmptyUtils.isStrEmpty(nage)) {
            home_faqi_xiangmu_text_partner.setText(wname + "   " + nage);
            String zXM_name = home_faqi_xiangmu_text_partner.getText().toString();
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "zXMname", home_faqi_xiangmu_text_partner.getText().toString());
        } else {
            xiangmuText.setText("请选择");
        }
         LogU.Ld("1608", "changguan=====" + zXM_name + "=======" + xm_name + "===" + nage + "====" + wname + "====" + yname + "===" + znage);
        /*if (moshihao.equals("3")  ) {
            zhuanhuanText.setText("陪练费用");
            peilText.setVisibility(View.VISIBLE);
            dashangText.setVisibility(View.GONE);
            LogU.Le("1608", "陪练费用=====" + cgname+moshihao);
        } else if (moshihao.equals("4")){
            zhuanhuanText.setText("打赏费用");
            peilText.setVisibility(View.GONE);
            dashangText.setVisibility(View.VISIBLE);
        }*/
//        gridViewA.setAdapter(adapter);
//        gridViewB.setAdapter(adapter);
//        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent();
//                intent.setClass(FaqiTiaozhanActivity.this,YaoqingActivity.class);
//                startActivity(intent);
//            }
//        });


        qiuleishuju();


        initjihe();
        LogU.Ld("1609", "1609==" + num);
        adapterC = new FQTZCPAdapter(this, num);
        gridViewC.setAdapter(adapterC);

        adapterC.notifyDataSetChanged();
    }


    private void initjihe() {

//        FQHDyaoqing = (String) spUtileFQTZ.get(this, "FQHDyaoqing", "");
//        if (FQHDyaoqing.length()>3){
//            JSONArray  array1 = JSONArray.fromObject();
//
//        }
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();


        if (YQtab.equals("1")) {
            LogU.Ld("1608", "走了1  返回" + YQtab);

            listTXA.add(YQtouxiang);
            listQA.add(fqtzXiangmuda);
            listDJA.add(YQdengji);
            listIDA.add(YQuuid);

            try {
                object2.put("team", "1");
                object2.put("uuid", YQuuid);
                array1.put(object2);
            } catch (Exception e) {


            }
        } else if (YQtab.equals("2")) {
            LogU.Ld("1608", "走了2  返回" + YQtab);

            listTXB.add(YQtouxiang);
            listQB.add(fqtzXiangmuda);
            listDJB.add(YQdengji);
            listIDB.add(YQuuid);
            try {
                object2.put("team", "2");
                object2.put("uuid", YQuuid);
                array1.put(object2);
            } catch (Exception e) {


            }
        }


//        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDyaoqing",  array1.toString());
//        array1.put(object3);
        System.out.println(array1.toString());
        LogU.Ld("1608", "邀请人拼接json------" + array1.toString());
//        qiuB[0] = "羽毛球";

    }

    //发起挑战
    private void init() {
        if (array1.toString().length() < 10) {
            FQHDyaoqing = "";
        } else {
            FQHDyaoqing = array1.toString();
        }
        /*String changdifei;
        if (TextUtils.isEmpty(feiyongEdit.getText())) {
            changdifei = "0";
        } else {
            changdifei = feiyongEdit.getText().toString() + "";
        }*/
        /*LogU.Ld("1608", "token" + token + "一级运动ID" + fqtzXiangmudaid + "二级运动ID" + fqtzXiangmuid + "运动模式" + moshihao
                + "场馆ID" + cgid + "场地费支付方式" + fangshi + "成员性别" + sex + "开始时间" + timeRI + timeSHI + "打赏费" + dashangString
                + "陪练费" + peilianString + "场地费" + feiyongEdit.getText().toString()+"时长" + shichang+"邀请" + FQHDyaoqing );*/
//        array1.toString()
        LogU.Le("1608", "我的参数" + placeMoney + cgname + timeStart + "场地号" + placeNun + "场馆" + cgid + "场馆费" + cp_fy + "裁判等级" + reseree_lv[lv] + "==");
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAddActivity")
                .addHeader("token", token)
                .addParams("sportid", fqtzXiangmudaid + "")//一级运动ID
                .addParams("sportType", fqtzXiangmuid + "")//二级运动ID
                .addParams("SportMode", moshihao)// 运动模式 1娱乐 2竞技 3陪练 4找陪练
                .addParams("siteUid", cgid)//场馆ID
                .addParams("StartTime", timeStart)//开始时间
                .addParams("PlayTime", placeTimeLen)//时长
                .addParams("SiteMoney", placeMoney + "")//场地费
                .addParams("PaySiteMoneyType", fangs)//场地费支付方式
                .addParams("teamSex", sex) //成员性别
                .addParams("LevelMin", JBmin)//成员最低技术等级
                .addParams("LevelMax", JBmax)//成员最高技术等级
                .addParams("Tips", dashangString)//打赏费
                .addParams("comments", beizhuEdit.getText().toString())// 备注
                .addParams("member", FQHDyaoqing)//发布前邀请的人 可为空 如果有 则为json数据 如[{"team":"1","uuid":"b60d8e06-1ff3-f048-d42f-49f42b7f0e2b"},{"team":"2","uuid":"99f24ba3-4e4f-ab35-d546-369ffa453884"}]
                .addParams("MoneyPerhour", peilianString)//陪练费
                .addParams("payType", "balance")
                .addParams("venueid", placeNun + "")
                .addParams("refereefee", cp_fy + "")//裁判总费用
                .addParams("RefereeNumber", num + "")//裁判人数
                .addParams("Refereegrade", reseree_lv[lv])//裁判等级
                .addParams("Agemin", Agemin)//裁判等级 低
                .addParams("Agemax", Agemax + "")//裁判等级 高
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战阴" + response);
                        LogU.Le("1608", "我的参数" + placeMoney + cgname + timeStart + "场地号" + placeNun);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            ZhifubaoEntity entity = gson.fromJson(result, ZhifubaoEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();//传值
                            Intent intent = new Intent();
                            intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuCGActivity.class);
                            bundle.putString("tag", "1");
                            bundle.putString("moshihao", moshihao);
                            bundle.putString("shichang", shichang);
                            bundle.putString("uuid", entity.getData().getUuid());
                            intent.putExtras(bundle);
                            startActivity(intent);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                        }
                    }
                });
    }


    //球类数据
    private void qiuleishuju() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAllSportLst")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "球类数据" + result);
                        Boolean a = result.indexOf("2000") != -1;

//                        if (a) {
//                            Gson gson = new Gson();
//                            CJHDEntity entity = gson.fromJson(result, CJHDEntity.class);
//                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.setClass(FaqiTiaozhanActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//
//
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {

//            case R.id.home_faqi_dashang://打赏
//                ArrayList<String> list = new ArrayList<>();
//                for (int i = 0; i < 90; i++) {
//                    String s = "";
////                    if(i<10){
////                        s = "0"+i;
////                    }else{
//                    s = i + "";
////                    }
//                    list.add(s);
//                }
////        String[] ss = (String[]) list.toArray();
//                SinglePicker<String> picker = new SinglePicker<>(this, list);
//                picker.setCanLoop(false);//不禁用循环
//                picker.setLineVisible(true);
//                picker.setTextSize(18);
//                picker.setSelectedIndex(8);
//                picker.setWheelModeEnable(false);
//                //启用权重 setWeightWidth 才起作用
//                picker.setLabel("元");
//                picker.setWeightEnable(true);
//                picker.setWeightWidth(1);
//                picker.setSelectedTextColor(Color.GREEN);//前四位值是透明度
//                picker.setUnSelectedTextColor(Color.RED);
//                picker.setSelectedTextColor(0xFF999999);//中间滚动项文字颜色
//                picker.setUnSelectedTextColor(0xFF999999);//中间滚动项文字颜色
//
////                picker.setOnSingleWheelListener(new OnSingleWheelListener() {//滑动监听
////                    @Override
////                    public void onWheeled(int index, String item) {
//////                        Toast.makeText(FaqiTiaozhanActivity.this, "index=" + index + ", item=" + item, Toast.LENGTH_SHORT).show();
////                        dashangText.setText(index+"元/人aaaa");
////                    }
////                });
//                picker.setOnItemPickListener(new OnItemPickListener<String>() {
//                    @Override
//                    public void onItemPicked(int index, String item) {
////                        Toast.makeText(FaqiTiaozhanActivity.this, "index=" + index + ", item=" + item, Toast.LENGTH_SHORT).show();
//                        dashangText.setText(item + "元");
//                        dashangString = item;
//                    }
//                });
//                picker.show();
//                break;
            case R.id.home_faqi_jibie://级别

                if (pTag == 1) {
                    showChooseJBDialog(listPL1, listPL2);
                } else {
                    showChooseJBDialog(listJB1, listJB2);
                }


               /* LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

                    @Override
                    public boolean isOnlyTwo() {
                        return true;
                    }

                    @Override
                    public List<String> provideFirstData() {
                        ArrayList<String> firstList = new ArrayList<>();
                        if (moshiText.getText().toString().equals("我找陪练")) {
                            for (int i = 4; i <= 10; i++) {
                                String str = "";
                                str = i + "";
                                firstList.add(str);
                            }
                        } else {
                            for (int i = 1; i <= 10; i++) {
                                String str = "";
                                str = i + "";
                                firstList.add(str);
                            }
                        }


                        return firstList;
                    }

                    @Override
                    public List<String> provideSecondData(int firstIndex) {
                        ArrayList<String> secondList = new ArrayList<>();
                        if (moshiText.getText().toString().equals("我找陪练")) {
                            for (int i = 4; i <= 10; i++) {
                                String str = "";

                                str = i + "";
                                secondList.add(str);
                            }
                        } else {
                            for (int i = 1; i <= 10; i++) {
                                String str = "";

                                str = i + "";
                                secondList.add(str);
                            }
                        }


                        return secondList;
                    }

                    @Override
                    public List<String> provideThirdData(int firstIndex, int secondIndex) {
                        return null;
                    }

                };
                LinkagePicker picker1 = new LinkagePicker(this, provider);
                picker1.setCanLoop(false);
                picker1.setLabel("—", " ");
                picker1.setSelectedIndex(0, 8);
                //picker.setSelectedItem("12", "9");
                picker1.setOnMoreItemPickListener(new OnMoreItemPickListener<String>() {

                    @Override
                    public void onItemPicked(String first, String second, String third) {
                        if (Integer.parseInt(first) > Integer.parseInt(second)) {
                            ToastUitl.longs("高级别不能低于低级别");
                        } else {
                            Toast.makeText(FaqiTiaozhanActivity.this, (first + "-" + second + "-" + third), Toast.LENGTH_SHORT).show();
                            zuidi = first;
                            zuigao = second;
                            jibieText.setText(first + "级—" + second + "级");
                        }

                    }
                });
                picker1.show();*/

                break;


            case R.id.home_faqi_nianl://年龄
                showChooseDialog(listNL1, listNL2);

                break;
            case R.id.fanhui://返回
                finish();
                break;

            case R.id.sport_text://运动模式
                LogU.Ld("1608", "==s=====");
                yaoQXZ_y();
                break;
            case R.id.lv_text://技术级别
                LogU.Ld("1608", "==s=====");
                yaoQXZ_js();
                break;
            case R.id.home_faqi_nl://年龄要求
                LogU.Ld("1608", "==s=====");
                yaoQXZ_nl();
                break;
            case R.id.sex_text://性别要求
                LogU.Ld("1608", "==s=====");
                yaoQXZ_xb();
                break;
            case R.id.home_faqi_zhuanhuan://打赏费用
                LogU.Ld("1608", "==s=====");
                if (zhuanhuanText.getText().toString().equals("打赏费用")) {
                    yaoQXZ_ds();
                } else {
                    yaoQXZ_pl();
                }
                break;

            case R.id.youshangjiao:

                intent.setClass(this, JLGZActivity.class);
                startActivity(intent);
                break;
            case R.id.home_faqi_moshi://模式选择
                if (EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim()) || xiangmuText.getText().toString().equals("请选择")) {
                    ToastUitl.longs("请选择运动项目");
                } else {
                    setDialog(R.layout.fqtz_moshi, "1");

                    LogU.Ld("1608", "选择模式" + moshihao);
                  /*  fangs = "1";
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "1");
                    fangshiText.setText("AA");*/
                }
                break;
            case R.id.home_faqi_xingbie://性别选择
                setDialog(R.layout.fqtz_xingbie, "3");
                break;
            case R.id.home_faqi_feiyong://费用方式选择

                if (moshiText.getText().toString().equals("竞技模式")) {
                    setDialog(R.layout.fqtz_feiyong, "2");
                } else {

                }
                break;
//            case R.id.fqtz_dialog_quxiao://窗口取消
//                mCameraDialog.dismiss();
//                break;
            case R.id.fqtz_ms_yule:
                if (yuleYesNO.equals("1")) {
//                    moshiString = "1";
                    mCameraDialog.dismiss();
                    moshiText.setText("娱乐模式");

                    dianTag = 1;
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "dianTag", "1");
                    youjiantou_aa.setVisibility(View.GONE);
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "娱乐模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "1");


                    LogU.Ld("1609", "1609娱乐模式" + num + referee_num.getText().toString());
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("打赏费用");
                    peilText.setVisibility(View.GONE);
                    dashangText.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                    fqyqAdapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                    home_cp.setVisibility(View.GONE);
                    cdf_cp.setVisibility(View.GONE);
                    cdf.setVisibility(View.VISIBLE);

                    cp_layout.setVisibility(View.GONE);
                    jingj_layout.setVisibility(View.GONE);
                    peilText.setText("");
                    dashangText.setText("");
                    cp_fy = "0.0";
                    lv = 0;
                    num = 0;
                    pTag = 0;

                    fangs = "1";
                    fangshiText.setText("AA");
                    spUtileFQTZ.remove(this, "JBmin");
                    spUtileFQTZ.remove(this, "JBmax");

                    spUtileFQTZ.remove(this, "JBnum");
                    spUtileFQTZ.remove(this, "JBnum1");
                    JBmin = "1";
                    JBmax = "10";
                    jibieText.setText("不限");
                    LogU.Ld("1608", "付款方式" + fangs);
                } else {

                    // Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_jingji:
//                jingji.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                if (jingjiYesNo.equals("1")) {
                    dianTag = 1;
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "dianTag", "1");
//                    moshiString = "2";
                    mCameraDialog.dismiss();
                    moshiText.setText("竞技模式");
                    youjiantou_aa.setVisibility(View.VISIBLE);
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "竞技模式");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "2");
                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("打赏费用");

                    peilText.setVisibility(View.GONE);
                    dashangText.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                    fqyqAdapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                    home_cp.setVisibility(View.VISIBLE);
                    referee_num.setText("0人");
                    num = 0;
                    lv = 0;
                    pTag = 0;

                    spUtileFQTZ.remove(this, "JBmin");
                    spUtileFQTZ.remove(this, "JBmax");

                    spUtileFQTZ.remove(this, "JBnum");
                    spUtileFQTZ.remove(this, "JBnum1");
                    JBmin = "1";
                    JBmax = "10";
                    jibieText.setText("不限");

                    LogU.Ld("1608", "竞技模式=======" + fangs);
                    fangs = "1";
                    fangshiText.setText("AA");
                    /*if (!EmptyUtils.isStrEmpty(fangs)) {
                        if (fangs.equals("1")) {
                            fangshiText.setText("AA");
                            //   aa.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        } else if (fangs.equals("2")) {
                            fangshiText.setText("输方买单");
                            //  shumai.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }
                    } else {
                        fangs = "1";
                        fangshiText.setText("AA");
                    }*/

                    if (referee_num.getText().toString().equals("0人")) {
                        jingj_layout.setVisibility(View.GONE);
                        cdf.setVisibility(View.VISIBLE);
                    } else {
                        jingj_layout.setVisibility(View.VISIBLE);
                        cdf.setVisibility(View.GONE);
                    }


                    if (fqtzXiangmuda.equals("高尔夫")) {
                        referee_lv_text.setText("初级");
                    } else {
                        referee_lv_text.setText("三级");
                    }
                    cdf_cp.setVisibility(View.VISIBLE);

                    if (!EmptyUtils.isStrEmpty(referee_num.getText().toString())) {
                        if (referee_num.getText().toString().equals("0人")) {
                            cp_layout.setVisibility(View.GONE);
                        } else {
                            cp_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        cp_layout.setVisibility(View.GONE);
                    }

                    // cp_layout.setVisibility(View.VISIBLE);

                    peilText.setText("");
                    dashangText.setText("");

                    LogU.Ld("1608", "裁判人数" + referee_num.getText().toString());
                    /*if (!EmptyUtils.isStrEmpty(referee_num.getText().toString())   && !EmptyUtils.isStrEmpty(referee_lv_text.getText().toString()) && !EmptyUtils.isStrEmpty(shichang)) {
                        LogU.Ld("1608", "费用" + shichang + referee_num.getText().toString());
                        getCaipanf(r_num, r_lv, fqtzXiangmudaid, shichang);
                        feiyong_cp.setVisibility(View.VISIBLE);
                    }*/

                } else {
                    // Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_woshi:
                if (fqtzqiurenshu.equals("2")) {

                    dianTag = 1;
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "dianTag", "1");
                    getAccompanyuser();


                } else {
                    // Toast.makeText(FaqiTiaozhanActivity.this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fqtz_ms_wozhao:
                if (fqtzqiurenshu.equals("2")) {

                    dianTag = 1;
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "dianTag", "1");

                    mCameraDialog.dismiss();
                    moshiText.setText("我找陪练");
                    youjiantou_aa.setVisibility(View.GONE);
                    cp_layout.setVisibility(View.GONE);
                    home_cp.setVisibility(View.GONE);
                    cdf_cp.setVisibility(View.GONE);
                    cdf.setVisibility(View.VISIBLE);
                    jingj_layout.setVisibility(View.GONE);

                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "我找陪练");
                    spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "4");


                    xingbie.setVisibility(View.VISIBLE);
                    jibie.setVisibility(View.VISIBLE);
                    zhuanhuanText.setText("陪练费用");
                    peilText.setVisibility(View.VISIBLE);
                    dashangText.setVisibility(View.GONE);
                    fqyqAdapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                    adapterB.notifyDataSetChanged();
                    peilText.setText("");
                    dashangText.setText("");
                    cp_fy = "0.0";
                    num = 0;
                    lv = 0;

                    pTag = 1;
                    fangs = "1";
                    fangshiText.setText("AA");
                    spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBmin");
                    spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBmax");

                    spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBnum");
                    spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBnum1");
                    JBmin = "4";
                    JBmax = "10";
                       /* if (!EmptyUtils.isStrEmpty(placeTimeLen) && !EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(placeMoney)) {
                            getLeves();
                        }*/
                    jibieText.setText(JBmin + "-10级");
                    if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(placeMoney)) {
                        getAccmoney(JBmin, fqtzXiangmudaid, city, shichang, placeMoney);
                    }


                } else {
                    // Toast.makeText(this, "此项运动不支持的模式", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fatz_feiyong_aa://费用模式 AA
                fangs = "1";
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "1");
                mCameraDialog.dismiss();
                fangshiText.setText("AA");
                break;
            case R.id.fatz_feiyong_shumai:
                fangs = "2";
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDfangshi", "2");
                mCameraDialog.dismiss();
                fangshiText.setText("输方买单");
                break;
            case R.id.fqtz_xingbie_nan:
                sex = "0";
                mCameraDialog.dismiss();
                sexText.setText("男");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "0");
                break;
            case R.id.fqtz_xingbie_nv:
                sex = "1";
                mCameraDialog.dismiss();
                sexText.setText("女");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "1");
                break;
            case R.id.fqtz_xingbie_buxian:
                sex = "2";
                mCameraDialog.dismiss();
                sexText.setText("不限");
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDsex", "2");
                break;
            case R.id.home_faqi_changguan://场馆跳转
                if (Utils.isFastClick()) {
                    if (!EmptyUtils.isStrEmpty(token)) {
                        if (EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim()) || xiangmuText.getText().toString().equals("请选择")) {
                            ToastUitl.longs("请选择运动项目");
                        } else if (EmptyUtils.isStrEmpty(moshiText.getText().toString().trim()) || moshiText.getText().toString().equals("请选择")) {
                            ToastUitl.longs("请选择运动模式");
                        } else {
                            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "beiz", beizhuEdit.getText().toString());

                            intent.setClass(this, ChuangguanActivity.class);
                            spUtils.put(this, "yId", "2");
                            bundle.putString("yId", "2");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } else {
                        intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                        startActivity(intent);
                    }

                }
                break;
            case R.id.home_faqi_xiangmu://项目选择

                if (!EmptyUtils.isStrEmpty(token)) {
                    intent.setClass(this, FQTZXiangmuActivity.class);
                    bundle.putString("tab", fqtzXiangmudaid);
                    bundle.putString("zId", "0");
                    spUtils.put(this, "zId", "0");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                break;


            case R.id.faqi_tiaozhan_fabu://发布跳转

                if (!EmptyUtils.isStrEmpty(token)) {
                    if (Utils.isFastClick()) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        DecimalFormat ef = new DecimalFormat("0.0000");
                        moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
                        if (moshihao.equals("3")) {
                            if (TextUtils.isEmpty(peilText.getText())) {
                                dashangString = "0.0";
                                peilianString = "0.0";
                                LogU.Le("1608", "打赏费1用" + moshihao + "===" + "====" + dashangText.getText().toString() + "===" + dashangText.getText() + "===" + dashangString + "===" + peilianString);

                            } else {
                                peilianString = peilText.getText().toString() + "";
                                dashangString = "0.0";
                                LogU.Le("1608", "打赏费2用" + moshihao + "===" + dashangString + "===" + peilianString);

                            }
                        } else if (moshihao.equals("4")) {
                            if (TextUtils.isEmpty(peilText.getText())) {
                                peilianString = "0.0";
                                dashangString = "0.0";
                            } else {
                                peilianString = peilText.getText().toString() + "";
                                dashangString = "0.0";
                            }
                        } else if (moshihao.equals("1")) {
                            if (TextUtils.isEmpty(dashangText.getText())) {

                                dashangString = "0.0";
                            } else {
                                dashangString = dashangText.getText().toString() + "";

                            }
                            peilianString = "0.0";
                        } else if (moshihao.equals("2")) {
                            if (TextUtils.isEmpty(dashangText.getText())) {

                                dashangString = "0.0";
                            } else {
                                dashangString = dashangText.getText().toString() + "";

                            }
                            peilianString = "0.0";
                        }

                        //  LogU.Le("1608", "请选择运动项目" + xiangmuText.getText().toString() + "==="+moshiText.getText().toString() + "====" + dashangText.getText().toString() + "===" + changguanText.getText().toString() + "===" + EmptyUtils.isStrEmpty(timeText.getText().toString());
                        // LogU.Le("1608", "打赏费2用" +  xiangmuText.getText().toString() + "==="+moshiText.getText().toString() + "====" + dashangText.getText().toString() + "===" + changguanText.getText().toString() + "===" + EmptyUtils.isStrEmpty(timeText.getText().toString()));
                        LogU.Ld("1608", "请选择运动项目" + xiangmuText.getText().toString() + "===" + moshiText.getText().toString() + "====" + dashangText.getText().toString() + "===" + changguanText.getText().toString() + "===" + EmptyUtils.isStrEmpty(timeText.getText().toString()));


                        if (EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim()) || xiangmuText.getText().toString().equals("请选择")) {
                            ToastUitl.longs("请选择运动项目");
                        } else if (EmptyUtils.isStrEmpty(moshiText.getText().toString().trim()) || moshiText.getText().toString().equals("请选择")) {
                            ToastUitl.longs("请选择运动模式");
                        } else if (EmptyUtils.isStrEmpty(changguanText.getText().toString().trim()) || changguanText.getText().toString().equals("请选择")) {
                            ToastUitl.longs("请选择运动场馆");
                        } else if (EmptyUtils.isStrEmpty(timeText.getText().toString().trim())) {
                            ToastUitl.longs("请选择开始时间");
                        } else {
                            // if (hezuo.equals("1")) {

                            //合作 一定跳支付

                            LogU.Ld("1608", "1608" + moshihao);
                            if (moshihao.equals("1") || moshihao.equals("3") || moshihao.equals("4")) {
                                cp_fy = "0.0";
                                num = 0;
                            }
                            if (dashangString.equals("0.0") || dashangString.equals("0") || dashangString.equals("0.00") || dashangString.equals("0.")) {
                                dashangString = "0.0";
                            }
                            if (peilianString.equals("0.0") || peilianString.equals("0") || peilianString.equals("0.00") || peilianString.equals("0.")) {
                                peilianString = "0.0";
                            }

                            getjudgeTime();

/*
                    } else {

                    }*/
                        }
                        LogU.Le("1608", "裁判费我的参数" + moshihao + "===" + moshiName + "==" + fangs + "====" + "裁判等级" + reseree_lv[lv] + lv + "====");
                        LogU.Le("1608", "打赏费用" + moshihao + "===" + dashangString + "===" + peilianString);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.home_faqi_time://时间
                if (!EmptyUtils.isStrEmpty(token)) {
                    String s = changguanText.getText().toString();
                    LogU.Le("1608", "sdfsd" + s + "值" + EmptyUtils.isStrEmpty(xiangmuText.getText().toString()) + xiangmuText.getText().toString());
                    if (EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim()) || xiangmuText.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动项目");
                    } else if (EmptyUtils.isStrEmpty(moshiText.getText().toString().trim()) || moshiText.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动模式");
                    } else if (EmptyUtils.isStrEmpty(changguanText.getText().toString().trim()) || changguanText.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动场馆");
                    } else {


                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "beiz", beizhuEdit.getText().toString());

                        intent.setClass(FaqiTiaozhanActivity.this, StartTimeActivity.class);
                        intent.putExtra("zId", "0");
                    /*intent.putExtra("yId", "2");
                    intent.putExtra("cgid", cgid);
                    intent.putExtra("cgname", cgname);
                    intent.putExtra("city", city);*/
                        bundle.putString("cgid", cgid);
                        bundle.putString("cgname", fqtzXiangmudaid);
                        bundle.putString("city", fqtzXiangmuid);

                        spUtils.put(this, "yId", "2");
                        bundle.putString("yId", "2");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                //  }

                break;


            case R.id.home_faqi_xiangmu_partner://伙伴运动项目
                if (!EmptyUtils.isStrEmpty(token)) {

                    int yd = 0;
                    intent.setClass(this, FQTZXiangmuActivity.class);
                    bundle.putString("tab", fqtzXiangmudaid);
                    spUtils.put(this, "yId", "1");
                    bundle.putString("zId", "1");
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                break;

            case R.id.home_faqi_changguan_partner://伙伴运动场馆
                if (!EmptyUtils.isStrEmpty(token)) {

                    if (EmptyUtils.isStrEmpty(home_faqi_xiangmu_text_partner.getText().toString().trim()) || home_faqi_xiangmu_text_partner.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动项目");
                    } else {



                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "beiz_partner", beizhu_edit_partner.getText().toString());

                        intent.setClass(this, ChuangguanActivity.class);
                        spUtils.put(this, "yId", "1");

                        bundle.putString("yId", "1");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                break;

            case R.id.home_faqi_time_partner://伙伴运动开始时间

                if (!EmptyUtils.isStrEmpty(token)) {
                    String ss = changguanText.getText().toString();
                    LogU.Le("1608", "sdfsd" + ss + "值" + EmptyUtils.isStrEmpty(changguanText.getText().toString()));

                    if (EmptyUtils.isStrEmpty(home_faqi_xiangmu_text_partner.getText().toString().trim()) || home_faqi_xiangmu_text_partner.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动项目");
                    } else if (EmptyUtils.isStrEmpty(home_faqi_changguan_text_partner.getText().toString().trim()) || home_faqi_changguan_text_partner.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动场馆");
                    } else {

                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "beiz_partner", beizhu_edit_partner.getText().toString());

                        intent.setClass(FaqiTiaozhanActivity.this, StartTimeActivity.class);
                        spUtils.put(this, "yId", "1");
                        bundle.putString("yId", "1");
                        bundle.putString("wcgid", wcgid);
                        bundle.putString("wfqtzXiangmudaid", wfqtzXiangmudaid);
                        bundle.putString("wfqtzXiangmuid", wfqtzXiangmuid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                break;


            case R.id.look_partner://找运动伙伴
                look_partner.setTextColor(getResources().getColor(R.color.my_tab));

                look_partner.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                //取消加粗
                have_partner.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                have_partner.setTextColor(getResources().getColor(R.color.huise));
                have_sprot_partner.setVisibility(View.GONE);
                sport_partner.setVisibility(View.VISIBLE);
                my_have_partner.setVisibility(View.INVISIBLE);
                my_look_partner.setVisibility(View.VISIBLE);
                timeText.setText("");
                shichangText.setText("");
                feiyongText.setText("");
                cdf_cp_text.setText("");
                // xiangmuText.setText("");
                //  changguanText.setText("");
                zhbTag = 1;
                break;
            case R.id.have_partner://我有运动伙伴
                have_partner.setTextColor(getResources().getColor(R.color.my_tab));

                look_partner.setTextColor(getResources().getColor(R.color.huise));

                have_partner.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                //取消加粗
                look_partner.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                have_sprot_partner.setVisibility(View.VISIBLE);
                sport_partner.setVisibility(View.GONE);
                my_look_partner.setVisibility(View.INVISIBLE);
                my_have_partner.setVisibility(View.VISIBLE);
                home_faqi_time_text_partner.setText("");
                home_faqi_shichang_text_partner.setText("");
                home_faqi_feiyong_text_partner.setText("");
                //  home_faqi_changguan_text_partner.setText("");
                //home_faqi_xiangmu_text_partner.setText("");

                zhbTag = 2;
                cp_fy = "0";
                break;

            case R.id.faqi_tiaozhan_yuding://预定场馆
                if (!EmptyUtils.isStrEmpty(token)) {

                    LogU.Ld("1609", "选择场馆" + fqtzXiangmu + "==" + moshihao + "=====" + dashangText.getText());
                    //  moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
                    if (EmptyUtils.isStrEmpty(home_faqi_xiangmu_text_partner.getText().toString().trim()) || home_faqi_xiangmu_text_partner.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动项目");
                    } else if (EmptyUtils.isStrEmpty(home_faqi_changguan_text_partner.getText().toString().trim()) || home_faqi_changguan_text_partner.getText().toString().equals("请选择")) {
                        ToastUitl.longs("请选择运动场馆");
                    } else if (EmptyUtils.isStrEmpty(home_faqi_time_text_partner.getText().toString().trim())) {
                        ToastUitl.longs("请选择开始时间");
                        return;
                    } else {
                      //  getCGjudgeTime();
                       // Intent intent = new Intent();
                       // Bundle bundle = new Bundle();
                        //合作 一定跳支付
                        if (moshihao.equals("3") || moshihao.equals("4")) {
//                        if (!dashangString.equals(peilianString)){
                            peilianString = "0.0";
                            dashangString = "0.0";
//                        }
                        } else {
                            dashangString = "0.0";
                            peilianString = "0.0";
                        }

                        LogU.Ld("1608", "场地费" + placeMoney + "    " + placeMoney.toString().length());

                        intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                        bundle.putString("tag", "4");

                        bundle.putString("moshiString", moshihao);

                        bundle.putString("shichang", shichang + "");
                        bundle.putString("hezuo", whezuo);
                        bundle.putString("beizhu", beizhu_edit_partner.getText().toString() + "");
                        bundle.putString("json", array1.toString());
                        bundle.putString("changdifei", placeMoney + "");
                        bundle.putString("wfqtzXiangmudaid", wfqtzXiangmudaid);
                        bundle.putString("wfqtzXiangmuid", wfqtzXiangmuid);
                        bundle.putString("timeStart", timeStart);
                        bundle.putString("placeNun", placeNun);
                        bundle.putString("wcgid", wcgid);
                        bundle.putString("cp_rs", "0");
                        bundle.putString("dashangString", dashangString);
                        bundle.putString("peilianString", peilianString);
                        bundle.putString("cp_fy", "0");
                        bundle.putString("Agemin", Agemin);
                        bundle.putString("Agemax", Agemax);
                        bundle.putString("hezuofeiyong", placeMoney + "");

                        bundle.putString("houtaifeiyong", placeMoney);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


                break;

            case R.id.home_faqi_referee_text://返回
                showSelectReserreeNumDialog();

                adapterC.notifyDataSetChanged();
                break;
            case R.id.home_faqi_referee_lv://返回


                if (fqtzXiangmuda.equals("高尔夫")) {


                    showSelectGEFReserreeLVDialog();

                } else {
                    // referee_lv_text.setText("三级");

                    showSelectReserreeLVDialog();

                }
                break;
        }
    }



   /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里要通过请求码来判断数据的来源
        switch (requestCode) {
            case 1:
                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("data");
                    LogU.Le("1608","zhi"+getData);
                   timeText.setText(getData);
                }else {
                    timeText.setText("");
                }
                break;
            */

    /**
     * 这里可以有多个requestcode
     **//*
            default:
        }
    }*/
    private void setDialog(int layout, String tab) {


        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
//        mCameraDialog.setCanceledOnTouchOutside(false);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                layout, null);
        if (tab.equals("1")) {
            moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
            jingji = root.findViewById(R.id.fqtz_ms_jingji);
            jingji.setOnClickListener(this);

            yule = root.findViewById(R.id.fqtz_ms_yule);
            yule.setOnClickListener(this);
            woshi = root.findViewById(R.id.fqtz_ms_woshi);
            woshi.setOnClickListener(this);
            wozhao = root.findViewById(R.id.fqtz_ms_wozhao);
            wozhao.setOnClickListener(this);
            yul_text = root.findViewById(R.id.yul_text);
            ji_text = root.findViewById(R.id.ji_text);
            wpl_text = root.findViewById(R.id.wpl_text);
            zpl_text = root.findViewById(R.id.zpl_text);


            if (moshihao.equals("1")) {
                yule.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                yul_text.setTextColor(getResources().getColor(R.color.white));
            } else if (moshihao.equals("2")) {
                jingji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                ji_text.setTextColor(getResources().getColor(R.color.white));
            } else if (moshihao.equals("3")) {
                woshi.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                wpl_text.setTextColor(getResources().getColor(R.color.white));
            } else if (moshihao.equals("4")) {
                wozhao.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                zpl_text.setTextColor(getResources().getColor(R.color.white));
            }

            if (yuleYesNO.equals("0")) {
                yule.setBackgroundResource(R.drawable.mos_bg);
            } else if (jingjiYesNo.equals("0")) {
                jingji.setBackgroundResource(R.drawable.mos_bg);
            }
            if (peilianYesNO.equals("0")) {
                woshi.setBackgroundResource(R.drawable.mos_bg);
                wozhao.setBackgroundResource(R.drawable.mos_bg);
            }
            LogU.Ld("1608", "fqtzqiurenshu" + fqtzqiurenshu);
            if (!fqtzqiurenshu.equals("2")) {
                woshi.setBackgroundResource(R.drawable.mos_bg);
                wozhao.setBackgroundResource(R.drawable.mos_bg);
                wpl_text.setTextColor(getResources().getColor(R.color.moshi));
                zpl_text.setTextColor(getResources().getColor(R.color.moshi));
            }
        } else if (tab.equals("2")) {
            // fangs = (String) spUtileFQTZ.get(this, "FQHDfangshi", "2");
            fangs = (String) spUtileFQTZ.get(this, "FQHDfangshi", "1");
            LogU.Ld("1608", "付款方式" + fangs);
            aa = root.findViewById(R.id.fatz_feiyong_aa);
            aa.setOnClickListener(this);
            shumai = root.findViewById(R.id.fatz_feiyong_shumai);
            shumai.setOnClickListener(this);
            aA = root.findViewById(R.id.aa);
            shM = root.findViewById(R.id.shm);
            if (fangs.equals("1")) {
                aa.setBackgroundResource(R.drawable.login_rounded_corners);
                aA.setTextColor(getResources().getColor(R.color.white));
            } else if (fangs.equals("2")) {
                shumai.setBackgroundResource(R.drawable.login_rounded_corners);
                shM.setTextColor(getResources().getColor(R.color.white));
            }


        } else if (tab.equals("3")) {

            sex = (String) spUtileFQTZ.get(this, "FQHDsex", "2");
            sexNan = root.findViewById(R.id.fqtz_xingbie_nan);
            sexNan.setOnClickListener(this);
            sexNv = root.findViewById(R.id.fqtz_xingbie_nv);
            sexNv.setOnClickListener(this);
            sexBuxian = root.findViewById(R.id.fqtz_xingbie_buxian);
            sexBuxian.setOnClickListener(this);
            nan = root.findViewById(R.id.nan);
            nv = root.findViewById(R.id.nv);
            bux = root.findViewById(R.id.bux);
            if (sex.equals("0")) {
                //sexText.setText("男");
                sexNan.setBackgroundResource(R.drawable.login_rounded_corners);
                nan.setTextColor(getResources().getColor(R.color.white));

            } else if (sex.equals("1")) {
                // sexText.setText("女");
                sexNv.setBackgroundResource(R.drawable.login_rounded_corners);
                nv.setTextColor(getResources().getColor(R.color.white));
            } else if (sex.equals("2")) {
                //  sexText.setText("不限");
                sexBuxian.setBackgroundResource(R.drawable.login_rounded_corners);
                bux.setTextColor(getResources().getColor(R.color.white));
            }
        }


//        quxiao = root.findViewById(R.id.fqtz_dialog_quxiao);
//        quxiao.setOnClickListener(this);
//        初始化视图
//        root.findViewById(R.id.dialog_item_button1);
//        root.findViewById(R.id.dialog_item_button2);
//
//        root.findViewById(R.id.btn_confirm).setOnClickListener(this);
//        mViewProvince = (WheelView) root.findViewById(R.id.id_province);
//        mViewCity = (WheelView) root.findViewById(R.id.id_city);
//        mViewDistrict = (WheelView) root.findViewById(R.id.id_district);
//        mBtnConfirm = (Button) root.findViewById(R.id.btn_confirm);
//        mBtnConfirm.setOnClickListener(this);
//        setUpListener();
//        setUpData();

        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();


    }


    @Override
    protected void onStart() {
        //wfqtzXiangmudaid = (String) spUtileFQTZ.get(this, "wfqtzXiangmudasportId", " ");
        // wcgid = (String) spUtileFQTZ.get(this, "wcgid", " ");
        // wfqtzXiangmuid = (String) spUtileFQTZ.get(this, "wfqtzXiangmusportId", " ");
        dTag = (String) spUtileFQTZ.get(this, "dianTag", "0");

        moshihao = (String) spUtileFQTZ.get(this, "FQHDmoshihao", "1");
        fqtzXiangmu = (String) spUtileFQTZ.get(this, "fqtzXiangmu", " ");
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", " ");
        fqtzXiangmuda = (String) spUtileFQTZ.get(this, "fqtzXiangmuda", " ");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", " ");
        fqtzqiurenshu = (String) spUtileFQTZ.get(this, "fqtzqiurenshu", "2");
        canyurenshu = (String) spUtileFQTZ.get(this, "canyurenshu", "1");
        jingjiYesNo = (String) spUtileFQTZ.get(this, "jingjiYesNo", " ");
        yuleYesNO = (String) spUtileFQTZ.get(this, "yuleYesNO", " ");
        peilianYesNO = (String) spUtileFQTZ.get(this, "peilianYesNO", " ");
        moshiTag = (String) spUtileFQTZ.get(this, "moshiTag", " ");
        timeRI = (String) spUtileFQTZ.get(this, "timeRI", " ");
        timeSHI = (String) spUtileFQTZ.get(this, "timeSHI", " ");
        YQtouxiang = (String) spUtileFQTZ.get(this, "YQtouxiang", " ");
        YQtab = (String) spUtileFQTZ.get(this, "YQtab", " ");
        //  YQname = (String) spUtileFQTZ.get(this, "YQname", " ");
        //shichang = (String) spUtileFQTZ.get(this, "shichang", " ");
        //  hezuo = (String) spUtileFQTZ.get(this, "hezuo2", "0");
        hezuo = (String) spUtileFQTZ.get(this, "hezuo", "1");
        whezuo = (String) spUtileFQTZ.get(this, "whezuo", "1");
        YQuuid = (String) spUtileFQTZ.get(this, "YQuuid", " ");
        YQdengji = (String) spUtileFQTZ.get(this, "YQdengji", " ");
        // hezuofeiyong = (String) spUtileFQTZ.get(this, "hezuofeiyong", "");
        fangs = (String) spUtileFQTZ.get(this, "FQHDfangshi", "1");
        //cgname = (String) spUtileFQTZ.get(this, "cgname", " ");
        // cgid = (String) spUtileFQTZ.get(this, "cgid", " ");
        // city = (String) spUtileFQTZ.get(this, "city", " ");

        LogU.Ld("1608", "===场馆地址" + zcgname + "====" + wcgname + "====" + cgid + "===" + city + "====" + wcgid);

        // getLeves();
        if (!EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(fqtzXiangmudaid)) {
            getTheBall();
        }
       /* if (!shichang.equals(" ")) {
            shichangText.setText(shichang + "小时");
        }*/

        if (moshiTag.equals("1")) {
            //   moshiText.setText("娱乐模式");
            if (dTag.equals("1")) {
                moshiText.setText("娱乐模式");
            } else {

            }
//            moshihao = "1";  Agemin  Agemax  JBmin  JBmax nl jB
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "1");
            spUtileFQTZ.remove(this, "moshiTag");
            spUtileFQTZ.remove(this, "FQHDmoshiName");
            spUtileFQTZ.remove(this, "cp_NUM");
            spUtileFQTZ.remove(this, "cpLV");
            spUtileFQTZ.remove(this, "cp_Ggef");

            spUtileFQTZ.remove(this, "FQHDsex");
            spUtileFQTZ.remove(this, "Agemin");
            spUtileFQTZ.remove(this, "Agemax");
            spUtileFQTZ.remove(this, "JBmin");
            spUtileFQTZ.remove(this, "JBmax");
            spUtileFQTZ.remove(this, "nL");
            spUtileFQTZ.remove(this, "jB");

            spUtileFQTZ.remove(this, "JBnum");
            spUtileFQTZ.remove(this, "JBnum1");
            spUtileFQTZ.remove(this, "NLnum");
            spUtileFQTZ.remove(this, "NLnum1");
            nial_text.setText("不限");

            sexText.setText("不限");
            sex = "2";
            Agemin = "1";
            Agemax = "99";

            JBmin = "1";
            JBmax = "10";
            jibieText.setText("不限");
            zhuanhuanText.setText("打赏费用");
            spUtileFQTZ.remove(this, "FQHDfangshi");
            fangs = "1";

            fangshiText.setText("AA");
            peilText.setVisibility(View.GONE);
            dashangText.setVisibility(View.VISIBLE);


            /*if (moshiName.equals("我找陪练")){
                JBmin="4";
                JBmax="10";
                jibieText.setText("4-10级");
            }else {

            }*/

            if (fqtzXiangmuda.equals("高尔夫")) {
                referee_lv_text.setText("初级");
            } else {
                referee_lv_text.setText("三级");
            }

            referee_num.setText("0人");
            num = 0;
            lv = 0;
            // spUtileFQTZ.remove(this, "moshiTag");
            LogU.Ld("1608", "走了标记" + moshiTag + cp_fy + "====" + cp_NUM + "=====" + cpLV);
            home_cp.setVisibility(View.GONE);
            cdf.setVisibility(View.VISIBLE);
            jingj_layout.setVisibility(View.GONE);
            youjiantou_aa.setVisibility(View.GONE);
            //  cdf_cp.setVisibility(View.GONE);
            cp_fy = "0.0";
            cp_layout.setVisibility(View.GONE);

            pTag = 0;


            LogU.Ld("1608", "走=====了标记" + moshiTag + cp_fy);

        }
        if (EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim()) || xiangmuText.getText().toString().equals("请选择")) {
            // ToastUitl.longs("请选择运动项目");
        } else if (EmptyUtils.isStrEmpty(changguanText.getText().toString().trim()) || changguanText.getText().toString().equals("请选择")) {
            // ToastUitl.longs("请选择运动场馆");
        } else if (EmptyUtils.isStrEmpty(timeText.getText().toString().trim())) {
            //ToastUitl.longs("请选择开始时间");
        } else {
            if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(placeMoney)) {
                getAccmoney(JBmin, fqtzXiangmudaid, city, shichang, placeMoney);
            }

        }

        /*if (moshiText.getText().toString().equals("竞技模式")) {
            if (!EmptyUtils.isStrEmpty(referee_num.getText().toString())  && !EmptyUtils.isStrEmpty(referee_lv_text.getText().toString()) && !EmptyUtils.isStrEmpty(shichang)) {
                getCaipanf(r_num, r_lv, fqtzXiangmudaid, shichang);
                LogU.Ld("1608", "请求数据" + fqtzXiangmudaid + r_lv + reseree_lv[lv] + r_num);
            }
            LogU.Ld("1608", "请求数据===" + fqtzXiangmudaid + r_lv + reseree_lv[lv] + r_num);

        }*/

        LogU.Ld("1608", "合作" + hezuo);


        xm_name = (String) spUtileFQTZ.get(this, "xmName", "请选择");
        zXM_name = (String) spUtileFQTZ.get(this, "zXMname", "请选择");
        LogU.Ld("1608", "级22别====" + Agemin + "====" + Agemax + "======" + JBmin + "=====" + JBmax + "====" + sex + "===" + xm_name + "===" + zXM_name);
        if (!EmptyUtils.isStrEmpty(xm_name)) {
            xiangmuText.setText(xm_name);
        } else {
            xiangmuText.setText("请选择");
        }
        if (!EmptyUtils.isStrEmpty(zXM_name)) {
            home_faqi_xiangmu_text_partner.setText(zXM_name);
        } else {
            xiangmuText.setText("请选择");
        }
       /* zcgname = (String) spUtileFQTZ.get(this, "zcgname", "请选择");
        wcgname = (String) spUtileFQTZ.get(this, "wcgname", "请选择");

        if (!EmptyUtils.isStrEmpty(cgname)) {
            changguanText.setText(cgname);
            dwd.setVisibility(View.VISIBLE);

        } else {
            changguanText.setText("请选择");
            dwd.setVisibility(View.GONE);

        }

        if (!EmptyUtils.isStrEmpty(wcgname)) {

            home_faqi_changguan_text_partner.setText(wcgname);

            cGdwd.setVisibility(View.VISIBLE);
        } else {
            home_faqi_changguan_text_partner.setText("请选择");
            cGdwd.setVisibility(View.GONE);
        }*/
       /* if (fqtzXiangmu.equals("中式黑八")) {
            xiangmuText.setText("中式黑八");

        } else if (fqtzXiangmu.equals("美式9球")) {
            xiangmuText.setText("美式9球");

        } else if (fqtzXiangmu.equals("斯诺克")) {
            xiangmuText.setText("斯诺克");

        } else if (fqtzXiangmu.equals("比杆赛")) {
            xiangmuText.setText(fqtzXiangmuda + "比杆");

        } else if (fqtzXiangmu.equals("比洞赛")) {
            xiangmuText.setText(fqtzXiangmuda + "比洞");

        } else {
            if (!fqtzXiangmu.equals("练习")) {
                xiangmuText.setText(fqtzXiangmuda + fqtzXiangmu);
            } else {
                xiangmuText.setText("");
            }
            String xm_name = xiangmuText.getText().toString();
            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "xm_name", xm_name);
        }
        home_faqi_xiangmu_text_partner.setText(fqtzXiangmuda + "  " + fqtzXiangmu);

        String zXM_name = home_faqi_xiangmu_text_partner.getText().toString();
        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "zXM_name", zXM_name);*/
        //home_faqi_xiangmu_text_partner.setText(nage);

        // changguanText.setText(cgname);
        LogU.Ld("1608", "fqtzXiangmudaid" + fqtzXiangmudaid + "fqtzXiangmuid" + fqtzXiangmuid + "标记" + moshiTag + "邀请头像" + YQtouxiang);

        fqyqAdapter = new FQYQAdapter(this, listF, Integer.parseInt(canyurenshu) / 2);

        adapter = new FQTZAdapter(this, listTXA, listQA, listDJA, Integer.parseInt(canyurenshu) / 2);
        adapterB = new FQTZBAdapter(this, listTXB, listQB, listDJB, Integer.parseInt(canyurenshu) / 2);

        initjihe();
        getSportLevel();
        /*if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(placeMoney)) {

            if (moshiText.getText().toString().equals("我是陪练")){
                getAccmoney(level, fqtzXiangmudaid, city, shichang, placeMoney);

            }

        }*/
        //gridViewA.setAdapter(adapter);
        gridViewA.setAdapter(fqyqAdapter);
        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                if (!EmptyUtils.isStrEmpty(token)) {
                    if (listF.size() > position) {

                        if (position != 0) {
                            quxiaoBM(position);
                            fqyqAdapter.notifyDataSetChanged();
                        } else {
                            return;
                        }

                    } else {
                        if (sexText.getText().toString().equals("不限")) {
                            teamSex = "2";
                        } else if (sexText.getText().toString().equals("男")) {
                            teamSex = "0";
                        } else if (sexText.getText().toString().equals("女")) {
                            teamSex = "1";
                        }

                        intent.setClass(FaqiTiaozhanActivity.this, YaoqingActivity.class);
                        bundle.putString("tab", "1");
                        bundle.putSerializable("listF", (Serializable) mfqtian);
                        //   bundle.putStringArrayList("listTXB",  listF);
                        bundle.putString("teamSex", teamSex);
                        bundle.putString("minlevel", JBmin);
                        bundle.putString("maxlevel", JBmax);
                        bundle.putString("team", "1");
                        bundle.putString("Agemin", Agemin);
                        bundle.putString("Agemax", Agemax);
                        bundle.putString("FirstSportId", fqtzXiangmudaid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }

            }
        });

        gridViewB.setAdapter(adapterB);
        gridViewB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                LogU.Ld("1608", "点击B队" + listQB.size() + "----" + "----" + position);
                if (!EmptyUtils.isStrEmpty(token)) {
                    if (listQB.size() > position) {

                        quxiaoBM(position);

                    } else {
                        intent.setClass(FaqiTiaozhanActivity.this, YaoqingActivity.class);
                        bundle.putString("tab", "2");
                        bundle.putStringArrayList("listTXA", (ArrayList<String>) listTXA);
                        bundle.putStringArrayList("listTXB", (ArrayList<String>) listTXB);
                        bundle.putString("FirstSportId", fqtzXiangmudaid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } else {
                    intent.setClass(FaqiTiaozhanActivity.this, DengluActivity.class);
                    startActivity(intent);
                }


            }
        });
        adapter.notifyDataSetChanged();
        adapterB.notifyDataSetChanged();

        fqyqAdapter.notifyDataSetChanged();

       /* if (timeText.getText().toString().length()<5){
            timeyjt.setVisibility(View.INVISIBLE);
        }else{
            timeyjt.setVisibility(View.INVISIBLE);
        }*/


        super.onStart();
    }

    public void getHuodBanner() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getActivityBanner")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发起挑战" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            HuodXQEntity entity = gson.fromJson(result, HuodXQEntity.class);
                            if (!EmptyUtils.isEmpty(entity)) {
                                list = new ArrayList<>();
                                for (int i = 0; i < entity.getData().size(); i++) {
                                    list.add(getResources().getString(R.string.imgurl) + entity.getData().get(i).getPicurl());
                                }
                                bannerView.setData(list, null);
                                bannerView.loadImage(new XBanner.XBannerAdapter() {
                                    @Override
                                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                                        Glide.with(FaqiTiaozhanActivity.this).load(list.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).placeholder(R.mipmap.logo).error(R.mipmap.logo).into((ImageView) view);

                                    }
                                });

                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //裁判人数
    private void showSelectReserreeNumDialog() {
        SelectNumDialog mSelectWeekDialog = new SelectNumDialog(this, new SelectNumDialog.OnClickListener() {
            @Override
            public boolean onSure(int item, int setTimeType) {
                num = item;


                referee_num.setText(reseree_num[item]);

                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "cpNUM", reseree_num[item]);
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "cp_NUM", num + "");
                LogU.Ld("1609", "1609" + num + referee_lv_text.getText().toString());
                if (referee_lv_text.getText().toString() != "" && !EmptyUtils.isStrEmpty(reseree_lv[lv]) && !EmptyUtils.isStrEmpty(shichang)) {
                    getCaipanf(item + "", slv, fqtzXiangmudaid, shichang);

                }
                if (num == 0) {

                    cp_layout.setVisibility(View.GONE);
                    jingj_layout.setVisibility(View.GONE);
                    cdf.setVisibility(View.VISIBLE);
                } else {
                    jingj_layout.setVisibility(View.VISIBLE);
                    cdf.setVisibility(View.GONE);
                    cp_layout.setVisibility(View.VISIBLE);
                }
                adapterC.getNum(num);
                adapterC.notifyDataSetChanged();

                LogU.Ld("1609", "1609" + num);

                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectWeekDialog.show(num);
    }


    //裁判等级
    private void showSelectReserreeLVDialog() {
        SelectResereeLVDialog mSelectWeekDialog = new SelectResereeLVDialog(this, new SelectResereeLVDialog.OnClickListener() {
            @Override
            public boolean onSure(int item, int setTimeType) {
                lv = item;
                referee_lv_text.setText(reseree_lv[item]);

                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "cpLV", reseree_lv[item]);

                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "refereeLV", item + "");
                LogU.Ld("1609", "1609" + num + referee_num.getText().toString());
                if (referee_num.getText().toString() != "" && num != 0 && !EmptyUtils.isStrEmpty(reseree_lv[item]) && !EmptyUtils.isStrEmpty(shichang)) {
                    getCaipanf(num + "", slv, fqtzXiangmudaid, shichang);

                }

                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectWeekDialog.show(lv);
    }


    //裁判等级高尔夫
    private void showSelectGEFReserreeLVDialog() {
        SelectGEFDialog mSelectWeekDialog = new SelectGEFDialog(this, new SelectGEFDialog.OnClickListener() {
            @Override
            public boolean onSure(int item, int setTimeType) {
                lv = item;
                referee_lv_text.setText(gef_lv[item]);
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "cp_Ggef", gef_lv[item]);
                spUtileFQTZ.put(FaqiTiaozhanActivity.this, "ref_GLV", item + "");

                LogU.Ld("1609", "1609" + num + referee_num.getText().toString());
                if (referee_num.getText().toString() != "" && num != 0 && !EmptyUtils.isStrEmpty(gef_lv[item]) && !EmptyUtils.isStrEmpty(shichang)) {
                    reseree_lv[lv] = gef_lv[item];
                    getCaipanf(num + "", gef_lv[item], fqtzXiangmudaid, shichang);

                }

                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectWeekDialog.show(lv);
    }

    //裁判费
    public void getCaipanf(String num, String lvv, String daId, String h) {
        LogU.Ld("1608", "裁判费=" + num + reseree_lv[lv] + fqtzXiangmudaid + shichang);
        slv = referee_lv_text.getText().toString();
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getcaipanf")
                .addParams("name", slv)
                .addParams("sportid", fqtzXiangmudaid)
                .addParams("number", num + "")
                .addParams("duration", shichang + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            RefereeMoneyEntity entity = gson.fromJson(result, RefereeMoneyEntity.class);
                            if (!EmptyUtils.isEmpty(entity)) {
                                String data_fy = entity.getData();
                                double v = Double.parseDouble(data_fy);
                                cp_fy = String.format("%.2f", v);

                                feiyong_cp.setText("¥" + cp_fy);

                                // home_faqi_feiyong_text_partner.setText("¥" + placeMoney+" + "+cp_fy);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //是否有活动
    public void getjudgeTime() {
        LogU.Ld("1608", "是否有活动" + token + "===" + timeStart + "====" + shichang + "===" +fqtzXiangmudaid+"====="+ wfqtzXiangmudaid + "===" + moshihao );
        LogU.Le("1608", "发布我的" + placeMoney + cgname + timeStart + "场地号" + placeNun + cp_fy + placeTimeLen + shichang);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getjudgeTime")
                .addHeader("token", token)
                .addParams("StartTime", timeStart)
                .addParams("PlayTime", shichang + "")
                .addParams("SportId", fqtzXiangmudaid)
                .addParams("SportMode", moshihao + "")
                .addParams("venueid", placeNun)
                .addParams("siteUid", cgid + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            String cpdj = referee_lv_text.getText().toString();
                            LogU.Ld("1608", "裁判等级" + cpdj);
                            intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                            bundle.putString("tag", "1");
                            bundle.putString("fangshi", fangs);
                            bundle.putString("moshiString", moshihao);
                            bundle.putString("sex", sex);
                            bundle.putString("zuidi", JBmin);
                            bundle.putString("zuigao", JBmax);
                            bundle.putString("dashangString", dashangString);
                            bundle.putString("shichang", shichang + "");
                            bundle.putString("peilianString", peilianString);
                            bundle.putString("json", array1.toString());
                            bundle.putString("beizhu", beizhuEdit.getText().toString() + "");
                        /*bundle.putString("changdifei", df.format(cdf) + "");
                        bundle.putString("hezuofeiyong", df.format(cdf) + "");*/
                            bundle.putString("changdifei", placeMoney + "");
                            bundle.putString("hezuofeiyong", placeMoney + "");
                            bundle.putString("hezuo", hezuo);
                            bundle.putString("houtaifeiyong", placeMoney);
                            bundle.putString("canyurenshu", canyurenshu);
                            bundle.putString("fqtzXiangmudaid", fqtzXiangmudaid);
                            bundle.putString("timeStart", timeStart);
                            bundle.putString("placeNun", placeNun);
                            bundle.putString("cp_dj", cpdj + "");
                            bundle.putString("cp_rs", num + "");
                            bundle.putString("cgid", cgid + "");
                            bundle.putString("cp_fy", cp_fy + "");
                            bundle.putString("Agemin", Agemin);
                            bundle.putString("Agemax", Agemax);
                            intent.putExtras(bundle);

                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //是否有活动
    public void getCGjudgeTime() {
        LogU.Ld("1608", "是否有活动" + token + "===" + Agemin + "====" + Agemax + "===" + JBmin + "===" + JBmax + "===" + cp_fy);
        LogU.Le("1608", "发布我的" + placeMoney + cgname + timeStart + "场地号" + placeNun + cp_fy + placeTimeLen + shichang);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getjudgeTime")
                .addHeader("token", token)
                .addParams("StartTime", timeStart)
                .addParams("PlayTime", shichang + "")
                .addParams("SportId", wfqtzXiangmudaid)
                .addParams("SportMode", moshihao + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            //合作 一定跳支付
                            if (moshihao.equals("3") || moshihao.equals("4")) {
//                        if (!dashangString.equals(peilianString)){
                                peilianString = "0.0";
                                dashangString = "0.0";
//                        }
                            } else {
                                dashangString = "0.0";
                                peilianString = "0.0";
                            }

                            LogU.Ld("1608", "场地费" + placeMoney + "    " + placeMoney.toString().length());

                            intent.setClass(FaqiTiaozhanActivity.this, HomeZhifuActivity.class);
                            bundle.putString("tag", "4");

                            bundle.putString("moshiString", moshihao);

                            bundle.putString("shichang", shichang + "");
                            bundle.putString("hezuo", whezuo);
                            bundle.putString("beizhu", beizhu_edit_partner.getText().toString() + "");
                            bundle.putString("json", array1.toString());
                            bundle.putString("changdifei", placeMoney + "");
                            bundle.putString("wfqtzXiangmudaid", wfqtzXiangmudaid);
                            bundle.putString("wfqtzXiangmuid", wfqtzXiangmuid);
                            bundle.putString("timeStart", timeStart);
                            bundle.putString("placeNun", placeNun);
                            bundle.putString("wcgid", wcgid);
                            bundle.putString("cp_rs", "0");
                            bundle.putString("dashangString", dashangString);
                            bundle.putString("peilianString", peilianString);
                            bundle.putString("cp_fy", "0");
                            bundle.putString("Agemin", Agemin);
                            bundle.putString("Agemax", Agemax);
                            bundle.putString("hezuofeiyong", placeMoney + "");

                            bundle.putString("houtaifeiyong", placeMoney);
                            intent.putExtras(bundle);

                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //弹窗签到
    private void quxiaoBM(final int position) {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        ds_xz.setText("您确定取消本次邀请么？");

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


        dialog.show();
        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // listTXA.remove(position);
                // listQA.remove(position);
                // listDJA.remove(position);
                listIDA.remove(position);
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    /**
     * chooseDialog  选择年龄
     */
    private void showChooseDialog(List<String> mlist, List<String> mlist1) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);

        NLnum = (String) spUtileFQTZ.get(this, "NLnum", "0");
        NLnum1 = (String) spUtileFQTZ.get(this, "NLnum1", "0");

        int nu = 0,nu1=0;
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
        chooseDialog = builder.setData(mlist, mlist1).setSelection(nu,nu1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position, String itemValue1, int position1) {

                        LogU.Ld("1608", "选择年龄" + position + "==" + position1);


                        if (position == 0 && position1 == 0) {
                            Agemin = "1";
                            Agemax = "99";
                            nial_text.setText("不限");
                        } else {
                            if (position == 0 || position1 == 0) {
                                if (position == 0 && position1 != 0) {
                                    Agemin = "1";
                                    Agemax = itemValue1;
                                    nial_text.setText("1" + "-" + itemValue1 + "岁");
                                }

                                if (position != 0 && position1 == 0) {
                                    Agemin = itemValue;
                                    Agemax = "99";
                                    nial_text.setText(itemValue +"-99岁");
                                }
                                //  nial_text.setText(itemValue + "-" + itemValue1);
                            } else {
                                if (position > position1) {
                                    ToastUitl.longs("最小年龄不能高于最大年龄");
                                } else {
                                    Agemin = itemValue;
                                    Agemax = itemValue1;
                                    nial_text.setText(itemValue + "-" + itemValue1 + "岁");
                                }
                            }
                        }
                        String nL = nial_text.getText().toString();
                        LogU.Ld("1608", "年龄===" + nL);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "nL", nL);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "Agemin", Agemin);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "Agemax", Agemax);

                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "NLnum", position+"");
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "NLnum1", position1+"");
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    /**
     * chooseDialog  选择级别
     */
    private void showChooseJBDialog(List<String> mlist, List<String> mlist1) {
        FQLVPickerDialog.Builder builder = new FQLVPickerDialog.Builder(this);
        JBnum = (String) spUtileFQTZ.get(this, "JBnum", "0");
        JBnum1 = (String) spUtileFQTZ.get(this, "JBnum1", "0");

        int ju = 0,ju1=0;
        try {

            if (!EmptyUtils.isStrEmpty(JBnum)) {
                ju = Integer.valueOf(JBnum).intValue();
            }
            if (!EmptyUtils.isStrEmpty(JBnum1)) {
                ju1 = Integer.valueOf(JBnum1).intValue();
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();

        }

        final int finalJu = ju;
        final int finalJu1 = ju1;
        chooseDialog = builder.setData(mlist, mlist1).setSelection(ju, ju1).setTitle("取消")
                .setOnDataSelectedListener(new FQLVPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position, String itemValue1, int position1) {

                        LogU.Ld("1608", "选择级别" + position + "==" + position1);
                        //    jibieText.setText(first + "级—" + second + "级");
                        if (pTag != 1) {
                            if (position == 0 && position1 == 0) {
                                JBmin = "1";
                                JBmax = "10";
                                jibieText.setText("不限");
                            } else {
                                if (position == 0 || position1 == 0) {
                                    if (position == 0 && position1 != 0) {
                                        JBmin = "1";
                                        JBmax = itemValue1;
                                        jibieText.setText("1" + "-" + itemValue1 + "级");
                                    }

                                    if (position != 0 && position1 == 0) {
                                        JBmin = itemValue;
                                        JBmax = "10";
                                        jibieText.setText(itemValue + "-" + "10级");
                                    }
                                    //  nial_text.setText(itemValue + "-" + itemValue1);
                                } else {
                                    if (position > position1) {
                                        ToastUitl.longs("最小级别不能高于最大级别");
                                    } else {
                                        JBmin = itemValue;
                                        JBmax = itemValue1;
                                        jibieText.setText(itemValue + "-" + itemValue1 + "级");
                                    }
                                }
                            }

                        } else {
                            if (position > position1) {
                                ToastUitl.longs("最小级别不能高于最大级别");
                            } else {
                                JBmin = itemValue;
                                JBmax = itemValue1;
                                jibieText.setText(itemValue + "-" + itemValue1 + "级");
                                if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(placeMoney)) {
                                    getAccmoney(itemValue, fqtzXiangmudaid, city, shichang, placeMoney);
                                }
                                LogU.Ld("1608", "级别===" + JBmin + "====" + fqtzXiangmudaid + "==" + city + "===" + shichang + placeMoney);

                            }
                        }
                        LogU.Ld("1608","记住级别"+JBnum+"==="+JBnum1+"===="+ finalJu +"==="+ finalJu1);
                        String jB = jibieText.getText().toString();
                        LogU.Ld("1608", "级别===" + jB);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "jB", jB);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "JBmin", JBmin);
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "JBmax", JBmax);

                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "JBnum", position+"");
                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "JBnum1", position1+"");
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    //弹窗运动
    private void yaoQXZ_y() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        LinearLayout sport;
        sport = (LinearLayout) LayoutInflater.from(this).inflate(
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

    // 弹窗性别
    private void yaoQXZ_xb() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);

        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);


        tis_text.setText("性别要求说明");
        ds_xz.setText("发布者对报名者的性别要求。如有裁判，裁判不受该性别要求限制。");


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


        dialog.show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //弹窗 年龄
    private void yaoQXZ_nl() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        tis_text.setText("年龄要求说明");
        ds_xz.setText("发布者对报名者的年龄要求。如有裁判，裁判不受该年龄要求限制。");


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


        dialog.show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    // 弹窗 技术
    private void yaoQXZ_js() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);

        tis_text.setText("技术等级说明");
        ds_xz.setText("发布者对报名者该运动项目的技术级别要求。用户的某运动项目的技术级别由技术分决定，用户在竞技模式活动结束后会输赢技术分。如有裁判，裁判不受该要求限制。");


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


        dialog.show();

        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    // 弹窗 打赏
    private void yaoQXZ_ds() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);


        tis_text.setText("打赏费用说明");
        ds_xz.setText("发布者打赏给报名者的费用，报名者均分该费用。会提高活动匹配成功率。");


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

    // 弹窗 陪练
    private void yaoQXZ_pl() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_yaoq_layput, null);
        ImageView icon_close = sport.findViewById(R.id.icon_close_i);


        TextView tis_text = sport.findViewById(R.id.tis_text);
        TextView ds_xz = sport.findViewById(R.id.ds_xz);
        TextView ds_xz_pl1 = sport.findViewById(R.id.ds_xz_pl1);
        TextView ds_xz_pl2 = sport.findViewById(R.id.ds_xz_pl2);
        tis_text.setText("陪练费用说明");
        if(!EmptyUtils.isStrEmpty(xiangmuText.getText().toString().trim())&&!xiangmuText.getText().toString().equals("请选择")&&!EmptyUtils.isStrEmpty(changguanText.getText().toString().trim())&&!changguanText.getText().toString().equals("请选择")){
            ds_xz.setText("1)练习方支付给陪练方的费用;");
            ds_xz_pl1.setText("2)陪练费用=陪练单价*时长+场地费/2;");
            ds_xz_pl2.setText("3)陪练单价（" + cityName + nameSport + "陪练，平台建议单价）:\n" +
                    "4级:" + money4 + "元/小时 5级:" + money5 + "元/小时\n" +
                    "6级:" + money6 + "元/小时 7级:" + money7 + "元/小时\n" +
                    "8级:" + money8 + "元/小时 9级:" + money9 + "元/小时\n" +
                    "10级:" + money10 + "元/小时");

            //  ds_xz_pl2.setText("陪练单价（北京市羽毛球陪练，平台建议单价）：4级：40元/小时   5级：50元/小时 6级：60元/小时   7级：70元/小时 8级：80元/小时   9级：90元/小时 10级：100元/小时" );
            ds_xz_pl1.setVisibility(View.VISIBLE);
            ds_xz_pl2.setVisibility(View.VISIBLE);
        }else {
            ds_xz.setText("1)练习方支付给陪练方的费用;");
            ds_xz_pl1.setText("2)陪练费用=陪练单价*时长+场地费/2;");
            ds_xz_pl1.setVisibility(View.VISIBLE);
        }
       /* if (EmptyUtils.isStrEmpty()) {
            ds_xz.setText("1)练习方支付给陪练方的费用;");
            ds_xz_pl1.setText("2)陪练费用=陪练单价*时长+场地费/2;");
            ds_xz_pl1.setVisibility(View.VISIBLE);
        } else {

        }*/
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

    //是否是陪练
    public void getAccompanyuser() {
        LogU.Ld("1608", "是否有活动" + token + "===" + fqtzXiangmudaid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAccompanyuser")
                .addHeader("token", token)
                .addParams("SportId", fqtzXiangmudaid)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "裁判费" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {

//                    moshiString = "3";
                            mCameraDialog.dismiss();
                            moshiText.setText("我是陪练");
                            youjiantou_aa.setVisibility(View.GONE);
                            cp_layout.setVisibility(View.GONE);
                            home_cp.setVisibility(View.GONE);
                            cdf_cp.setVisibility(View.GONE);
                            cdf.setVisibility(View.VISIBLE);
                            jingj_layout.setVisibility(View.GONE);

                            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshiName", "我是陪练");
                            spUtileFQTZ.put(FaqiTiaozhanActivity.this, "FQHDmoshihao", "3");

                            xingbie.setVisibility(View.VISIBLE);
                            jibie.setVisibility(View.VISIBLE);
                            peilText.setVisibility(View.VISIBLE);
                            dashangText.setVisibility(View.GONE);
                            zhuanhuanText.setText("陪练费用");
                            adapter.notifyDataSetChanged();
                            fqyqAdapter.notifyDataSetChanged();
                            adapterB.notifyDataSetChanged();
                            peilText.setText("");
                            dashangText.setText("");
                            cp_fy = "0.0";
                            num = 0;
                            lv = 0;
                            pTag = 0;
                            spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBmin");
                            spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBmax");

                            spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBnum");
                            spUtileFQTZ.remove(FaqiTiaozhanActivity.this, "JBnum1");
                            JBmin = "1";
                            JBmax = "10";
                            if (!EmptyUtils.isStrEmpty(placeTimeLen) && !EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(placeMoney)) {

                                getAccmoney(level, fqtzXiangmudaid, city, shichang, placeMoney);

                            }
                            LogU.Ld("1608", "陪练级别" + JBmin + "====" + JBmax);
                            if (JBmin.equals("1") && JBmax.equals("10")) {
                                jibieText.setText("不限");
                            } else {
                                jibieText.setText(JBmin + "-" + JBmax);
                            }

                            fangs = "1";
                            fangshiText.setText("AA");

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //获取用户等级
    public void getSportLevel() {
        //  LogU.Ld("1608", "是否有活动" + token + "===" + timeStart + "====" + shichang+"==="+dashangString+"==="+peilianString);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSportLevel")
                .addHeader("token", token)
                .addParams("sportId", fqtzXiangmudaid)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "用户等级" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();

                            SportLeveEntity entity = gson.fromJson(result, SportLeveEntity.class);
                            level = entity.getData().getLevel();
                            if (!EmptyUtils.isStrEmpty(shichang) && !EmptyUtils.isStrEmpty(city) && !EmptyUtils.isStrEmpty(placeMoney)) {

                                if (moshiText.getText().toString().equals("我是陪练")) {
                                    getAccmoney(level, fqtzXiangmudaid, city, shichang, placeMoney);

                                }

                            }
                            fqyqAdapter.setNumLv(level);
                            fqyqAdapter.notifyDataSetChanged();
                            LogU.Ld("1608", "用户====等级" + level);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    // 弹窗打赏费
    private void payDS() {
        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.edit_pay_out, null);

        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);


        dialog.show();


    }

    /**
     * chooseDialog  选择级别 陪练
     */
    private void showChooseJBPL(List<String> list, List<String> list1) {
        FQLVPickerDialog.Builder builder = new FQLVPickerDialog.Builder(this);
        chooseDialog = builder.setData(list, list1).setSelection(0, 0).setTitle("取消")
                .setOnDataSelectedListener(new FQLVPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position, String itemValue1, int position1) {

                        LogU.Ld("1608", "选择级别" + position + "==" + position1);
                        //    jibieText.setText(first + "级—" + second + "级");
                        plnum = position;

                        if (position > position1) {
                            ToastUitl.longs("最小级别不能高于最大级别");
                        } else {
                            JBmin = itemValue;
                            JBmax = itemValue1;
                            jibieText.setText(itemValue + "-" + itemValue1 + "级");

                        }
                        String jB = jibieText.getText().toString();
                        getAccmoney(JBmin, fqtzXiangmudaid, city, shichang, placeMoney);
                        LogU.Ld("1608", "级别===" + JBmin + "====" + fqtzXiangmudaid + "==" + city + "===" + shichang + placeMoney);

                        spUtileFQTZ.put(FaqiTiaozhanActivity.this, "jB", jB);

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    //获取等级
    public void getLeves() {
        LogU.Ld("1608", "获取等级" + token + "===" + fqtzXiangmudaid + "====" + city);
        LogU.Ld("1608", "陪练级别" + JBmin + "====" + JBmax);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLeves")
                //  .addHeader("token", token)
                .addParams("SportId", fqtzXiangmudaid)
                .addParams("CityName", city)
                .addParams("PlayTime", placeTimeLen)
                .addParams("SiteMoney", placeMoney)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取等级" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();

                            LevesEntity entity = gson.fromJson(result, LevesEntity.class);

                            //  JBmin= entity.getData();


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //陪练球等级和钱（建议价格）
    public void getTheBall() {
        //  LogU.Ld("1608", "是否有活动" + token + "===" + timeStart + "====" + shichang+"==="+dashangString+"==="+peilianString);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTheBall")

                .addParams("SportId", fqtzXiangmudaid)
                .addParams("CityName", city)

                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "陪练球等级和钱" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();

                            TheBallEntity entity = gson.fromJson(result, TheBallEntity.class);
                            cityName = entity.getData().getCityName();
                            nameSport = entity.getData().getName();
                            try {

                                jb_int = Integer.parseInt(JBmin);

                            } catch (NumberFormatException e) {

                                e.printStackTrace();

                            }

                            comm = entity.getData().getComm();

                            LogU.Ld("1608", "数据" + comm + comm.toString());
                            if (!EmptyUtils.isListEmpty(comm)) {
                                money4 = entity.getData().getComm().get(0).getMoney();
                                money5 = entity.getData().getComm().get(1).getMoney();
                                money6 = entity.getData().getComm().get(2).getMoney();
                                money7 = entity.getData().getComm().get(3).getMoney();
                                money8 = entity.getData().getComm().get(4).getMoney();
                                money9 = entity.getData().getComm().get(5).getMoney();
                                money10 = entity.getData().getComm().get(6).getMoney();
                            }


                            LogU.Ld("1608", "用户====等级" + plnum);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //陪练球等级和钱（建议价格）
    public void getAccmoney(String jb, String id, String ci, String time, String feiy) {
        //  LogU.Ld("1608", "是否有活动" + token + "===" + timeStart + "====" + shichang+"==="+dashangString+"==="+peilianString);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getAccmoney")
                .addParams("grade", jb)
                .addParams("SportId", id)
                .addParams("CityName", ci)
                .addParams("PlayTime", time)
                .addParams("SiteMoney", feiy)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608阴", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "陪练=========" + response);

                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();

                            AccmoneyEntity entity = gson.fromJson(result, AccmoneyEntity.class);

                            peilText.setText(entity.getData() + "");
                            LogU.Ld("1608", "用户====等级" + plnum);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(FaqiTiaozhanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
