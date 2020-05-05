package com.example.android.tiaozhan.Toos;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.android.tiaozhan.R;

public class CustomPopwindowTY extends PopupWindow {
    private View mView;

    public CustomPopwindowTY(Activity context, View.OnClickListener itemsOnClick){
        super(context);
        initView(context,itemsOnClick);
    }

    private void initView(final Activity context, View.OnClickListener itemsOnClick) {
        // TODO Auto-generated method stub
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.select_popwindow_ty,null);
        LinearLayout ll_select01 = (LinearLayout) mView.findViewById(R.id.ll_select01_ty);
        LinearLayout ll_select02 = (LinearLayout) mView.findViewById(R.id.ll_select02_ty);
        LinearLayout ll_select03 = (LinearLayout) mView.findViewById(R.id.ll_select03_ty);
        LinearLayout ll_select04 = (LinearLayout) mView.findViewById(R.id.ll_select04_ty);
        LinearLayout ll_select05 = (LinearLayout) mView.findViewById(R.id.ll_select05_ty);
        //取消按钮
       /* ll_select03.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
                backgroundAlpha(context, 1f);
            }
        });*/
        //设置按钮监听
        ll_select01.setOnClickListener(itemsOnClick);
        ll_select02.setOnClickListener(itemsOnClick);
        ll_select03.setOnClickListener(itemsOnClick);
        ll_select04.setOnClickListener(itemsOnClick);
        ll_select05.setOnClickListener(itemsOnClick);

        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
//        this.setOutsideTouchable(false);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //  this.setAnimationStyle(R.style.select_anim);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context,0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


}
