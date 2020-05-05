package com.example.android.tiaozhan.Home;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.CGCXAdapter;
import com.example.android.tiaozhan.Adapter.CGGZYundongAdapter;
import com.example.android.tiaozhan.Adapter.GvAdapter;
import com.example.android.tiaozhan.Entity.CGCXEntity;
import com.example.android.tiaozhan.Entity.CGXXEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.YundongEntity;
import com.example.android.tiaozhan.Entity.ZhaopianPostEntity;
import com.example.android.tiaozhan.My.DituActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.FileUtilcll;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Wonderful.PaisheActivity;
import com.google.gson.Gson;
import com.scrat.app.selectorlibrary.ImageSelector;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 *场馆更正
 */
public class CGGZActivity extends BaseActivity implements View.OnClickListener {
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码

    private TextView biaoti, shuzi, dizhiText,zishu;
    private ImageView fanhui, wifi, tingche, linyu;
    private String wifitag = "0", tingchetag = "0", linyutag = "0", kstime, jstime, siteId;
    private String lat, log, city, district, province, tag, s = "", baseURL, imgURL, address = "", sematicDescription = "";
    private LinearLayout dizhi;
    private Spinner spinner, spinner2;
    private EditText editText, name, shouji, xxdz;
    private final int maxNum = 200;
    private String token, path, urlpath, uid;
    private SPUtils spUtils;
    private RelativeLayout xiayibu;
    private List<String> pathlist;//路径集合
    private ListView listView;
    private CGCXAdapter CGCXadapter;
    private List<CGCXEntity.DataBean.LstBean> data2;
    private int bj = 2;

    private RecyclerView recyclerView;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chgz);
//    }
    private List<YundongEntity.DataBean> data;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Map<String, List<String>> hashMap = new HashMap<>();
    private List<String> list1 = new ArrayList<>();
    private Gson gson = new Gson();
    private GridView gridView;
    private List<String> list2;//照片集合
    private GvAdapter adapter2;//照片适配器
    private Uri uritempFile;
    private Bitmap bitmap;
    private File file;
    private CGGZYundongAdapter linkLabelAdapter;
    private List<CGXXEntity.DataBean.SupportSportIDBean> data3;

    @Override
    public int initContentView() {
        return R.layout.activity_chgz;
    }

    @Override
    protected void initUIAndListener() {
        LogU.Ld("1608","开门进入");
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        dizhi = findViewById(R.id.cggz_dizhi);
        dizhi.setOnClickListener(this);
        editText = findViewById(R.id.cggz_edit);
        shuzi = findViewById(R.id.cggz_text);

        wifi = findViewById(R.id.cggz_wifi);
        wifi.setOnClickListener(this);
        linyu = findViewById(R.id.cggz_linyu);
        linyu.setOnClickListener(this);
        tingche = findViewById(R.id.cggz_tingche);
        tingche.setOnClickListener(this);
        dizhiText = findViewById(R.id.cggz_dizhi_text);
        name = findViewById(R.id.cggz_name);
        xxdz = findViewById(R.id.cggz_xiangxi_text);


//        name.setFocusable(true);
//        name.setFocusableInTouchMode(true);
//        name .requestFocus();
        shouji = findViewById(R.id.cggz_shouji);
        xiayibu = findViewById(R.id.cggz_xiayibu);
        xiayibu.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        gridView = findViewById(R.id.cggz_grid);
        listView = findViewById(R.id.cggz_list);

        list2 = new ArrayList<>();
        adapter2 = new GvAdapter(this, list2);
        gridView.setAdapter(adapter2);

        //场馆查询
        data2 = new ArrayList<>();
        CGCXadapter = new CGCXAdapter(this, data2);


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tag = bundle.getString("tag");

        if (tag.equals("3")) {
            uid = bundle.getString("uid");

        }


        // 第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("00:00");
        list.add("01:00");
        list.add("02:00");
        list.add("03:00");
        list.add("04:00");
        list.add("05:00");
        list.add("06:00");
        list.add("07:00");
        list.add("08:00");
        list.add("09:00");
        list.add("10:00");
        list.add("11:00");
        list.add("19:00");
        list.add("13:00");
        list.add("14:00");
        list.add("15:00");
        list.add("16:00");
        list.add("17:00");
        list.add("18:00");
        list.add("19:00");
        list.add("20:00");
        list.add("21:00");
        list.add("22:00");
        list.add("23:00");
        list.add("24:00");

        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 第四步：将适配器添加到下拉列表上
        spinner.setAdapter(adapter);

        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {



            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                /* 将所选mySpinner 的值带入myTextView 中 */
//                myTextView.setText("您选择的是：" + adapter.getItem(arg2));
               kstime = adapter.getItem(arg2);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
//                myTextView.setText("NONE");
            }
        });

        spinner2.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                /* 将所选mySpinner 的值带入myTextView 中 */
