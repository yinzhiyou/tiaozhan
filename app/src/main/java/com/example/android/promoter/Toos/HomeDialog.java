package com.example.android.promoter.Toos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.R;

public class HomeDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView textView;
    private String jinbi;
    public HomeDialog(Context context,String jinbi) {
// 更改样式,把背景设置为透明的
        super(context, R.style.LocatonDialogStyle);

        this.context = context;
        this.jinbi = jinbi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//加载dialog的布局
        setContentView(R.layout.home_dialog);
//拿到布局控件进行处理
        RelativeLayout imageView = (RelativeLayout) findViewById(R.id.mm);
        imageView.setOnClickListener(this);
        textView = findViewById(R.id.qiandao_text);
        textView.setText("恭喜+1枚对手币\n目前对手币"+jinbi+"枚");
//初始化布局的位置
        initLayoutParams();

    }

    // 初始化布局的参数
    private void initLayoutParams() {
// 布局的参数
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

}