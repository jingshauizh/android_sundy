package com.ericsson.NewPlayer;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.ericsson.NewPlayer.HomePage.datalayer.localdb.DBManager;
import com.ericsson.NewPlayer.HomePage.datalayer.localdb.MovieIdal;
import com.ericsson.NewPlayer.HomePage.datalayer.localdb.NewPlayerDB;
import com.ericsson.NewPlayer.HomePage.datalayer.net.MyVolley;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by eqruvvz on 8/31/2017.
 */

public class PlayerApp extends Application {
    private static PlayerApp _instance;
    private static DBManager dbManager;

    private RefWatcher getmRefWatcher() {
        return mRefWatcher;
    }

    private static RefWatcher mRefWatcher;
    private final String TAG = "PlayerApp";

    public static DBManager getDbManager() {
        return dbManager;
    }

    public static PlayerApp get() {
        return _instance;
    }


    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        PlayerApp application = (PlayerApp) context
                .getApplicationContext();
        return application.mRefWatcher;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "app start onCreate");

        super.onCreate();
        _instance = (PlayerApp) getApplicationContext();
        dbManager = new DBManager(_instance);
        mRefWatcher = LeakCanary.install(this);
        // Initialize Volley
        MyVolley.init(this);

        Log.i(TAG, "app start onCreate finished");

    }


}
