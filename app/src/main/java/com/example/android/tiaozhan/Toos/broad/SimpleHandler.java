package com.example.android.tiaozhan.Toos.broad;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;


import java.lang.ref.WeakReference;


public class SimpleHandler<T extends Activity> extends Handler {
    WeakReference<T> weakReference;

    public SimpleHandler(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (weakReference.get() != null) {
            //发送广播
            Intent mCheckNetworkIntent = new Intent();
            mCheckNetworkIntent.setAction(CheckNetworkStatusChangeReceiver.ACTION);
            CheckNetworkStatusChangeListener.Status status= (CheckNetworkStatusChangeListener.Status) msg.obj;
            mCheckNetworkIntent.putExtra(CheckNetworkStatusChangeReceiver.EVENT,status);
            weakReference.get().sendBroadcast(mCheckNetworkIntent);
        }


    }
}
