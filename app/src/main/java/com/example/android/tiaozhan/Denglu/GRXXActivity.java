package com.example.android.tiaozhan.Denglu;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.util.Log;
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
import com.example.android.tiaozhan.Adapter.GRXXGridAdapter;
import com.example.android.tiaozhan.Adapter.GrenAdapter;
import com.example.android.tiaozhan.Adapter.GvAdapter;
import com.example.android.tiaozhan.Adapter.ImageListAdapter;
import com.example.android.tiaozhan.Entity.GRxxInfoEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PersonalprofileEntity;
import com.example.android.tiaozhan.Entity.TouxiangEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.AddressPickTask;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.FileUtilcll;
import com.example.android.tiaozhan.Toos.GetFile;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.NetUtil;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.fuyin.constans.P;
import com.example.android.tiaozhan.Toos.fuyin.ui.ImagePreviewActivity;
import com.example.android.tiaozhan.Toos.mypicker.DatePickerDialog;
import com.example.android.tiaozhan.Toos.mypicker.DateUtil;


import com.example.android.tiaozhan.Wonderful.PaisheActivity;
import com.google.gson.Gson;


import com.jaeger.library.StatusBarUtil;


import com.lcw.library.imagepicker.ImagePicker;

import com.scrat.app.selectorlibrary.ImageSelector;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
//import io.reactivex.functions.Consumer;

import okhttp3.Call;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 个人信息
 */

public class GRXXActivity extends BaseActivity implements View.OnClickListener {


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;


    private Context mContext;


    private Dialog dateDialog;

    private ImageListAdapter mImage;
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private String filePath, path;

    private List<String> list = new ArrayList<String>();
    private List<String> gRlist = new ArrayList<String>();
    private List<String> zSpic = new ArrayList<String>();

    private List<String> tuZList = new ArrayList<String>();
    private List<String> list2;//照片集合
    private GrenAdapter adapter2;//照片适配器
    private TextView biaoti, riqi, youshangjiao, age, changzhu;
    private EditText name, shengao, tizhong, jianjie;
    private ImageView fanhui, touxiang;
    private RelativeLayout wancheng;
    private LinearLayout LayoutTouxiang, LayoutName, LayoutAge, LayoutChusheng, LayoutShengao, LayoutTizhong, LayputYundong, LayoutChangzhu, LayoutJianjie, grxx_jianjie_zhao, zhaopian;
    private Dialog mCameraDialog;
    private int mYear, mMonth, mDay;
    private Bitmap bitmap, bitmapGe;
    private String urlpath, ss, tab, xiuTag = "0", gren, sg, tz, gr_sex;//图片路径
    private Uri uritempFile;

