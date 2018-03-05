package com.arouter.jingshuai.javademo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by eqruvvz on 2/23/2018.
 */

public class JavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
