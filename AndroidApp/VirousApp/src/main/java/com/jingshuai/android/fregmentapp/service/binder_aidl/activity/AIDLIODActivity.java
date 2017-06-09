package com.jingshuai.android.fregmentapp.service.binder_aidl.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.IODAIDL;
import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.service.binder_aidl.service.IODService;


public class AIDLIODActivity extends Activity
{
	private IODAIDL mIodAidl;

	private ServiceConnection mServiceConn = new ServiceConnection()
	{
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			Log.e("client", "onServiceDisconnected");
			mIodAidl = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			Log.e("client", "onServiceConnected");
			mIodAidl = IODAIDL.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_aidl_service_main);

	}
	
	/**
	 * 点击BindService按钮时调用
	 * @param view
	 */
	public void bindService(View view)
	{
		Intent intent = new Intent(this, IODService.class);
		intent.setAction("service.binder_aidl.service.IODService");
		bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
	}
	/**
	 * 点击unBindService按钮时调用
	 * @param view
	 */
	public void unbindService(View view)
	{
		unbindService(mServiceConn);
	}
	/**
	 * 点击12+12按钮时调用
	 * @param view
	 */
	public void addInvoked(View view) throws Exception
	{

		if (mIodAidl != null)
		{
			int addRes = mIodAidl.add12(100);
			Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
					.show();

		}

	}
	/**
	 * 点击50-12按钮时调用
	 * @param view
	 */
	public void minInvoked(View view) throws Exception
	{

		if (mIodAidl != null)
		{
			int addRes = mIodAidl.add12(50);
			Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(this, "服务器未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
					.show();

		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mServiceConn);
	}
}
