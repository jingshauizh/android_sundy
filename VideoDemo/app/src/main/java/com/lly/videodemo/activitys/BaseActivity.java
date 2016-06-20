package com.lly.videodemo.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.xutils.x;

/**
 * BaseActivity[v 1.0.0]
 * classes:com.lly.videodemo.activitys.BaseActivity
 *
 * @author lileiyi
 * @date 2016/1/27
 * @time 10:21
 * @description
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
