package com.arouter.jingshuai.javademo.fanxing;

/**
 * Created by jings on 2020/7/5.
 */

public class GenericClass<T> {
    private T mGc;

    public GenericClass(T mGc) {
        this.mGc = mGc;
    }

    public T getmGc() {
        return mGc;
    }

    public void setmGc(T mGc) {
        this.mGc = mGc;
    }
}
