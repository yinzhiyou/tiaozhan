package com.example.android.tiaozhan.bean;

import android.graphics.Bitmap;

import java.util.List;

public class EventMessage {
    Object object;

    int i;
    List<Bitmap> bmp;
    public EventMessage(int i, List<Bitmap> bmp) {
        this.i=i;
        this.bmp=bmp;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
