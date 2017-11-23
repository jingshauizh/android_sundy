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
        MLog.i("dispatchTouchEvent default type="+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MLog.i("onInterceptTouchEvent default type="+ev.getAction());
        //super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i("onTouchEvent onTouchEvent type="+event.getAction());
        return super.onTouchEvent(event);
    }
}
