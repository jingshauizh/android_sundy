package com.arouter.jingshuai.javademo.concurrent.threadclass;

import java.util.concurrent.CountDownLatch;

import io.reactivex.ObservableEmitter;

/**
 * Created by eqruvvz on 3/8/2018.
 */

public class Decrementer implements Runnable {

    CountDownLatch latch = null;
    ObservableEmitter<String> mObservableEmitter = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public Decrementer(CountDownLatch latch, ObservableEmitter<String> mObservableEmitter) {
        this.latch = latch;
        this.mObservableEmitter = mObservableEmitter;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            this.latch.countDown();
            mObservableEmitter.onNext("First");

            Thread.sleep(1000);
            mObservableEmitter.onNext("Second");
            this.latch.countDown();

            Thread.sleep(1000);
            mObservableEmitter.onNext("Third");
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
