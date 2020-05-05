package com.example.android.tiaozhan.My.referee;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Denglu.TiaoKuanActivity;
import com.example.android.tiaozhan.Entity.AddrefereeEntity;
import com.example.android.tiaozhan.Entity.SFZEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.AddressPickTask;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.GetFile;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import okhttp3.Call;

public class MyCwRefereeActivity extends BaseActivity implements View.OnClickListener {
    private TextView biaoti, slt1, slt2, tiaokuan, czd;
    private ImageView fanhui, zhengmian, fanmian, yuedu;
    private RelativeLayout tijiao;
    private String token, uid, nameString, scjiageString, ZMurl, FMurl, name_zs, num_sfz, user_name, ID_number;
    private SPUtils spUtils;
    private String urlpath, ss;//图片路径
    private boolean tab = true;
    private Uri uritempFile;
    private Bitmap bitmap;
    private int tag;
    private EditText name, sfz;
    private Dialog mCameraDialog;
    private LinearLayout czdz;
    private String provinceAreaName;
    private String cityAreaName;
    private String countyAreaName;

    @Override
    public int initContentView() {
        return R.layout.activity_my_cw_referee;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        biaoti.setText("成为裁判");
        fanhui.setOnClickListener(this);
        tijiao = findViewById(R.id.PromoterONE_tijiao);
        tijiao.setOnClickListener(this);
        zhengmian = findViewById(R.id.promo_one_image1);
        zhengmian.setOnClickListener(this);
        fanmian = findViewById(R.id.promo_one_image2);
        fanmian.setOnClickListener(this);
        name = findViewById(R.id.PromoterONE_name);
        sfz = findViewById(R.id.PromoterONE_sfz);
        czd = findViewById(R.id.promoter_czd);
        slt1 = findViewById(R.id.promo_one_slt1);
        slt1.setOnClickListener(this);
        slt2 = findViewById(R.id.promo_one_slt2);
        slt2.setOnClickListener(this);

        yuedu = findViewById(R.id.promo_one_yuedu);
        yuedu.setOnClickListener(this);
        tiaokuan = findViewById(R.id.promo_one_tiaokuan);
        tiaokuan.setOnClickListener(this);
        czdz = findViewById(R.id.czdz);
        czdz.setOnClickListener(this);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        String number = intent.getStringExtra("number");
        String playerRealName = intent.getStringExtra("playerRealName");
        String playerID = intent.getStringExtra("playerID");
        if (!EmptyUtils.isStrEmpty(number)) {


            String realname1 =null;
            char[] s = playerID.toCharArray();

            char[] chars = playerRealName.toCharArray();
            //  String name = cardName.replaceFirst(cardName.substring(1, chars.length - 4), "****");

            if(chars.length ==1){
                realname1 =  playerRealName;
            }
            if(chars.length == 2){
                realname1 =  playerRealName.replaceFirst(playerRealName.substring(1),"*");
            }
            if (chars.length > 2) {
                realname1 =  playerRealName.replaceFirst(playerRealName.substring(1,chars.length-1) ,"*");
            }

            String sfzID = playerID.replaceFirst(playerID.substring(3, s.length - 4), "****");

                name.setText(realname1);
                sfz.setText(sfzID);

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.promo_one_slt1:
                setDialog("1");
                break;
            case R.id.promo_one_slt2:
                setDialog("2");
                break;
            case R.id.promo_one_yuedu:
                if (tab) {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.hongdian));
                } else {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.huidian));
                }
                tab = !tab;
                break;
            case R.id.promo_one_tiaokuan:
                intent.setClass(this, TiaoKuanActivity.class);
                startActivity(intent);
                break;
            case R.id.PromoterONE_tijiao:

                if (!tab) {
                    if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(sfz.getText())) {
                        Toast.makeText(this, "请把内容填写完整", Toast.LENGTH_SHORT).show();
                    } else {
                        intent.setClass(MyCwRefereeActivity.this, RefereePerfectXXActivity.class);
                        startActivity(intent);
                        initDownload();
                    }


                } else {
                    Toast.makeText(this, "请阅读条款", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.promo_one_image1:
                tag = 1;
                ActivityCompat.requestPermissions(MyCwRefereeActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                new ActionSheetDialog(MyCwRefereeActivity.this)
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
            case R.id.promo_one_image2:
                tag = 2;
                ActivityCompat.requestPermissions(MyCwRefereeActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                new ActionSheetDialog(MyCwRefereeActivity.this)
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


            case R.id.czdz://地址
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(MyCwRefereeActivity.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
//                            showToast(province.getAreaName() + city.getAreaName()+"2");
                            czd.setText(province.getAreaName() + city.getAreaName());
                            //    changzhuString = province.getAreaName() +","+ city.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName());
                        } else {
//                            showToast(province.getAreaName() + city.getAreaName() + county.getAreaName()+"1");
                            czd.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                            //  changzhuString = province.getAreaName() +","+ city.getAreaName() + ","+county.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                        provinceAreaName = province.getAreaName();
                        cityAreaName = city.getAreaName();
                        countyAreaName = county.getAreaName();
                    }
                });
                task.execute("北京", "北京", "通州");
                break;
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

    //上传照片
    public void touxiangupost() {

        ss = GetFile.getFile(bitmap, 1, 2 + "").toString();
        if (EmptyUtils.isEmpty(ss)) {
            return;
        } else {
            File img = new File(ss);
            try {
                Log.d("1608", bitmap + "----------分1割----------");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/positiveidcard")
                    .addFile("files", "multipart/form-data.png", img)
//                .addHeader("token", token)
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
                            SFZEntity entity = gson.fromJson(result, SFZEntity.class);

                            if (tag == 1) {
                                Glide.with(MyCwRefereeActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getBaseURL() + entity.getData().getFilesURL()).into(zhengmian);
                                ZMurl = entity.getData().getBaseURL() + entity.getData().getFilesURL();
                            } else if (tag == 2) {
                                Glide.with(MyCwRefereeActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getBaseURL() + entity.getData().getFilesURL()).into(fanmian);
                                FMurl = entity.getData().getBaseURL() + entity.getData().getFilesURL();
                            }
//                        spUtils.put(GRXXActivity.this,"touxiang",entity.getData());

                        }
                    });
        }
    }

    private void initDownload() {
        user_name = (String) spUtils.get(this, "logintoken_user_name", name.getText().toString());
        ID_number = (String) spUtils.get(this, "logintoken_ID_number", sfz.getText().toString());
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "推广员身份验证" + token + "CardName" + name.getText().toString() + "CardNumber" + sfz.getText().toString() + "PositiveUrl" + ZMurl + "BackUrl" + FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addreferee")
                .addHeader("token", token)
                .addParams("user_name", name.getText().toString())
                .addParams("ID_number", sfz.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "推广员身份验证" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Intent intent = new Intent();
                        if (a) {
                            Gson gson = new Gson();
                            AddrefereeEntity entity = gson.fromJson(result, AddrefereeEntity.class);
                            int status = entity.getData().getStatus();

                                ToastUitl.longs(entity.getMsg());
                                intent.setClass(MyCwRefereeActivity.this, RefereePerfectXXActivity.class);
                                startActivity(intent);
                                finish();
                        } else {
                          /*  Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            if (entity.getMsg().equals("登录超时")) {

                                intent.setClass(MyCwRefereeActivity.this, DengluActivity.class);
                                startActivity(intent);
                            }*/
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

//    @Override
//    protected void onResume() {
//        initDownload();
//        super.onResume();
//    }
//
//    @Override
//    protected void onRestart() {
//        initDownload();
//        super.onRestart();
//    }

    //性别选择
    private void setDialog(String tab) {

        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        LinearLayout root;
        if (tab.equals("1")) {
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.sfz_zhengmian, null);
        } else {
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.sfz_fanmian, null);
        }


        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
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
}
