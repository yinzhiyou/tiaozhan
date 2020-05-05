package com.example.android.tiaozhan.My.About;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;

/**
 *关于我们
 */
public class AboutMyActivity extends BaseActivity {
    private TextView biaoti;
    private ImageView fanhui;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_my);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_about_my;
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
        biaoti.setText("关于我们");
    }
}
