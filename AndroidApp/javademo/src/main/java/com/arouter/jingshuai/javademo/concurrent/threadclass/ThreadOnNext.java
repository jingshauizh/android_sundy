package com.arouter.jingshuai.javademo.concurrent.threadclass;

import java.util.concurrent.CountDownLatch;

import io.reactivex.ObservableEmitter;

/**
 * Created by eqruvvz on 3/8/2018.
 */

public class ThreadOnNext implements Runnable {


    ObservableEmitter<String> mObservableEmitter = null;



    public ThreadOnNext(ObservableEmitter<String> mObservableEmitter) {

        this.mObservableEmitter = mObservableEmitter;
    }

    public void run() {

        try {
            if(mObservableEmitter == null){
                return;
            }
            Thread.sleep(1000);
            mObservableEmitter.onNext("First");

            Thread.sleep(1000);
            mObservableEmitter.onNext("Second");

            Thread.sleep(1000);
            mObservableEmitter.onNext("Third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
