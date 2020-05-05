package com.example.android.tiaozhan.My;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.tiaozhan.Adapter.GvAdapter;
import com.example.android.tiaozhan.Adapter.LinkLabelAdapter;
import com.example.android.tiaozhan.Adapter.PingjiaListAdapter;
import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PingjiaListEntity;
import com.example.android.tiaozhan.Entity.ZhaopianPostEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.ActionSheetDialog;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.FileUtil;
import com.example.android.tiaozhan.Toos.FileUtilcll;
import com.example.android.tiaozhan.Toos.GetFile;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.StarBar;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 活动评价
 */
public class PingjiaActivity extends BaseActivity implements View.OnClickListener {
    private TextView biaoti, yjhp, zishu;
    private final int maxNum = 200;
    private ImageView fanhui;
    private ListView listView;
    private PingjiaListAdapter adapter;
    private String token, uuid, siteUid, syUID, tag, pingjiaTAG, ss;
    private SPUtils spUtils;
    private List<PingjiaListEntity.DataBean.UsersInfoBean> data;

    //    private List<PingjiaListEntity.DataBean.LabelBean> data2;
    private List<BiaoqianEntity.DataBean> data2;
    private RelativeLayout tijiao;
    private StarBar sheshi, fuwu, jiage;
    private EditText editText;
    private List<String> listuuid;
    private List<String> listscore;
    private List<String> listlabel;
    private JSONArray array1;
    private SPUtileFQTZ spUtileFQTZ;
    private TextView fenshu1, fenshu2, fenshu3;
    DecimalFormat df = new DecimalFormat("0.0");


    private GridView gridView;
    private List<String> list2;//照片集合
    private GvAdapter adapter2;//照片适配器
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private String path, urlpath, uid, baseURL, imgURL;
    private List<String> list = new ArrayList<String>();
    private Uri uritempFile;
    private Bitmap bitmap;
    private PingjiaListEntity entity;
    private String filePath;
    private Bitmap bitmap2;
    private RecyclerView recyclerView;
    private LinkLabelAdapter linkLabelAdapter;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pingjia);
//    }


    @Override
    public int initContentView() {
        return R.layout.activity_pingjia;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = findViewById(R.id.pingjia_list);
        yjhp = findViewById(R.id.pingjia_yjhp);
        yjhp.setOnClickListener(this);
        tijiao = findViewById(R.id.pingjia_tijiao);
        tijiao.setOnClickListener(this);
        sheshi = findViewById(R.id.pingjia_sheshi);
        sheshi.setStarMark(5, 2);
        sheshi.setIntegerMark(true);
        fuwu = findViewById(R.id.pingjia_fuwu);
        fuwu.setStarMark(5, 2);
        fuwu.setIntegerMark(true);
        jiage = findViewById(R.id.pingjia_jiage);
        jiage.setStarMark(5, 2);
        jiage.setIntegerMark(true);
        fenshu1 = findViewById(R.id.pingjia_fenshu1);
        fenshu2 = findViewById(R.id.pingjia_fenshu2);
        fenshu3 = findViewById(R.id.pingjia_fenshu3);
        gridView = findViewById(R.id.cggz_grid);

        editText = findViewById(R.id.pingjia_edit);
        zishu = findViewById(R.id.yjfk_text);
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        adapter = new PingjiaListAdapter(this, data, uuid);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid = bundle.getString("uuid");
        pingjiaTAG = bundle.getString("tag");
        array1 = new JSONArray();
        listuuid = new ArrayList<>();
        listscore = new ArrayList<>();
        listlabel = new ArrayList<>();

        spUtileFQTZ = new SPUtileFQTZ();


        recyclerView = findViewById(R.id.pingjia_list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //照片
        list2 = new ArrayList<>();
        adapter2 = new GvAdapter(this, list2);
        gridView.setAdapter(adapter2);

    }

    @Override
    protected void initData() {
        biaoti.setText("发表评价");
        initDownload();
        dianjiGrid();
        adapter.setOnItemDeleteClickListener(new PingjiaListAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int position) {
//                adapter.notifyDataSetChanged();


            }
        });

        sheshi.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                if (mark == 5) {
                    fenshu1.setText("超赞");
                } else if (mark == 4) {
                    fenshu1.setText("满意");
                } else if (mark <= 3) {
                    fenshu1.setText("一般");
                }
            }
        });
        fuwu.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                if (mark == 5) {
                    fenshu2.setText("超赞");
                } else if (mark == 4) {
                    fenshu2.setText("满意");
                } else if (mark <= 3) {
                    fenshu2.setText("一般");
                }
            }
        });
        jiage.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {

                if (mark == 5) {
                    fenshu3.setText("超赞");
                } else if (mark == 4) {
                    fenshu3.setText("满意");
                } else if (mark <= 3) {
                    fenshu3.setText("一般");
                }

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (s.length() + (after-count) >=maxNum) {
//
//                    ToastUitl.longs("不能超过" + maxNum + "字！");
//                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                zishu.setText("剩余字数：" + (maxNum - s.length()));
            }
        });
    }


    //获取列表
    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "获取点评列表---" + token + "---uuid---" + uuid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNeedCommentUsersLst")
                .addHeader("token", token)
                .addParams("uuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取点评列表" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, PingjiaListEntity.class);
                            data.clear();
