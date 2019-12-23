package com.example.android.promoter.Toos;

public interface OnResponseListener {
    void onSuccess();

    void onCancel();

    void onFail(String message);
}