package com.example.android.promoter.My;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.promoter.Adapter.HelpAdapter;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;

/**
 * 帮助中心
 */
public class HelpActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private ListView listView;
    private HelpAdapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_help;
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
        listView = findViewById(R.id.help_list);
        adapter = new HelpAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        biaoti.setText("帮助中心");
    }
}
