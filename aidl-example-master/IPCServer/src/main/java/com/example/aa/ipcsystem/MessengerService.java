package com.example.aa.ipcsystem;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.aa.ipcsystem.Util.MyConstantce;

public class MessengerService extends Service {

    private static final String TAG ="MessengerService";
    public MessengerService() {
    }

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.w(TAG, "handleMessage client:"+msg.getData().getString("msg") );

            try {
                switch (msg.what){
                    case MyConstantce.MSG_FROM_CLIENT:
                        Log.w(TAG, "receive msg from client:" + msg.getData().getString("msg"));
                        Messenger clientMSG = msg.replyTo;
                        Message msgr = Message.obtain(null, MyConstantce.MSG_FROM_SERVER);
                        Bundle data = new Bundle();
                        data.putString("msg","hello ,this is server");
                        msgr.setData(data);
                        clientMSG.send(msgr);
                        break;
                    default:
                        super.handleMessage(msg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mMessenger.getBinder();
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
