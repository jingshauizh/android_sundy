package com.jingshuai.android.fregmentapp.service.binder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.service.Dog;
import com.jingshuai.appcommonlib.log.MLog;

public class CustomerBinderAct extends AppCompatActivity {
    private IDogManager mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_binder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void binder_startService(View view)
    {
        MLog.i("binder_startService");
        Intent intentPlus = new Intent(this, RemoteService.class);
        intentPlus.setAction("com.jingshuai.android.fregmentapp.service.binder.RemoteService");
        startService(intentPlus);
        Log.e("startService","");
    }
    public void binder_binderService(View view)
    {
        MLog.i("binder_binderService");
        Intent intentPlus = new Intent(this, RemoteService.class);
        intentPlus.setAction("com.jingshuai.android.fregmentapp.service.binder.RemoteService");
        boolean plus = bindService(intentPlus, sc,
                Context.BIND_AUTO_CREATE);
        Log.e("plus", plus + "");
    }




    public void binder_stopService(View view)
    {
        MLog.i("binder_stopService");
        Intent intentPlus = new Intent(this, RemoteService.class);
        intentPlus.setAction("com.jingshuai.android.fregmentapp.service.binder.RemoteService");
        stopService(intentPlus);
        Log.e("startService","");
    }


    public void binder_bindService(View view)
    {
        MLog.i("binder_bindService");
        Intent intentPlus = new Intent(this, RemoteService.class);
        intentPlus.setAction("com.jingshuai.android.fregmentapp.service.binder.RemoteService");
        boolean plus = bindService(intentPlus, sc,
                Context.BIND_AUTO_CREATE);
        Log.e("plus", plus + "");
    }

    public void binder_addInvoked(View view)
    {
        MLog.i("binder_addInvoked");
        if(mService != null){
            try {
                mService.addDog(new Dog());
                Integer listSize = mService.getDogList().size();
                MLog.i("listSize = "+listSize);
            }
            catch(RemoteException er){
                MLog.i(er.getMessage());
            }
        }


    }

    public void binder_getList(View view)
    {
        MLog.i("binder_getList");
        if(mService != null){
            try {
                Integer listSize = mService.getDogList().size();
                MLog.i("listSize = "+listSize);
            }
            catch(RemoteException er){
                MLog.i(er.getMessage());
            }
        }



    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = DogManagerImpl.asInterface(service);



        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

}
