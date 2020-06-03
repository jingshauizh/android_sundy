package com.jingshuai.android.fregmentapp.pattern.command;

/**
 * Created by jings on 2020/4/17.
 */

public class OrderExcuterImp implements OrderExcuter {
    @Override
    public void excute(IOrder order) {
        order.run();
    }
}
