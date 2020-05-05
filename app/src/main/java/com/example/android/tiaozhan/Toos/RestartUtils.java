package com.example.android.tiaozhan.Toos;

public class RestartUtils {
    public static final int STATUS_FORCE_KILLED = -1;//应用在后台被强杀了
    public static final int STATUS_NORMAL = 2; //APP正常态
    public static final String START_LAUNCH_ACTION = "start_launch_action";

    private int appStatus = STATUS_FORCE_KILLED; //默认为被后台回收了

    private static RestartUtils appStatusManager;

    public static RestartUtils getInstance() {
        if (appStatusManager == null) {
            appStatusManager = new RestartUtils();
        }
        return appStatusManager;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }

}
