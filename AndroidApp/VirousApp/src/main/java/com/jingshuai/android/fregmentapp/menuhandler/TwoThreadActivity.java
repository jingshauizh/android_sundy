package com.jingshuai.android.fregmentapp.menuhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TwoThreadActivity extends AppCompatActivity {
    @BindView(R.id.buttonMain)
    Button mButtonMain;
    @BindView(R.id.buttonSecond)
    Button mButtonScend;
    @BindView(R.id.buttonCallBack)
    Button mButtonCallBack;

    private Handler mainHandler;
    private Handler secondHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initHandler();
    }

    private void initHandler(){
        mainHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                MessageQueue myMQ = Looper.myQueue();
                MLog.i("mainHandler  handleMessage "+msg.toString());
            }
        };

        Runnable mRunnable = new Runnable(){
            @Override
            public void run() {
                Looper.prepare();
                secondHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        MessageQueue myMQ = Looper.myQueue();

                        MLog.i("secondHandler  handleMessage "+msg.toString());
                    }
                };
                Looper.loop();

            }
        };

        (new Thread(mRunnable)).start();
    }


    @OnClick(R.id.buttonMain)
    public void onClickButtonMain(){
        MLog.i("TwoThreadActivity onClickButtonMain ");
        Message mMessage = Message.obtain(secondHandler, 1);
        mMessage.what = 1;
        mMessage.sendToTarget();
       // secondHandler.sendMessage(mMessage);
    }

    @OnClick(R.id.buttonSecond)
    public void onClickButtonSecond(){
        MLog.i("TwoThreadActivity onClickButtonSecond ");
        Message mMessage = Message.obtain();
        mMessage.what = 1;
        mainHandler.sendMessage(mMessage);
    }


    @OnClick(R.id.buttonCallBack)
    public void onClickButtonCallBack(){
        MLog.i("TwoThreadActivity onClickButtonCallBack ");
        Message mMessage =Message.obtain(mainHandler,new Runnable(){
            @Override
            public void run() {
                MLog.i("mMessage Runnable is called");
            }
        });
        mMessage.what = 1;
        mMessage.sendToTarget();

    }



}