    private TextView text_nv, text_nan;
    private String token, sgeString, days = "", changzhuString = "", uuid;
    private String sex = "", sfzString, nm;
    private SPUtils spUtils;
    private RelativeLayout nan, nv;
    private List<GRxxInfoEntity.DataBean.FaveriteSportBean> data;
    private GridView gridView;
    private GridView mRlvImage;
    private GRXXGridAdapter adapter;
    private String urlpathGR, urlNameGR;
    private String dataTu;
    private int registration;
    private JiekouSBEntity entity;
    private Boolean b;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_grxx);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_grxx;

    }

    @Override
    protected void initUIAndListener() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);

        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        LayoutTouxiang = findViewById(R.id.grxx_touxiang);
        LayoutTouxiang.setOnClickListener(this);
        LayoutName = findViewById(R.id.grxx_name);
        LayoutName.setOnClickListener(this);
        LayoutAge = findViewById(R.id.grxx_age);
        LayoutAge.setOnClickListener(this);
        LayoutChusheng = findViewById(R.id.grxx_chusheng);
        LayoutChusheng.setOnClickListener(this);
        riqi = findViewById(R.id.grxx_riqi);
        LayoutShengao = findViewById(R.id.grxx_shengao);
        LayoutShengao.setOnClickListener(this);
        LayoutTizhong = findViewById(R.id.grxx_tizhong);
        LayoutTizhong.setOnClickListener(this);
        LayputYundong = findViewById(R.id.grxx_yundong);
        LayputYundong.setOnClickListener(this);
        LayoutChangzhu = findViewById(R.id.grxx_changzhu);
        LayoutChangzhu.setOnClickListener(this);
        LayoutJianjie = findViewById(R.id.grxx_jianjie);
        LayoutJianjie.setOnClickListener(this);
        mRlvImage = findViewById(R.id.gern_grid);

        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        touxiang = findViewById(R.id.gexx_touxiang_img);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        spUtils = new SPUtils();
        youshangjiao.setVisibility(View.VISIBLE);

        name = findViewById(R.id.grxx_text_name);
        //name.setSelection(name.getText().length());
        // name.setGravity(Gravity.RIGHT);
        age = findViewById(R.id.grxx_text_age);
        changzhu = findViewById(R.id.grxx_text_changzhu);
        shengao = findViewById(R.id.grxx_text_shengao);
        shengao.setSelection(shengao.getText().length());
        tizhong = findViewById(R.id.grxx_tizhong_text);
        tizhong.setSelection(tizhong.getText().length());
        jianjie = findViewById(R.id.grxx_text_jianjie);


        grxx_jianjie_zhao = findViewById(R.id.grxx_jianjie_zhao);
        grxx_jianjie_zhao.setOnClickListener(this);
        zhaopian = findViewById(R.id.zhaopian);

        gridView = findViewById(R.id.grxx_grid);

        wancheng = findViewById(R.id.grxx_wancheng);
        wancheng.setOnClickListener(this);

        data = new ArrayList<>();

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tab");
        xiuTag = bundle.getString("xiuTag");
        LogU.Ld("1660", "修改" + xiuTag);
        /*gren=(String)spUtils.get(this,"gr","");
        if (!EmptyUtils.isStrEmpty(gren)){
            jianjie.setText(gren);
        }*/
        //照片
        list2 = new ArrayList<>();
        adapter2 = new GrenAdapter(this, list2);
        mContext = this;
        // initImagePicker();
        //  initWidget();
        mRlvImage.setAdapter(adapter2);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        xiuTag = bundle.getString("xiuTag");
        tab = bundle.getString("tab");
        LogU.Ld("1660", "修改" + xiuTag + "===" + tab);


    }

    private void showImageDialog() {

        ActivityCompat.requestPermissions(GRXXActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        new ActionSheetDialog(GRXXActivity.this)
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
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image/*");
                                startActivityForResult(intent, 1010);
                                //   ImageSelector.show(PingjiaActivity.this, 3, 3 - list2.size());
                            }
                        })
                .addSheetItem("拍照",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {

                            @Override
                            public void onClick(int which) {


                                getPermissions();
                            }
                        }).show();


    }

    @Override
    protected void initData() {

        biaoti.setText("个人资料");
        if (tab.equals("1")) {
            youshangjiao.setText("跳过");
            youshangjiao.setVisibility(View.VISIBLE);
        } else {
            youshangjiao.setVisibility(View.INVISIBLE);
        }

        token = (String) spUtils.get(this, "logintoken", "无");
        uuid = (String) spUtils.get(this, "uuid", "无");

        initDownload();
        dianjiGrid();
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "token++++++++" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getuuidInfo")
                //               .addHeader("token",token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();

                        LogU.Ld("1608", "个人信息" + result);
                        SPUtils.put(GRXXActivity.this, "GRXXActivity.XinX", result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            GRxxInfoEntity entity = gson.fromJson(result, GRxxInfoEntity.class);
                            // spUtils.put(GRXXActivity.this, "nickname", entity.getData().getNickname());


                            // adapterImage.setImages(selImageList);
                            //  adapterImage.notifyDataSetChanged();
                            //  recyclerView.setAdapter(adapterImage);

                            final List<GRxxInfoEntity.DataBean.FaveriteSportBean> list;
                            list = entity.getData().getFaveriteSport();
                            data.clear();
                            data.addAll(list);
                            adapter = new GRXXGridAdapter(GRXXActivity.this, data);


                            gridView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            String imgURL = entity.getData().getImgURL();
                            LogU.Ld("1608", "图片" + imgURL);
                            if (!EmptyUtils.isStrEmpty(imgURL)) {
                                String substring = imgURL.substring(0, 4);
                                if (substring.equals("http")) {
                                    Glide.with(GRXXActivity.this).load(entity.getData().getImgURL()).into(touxiang);

                                } else {
                                    Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getImgURL()).into(touxiang);

                                }
                            }
                            registration = entity.getData().getRealNameRegistration();
                            List<String> personalImgurl = entity.getData().getPersonalImgurl();
                            if (tab.equals("8")) {
                                return;
                            } else {


                                String s = null;
                                for (int i = 0; i < personalImgurl.size(); i++) {
                                    //  selImageList.add(personalImgurl.get(i));
                                    //   Bitmap bitmap = returnBitmap(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                                    //    bmp.add(bitmap);
                                    list2.add(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                                    tuZList.add(personalImgurl.get(i));
                                    gRlist.add(personalImgurl.get(i));
                                    adapter2.notifyDataSetChanged();
                                    //  selImageList.addAll(imageItem)

                                }

                                sgeString = entity.getData().getSex() + "";
                                LogU.Ld("1608", "出生日期" + entity.getData().getBirthday());
                                if (!EmptyUtils.isStrEmpty(entity.getData().getBirthday())) {
                                    if (entity.getData().getBirthday().equals("0000-00-00")) {
                                        riqi.setText("为让您找到合适对手，请客观选择");
                                    } else {
                                        riqi.setText(entity.getData().getBirthday());
                                    }
                                } else {
                                    riqi.setText("为让您找到合适对手，请客观选择");
                                }
                                if (!EmptyUtils.isStrEmpty(entity.getData().getAddress())) {
                                    changzhu.setText(entity.getData().getAddress());
                                } else {
                                    changzhu.setText("将会邀您在常驻地参加有奖选拔比赛");
                                }

                                jianjie.setText(entity.getData().getComment());

                                if (entity.getData().getTall().equals("0") || EmptyUtils.isStrEmpty(entity.getData().getTall())) {
                                    shengao.setText("");
                                } else {
                                    shengao.setText(entity.getData().getTall());
                                    /*if (entity.getData().getTall().equals(shengao.getText().toString())) {
                                        shengao.setText(entity.getData().getTall());
                                    } else {
                                        shengao.setText(sg);
                                    }*/
                                }


                                if (entity.getData().getWeight().equals("0") || EmptyUtils.isStrEmpty(entity.getData().getWeight())) {
                                    tizhong.setText("");
                                } else {
                                    tizhong.setText(entity.getData().getWeight());
                                }

                                if (entity.getData().getSex() == 0) {
                                    age.setText("男");
                                } else if (entity.getData().getSex() == 1) {
                                    age.setText("女");
                                } else {
                                    age.setText("为让您找到合适对手，请客观选择");
                                }

                                name.setText(entity.getData().getNickname());
                            }
                            if (personalImgurl.size() > 0) {
                                zhaopian.setVisibility(View.VISIBLE);
                            } else {
                                zhaopian.setVisibility(View.GONE);

                            }


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GRXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.fanhui:
                LogU.Ld("1608","网络判断"+token);

                xiugai(1);
                    finish();

                break;
            case R.id.grxx_touxiang:
                xiugai(1);
                ActivityCompat.requestPermissions(GRXXActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                new ActionSheetDialog(GRXXActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("相册",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {

                                    @Override
                                    public void onClick(int which) {

                                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                                        intent.setDataAndType(
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                "image/*");
                                        startActivityForResult(intent, 10);
                                    }
                                })
                        .addSheetItem("拍照",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {

                                    @Override
                                    public void onClick(int which) {

                                        Intent intent = new Intent(
                                                MediaStore.ACTION_IMAGE_CAPTURE);
                                        Log.d("1608", "走的1");
                                        //下面这句指定调用相机拍照后的照片存储的路径
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                                .fromFile(new File(Environment
                                                        .getExternalStorageDirectory(),
                                                        "xiaoma.jpg")));
                                        Log.d("1608", "走的2");
                                        startActivityForResult(intent, 11);
                                    }
                                }).show();

                break;

//            case R.id.grxx_name:
//                intent.setClass(this, XiugaiXXActivity.class);
//                bundle.putString("tab", "1");
//                bundle.putString("biaoti", "更改昵称");
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
            case R.id.grxx_age:
                if (registration == 1) {
                    ToastUitl.longs("已进行实名认证，性别不可更改");

                } else {
                    if (sgeString.equals("2")) {
                        setDialog();
                    } else {
                        ToastUitl.longs("对不起，性别不可更改，如资料与您实际性别不符合，请进行实名认证");

                    }
                }
                break;
            case R.id.grxx_chusheng://出生日期
                //TODO 调用时间选择器
                if (registration == 1) {
                    ToastUitl.longs("已进行实名认证，出生年月不可更改");

                } else {
                    if (riqi.getText().toString().equals("为让您找到合适对手，请客观选择")) {
                        showDateDialog(DateUtil.getDateForString("1990-01-01"));
                    } else {
                        ToastUitl.longs("对不起，年龄不可更改，如资料与您实际年龄不符合，请进行实名认证");

                    }
                }


                //  new DatePickerDialog(GRXXActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
//            case R.id.grxx_shengao:
//                intent.setClass(this, XiugaiXXActivity.class);
//                bundle.putString("tab", "3");
//                bundle.putString("biaoti", "更改身高");
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
//            case R.id.grxx_tizhong:
//                intent.setClass(this, XiugaiXXActivity.class);
//                bundle.putString("tab", "4");
//                bundle.putString("biaoti", "更改体重");
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
            case R.id.grxx_jianjie:
//                intent.setClass(this, XiugaiXXActivity.class);
//                bundle.putString("tab", "5");
//                bundle.putString("biaoti", "个人简介");
//                intent.putExtras(bundle);
//                startActivity(intent);
                break;
            case R.id.grxx_yundong:

                    xiugai(1);
                    intent.setClass(this, XiugaiXXActivity.class);
                    bundle.putString("tab", "2");
                    bundle.putString("biaoti", "技术水平自我评定");


                    intent.putExtras(bundle);

                    startActivity(intent);

                break;
            case R.id.youshangjiao:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.grxx_changzhu://地址
                AddressPickTask task = new AddressPickTask(this);

                // task.setTextColor(activity.getResources().getColor(R.color.purple));
                task.setHideProvince(false);
                task.setHideCounty(false);

                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        showToast("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
//                            showToast(province.getAreaName() + city.getAreaName()+"2");
                            changzhu.setText(province.getAreaName() +","+ city.getAreaName());
                            changzhuString = province.getAreaName() + "," + city.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName());
                        } else {
//                            showToast(province.getAreaName() + city.getAreaName() + county.getAreaName()+"1");
                            changzhu.setText(province.getAreaName() +","+ city.getAreaName() +","+ county.getAreaName());
                            changzhuString = province.getAreaName() + "," + city.getAreaName() + "," + county.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                    }
                });
                task.execute("北京", "北京", "通州");
                break;
            case R.id.age_dialog_nan://选择男
                nan.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));

                text_nan.setTextColor(getResources().getColor(R.color.white));
                mCameraDialog.dismiss();
                sex = "0";
