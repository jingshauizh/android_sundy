package com.jingshuai.android.fregmentapp.service.binder_aidl.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.service.binder_aidl.service.IODPlusService;

public class IODActivity extends Activity
{

	private IBinder mPlusBinder;
	private ServiceConnection mServiceConnPlus = new ServiceConnection()
	{
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			Log.e("client", "mServiceConnPlus onServiceDisconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{

			Log.e("client", " mServiceConnPlus onServiceConnected");
			mPlusBinder = service;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_aidl_service_main_start);

	}

	public void startService(View view)
	{
		Intent intentPlus = new Intent(this, IODPlusService.class);
		intentPlus.setAction("service.binder_aidl.service.IODPlusService");
		startService(intentPlus);
		Log.e("startService","");
	}

	public void stopService(View view)
	{
		Intent intentPlus = new Intent(this, IODPlusService.class);
		intentPlus.setAction("service.binder_aidl.service.IODPlusService");
		stopService(intentPlus);
		Log.e("startService","");
	}


	public void bindService(View view)
	{
		Intent intentPlus = new Intent(this, IODPlusService.class);
		intentPlus.setAction("service.binder_aidl.service.IODPlusService");
		boolean plus = bindService(intentPlus, mServiceConnPlus,
				Context.BIND_AUTO_CREATE);
		Log.e("plus", plus + "");
	}

	public void unbindService(View view)
	{
		unbindService(mServiceConnPlus);
	}

	public void addInvoked(View view)
	{

		if (mPlusBinder == null)
		{
			Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
		} else
		{
			android.os.Parcel _data = android.os.Parcel.obtain();
			android.os.Parcel _reply = android.os.Parcel.obtain();
			int _result;
			try
			{
				_data.writeInterfaceToken("IODPlusService");
				_data.writeInt(50);
				mPlusBinder.transact(0x110, _data, _reply, 0);
				_reply.readException();
				_result = _reply.readInt();
				Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();

			} catch (RemoteException e)
			{
				e.printStackTrace();
			} finally
			{
				_reply.recycle();
				_data.recycle();
			}
		}

	}
	
	public void minInvoked(View view)
	{

		if (mPlusBinder == null)
		{
			Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
		} else
		{
			android.os.Parcel _data = android.os.Parcel.obtain();
			android.os.Parcel _reply = android.os.Parcel.obtain();
			int _result;
			try
			{
				_data.writeInterfaceToken("IODPlusService");
				_data.writeInt(36);
				mPlusBinder.transact(0x111, _data, _reply, 0);
				_reply.readException();
				_result = _reply.readInt();
				Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();

			} catch (RemoteException e)
			{
				e.printStackTrace();
			} finally
			{
				_reply.recycle();
				_data.recycle();
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mServiceConnPlus);
	}
}
