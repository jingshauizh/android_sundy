package com.mvp.jingshuai.jinapp;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class NdkTest {
    static {
        System.loadLibrary("myNativeLib");//加载要使用的so文件
    }
    //生命native方法
    public static native String getString();
    public static native int doAdd(int param1,int param2);
}
