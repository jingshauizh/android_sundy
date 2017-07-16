package com.jingshuai.android.fregmentapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/7/16 0016.
 */

public class MyApplication  extends Application{
    private static Context appContext;

    @Override
    public void onCreate(){
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
