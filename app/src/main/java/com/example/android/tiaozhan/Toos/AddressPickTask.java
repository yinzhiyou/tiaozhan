package com.example.android.tiaozhan.Toos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.example.android.tiaozhan.R;

import java.util.ArrayList;

import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnLinkageListener;
import cn.addapp.pickers.picker.AddressPicker;
import cn.addapp.pickers.util.ConvertUtils;


/**
 * 获取地址数据并显示地址选择器
 *
 * @author matt
 * blog: addapp.cn
 */
public class AddressPickTask extends AsyncTask<String, Void, ArrayList<Province>> {
    private Activity activity;
    private ProgressDialog dialog;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;

    public AddressPickTask(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<Province> data = new ArrayList<>();
        try {
            String json = ConvertUtils.toString(activity.getAssets().open("city.json"));
            data.addAll(JSON.parseArray(json, Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

   /* @Override
    protected void onPostExecute(ArrayList<Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(activity, result);
            picker.setCanLoop(true);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            picker.setWheelModeEnable(true);
            if (hideCounty) {
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnLinkageListener(callback);
            picker.show();
        } else {
            callback.onAddressInitFailed();
        }
    }
*/
    public interface Callback extends OnLinkageListener {

        void onAddressInitFailed();

    }


    @SuppressWarnings("deprecation")
    @Override
    protected void onPostExecute(ArrayList<Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(activity, result);
           // AddressPicker picker = new AddressPicker(activity, result);
            picker.setCanLoop(true);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            picker.setWheelModeEnable(true);
            if (hideCounty) {
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnLinkageListener(callback);
            picker.show();
            /** 显示几列 **/
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);

           /** 标题区域 **/
            // picker.setTopBackgroundColor(activity.getResources().getColor(R.color.brown));//设置标题背景颜色
            picker.setTopHeight(35);//设置顶部标题栏高度（单位为dp）

            picker.setCancelText("取消");
            picker.setCancelTextSize(18);
            picker.setCancelTextColor(activity.getResources().getColor(R.color.tanc));//设置顶部标题栏取消按钮文字颜色
            picker.setCancelVisible(true);//设置左边按钮是否显示

            /*picker.setTitleText("所在区域");//标题栏
            picker.setTitleTextSize(13);//标题栏大小
            picker.setTitleTextColor(activity.getResources().getColor(R.color.colorPrimary));//设置所在区域颜色
*/
            picker.setSubmitText("确定");//设置标题右边文字
            picker.setSubmitTextSize(18);//设置完成按钮大小

            picker.setSubmitTextColor(activity.getResources().getColor(R.color.tanc));//设置完成按钮颜色

         /* *//**//**  标题与列表的分割线 **//**//*
            picker.setTopLineColor(activity.getResources().getColor(R.color.qing));
            picker.setTopLineVisible(true);
            picker.setTopLineHeight(10);

         /** 列表区域 **/
            picker.setTextSize(25);//字体大小
            picker.setBackgroundColor(activity.getResources().getColor(R.color.tanc));
            picker.setCancelTextColor(activity.getResources().getColor(R.color.tanc));

            //picker.setTextColor(activity.getResources().getColor(R.color.purple));//设置省市县字体滚动颜色
            //  picker.setDividerColor(activity.getResources().getColor(R.color.colorAccent));//设置分割线的颜色

          /** 弹框消失的设置 **/
           //   picker.setCancelable(true);//false：必须选择后弹框才消失，true：点击框外可弹框消失


           /** 列的比重 **/
            if (hideCounty) {
                picker.setColumnWeight(0.8f, 1.0f);
            } else if (hideProvince) {
                picker.setColumnWeight(1.0f, 0.8f);
            } else {
                picker.setColumnWeight(1.0f, 1.0f, 1.0f);
            }

          picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);//设置默认
          //  picker.setOnAddressPickListener(callback);
            picker.show();
        } else {
            callback.onAddressInitFailed();
        }
    }

}
