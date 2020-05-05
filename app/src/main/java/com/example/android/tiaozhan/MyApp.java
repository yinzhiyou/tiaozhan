package com.example.android.tiaozhan;

import com.bumptech.glide.request.target.ViewTarget;

public class MyApp extends MyApplication {
    public static int CODE = -1;
    public MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
        app=this;
    }
}
