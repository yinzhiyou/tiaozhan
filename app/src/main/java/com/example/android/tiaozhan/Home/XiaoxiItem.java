package com.example.android.tiaozhan.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.XiaoxiItemEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *消息详情
 */
public class XiaoxiItem extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xiaoxi_item);
//    }
    private TextView biaoti,time,neirong;
    private ImageView fanhui;

    private String token, touxiang, nickname, uuid;
    private SPUtils spUtils;
    @Override
    public int initContentView() {
        return R.layout.activity_xiaoxi_item;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        time = findViewById(R.id.xiaoxi_item_time);
        neirong = findViewById(R.id.xiaoxi_item_neirong);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
    }

    @Override
    protected void initData() {
        biaoti.setText("消息详情");
        xiaoxi();
    }


    //消息详情
    private void xiaoxi() {

        OkHttpUtils
                .get()
                .url("https://app.tiaozhanmeiyitian.com/api" + "/getMessageInfo")
                .addHeader("token",token)
                .addParams("uuid", uuid)
                .addParams("msgCate","systems")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "消息详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            XiaoxiItemEntity entity = gson.fromJson(result, XiaoxiItemEntity.class);
                            time.setText(entity.getData().getAddTime());
                            neirong.setText(entity.getData().getContent());

                        } else {

                        }
                    }
                });

    }

}
