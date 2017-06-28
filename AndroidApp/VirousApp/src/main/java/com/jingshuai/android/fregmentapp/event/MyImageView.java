package com.jingshuai.android.fregmentapp.event;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import  android.support.v7.widget.AppCompatImageView;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 6/28/2017.
 */

public class MyImageView extends AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

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

}
