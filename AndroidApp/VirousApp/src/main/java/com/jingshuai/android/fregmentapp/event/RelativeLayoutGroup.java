package com.jingshuai.android.fregmentapp.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 11/22/2017.
 */

public class RelativeLayoutGroup extends RelativeLayout {
    public RelativeLayoutGroup(Context context) {
        super(context);
    }

    public RelativeLayoutGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup dispatchTouchEvent default type="+ev.getAction());
        boolean returnflag = super.dispatchTouchEvent(ev);
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup dispatchTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup onInterceptTouchEvent default type="+ev.getAction());
        boolean returnflag = super.onInterceptTouchEvent(ev);
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup onInterceptTouchEvent return="+returnflag);
        return returnflag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup onTouchEvent onTouchEvent type="+event.getAction());
        boolean returnflag = super.onTouchEvent(event);
        MLog.i(Constaint.LOG_TAG,"RelativeLayoutGroup onTouchEvent return="+returnflag);
        return returnflag;
    }


}
