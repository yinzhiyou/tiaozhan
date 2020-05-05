package com.example.android.tiaozhan.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;


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
        biaoti.setText("对手币");

    }
}
