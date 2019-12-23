package com.example.android.promoter.My;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.SMRZEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 添加银行卡
 */
public class TJYHKActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti, name, sfz, moren;
    private EditText kahao, shouji;
    private ImageView fanhui;
    private String token, touxiang, nickname, uuid,realname,sfzhao,nameString,sfzString;
    private SPUtils spUtils;
    private RelativeLayout tijiao;
    private LinearLayout morenLayout;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tjyhk);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_tjyhk;
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
        name = findViewById(R.id.bdihk_name);
        moren = findViewById(R.id.bdihk_moren);
        sfz = findViewById(R.id.bdihk_sfz);
        kahao = findViewById(R.id.bdihk_kahao);
        shouji = findViewById(R.id.bdihk_shouji);
        tijiao = findViewById(R.id.my_tixian_tx);
        morenLayout = findViewById(R.id.bdihk_moren_layout);
        morenLayout.setOnClickListener(this);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(shouji.getText()) || TextUtils.isEmpty(kahao.getText())) {
                    ToastUitl.longs("请把信息填写完整");
                } else {
                    initDownload();
                }

            }
        });

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        init();
    }

    @Override
    protected void initData() {
        biaoti.setText("添加银行卡");
    }


    //实名信息
    private void init() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getUserRealInfo")
                .addHeader("token", token)
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


//                            sfzString = entity.getData().getPlayerID();
                            String realname1 = null;
                             realname = entity.getData().getPlayerRealName();
                            nameString = entity.getData().getPlayerRealName();
                            char[] r = realname.toCharArray();
                            if (r.length == 1) {
                                realname1 = realname;
                            }
                            if (r.length == 2) {
                                realname1 = realname.replaceFirst(realname.substring(1), "*");
                            }
                            if (r.length > 2) {
                                realname1 = realname.replaceFirst(realname.substring(1, r.length - 1), "*");
                            }
                            name.setText(realname1);

                            String sfzhao1 = null;
                             sfzhao = entity.getData().getPlayerID();
                             sfzString = entity.getData().getPlayerID();
                            char[] s = sfzhao.toCharArray();

                            sfzhao1 = sfzhao.replaceFirst(sfzhao.substring(4, s.length - 5), "****");
                            sfz.setText(sfzhao1);
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs("您还未实名认证，");

                        }
                    }
                });
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "绑定银行卡" + token+"realName"+realname+"idCardNum"+sfzhao+"bankCardNum"+kahao.getText().toString()
        +"mobile"+shouji.getText().toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addBankInfo")
                .addHeader("token", token)
                .addParams("realName", nameString)
                .addParams("idCardNum", sfzString)
                .addParams("bankCardNum", kahao.getText().toString())
                .addParams("mobile", shouji.getText().toString())
                .addParams("isDefault", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "绑定银行卡" + result);
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


                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bdihk_moren_layout:

                break;


        }
    }
}
