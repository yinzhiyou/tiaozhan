package com.example.android.tiaozhan.My.About;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;

/**
 *关于应用
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener{

    private TextView biaoti;
    private ImageView fanhui;
    private LinearLayout kefu,women,shengming;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        kefu = findViewById(R.id.about_kefu);
        kefu.setOnClickListener(this);
        women = findViewById(R.id.about_women);
        women.setOnClickListener(this);
        shengming = findViewById(R.id.about_shengming);
        shengming.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        biaoti.setText("关于应用");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.about_kefu:
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "18201395884");
                intent1.setData(data);
                startActivity(intent1);
                break;
            case R.id.about_women:
                intent.setClass(this,AboutMyActivity.class);
                startActivity(intent);
                break;
            case R.id.about_shengming:
                intent.setClass(this,AboutSMActivity.class);
                startActivity(intent);
                break;

        }
    }
}
