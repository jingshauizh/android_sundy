package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.MainActivity;
import com.mvp.jingshuai.leakcanaryapp.MenuActivity;
import com.mvp.jingshuai.leakcanaryapp.R;

public class Act_StaticActivitys extends AppCompatActivity {

    private  static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_static_activitys);

        Button saButton = findViewById(R.id.btn_static_activitys);
        saButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStaticActivity();
                Act_StaticActivitys.this.finish();
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(intent);

            }
        });


    }



    void setStaticActivity() {
        activity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i("111111 MenuActivity onDestroy");
        ExampleApplication.getmRefWatcher().watch(this);

    }


}
