package com.yang.conf;

/**
 * Created by eqruvvz on 8/22/2016.
 */
public class CameraConfig {
    private int mQuality;
    private int mWidth;
    private int mHeight;
    private int mVideoLength;
    private int mCameraNo;
    private String mShutterName;
    private int mPicsPerSec;
    private int mPicTakeSec;
    private boolean mStartNotify;
    private boolean mFinishNotify;

    public boolean ismStartNotify() {
        return mStartNotify;
    }

    public void setmStartNotify(boolean mStartNotify) {
        this.mStartNotify = mStartNotify;
    }

    public int getmPicTakeSec() {
        return mPicTakeSec;
    }

    public void setmPicTakeSec(int mPicTakeSec) {
        this.mPicTakeSec = mPicTakeSec;
    }

    public CameraConfig(){
        this.mQuality = 85;
        this.mWidth = 1600;
        this.mHeight = 1200;
        this.mVideoLength = 18000000;
        this.mCameraNo = 0;
        this.mShutterName = "";
        this.mPicsPerSec = 5;
        this.mPicTakeSec = 10;
        this.mStartNotify = true;
        this.mFinishNotify = true;
    }
    public int getmQuality() {
        return mQuality;
    }

    public void setmQuality(int mQuality) {
        this.mQuality = mQuality;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getmVideoLength() {
        return mVideoLength;
    }

    public void setmVideoLength(int mVideoLength) {
        this.mVideoLength = mVideoLength;
    }

    public int getmCameraNo() {
        return mCameraNo;
    }

    public void setmCameraNo(int mCameraNo) {
        this.mCameraNo = mCameraNo;
    }

    public String getmShutterName() {
        return mShutterName;
    }

    public void setmShutterName(String mShutterName) {
        this.mShutterName = mShutterName;
    }

    public int getmPicsPerSec() {
        return mPicsPerSec;
    }

    public void setmPicsPerSec(int mPicsPerSec) {
        this.mPicsPerSec = mPicsPerSec;
    }


    public boolean ismFinishNotify() {
        return mFinishNotify;
    }

    public void setmFinishNotify(boolean mFinishNotify) {
        this.mFinishNotify = mFinishNotify;
    }
}
