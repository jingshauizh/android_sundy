package com.jingshuai.android.fregmentapp.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.jingshuai.appcommonlib.log.MLog;

/**
 * Created by eqruvvz on 11/22/2017.
 */

public class ImageViewCustomer extends AppCompatImageView {
    public ImageViewCustomer(Context context) {
        super(context);
    }

    public ImageViewCustomer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewCustomer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MLog.i(Constaint.LOG_TAG,"ImageViewCustomer dispatchTouchEvent default type="+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MLog.i(Constaint.LOG_TAG,"ImageViewCustomer onTouchEvent onTouchEvent type="+event.getAction() );
        return super.onTouchEvent(event);
    }


}
