package com.example.android.tiaozhan.Promoter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;

/**
 * 推广员 成为推广运营专员
 */
public class TuiguangHomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView lj_join,ljjr2;




    @Override
    public int initContentView() {
        return R.layout.activity_tuiguang_home;
    }

    @Override
    protected void initUIAndListener() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // StatusBarUtil.setStatusBarColor(this, R.color.white);

            LogU.Le("sou","1111");
        }else {
            //  StatusBarUtil.setTransparent(this);
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
            LogU.Le("sou","000");
        }*/
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        lj_join = findViewById(R.id.lj_join);
        lj_join.setOnClickListener(this);
        ljjr2 = findViewById(R.id.lj_join2);
        ljjr2.setOnClickListener(this);



    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.lj_join:
                intent.setClass(this,DengluActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.lj_join2:
                intent.setClass(this,DengluActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }
}
