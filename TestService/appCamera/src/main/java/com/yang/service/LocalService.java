package com.yang.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Binder;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;


import com.jingshuai.appcommonlib.camera.CameraEngine;
import com.yang.handle.CameraHolder;
import com.yang.testservice.MainActivity;
import com.yang.testservice.R;
import com.jingshuai.appcommonlib.util.Storage;


import java.io.IOException;
import java.util.Date;
import java.util.List;

public class LocalService extends Service {

	private AlarmManager am = null;
	private Camera camera;

	private SurfaceView dummy;

	private final IBinder mBinder = new LocalBinder();

	private NotificationManager mNM;
	private final String NOTIFY_TEXT = "Service Notify";

	private int NOTIFICATION = R.string.local_service_started;

	private Surface mSurface;
	private SurfaceTexture mSurfaceTexture;
	private Boolean enableCameraFlag = true;

	private MonitoHomeReceiver mMonitoHomeReceiver;
	private long startTime = new Date().getTime();
	private CameraHolder mCameraHolder ;

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		public LocalService getService() {
			return LocalService.this;
		}

	}

	@Override
	public void onCreate() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		String text = getString(R.string.local_service_started);
		showNotification(text);
		init();
	}

	private void init() {


		camera = openFacingBackCamera();
		mSurfaceTexture = new SurfaceTexture(344);
		mSurface = new Surface(mSurfaceTexture);
		mCameraHolder = new CameraHolder(mSurface);

		// 注册广播
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.vegetables_source.alarm");

		mMonitoHomeReceiver = new MonitoHomeReceiver();
		IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		registerReceiver(mMonitoHomeReceiver, homeFilter);
		Intent intent = new Intent();
		intent.setAction("com.vegetables_source.alarm");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);



	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("LocalService", "Received start id " + startId + ": " + intent);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		mNM.cancel(NOTIFICATION);

		cancelAlertManager();

		if (camera != null) {
			camera.setPreviewCallback(null);
			camera.release();
			camera = null;
		}

		Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}



	private void showNotification(String message) {
		CharSequence text = message;
		Notification.Builder builder = new Notification.Builder(this).setTicker(text)
				.setSmallIcon(R.drawable.ic_launcher);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		Notification notification = builder.setContentIntent(contentIntent).setContentTitle(NOTIFY_TEXT).setContentText(text).build();
		notification.tickerText = "Message received!";

		notification.when = System.currentTimeMillis();
		mNM.notify(NOTIFICATION, notification);
	}

	private void cancelAlertManager() {
		Intent intent = new Intent();
		intent.setAction("com.vegetables_source.alarm");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		am.cancel(pi);

		// 注销广播
		unregisterReceiver(mMonitoHomeReceiver);
	}


	public class MonitoHomeReceiver extends BroadcastReceiver {
		final String HOME_DIALOG_REASON = "homereason";
		final String HOME_DIALOG_REASON_HOME = "homekey";


		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("LocalService", "on MonitoHomeReceiver  onReceive" );
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				long currentTime = new Date().getTime();
				if(currentTime - startTime > 15000){
					enableCameraFlag = true;
				}
				if (camera != null && enableCameraFlag) {
					enableCameraFlag = false;
					Log.i("LocalService", "on MonitoHomeReceiver open camera" );

					try {
						startTime = new Date().getTime();
						//setupPreview(context,camera);
						camera.setPreviewDisplay(mCameraHolder);
						camera.startPreview();
						camera.setPreviewCallback(new PhotoHandlerCallback(
								getApplicationContext()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	private void setupPreview(Context context,Camera camera ){
		Camera.Parameters params = camera.getParameters();

		List<Camera.Size> supportedPictureSizes
				= SupportedSizesReflect.getSupportedPictureSizes(params);
		List<Camera.Size> supportedPreviewSizes
				= SupportedSizesReflect.getSupportedPreviewSizes(params);

		if ( supportedPictureSizes != null &&
				supportedPreviewSizes != null &&
				supportedPictureSizes.size() > 0 &&
				supportedPreviewSizes.size() > 0) {

			//2.x
			Camera.Size pictureSize = supportedPictureSizes.get(0);

			int maxSize = 1280;
			if(maxSize > 0){
				for(Camera.Size size : supportedPictureSizes){
					if(maxSize >= Math.max(size.width,size.height)){
						pictureSize = size;
						break;
					}
				}
			}

			WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Display display = windowManager.getDefaultDisplay();
			DisplayMetrics displayMetrics = new DisplayMetrics();
			display.getMetrics(displayMetrics);

			Camera.Size previewSize = getOptimalPreviewSize(
					supportedPreviewSizes,
					display.getWidth(),
					display.getHeight());

			params.setPictureSize(pictureSize.width, pictureSize.height);
			params.setPreviewSize(previewSize.width, previewSize.height);

			params.set("rotation","90");

		}
		camera.setParameters(params);
		try {
			camera.setPreviewDisplay(mCameraHolder);
			camera.startPreview();
			camera.setPreviewCallback(new PhotoHandlerCallback(
					getApplicationContext()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
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

	private Camera openFacingBackCamera() {
		Camera cam = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

		for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				try {
					cam = Camera.open(camIdx);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}

		return cam;
	}

	private class PhotoHandlerCallback implements Camera.PreviewCallback {

		private final Context context;
		private final String TAG="PhotoHandler";
		private long checkTime = new Date().getTime();

		private  boolean mIsSave = true;
		public PhotoHandlerCallback(Context context) {
			this.context = context;
		}

		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {

			try {
				Log.i(TAG,"onPreviewFrame=");
				long currentTime = new Date().getTime();
				final Camera lmcamera = camera;
				byte[] yuvdata = data;
				if(currentTime - startTime > 10000){
                    camera.stopPreview();
					enableCameraFlag = true;
					showNotification("message done");
                    return;
                }
				if(currentTime - checkTime > 200 || mIsSave){
                    mIsSave = false;
                    checkTime = currentTime;
                    Log.v(TAG,"save jpeg size"+yuvdata.length);
                    String fileName = System.currentTimeMillis() + ".jpeg";
					//byte[] yuvdata90 = CameraEngine.rotateYUV420Degree90(yuvdata,1280,720);
					mIsSave = Storage.savePicture(yuvdata, fileName,
                            lmcamera.getParameters().getPreviewSize(),
                            90);
                    yuvdata=null;
                }
				} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}



}
