package com.example.android.promoter.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;


/**
 *通用金币
 */
public class HomeTYJBActivity extends BaseActivity {

    private TextView  biaoti;
    private ImageView fanhui;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_tyjb);
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_home_tyjb;
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
    }

    @Override
    protected void initData() {
        biaoti.setText("通用金币");

    }
}
