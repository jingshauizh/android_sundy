package com.arouter.jingshuai.mvpdemo.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arouter.jingshuai.mvpdemo.ButterKnifeActivity;
import com.arouter.jingshuai.mvpdemo.R;
import com.jingshuai.appcommonlib.log.MLog;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = "/module/thread", group = "thread")
public class ActThread extends ButterKnifeActivity {

    @BindView(R.id.btn_thread_getnext)
    Button btn_Next;
    @BindView(R.id.tv_thread_newstext)
    TextView tv_newsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_thread);
        super.onCreate(savedInstanceState);
        MLog.i("onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 更新TextView文本内容
                tv_newsText.setText("onCreate update TextView");
        }
        }).start();

    }


    @OnClick(R.id.btn_thread_getnext)
    public void onBtnClick(View v){
        MLog.i("onBtnClick");
        Callable<String> mCallable = new Callable<String>(){
            @Override
            public String call() throws Exception {
                // 更新TextView文本内容
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_newsText.setText("onBtnClick update TextView");
                    }
                });

                return null;
            }
        };
        FutureTask<String> future = new FutureTask<String>(mCallable);
        Thread mthread = new Thread(future,"future");
        mthread.start();
        try {
            mthread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }



}
