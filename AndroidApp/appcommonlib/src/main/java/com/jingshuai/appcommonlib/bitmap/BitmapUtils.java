package com.jingshuai.appcommonlib.bitmap;

/**
 * Created by eqruvvz on 1/3/2018.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LvStudio on 2016/11/7.
 */

/**
 * Created by LvStudio on 2016/11/7.
 */

public class BitmapUtils {

    /**
     * ??????????????????
     *
     * @param context
     * @param image   Bitmap??
     * @return
     */
    public static Bitmap comp(Context context, Bitmap image) {
        int maxLength = 1024 * 1024; // ????????????byte
        // ????
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ?????????100????????????????baos?
        int options = 100;
        while (baos.toByteArray().length > maxLength) { // ???????????
            options -= 10;// ?????10
            baos.reset();// ??baos???baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//PNG ??options%
        }
        // ????
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options opts = new BitmapFactory.Options(); // ????(????????)
        opts.inJustDecodeBounds = true; // ????, ?????
        BitmapFactory.decodeStream(bais, null, opts);// ????(???????)
        // ????????????
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int scaleX = opts.outWidth / manager.getDefaultDisplay().getWidth(); // X?????(????/????)
        int scaleY = opts.outHeight / manager.getDefaultDisplay().getHeight(); // Y?????
        int scale = scaleX > scaleY ? scaleX : scaleY; // ???????(X?Y??????)

        opts.inJustDecodeBounds = false; // ????, ??????
        opts.inSampleSize = scale > 1 ? scale : 1; // ????, ??????????
        return BitmapFactory.decodeStream(bais, null, opts); // ????(????????)
    }

    /**
     * ??????????????????
     *
     * @param context
     * @param path    ?????
     * @return
     */
    public static Bitmap comp(Context context, String path) {
        return compressImage(getUsableImage(context, path));
    }

    /**
     * ????????Bitmap
     *
     * @param context
     * @param path    ?????
     * @return
     */
    public static Bitmap getUsableImage(Context context, String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options(); // ????(????????)
        opts.inJustDecodeBounds = true; // ????, ?????
        BitmapFactory.decodeFile(path, opts); // ????(???????)
        DisplayMetrics metrics = new DisplayMetrics();
        metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        int scaleX = opts.outWidth / metrics.widthPixels; // X?????(????/????)
        int scaleY = opts.outHeight / metrics.heightPixels; // Y?????
        int scale = scaleX > scaleY ? scaleX : scaleY; // ???????(X?Y??????)

        opts.inJustDecodeBounds = false; // ????, ??????
        opts.inSampleSize = scale > 1 ? scale : 1; // ????, ??????????
        return BitmapFactory.decodeFile(path, opts); // ????(????????)
    }

    /**
     * ?????????????
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        int maxLength = 1024 * 1024; // (byte)

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ?????????100????????????????baos?
        int options = 100;
        while (baos.toByteArray().length > maxLength) { // ???????????????1mb,??????
            options -= 10;// ?????10
            baos.reset();// ??baos???baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ????options%???????????baos?
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ???????baos???ByteArrayInputStream?
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ?ByteArrayInputStream??????
        return bitmap;
    }

    /**
     * ????????????????
     *
     * @param fromFile
     * @param toFile
     * @param reqWidth
     * @param reqHeight
     * @param quality
     */
    public static void transImage(String fromFile, String toFile, int reqWidth, int reqHeight, int quality) {
        Bitmap bitmap = BitmapFactory.decodeFile(fromFile);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        // ?????
        float scaleWidth = (float) reqWidth / bitmapWidth;
        float scaleHeight = (float) reqHeight / bitmapHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // ??????Bitmap??
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
        // ?????
        bitmap2File(toFile, quality, resizeBitmap);
        if (!bitmap.isRecycled()) {
            // ????????OOM
            bitmap.recycle();
        }
        if (!resizeBitmap.isRecycled()) {
            resizeBitmap.recycle();
        }
    }


    /**
     * Bitmap?????
     *
     * @param toFile
     * @param quality
     * @param bitmap
     * @return
     */
    public static File bitmap2File(String toFile, int quality, Bitmap bitmap) {
        File captureFile = new File(toFile);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(captureFile);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return captureFile;
    }

    /**
     * Drawable???Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // ????????????????View??surfaceview??canvas.drawBitmap?????
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    // Bitmap?Drawable?InputStream?byte[] ????

    /**********************************************************/
    // 1. Bitmap to InputStream
    public static InputStream bitmap2Input(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public static InputStream bitmap2Input(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    // 2. Bitmap to byte[]
    public static byte[] bitmap2ByteArray(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, baos);
        return baos.toByteArray();
    }

    public static byte[] bitmap2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    // 3. Drawable to byte[]
    public static byte[] drawable2ByteArray(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        return out.toByteArray();
    }

    // 4. byte[] to Bitmap
    public static Bitmap byteArray2Bitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}