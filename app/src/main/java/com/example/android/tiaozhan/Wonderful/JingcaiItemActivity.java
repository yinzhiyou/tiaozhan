package com.example.android.tiaozhan.Wonderful;

import android.os.IBinder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Adapter.MyjingcaiListGridAdapter;
import com.example.android.tiaozhan.Adapter.PinglunAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.MyListView;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
/**
 * 精彩瞬间详情
 */
public class JingcaiItemActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti,neirongText,nameText,riqiText,pinglunText,dianzanText,pinglun2Text;
    private ImageView fanhui,touxiangImage,imageView;
    private String neirong,pinglunshu,dianzanshu,yue,ri,uuid;
    private String token,touxiang,nickname;
    private List<String> list;
    private SPUtils spUtils;
    private GridView gridView;
    private MyjingcaiListGridAdapter adapter;
    private int shuliang,xiabiao;
//    private List<MyjingcaiEntity.DataBean.ResLstBean.CommentsBean.CommentLstBean> data;
    private MyListView listView;
    private PinglunAdapter pinglunAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_jingcai_item);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            StatusBarUtil.setStatusBarColor(this, R.color.white); }
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_jingcai_item;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        neirongText = findViewById(R.id.my_jingcai_item_neirong);
        nameText = findViewById(R.id.my_jingcai_item_name);
        riqiText = findViewById(R.id.my_jingcai_item_riqi);
        pinglunText = findViewById(R.id.my_jingcai_item_pinglunshu);
        dianzanText = findViewById(R.id.my_jingcai_item_dianzanshu);
        pinglun2Text = findViewById(R.id.my_jingcai_item_pinglunshu2);
        touxiangImage = findViewById(R.id.my_jingcai_item_touxiang);
        gridView = findViewById(R.id.my_jingcai_item_grid);
        imageView = findViewById(R.id.my_jingcai_item_image);
        listView = findViewById(R.id.my_jingcai_item_list);
    }

    @Override
    protected void initData() {
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        touxiang = (String) spUtils.get(this, "touxiang", "无");
        nickname = (String) spUtils.get(this, "nickname", " ");

        biaoti.setText("详情");
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        neirong =  bundle.getString("neirong");
        pinglunshu =  bundle.getString("pinglunshu");
        dianzanshu =  bundle.getString("dianzanshu");
        yue =  bundle.getString("yue");
        ri =  bundle.getString("ri");
        list = bundle.getStringArrayList("tupian");
        shuliang = bundle.getInt("shuliang");
        xiabiao = bundle.getInt("xiabiao");
        uuid = bundle.getString("uuid");

        neirongText.setText(neirong);
        pinglunText.setText(pinglunshu);
        dianzanText.setText(dianzanshu);
        riqiText.setText(yue+"月"+ri+"日");
        pinglun2Text.setText("评论    "+pinglunshu);
        nameText.setText(nickname);
        Glide.with(this).load(getResources().getString(R.string.imgurl)+touxiang).into(touxiangImage);
        adapter = new MyjingcaiListGridAdapter(this,list);
        if (shuliang > 1) {
            gridView.setAdapter(adapter);
            gridView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        } else {
            Glide.with(this).load(getResources().getString(R.string.imgurl)+ list.get(0)).into(imageView);
        }
        //评论数据
//        data = new ArrayList<>();
//        pinglunAdapter = new PinglunAdapter(this,data);
        jingcai();
    }

    //精彩瞬间
    private void jingcai() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "个人精彩评论" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRelationCommentLst")
                .addHeader("token",token)
                .addParams("uuid",uuid)
                .addParams("page","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "个人精彩" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
//                            MyjingcaiEntity entity = gson.fromJson(result, MyjingcaiEntity.class);
//                            final List<MyjingcaiEntity.DataBean.ResLstBean.CommentsBean.CommentLstBean> list;
//                            list = entity.getData().getResLst().get(xiabiao).getComments().getCommentLst();
//                            data.addAll(list);
//                            listView.setAdapter(pinglunAdapter);

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JingcaiItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
