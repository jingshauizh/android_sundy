package com.mvp.jingshuai.leakcanaryapp.LeakActivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jingshuai.appcommonlib.log.MLog;
import com.mvp.jingshuai.leakcanaryapp.ExampleApplication;
import com.mvp.jingshuai.leakcanaryapp.LeakBaseActivity;
import com.mvp.jingshuai.leakcanaryapp.R;

/*****
 *   https://www.cnblogs.com/whoislcj/p/6001422.html
 *    3.)线程造成的内存泄漏
 *   最早时期的时候处理耗时操作多数都是采用Thread+Handler的方式，后来逐步被AsyncTask取代，
 *   直到现在采用RxJava的方式来处理异步。这里以AsyncTask为例，可能大部分人都会这样处理一个耗时操作然后通知UI更新结果：
 *    对于上面的例子来说，在处理一个比较耗时的操作时，可能还没处理结束MainActivity就执行了退出操作，
 *    但是此时AsyncTask依然持有对MainActivity的引用就会导致MainActivity无法释放回收引发内存泄漏。
 *
 *
 *
 *    如何解决这种内存泄漏呢？在使用AsyncTask时，在Activity销毁时候也应该取消相应的任务AsyncTask.cancel()方法，
 *    避免任务在后台执行浪费资源，进而避免内存泄漏的发生。
 *
 * ****/
public class Act_AsyncTask extends LeakBaseActivity {
    private AsyncTask<Void, Void, Integer> asyncTask;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__handler);
        mTextView = (TextView) findViewById(R.id.Act_Handler_textView);
        testAsyncTask();
        finish();
    }

    private void testAsyncTask() {
        asyncTask = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int i = 0;
                //模拟耗时操作
                while (!isCancelled()) {
                    i++;
                    if (i > 1000000000) {
                        break;
                    }
                    MLog.e("LeakCanary", "asyncTask---->" + i);
                }
                return i;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                mTextView.setText(String.valueOf(integer));
            }
        };
        asyncTask.execute();

    }

    private void destroyAsyncTask() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        asyncTask = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //destroyAsyncTask();
        MLog.e("Act_AsyncTask onDestroy ");
    }

}