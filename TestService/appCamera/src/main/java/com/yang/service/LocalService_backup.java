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
import android.hardware.Camera;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.yang.handle.PhotoHandler;
import com.yang.testservice.MainActivity;
import com.yang.testservice.R;

public class LocalService_backup extends Service {

	private AlarmManager am = null;
	private Camera camera;

	private final IBinder mBinder = new LocalBinder();

	private NotificationManager mNM;

	private int NOTIFICATION = R.string.local_service_started;

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		public LocalService_backup getService() {
			return LocalService_backup.this;
		}

	}

	@Override
	public void onCreate() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
		init();
	}

	private void init() {
		am = (AlarmManager) getSystemService(ALARM_SERVICE);

		camera = openFacingBackCamera();

		// 注册广播
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.vegetables_source.alarm");
		registerReceiver(alarmReceiver, filter);

		Intent intent = new Intent();
		intent.setAction("com.vegetables_source.alarm");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);

		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 10, pi);// 马上开始，每1分钟触发一次
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

	/**
	 * Show a notification while this service is running.
	 */
	private void showNotification() {
		CharSequence text = getText(R.string.local_service_started);

		Notification notification = new Notification(R.drawable.stat_running,
				text, System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

//		notification.setLatestEventInfo(this,
//				getText(R.string.local_service_label), text, contentIntent);

		mNM.notify(NOTIFICATION, notification);
	}

	private void cancelAlertManager() {
		Intent intent = new Intent();
		intent.setAction("com.vegetables_source.alarm");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		am.cancel(pi);

		// 注销广播
		unregisterReceiver(alarmReceiver);
	}

	BroadcastReceiver alarmReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if ("com.vegetables_source.alarm".equals(intent.getAction())) {

				if (camera != null) {
					SurfaceView dummy = new SurfaceView(getBaseContext());
					try {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						camera.setPreviewDisplay(dummy.getHolder());
						camera.startPreview();
//						camera.takePicture(null, null, new PhotoHandler(
//								getApplicationContext()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		}
	};

	private Camera openFacingBackCamera() {
		Camera cam = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		;
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
}