//                            data2.clear();


                            List<PingjiaListEntity.DataBean.UsersInfoBean> list;
                            list = entity.getData().getUsersInfo();
                            List<PingjiaListEntity.DataBean.LabelBean> list2;
                            list2 = entity.getData().getLabel();
//                            data2.addAll(list2);
                            data.addAll(list);
                            listView.setAdapter(adapter);
                            adapter.getUUid(uuid);
                            adapter.getSport(entity.getData().getSportMode());
                           /* final PingjiaList2Adapter mpj=new PingjiaList2Adapter(R.layout.pingjia_list,data,data2,uuid);
                            recyclerView.setAdapter(mpj);
                            mpj.getUUid(uuid);*/


                           adapter.setJsCallBack(new PingjiaListAdapter.JsCallBack() {
                               @Override
                               public void callBack(int spId) {
                                 //  huoqubiaoqian(spId+"");
                               }
                           });
                         //  adapter.notifyDataSetChanged();

                            siteUid = entity.getData().getSiteId();
                            syUID = data.get(0).getPlayerUUID() + "|";
                            for (int i = 1; i < data.size(); i++) {
                                syUID = syUID + data.get(i).getPlayerUUID() + "|";


                            }
                            LogU.Ld("1608", "八八八八八八八" + syUID);

                            //huoqubiaoqian();


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PingjiaActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //一键好评
    private void yijianhaoping() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "一键好评---" + token + "---syUID---" + syUID+pingjiaTAG);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addAllGoodsComment")
                .addHeader("token", token)
                .addParams("publicUuid", uuid)
                .addParams("siteUid", siteUid)
                .addParams("uuid", syUID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "一键好评" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            finish();



                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //    //获取标签
    private void huoqubiaoqian(String num) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "获取标签---" + token + "---uuid---" + syUID);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getLabelInfo")
                .addParams("level", num)
                .addParams("uuid", uuid)
                .addParams("userid", entity.getData().getUsersInfo().get(0).getPlayerUUID())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取标签" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            BiaoqianEntity entity = gson.fromJson(result, BiaoqianEntity.class);
                            List<BiaoqianEntity.DataBean> list;
                            list = entity.getData();
                            data2.clear();
                            data2.addAll(list);
                            adapter.notifyDataSetChanged();
                            /*for (int i = 0; i < data.size(); i++) {

                                StarBar starBar = listView.getChildAt(i).findViewById(R.id.starBar);
                                starBar.setStarMark(5, 2);
                            }*/
                          //  adapter2.notifyDataSetChanged();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //评价
    private void pingjia() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952

        String resultString = "";
        LogU.Ld("1608", "评价---" + token + "---uuid---" + syUID+resultString);
        if(null ==list2 && list2.size()<=0){
            System.out.println("list2内容为空！");
        }else{

            StringBuilder sb = new StringBuilder();


            for(int i=0;i<list2.size();i++){
                if(i<list2.size()-1){
                    sb.append(list2.get(i));
                    sb.append(",");
                }else{
                    sb.append(list2.get(i));
                }
            }

            resultString = sb.toString();

        }

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addComment")
                .addHeader("token", token)
                .addParams("publicUuid", uuid)
                .addParams("siteUid", siteUid)
                .addParams("userComment", array1.toString())
                .addParams("price", jiage.getStarMark() + "")
                .addParams("service", fuwu.getStarMark() + "")
                .addParams("equscore", sheshi.getStarMark() + "")
                .addParams("siteContent", editText.getText().toString())
                .addParams("imgBaseURL", resultString)
                .addParams("imgURL", resultString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "评价" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            /*if (pingjiaTAG.equals("0")){
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                intent.setClass(PingjiaActivity.this, PingjiaTwoActivity.class);
                                bundle.putString("uuid", uuid);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }else{*/
                            ToastUitl.longs(entity.getMsg());
                            finish();
                            //  }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    private void initjihe() {

//        FQHDyaoqing = (String) spUtileFQTZ.get(this, "FQHDyaoqing", "");
//        if (FQHDyaoqing.length()>3){
//            JSONArray  array1 = JSONArray.fromObject();
//
//        }

        JSONObject object3 = new JSONObject();

//        listuuid.add(YQtouxiang);
//        listscore.add(fqtzXiangmuda);
//        listlabel.add(YQdengji);
        StarBar starBar;
        RecyclerView recyclerView;
        CheckBox textView;
        String s = "";
        listuuid.clear();
//        for (int ii = 0; ii < data2.size(); ii++) {
//            if (data2.get(ii).isSelect()) {
//
//                listuuid.add(data2.get(ii).getId()+"");
//
//            }
//        }
//        LogU.Ld("1608", "标签------" + listuuid.toString());
        for (int i = 0; i < data.size(); i++) {
            JSONObject object2 = new JSONObject();
            try {
                starBar = listView.getChildAt(i).findViewById(R.id.starBar);
                recyclerView = listView.getChildAt(i).findViewById(R.id.recyclerview);
                textView = recyclerView.getChildAt(i).findViewById(R.id.textViewContent);

//                recyclerView.
//                for (int a = 0;a<data2.size();a++){
//                    if (data2.get(a).isSelect()){
//                        s = s+data2.get(a).getLabelName()+"|";
//                    }else{
//
//                    }
//
//                }
                tag = (String) spUtileFQTZ.get(this, "pingjia" + i, "");
                LogU.Ld("1608", "标签------" + tag);

                LogU.Ld("1608", i + "评价拼接------" + starBar.getStarMark());
                object2.put("uuid", data.get(i).getPlayerUUID());
                object2.put("score", df.format(starBar.getStarMark()));
                object2.put("label", tag);
                array1.put(object2);
                LogU.Ld("1608", i + "拼接------" + array1.toString());
            } catch (Exception e) {

            }
        }


//        System.out.println(array1.toString());
        LogU.Ld("1608", "评价拼接json------" + array1.toString());
//        qiuB[0] = "羽毛球";


        pingjia();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pingjia_yjhp:
                if (list2.size() < 1) {
                    yijianhaoping();
                } else {
                    tupian("1");
                }


                break;
            case R.id.pingjia_tijiao:
//                pingjia();
                if (list2.size() < 1) {
                    initjihe();
                } else {
                    tupian("2");
                }


                LogU.Ld("1608", "评价" + jiage.getStarMark() + "");
                break;
        }
    }

    //照片上传
    private void tupian(final String tag) {
        //ss = GetFile.getFile(bitmap, 1, 2 + "").toString();
        ss = GetFile.getFile(bitmap2, 1, 2 + "").toString();
        File img = new File(ss);
        try {
            Log.d("1608", bitmap + "----------分1割----------" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("1608", ss + "----------分割----------" + img + "=========" + token);

//        for (String s : list) {
//            img = new File(s);
//            LogU.Ld("1608", "发布精彩" + img);
//        }
        LogU.Ld("1608", "图片上传接口" + list2.toString());
        PostFormBuilder builder = OkHttpUtils.post();
        builder.url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadSiteImg")
                .addFile("files", "multipart/form-data.png", img)
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
                                yijianhaoping();
                            } else {
                                initjihe();
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PingjiaActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (entity.getMsg().equals("登录超时")){
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(),DengluActivity.class);
//                            startActivity(intent);
//                        }
                        }
                    }
                });
    }


    public void dianjiGrid() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (list2.size() > 2) {
                    ToastUitl.longs("最多只能上传3张照片");
                } else {


                    ActivityCompat.requestPermissions(PingjiaActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    new ActionSheetDialog(PingjiaActivity.this)
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
                                            startActivityForResult(intent, 10);
                                            //   ImageSelector.show(PingjiaActivity.this, 3, 3 - list2.size());
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
                startActivityForResult(new Intent(PingjiaActivity.this, PaisheActivity.class), 100);
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(PingjiaActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(PingjiaActivity.this, PaisheActivity.class), 100);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("1608", requestCode + "++相机1" + data);


        if (resultCode == 101) {

            path = data.getStringExtra("path");
            //图片路径
//            urlpath = FileUtilcll.saveFile(JCFBActivity.this, "temphead.jpg", BitmapFactory.decodeFile(path));
            list2.add(String.valueOf(path));
            adapter2.notifyDataSetChanged();
            Log.i("1608", "picture" + path + "---" + urlpath + "====" + String.valueOf(path));
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
        if (requestCode == 10) {
            Log.d("1608", "相机完毕");
         /*   if (data != null) {
                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/xiaoma.jpg");
                Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp));
                bitmap2 = BitmapFactory.decodeFile(temp.getPath());
                filePath = FileUtil.getFilePathByUri(this, Uri.fromFile(temp));
                Log.d("1608", "相机完毕" + filePath);
                Log.i("1608", "picture" + filePath + "---" + urlpath + "====" + String.valueOf(filePath));
                startPhotoZoom(Uri.fromFile(temp));
                list2.add(filePath);
                adapter2.notifyDataSetChanged();

            } else {*/
            Uri data1 = data.getData();
            String filePathByUri = FileUtil.getFilePathByUri(this, data1);
            File temp = new File(Environment.getExternalStorageDirectory()
                    + "/xiaoma.jpg");
            filePath = FileUtil.getFilePathByUri(this, Uri.fromFile(temp));
            bitmap2 = BitmapFactory.decodeFile(filePathByUri);
            Log.d("1608", "相机完毕" + temp + "------" + Uri.fromFile(temp)+"====="+bitmap2+"========"+filePath);

                startPhotoZoom(data.getData());
          //  }

        }
        if (requestCode == 12) {
            Log.d("1608", "裁剪结束1" + data);
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                Log.d("1608", bitmap + "--=====" + uritempFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Log.d("1608", "裁剪结束2" + bitmap);
            setPicToView(bitmap);



        }

        if (requestCode == 3) {
            Log.d("1608", bitmap + "===--" + uritempFile);
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
                urlpath = FileUtilcll.saveFile(PingjiaActivity.this, "temphead.jpg", getimage(list.get(i)));
                list2.add(urlpath);
                LogU.Ld("1608", "压缩后" + getimage(list2.get(i)));
                LogU.Ld("1608", "压缩后" + list2.toString());
            }

            GvAdapter adapter = new GvAdapter(PingjiaActivity.this, list2);
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
    private void setPicToView(Bitmap picdata) {

        Log.d("1608", "----------路径----------" + urlpath + "啥" + bitmap + "啥" + picdata);
        if (picdata != null) {

            //图片路径
            urlpath = FileUtilcll.saveFile(PingjiaActivity.this, "temphead.jpg", picdata);
            Log.d("1608", "----------路径----------" + urlpath + "啥" + bitmap + "啥" + picdata);
            Log.d("1608", urlpath + "啥" + bitmap + "啥" + picdata);

            if (list2.size() >= 6) {
                Toast.makeText(PingjiaActivity.this, "最多选择六张图片", Toast.LENGTH_LONG).show();
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
