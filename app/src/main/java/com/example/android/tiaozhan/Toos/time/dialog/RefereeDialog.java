package com.example.android.tiaozhan.Toos.time.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.time.view.WheelStyle;
import com.example.android.tiaozhan.Toos.time.view.WheelView;

public class RefereeDialog extends BaseDialog {
    private View dialogView;
    private WheelView leftWheel;
    private WheelView rightWheel;
    private String  sportName;
    private int timeType = 0;
    private OnClickListener onClickListener;

    boolean cancelable = true;
    int tag;
    /**
     * 创建一个时间选择对话框
     *
     * @param mContext
     */
    public RefereeDialog(Context mContext, OnClickListener listener) {
        this.context = mContext;
        onClickListener = listener;
        create();
    }

    /**
     * 创建选择时间对话框
     */
    private void create() {

        if (dialog != null) {
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        dialogView = layoutInflater.inflate(R.layout.dialog_referee_layout, null);
        leftWheel = (WheelView) dialogView.findViewById(R.id.select_time_wheel_left);
        rightWheel = (WheelView) dialogView.findViewById(R.id.select_time_wheel_right);
        leftWheel.setWheelStyle(WheelStyle.REFEREE_SPORT);
        if (!EmptyUtils.isStrEmpty(sportName)){
            if (sportName.equals("高尔夫")){
                rightWheel.setWheelStyle(WheelStyle.GEF_LV);
            }
        }
        else {
            rightWheel.setWheelStyle(WheelStyle.STYLE_RESEREE_LV);
        }


        LogU.Ld("1608","名字"+sportName);


        dialog = new AlertDialog.Builder(context).setView(dialogView).create();
        dialog.setCancelable(cancelable);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.getDecorView().setBackgroundColor(Color.WHITE);
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.main_menu_animStyle);

        final TextView cancelBtn = (TextView) dialogView.findViewById(R.id.select_time_cancel_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (onClickListener != null) {
                    if (!onClickListener.onCancel()) {


                        dialog.dismiss();
                    }
                } else {
                    dialog.dismiss();
                }

            }
        });
        TextView sureBtn = (TextView) dialogView.findViewById(R.id.select_time_sure_btn);
        sureBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    if (!onClickListener.onSure(leftWheel.getCurrentItem(),
                            rightWheel.getCurrentItem(), timeType)) {
                        dialog.dismiss();
                    }
                } else {
                    dialog.dismiss();
                }

            }
        });

    }

    /**
     * 显示选择时间对话框
     *
     * @param mHour    默认显示的小时
     * @param mMinute  默认小时的分钟
     * @param timeType 设置的时间标签，用于调用者识别回调
     */
    public void show(int mHour, int mMinute, int timeType) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        this.timeType = timeType;
        leftWheel.setCurrentItem(mHour);
        rightWheel.setCurrentItem(mMinute);
        dialog.show();
    }

    /**
     * 显示选择时间对话框
     *
     * @param mHour   默认显示的小时
     * @param mMinute 默认小时的分钟
     */
    public void show(int mHour, int mMinute) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        leftWheel.setCurrentItem(mHour);
        rightWheel.setCurrentItem(mMinute);
        dialog.show();
    }

    /**
     * 选择时间对话框回调
     *
     * @param listener
     */
    public void setOnUpdateTimeListener(OnClickListener listener) {
        onClickListener = listener;
    }

    /**
     * 选择时间对话框回调接口，调用者实现
     *
     * @author huangzj
     */
    public interface OnClickListener {
        boolean onSure(int hour, int minute, int setTimeType);

        boolean onCancel();
    }

    public void setDialogView(String name) {
        this.sportName=name;
    }
}
