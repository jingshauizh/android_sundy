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
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import com.jingshuai.appcommonlib.camera.CameraEngine;
import com.jingshuai.appcommonlib.log.MLog;
import com.jingshuai.appcommonlib.util.Storage;
import com.yang.conf.CameraConfig;
import com.yang.handle.CameraHolder;
import com.yang.testservice.MainActivity;
import com.yang.testservice.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CameraService extends Service {
	private final String LOGTAG="CameraService";

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

	private int picQuality;
	private int pictureHeight;
	private int pictureWidth;
	private int videoLength;
	private int savedCameraNo;
	private String shutterName;
	private CameraConfig mCameraConfig;
	private final Integer SECOND_COUNT = 1000;

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		public CameraService getService() {
			return CameraService.this;
		}

	}

	@Override
	public void onCreate() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		String text = getString(R.string.local_service_started);
		showNotification(text);
		mCameraConfig = new CameraConfig();
		resetParameter();
		init();
	}

	private void resetParameter(){

		SharedPreferences localSharedPreferences = getSharedPreferences("widefide.cameraPrefs2", 0);
		mCameraConfig.setmQuality(localSharedPreferences.getInt("savedQuality", 85));
		mCameraConfig.setmWidth(localSharedPreferences.getInt("savedWidth", 1600));
		mCameraConfig.setmHeight(localSharedPreferences.getInt("savedHeight", 1200));
		mCameraConfig.setmVideoLength(localSharedPreferences.getInt("savedVLength", 18000000));
		mCameraConfig.setmCameraNo(localSharedPreferences.getInt("savedCameraPos", 0));
		mCameraConfig.setmShutterName(localSharedPreferences.getString("savedShutterName", "Click Me"));

		// to do if camera no changed ,reopen camera

		int pos = localSharedPreferences.getInt("savedPicCountPerSecond", 2);
		int _PicCountPerSecond = 0;
		if(pos == 0){
			_PicCountPerSecond = 2;
		}
		if(pos == 1){
			_PicCountPerSecond = 3;
		}
		if(pos == 2){
			_PicCountPerSecond = 5;
		}
		if(pos == 3){
			_PicCountPerSecond = 10;
		}
		mCameraConfig.setmPicsPerSec(_PicCountPerSecond);

		pos = localSharedPreferences.getInt("savedPicTakeTimeLength", 0);
		int _PicTakeSec = 0;
		if(pos == 0){
			_PicTakeSec = 10;
		}
		if(pos == 1){
			_PicTakeSec = 20;
		}
		if(pos == 2){
			_PicTakeSec = 300;
		}
		if(pos == 3){
			_PicTakeSec = 600;
		}
		if(pos == 4){
			_PicTakeSec = 3600;
		}
		mCameraConfig.setmPicTakeSec(_PicTakeSec);
	}

	private void init() {

		CameraEngine.openCamera(mCameraConfig.getmCameraNo());
		camera = CameraEngine.getCamera();
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
		Log.i(LOGTAG, "Received start id " + startId + ": " + intent);
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

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(LOGTAG, "on MonitoHomeReceiver  onReceive" );
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				long currentTime = new Date().getTime();
				if(currentTime - startTime > 15000){
					enableCameraFlag = true;
				}
				if (camera != null && enableCameraFlag) {
					enableCameraFlag = false;
					Log.i(LOGTAG, "on MonitoHomeReceiver open camera" );

					try {
						startTime = new Date().getTime();
						resetParameter();
						CameraEngine.startPreview(mCameraHolder,new PhotoHandlerCallback(
								getApplicationContext()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	private class PhotoHandlerCallback implements Camera.PreviewCallback {

		private final Context context;
		private final String TAG="PhotoHandlerCallback";
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
				if(currentTime - startTime > mCameraConfig.getmPicTakeSec()*SECOND_COUNT){
                    camera.stopPreview();
					enableCameraFlag = true;
					showNotification("message done");
                    return;
                }
				int interval_milsecond = SECOND_COUNT/mCameraConfig.getmPicsPerSec();
				if(currentTime - checkTime > interval_milsecond && mIsSave){
                    mIsSave = false;
                    checkTime = currentTime;
					String fileName = "IMG_"
							+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
							.toString() + ".jpg";
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