//                myTextView.setText("您选择的是：" + adapter.getItem(arg2));
//                ToastUitl.longs(adapter.getItem(arg2));
                jstime = adapter.getItem(arg2);
                LogU.Ld("1608", "查询shijian关闭" + jstime);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
//                myTextView.setText("NONE");
            }
        });
        data = new ArrayList<>();

        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recyclerView.getAdapter().getItemViewType(position);//获得返回值
                if (type == 99) {
                    return mLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        data3 = new ArrayList<>();
        recyclerView.setLayoutManager(mLayoutManager);
        linkLabelAdapter = new CGGZYundongAdapter(this, data, data3);


    }

    @Override
    protected void initData() {
        if (tag.equals("1")) {
            biaoti.setText("新增场馆信息");
        } else if (tag.equals("3")) {

            biaoti.setText("更正场馆信息");

            init(uid);
        } else {
            biaoti.setText("更正场馆信息");
        }


        if (tag.equals("2") || tag.equals("3")) {


            name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    name.setSelection(s.length());
                    if (s.length() <= 0) {
                        listView.setVisibility(View.GONE);
//                    return;
                    } else {
                        if (bj == 2) {
                            listView.setVisibility(View.VISIBLE);
                            chaxun(s.toString());
                        } else {
                            bj = 2;
                        }
                    }
                }
            });

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                shuzi.setText("剩余字数：" + (maxNum - s.length()));
            }
        });



        dianjiGrid();
        huoquyundong();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cggz_dizhi:
                Intent intent = new Intent();
                intent.setClass(this, DituActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);
                break;
            case R.id.cggz_xiayibu:

                initjihe(tag);


                break;
            case R.id.cggz_wifi:
                if (wifitag.equals("0")) {
                    wifi.setBackgroundResource(R.mipmap.wifihong);
                    wifitag = "1";
                } else {
                    wifi.setBackgroundResource(R.mipmap.wifihui);
                    wifitag = "0";
                }
                break;
            case R.id.cggz_tingche:
                if (tingchetag.equals("0")) {
                    tingche.setBackgroundResource(R.mipmap.tingchehong);
                    tingchetag = "1";
                } else {
                    tingche.setBackgroundResource(R.mipmap.tingchehui);
                    tingchetag = "0";
                }
                break;
            case R.id.cggz_linyu:
                if (linyutag.equals("0")) {
                    linyu.setBackgroundResource(R.mipmap.linyuhong);
                    linyutag = "1";
                } else {
                    linyu.setBackgroundResource(R.mipmap.linyuhui);
                    linyutag = "0";
                }
                break;

        }
    }

    //    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == 2){