//                xiugai( sex+"","","");
                age.setText("男");
                spUtils.put(this, "gr_sex", "0");
                break;
            case R.id.age_dialog_nv://选择女
                nv.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                text_nv.setTextColor(getResources().getColor(R.color.white));
                mCameraDialog.dismiss();
                sex = "1";
                spUtils.put(this, "gr_sex", "1");
//                xiugai( sex+"","","");
                age.setText("女");
                break;

            case R.id.grxx_jianjie_zhao://选择照片
                zhaopian.setVisibility(View.VISIBLE);
                // ToastUitl.longs("开发中。。。。");
                break;

            case R.id.grxx_wancheng://完成按钮

                String niCheng = name.getText().toString();
                String sexName = age.getText().toString();
                String riqiName = riqi.getText().toString();
                String changZhuName = changzhu.getText().toString();

                spUtils.remove(this, "gr");
                spUtils.remove(this, "sg");
                spUtils.remove(this, "tz");
                spUtils.remove(this, "nm");
                LogU.Ld("1608", "提交" + sexName + "===" + riqiName + "====" + changZhuName);
                if (EmptyUtils.isStrEmpty(niCheng)) {
                    ToastUitl.longs("昵称不能为空");
                } else if (EmptyUtils.isStrEmpty(sexName) || sexName.equals("为让您找到合适对手，请客观选择")) {
                    ToastUitl.longs("性别不能为空");
                } else if (EmptyUtils.isStrEmpty(riqiName) || riqiName.equals("为让您找到合适对手，请客观选择")) {
                    ToastUitl.longs("出生日期不能为空");
                } else if (EmptyUtils.isStrEmpty(changZhuName) || changZhuName.equals("将会邀您在常驻地参加有奖选拔比赛")) {
                    ToastUitl.longs("常驻地不能为空");
                } else {

                    if (TextUtils.isEmpty(shengao.getText().toString())) {

                        xiugai(2);
                    } else {
                        Double a = Double.parseDouble(shengao.getText().toString());
//                    int a = Integer.parseInt(shengao.getText().toString());
                        if (a <= 250 && a >= 100) {


                            if (TextUtils.isEmpty(tizhong.getText())) {

                                xiugai(2);
                            } else {
                                Double b = Double.parseDouble(tizhong.getText().toString());
//                            int c = Integer.parseInt(tizhong.getText().toString());
                                if (b <= 200 && b >= 30) {
                                    xiugai(2);
                                } else {

                                    ToastUitl.longs("请输入真实体重  30-200kg");
                                    tizhong.setText("");
                                }
                            }

                        } else {

                            ToastUitl.longs("请输入真实身高  100-250cm");
                            shengao.setText("");
                        }
                    }
                }

                break;

        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //性别选择
    private void setDialog() {


        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.age_dialog, null);

        nan = root.findViewById(R.id.age_dialog_nan);
        nan.setOnClickListener(this);
        nv = root.findViewById(R.id.age_dialog_nv);
        nv.setOnClickListener(this);
        text_nan = root.findViewById(R.id.text_nan);
        text_nv = root.findViewById(R.id.text_nv);
        //初始化视图
