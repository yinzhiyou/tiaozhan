package com.example.android.tiaozhan.Toos.time.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.time.view.WheelStyle;
import com.example.android.tiaozhan.Toos.time.view.WheelView;

public class FQSelectLVDialog extends BaseDialog {
    private View dialogView;
    private WheelView leftWheel;
    private WheelView rightWheel;

    private int timeType = 0;
    private OnClickListener onClickListener;

    boolean cancelable = true;
    int tag;
    /**
     * 创建一个时间选择对话框
     *
     * @param mContext
     */
    public FQSelectLVDialog(Context mContext, OnClickListener listener) {
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
        dialogView = layoutInflater.inflate(R.layout.dialog_fq_lv_select, null);
        leftWheel = (WheelView) dialogView.findViewById(R.id.select_time_wheel_left);
        rightWheel = (WheelView) dialogView.findViewById(R.id.select_time_wheel_right);
        leftWheel.setWheelStyle(WheelStyle.STYLE_LIGHT_LV);
        rightWheel.setWheelStyle(WheelStyle.STYLE_LIGHT_LV);

        dialog = new AlertDialog.Builder(context).setView(dialogView).create();

        dialog.setCancelable(cancelable);

        TextView cancelBtn = (TextView) dialogView.findViewById(R.id.select_time_cancel_btn);

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
}
