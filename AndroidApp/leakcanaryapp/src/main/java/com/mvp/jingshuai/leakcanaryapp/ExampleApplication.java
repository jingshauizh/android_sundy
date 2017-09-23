package com.mvp.jingshuai.leakcanaryapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class ExampleApplication extends Application {

    public static RefWatcher getmRefWatcher() {
        return mRefWatcher;
    }

    private static RefWatcher mRefWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
    }

}
