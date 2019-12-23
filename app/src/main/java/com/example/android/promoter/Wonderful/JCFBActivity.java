package com.example.android.promoter.Wonderful;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

import com.example.android.promoter.Adapter.GvAdapter;
import com.example.android.promoter.Adapter.JCFBXiangmuAdapter;
import com.example.android.promoter.Adapter.XiugaiAihaoAdapter;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.Entity.ZhaopianPostEntity;
import com.example.android.promoter.Home.CGGZActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.ActionSheetDialog;
import com.example.android.promoter.Toos.FileUtilcll;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scrat.app.selectorlibrary.ImageSelector;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

/**
 * 发布精彩瞬间
 */
public class JCFBActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private TextView biaoti, youshangjiao,fanweiText,xiangmuText;
    private ImageView fanhui, xiangji,shanchu;
    private String token, path, ss, urlpath,fanweiString = "1",xiangmuString = "全部",xiangmuID = "all",oneImages;
    private SPUtils spUtils;
    private List<String> list;
    private List<String> list2;
    private File img;
    private String shipin, baseURL, imgURL1="",imgURL2="" ;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private GridView gridView,gridView2;

    private GvAdapter adapter;
    private Uri uritempFile;
    private Bitmap bitmap;
    private EditText editText;
    private int themeId;
    private ProgressDialog progressDialog;

    private LinearLayout fanwei,xiangmu;
    private Dialog mCameraDialog;
    private RelativeLayout gongkai,jinhaoyou;
    private JCFBXiangmuAdapter adapter2;
    private RelativeLayout shipinlayout;
    private   List<YundongEntity.DataBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcfb);
        biaoti = findViewById(R.id.biaoti);
        jcVideoPlayerStandard = findViewById(R.id.shipinshangchuan_video);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setOnClickListener(this);
        youshangjiao.setVisibility(View.VISIBLE);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
//        xiangji = findViewById(R.id.jcfb_xiangji);
//        xiangji.setOnClickListener(this);
        gridView = findViewById(R.id.jcfb_grid);
        editText = findViewById(R.id.jcfb_edit);
        xiangmu = findViewById(R.id.jcfb_xiangmu);
        xiangmu.setOnClickListener(this);
        fanwei = findViewById(R.id.jcfb_fanwei);
        fanwei.setOnClickListener(this);
        fanweiText = findViewById(R.id.jcfb_text_fanwei);
        xiangmuText = findViewById(R.id.home_faqi_moshi_text);
        shanchu = findViewById(R.id.jcfb_shanchu);
        shanchu.setOnClickListener(this);
        shipinlayout = findViewById(R.id.shipin_layout);

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        adapter = new GvAdapter(this, list);
        gridView.setAdapter(adapter);

        data = new ArrayList<>();

        dianjiGrid();

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");


        biaoti.setText("发布动态");
        youshangjiao.setText("发布");
    }