//
//            Bundle b = data.getExtras();
//
//            lat =  b.getString("lat");
//            log =  b.getString("log");
//            city =  b.getString("city");
//            district =  b.getString("district");
//            province =  b.getString("province");
//        }
//
//    }
    //模糊查询
    private void chaxun(String searchParams) {

        LogU.Ld("1608", "查询" + searchParams);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/seachSiteName")
                .addHeader("token", token)
                .addParams("searchParams", searchParams)
                .addParams("page", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "查询" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            CGCXEntity entity = gson.fromJson(result, CGCXEntity.class);
                            List<CGCXEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
                            data2.clear();
                            data2.addAll(list);
                            listView.setAdapter(CGCXadapter);
                            CGCXadapter.notifyDataSetChanged();
                            CGCXadapter.notifyDataSetInvalidated();
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    init(data2.get(position).getUid());
                                    listView.setVisibility(View.GONE);
                                    bj = 1;
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

    //场馆详细信息
    private void init(String uid) {

        LogU.Ld("1608", "场馆详细信息" + uid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSiteInfo")
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "场馆详细信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            final CGXXEntity entity = gson.fromJson(result, CGXXEntity.class);
                            siteId = entity.getData().getUid();
                            name.setText(entity.getData().getName());
                            dizhiText.setText(entity.getData().getAddress());
                            shouji.setText(entity.getData().getTelephone());
                            city = entity.getData().getCity();
                            district = entity.getData().getArea();
                            province = entity.getData().getProvince();
                            lat = entity.getData().getLat();
                            log = entity.getData().getLng();
                            xxdz.setText(entity.getData().getAddres());
                            editText.setText(entity.getData().getComment());
                            List<CGXXEntity.DataBean.SupportSportIDBean> list;
                            list = entity.getData().getSupportSportID();
                            data3.addAll(list);
                            linkLabelAdapter.notifyDataSetChanged();
//                            linkLabelAdapter.notifyDataSetChanged();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < entity.getData().getFilesURL().length; i++) {

//                                        list2.add(String.valueOf(getResources().getString(R.string.imgurl) + entity.getData().getFilesURL().get(i)));
//                                        LogU.Ld("1608", "转换" + Uri.parse(String.valueOf(getResources().getString(R.string.imgurl) + entity.getData().getFilesURL().get(i))));
                                        urlpath = FileUtilcll.saveFile(CGGZActivity.this, "temphead.jpg", returnBitmap(String.valueOf(getResources().getString(R.string.imgurl) + entity.getData().getFilesURL()[i])));
                                        list2.add(urlpath);


//                                        LogU.Ld("1608", "转换2" + returnBitmap(String.valueOf(getResources().getString(R.string.imgurl) + entity.getData().getFilesURL().get(i))));
                                        LogU.Ld("1608", "转换2" + list2.toString());
//                                        adapter2.notifyDataSetChanged();

                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter2.notifyDataSetChanged();
                                            adapter2.notifyDataSetInvalidated();
                                        }
                                    });
                                }

                            }).start();
//                             adapter2 = new GvAdapter(CGGZActivity.this, list2);
//                            gridView.setAdapter(adapter2);
//
//                            adapter2.notifyDataSetChanged();
//                            adapter2.notifyDataSetInvalidated();
//                                list2.add(String.valueOf(getResources().getString(R.string.imgurl)+entity.getData().getFilesURL().get(1)));
//                                list2.add(String.valueOf(getResources().getString(R.string.imgurl)+entity.getData().getFilesURL().get(2)));


                            if (entity.getData().getSiteInfoext().getParking() == 1) {
                                tingche.setBackgroundResource(R.mipmap.tingchehong);
                                tingchetag = "1";
                            } else {
                                tingche.setBackgroundResource(R.mipmap.tingchehui);
                                tingchetag = "0";
                            }

                            if (entity.getData().getSiteInfoext().getWifi() == 1) {
                                wifi.setBackgroundResource(R.mipmap.wifihong);
                                wifitag = "1";
                            } else {
                                wifi.setBackgroundResource(R.mipmap.wifihui);
                                wifitag = "0";
                            }

                            if (entity.getData().getSiteInfoext().getShower() == 1) {
                                linyu.setBackgroundResource(R.mipmap.linyuhong);
                                linyutag = "1";
                            } else {
                                linyu.setBackgroundResource(R.mipmap.linyuhui);
                                linyutag = "0";
                            }


                            listView.setVisibility(View.GONE);
                            if (entity.getData().getStarttime().length() > 1) {
                                LogU.Ld("1608", "" + Integer.parseInt(entity.getData().getEndtime().substring(0, entity.getData().getEndtime().indexOf(":"))));
                                spinner.setSelection(Integer.parseInt(entity.getData().getStarttime().substring(0, entity.getData().getStarttime().indexOf(":"))));
                                spinner2.setSelection(Integer.parseInt(entity.getData().getEndtime().substring(0, entity.getData().getEndtime().indexOf(":"))));

                            }
