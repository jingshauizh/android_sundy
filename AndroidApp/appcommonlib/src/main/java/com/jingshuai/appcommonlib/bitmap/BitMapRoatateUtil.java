package com.jingshuai.appcommonlib.bitmap;

/**
 * Created by eqruvvz on 1/3/2018.
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

/**
 * TODO<???????????>
 *
 * @author xjp
 * @data: 2014?9?18? ??10:10:30
 * @version: V1.0
 */
public class BitMapRoatateUtil {

    private static final String TAG = "BitmapUtils";

    /**
     *
     * TODO<??????>
     * @throw
     * @return Bitmap
     * @param srcBitmap ????bitmap
     * @param reflectionHeight ???????
     */
    public static Bitmap createReflectedBitmap(Bitmap srcBitmap,int reflectionHeight) {

        if (null == srcBitmap) {
            Log.e(TAG, "the srcBitmap is null");
            return null;
        }

        // The gap between the reflection bitmap and original bitmap.
        final int REFLECTION_GAP = 0;

        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();

        if (0 == srcWidth || srcHeight == 0) {
            Log.e(TAG, "the srcBitmap is null");
            return null;
        }

        // The matrix
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        try {

            // The reflection bitmap, width is same with original's, height is
            // half of original's.
            Bitmap reflectionBitmap = Bitmap.createBitmap(srcBitmap, 0, srcHeight - reflectionHeight,
                    srcWidth, reflectionHeight, matrix, false);

            if (null == reflectionBitmap) {
                Log.e(TAG, "Create the reflectionBitmap is failed");
                return null;
            }

            // Create the bitmap which contains original and reflection bitmap.
            Bitmap bitmapWithReflection = Bitmap.createBitmap(srcWidth, srcHeight + reflectionHeight,
                    Bitmap.Config.ARGB_8888);

            if (null == bitmapWithReflection) {
                return null;
            }

            // Prepare the canvas to draw stuff.
            Canvas canvas = new Canvas(bitmapWithReflection);

            // Draw the original bitmap.
            canvas.drawBitmap(srcBitmap, 0, 0, null);

            // Draw the reflection bitmap.
            canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP,
                    null);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            LinearGradient shader = new LinearGradient(0, srcHeight, 0,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP,
                    0x70FFFFFF, 0x00FFFFFF, TileMode.MIRROR);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(
                    android.graphics.PorterDuff.Mode.DST_IN));

            canvas.save();
            // Draw the linear shader.
            canvas.drawRect(0, srcHeight, srcWidth,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP, paint);
            if (reflectionBitmap != null && !reflectionBitmap.isRecycled()){
                reflectionBitmap.recycle();
                reflectionBitmap = null;
            }

            canvas.restore();

            return bitmapWithReflection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "Create the reflectionBitmap is failed");
        return null;
    }

    /**
     *
     * TODO<??????>
     * @throw
     * @return Bitmap
     * @param srcBitmap ????bitmap
     * @param ret ?????
     */
    public static Bitmap getRoundImage(Bitmap srcBitmap, float ret) {

        if(null == srcBitmap){
            Log.e(TAG, "the srcBitmap is null");
            return null;
        }

        int bitWidth = srcBitmap.getWidth();
        int bitHight = srcBitmap.getHeight();

        BitmapShader bitmapShader = new BitmapShader(srcBitmap,
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        RectF rectf = new RectF(0, 0, bitWidth, bitHight);

        Bitmap outBitmap = Bitmap.createBitmap(bitWidth, bitHight,
                Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        canvas.drawRoundRect(rectf, ret, ret, paint);
        canvas.save();
        canvas.restore();

        return outBitmap;
    }


    /**
     *
     * TODO<????Y???????>
     * @throw
     * @return Bitmap
     * @param srcBitmap ????bitmap
     * @param reflectionHeight ???????
     * @param rotate ???????
     */
    public static Bitmap skewImage(Bitmap srcBitmap, float rotate, int reflectionHeight) {

        if(null == srcBitmap){
            Log.e(TAG, "the srcBitmap is null");
            return null;
        }

        Bitmap reflecteBitmap = createReflectedBitmap(srcBitmap, reflectionHeight);

        if (null == reflecteBitmap){
            Log.e(TAG, "failed to createReflectedBitmap");
            return null;
        }

        int wBitmap = reflecteBitmap.getWidth();
        int hBitmap = reflecteBitmap.getHeight();
        float scaleWidth = ((float) 180) / wBitmap;
        float scaleHeight = ((float) 270) / hBitmap;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        reflecteBitmap = Bitmap.createBitmap(reflecteBitmap, 0, 0, wBitmap, hBitmap, matrix,
                true);
        Camera localCamera = new Camera();
        localCamera.save();
        Matrix localMatrix = new Matrix();
        localCamera.rotateY(rotate);
        localCamera.getMatrix(localMatrix);
        localCamera.restore();
        localMatrix.preTranslate(-reflecteBitmap.getWidth() >> 1,
                -reflecteBitmap.getHeight() >> 1);
        Bitmap localBitmap2 = Bitmap.createBitmap(reflecteBitmap, 0, 0,
                reflecteBitmap.getWidth(), reflecteBitmap.getHeight(), localMatrix,
                true);
        Bitmap localBitmap3 = Bitmap.createBitmap(localBitmap2.getWidth(),
                localBitmap2.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap3);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setFilterBitmap(true);
        localCanvas.drawBitmap(localBitmap2, 0.0F, 0.0F, localPaint);
        if (null != reflecteBitmap && !reflecteBitmap.isRecycled()) {
            reflecteBitmap.recycle();
            reflecteBitmap = null;
        }
        if (null != localBitmap2 && !localBitmap2.isRecycled()) {
            localBitmap2.recycle();
            localBitmap2 = null;
        }
        localCanvas.save();
        localCanvas.restore();
        return localBitmap3;
    }


    /**
     *
     * TODO<???????>
     * @throw
     * @return Bitmap
     * @param bitmap ???
     * @param radius The radius of the blur Supported range 0 < radius <= 25
     * @param context ???
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("NewApi")
    public static Bitmap blurBitmap(Bitmap bitmap,float radius,Context context){

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        if(radius > 25){
            radius = 25.0f;
        }else if (radius <= 0){
            radius = 1.0f;
        }
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();
        bitmap = null;
        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;


    }


    /**
     * TODO<????????????>
     * @param srcBitmap ???
     * @param borderWidth ????
     * @param color ??????
     * @return
     */
    public static Bitmap addFrameBitmap(Bitmap srcBitmap,int borderWidth,int color)
    {
        if (srcBitmap == null){
            Log.e(TAG, "the srcBitmap or borderBitmap is null");
            return null;
        }

        int newWidth = srcBitmap.getWidth() + borderWidth ;
        int newHeight = srcBitmap.getHeight() + borderWidth ;

        Bitmap outBitmap = Bitmap.createBitmap(newWidth, newHeight, Config.ARGB_8888);

        Canvas canvas = new Canvas(outBitmap);

        Rect rec = canvas.getClipBounds();
        rec.bottom--;
        rec.right--;
        Paint paint = new Paint();
        //??????
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        //??????
        paint.setStrokeWidth(borderWidth);
        canvas.drawRect(rec, paint);

        canvas.drawBitmap(srcBitmap, borderWidth/2, borderWidth/2, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        if (srcBitmap !=null && !srcBitmap.isRecycled()){
            srcBitmap.recycle();
            srcBitmap = null;
        }

        return outBitmap;
    }

}