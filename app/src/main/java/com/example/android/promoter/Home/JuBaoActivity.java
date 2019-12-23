package com.example.android.promoter.Home;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Adapter.GvAdapter;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.ActionSheetDialog;
import com.example.android.promoter.Toos.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *举报
 */
public class JuBaoActivity extends BaseActivity implements View.OnClickListener{


    private GridView gridView;
    private TextView biaoti;
    private ImageView fanhui;
    private GvAdapter adapter;
    private List<String> list;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ju_bao);
//    }



    @Override
    public int initContentView() {
        return R.layout.activity_ju_bao;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        gridView = findViewById(R.id.grid_view);
        list = new ArrayList<>();
        adapter = new GvAdapter(this,list);
    }

    @Override
    protected void initData() {
        biaoti.setText("举报");
        gridView.setAdapter(adapter);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;

        }
    }
}
