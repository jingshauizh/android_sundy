package com.mvp.jingshuai.leakcanaryapp;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by eqruvvz on 9/23/2017.
 */

public class TestHelper {
    private Context mCtx;
    private TextView mTextView;

    private static TestHelper outInstance;
    private TestHelper(Context context){
        this.mCtx = context;
    }

    public static TestHelper getOutInstance(Context context){
        if(outInstance == null){
            outInstance = new TestHelper(context);
        }
        return outInstance;
    }
}
