package com.example.android.tiaozhan.Toos.mypicker;

import android.view.MotionEvent;

public class LoopViewGestureListenerLV extends android.view.GestureDetector.SimpleOnGestureListener  {
    final LoopViewLV loopView;

    LoopViewGestureListenerLV(LoopViewLV loopview) {
        super();
        loopView = loopview;
    }

    public final boolean onDown(MotionEvent motionevent) {
        if (loopView.mTimer != null) {
            loopView.mTimer.cancel();
        }
        return true;
    }

    public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1) {
        loopView.b(f1);
        return true;
    }
}
