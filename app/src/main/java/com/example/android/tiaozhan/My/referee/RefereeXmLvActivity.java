package com.example.android.tiaozhan.My.referee;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import com.example.android.tiaozhan.Entity.AddrefereeSportEntity;
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
import com.example.android.tiaozhan.Toos.puplv.DoublePicker;
import com.example.android.tiaozhan.Toos.time.dialog.RefereeDialog;
import com.example.android.tiaozhan.Toos.time.view.PickValueView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import okhttp3.Call;

public class RefereeXmLvActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui, referee_zp;
    private TextView biaoti, text_dj, biaoti_fu;
    private LinearLayout referee_dj, referee_bh,referee_pz;
    private PickValueView pickString;
    private String qiu, dj, token;
    private RelativeLayout cp_tijiao;
    private EditText text_bh;

    private Bitmap bitmap;
    private String urlpath, ss, tab;//图片路径
    private Uri uritempFile;
    private SPUtils spUtils;
    private String sport_name;
    private String image;
    private String certificate;
    private String reason;
    private String sport_id;
    private String data_url;
    private String[] timesString, djString;
    private int mhour, mminute,num;
    private String baseURL;
    private Uri uri;
    private Bitmap bitmap1;
    private RefereeDialog mSelectTimeDialog;

    @Override
    public int initContentView() {
        return R.layout.activity_referee_xm_lv;
    }

    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        biaoti_fu = findViewById(R.id.youshangjiao);
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

        biaoti.setText("裁判项目等级");
        initValue();
        // Glide.with(RefereeXmLvActivity.this).load(getResources().getDrawable(R.mipmap.icon_zp).into(referee_zp);
        Glide.with(RefereeXmLvActivity.this).load(getResources().getString(R.string.imgurl) + image).into(referee_zp);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;

            case R.id.referee_dj:
                showReserreeLVDialog();
               // onDoublePicker();
                break;


            case R.id.referee_pz:
                addPZ();
                break;

            case R.id.cp_tijiao:
                String bh = text_bh.getText().toString();
                if (EmptyUtils.isStrEmpty(bh)) {
                    ToastUitl.longs("证书编号不能为空");
                } else if (EmptyUtils.isStrEmpty(text_dj.getText().toString())) {
                    ToastUitl.longs("项目不能为空");
                } else if (EmptyUtils.isStrEmpty(data_url)) {
                    ToastUitl.longs("证书照片不能为空");
                } else {
                    certificateAdd();
                }

                break;
        }
    }


    private void initValue() {
        timesString = getResources().getStringArray(R.array.referee);
        if (timesString[num].equals("高尔夫")){
            djString = getResources().getStringArray(R.array.gef_lv);
        }else {
            djString = getResources().getStringArray(R.array.reseree_lv);
        }


    }

    private void showReserreeLVDialog() {
        mSelectTimeDialog = new RefereeDialog(this, new RefereeDialog.OnClickListener() {
            @Override
            public boolean onSure(int hour, int minute, int setTimeType) {
                num=hour;
                mhour = hour + 1;
                mminute = minute;
                text_dj.setText(timesString[hour] + "     " + djString[minute]);

                Log.d("1608", "----什么球----" + mhour + minute);
                return false;
            }

            @Override
            public boolean onCancel() {


                return false;
            }
        });
        mSelectTimeDialog.setDialogView(timesString[num]);
        mSelectTimeDialog.show(timesString[mhour], timesString[mminute], 1);
    }


    public void onDoublePicker() {
        final ArrayList<String> firstData = new ArrayList<>();
        firstData.add("2017年5月2日星期二");
        firstData.add("2017年5月3日星期三");
        firstData.add("2017年5月4日星期四");
        firstData.add("2017年5月5日星期五");
        firstData.add("2017年5月6日星期六");
        final ArrayList<String> secondData = new ArrayList<>();
        secondData.add("电动自行车");
        secondData.add("二轮摩托车");
        secondData.add("私家小汽车");
        secondData.add("公共交通汽车");
        final DoublePicker picker = new DoublePicker(this, firstData, secondData);
        picker.setDividerVisible(false);
        picker.setShadowColor(Color.DKGRAY, 80);
        picker.setOffset(2);
        picker.setSelectedIndex(2, 1);
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                ToastUitl.longs(firstData.get(selectedFirstIndex) + " " + secondData.get(selectedSecondIndex));
            }
        });
        picker.show();
    }


    // 照相
    public void addPZ() {
        ActivityCompat.requestPermissions(RefereeXmLvActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        new ActionSheetDialog(RefereeXmLvActivity.this)
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
       // ss = GetFile.getFile(bitmap1, 1, 2 + "").toString();
        File img = new File(ss);
        try {
            Log.d("1608", bitmap + "----------分1割----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadrefesee")
                .addFile("files", "multipart/form-data.png", img)
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
                        Boolean a = result.indexOf("上传成功") != -1;
                        Log.d("1608", a + result);

                        Gson gson = new Gson();
                        RefereeUploadreEntity entity = gson.fromJson(result, RefereeUploadreEntity.class);
//                        Glide.with(GRXXActivity.this).load("http://192.168.0.203/tzOne/public/"+entity.getData()).into(touxiang);
                        data_url = entity.getData().getFilesURL();
                        baseURL = entity.getData().getBaseURL();
                        spUtils.put(RefereeXmLvActivity.this, "referee_zp", entity.getData().getFilesURL());
                        Glide.with(RefereeXmLvActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getBaseURL() + data_url).into(referee_zp);

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
                    uri = Uri.fromFile(temp);
                      startPhotoZoom(Uri.fromFile(temp));
                   /* try {
                        bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        touxiangupost(bitmap1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }*/

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
                    Uri data1 = data.getData();
                    /*try {
                        bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data1));
                        touxiangupost(bitmap1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }*/

                     startPhotoZoom(data.getData());
                    break;
                // 如果是调用相机拍照时
                case 11:
                    Log.d("1608", "相机完毕");
                    File temp = new File(Environment.getExternalStorageDirectory()

                            + "/xiaoma.jpg");
                    /*uri = Uri.fromFile(temp);
                    Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp));
                    try {
                        bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        touxiangupost(bitmap1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }*/

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
        LogU.Ld("1608", "平台接入" + text_bh.getText().toString() + data_url + mhour + djString[mminute]);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addcertificate")
                .addHeader("token", token)
                .addParams("certificate", text_bh.getText().toString())
                .addParams("certificate_img", baseURL + data_url)
                .addParams("sportid", mhour + "")
                .addParams("grade", djString[mminute])
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "添加项目" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            AddrefereeSportEntity entity = gson.fromJson(result, AddrefereeSportEntity.class);

                            ToastUitl.longs(entity.getMsg());
                            finish();
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

    /**
     * 选择时间对话框回调
     *
     * @param listener
     */ /**
     * 选择时间对话框回调
     *
     * @param listener
     */
     private OnClickListener clickListener;
    public void setOnUpdateTimeListener(OnClickListener listener) {
        clickListener = listener;
    }


    /**
     * 选择时间对话框回调接口，调用者实现
     *
     * @author huangzj
     */
    public interface OnClickListener {
        boolean onSure(int hour, int minute, int setTimeType);

        boolean onCancel();
    }
}
