package com.chenww.camera.ui.replace;
/**
 * 用来拍照的Activity
 * @author Chenww
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.chenww.camera.ui.R;
import com.chenww.camera.ui.Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CameraActivity extends Activity {
	private final String TAG="Demo";
	private SurfaceView mySurfaceView;
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
		setContentView(R.layout.activity_camera);
		mSurfaceTexture = new SurfaceTexture(344);
		mSurface = new Surface(mSurfaceTexture);

		Log.d("Demo", "oncreate");

		//初始化surface
		initSurface();
		dataList = new ArrayList<byte[]>();

		//这里得开线程进行拍照，因为Activity还未完全显示的时候，是无法进行拍照的，SurfaceView必须先显示
		new Thread(new Runnable() {
			@Override
			public void run() {
				//初始化camera并对焦拍照
				initCamera();
			}
		}).start();

	}

	//初始化surface
	@SuppressWarnings("deprecation")
	private void initSurface()
	{
		//初始化surfaceview
		mySurfaceView = (SurfaceView) findViewById(R.id.camera_surfaceview);

		//初始化surfaceholder
		myHolder = mySurfaceView.getHolder();

		//myHolder = new CameraHoilder();
		myHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);



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
			//mParameters.setPictureSize(1024, 768);
			//mParameters.setPictureSize(1024, 768);
			mParameters.setPreviewSize(1280, 720);
			myCamera.setParameters(mParameters);
//			for(int i=0;i<10;i++){
//				Thread.sleep(1500);
//				if(Preview_Flag){
//					try {
//						myCamera.takePicture(null, null, myPicCallback);
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
			myCamera.setPreviewCallback(new JpegPreviewCallback());
			//myCamera.takePicture(null, null, myPicCallback);

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
			//完成拍照后关闭Activity
			//CameraActivity.this.finish();
//			dataList.add(data);
//			if(dataList.size() >5){
//				saveData();
//			}
//			Preview_Flag = true;

		}
	};

	class JpegPreviewCallback  implements Camera.PreviewCallback {
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			//Toast.makeText(getApplicationContext(), "take", 0).show();
			final byte[] yuvdata = data;
			long currentTime = new Date().getTime();
			if(currentTime - startTime > 10000){
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
				CameraActivity.this.finish();
				return;
			}
			if(currentTime - checkTime > 200 && yuvdata != null){
				checkTime = currentTime;
				Thread save = new Thread(new Runnable() {
					@Override
					public void run() {
						Log.v(TAG,"save jpeg size"+yuvdata.length);
						//saveData(yuvdata);
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

	private void saveData(){
		Preview_Flag = false;
		//将得到的照片进行270°旋转，使其竖直
		for (byte [] data:dataList ) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			Matrix matrix = new Matrix();
			matrix.preRotate(90);
			bitmap = Bitmap.createBitmap(bitmap ,0,0, bitmap .getWidth(), bitmap .getHeight(),matrix,true);

			//创建并保存图片文件
			String filename =String.valueOf( new Date().getTime());
			File pictureFile = new File(getDir(), filename+"ca.jpg");
			try {
				Log.i("Demo","pictureFile="+pictureFile.getAbsolutePath());
				FileOutputStream fos = new FileOutputStream(pictureFile);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
				fos.close();
			} catch (Exception error) {
				Toast.makeText(CameraActivity.this, "fail", Toast.LENGTH_SHORT).show();
				Log.d("Demo", "保存照片失败" + error.toString());
				error.printStackTrace();
			}

			Log.d("Demo", "获取照片成功");
			Toast.makeText(CameraActivity.this, "done", Toast.LENGTH_SHORT).show();
			//myCamera.startPreview();
			Preview_Flag = true;
		}

	}

	private void saveData(byte [] data){
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		Matrix matrix = new Matrix();
		matrix.preRotate(90);
		bitmap = Bitmap.createBitmap(bitmap ,0,0, bitmap .getWidth(), bitmap .getHeight(),matrix,true);

		//创建并保存图片文件
		String filename =String.valueOf( new Date().getTime());
		File pictureFile = new File(getDir(), filename+"ca.jpg");
		try {
			Log.i("Demo","pictureFile="+pictureFile.getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(pictureFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (Exception error) {
			Toast.makeText(CameraActivity.this, "fail", Toast.LENGTH_SHORT).show();
			Log.d("Demo", "保存照片失败" + error.toString());
			error.printStackTrace();
		}
		Log.d("Demo", "获取照片成功");
		Toast.makeText(CameraActivity.this, "done", Toast.LENGTH_SHORT).show();
	}

	//获取文件夹
	private File getDir()
	{
		//得到SD卡根目录
		File dir = Environment.getExternalStorageDirectory();
		dir = new File("/storage/sdcard1/11test/");
		if (dir.exists()) {
			return dir;
		}
		else {
			dir.mkdirs();
			return dir;
		}
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
