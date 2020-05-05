package com.example.android.tiaozhan.Toos.mypicker;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.android.tiaozhan.R;

import java.util.ArrayList;
import java.util.List;

public class NLPickerDialog  extends Dialog {
    private NLPickerDialog.Params params;

    public NLPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(NLPickerDialog.Params params) {
        this.params = params;
    }


    public void setSelection(String itemValue,String itemValue2) {
        if (params.dataList.size() > 0) {
            int idx = params.dataList.indexOf(itemValue);
            if (idx >= 0) {
                params.initSelection = idx;
                params.loopData.setCurrentItem(params.initSelection);
            }
        }
        if (params.dataList2.size() > 0) {
            int idx = params.dataList2.indexOf(itemValue2);
            if (idx >= 0) {
                params.initSelection1 = idx;
                params.loopData2.setCurrentItem(params.initSelection1);
            }
        }
    }

    public interface OnDataSelectedListener {
        void onDataSelected(String itemValue, int position,String itemValue1, int position1);
        void onCancel();
    }

    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopData,loopData2;
        private String title;
        private String unit;
        private int initSelection,initSelection1;
        private OnDataSelectedListener callback;
        private final List<String> dataList = new ArrayList<>();
        private final List<String> dataList2 = new ArrayList<>();
    }

    public static class Builder {
        private final Context context;
        private final NLPickerDialog.Params params;

        public Builder(Context context) {
            this.context = context;
            params = new NLPickerDialog.Params();
        }

        private final String getCurrDateValue() {
            return params.loopData.getCurrentItemValue();
        }
        private final String getCurrDateValue2() {
            return params.loopData2.getCurrentItemValue();
        }
        public NLPickerDialog.Builder setData(List<String> dataList, List<String> dataList2) {
            params.dataList.clear();
            params.dataList.addAll(dataList);
            params.dataList2.clear();
            params.dataList2.addAll(dataList2);
            return this;
        }

        public NLPickerDialog.Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public NLPickerDialog.Builder setUnit(String unit) {
            params.unit = unit;
            return this;
        }

        public NLPickerDialog.Builder setSelection(int selection,int selection1) {
            params.initSelection = selection;
            params.initSelection1 = selection1;
            return this;
        }

        public NLPickerDialog.Builder setOnDataSelectedListener(OnDataSelectedListener onDataSelectedListener) {
            params.callback = onDataSelectedListener;

            return this;
        }


        public NLPickerDialog create() {
            final NLPickerDialog dialog = new NLPickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_nl_picker, null);

            if (!TextUtils.isEmpty(params.title)) {
                TextView txTitle = (TextView) view.findViewById(R.id.tx_title);
                txTitle.setText(params.title);
                txTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        params.callback.onCancel();
                    }
                });
            }
            if (!TextUtils.isEmpty(params.unit)) {
                TextView txUnit = (TextView) view.findViewById(R.id.tx_unit);
                txUnit.setText(params.unit);
            }

            if (!TextUtils.isEmpty(params.unit)) {
                TextView txUnit2 = (TextView) view.findViewById(R.id.tx_unit2);
                txUnit2.setText(params.unit);
            }
            final LoopView loopData = (LoopView) view.findViewById(R.id.loop_data);
            final LoopView loopData2 = (LoopView) view.findViewById(R.id.loop_data2);
            loopData.setArrayList(params.dataList);
            loopData.setNotLoop();

            loopData2.setArrayList(params.dataList2);
            loopData2.setNotLoop();
            if (params.dataList.size() >0) loopData.setCurrentItem(params.initSelection);
            if (params.dataList2.size() >0) loopData2.setCurrentItem(params.initSelection1);
            view.findViewById(R.id.tx_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    params.callback.onDataSelected(getCurrDateValue(),loopData.getCurrentItem(),getCurrDateValue2(),loopData2.getCurrentItem());
                }
            });

            Window dialogWindow = dialog.getWindow();
            WindowManager m = dialogWindow.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用

            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            // lp.height = (int) (d.getHeight() * 0.38); // 高度设置为屏幕的0.6
            lp.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的0.65
            //  lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            win.setAttributes(lp);
            win.setGravity(Gravity.CENTER);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);

            params.loopData = loopData;
            params.loopData2 = loopData2;
            dialog.setParams(params);

            return dialog;
        }
    }
}
