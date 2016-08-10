package com.yang;

import android.app.Application;

/**
 * Created by eqruvvz on 7/29/2016.
 */
public class ExampleApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        //LeakCanary.install(this);
    }
}