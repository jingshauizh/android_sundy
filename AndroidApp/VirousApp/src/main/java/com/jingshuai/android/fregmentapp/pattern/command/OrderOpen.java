package com.jingshuai.android.fregmentapp.pattern.command;

/**
 * Created by jings on 2020/4/17.
 */

public class OrderOpen implements IOrder {
    private IOrderTarget mIOrderTarget;
    private Boolean enableFlag = true;
    public OrderOpen(IOrderTarget mIOrderTarget) {
        this.mIOrderTarget = mIOrderTarget;
    }

    @Override
    public void run() {
        if(!enableFlag){
            return;
        }
        Camera mCamera = (Camera)mIOrderTarget;
        mCamera.open();
    }

    @Override
    public void undo() {
        enableFlag = false;
    }
}
