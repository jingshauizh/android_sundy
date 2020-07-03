package com.arouter.jingshuai.javademo.proxy.dyncproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jings on 2020/6/30.
 */

public class PersonHandler<T> implements InvocationHandler {
    // ???????
    private T mTarget;

    public PersonHandler(T target) {
        mTarget = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        // ????????
        ProxyUtil.start();
        // ????,?????????? mTarget ? method
        Object result = method.invoke(mTarget, objects);
        // ????????
        ProxyUtil.log(objects[0].toString());
        // ????????
        ProxyUtil.finish();
        return result;
    }
}