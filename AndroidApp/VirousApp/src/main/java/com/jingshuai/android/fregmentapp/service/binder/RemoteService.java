package com.jingshuai.android.fregmentapp.service.binder;




import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jingshuai.android.fregmentapp.service.Dog;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {
    private final String TAG="RemoteService";

    private List<Dog> mDogsList = new ArrayList<Dog>();

    private final DogManagerImpl mBinder = new DogManagerImpl() {
        @Override
        public List<Dog> getDogList() throws RemoteException {
            return mDogsList;
        }

        @Override
        public void addDog(Dog dog) throws RemoteException {
            mDogsList.add(dog);
        }
    };



    public void onCreate()
    {
        Log.e(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent t)
    {
        Log.e(TAG, "onBind");
        return mBinder;
    }

    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    public boolean onUnbind(Intent intent)
    {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent)
    {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }
}