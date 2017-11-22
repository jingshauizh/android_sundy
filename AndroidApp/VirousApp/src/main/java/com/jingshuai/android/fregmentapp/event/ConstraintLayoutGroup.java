package com.jingshuai.android.fregmentapp.event;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 11/22/2017.
 */

public class ConstraintLayoutGroup extends ConstraintLayout {
    public ConstraintLayoutGroup(Context context) {
        super(context);
    }

    public ConstraintLayoutGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConstraintLayoutGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i("dispatchTouchEvent default type="+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i("onInterceptTouchEvent default type="+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i("onTouchEvent onTouchEvent type="+event.getAction());
        return super.onTouchEvent(event);
    }
}
