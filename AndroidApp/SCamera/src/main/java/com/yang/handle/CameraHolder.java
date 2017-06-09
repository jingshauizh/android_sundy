package com.yang.handle;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by eqruvvz on 6/27/2016.
 */


public class CameraHolder implements SurfaceHolder {
    private Surface mSurface;
    public CameraHolder(Surface pSurface){
        mSurface = pSurface;
    }
    @Override
    public void addCallback(Callback callback) {

    }

    @Override
    public void removeCallback(Callback callback) {

    }

    @Override
    public boolean isCreating() {
        return false;
    }

    @Override
    public void setType(int i) {

    }

    @Override
    public void setFixedSize(int i, int i1) {

    }

    @Override
    public void setSizeFromLayout() {

    }

    @Override
    public void setFormat(int i) {

    }

    @Override
    public void setKeepScreenOn(boolean b) {

    }

    @Override
    public Canvas lockCanvas() {
        return null;
    }

    @Override
    public Canvas lockCanvas(Rect rect) {
        return null;
    }

    @Override
    public void unlockCanvasAndPost(Canvas canvas) {

    }

    @Override
    public Rect getSurfaceFrame() {
        return null;
    }

    @Override
    public Surface getSurface() {
        return mSurface;
    }

}
