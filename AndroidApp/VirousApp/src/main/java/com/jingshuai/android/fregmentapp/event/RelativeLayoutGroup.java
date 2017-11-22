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
