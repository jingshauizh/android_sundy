package com.jingshuai.android.fregmentapp.pattern.command;

import com.jingshuai.android.fregmentapp.pattern.patternlist.IActionPattern;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by jings on 2020/4/17.
 */

public class CommandPattern implements IActionPattern  {
    private Queue<IOrder> orderQueue;
    private OrderExcuterImp mOrderExcuterImp;

    public CommandPattern() {
        orderQueue = new ArrayDeque<IOrder>();
        mOrderExcuterImp = new OrderExcuterImp();
    }

    @Override
    public void excute() {
        Camera mCamera = new Camera();
        IOrder order1 = new OrderOpen(mCamera);
        IOrder order2 = new OrderClose(mCamera);
        orderQueue.add(order1);
        orderQueue.add(order2);
        while(null != orderQueue && orderQueue.size()>0){
            IOrder tmpOrder = orderQueue.poll();
            mOrderExcuterImp.excute(tmpOrder);
        }
    }
}

