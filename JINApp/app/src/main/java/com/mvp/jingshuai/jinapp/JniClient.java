package com.mvp.jingshuai.jinapp;

/**
 * Created by eqruvvz on 7/19/2017.
 */

public class JniClient {

    static {

        System.loadLibrary("myNativeLib");

    }
    static public native String sayName();
}