package com.example.android.promoter.Home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.XiuGaiSportAdapter;
import com.example.android.promoter.Denglu.XiugaiXXActivity;
import com.example.android.promoter.Entity.GzXqEntity;
import com.example.android.promoter.Entity.HqSportEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class HomeGzsmActivity extends BaseActivity {

    private ImageView fanhui;
    private TextView jff,dsd,cdf,dsf,wycs,bz,tqtc,wqd,cdzt,zcdc,biaoti;

    private String uuid;

    @Override
    public int initContentView() {
        return R.layout.activity_home_gzsm;
    }

    @Override
    protected void initUIAndListener() {

        fanhui = findViewById(R.id.fanhui);
        jff=findViewById(R.id.gzsm_jsf);
        dsd=findViewById(R.id.gzsm_dsb);
        cdf=findViewById(R.id.gzsm_cdf);
        dsf=findViewById(R.id.gzsm_dsf);
        wycs=findViewById(R.id.gzsm_wycs);
        bz=findViewById(R.id.gzsm_bz);
        tqtc=findViewById(R.id.gzsm_tqtc);
        wqd=findViewById(R.id.gzsm_wqd);
        cdzt=findViewById(R.id.gzsm_cdzt);
        zcdc=findViewById(R.id.gzsm_zcdc);
        biaoti = findViewById(R.id.biaoti);
        biaoti.setText("到场及规则说明");
        Intent intent=getIntent();
        uuid = intent.getStringExtra("uuid");
        LogU.Ld("1608", "运动项目" +uuid);
    }

    @Override
    protected void initData() {

        huoquGzXq();
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void huoquGzXq() {

        OkHttpUtils.get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAttendance")
                .addParams("uuid",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "运动项目" + e.getMessage()+"粗错了"+e+uuid);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final String result = response.toString();
                        LogU.Ld("1608", "运动项目" + result+uuid);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            GzXqEntity gzXqEntity = gson.fromJson(result, GzXqEntity.class);
                            LogU.Le("1608",gzXqEntity+"");
                            jff.setText(gzXqEntity.getData().getProfessionalGoldNotes());
                            dsd.setText(gzXqEntity.getData().getGeneralGoldNotes());
                            cdf.setText(gzXqEntity.getData().getStatementofsitefee());
                            dsf.setText(gzXqEntity.getData().getExplanationofRewardFee());
                            wycs.setText(gzXqEntity.getData().getDefault());
                            bz.setText(gzXqEntity.getData().getGetRemarks());
                            tqtc.setText(gzXqEntity.getData().getTqtc());
                            wqd.setText(gzXqEntity.getData().getWqd());
                            cdzt.setText(gzXqEntity.getData().getCdzt());
                            zcdc.setText(gzXqEntity.getData().getZc());

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeGzsmActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
