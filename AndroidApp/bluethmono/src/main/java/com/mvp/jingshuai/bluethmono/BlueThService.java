package com.mvp.jingshuai.bluethmono;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.jingshuai.appcommonlib.log.MLog;

public class BlueThService extends Service {

    public BlueThService() {
    }



    @Override
    public void onCreate() {
        MLog.i("service onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        MLog.i("service onDestroy");
        super.onDestroy();
//        MLog.i("service start service in onDestroy");
//        Intent sevice = new Intent(this, BlueThService.class);
//        this.startService(sevice);

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        MLog.i("service onStartCommand");
        startSCO();
        return super.onStartCommand(intent, flags, startId);
        //return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        MLog.i("service onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startSCO(){
        AudioManager localAudioManager = getAudioManager();
        MLog.i("localAudioManager="+localAudioManager.toString());

        localAudioManager.setMode(3);

        localAudioManager.setBluetoothScoOn(true);

        localAudioManager.startBluetoothSco();

        MLog.i("setBluetoothScoOn finished");
    }

    private  AudioManager getAudioManager()
    {
        return (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }
}
