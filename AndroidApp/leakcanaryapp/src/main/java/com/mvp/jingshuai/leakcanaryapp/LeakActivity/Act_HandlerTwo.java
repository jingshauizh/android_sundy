package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.R;

import java.lang.ref.WeakReference;


public class Act_HandlerTwo extends AppCompatActivity {

    private TextView tvHelloWorld;
    private Button btnSetText;
    private Handler mHandler = new InternalHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("Act_HandlerTwo onCreate");
        setContentView(R.layout.activity_act__handler_two);
        btnSetText = (Button) findViewById(R.id.btn_set_text);
        tvHelloWorld = (TextView) findViewById(R.id.tv_hello_world);
        btnSetText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        tvHelloWorld.setText("Runnable");
                    }

                },3 * 60 * 1000);
                Act_HandlerTwo.this.finish();
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i("Act_HandlerTwo onDestroy");
        ExampleApplication.getmRefWatcher().watch(this);
    }


    /**** 方案2  静态内部类的 Handler
     *   为什么这种方案 还会导致 内存泄漏
     * **/
    private static class InternalHandler extends Handler {
        private WeakReference<Activity> weakRefActivity;

        /**
         * A constructor that gets a weak reference to the enclosing class. We
         * do this to avoid memory leaks during Java Garbage Collection.
         */
        public InternalHandler(Activity activity) {
            weakRefActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = weakRefActivity.get();
            if (activity != null) {

            }
        }
    }

}
