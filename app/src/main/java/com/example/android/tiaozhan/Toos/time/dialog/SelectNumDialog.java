package com.example.android.tiaozhan.Toos.time.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.time.view.WheelStyle;
import com.example.android.tiaozhan.Toos.time.view.WheelView;


/**
 * Created by huangzj on 2015/10/25.
 * <p/>
 * 选择亮屏时间对话框
 */
public class SelectNumDialog extends BaseDialog {

    private View dialogView;
    private WheelView wheel;

    private int timeType = 0;
    private OnClickListener onClickListener;

    boolean cancelable = true;

    /**
     * 创建一个选择亮屏时间对话框
     *
     * @param mContext
     */
    public SelectNumDialog(Context mContext, OnClickListener listener) {
        this.context = mContext;
        onClickListener = listener;
        create();
    }

    /**
     * 创建选择亮屏时间对话框
     */
    private void create() {

        if (dialog != null) {
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        dialogView = layoutInflater.inflate(R.layout.dialog_wheel_select_week, null);
        wheel = (WheelView) dialogView.findViewById(R.id.wheel_week_wheel);

        wheel.setWheelStyle(WheelStyle.REFEREE_NUM);

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
        //设置对话框大小
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView cancelBt = (TextView) dialogView.findViewById(R.id.wheel_week_cancel_btn);
        cancelBt.setOnClickListener(new View.OnClickListener() {

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
        TextView sureBt = (TextView) dialogView.findViewById(R.id.week_wheel_sure_btn);
        sureBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index = wheel.getCurrentItem();

                if (onClickListener != null) {
                    if (!onClickListener.onSure(index, timeType)) {
                        LogU.Ld("1608","sadfsadf");
                        dialog.dismiss();
                    }
                } else {
                    LogU.Ld("1608","sadfsadf不走");
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * 设置对话框类型
     *
     * @param style WheelAdapter类型
     */
    public void setDialogWheelStyle(int style) {
        wheel.setWheelStyle(style);
    }

    /**
     * 显示亮屏时间对话框
     *
     * @param selectItem 默认选中项
     */
    public void show(int selectItem) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        wheel.setCurrentItem(selectItem);
        dialog.show();
    }

    /**
     * 显示选择时间对话框
     *
     * @param selectItme 默认选中项
     * @param timeType   设置的类型标签，用于调用者识别回调
     */
    public void show(int selectItme, int timeType) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }
        this.timeType = timeType;
        wheel.setCurrentItem(selectItme);
        dialog.show();
    }

    /**
     * 选择时间对话框回调
     *
     * @param listener
     */
    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    /**
     * 选择时间对话框回调接口，调用者实现
     *
     * @author huangzj
     */
    public interface OnClickListener {
        boolean onSure(int item, int setTimeType);

        boolean onCancel();
    }
}
