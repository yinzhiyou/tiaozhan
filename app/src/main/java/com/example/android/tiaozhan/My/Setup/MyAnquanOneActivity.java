package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.SMRZEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 设置安全提示问题1
 */
public class MyAnquanOneActivity extends BaseActivity {

    private TextView biaoti,name;
    private ImageView fanhui;
    private RelativeLayout xiayibu;
    private String token,touxiang,nickname,sfzString;
    private SPUtils spUtils;
    private EditText sfz;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_anquan_one);
//    }

    @Override
    public int initContentView() {

        return R.layout.activity_my_anquan_one;
        
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
        xiayibu = findViewById(R.id.my_anquan_one_xiayibu);
        name = findViewById(R.id.my_anquan_name);
        sfz = findViewById(R.id.my_anquan_sfz);
        xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sfz.getText().toString().equals(sfzString)) {
                    Intent intent = new Intent();
                    intent.setClass(MyAnquanOneActivity.this,MyAnquanTWOActivity.class);
                    startActivity(intent);
                }else{

                    ToastUitl.longs("信息不匹配");

                }

            }
        });

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

    }

    @Override
    protected void initData() {
        biaoti.setText("设置安全提示问题");
        init();
    }

//
    //安全问题
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "实名信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            SMRZEntity entity = gson.fromJson(result, SMRZEntity.class);
//                            HomeDialog dialog = new HomeDialog(this);
//                            dialog.show();

                            sfzString = entity.getData().getPlayerID();
                            String realname1 =null;
                            String realname =  entity.getData().getPlayerRealName();

                            char[] r =  realname.toCharArray();
                            if(r.length ==1){
                                realname1 =  realname;
                            }
                            if(r.length == 2){
                                realname1 =  realname.replaceFirst(realname.substring(1),"*");
                            }
                            if (r.length > 2) {
                                realname1 =  realname.replaceFirst(realname.substring(1,r.length-1) ,"*");
                            }
                            name.setText(realname1);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }

}
