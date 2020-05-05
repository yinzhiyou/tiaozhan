package com.example.android.tiaozhan.Wonderful;

import android.os.IBinder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.MyjingcaiListGridAdapter;
import com.example.android.tiaozhan.Adapter.PinglunAdapter;
import com.example.android.tiaozhan.Entity.JCItemEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
/**
 * 我的精彩瞬间详情
 */
public class MainJingcaiItemActivity extends BaseActivity implements View.OnClickListener{
    private TextView biaoti;
    private ImageView touxiang,fanhui,imageView,dianzanImage;
    private TextView name,time,neirong,pinglun,dianzan,pinglunTwo;
    private   Bundle bundle;
    private String uuid,token;
    private SPUtils spUtils;
    private ListView listView;
    private   List<JCItemEntity.DataBean.CommentsBean.CommentLstBean> data;
    private PinglunAdapter adapter;
    private MyjingcaiListGridAdapter adapter2;
    private EditText editText;
    private Button fasong;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private GridView gridView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_jingcai_item);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_main_jingcai_item;
    }

    @Override
    protected void initUIAndListener() {
        touxiang = findViewById(R.id.jingcai_item_touxiang);
        name = findViewById(R.id.jingcai_item_name);
        time = findViewById(R.id.jingcai_item_riqi);
        neirong = findViewById(R.id.jingcai_item_neirong);
        pinglun = findViewById(R.id.jingcai_item_pinglunshu);
        pinglunTwo = findViewById(R.id.jingcai_item_pinglunshu2);
        dianzan = findViewById(R.id.jingcai_item_dianzanshu);
        listView = findViewById(R.id.jingcai_item_list);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        editText = findViewById(R.id.main_jingcai_edit);
        fasong = findViewById(R.id.jingcai_fasong);
        fasong.setOnClickListener(this);
        imageView = findViewById(R.id.jingcai_item_image);
        gridView = findViewById(R.id.my_jingcai_item_grid);
        jcVideoPlayerStandard = findViewById(R.id.jingcai_item_video);
        dianzanImage = findViewById(R.id.jingcai_item_dianzan);
        dianzanImage.setOnClickListener(this);
        spUtils = new SPUtils();
        data = new ArrayList<>();
        adapter = new PinglunAdapter(this,data);
    }

    @Override
    protected void initData() {
        biaoti.setText("详情");
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
        token = (String) spUtils.get(this, "logintoken", "无");
        jingcai();
    }



    //精彩瞬间详情
    private void jingcai() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/detailWonderful")
                .addHeader("token",token)
                .addParams("wonderfulId",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "精彩瞬间详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            ImageLoader imageLoader = null;
                            Gson gson = new Gson();
                            JCItemEntity entity = gson.fromJson(result, JCItemEntity.class);
                            name.setText(entity.getData().getUserInfo().getNickname());
                            Glide.with(MainJingcaiItemActivity.this).load(getResources().getString(R.string.imgurl)+entity.getData().getUserInfo().getImgURL()).into(touxiang);
                            neirong.setText(entity.getData().getComment());
                            pinglun.setText(entity.getData().getCommentCount()+"");
                            pinglunTwo.setText("评论    "+entity.getData().getCommentCount());
                            dianzan.setText(entity.getData().getPraiseCount()+"");

                            if (entity.getData().getImgPath().get(0).indexOf(".mp4") != -1 &&entity.getData().getImgPath().size()<=1){
                                jcVideoPlayerStandard.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                                gridView.setVisibility(View.GONE);
                                jcVideoPlayerStandard.setUp(getResources().getString(R.string.imgurl) +entity.getData().getImgPath().get(0)
                                        , jcVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
                                ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainJingcaiItemActivity.this));
                                imageLoader.getInstance().displayImage("",
                                        jcVideoPlayerStandard.thumbImageView);
                            }else if (entity.getData().getImgPath().size()>1){
                                adapter2 = new MyjingcaiListGridAdapter(MainJingcaiItemActivity.this,entity.getData().getImgPath());
                                jcVideoPlayerStandard.setVisibility(View.GONE);
                                imageView.setVisibility(View.GONE);
                                gridView.setVisibility(View.VISIBLE);
                                gridView.setAdapter(adapter2);
                                gridView.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                            } else if(entity.getData().getImgPath().size()<=1){
                                Glide.with(MainJingcaiItemActivity.this).load(getResources().getString(R.string.imgurl) + entity.getData().getImgPath().get(0)).into(imageView);
                                jcVideoPlayerStandard.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                gridView.setVisibility(View.GONE);
                            }









                            List<JCItemEntity.DataBean.CommentsBean.CommentLstBean> list;
                            list = entity.getData().getComments().getCommentLst();
                            data.clear();
                            data.addAll(list);
                            listView.setAdapter(adapter);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainJingcaiItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //发布评论
    private void pinglun() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/commentWonderful")
                .addHeader("token",token)
                .addParams("wonderfulId",uuid)
                .addParams("comment",editText.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "发布评论" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainJingcaiItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            jingcai();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MainJingcaiItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(HomeGRTXActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.jingcai_fasong:
                pinglun();
                break;
            case R.id.jingcai_item_dianzan:

                break;
        }
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN  &&
                getCurrentFocus()!=null &&
                getCurrentFocus().getWindowToken()!=null) {

            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, event)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
