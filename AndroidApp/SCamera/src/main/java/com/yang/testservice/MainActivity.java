package com.yang.testservice;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jingshuai.appcommonlib.activity.ActivityLibBase;
import com.yang.service.CameraService;
import com.yang.service.LocalService;

public class MainActivity extends ActivityLibBase {
	private Intent serviceIntent;
	private LocalService myService = null;

	private String str = "Hello";

	private boolean mIsBound = false;

	private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
	private Button mButton;
	private Button mCloseButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		//startMyService();

	}

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_camera_no_view);
	}

	@Override
	public void initViews() {
		mButton = (Button)findViewById(R.id.settingsButton);
		mCloseButton = (Button)findViewById(R.id.closeButton);
		checkPermission();
	}

	@Override
	public void initListeners() {
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openActivity(SettingsActivity.class);
			}
		});
		mCloseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();
			}
		});
	}

	@Override
	public void initData() {

	}

	@Override
	protected boolean cancelRequest() {
		return false;
	}

	private void checkPermission(){
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.CAMERA)
				!= PackageManager.PERMISSION_GRANTED)
		{

			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.CAMERA,  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
					MY_PERMISSIONS_REQUEST_CAMERA);
		}
		else
		{
			startMyService();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
	{
		if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA)
		{
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
			{
				startMyService();
			} else
			{
				// Permission Denied
				Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
			}
			return;
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}


	private void startMyService() {
		serviceIntent = new Intent(MainActivity.this, CameraService.class);
		startService(serviceIntent);
	}

	@Override
	protected void onResume() {

		super.onResume();
		//this.finish();
	}
}
