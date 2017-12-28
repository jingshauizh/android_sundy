package com.mvp.jingshuai.leakcanaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_AsyncTask;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_Handler;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_HandlerTwo;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_Webview;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_innerClass;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.InnerClassActivity;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.Act_StaticActivitys;

public class MenuActivity extends AppCompatActivity {
    Button gotoTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


    }

    private void initViews(){
        gotoTestBtn = findViewById(R.id.btn_Static);
        gotoTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act_StaticActivitys.class);
                startActivity(intent);
            }
        });

        Button innerclassBtn = findViewById(R.id.btn_innerclass);
        innerclassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InnerClassActivity.class);
                startActivity(intent);
            }
        });

        Button handlerBtn = findViewById(R.id.btn_handler);
        handlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act_Handler.class);
                startActivity(intent);
            }
        });


        Button handler2Btn = findViewById(R.id.btn_handler2);
        handler2Btn.setText("Handler Two");
        handler2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act_HandlerTwo.class);
                startActivity(intent);
            }
        });

        Button asynctaskBtn = findViewById(R.id.btn_asynctask);
        asynctaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act_AsyncTask.class);
                startActivity(intent);
            }
        });

        Button webBtn = findViewById(R.id.btn_webview);
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act_Webview.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i("111111 MenuActivity onDestroy");
        ExampleApplication.getmRefWatcher().watch(this);

    }
}
