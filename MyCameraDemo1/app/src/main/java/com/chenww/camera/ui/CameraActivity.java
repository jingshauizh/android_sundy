package com.chenww.camera.ui;
/**
 * 用来拍照的Activity
 * @author Chenww
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

public class CameraActivity extends Activity {
	private final String TAG="Demo";
	private SurfaceHolder myHolder;
	private Camera myCamera;
	private Boolean Camera_Open_flag = false;
	private Boolean Preview_Flag = false;
	private List<byte[]> dataList;
	private long checkTime = new Date().getTime();
	private long startTime = new Date().getTime();
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

		//设置布局
		setContentView(R.layout.activity_camera_no_view);
		mSurfaceTexture = new SurfaceTexture(344);
		mSurface = new Surface(mSurfaceTexture);

		Log.d("Demo", "oncreate");

		//初始化surface
		initSurface();
		dataList = new ArrayList<byte[]>();

		//这里得开线程进行拍照，因为Activity还未完全显示的时候，是无法进行拍照的，SurfaceView必须先显示
		//startCapture();

	}

	//初始化surface
	@SuppressWarnings("deprecation")
	private void initSurface()
	{
		myHolder = new CameraHoilder();

	}

	private void startCapture()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				//初始化camera并对焦拍照
				initCamera();
			}
		}).start();
	}

	//初始化摄像头
	private void initCamera() {

		//如果存在摄像头
		if(checkCameraHardware(getApplicationContext()))
		{
			//获取摄像头（首选前置，无前置选后置）
			if(openFacingFrontCamera())
			{
				Log.d("Demo", "openCameraSuccess");
				//进行对焦
				autoFocus();
			}
			else {
				Log.d("Demo", "openCameraFailed");
			}

		}
	}

	//对焦并拍照
	private void autoFocus() {

		try {
			//因为开启摄像头需要时间，这里让线程睡两秒
			if(!Camera_Open_flag){
				Thread.sleep(2000);
				//自动对焦

			}
			myCamera.autoFocus(myAutoFocus);
			//对焦后拍照
			Camera.Parameters mParameters = myCamera.getParameters();
			mParameters.setPreviewSize(1280, 720);
			myCamera.setParameters(mParameters);//
			myCamera.setPreviewCallback(new JpegPreviewCallback());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	//判断是否存在摄像头
	private boolean checkCameraHardware(Context context) {

		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			// 设备存在摄像头
			return true;
		} else {
			// 设备不存在摄像头
			return false;
		}

	}

	//得到后置摄像头
	private boolean openFacingFrontCamera() {

		//尝试开启开启后置摄像头
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		if(!Camera_Open_flag){
			for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
				Camera.getCameraInfo(camIdx, cameraInfo);
				if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
					try {
						Log.d("Demo", "tryToOpenCamera");
						myCamera = Camera.open(camIdx);
						Camera_Open_flag = true;
					} catch (RuntimeException e) {
						e.printStackTrace();
						return false;
					}
				}
			}

			//如果开启开启后置失败（无前置）则开启前置
			if (myCamera == null) {
				for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
					Camera.getCameraInfo(camIdx, cameraInfo);
					if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
						try {
							myCamera = Camera.open(camIdx);
							Camera_Open_flag = true;
						} catch (RuntimeException e) {
							return false;
						}
					}
				}
			}
			try {
				//这里的myCamera为已经初始化的Camera对象
				myCamera.setPreviewDisplay(myHolder);
			} catch (IOException e) {
				e.printStackTrace();
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
			}
			myCamera.startPreview();
			Preview_Flag = true;
		}



		return true;
	}

	//自动对焦回调函数(空实现)
	private AutoFocusCallback myAutoFocus = new AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
		}
	};

	//拍照成功回调函数
	private PictureCallback myPicCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
		}
	};

	class JpegPreviewCallback  implements Camera.PreviewCallback {
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			//Toast.makeText(getApplicationContext(), "take", 0).show();
			final byte[] yuvdata = data;
			long currentTime = new Date().getTime();
			if(currentTime - startTime > 10000){
//				myCamera.stopPreview();
//				myCamera.release();
//				myCamera = null;
				return;
			}
			if(currentTime - checkTime > 200 && yuvdata != null){
				checkTime = currentTime;
				Thread save = new Thread(new Runnable() {
					@Override
					public void run() {
						Log.v(TAG,"save jpeg size"+yuvdata.length);
						String fileName = System.currentTimeMillis() + ".jpeg";
						boolean mIsSave = Storage.savePicture(yuvdata, fileName,
								myCamera.getParameters().getPreviewSize(),
								90);
						if(!mIsSave){
							Log.v(TAG,"save fail");
						}
					}
				});
				save.start();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
			//do something here
			startCapture();
			startTime = new Date().getTime();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class CameraHoilder implements SurfaceHolder{
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
}
