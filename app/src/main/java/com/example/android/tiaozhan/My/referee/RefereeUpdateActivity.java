package com.example.android.tiaozhan.My.referee;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.RefereeUploadreEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.GetFile;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.time.dialog.SelectResereeLVDialog;
import com.example.android.tiaozhan.Toos.time.view.PickValueView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.Call;

public class RefereeUpdateActivity extends BaseActivity implements View.OnClickListener{

    private ImageView fanhui, referee_zp;
    private TextView biaoti, text_dj,biaoti_fu;
    private LinearLayout referee_dj, referee_bh,referee_pz;
    private PickValueView pickString;
    private String qiu,dj,token;
    private RelativeLayout cp_tijiao;
    private EditText text_bh;

    private Bitmap bitmap;
    private String urlpath,ss,tab;//图片路径
    private Uri uritempFile;
    private SPUtils spUtils;
    private String sport_name;
    private String image;
    private String certificate;
    private String reason,grade;
    private String sport_id,id;
    private String data_url;
    private String[] timesString,djString;
    private int mhour, mminute,lv;
    @Override
    public int initContentView() {
        return R.layout.activity_referee_xm_lv;
    }

    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        biaoti_fu=findViewById(R.id.youshangjiao);
        biaoti_fu.setVisibility(View.VISIBLE);
        referee_dj = findViewById(R.id.referee_dj);
        referee_dj.setOnClickListener(this);
        referee_zp = findViewById(R.id.referee_zp_icon);

        referee_bh = findViewById(R.id.referee_bh);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        text_bh = findViewById(R.id.referee_bh_text);
        text_dj = findViewById(R.id.referee_dj_text);
        referee_pz = findViewById(R.id.referee_pz);
        referee_pz.setOnClickListener(this);
        cp_tijiao = findViewById(R.id.cp_tijiao);
        cp_tijiao.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initValue();
        Intent intent = getIntent();
        sport_name = intent.getStringExtra("sport_name");
        data_url = intent.getStringExtra("image");
        certificate = intent.getStringExtra("certificate");
        reason = intent.getStringExtra("reason");
        grade = intent.getStringExtra("grade");
        sport_id = intent.getStringExtra("sport_id");
        id = intent.getStringExtra("id");
        LogU.Ld("1608",sport_id+text_dj.getText().toString()+data_url+"传值"+id);
        if (!EmptyUtils.isStrEmpty(sport_name)) {
            biaoti.setText(sport_name);
            text_dj.setText(grade);

            text_bh.setText(certificate);

            Glide.with(RefereeUpdateActivity.this).load(getResources().getString(R.string.imgurl) + data_url).into(referee_zp);
        }else {
            biaoti.setText("裁判项目等级");
            text_dj.setText("请选择");
            text_bh.setText("请选择");
            // Glide.with(RefereeXmLvActivity.this).load(getResources().getDrawable(R.mipmap.icon_zp).into(referee_zp);
            referee_zp.setImageDrawable(getResources().getDrawable(R.mipmap.icon_zp));
        }
        // biaoti_fu.setText("保存");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;

            case R.id.referee_dj:
                showSelectReserreeLVDialog();
                break;


            case R.id.referee_pz:
                addPZ();
                break;

            case R.id.cp_tijiao:
                String bh = text_bh.getText().toString();
                LogU.Ld("1608",bh+text_dj.getText().toString()+data_url);
                if (!EmptyUtils.isStrEmpty(bh)&&!EmptyUtils.isStrEmpty(text_dj.getText().toString())&&!EmptyUtils.isStrEmpty(data_url)){
                    certificateAdd();

                }

                break;
        }
    }










    private void initValue() {
        timesString = getResources().getStringArray(R.array.referee);
        djString = getResources().getStringArray(R.array.reseree_lv);
    }

    // 等级
    //裁判等级
    private void showSelectReserreeLVDialog() {
        SelectResereeLVDialog mSelectWeekDialog = new SelectResereeLVDialog(this, new SelectResereeLVDialog.OnClickListener() {
            @Override
            public boolean onSure(int item, int setTimeType) {
                lv = item;
                text_dj.setText(djString[item]);
                return false;
            }

            @Override
            public boolean onCancel() {
                return false;
            }
        });
        mSelectWeekDialog.show(lv);
    }



    // 照相
    public void addPZ(){
        ActivityCompat.requestPermissions(RefereeUpdateActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        new ActionSheetDialog(RefereeUpdateActivity.this)
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
        File img = new File(ss);
        try {
            Log.d("1608", bitmap + "----------分1割----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("1608", ss + "----------分割线----------" + img + "=========" + token);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadrefesee")
                .addFile("files","multipart/form-data.png",img)
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
                        RefereeUploadreEntity entity = gson.fromJson(result, RefereeUploadreEntity.class);
                        data_url = entity.getData().getFilesURL();
                        spUtils.put(RefereeUpdateActivity.this, "referee_zp", entity.getData().getFilesURL());
                        Glide.with(RefereeUpdateActivity.this).load(getResources().getString(R.string.imgurl) +entity.getData().getBaseURL()+ data_url).into(referee_zp);

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
    //上传照片
    private void certificateAdd() {
        LogU.Ld("1608", "平台接入" +  text_bh.getText().toString()+data_url+"值"+id+text_dj.getText().toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/updateReferee")
                .addParams("certificate",text_bh.getText().toString())
                .addParams("certificate_img",data_url)
                .addParams("refereeid",id)
                .addParams("grade",text_dj.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                       //     RefereeSelectEntity entity = gson.fromJson(result, RefereeSelectEntity.class);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
