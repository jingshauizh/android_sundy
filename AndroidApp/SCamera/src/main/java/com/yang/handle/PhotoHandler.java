package com.yang.handle;

import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;

import com.jingshuai.appcommonlib.util.Storage;

public class PhotoHandler implements Camera.PreviewCallback {

    private final Context context;
    private final String TAG="PhotoHandler";
    private long checkTime = new Date().getTime();
    private long startTime = new Date().getTime();
    private  boolean mIsSave = true;
    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        Log.i(TAG,"onPreviewFrame=");
        long currentTime = new Date().getTime();
        final Camera mcamera = camera;
         byte[] yuvdata = data;

        if(currentTime - checkTime > 200 || mIsSave){
            mIsSave = false;
            checkTime = currentTime;
//            Thread save = new Thread(new Runnable() {
//                @Override
//                public void run() {
                    Log.v(TAG,"save jpeg size"+yuvdata.length);
                    String fileName = System.currentTimeMillis() + ".jpeg";
                    mIsSave = Storage.savePicture(yuvdata, fileName,
                            mcamera.getParameters().getPreviewSize(),
                            90);
            yuvdata=null;
               // }
//            });
//            save.start();
        }

    }
}