package com.example.android.promoter.My.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 修改登陆密码
 */
public class MyXGMMActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti;
    private ImageView fanhui;
    private RelativeLayout tijiao;
    private SPUtils spUtils;
    private String token;
    private EditText editTextJiu, editTextXin, editTextXinTwo;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_xgmm);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_xgmm;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        tijiao = findViewById(R.id.my_xgmm_tijiao);
        tijiao.setOnClickListener(this);
        editTextJiu = findViewById(R.id.my_xgmm_jiu);
        editTextXin = findViewById(R.id.my_xgmm_xin);
        editTextXinTwo = findViewById(R.id.my_xgmm_xin_two);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
    }

    @Override
    protected void initData() {
        biaoti.setText("修改登陆密码");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_xgmm_tijiao:
                initDownload();
                break;
        }
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "修改密码" + token);

        if (TextUtils.isEmpty(editTextJiu.getText()) || TextUtils.isEmpty(editTextXin.getText()) || TextUtils.isEmpty(editTextXinTwo.getText())) {
            Toast.makeText(this, "请把信息填写完整", Toast.LENGTH_SHORT).show();
        } else if (!editTextXin.getText().toString().equals(editTextXinTwo.getText().toString())) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        } else if (editTextXin.getText().length() < 6) {

            Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
        } else {

            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/updateUserPwd")
                    .addHeader("token", token)
                    .addParams("oldPwd", editTextJiu.getText().toString())
                    .addParams("newPwd", editTextXin.getText().toString())
                    .addParams("comNewPwd", editTextXinTwo.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();
                            LogU.Ld("1608", "修改密码" + result);
                            Boolean a = result.indexOf("2000") != -1;
                            if (a) {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(MyXGMMActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MyXGMMActivity.this, DengluActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(MyXGMMActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                            }
                        }
                    });
        }
    }
}