//        root.findViewById(R.id.dialog_item_button1);
//        root.findViewById(R.id.dialog_item_button2);

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
        sex = (String) spUtils.get(this, "gr_sex", "2");
        if (!EmptyUtils.isStrEmpty(sex)) {
            if (sex.equals("0")) {
                nan.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                text_nan.setTextColor(getResources().getColor(R.color.white));
            } else if (sex.equals("1")) {
                nv.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                text_nv.setTextColor(getResources().getColor(R.color.white));
            }
        } else {
            sex = "2";
            // nan.setBackgroundDrawable(getResources().getDrawable(R.drawable.mos_bg));
            // text_nan.setTextColor(getResources().getColor(R.color.bbbbb));
            // nv.setBackgroundDrawable(getResources().getDrawable(R.drawable.mos_bg));
            // text_nv.setTextColor(getResources().getColor(R.color.bbbbb));
        }


        mCameraDialog.show();


    }

    /* *//**
     * 日期选择器对话框监听
     *//*
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
//            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }
            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }
            }
            riqi.setText(days);
//            xiugai("",days,"");
        }
    };
*/

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

    //上传照片
    public void touxiangupost() {

        ss = GetFile.getFile(bitmap, 1, 2 + "").toString();
        File img = new File(ss);
        try {
            Log.d("1608", bitmap + "----------分1割----------" + img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadHeaderImg")
                .addFile("img", "multipart/form-data.png", img)
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", result);
                        Boolean a = result.indexOf("获取成功") != -1;
                        Gson gson = new Gson();
                        TouxiangEntity entity = gson.fromJson(result, TouxiangEntity.class);
//                        Glide.with(GRXXActivity.this).load("http://192.168.0.203/tzOne/public/"+entity.getData()).into(touxiang);
                        spUtils.put(GRXXActivity.this, "touxiang", entity.getData());
                        Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData()).into(touxiang);

                    }
                });

    }


    //上传照片个人
    public void genRen() {
        final File[] files = {null};
        StringBuilder sb = new StringBuilder();

        if (list2.size() > 0) {
            for (int i = 0; i < list2.size(); i++) {
           /* if(i<pathList.size()-1){
                sb.append(pathList.get(i));
                sb.append(",");
            }else{
                sb.append(pathList.get(i));
            }*/
                //  files = new File(list2.get(i));
                LogU.Ld("1608", "图片解析" + list.size() + "=====" + list2.get(i));
                //  files = CompressHelper.getDefault(getApplicationContext()).compressToFile(new File(list2.get(i)));

                Luban.with(this)
                        .load(list2.get(i))
                        .ignoreBy(100)
                        // .setTargetDir(getPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                LogU.Ld("1608", "图片====解析" + "=====");
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                LogU.Ld("1608", "图片解析" + "=====" + file);
                                OkHttpUtils
                                        .post()
                                        .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PersonalprofileImg")
                                        .addFile("files[]", "multipart/form-data.png", file)
                                        .addHeader("token", token)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.d("1608", "个人简介上传失败" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                String result = response.toString();
                                                Log.d("1608", "个人简介上传" + result);
                                                Boolean a = result.indexOf("2000") != -1;

                                                if (a) {
                                                    Gson gson = new Gson();
                                                    PersonalprofileEntity entity = gson.fromJson(result, PersonalprofileEntity.class);
                                                    dataTu = entity.getData();
                                                    tuZList.add(dataTu);

//                        Glide.with(GRXXActivity.this).load("http://192.168.0.203/tzOne/public/"+entity.getData()).into(touxiang);
                                                    //   String filesURL = entity.getData().getFilesURL();
                                                    //   String baseURL = entity.getData().getBaseURL();
                                                    // list2.add(baseURL + filesURL);
                                                    //  mImageAdapter.notifyDataSetChanged();
                                                    // spUtils.put(GRXXActivity.this, "touxiang", entity.getData());
                                                    //   Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData()).into(touxiang);
                                                } else {
                                                    Gson gson = new Gson();
                                                    PersonalprofileEntity entity = gson.fromJson(result, PersonalprofileEntity.class);
                                                    Toast.makeText(GRXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                LogU.Ld("1608", "图片====解析" + file + "=====" + files[0]);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                LogU.Ld("1608", "图片解析失败" + e.getMessage() + "=====");

                            }
                        }).launch();
                // files = getFile(getimage(list2.get(i)));
                // String s  = FileUtilcll.saveFile(GRXXActivity.this, "temphead.jpg", getimage(list.get(i)));

                //  files = new File(s);
            }

        }


    }


    //上传照片个人
    public void genRenDel(String imgurl) {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/DelPersonalprofileImg")
                .addHeader("token", token)
                .addParams("imgurl", imgurl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("1608", "个人简介删除失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "个人简介删除" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Boolean b = result.indexOf("4001") != -1;
                        Boolean c = result.indexOf("4002") != -1;
                        if (a) {
                            Toast.makeText(GRXXActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                        }

                        if (b) {
                            // PersonalprofileEntity entity = gson.fromJson(result, PersonalprofileEntity.class);
                            Toast.makeText(GRXXActivity.this, "登录超时", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    //修改信息
    private void xiugai(final int tag) {
        LogU.Ld("1608", "修改信息" + token + "---name---" + name.getText().toString() + "---sex---" + sex + "---birthday---" + days +
                "---address---" + changzhuString + "---tall---" + shengao.getText().toString()
                + "---weight---" + tizhong.getText().toString());
        String toStringName = null;
        String shen = shengao.getText().toString();
        String ti = tizhong.getText().toString();
        StringBuilder sbName = new StringBuilder();

        if (age.getText().toString().equals("男")) {
            sex = "0";
        } else if (age.getText().toString().equals("女")) {
            sex = "1";
        } else {
            sex = "2";
        }
        LogU.Ld("1608", "修改信息" + token + "---name---" + name.getText().toString() + "---sex---" + sex + "---birthday---" + riqi.getText().toString() + "==" + days +
                "---address---" + changzhuString + "---tall---" + shengao.getText().toString()
                + "---weight---" + tizhong.getText().toString());


        if (tuZList.size() > 0) {
            for (int i = 0; i < tuZList.size(); i++) {
                if (i < tuZList.size() - 1) {
                    sbName.append(tuZList.get(i));
                    sbName.append(",");
                } else {
                    sbName.append(tuZList.get(i));
                }
            }
        } else {

            toStringName = null;

        }
        toStringName = sbName.toString();
        LogU.Ld("1608", "图片集合" + toStringName);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PerfectInfo")
                .addHeader("token", token)
                .addParams("nickname", name.getText().toString())
                .addParams("sex", sex + "")
                .addParams("birthday", riqi.getText().toString())
                .addParams("address", changzhuString)
                .addParams("imgURL", "")
                .addParams("sport_id", "")
                .addParams("tall", shen + "")
                .addParams("weight", ti + "")
                .addParams("comment", jianjie.getText().toString() + "")
                .addParams("personalImgurl", toStringName + "")

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "修改信息失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "修改信息" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        b = result.indexOf("4001") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (tag == 1) {

                            } else {

                                if (tab.equals("1")) {
                                    Intent intent = new Intent();
                                    intent.setClass(GRXXActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    ToastUitl.longs("修改成功");
                                    finish();
                                } else {
                                    spUtils.put(GRXXActivity.this, "nickname", name.getText().toString());
                                    ToastUitl.longs("修改成功");
                                    finish();
                                }
                            }

                        } else {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GRXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (b) {
                                Intent intent = new Intent();
                                intent.setClass(GRXXActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }

                        }
                    }
                });

    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  getTakePhoto().onActivityResult(requestCode, resultCode, data);


        Log.i("1608", "picture" + "=======" + resultCode + "======" + data + "===" + requestCode);

        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 10 && resultCode == RESULT_OK) {
            startPhotoZoom(data.getData());
        }
        if (requestCode == 11 && resultCode == RESULT_OK) {


            File temp = new File(Environment.getExternalStorageDirectory()
                    + "/xiaoma.jpg");
            Log.d("1608", "相机完毕上" + temp + "------" + Uri.fromFile(temp));
            startPhotoZoom(Uri.fromFile(temp));


        }

        if (requestCode == 12 && resultCode == RESULT_OK) {
            Log.d("1608", "裁剪结束..");
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                Log.d("1608", bitmap + "--" + uritempFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            touxiangupost();
        }


        Log.d("1608", requestCode + "++相机1" + data + "====" + resultCode);
       /* if (data == null) {
            switch (requestCode) {
                case 11:
                    Log.d("1608", "相机完毕上");
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/xiaoma.jpg");
                    Log.d("1608", "相机完毕上" + temp + "------" + Uri.fromFile(temp));
                    startPhotoZoom(Uri.fromFile(temp));
                    break;
                // 取得裁剪后的图片
                case 12:
                    Log.d("1608", "裁剪结束null状态");
//                    if (data != null) {
//                        setPicToView(data);
//                    }
                    if (data != null) {
                        touxiangupost();
                    }

                    break;
            }

        } else {
            if (data != null) {
                String s = data.getStringExtra("neirong");
                switch (requestCode) {
//            case 10:
//                Bundle bundle = data.getExtras();
//                bitmap = bundle.getParcelable("bitmap");
////            ss= GetFile.getFile(bitmap,UserID,id+"").toString();
//                ss= GetFile.getFile(bitmap,1,2+"").toString();
//                Itouxiang.setImageBitmap(bitmap);
//                Log.d("1608", ss+"---------照片--------------" + bitmap);
//                break;
                    case 10:
                        startPhotoZoom(data.getData());
                        break;
                    // 如果是调用相机拍照时
                    case 11:
                        Log.d("1608", "相机完毕");
                            *//*File temp = new File(Environment.getExternalStorageDirectory()
                                    + "/xiaoma.jpg");
                            Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp));
                            startPhotoZoom(Uri.fromFile(temp));*//*

                        if (!EmptyUtils.isStrEmpty(data.getStringExtra("path"))) {

                            File temp = new File(data.getStringExtra("path"));
                            startPhotoZoom(Uri.fromFile(temp));

                        }

                        break;
                    // 取得裁剪后的图片
                    case 12:
                        Log.d("1608", "裁剪结束..");
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                            Log.d("1608", bitmap + "--" + uritempFile);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        touxiangupost();
                        break;
                }
            }
        }
        */

        if (requestCode == 1000 && resultCode == 101) {

            if (data != null) {
                if (!EmptyUtils.isStrEmpty(data.getStringExtra("path"))) {
                    path = data.getStringExtra("path");
                    //图片路径
//            urlpath = FileUtilcll.saveFile(JCFBActivity.this, "temphead.jpg", BitmapFactory.decodeFile(path));
                    list2.add(String.valueOf(path));
                    adapter2.notifyDataSetChanged();
                    genRen();
                }
            }
        }

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            list2.addAll(imagePaths);
            adapter2.notifyDataSetChanged();
            genRen();
            LogU.Ld("1608", "图片路径" + imagePaths + "===" + imagePaths);

        }
    }


    @Override
    protected void onResume() {
        if (!EmptyUtils.isStrEmpty(tab)) {
            if (tab.equals("8")) {
                initDownload();
            }
        }



        LogU.Ld("1660", "修改" + xiuTag + "===" + tab);
        gren = (String) spUtils.get(this, "gr", "");
        sg = (String) spUtils.get(this, "sg", "");
        tz = (String) spUtils.get(this, "tz", "");
        sex = (String) spUtils.get(this, "gr_sex", "2");
        nm = (String) spUtils.get(this, "nm", "");

        if (!EmptyUtils.isStrEmpty(nm)) {
            name.setText(nm);
        }
        if (!EmptyUtils.isStrEmpty(sex)) {
            if (sex.equals("0")) {
                age.setText("男");
            } else if (sex.equals("1")) {
                age.setText("女");
            }
        }

        if (!EmptyUtils.isStrEmpty(gren)) {
            jianjie.setText(gren);
        }

        if (!EmptyUtils.isStrEmpty(sg)) {
            shengao.setText(sg);
        }
        if (!EmptyUtils.isStrEmpty(tz)) {
            tizhong.setText(tz);
        }
        name.setSelection(name.getText().toString().length());
        shengao.setSelection(shengao.getText().toString().length());
        tizhong.setSelection(tizhong.getText().toString().length());
        jianjie.setSelection(jianjie.getText().toString().length());
        String grXX = (String) SPUtils.get(GRXXActivity.this, "GRXXActivity.XinX", "");
        if (NetUtil.getNetWorkStart(GRXXActivity.this) == 1) {

            Gson gson = new Gson();
            if (!EmptyUtils.isStrEmpty(grXX)) {
                GRxxInfoEntity entity = gson.fromJson(grXX, GRxxInfoEntity.class);
                // spUtils.put(GRXXActivity.this, "nickname", entity.getData().getNickname());

                registration = entity.getData().getRealNameRegistration();
                List<String> personalImgurl = entity.getData().getPersonalImgurl();

                String s = null;
                for (int i = 0; i < personalImgurl.size(); i++) {
                    //  selImageList.add(personalImgurl.get(i));
                    //   Bitmap bitmap = returnBitmap(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                    //    bmp.add(bitmap);
                    list2.add(getResources().getString(R.string.imgurl) + personalImgurl.get(i));
                    tuZList.add(personalImgurl.get(i));
                    gRlist.add(personalImgurl.get(i));
                    adapter2.notifyDataSetChanged();
                    //  selImageList.addAll(imageItem)

                }

                if (personalImgurl.size() > 0) {
                    zhaopian.setVisibility(View.VISIBLE);
                } else {
                    zhaopian.setVisibility(View.GONE);

                }


                // adapterImage.setImages(selImageList);
                //  adapterImage.notifyDataSetChanged();
                //  recyclerView.setAdapter(adapterImage);

                final List<GRxxInfoEntity.DataBean.FaveriteSportBean> list;
                list = entity.getData().getFaveriteSport();
                data.clear();
                data.addAll(list);
                adapter = new GRXXGridAdapter(GRXXActivity.this, data);


                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                String imgURL = entity.getData().getImgURL();
                LogU.Ld("1608", "图片" + imgURL);
                if (!EmptyUtils.isStrEmpty(imgURL)) {
                    String substring = imgURL.substring(0, 4);
                    if (substring.equals("http")) {
                        Glide.with(GRXXActivity.this).load(entity.getData().getImgURL()).into(touxiang);

                    } else {
                        Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getImgURL()).into(touxiang);

                    }
                }


                if (tab.equals("8")) {
                    return;
                } else {

                    sgeString = entity.getData().getSex() + "";
                    LogU.Ld("1608", "出生日期" + entity.getData().getBirthday());
                    if (!EmptyUtils.isStrEmpty(entity.getData().getBirthday())) {
                        if (entity.getData().getBirthday().equals("0000-00-00")) {
                            riqi.setText("为让您找到合适对手，请客观选择");
                        } else {
                            riqi.setText(entity.getData().getBirthday());
                        }
                    } else {
                        riqi.setText("为让您找到合适对手，请客观选择");
                    }
                    if (!EmptyUtils.isStrEmpty(entity.getData().getAddress())) {
                        changzhu.setText(entity.getData().getAddress());
                    } else {
                        changzhu.setText("将会邀您在常驻地参加有奖选拔比赛");
                    }

                    jianjie.setText(entity.getData().getComment());

                    if (entity.getData().getTall().equals("0") || EmptyUtils.isStrEmpty(entity.getData().getTall())) {
                        shengao.setText("");
                    } else {
                        shengao.setText(entity.getData().getTall());
                                    /*if (entity.getData().getTall().equals(shengao.getText().toString())) {
                                        shengao.setText(entity.getData().getTall());
                                    } else {
                                        shengao.setText(sg);
                                    }*/
                    }

                    if (entity.getData().getWeight().equals("0") || EmptyUtils.isStrEmpty(entity.getData().getWeight())) {
                        tizhong.setText("");
                    } else {
                        tizhong.setText(entity.getData().getWeight());
                    }

                    if (entity.getData().getSex() == 0) {
                        age.setText("男");
                    } else if (entity.getData().getSex() == 1) {
                        age.setText("女");
                    } else {
                        age.setText("为让您找到合适对手，请客观选择");
                    }

                    name.setText(entity.getData().getNickname());
                }
            }
        }
        super.onResume();

    }

    @Override
    protected void onPause() {
        String jj = jianjie.getText().toString();
        String sg = shengao.getText().toString();
        String tz = tizhong.getText().toString();
        String nm = name.getText().toString();
        LogU.Ld("1608", "====" + jj + "====" + jianjie.getText().toString());
        spUtils.put(this, "gr", jj);
        spUtils.put(this, "sg", sg);
        spUtils.put(this, "tz", tz);
        spUtils.put(this, "nm", nm);
        super.onPause();

    }

    @Override
    protected void onRestart() {
        //  initDownload();
        LogU.Ld("1660", "修改======" + xiuTag + "===" + tab);
        super.onRestart();
    }

    public void dianjiGrid() {
        mRlvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == list2.size()) {
                    ActivityCompat.requestPermissions(GRXXActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    new ActionSheetDialog(GRXXActivity.this)
                            .builder()
                            .setCancelable(true)
                            .setCanceledOnTouchOutside(true)
                            .addSheetItem("相册",
                                    ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {

                                        @Override
                                        public void onClick(int which) {

//
                                          /*Intent intent = new Intent(Intent.ACTION_PICK, null);
                                            intent.setDataAndType(
                                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                    "image/*");
                                            startActivityForResult(intent, 1010);*/

                                            ImagePicker.getInstance()
                                                    .setTitle("标题")//设置标题
                                                    .showCamera(true)//设置是否显示拍照按钮
                                                    .showImage(true)//设置是否展示图片
                                                    .showVideo(false)//设置是否展示视频
                                                    //    .filterGif(false)//设置是否过滤gif图片
                                                    .setSingleType(true)//设置图片视频不能同时选择
                                                    .setMaxCount(9 - list2.size())//设置最大选择图片数目(默认为1，单选)
                                                    // .setImagePaths((ArrayList<String>) list2)//保存上一次选择图片的状态，如果不需要可以忽略
                                                    .setImageLoader(new GlideLoader())//设置自定义图片加载器
                                                    .start(GRXXActivity.this, 1001);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode




                                            /*EasyPhotos.createAlbum(GRXXActivity.this, false, GlideEngine.getInstance())
                                                    .setCount(9 - list2.size())//参数说明：最大可选数，默认1
                                                    .start(new SelectCallback() {
                                                        @Override
                                                        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {

                                                            list2.addAll(paths);
                                                            adapter2.notifyDataSetChanged();
                                                            genRen();
                                                            LogU.Ld("1608", "图片路径" + paths + "===" + photos);
                                                        }
                                                    });*/
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

                } else {


                    Intent intent = new Intent(GRXXActivity.this, ImagePreviewActivity.class);

                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) list2);
                    intent.putExtra(P.START_ITEM_POSITION, 0);
                    intent.putExtra(P.START_IAMGE_POSITION, position);


                   /* intent.putStringArrayListExtra("imageList", (ArrayList<String>) list2);

                    intent.putExtra("start_item_image", position);
                    intent.putExtra("id", position);   //将当前点击的位置传递过去
*/

                    startActivity(intent);     //启动Activity


                }
            }
        });

        adapter2.setOnClickListener(new GrenAdapter.OnClickListener() {
            @Override
            public void onChildClickListen(int position) {
                if (tuZList != null && tuZList.size() > 0) {
                    LogU.Ld("1608", "删除照片" + tuZList.get(position));
                    genRenDel(tuZList.get(position));
                    tuZList.remove(position);


                    adapter.notifyDataSetChanged();
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
                startActivityForResult(new Intent(GRXXActivity.this, PaisheActivity.class), 1000);
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(GRXXActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(GRXXActivity.this, PaisheActivity.class), 100);
        }

    }


    private void showContent(Intent data) {
        List<String> paths = ImageSelector.getImagePaths(data);
        if (paths.isEmpty()) {
//            mContentTv.setText(R.string.image_selector_select_none);
            return;
        }

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
                urlpath = FileUtilcll.saveFile(GRXXActivity.this, "temphead.jpg", getimage(list.get(i)));
                list2.add(urlpath);
                LogU.Ld("1608", "压缩后" + getimage(list2.get(i)));
                LogU.Ld("1608", "压缩后" + list2.toString());
            }

            GvAdapter adapter = new GvAdapter(GRXXActivity.this, list2);
            gridView.setAdapter(adapter);
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhoto(Uri uri) {
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
        startActivityForResult(intent, 1212);


//        setPicToView(intent);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Bitmap picdata) {

        Log.d("1608", "----------路径----------" + urlpath + "啥" + bitmap + "啥" + picdata);
        if (picdata != null) {

            //图片路径
            urlpath = FileUtilcll.saveFile(GRXXActivity.this, "temphead.jpg", picdata);
            Log.d("1608", "----------路径----------" + urlpath + "啥" + bitmap + "啥" + picdata);
            Log.d("1608", urlpath + "啥" + bitmap + "啥" + picdata);

            if (list2.size() > 9) {
                Toast.makeText(GRXXActivity.this, "最多选择九张图片", Toast.LENGTH_LONG).show();
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

        float hh = 1080f;// 这里设置高度为800f

        float ww = 1920f;// 这里设置宽度为480f

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


    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                riqi.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));

            }

            @Override
            public void onCancel() {

            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }


    public static void compress(Bitmap.CompressFormat format, int quality) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        Bitmap originBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        originBitmap.compress(format, quality, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(sdFile, "resultImg.jpg"));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}