//                            spUtils.put(CGXXActivity.this, "cgname", entity.getData().getName());
//                            spUtils.put(CGXXActivity.this, "cgid", uid);
//                            zoongxing.setText(entity.getData().getScores()+"分");
//                            shezhi.setText("设施:"+entity.getData().getEquscore()+"分");
//                            fuwu.setText("环境:"+entity.getData().getEnvscore()+"分");
//                            jiage.setText("性价比:"+entity.getData().getXjbScore()+"分");
//                            starBar.setStarMark((float) Double.parseDouble(entity.getData().getScores()));


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(CGGZActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //addSiteInfo
    //新增场馆 lat, log, city, district, province
    private void xinzeng(String siteInfoext, String imgURL, String imgBaseURL) {

        LogU.Ld("1608", "新增场馆" + token + "siteName" + name.getText().toString() + "siteAddress" + dizhiText.getText().toString() + "siteTel" + shouji.getText().toString()
                + "openTime" + kstime + "siteSport" + s + "city" + city + "area" + district + "lat" + lat + "log" + log + "siteInfoext" + siteInfoext + "imgURL" + imgURL + "imgBaseURL" + imgBaseURL
                + "closeTime" + jstime + "siteComment" + editText.getText().toString() + "province" + province);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addSiteInfo")
                .addHeader("token", token)
                .addParams("siteName", name.getText().toString())
                .addParams("siteAddress", dizhiText.getText().toString())
                .addParams("siteTel", shouji.getText().toString())
                .addParams("openTime", "")
                .addParams("fromTime", kstime+"")
                .addParams("siteSport", s)
                .addParams("city", city)
                .addParams("area", district)
                .addParams("lat", lat)
                .addParams("lng", log)
                .addParams("siteInfoext", siteInfoext)
                .addParams("imgURL", imgURL)
                .addParams("imgBaseURL", imgBaseURL)
                .addParams("closeTime", jstime+"")
                .addParams("siteComment", editText.getText().toString())
                .addParams("province", province)
                .addParams("address", xxdz.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "新增场馆" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            Intent intent = new Intent();
//                            intent.setClass(CGGZActivity.this,MyShezhiActivity.class);
//                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

    //updateSiteInfo
    //更正场馆 lat, log, city, district, province
    private void gengzheng(String siteInfoext, String imgURL, String imgBaseURL) {

        LogU.Ld("1608", "更正场馆" + token + "siteName" + name.getText().toString() + "siteAddress" + dizhiText.getText().toString() + "siteTel" + shouji.getText().toString()
                + "openTime" + kstime + "siteSport" + s + "city" + city + "area" + district + "lat" + lat + "log" + log + "siteInfoext" + siteInfoext + "imgURL" + imgURL + "imgBaseURL" + imgBaseURL
                + "closeTime" + jstime + "siteComment" + editText.getText().toString() + "province" + province);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addSiteInfo")
                .addHeader("token", token)
                .addParams("siteId", siteId)
                .addParams("siteName", name.getText().toString())
                .addParams("siteAddress", dizhiText.getText().toString())
                .addParams("siteTel", shouji.getText().toString())
                .addParams("openTime", "")
                .addParams("fromTime", kstime)
                .addParams("siteSport", s)
                .addParams("city", city)
                .addParams("area", district)
                .addParams("lat", lat)
                .addParams("lng", log)
                .addParams("siteInfoext", siteInfoext)
                .addParams("imgURL", imgURL)
                .addParams("imgBaseURL", imgBaseURL)
                .addParams("closeTime", jstime)
                .addParams("siteComment", editText.getText().toString())
                .addParams("province", province)
                .addParams("imgCount", list2.size() + "")
                .addParams("address", xxdz.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "更正场馆" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            Intent intent = new Intent();
//                            intent.setClass(CGGZActivity.this,MyShezhiActivity.class);
//                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

    //新增场馆图片
    private void initDownload(final String ss, final String tag) {

//        ss = GetFile.getFile(BitmapFactory.decodeFile(path), 1, 2 + "").toString();
//        File img = new File(ss);

//        for (String s : list) {
//            img = new File(s);
//            LogU.Ld("1608", "发布精彩" + img);
//        }
        LogU.Ld("1608", "图片上传接口" + list2.toString());
        PostFormBuilder builder = OkHttpUtils.post();
        builder.url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadSiteImg")
                .addHeader("token", token);


        for (int i = 0; i < list2.size(); i++) {
            File file = new File(list2.get(i));
            if (!file.exists()) {
                LogU.Ld("1608", "文件不存在，请修改文件路径");
                return;
            }
            String filename = file.getName();
            builder.addFile("files[]", filename, file);
        }
        builder.build()

                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "新增场馆图片" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ZhaopianPostEntity entity = gson.fromJson(result, ZhaopianPostEntity.class);
                            imgURL = entity.getData().getFilesURL();
                            baseURL = entity.getData().getBaseURL();
//                            initZong();
                            if (tag.equals("1")) {
                                xinzeng(ss, imgURL, baseURL);
                            } else {
                                gengzheng(ss, imgURL, baseURL);
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(CGGZActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }
                    }
                });
    }

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
                            data.clear();
                            data.addAll(list);
                            recyclerView.setAdapter(linkLabelAdapter);
                            linkLabelAdapter.notifyDataSetChanged();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(CGGZActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void initjihe(String tag) {
        JSONArray array1;
        array1 = new JSONArray();
        JSONObject object2 = new JSONObject();
        try {
            object2.put("parking", tingchetag);
            object2.put("wifi", wifitag);
            array1.put(object2);
        } catch (Exception e) {

        }
        System.out.println(array1.toString());
        LogU.Ld("1608", "邀请人拼接json------" + array1.toString());
        LogU.Ld("1608", "邀请人拼接json------" + object2.toString());

        //这边其实可以优化 但是时间不够就不再做优化。
        boolean zhishao = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                zhishao = true;
            }
        }
        if (!zhishao) {
            ToastUitl.longs("至少选择一项运动。");
            return;
        }
        hashMap.clear();
        list1.clear();
        s = "";
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                list1.add(data.get(i).getId() + "");
                s += data.get(i).getId() + "|";
            }
        }

        s = s.substring(0, s.length() - 1);
//        if (list1.size() > 0) {
//            hashMap.put("1", list1);
//        }
//
//        String s = gson.toJson(hashMap);

//        s=list1.toString().replace("[","");
//        s=list1.toString().replace("]","");
//
////根据逗号切割数据
//        String[] temp = list1.toString().split(",");
//        for (int i = 0;i<temp.length;i++){
//            //数据叠加且用空格替换
//            s += temp[i]+"|";
//        }
//        ToastUitl.longs("选中数据--" + s);
        LogU.Ld("1608", s);
        if (list2.size() < 1) {
            if (tag.equals("1")) {
                xinzeng(object2.toString(), "", "");
            } else {
                gengzheng(object2.toString(), "", "");
            }
        } else {
            initDownload(object2.toString(), tag);
        }


    }

    public void dianjiGrid() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (list2.size() > 2) {
                    ToastUitl.longs("最多只能上传3张照片");
                } else {


                    ActivityCompat.requestPermissions(CGGZActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    new ActionSheetDialog(CGGZActivity.this)
                            .builder()
                            .setCancelable(true)
                            .setCanceledOnTouchOutside(true)
                            .addSheetItem("相册",
                                    ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {

                                        @Override
                                        public void onClick(int which) {

//                                        Intent intent = new Intent(Intent.ACTION_PICK, null);
//                                        intent.setDataAndType(
//                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                                "image/*");
//                                        startActivityForResult(intent, 10);

                                            ImageSelector.show(CGGZActivity.this, 3, 3-list2.size());
                                        }
                                    })
                            .addSheetItem("拍照",
                                    ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {

                                        @Override
                                        public void onClick(int which) {

//                                        Intent intent = new Intent(
//                                                MediaStore.ACTION_IMAGE_CAPTURE);
//                                        Log.d("1608", "走的1");
//                                        //下面这句指定调用相机拍照后的照片存储的路径
//                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
//                                                .fromFile(new File(Environment
//                                                        .getExternalStorageDirectory(),
//                                                        "xiaoma.jpg")));
//                                        Log.d("1608", "走的2");
//                                        startActivityForResult(intent, 10);
                                            getPermissions();
                                        }
                                    }).show();


                }
            }
        });

    }

    /**
     * 获取权限
     */
    private void getPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager
                            .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED) {
                startActivityForResult(new Intent(CGGZActivity.this, PaisheActivity.class), 100);
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(CGGZActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(CGGZActivity.this, PaisheActivity.class), 100);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("1608", requestCode + "++相机1" + data);
        if (requestCode == 1 && resultCode == 2) {

            Bundle b = data.getExtras();

            lat = b.getString("lat");
            log = b.getString("log");
            city = b.getString("city");
            district = b.getString("district");
            province = b.getString("province");
            address = b.getString("address");
            sematicDescription = b.getString("sematicDescription");
            dizhiText.setText(address + sematicDescription + "");
        }


        if (resultCode == 101) {

            path = data.getStringExtra("path");
            //图片路径
//            urlpath = FileUtilcll.saveFile(JCFBActivity.this, "temphead.jpg", BitmapFactory.decodeFile(path));
            list2.add(String.valueOf(path));
            adapter2.notifyDataSetChanged();
            Log.i("1608", "picture" + path + "---" + urlpath);
//            xiangji.setImageBitmap(BitmapFactory.decodeFile(path));

        }
//        if (resultCode == 102) {
//
//            shipin = data.getStringExtra("shipin");
//            Log.i("1608", "video" + shipin);
//            ImageLoader imageLoader = null;
//            jcVideoPlayerStandard.setVisibility(View.VISIBLE);
//            jcVideoPlayerStandard.setUp(shipin
//                    , jcVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
//            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
//            imageLoader.getInstance().displayImage("",
//                    jcVideoPlayerStandard.thumbImageView);
//            gridView.setVisibility(View.GONE);
//        }
        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
        if (resultCode == 10) {
            Log.d("1608", "相机完毕");
            File temp = new File(Environment.getExternalStorageDirectory()
                    + "/xiaoma.jpg");
            Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp));
            startPhotoZoom(Uri.fromFile(temp));
        }
        if (resultCode == 12) {
            Log.d("1608", "裁剪结束");
            if (data != null) {
                setPicToView(data);
            }
        }

        if (requestCode == 3) {
            showContent(data);
            uploadImage(data);
            return;
        }
    }


    private void showContent(Intent data) {
        List<String> paths = ImageSelector.getImagePaths(data);
        if (paths.isEmpty()) {
//            mContentTv.setText(R.string.image_selector_select_none);
            return;
        }

//        mContentTv.setText(paths.toString());
        LogU.Ld("1608", "相册返回" + paths.toString());
    }


    private void uploadImage(Intent data) {
        if (data != null) {
            list = ImageSelector.getImagePaths(data);
//            for (int i = 0; i < pathlist.size(); i++) {                        /*                        * 从本地文件中读读取图片                        * */
//                String fileName = "";
//                file = new File(pathlist.get(i));
//                if (file.getName() == null) {
//                } else {
//                    fileName = getFileName(pathlist.get(i));
//                }
////                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("image/jpg"), file)).build();
//                Request build = new Request.Builder().url("http://172.16.52.10:8080/UploadDemo4/UploadFile") //TomCat服务器
//                        .post(requestBody).build();
//                new OkHttpClient().newCall(build).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        setResult(response.body().string(), true);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//            }
//        }
            for (int i = 0; i < list.size(); i++) {

                getimage(list.get(i));
                urlpath = FileUtilcll.saveFile(CGGZActivity.this, "temphead.jpg", getimage(list.get(i)));
                list2.add(urlpath);
                LogU.Ld("1608", "压缩后" + getimage(list2.get(i)));
                LogU.Ld("1608", "压缩后" + list2.toString());
            }

            GvAdapter adapter = new GvAdapter(CGGZActivity.this, list2);
            gridView.setAdapter(adapter);
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Log.d("1608", "剪切进来");
        Intent intent = new Intent("com.android.camera.action.CROP");
        //下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                .fromFile(new File(Environment
                        .getExternalStorageDirectory(),
                        "xiaom.jpg")));
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", false);

        //裁剪后的图片Uri路径，uritempFile为Uri类变量
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 12);