//    @Override
//    public int initContentView() {
//        return R.layout.activity_jcfb;
//
//
//    }
//
//    @Override
//    protected void initUIAndListener() {
//        biaoti = findViewById(R.id.biaoti);
//        jcVideoPlayerStandard =  findViewById(R.id.shipinshangchuan_video);
//        youshangjiao = findViewById(R.id.youshangjiao);
//        youshangjiao.setOnClickListener(this);
//        youshangjiao.setVisibility(View.VISIBLE);
//        fanhui = findViewById(R.id.fanhui);
//        fanhui.setOnClickListener(this);
//        xiangji = findViewById(R.id.jcfb_xiangji);
//        xiangji.setOnClickListener(this);
//        spUtils = new SPUtils();
//        token = (String) spUtils.get(this, "logintoken", "无");
//        list = new ArrayList<>();
//    }
//
//    @Override
//    protected void initData() {
//        biaoti.setText("发布动态");
//        youshangjiao.setText("发布");
//    }

    public void dianjiGrid() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    if (position == 30) {//不能点击了
                    } else {

                        new ActionSheetDialog(JCFBActivity.this)
                                .builder()
                                .setCancelable(true)
                                .setCanceledOnTouchOutside(true)
                                .addSheetItem("相册",
                                        ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {

                                            @Override
                                            public void onClick(int which) {

//                                                Intent intent = new Intent(Intent.ACTION_PICK, null);
////                                                intent.setDataAndType(
////                                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
////                                                        "video/*;image/*");
//                                                intent.setType("*/*");
//                                                ArrayList<String> mimes = new ArrayList<>();
//                                                mimes.add("image/*");
//                                                mimes.add("video/*");
//                                                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimes);
//                                                //４．３以上的设备才支持Intent.EXTRA_ALLOW_MULTIPLE，是否可以一次选择多个文件
//                                                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
//                                                //返回的文件是否必须存在于设备上，而不是需要从远程服务下载的,用于解决用户选中的是云端文件时的问题
//                                                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, false);
//                                                startActivityForResult(intent, 10);

                                                ImageSelector.show(JCFBActivity.this, 3, 3);
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
                } else {//可以加点预览功能。

                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.jcfb_shanchu:
                shipin = "";
                jcVideoPlayerStandard.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                shipinlayout.setVisibility(View.GONE);
                break;
            case R.id.jcfb_xiangmu://项目选择
                setDialog(R.layout.jcfb_xiangmu, "2");

                break;
            case R.id.jcfb_fanwei://可视范围选择
                setDialog(R.layout.jcfb_fanwei, "3");

                break;


            case R.id.jcfb_gongkai://可视范围选择  公开
                mCameraDialog.dismiss();
                fanweiText.setText("公开");
                fanweiString = "1";
                break;
            case R.id.jcfb_haoyou://可视范围选择  仅好友可看
                mCameraDialog.dismiss();
                fanweiText.setText("仅好友可见");
                fanweiString = "0";
                break;
            case R.id.youshangjiao:

//                if (list.get(0).indexOf(".mp4") != -1)
//                {
//                    init();
//                }else {
//                    initDownload();
//                }
//


                if (TextUtils.isEmpty(editText.getText())){
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                }else {

                    if (shipin == null){
                        if (list.size() == 0){
                            initwenzi();
                        }else{
                            initDownload();
                        }

                    }else{

                        init();
                    }
                }


                break;

//            case R.id.jcfb_xiangji:
////                intent.setClass(JCFBActivity.this, PaisheActivity.class);
////                startActivityForResult(intent, 1);
//
//                getPermissions();
//
//                break;
        }

    }
    private void setDialog(int layout, String tab) {


        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
//        mCameraDialog.setCanceledOnTouchOutside(false);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                layout, null);
        if (tab.equals("3")) {
            gongkai = root.findViewById(R.id.jcfb_gongkai);
            gongkai.setOnClickListener(this);
            jinhaoyou = root.findViewById(R.id.jcfb_haoyou);
            jinhaoyou.setOnClickListener(this);

        }else  if (tab.equals("2")) {
            gridView2 = root.findViewById(R.id.jcfb_xiangmu_grid);
            huoquyundong();

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
    //获取所有运动
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
                            adapter2 = new JCFBXiangmuAdapter(JCFBActivity.this, data);
                            gridView2.setAdapter(adapter2);
                            //爱好的运动点击监听器

                            gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    xiangmuText.setText(data.get(position).getName());
                                    xiangmuString = data.get(position).getName();
                                    xiangmuID = data.get(position).getId()+"";
                                    mCameraDialog.dismiss();


                                }
                            });


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

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
                startActivityForResult(new Intent(JCFBActivity.this, PaisheActivity.class), 100);
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(JCFBActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(JCFBActivity.this, PaisheActivity.class), 100);
        }

    }

    private void initDownload() {

//        ss = GetFile.getFile(BitmapFactory.decodeFile(path), 1, 2 + "").toString();
//        File img = new File(ss);

//        for (String s : list) {
//            img = new File(s);
//            LogU.Ld("1608", "发布精彩" + img);
//        }

        PostFormBuilder builder = OkHttpUtils.post();
        builder.url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadWonderImgs")
                .addHeader("token", token);
        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i));
            if (!file.exists()) {
                LogU.Ld("1608", "文件不存在，请修改文件路径");
                return;
            }
            String filename = file.getName();
            LogU.Ld("1608", "发布图片"+filename+"---"+file);
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
                        LogU.Ld("1608", "发布精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ZhaopianPostEntity entity = gson.fromJson(result, ZhaopianPostEntity.class);
                            imgURL1= entity.getData().getFilesURL();

                            baseURL = entity.getData().getBaseURL();
                            initZong();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }
                    }
                });


