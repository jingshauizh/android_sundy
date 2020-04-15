package com.arouter.jingshuai.javademo.concurrent.threadclass;

import java.util.concurrent.CountDownLatch;

/**
 * Created by eqruvvz on 3/8/2018.
 */

public class Waiter implements Runnable{

    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released");
    }
}