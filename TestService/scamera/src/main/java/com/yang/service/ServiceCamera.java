package com.yang.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.Toast;

import com.yang.handle.CameraHolder;
import com.yang.handle.PhotoHandler;
import com.yang.testservice.MainActivity;
import com.yang.testservice.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceCamera extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.yang.service.action.FOO";
    private static final String ACTION_BAZ = "com.yang.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.yang.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.yang.service.extra.PARAM2";

    private AlarmManager am = null;
    private Camera camera;

    private SurfaceView dummy;


    private NotificationManager mNM;

    private int NOTIFICATION = R.string.local_service_started;

    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;


    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
        init();
        super.onCreate();
    }

    public ServiceCamera() {
        super("ServiceCamera");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ServiceCamera.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ServiceCamera.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    private void startCamera(){

        if (camera != null) {

            mSurfaceTexture = new SurfaceTexture(344);
            mSurface = new Surface(mSurfaceTexture);
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CameraHolder mCameraHolder = new CameraHolder(mSurface);
                camera.setPreviewDisplay(mCameraHolder);
                camera.startPreview();
                camera.setPreviewCallback(new PhotoHandler(
                        getApplicationContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void init() {
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        camera = openFacingBackCamera();

        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.vegetables_source.alarm");
        registerReceiver(alarmReceiver, filter);
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

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        CharSequence text = getText(R.string.local_service_started);

        Notification notification = new Notification(R.drawable.stat_running,
                text, System.currentTimeMillis());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

//        notification.setLatestEventInfo(this,
//                getText(R.string.local_service_label), text, contentIntent);

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

                    mSurfaceTexture = new SurfaceTexture(344);
                    mSurface = new Surface(mSurfaceTexture);
                    try {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        CameraHolder mCameraHolder = new CameraHolder(mSurface);
                        camera.setPreviewDisplay(mCameraHolder);
                        camera.startPreview();
                        camera.setPreviewCallback(new PhotoHandler(
                                getApplicationContext()));
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
