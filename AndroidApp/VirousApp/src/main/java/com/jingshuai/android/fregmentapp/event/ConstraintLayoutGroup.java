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
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup dispatchTouchEvent default type="+ev.getAction());
        boolean returnflag = super.dispatchTouchEvent(ev);
        //boolean returnflag = true;
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup dispatchTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup onInterceptTouchEvent default type="+ev.getAction());
        boolean returnflag = super.onInterceptTouchEvent(ev);
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup onInterceptTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup onTouchEvent onTouchEvent type="+event.getAction());
        boolean returnflag = super.onTouchEvent(event);
        MLog.i(Constaint.LOG_TAG,"ConstraintLayoutGroup onTouchEvent return="+returnflag);
        return returnflag;
    }
}
