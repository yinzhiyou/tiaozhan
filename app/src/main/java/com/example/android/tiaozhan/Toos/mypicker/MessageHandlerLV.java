package com.example.android.tiaozhan.Toos.mypicker;

import android.os.Handler;
import android.os.Message;

public class MessageHandlerLV extends Handler {
    final LoopViewLV a;

    MessageHandlerLV(LoopViewLV loopview) {
        super();
        a = loopview;
    }

    public final void handleMessage(Message paramMessage) {
        if (paramMessage.what == 1000)
            this.a.invalidate();
        while (true) {
            if (paramMessage.what == 2000)
                LoopViewLV.b(a);
            else if (paramMessage.what == 3000)
                this.a.c();
            super.handleMessage(paramMessage);
            return;
        }
    }
}
