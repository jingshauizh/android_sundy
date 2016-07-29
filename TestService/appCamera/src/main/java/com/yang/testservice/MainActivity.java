package com.yang.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import com.yang.service.LocalService;

public class MainActivity extends Activity {
	private Intent serviceIntent;
	private LocalService myService = null;

	private String str = "Hello";

	private boolean mIsBound = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_no_view);
		startMyService();
	}

	private void startMyService() {
		serviceIntent = new Intent(MainActivity.this, LocalService.class);
		startService(serviceIntent);
	}

	@Override
	protected void onResume() {

		super.onResume();
		this.finish();
	}
}
