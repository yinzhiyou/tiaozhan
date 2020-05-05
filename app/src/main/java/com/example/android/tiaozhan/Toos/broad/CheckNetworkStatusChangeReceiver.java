package com.example.android.tiaozhan.Toos.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckNetworkStatusChangeReceiver extends BroadcastReceiver {
    public static final String EVENT = "event";
    public static final String ACTION="action";
    private CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener;

    public CheckNetworkStatusChangeReceiver() {

    }

    public void setCheckNetworkStatusChangeListener(CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener) {
        this.mCheckNetworkStatusChangeListener = mCheckNetworkStatusChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        CheckNetworkStatusChangeListener.Status mStatus = (CheckNetworkStatusChangeListener.Status) intent.getSerializableExtra(EVENT);
        mCheckNetworkStatusChangeListener.onEvent(mStatus);
    }

}
