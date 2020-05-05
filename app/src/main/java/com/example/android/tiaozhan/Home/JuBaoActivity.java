package com.example.android.tiaozhan.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.GrenAdapter;
import com.example.android.tiaozhan.Adapter.GvAdapter;
import com.example.android.tiaozhan.Adapter.ReportAdapter;
import com.example.android.tiaozhan.Denglu.GRXXActivity;
import com.example.android.tiaozhan.Denglu.GlideLoader;
import com.example.android.tiaozhan.Denglu.ZhuCeActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PersonalprofileEntity;
import com.example.android.tiaozhan.Entity.ReportEntity;
import com.example.android.tiaozhan.Entity.ReportImageEntity;
import com.example.android.tiaozhan.Entity.ZhuceEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.fuyin.constans.P;
import com.example.android.tiaozhan.Toos.fuyin.ui.ImagePreviewActivity;
import com.google.gson.Gson;
import com.lcw.library.imagepicker.ImagePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 举报
 */
public class JuBaoActivity extends BaseActivity implements View.OnClickListener {
    private ReportAdapter adapter2;//照片适配器
    private SPUtils spUtils;
    private Dialog mCameraDialog;
    private GridView gridView;
    private TextView biaoti, text_qued, text_close, text_jubao;
    private ImageView fanhui;
    private GvAdapter adapter;
    private List<String> list;
    private List<String> listJH =new ArrayList<>();
    private RelativeLayout submit_layout, cailiao, guangao, qiza, seqing, erxing, qita;
    private ImageView image_cailiao, image_guangao, image_qiza, image_seqing, image_erxing, image_qita;
    private LinearLayout check_layout;
    private int juBTag = 1;
    private String token, uid;
    private Bundle bundle;
    private String baseURL,toStringName;
    private String filesURL;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ju_bao);
