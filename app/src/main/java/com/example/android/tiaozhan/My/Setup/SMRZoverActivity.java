package com.example.android.tiaozhan.My.Setup;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
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

public class SMRZoverActivity extends BaseActivity {

    private TextView biaoti,name,sfz;
    private ImageView fanhui;
    private String token,tag;
    private SPUtils spUtils;
    @Override
    public int initContentView() {
        return R.layout.activity_smrz_over;
    }

    @Override
    protected void initUIAndListener() {
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }
        });
        name = findViewById(R.id.smrz_name);
        sfz = findViewById(R.id.smrz_sfz);
    }

    @Override
    protected void initData() {
        init();
    }

    //实名认证返回数据
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

                            String  sfzString = entity.getData().getPlayerID();
                            String phoneNumber = sfzString.substring(0, 1) + "*****************" + sfzString.substring(17, sfzString.length());
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

                            sfz.setText(phoneNumber);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }
}
