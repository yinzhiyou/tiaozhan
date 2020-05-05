package com.example.android.tiaozhan.Toos;

public interface OnResponseListener {
    void onSuccess();

    void onCancel();

    void onFail(String message);
}