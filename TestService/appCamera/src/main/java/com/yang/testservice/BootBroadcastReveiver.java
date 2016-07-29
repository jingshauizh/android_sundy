package com.yang.testservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yang.service.LocalService;
import com.yang.testservice.MainActivity;

/**
 * Created by eqruvvz on 6/30/2016.
 */
public class BootBroadcastReveiver extends BroadcastReceiver {
    private static final String TAG = "BootBroadcastReveiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent bootServiceIntent = new Intent(context, LocalService.class);
            context.startService(bootServiceIntent);
            Log.d(TAG, "--------Boot start service-------------");
        }
    }
}