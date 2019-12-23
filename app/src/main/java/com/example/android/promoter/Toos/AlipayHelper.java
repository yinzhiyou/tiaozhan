package com.example.android.promoter.Toos;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.example.android.promoter.Entity.ZhifubaoEntity;


import java.util.Map;

/**
 * Created by Angel on 2018/1/15.
 */

public class AlipayHelper {
    private Activity mActivity;
    private OnAlipayResultListener onAlipayResultListener;
    private static final int SDK_PAY_FLAG = 1;

    public AlipayHelper(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void pay(final String orderInfo){
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                //支付行为需要在独立的非ui线程中执行
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();



        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if(onAlipayResultListener!=null)onAlipayResultListener.alipaySucess();
                    }else if(TextUtils.equals(resultStatus, "6001")){
                        if(onAlipayResultListener!=null)onAlipayResultListener.alipayCancel();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        if(onAlipayResultListener!=null)onAlipayResultListener.alipayFailed();
                    }
                    break;
            }
        }
    };

    public void setOnAlipayResultListener(OnAlipayResultListener onAlipayResultListener) {
        this.onAlipayResultListener = onAlipayResultListener;
    }

    public interface OnAlipayResultListener{
        void alipaySucess();
        void alipayCancel();
        void alipayFailed();
    }
}
