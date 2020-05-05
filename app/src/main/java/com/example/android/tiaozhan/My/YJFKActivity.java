package com.example.android.tiaozhan.My;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 意见反馈
 */
public class YJFKActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti;
    private ImageView fanhui;
    private EditText editText,phone_num,eml_num;
    private TextView textView;
    private final int maxNum = 200;
    private RelativeLayout tj;
    private String yj;
    private String num;
    private String eml,token;
    private SPUtils spUtils;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_yjfk);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_yjfk;
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
        editText = findViewById(R.id.yjfk_edit);
        textView = findViewById(R.id.yjfk_text);
        phone_num = findViewById(R.id.phone_num);
        eml_num = findViewById(R.id.eml_num);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        tj = findViewById(R.id.my_anquan_one_xiayibu);
        tj.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        biaoti.setText("意见反馈");

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
                textView.setText("剩余字数：" + (maxNum - s.length()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_anquan_one_xiayibu:
                yj = editText.getText().toString();
                num = phone_num.getText().toString();
                eml = eml_num.getText().toString();
                if (EmptyUtils.isStrEmpty(yj)){
                    Toast.makeText(YJFKActivity.this, "请输入您的意见或建议！", Toast.LENGTH_SHORT).show();
                }else {
                    getSubmit();
                }


                break;
        }
    }

    public void getSubmit(){

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addFeedBack")
                .addHeader("token", token)
                .addParams("comment", yj)
                .addParams("mobile", num + "")
                .addParams("email", eml)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "意见反馈" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(YJFKActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(YJFKActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
