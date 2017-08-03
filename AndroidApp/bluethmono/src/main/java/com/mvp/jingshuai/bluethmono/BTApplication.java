package com.mvp.jingshuai.bluethmono;

import android.app.Application;
import android.content.Context;

/**
 * Created by eqruvvz on 8/2/2017.
 */

public class BTApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        appContext = this.getApplicationContext();
        super.onCreate();
    }

    public static Context getAppContext(){
        return appContext;
    }
}
