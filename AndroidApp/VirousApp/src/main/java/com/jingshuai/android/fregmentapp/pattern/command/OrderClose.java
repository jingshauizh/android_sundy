package com.jingshuai.android.fregmentapp.pattern.command;

/**
 * Created by jings on 2020/4/17.
 */

public class OrderClose implements IOrder {
    private IOrderTarget mIOrderTarget;
    private Boolean enableFlag = true;
    public OrderClose(IOrderTarget mIOrderTarget) {
        this.mIOrderTarget = mIOrderTarget;
    }

    @Override
    public void run() {
        if(!enableFlag){
            return;
        }
        Camera mCamera = (Camera)mIOrderTarget;
        mCamera.close();
    }

    @Override
    public void undo() {
        enableFlag = false;
    }
}
