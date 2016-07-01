package org.example.camera;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MyCamera20130206Activity extends Activity implements SurfaceHolder.Callback,OnClickListener{
    /** Called when the activity is first created. */
	private static final String TAG = "Main";
	
	private FrameLayout mFrameLayout;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private Camera mCamera,mCamera1;
	private CameraInfo[] mCameraInfo;
	private int mNumberOfCamera;
	private int mBackCameraId = -1,mFrontCameraId = -1;
	private Parameters mParameters;
	
	private int mCameraStatus;
	private final static int STOP_PREVIEW = 0;
	private final static int START_PREVIEW = 1;
	
	private Context mContext;
	
	//for test
	public Button takePictureButton;
	String fileName = null;
	public Button autofocusButton;
	public int pictureOrientation = 0;
	
	private ContentResolver mContentResolver;
	
	private SVDraw mSvDraw;
	
	int mSVDrawtime;
	//for test
	
	//if too much work to do in CameraStartUp can do it in this thread
	private class CameraStartUpThread extends Thread{
		public void run(){
		}
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.v(TAG,"onCreate");
        mContext = this;
        initView();
        initCamera();
        initSurfaceHolder();
    }

    @Override
	protected void onResume() {
    	Log.v(TAG,"onresume");
		super.onResume();

	}
    


	@Override
	protected void onPause() {
		Log.v(TAG,"onpause");
		stopPreview();
		releaseCamera();
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.v(TAG,"stop");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void initView(){
		Log.v(TAG,"initView");
	
    	mFrameLayout = (FrameLayout)findViewById(R.id.cameraframe);
        mSurfaceView = (SurfaceView)findViewById(R.id.camera_screen);
        
        // for test
        takePictureButton = (Button)findViewById(R.id.takepicture);
        takePictureButton.setOnClickListener(this);
        autofocusButton = (Button)findViewById(R.id.setpic);
        autofocusButton.setOnClickListener(this);
        
        mSvDraw = (SVDraw)findViewById(R.id.small_camera_screen);
        
        // for test
    }
	public void initCamera(){
		Log.v(TAG,"initCamera");
		setCameraInfo();
		setCameraId();
	}
    
    public void initSurfaceHolder(){
    	Log.v(TAG,"initSurafaceHolder");
    	mSurfaceHolder = mSurfaceView.getHolder();
    	mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    	mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(this);
    }
    
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		openCamera();
		prepareCamera();
		setDisplayOrientation(mContext);
		setParameters();
		startPreview();
		mParameters = mCamera.getParameters();
		Log.v(TAG,""+mParameters.flatten());
		
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		stopPreview();
		releaseCamera();
		
	}
	private void setCameraInfo() {
		Log.v(TAG,"setCameraInfo");
		mNumberOfCamera = Camera.getNumberOfCameras();
		Log.v(TAG,""+mNumberOfCamera);
		if(mNumberOfCamera == 0){
			Toast.makeText(getApplicationContext(), "not camera ", 0).show();
			//finish();
		}
		mCameraInfo = new CameraInfo[mNumberOfCamera];
		for(int i=0;i<mNumberOfCamera;i++){
			mCameraInfo[i] = new CameraInfo();
			Camera.getCameraInfo(i, mCameraInfo[i]);
		}
	}
	private void setCameraId() {
		Log.v(TAG,"setCameraId");
		for(int i=0;i<mNumberOfCamera;i++){
			if(mBackCameraId == -1 && mCameraInfo[i].facing == CameraInfo.CAMERA_FACING_BACK){
				mBackCameraId = i;
			}else if(mFrontCameraId == -1 && mCameraInfo[i].facing == CameraInfo.CAMERA_FACING_FRONT){
				mFrontCameraId = i;
			}
		}
	}
	//open back camera as default
	private void openCamera(){
		try{
		    mCamera = Camera.open(mBackCameraId);
//			mCamera = Camera.open(0);
		}catch (Exception e){
			Toast.makeText(this, "false", 0).show();
			Log.v(TAG,"open faild");
		}
	}
	private void prepareCamera(){
		try {
			mCamera.setPreviewDisplay(mSurfaceHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void startPreview(){
		mCamera.startPreview();
	}
	private void stopPreview(){
		if(mCameraStatus == START_PREVIEW)
			if(mCamera != null){
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
			}
			
	}
	private void releaseCamera(){
		if(mCamera != null){
			mCamera.release();
			mCamera = null;
		}
		if(mCamera1 != null){
			mCamera1.release();
			mCamera1 = null;
		}
	}
	private void setDisplayOrientation(Context context){
		int rotation = ((Activity) context).getWindowManager()
		           .getDefaultDisplay().getRotation();
		int degree = 0;
		switch (rotation) {
			case Surface.ROTATION_0:	degree = 0; break;
			case Surface.ROTATION_90:	degree = 90; break;
			case Surface.ROTATION_180:	degree = 180; break;
			case Surface.ROTATION_270:	degree = 270; break;
		}
		int result;
		CameraInfo info = new CameraInfo();
		//Camera.getCameraInfo(mBackCameraId, info);
		Camera.getCameraInfo(0, info);
		if(info.facing == CameraInfo.CAMERA_FACING_FRONT){
			result = (info.orientation + degree) % 360;
			result = (360 - result) % 360;
		}else{
			result =(info.orientation - degree + 360 ) % 360;
		}
		mCamera.setDisplayOrientation(result);
		Log.v(TAG,"result:"+result);
		pictureOrientation = result;
	}
	private void setParameters(){
		mParameters = mCamera.getParameters();
		mParameters.setPictureSize(1280, 768);
		mParameters.setPreviewSize(1280, 720);
		mCamera.setParameters(mParameters);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.takepicture:
			//mCamera.takePicture(null, null, null,new JpegPictureCallback());
			//Toast.makeText(getApplicationContext(), "before take", 0).show();
			//mCamera.setPreviewCallbackWithBuffer(new JpegPreviewCallback());
			mCamera.setPreviewCallback(new JpegPreviewCallback());
			
			break;

		case R.id.setpic:
			mCamera.autoFocus(null);
			break;
		}
		
	}
	
	class JpegPictureCallback implements Camera.PictureCallback{
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				camera.stopPreview();
				camera.startPreview();
				mCameraStatus = START_PREVIEW;
			} catch (Exception e) {
				mCameraStatus = STOP_PREVIEW;
			}
		}
	}
    class JpegPreviewCallback  implements PreviewCallback {
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			//Toast.makeText(getApplicationContext(), "take", 0).show();
			final byte[] yuvdata = data;
			if(mSVDrawtime == 1){
				mSvDraw.drawPicture(data,mCamera.getParameters().getPreviewSize());
				
			}else{
				mSVDrawtime = (mSVDrawtime+1)%3;
			}
//			Thread save = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					Log.v(TAG,"save jpeg size"+yuvdata.length);
//					fileName = System.currentTimeMillis() + ".jpeg";
//					boolean mIsSave = Storage.savePicture(yuvdata, fileName,
//							mCamera.getParameters().getPreviewSize(),
//							pictureOrientation);
//					if(!mIsSave){
//						Log.v(TAG,"save fail");
//					}
//				}
//			});
//			save.start();
//			mCamera.setPreviewCallback(null);
		}
    }

	//for storage
    private void getContentResoler(Context context){
    	mContentResolver = context.getContentResolver();
    }
    //for storage
}