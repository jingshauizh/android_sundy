package org.example.camera;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.YuvImage;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SVDraw extends SurfaceView implements SurfaceHolder.Callback {

	protected SurfaceHolder mSurfaceHolder;
	private int mWidth,mHeight;
	private Context mContext;
	private static final int EXIT = 0;
	private static final int NOTEXIT = 1;
	private  int exit = NOTEXIT;
	
	public SVDraw(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		setZOrderOnTop(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanDraw(){
		exit = NOTEXIT;
		Canvas canvas = mSurfaceHolder.lockCanvas();
		canvas.drawColor(Color.TRANSPARENT);
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}
	
	public void drawPicture(byte[] data,Size previewSize){
		
		exit = EXIT;
		
		mWidth = getWidth();
		mHeight = getHeight();
		Canvas canvas = mSurfaceHolder.lockCanvas();
		//canvas.
		canvas.drawColor(Color.TRANSPARENT);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, previewSize.width,previewSize.height, null);
		yuvImage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 50, out);
		byte[] imageBytes = out.toByteArray();
		Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
		
		Matrix matrix=new Matrix();
        matrix.postScale(0.2f, 0.2f);
        matrix.postRotate(90);
        Bitmap dstbmp=Bitmap.createBitmap(image,0,0,image.getWidth(),
        		image.getHeight(),matrix,true);
		
		canvas.drawBitmap(dstbmp, 10, 10, null);
		
		//此处为添加网格的代码
//		Paint p = new Paint();
//		p.setAntiAlias(true);
//		p.setColor(Color.RED);
//		p.setStyle(Style.STROKE);
//		mWidth = getWidth();
//		mHeight = getHeight();
//		
//		// draw  行
//		canvas.drawLine(0, mHeight/3, mWidth, mHeight/3, p);
//		canvas.drawLine(0, mHeight/3*2, mWidth, mHeight/3*2, p);
//		
//		// draw 列
//		canvas.drawLine(mWidth/3, 0, mWidth/3, mHeight, p);
//		canvas.drawLine(mWidth/3*2, 0, mWidth/3*2, mHeight, p);
		
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}
	
	public boolean getExit(){
		return (exit == EXIT);
	}
	
//	static public void decodeYUV420SP(int[] rgba, byte[] yuv420sp, int width,
//		    int height) {
//		final int frameSize = width * height;
//
//		for (int j = 0, yp = 0; j < height; j++) {
//		    int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
//		    for (int i = 0; i < width; i++, yp++) {
//		        int y = (0xff & ((int) yuv420sp[yp])) - 16;
//		        if (y < 0)
//		            y = 0;
//		        if ((i & 1) == 0) {
//		            v = (0xff & yuv420sp[uvp++]) - 128;
//		            u = (0xff & yuv420sp[uvp++]) - 128;
//		        }
//
//		        int y1192 = 1192 * y;
//		        int r = (y1192 + 1634 * v);
//		        int g = (y1192 - 833 * v - 400 * u);
//		        int b = (y1192 + 2066 * u);
//
//		        if (r < 0)
//		            r = 0;
//		        else if (r > 262143)
//		            r = 262143;
//		        if (g < 0)
//		            g = 0;
//		        else if (g > 262143)
//		            g = 262143;
//		        if (b < 0)
//		            b = 0;
//		        else if (b > 262143)
//		            b = 262143;
//
//		        // rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) &
//		        // 0xff00) | ((b >> 10) & 0xff);
//		        // rgba, divide 2^10 ( >> 10)
//		        rgba[yp] = ((r << 14) & 0xff000000) | ((g << 6) & 0xff0000)
//		                | ((b >> 2) | 0xff00);
//		    }
//		}
//	}


}
