package com.example.android.promoter.Toos;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.MyApplication;
import com.example.android.promoter.R;

public class ToastUitl {


    public static void longs(String message){
        message(true,message);
    }

    private static void shorts(String message){
        message(false,message);
    }

    private static void message(boolean isLong,String message){
        //加载Toast布局
        View toastRoot = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.toast_custom, null);
        //初始化布局控件
       TextView mTextView = (TextView) toastRoot.findViewById(R.id.txt_toast);
        //为控件设置属性
        mTextView.setText(message);
        //Toast的初始化
        Toast toastStart = new Toast(MyApplication.getContext());
        //获取屏幕高度
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的2/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3*2);
        toastStart.setDuration(isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT);
        toastStart.setView(toastRoot);
        toastStart.show();
    }


}