//        OkHttpUtils
//                .post()
//                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadWonderImgs")
//                .addHeader("token", token)
//                .addFile("files[]", img + "", img)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        String result = response.toString();
//                        LogU.Ld("1608", "发布精彩" + result);
//                        Boolean a = result.indexOf("2000") != -1;
//                        if (a) {
//                            Gson gson = new Gson();
//                            ZhaopianPostEntity entity = gson.fromJson(result, ZhaopianPostEntity.class);
//                            imgURL = entity.getData().getFilesURL();
//                            baseURL = entity.getData().getBaseURL();
//                            initZong();
//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
////                        if (entity.getMsg().equals("登录超时")){
////                            Intent intent = new Intent();
////                            intent.setClass(getContext(),DengluActivity.class);
////                            startActivity(intent);
////                        }
//                        }
//                    }
//                });

    }

    public void init() {
        LogU.Ld("1608", "发布精彩视频" + shipin);
//        ss = GetFile.getFile(BitmapFactory.decodeFile(path), 1, 2 + "").toString();
//        File img = new File(ss);

//        for (String s : list){
//            img = new File(s);
//        }

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadWonderImgs")
                .addHeader("token", token)
                .addFile("files[]", shipin + "", new File(shipin))

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发布精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ZhaopianPostEntity entity = gson.fromJson(result, ZhaopianPostEntity.class);
                            imgURL1 = entity.getData().getFilesURL();
                            baseURL = entity.getData().getBaseURL();
                            diyizhen();
//                            initDownload();
//                            initZong();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }

                    }
                });
    }
    public void diyizhen() {
        File file = new File(oneImages);
        LogU.Ld("1608","第一帧"+file+"----"+file.getName());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadVideoOneImg")
                .addHeader("token", token)
                .addFile("files", file.getName() + "", file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发布精彩第一帧" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ZhaopianPostEntity entity = gson.fromJson(result, ZhaopianPostEntity.class);
                            imgURL2 = entity.getData().getFilesURL();
                            baseURL = entity.getData().getBaseURL();
                            initZong();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }

                    }
                });
    }
    public void initZong() {
        LogU.Ld("1608", "发布精彩总体" + baseURL+imgURL1+"图片"+imgURL1);
//        ss = GetFile.getFile(BitmapFactory.decodeFile(path), 1, 2 + "").toString();
//        File img = new File(ss);

//        for (String s : list){
//            img = new File(s);
//        }
        showDialog();
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addWonderful")
                .addHeader("token", token)
                .addParams("imgURL", imgURL1)
                .addParams("Comment", editText.getText().toString()+"")
                .addParams("baseURL", baseURL)
                .addParams("sportId", xiangmuID)
                .addParams("sportName", xiangmuString)
                .addParams("isPublic", fanweiString)
                .addParams("oneImgs",imgURL2)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发布精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            progressDialog.cancel();
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }

                    }
                });
    }

    public void initwenzi() {
        LogU.Ld("1608", "发布精彩总体" + baseURL+imgURL1+"图片"+imgURL1);
//        ss = GetFile.getFile(BitmapFactory.decodeFile(path), 1, 2 + "").toString();
//        File img = new File(ss);

//        for (String s : list){
//            img = new File(s);
//        }
        showDialog();
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addWonderful")
                .addHeader("token", token)
                .addParams("imgURL", "")
                .addParams("Comment", editText.getText().toString()+"")
                .addParams("baseURL", "")
                .addParams("sportId", xiangmuID)
                .addParams("sportName", xiangmuString)
                .addParams("isPublic", fanweiString)
                .addParams("oneImgs","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发布精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            progressDialog.cancel();
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JCFBActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {

            path = data.getStringExtra("path");
            //图片路径
//            urlpath = FileUtilcll.saveFile(JCFBActivity.this, "temphead.jpg", BitmapFactory.decodeFile(path));
            list.add(String.valueOf(path));
            adapter.notifyDataSetChanged();
            Log.i("1608", "picture" + path + "---" + urlpath);
//            xiangji.setImageBitmap(BitmapFactory.decodeFile(path));

        }
        if (resultCode == 102) {

            shipin = data.getStringExtra("shipin");
            oneImages = data.getStringExtra("oneImgs");
//            list.add( data.getStringExtra("oneImgs"));
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();//实例化MediaMetadataRetriever对象
//
            //根据文件路径获取缩略图
            mmr.setDataSource(shipin);
            //获得第一帧图片
            Bitmap bitmap = mmr.getFrameAtTime();

            Uri uri1 = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, null,null));
            Log.i("1608", "video" + shipin+"---"+oneImages);
            ImageLoader imageLoader = null;
            shipinlayout.setVisibility(View.VISIBLE);
            jcVideoPlayerStandard.setVisibility(View.VISIBLE);
            jcVideoPlayerStandard.setUp(shipin
                    , jcVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
            imageLoader.getInstance().displayImage(uri1.toString(),
                    jcVideoPlayerStandard.thumbImageView);

//            oneImages = uri1.toString();
            gridView.setVisibility(View.GONE);
        }
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
            list2 = ImageSelector.getImagePaths(data);
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
//            LogU.Ld("1608",list2.size()+"啊啊啊"+list2.get(0)+list2.get(1));
            for (int i = 0; i < list2.size(); i++) {
//                getimage(list2.get(i));
                urlpath = FileUtilcll.saveFile(JCFBActivity.this, i+"temphead.jpg", getimage(list2.get(i)));
                list.add(urlpath);
                LogU.Ld("1608",urlpath+"压缩后"+ getimage(list.get(i)));
            }


            GvAdapter adapter = new GvAdapter(JCFBActivity.this, list);
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
            urlpath = FileUtilcll.saveFile(JCFBActivity.this, "temphead.jpg", bitmap);
            System.out.println("----------路径----------" + urlpath);


            if (list.size() >= 6) {
                Toast.makeText(JCFBActivity.this, "最多选择六张图片", Toast.LENGTH_LONG).show();
            } else {
                Log.d("1608", urlpath + "啥" + bitmap + "啥" + picdata);
                list.add(String.valueOf(urlpath));
            }
            adapter.notifyDataSetChanged();
//            Itouxiang.setImageBitmap(bitmap);
        }
    }



    /**

     * 图片按比例大小压缩方法

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
    //进度条
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        progressDialog.setMessage("加载中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
