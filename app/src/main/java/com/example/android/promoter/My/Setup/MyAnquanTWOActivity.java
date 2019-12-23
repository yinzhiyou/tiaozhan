package com.example.android.promoter.My.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import android.widget.TextView;


import com.example.android.promoter.Adapter.SpinnerAdapter;
import com.example.android.promoter.Adapter.SpinnerThreeAdapter;
import com.example.android.promoter.Adapter.SpinnerTwoAdapter;
import com.example.android.promoter.Entity.AQWentiEnditu;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 设置安全提示问题2
 */
public class MyAnquanTWOActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private Spinner spinner, spinner2, spinner3;
    private ArrayAdapter<String> arr_adapter;
    private List<String> data_list;
    private List<AQWentiEnditu.DataBean.OneQuestionBean> data;
    private List<AQWentiEnditu.DataBean.TwoQuestionBean> data2;
    private List<AQWentiEnditu.DataBean.ThreeQuestionBean> data3;
    private SpinnerAdapter adapter;
    private SpinnerTwoAdapter adapter2;
    private SpinnerThreeAdapter adapter3;
    private RelativeLayout tijiao;
    private String token,one,two,three;
    private SPUtils spUtils;
    private EditText daanOne,daanTwo,daanThree;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_anquan_two);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_anquan_two;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        daanOne = findViewById(R.id.daan_one);
        daanTwo = findViewById(R.id.daan_two);
        daanThree = findViewById(R.id.daan_three);


        tijiao = findViewById(R.id.my_anquan_two_tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(daanOne.getText()) || TextUtils.isEmpty(daanTwo.getText()) || TextUtils.isEmpty(daanThree.getText())){
                    ToastUitl.longs("请把问题填写完整");
                }else{
                    tijiao();
                }

            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        adapter = new SpinnerAdapter(this, data);
        adapter2 = new SpinnerTwoAdapter(this, data2);
        adapter3 = new SpinnerThreeAdapter(this, data3);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器

    }

    @Override
    protected void initData() {
        biaoti.setText("设置安全提示问题");
        init();
    }

    //安全问题
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getSafeQuestion")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "安全问题" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            AQWentiEnditu entity = gson.fromJson(result, AQWentiEnditu.class);
                            List<AQWentiEnditu.DataBean.OneQuestionBean> list;
                            list = entity.getData().getOneQuestion();
                            data.addAll(list);
                            List<AQWentiEnditu.DataBean.TwoQuestionBean> list2;
                            list2 = entity.getData().getTwoQuestion();
                            data2.addAll(list2);
                            List<AQWentiEnditu.DataBean.ThreeQuestionBean> list3;
                            list3 = entity.getData().getThreeQuestion();
                            data3.addAll(list3);
                            spinner.setAdapter(adapter);
                            spinner2.setAdapter(adapter2);
                            spinner3.setAdapter(adapter3);
                            spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                           int arg2, long arg3) {
                                    // TODO Auto-generated method stub
                                    // 将所选mySpinner 的值带入myTextView 中
                                    ToastUitl.longs(arg2 + "" + data.get(arg2).getId() + "");
                                    one = data.get(arg2).getId()+"";
                                }

                                public void onNothingSelected(AdapterView<?> arg0) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                           int arg2, long arg3) {
                                    // TODO Auto-generated method stub
                                    // 将所选mySpinner 的值带入myTextView 中
                                    ToastUitl.longs(arg2 + "" + data.get(arg2).getId() + "");
                                    two = data2.get(arg2).getId()+"";
                                }

                                public void onNothingSelected(AdapterView<?> arg0) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                           int arg2, long arg3) {
                                    // TODO Auto-generated method stub
                                    // 将所选mySpinner 的值带入myTextView 中
                                    ToastUitl.longs(arg2 + "" + data.get(arg2).getId() + "");
                                    three = data3.get(arg2).getId()+"";
                                }

                                public void onNothingSelected(AdapterView<?> arg0) {
                                    // TODO Auto-generated method stub

                                }
                            });

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

//提交安全问题
    private void tijiao() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/setUserSafeQuestion")
                .addHeader("token",token)
                .addParams("oneQuestionId",one)
                .addParams("oneAnswer",daanOne.getText().toString())
                .addParams("twoQuestionId",two)
                .addParams("twoAnswer",daanTwo.getText().toString())
                .addParams("threeQuestionId",three)
                .addParams("threeAnswer",daanThree.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提交安全问题" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                           Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            Intent intent = new Intent();
                            intent.setClass(MyAnquanTWOActivity.this,MyShezhiActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }
}
