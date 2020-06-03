package com.jingshuai.android.fregmentapp.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 11/22/2017.
 */

public class LinearLayoutGroup extends LinearLayout {
    public LinearLayoutGroup(Context context) {
        super(context);
    }

    public LinearLayoutGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup dispatchTouchEvent default type="+ev.getAction());
        boolean returnflag = super.dispatchTouchEvent(ev);
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup dispatchTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup onInterceptTouchEvent default type="+ev.getAction());
        boolean returnflag = super.onInterceptTouchEvent(ev);
        returnflag = true;
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup onInterceptTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup onTouchEvent onTouchEvent type="+event.getAction());
        boolean returnflag = super.onTouchEvent(event);
        //returnflag = true;
        MLog.i(Constaint.LOG_TAG,"LinearLayoutGroup onTouchEvent return="+returnflag);
        return returnflag;
    }
}
