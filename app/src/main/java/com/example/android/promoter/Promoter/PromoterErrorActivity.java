package com.example.android.promoter.Promoter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.SPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromoterErrorActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti,sfs_num,zs_name,chakan_text,again_text1,again_text;
    private ImageView fanhui;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_error;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        zs_name = findViewById(R.id.name_zs);
        sfs_num = findViewById(R.id.num_sfz);
        chakan_text = findViewById(R.id.chakan_text);
        chakan_text.setOnClickListener(this);
        again_text1 = findViewById(R.id.again_text1);
        again_text1.setOnClickListener(this);
        again_text = findViewById(R.id.again_text);
        again_text.setOnClickListener(this);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(PromoterErrorActivity.this, DengluActivity.class);
                startActivity(intent);
                finish();
            }
        });
        biaoti.setText("成为推广运营专员");
    }

    @Override
    protected void initData() {
        SPUtils spUtils = new SPUtils();
        String name = (String) spUtils.get(this, "name_zs", "无");
        String sfz = (String) spUtils.get(this, "num_sfz", "无");
        if (!EmptyUtils.isEmpty(name) || !EmptyUtils.isEmpty(sfz)) {
            String result = replaceNameX(name);
            String sf = showNumber(sfz);
            zs_name.setText(result);
            sfs_num.setText(sf);
        } else {
            zs_name.setText("");
            sfs_num.setText("");
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();

        switch (v.getId()){
            case R.id.chakan_text:
                intent.setClass(PromoterErrorActivity.this, PromoterShowActivity.class);

                startActivity(intent);
                break;
            case R.id.again_text1:
                intent.setClass(PromoterErrorActivity.this, PromoterONEActivity.class);

                startActivity(intent);
                break;
            case R.id.again_text:
                intent.setClass(PromoterErrorActivity.this, PromoterONEActivity.class);

                startActivity(intent);
                break;
        }
    }


    // 保护用户隐私姓名
    public static String replaceNameX(String str) {
// 获取姓名长度
        String custName = str;
        int length = custName.length();

        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == length)
                continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    // 保护用户隐私身份证
    private static String showNumber(String str) {

        int hideLength = 10;//替换位数，这里替代中间8位
        int startIndex = str.length()/2-hideLength/2;
        String replaceSymbol = "*";//替换符号，这里用“#”为例
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<str.length();i++){
            char number = str.charAt(i);
            if (i>=startIndex-1&&i<startIndex+hideLength){
                stringBuilder.append(replaceSymbol);
            }else {
                stringBuilder.append(number);
            }
        }
        return stringBuilder.toString();
    }

}
