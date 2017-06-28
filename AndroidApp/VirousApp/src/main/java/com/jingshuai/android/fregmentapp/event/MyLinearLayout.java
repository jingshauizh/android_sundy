package com.jingshuai.android.fregmentapp.event;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 6/28/2017.
 */

public class MyLinearLayout extends LinearLayout {

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        MLog.i(" dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i(" dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(" onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i(" onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
