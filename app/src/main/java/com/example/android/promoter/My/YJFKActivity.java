package com.example.android.promoter.My;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.ToastUitl;

/**
 * 意见反馈
 */
public class YJFKActivity extends BaseActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private EditText editText;
    private TextView textView;
    private final int maxNum = 200;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_yjfk);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_yjfk;
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
       editText = findViewById(R.id.yjfk_edit);
       textView = findViewById(R.id.yjfk_text);


    }

    @Override
    protected void initData() {
        biaoti.setText("意见反馈");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (s.length() + (after-count) >=maxNum) {
//
//                    ToastUitl.longs("不能超过" + maxNum + "字！");
//                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView.setText("剩余字数："+ (maxNum-s.length()));
            }
        });
    }
}
