package com.jingshuai.appcommonlib.util.ui;

import android.hardware.Camera;

import java.util.List;

/**
 * Created by eqruvvz on 6/28/2016.
 */
public class CameraUtil {
    private  Camera mCamera;
   private List<Camera.Size> sizes;
    public CameraUtil(Camera pCamera){
        mCamera = pCamera;
        sizes = mCamera.getParameters().getSupportedPreviewSizes();
    }



    //mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);

    public  Camera.Size getOptimalPreviewSize( int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
}
