package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.R;

import java.lang.ref.WeakReference;


/****
 *
 *
 *
 * 内部类的方式创建mHandler对象,此时mHandler会隐式地持有一个外部类对象引用这里就是MainActivity，
 * 当执行postDelayed方法时，该方法会将你的Handler装入一个Message，并把这条Message推到MessageQueue中，
 * MessageQueue是在一个Looper线程中不断轮询处理消息，那么当这个Activity退出时消息队列中还有未处理的消息或者正在处理消息，
 * 而消息队列中的Message持有mHandler实例的引用，mHandler又持有Activity的引用，所以导致该Activity的内存资源无法及时回收，
 * 引发内存泄漏。
 *
 *  要想避免Handler引起内存泄漏问题，需要我们在Activity关闭退出的时候的移除消息队列中所有消息和所有的Runnable。
 *  上述代码只需在onDestroy()函数中调用mHandler.removeCallbacksAndMessages(null);就行了。
 *
 *
 * ***/
public class Act_Handler extends AppCompatActivity {

    private  Handler mHandler;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__handler);
        MLog.i("aaaaaaaaaaaaaaa");
        mHandler = new InternalHandler(this);
        mTextView = (TextView) findViewById(R.id.Act_Handler_textView);//模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("lcj");
            }
        }, 3 * 60 * 1000);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.i("Act_Handler onDestroy");


        //方案1  移除 Message 并且 set mHandler = null
        //mHandler.removeCallbacksAndMessages(null);
        //mHandler=null;
        ExampleApplication.getmRefWatcher().watch(this);
    }

/**** 方案2  静态内部类的 Handler
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
