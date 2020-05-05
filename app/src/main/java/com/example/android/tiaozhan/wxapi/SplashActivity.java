package com.example.android.tiaozhan.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.tiaozhan.GuideActivity;
import com.example.android.tiaozhan.Main;
import com.example.android.tiaozhan.R;

public class SplashActivity extends AppCompatActivity {
    //是否是第一次使用

    private boolean isFirstUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_WORLD_READABLE);
        isFirstUse = preferences.getBoolean("isFirstUse", true);        /**         *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面         */
        if (isFirstUse) {
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            startActivity(new Intent(this, Main.class));
        }
        finish();        //实例化Editor对象

        SharedPreferences.Editor editor = preferences.edit();        //存入数据
        editor.putBoolean("isFirstUse", false);        //提交修改
        editor.commit();


    }
}
