package jings.ex.android.com.customuiapp.bigimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jings on 2020/6/28.
 */

public class BigImageViewJ  extends View implements GestureDetector.OnGestureListener,View.OnTouchListener {

    private final static String LOG_TAG = BigImageViewJ.class.getName();
    private int regionLeft = 0;
    private int regionTop = 0;
    private int regionRight = 0;
    private int regionBottom = 0;

    private int mImageWidth;
    private int mImageHeight;


    private float moveStartX = 0;
    private float moveStartY = 0;
    private final static float MINI_MOVE_DISTANCE = 30;
    private boolean movestartFlag = false;
    private float moveDistance = 0;

    private float mScale;
    private int viewWidth = 0;
    private int viewHeight = 0;
    private Rect mRect = null;
    private BitmapRegionDecoder mBitmapRegionDecoder;
    private BitmapFactory.Options mOptions;
    private Bitmap mbitMap;
    private  Matrix mMatrix;
    public BigImageViewJ(Context context) {
        super(context);
    }

    public BigImageViewJ(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BigImageViewJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BigImageViewJ(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context){
        setOnTouchListener(this);
        mOptions = new BitmapFactory.Options();
    }

    public void setImageInStream(InputStream is){
        try {
            mOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, mOptions);

            mImageWidth = mOptions.outWidth;
            mImageHeight = mOptions.outHeight;
            Log.v("aaa","onMeasure mImageWidth="+mImageWidth);

            mOptions.inMutable = true;
            mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

            mOptions.inJustDecodeBounds = false;

            mMatrix = new Matrix();
            mBitmapRegionDecoder = BitmapRegionDecoder.newInstance(is,false);
           // requestLayout();


        } catch (IOException e) {
            Log.v(LOG_TAG,"onMeasure IOException="+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        Log.v(LOG_TAG,"onMeasure viewWidth="+viewWidth);
        Log.v(LOG_TAG,"onMeasure mImageWidth="+mImageWidth);
        mScale = viewWidth/(float)mImageWidth;
        Log.v(LOG_TAG,"onMeasure mScale="+mScale);


        regionLeft = 0;
        regionTop = 0;
        regionRight = regionLeft + viewWidth;
        regionBottom = (int) (viewHeight/mScale);
        mRect = new Rect(regionLeft, regionTop, regionRight, regionBottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if(null == mBitmapRegionDecoder){
                return;
            }
            mOptions.inBitmap = mbitMap;
            mbitMap = mBitmapRegionDecoder.decodeRegion(mRect,mOptions);
            Matrix matrix = new Matrix();
            matrix.postScale(mScale,mScale);
            canvas.drawBitmap(mbitMap,matrix,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                moveStartX = event.getX();
                moveStartY = event.getY();
                movestartFlag = true;
                break;

            case MotionEvent.ACTION_MOVE:
                float  moveEndX = event.getX();
                float  moveEndY = event.getY();
                float daltarDisX = Math.abs(moveEndX - moveStartX) ;
                float daltarDisY = Math.abs(moveEndY - moveStartY) ;
                Log.v(LOG_TAG,"daltarDisY="+daltarDisY);
                if(daltarDisY > daltarDisX && MINI_MOVE_DISTANCE < daltarDisX){
                    moveDistance = moveStartY  - moveEndY ;

                    Log.v(LOG_TAG,"moveDistance="+moveDistance);
                    if(movestartFlag){
                        computeRegionRect(moveDistance);
                        invalidate();
                        movestartFlag = false;
                    }

                }
                break;

            case MotionEvent.ACTION_UP:
                movestartFlag = false;
                break;
        }

        return true;
    }

    private void computeRegionRect(float moveDistance){
        regionLeft = 0;
        regionTop += moveDistance;
        regionRight = regionLeft + viewWidth;
        regionBottom += moveDistance;
        Log.v(LOG_TAG,"regionTop="+regionTop);
        Log.v(LOG_TAG,"regionBottom="+regionBottom);
        mRect = new Rect(regionLeft, regionTop, regionRight, regionBottom);
    }
}
