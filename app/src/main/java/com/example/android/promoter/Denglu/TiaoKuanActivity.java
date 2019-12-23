package com.example.android.promoter.Denglu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.DengluEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.TiaokuanEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *条款
 */
public class TiaoKuanActivity extends BaseActivity {

    private TextView textView,biaoti;
    private ImageView fanhui;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tiao_kuan);
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_tiao_kuan;
    }

    @Override
    protected void initUIAndListener() {
        textView = findViewById(R.id.tiaokuan_text);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        biaoti.setText("服务条款");
        initDownload();
    }

    private void initDownload() {
//       http://192.168.0.203/tzOne/public/index.php/api/term

        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs)+"/term")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608","条约信息"+result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a){
                            Gson gson = new Gson();
                            TiaokuanEntity entity = gson.fromJson(result,TiaokuanEntity.class);
                            textView.setText(entity.getData().getComment());

                        }else{
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result,JiekouSBEntity.class);
                            Toast.makeText(TiaoKuanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}
