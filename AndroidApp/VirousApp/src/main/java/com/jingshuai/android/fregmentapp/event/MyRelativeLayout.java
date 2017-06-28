package com.jingshuai.android.fregmentapp.event;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 6/28/2017.
 */

public class MyRelativeLayout extends RelativeLayout {
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

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
