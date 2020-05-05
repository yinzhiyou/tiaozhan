package com.example.android.tiaozhan.Toos;

import android.content.Context;
import android.util.AttributeSet;

import com.example.android.tiaozhan.R;

public class AutoRightEditText extends androidx.appcompat.widget.AppCompatEditText {
    public AutoRightEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSelection(getText().length());
    }

    public AutoRightEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public AutoRightEditText(Context context) {
        this(context, null);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //光标首次获取焦点是在最后面，之后操作就是按照点击的位置移动光标
        if (isEnabled() && hasFocus() && hasFocusable()) {
            if (selStart==0){
                setSelection(getText().length());
            }else {
                setSelection(selEnd);
            }
        } else {
            setSelection(getText().length());
        }

    }


}
