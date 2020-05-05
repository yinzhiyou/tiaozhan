package com.example.android.tiaozhan.Toos.util;



public class WxShareManagerUtil {
private static  JSCallback mJsCallback;
    private static class WxInstance {
        private static final WxShareManagerUtil mInstance = new WxShareManagerUtil();
    }
    public static WxShareManagerUtil getInstance() {
        return WxInstance.mInstance;
    }
    public void sendResult(String result) {
            mJsCallback.invoke(result);
    }
    public void setJsCallback(JSCallback mJsCallback) {
        this.mJsCallback = mJsCallback;
    }

    interface JSCallback {

        void invoke(String result);
    }
}
