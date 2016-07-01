package com.chenww.camera.ui;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

import com.chenww.camera.ui.camera.CameraNoView;

public class ActivityStart extends Activity {
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_activity_start);
        this.mSurfaceTexture = new SurfaceTexture(344);
        this.mSurface = new Surface(mSurfaceTexture);
        CameraNoView mCameraNoView = new CameraNoView(getApplicationContext(),mSurface);
        mCameraNoView.startCamera();
    }
}
