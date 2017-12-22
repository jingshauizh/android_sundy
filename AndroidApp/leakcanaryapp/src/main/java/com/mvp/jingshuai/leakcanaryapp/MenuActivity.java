package com.mvp.jingshuai.leakcanaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.InnerClassActivity;
import com.mvp.jingshuai.leakcanaryapp.LeakActivity.StaticActivitysAct;

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
                Intent intent = new Intent(getApplicationContext(), StaticActivitysAct.class);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i("111111 MenuActivity onDestroy");
        ExampleApplication.getmRefWatcher().watch(this);

    }
}
