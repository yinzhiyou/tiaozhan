package com.example.android.promoter.Denglu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Adapter.GRXXGridAdapter;
import com.example.android.promoter.Entity.GRxxInfoEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.TouxiangEntity;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.ActionSheetDialog;
import com.example.android.promoter.Toos.AddressPickTask;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.GetFile;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;

import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import okhttp3.Call;

/**
 *个人信息
 */

public class GRXXActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, riqi, youshangjiao,age,changzhu;
    private EditText name,shengao,tizhong,jianjie;
    private ImageView fanhui, touxiang;
    private RelativeLayout wancheng;
    private LinearLayout LayoutTouxiang, LayoutName, LayoutAge, LayoutChusheng, LayoutShengao, LayoutTizhong, LayputYundong,LayoutChangzhu,LayoutJianjie;
    private Dialog mCameraDialog;
    private int mYear, mMonth, mDay;
    private Bitmap bitmap;
    private String urlpath,ss,tab;//图片路径
    private Uri uritempFile;

    private String token,sgeString,days= "",changzhuString= "",uuid;
    private String sex = "",sfzString;
    private SPUtils spUtils;
    private RelativeLayout nan,nv;
    private List<GRxxInfoEntity.DataBean.FaveriteSportBean> data;
    private GridView gridView;
    private GRXXGridAdapter adapter;
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
        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),100);

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
        gridView = findViewById(R.id.grxx_grid);

        wancheng = findViewById(R.id.grxx_wancheng);
        wancheng.setOnClickListener(this);

        data = new ArrayList<>();

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tab =  bundle.getString("tab");

    }

    @Override
    protected void initData() {

        biaoti.setText("个人信息");
        if (tab.equals("1")){
            youshangjiao.setText("跳过");
            youshangjiao.setVisibility(View.VISIBLE);
        }else{
            youshangjiao.setVisibility(View.INVISIBLE);
        }

        token = (String) spUtils.get(this, "logintoken", "无");
        uuid=(String)spUtils.get(this,"uuid","无");
        initDownload();
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "token++++++++" + token);
            OkHttpUtils
                    .get()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getuuidInfo")
     //               .addHeader("token",token)
                    .addParams("uuid",uuid)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();

                            LogU.Ld("1608", "个人信息" + result);
                            Boolean a = result.indexOf("2000") != -1;
                            if (a) {
                                Gson gson = new Gson();
                                GRxxInfoEntity entity = gson.fromJson(result, GRxxInfoEntity.class);
                                spUtils.put(GRXXActivity.this, "nickname", entity.getData().getNickname());
                                Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl)+entity.getData().getImgURL()).into(touxiang);
                                name.setText(entity.getData().getNickname());

                                sgeString = entity.getData().getSex()+"";
                                if (entity.getData().getSex() == 0){
                                    age.setText("男");
                                }else   if (entity.getData().getSex() == 1){
                                    age.setText("女");
                                }else {
                                    age.setText("");
                                }

                                if (entity.getData().getBirthday().equals("0000-00-00")){

                                }else{
                                    riqi.setText(entity.getData().getBirthday());
                                }

                                changzhu.setText(entity.getData().getAddress());
                                if (entity.getData().getTall()==0){

                                }else{
                                    shengao.setText(entity.getData().getTall()+"");
                                }

                                if (entity.getData().getWeight()==0){

                                }else{
                                    tizhong.setText(entity.getData().getWeight()+"");
                                }


                                jianjie.setText(entity.getData().getComment());

                               final List<GRxxInfoEntity.DataBean.FaveriteSportBean> list;
                                list = entity.getData().getFaveriteSport();
                                data.clear();
                                data.addAll(list);
                                adapter = new GRXXGridAdapter(GRXXActivity.this,data);


                                gridView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

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
                finish();
                break;
            case R.id.grxx_touxiang:

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
                if (sgeString.equals("2")){
                    setDialog();
                }else{
                    ToastUitl.longs("性别不可更改");

                }

                break;
            case R.id.grxx_chusheng://出生日期
                //TODO 调用时间选择器
                new DatePickerDialog(GRXXActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
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
                bundle.putString("biaoti", "喜爱的运动");


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
                            changzhu.setText(province.getAreaName() + city.getAreaName());
                            changzhuString = province.getAreaName() +","+ city.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName());
                        } else {
//                            showToast(province.getAreaName() + city.getAreaName() + county.getAreaName()+"1");
                            changzhu.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                            changzhuString = province.getAreaName() +","+ city.getAreaName() + ","+county.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                    }
                });
                task.execute("北京", "北京", "通州");
                break;
            case R.id.age_dialog_nan://选择男
                nan.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                mCameraDialog.dismiss();
                sex = "0";
//                xiugai( sex+"","","");
                age.setText("男");
                break;
            case R.id.age_dialog_nv://选择女
                nv.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_rounded_corners));
                mCameraDialog.dismiss();
                sex = "1";
//                xiugai( sex+"","","");
                age.setText("女");
                break;
            case R.id.grxx_wancheng://完成按钮

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
        mCameraDialog.show();


    }

    /**
     * 日期选择器对话框监听
     */
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
            Log.d("1608", bitmap + "----------分1割----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadHeaderImg")
                .addFile("img","multipart/form-data.png",img)
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
                        spUtils.put(GRXXActivity.this,"touxiang",entity.getData());
                        Glide.with(GRXXActivity.this).load(getResources().getString(R.string.imgurl)+entity.getData()).into(touxiang);

                    }
                });

    }
    //修改信息
    private void xiugai(final int tag) {
        LogU.Ld("1608", "修改信息" + token+"---name---" + name.getText().toString()+"---sex---" + sex+"---birthday---" + days+
                "---address---" + changzhuString+"---tall---" + shengao.getText().toString()
                +"---weight---" + tizhong.getText().toString());

        String shen = shengao.getText().toString();
        String ti = tizhong.getText().toString();
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PerfectInfo")
                .addHeader("token", token)
                .addParams("nickname",name.getText().toString())
                .addParams("sex",sex+"")
                .addParams("birthday",days)
                .addParams("address",changzhuString)
                .addParams("imgURL","")
                .addParams("sport_id","")
                .addParams("tall",shen+"")
                .addParams("weight",ti+"")
                .addParams("comment",jianjie.getText().toString()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "修改信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            if (tag ==1){

                            }else{
                                Intent intent = new Intent();
                                intent.setClass(GRXXActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                ToastUitl.longs("修改成功");
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GRXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode  == 1) {
//            String s=data.getStringExtra("neirong");
//            Tnicheng.setText(s);
//        }
        Log.d("1608", requestCode + "++相机1" + data);
        if (data == null) {
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
                    touxiangupost();
                    break;
            }
        } else {
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
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/xiaoma.jpg");
                    Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp));
                    startPhotoZoom(Uri.fromFile(temp));
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

//                if (data != null) {
//                    setPicToView(data);
//                }
                    touxiangupost();
                    break;
            }
        }

    }

    @Override
    protected void onResume() {
        initDownload();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        initDownload();
        super.onRestart();
    }
}
