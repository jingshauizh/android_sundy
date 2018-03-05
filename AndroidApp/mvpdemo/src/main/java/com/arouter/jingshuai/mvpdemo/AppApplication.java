package com.arouter.jingshuai.mvpdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by eqruvvz on 2/23/2018.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