//    }


    @Override
    public int initContentView() {
        return R.layout.activity_ju_bao;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        submit_layout = findViewById(R.id.submit_layout);
        submit_layout.setOnClickListener(this);
        check_layout = findViewById(R.id.check_layout);
        check_layout.setOnClickListener(this);
        text_jubao = findViewById(R.id.text_jubao);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        gridView = findViewById(R.id.grid_view);
        list = new ArrayList<>();
        //   adapter = new GvAdapter(this, list);
        adapter2 = new ReportAdapter(this, list);
        gridView.setAdapter(adapter2);

    }

    @Override
    protected void initData() {
        biaoti.setText("举报");
     //   gridView.setAdapter(adapter);
        bundle = this.getIntent().getExtras();

        uid = bundle.getString("uid");
        dianjiGrid();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.check_layout:
                setDialog(R.layout.check_jubao);
                break;
            case R.id.cailiao:
                juBTag = 1;
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;
            case R.id.guangao:
                juBTag = 2;
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;
            case R.id.qiza:
                juBTag = 3;
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;
            case R.id.seqing:
                juBTag = 4;
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;
            case R.id.erxing:
                juBTag = 5;
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;
            case R.id.qita:
                juBTag = 6;
                image_qita.setImageDrawable(getResources().getDrawable(R.mipmap.duihaohong));
                image_cailiao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_guangao.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_qiza.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_seqing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));
                image_erxing.setImageDrawable(getResources().getDrawable(R.mipmap.duihaobai));

                break;

            case R.id.text_close:
                mCameraDialog.cancel();
                break;

            case R.id.text_qued:

                if (juBTag == 1) {
                    text_jubao.setText("本人与资料不符");
                } else if (juBTag == 2) {
                    text_jubao.setText("散布垃圾广告");
                } else if (juBTag == 3) {
                    text_jubao.setText("欺诈");
                } else if (juBTag == 4) {
                    text_jubao.setText("散布色情");
                } else if (juBTag == 5) {
                    text_jubao.setText("恶性骚扰");
                } else if (juBTag == 6) {
                    text_jubao.setText("其他");
                }
                mCameraDialog.cancel();
                break;
            case R.id.submit_layout:



                    initDownload();


                break;


        }
    }

    private void setDialog(int layout) {


        mCameraDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
//        mCameraDialog.setCanceledOnTouchOutside(false);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                layout, null);


        cailiao = root.findViewById(R.id.cailiao);
        cailiao.setOnClickListener(this);

        guangao = root.findViewById(R.id.guangao);
        guangao.setOnClickListener(this);

        qiza = root.findViewById(R.id.qiza);
        qiza.setOnClickListener(this);

        seqing = root.findViewById(R.id.seqing);
        seqing.setOnClickListener(this);

        erxing = root.findViewById(R.id.erxing);
        erxing.setOnClickListener(this);

        qita = root.findViewById(R.id.qita);
        qita.setOnClickListener(this);
        text_qued = root.findViewById(R.id.text_qued);
        text_qued.setOnClickListener(this);
        text_close = root.findViewById(R.id.text_close);
        text_close.setOnClickListener(this);

        image_cailiao = root.findViewById(R.id.image_cailiao);
        image_guangao = root.findViewById(R.id.image_guangao);
        image_qiza = root.findViewById(R.id.image_qiza);
        image_seqing = root.findViewById(R.id.image_seqing);
        image_erxing = root.findViewById(R.id.image_erxing);
        image_qita = root.findViewById(R.id.image_qita);


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


    private void initDownload() {
       StringBuilder sbName = new StringBuilder();
        if (listJH.size() > 0) {
            for (int i = 0; i < listJH.size(); i++) {
                if (i < listJH.size() - 1) {
                    sbName.append(listJH.get(i));
                    sbName.append(",");
                } else {
                    sbName.append(listJH.get(i));
                }
            }
        } else {

            toStringName = null;

        }
        toStringName = sbName.toString();

        LogU.Ld("1608","返回图片集合"+toStringName);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getReport")
                .addHeader("token", token)
                .addParams("Be_report_uuid", uid)
                .addParams("Report_type", juBTag + "")
                .addParams("img", toStringName)
                .addParams("comment", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "举报信息" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "举报信息" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Boolean b = result.indexOf("4004") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            ReportEntity entity = gson.fromJson(result, ReportEntity.class);
                            Toast.makeText(JuBaoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            list.clear();
                            finish();
                        } else {
                            Gson gson = new Gson();
                            LogU.Ld("1608", "UUID转换失败");
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JuBaoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();


                        }

                    }
                });

    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1001 && resultCode == RESULT_OK) {
            List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            list.addAll(imagePaths);
            adapter2.notifyDataSetChanged();

                genRen();

            LogU.Ld("1608", "图片路径" + imagePaths + "===" + imagePaths);

        }
    }

    public void dianjiGrid() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == list.size()) {
                    ActivityCompat.requestPermissions(JuBaoActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    new ActionSheetDialog(JuBaoActivity.this)
                            .builder()
                            .setCancelable(true)
                            .setCanceledOnTouchOutside(true)
                            .addSheetItem("相册",
                                    ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {

                                        @Override
                                        public void onClick(int which) {

                                            ImagePicker.getInstance()
                                                    .setTitle("标题")//设置标题
                                                    .showCamera(true)//设置是否显示拍照按钮
                                                    .showImage(true)//设置是否展示图片
                                                    .showVideo(false)//设置是否展示视频
                                                    //    .filterGif(false)//设置是否过滤gif图片
                                                    .setSingleType(true)//设置图片视频不能同时选择
                                                    .setMaxCount(9 - list.size())//设置最大选择图片数目(默认为1，单选)
                                                    // .setImagePaths((ArrayList<String>) list2)//保存上一次选择图片的状态，如果不需要可以忽略
                                                    .setImageLoader(new GlideLoader())//设置自定义图片加载器
                                                    .start(JuBaoActivity.this, 1001);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

                                        }
                                    })
                            .addSheetItem("拍照",
                                    ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {

                                        @Override
                                        public void onClick(int which) {


                                            //  getPermissions();
                                        }
                                    }).show();

                } else {
                    Intent intent = new Intent(JuBaoActivity.this, ImagePreviewActivity.class);
                    intent.putStringArrayListExtra("imageList", (ArrayList<String>) list);
                    intent.putExtra(P.START_ITEM_POSITION, 0);
                    intent.putExtra(P.START_IAMGE_POSITION, position);
                    startActivity(intent);     //启动Activity
                }
            }
        });

        adapter2.setOnClickListener(new ReportAdapter.OnClickListener() {
            @Override
            public void onChildClickListen(int position) {
                if (list != null && list.size() > 0) {
                    LogU.Ld("1608", "删除照片" + list.get(position));
                    //genRenDel(list.get(position));
                    list.remove(position);
                    adapter2.notifyDataSetChanged();
                }

            }
        });

    }

    //上传照片个人
    public void genRen() {
        final File[] files = {null};
        StringBuilder sb = new StringBuilder();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
           /* if(i<pathList.size()-1){
                sb.append(pathList.get(i));
                sb.append(",");
            }else{
                sb.append(pathList.get(i));
            }*/
                //  files = new File(list2.get(i));
                LogU.Ld("1608", "图片解析" + list.size() + "=====" + list.get(i));
                //  files = CompressHelper.getDefault(getApplicationContext()).compressToFile(new File(list2.get(i)));

                Luban.with(this)
                        .load(list.get(i))
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
                                        .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userReportImgs")
                                        .addFile("files[]", "multipart/form-data.png", file)
                                        .addHeader("token", token)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.d("1608", "举报简介上传失败" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                String result = response.toString();
                                                Log.d("1608", "举报简介上传" + result);
                                                Boolean a = result.indexOf("2000") != -1;

                                                if (a) {
                                                    Gson gson = new Gson();
                                                    ReportImageEntity entity = gson.fromJson(result, ReportImageEntity.class);
                                                    ReportImageEntity.DataBean data = entity.getData();
                                                    LogU.Ld("1608","举报简介上传路径"+data);
                                                    baseURL = data.getBaseURL();
                                                    filesURL = data.getFilesURL();
                                                    listJH.add(baseURL+filesURL);
                                                } else {
                                                    Gson gson = new Gson();
                                                    ReportImageEntity entity = gson.fromJson(result, ReportImageEntity.class);
                                                    Toast.makeText(JuBaoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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

            }

        }


    }
}
