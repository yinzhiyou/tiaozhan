package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.MyWentiAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyWentiEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *更换手机号2
 */
public class GHSJTwoActivity extends BaseActivity implements View.OnClickListener{
    private TextView biaoti;
    private ImageView fanhui;
    private RelativeLayout xiayibu;
    private String token,touxiang,nickname,one;
    private SPUtils spUtils;
    private Spinner spinner;
    private List<MyWentiEntity.DataBean> data;
    private MyWentiAdapter adapter;
    private EditText daan;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ghsjtwo);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_ghsjtwo;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        xiayibu = findViewById(R.id.my_ghsj_two_xiayibu);
        xiayibu.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        data = new ArrayList<>();
        adapter = new MyWentiAdapter(this,data);
        daan = findViewById(R.id.ghsj_daan);
    }

    @Override
    protected void initData() {
        biaoti.setText("更换手机号");
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_ghsj_two_xiayibu:
                panduan();
                break;
        }
    }


    //获取安全问题
    private void init() {

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserSafeQuestion")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取安全问题" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if(a){
                            Gson gson = new Gson();
                            MyWentiEntity entity = gson.fromJson(result, MyWentiEntity.class);
                            List<MyWentiEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);
                            spinner.setAdapter(adapter);
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
                        }else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }


    //判断安全问题
    private void panduan() {
        LogU.Ld("1608", "判断安全问题" + token+one+daan.getText().toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkSafeAnswer")
                .addHeader("token", token)
                .addParams("questionID",one)
                .addParams("answer",daan.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "判断安全问题" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if(a){
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(GHSJTwoActivity.this,GHSJThreeActivity.class);
                            startActivity(intent);
                        }else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(GHSJTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}
