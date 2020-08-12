package com.arouter.jingshuai.javademo.fanxing.impl;

import com.arouter.jingshuai.javademo.fanxing.interfaces.GenericInterface;

/**
 * Created by jings on 2020/7/6.
 */

public class GenericImpl1<T>  implements GenericInterface<T> {

    private T mT;

    public T getmT() {
        return mT;
    }

    public void setmT(T mT) {
        this.mT = mT;
    }

    public GenericImpl1() {

    }


    public GenericImpl1(T t) {
        mT = t;
    }

    @Override
    public T getInstance() {
        System.out.println("GenericImpl1");
        return null;
    }


    public<K,V> void Method2(K k, V v){
        System.out.println(k);
        System.out.println(v);

    }
}
