package com.example.android.promoter.Home;

import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;

/**
 *奖励规则
 */
public class JLGZActivity extends BaseActivity {

    private  TextView biaoti,context;
    private ImageView fanhui;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_jlgz);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_jlgz;

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
        context = findViewById(R.id.jlgz_text);

    }

    @Override
    protected void initData() {
        biaoti.setText("奖励规则");
        context.setText("");
    }
}