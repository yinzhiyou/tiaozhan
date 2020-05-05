package com.example.android.tiaozhan.Toos.mypicker;

public class LoopRunnableLV implements Runnable  {
    final LoopViewLV loopView;

    LoopRunnableLV(LoopViewLV loopview) {
        super();
        loopView = loopview;

    }

    public final void run() {
        LoopListener listener = loopView.loopListener;
        int i = LoopViewLV.getSelectItem(loopView);
        listener.onItemSelect(i);
    }
}