//        setPicToView(intent);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            bitmap = extras.getParcelable("data");
            //图片路径
            urlpath = FileUtilcll.saveFile(CGGZActivity.this, "temphead.jpg", bitmap);
            System.out.println("----------路径----------" + urlpath);


            if (list2.size() >= 6) {
                Toast.makeText(CGGZActivity.this, "最多选择六张图片", Toast.LENGTH_LONG).show();
            } else {
                Log.d("1608", urlpath + "啥" + bitmap + "啥" + picdata);
                list2.add(String.valueOf(urlpath));
            }
            adapter2.notifyDataSetChanged();
//            Itouxiang.setImageBitmap(bitmap);
        }
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */

    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();

        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了

        newOpts.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;

        int h = newOpts.outHeight;

        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 800f;// 这里设置高度为800f

        float ww = 480f;// 这里设置宽度为480f

        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = 1;// be=1表示不缩放

        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放

            be = (int) (newOpts.outWidth / ww);

        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放

            be = (int) (newOpts.outHeight / hh);

        }

        if (be <= 0)

            be = 1;

        newOpts.inSampleSize = be;// 设置缩放比例

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

    }


    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩

            baos.reset(); // 重置baos即清空baos

            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

            options -= 10;// 每次都减少10

        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中

        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        return bitmap;

    }


    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


}
