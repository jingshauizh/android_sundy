package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.R;



public class Act_innerClass extends AppCompatActivity {
    private static Config mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_inner_class);
        MLog.i("Act_innerClass  onCreate");
        //模拟内存泄露
        if (mConfig == null) {
            mConfig = new Config();
            mConfig.setSize(18);
            mConfig.setTitle("老九门");
        }
        MLog.i("Act_innerClass  finish()");
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExampleApplication.getmRefWatcher().watch(this);
    }

    class Config {
        private int size;
        private String title;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
