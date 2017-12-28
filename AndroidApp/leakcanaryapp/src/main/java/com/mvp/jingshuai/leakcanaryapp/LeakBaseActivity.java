package com.mvp.jingshuai.leakcanaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.appcommonlib.log.MLog;

public class LeakBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.e(" LeakBaseActivity onCreate ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.e(" LeakBaseActivity onDestroy ");
        ExampleApplication.getmRefWatcher().watch(this);
    }
}
