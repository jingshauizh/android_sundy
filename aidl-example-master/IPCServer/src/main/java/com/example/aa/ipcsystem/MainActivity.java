package com.example.aa.ipcsystem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.aa.ipcsystem.AIDL.BookManagerActivity;
import com.example.aa.ipcsystem.AIDL.BookManagerService;
import com.example.aa.ipcsystem.Util.MyConstantce;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Button mButtonSend;

    private Button mbuttonSendMessage;
    private Button  mButtonStartActivity;
    private Messenger mservice;

    private static final String TAG ="MainActivity";

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mButton = (Button)findViewById(R.id.ButtonStartService);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(MainActivity.this,MessengerService.class);
                startService(_intent);

                Intent _intent2 = new Intent(MainActivity.this,BookManagerService.class);
                startService(_intent2);
            }
        });

        mButtonSend = (Button)findViewById(R.id.ButtonSendMessage);
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(MainActivity.this,MessengerService.class);
                bindService(_intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        mbuttonSendMessage = (Button)findViewById(R.id.ButtonSend);
        mbuttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mservice != null){
                    Message msg = Message.obtain(null, MyConstantce.MSG_FROM_CLIENT);
                    Bundle data = new Bundle();
                    data.putString("msg","hello ,this is client");
                    msg.setData(data);
                    msg.replyTo = mClientMessenger;
                    try {
                        Log.w(TAG, "Send msg from client:" );
                        mservice.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        mButtonStartActivity = (Button)findViewById(R.id.ButtonStartActivity);
        mButtonStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(MainActivity.this,BookManagerActivity.class);
                startActivity(_intent);


            }
        });




    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstantce.MSG_FROM_SERVER:
                    Log.w(TAG, "receive msg from server:" + msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private Messenger  mClientMessenger = new Messenger(new MessengerHandler());

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private ServiceConnection mConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mservice = new Messenger(service);
            Message msg = Message.obtain(null, MyConstantce.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg","hello ,this is client");
            msg.setData(data);
            msg.replyTo = mClientMessenger;
            try {
                Log.e(TAG, "Send msg from client:" );
                mservice.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
