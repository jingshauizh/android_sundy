package com.arouter.jingshuai.javademo.proxy.testproxy;

import com.arouter.jingshuai.javademo.proxy.dyncproxy.Person;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jings on 2020/6/30.
 */

public class TestInterfaceImpl  implements  TestInterface{
    private String name;
    private Integer age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String... args) throws IOException {
        TestInterface targetProxy  = new TestInterfaceImpl();
        targetProxy.setName("zhangsan");
        targetProxy.setAge(43);
        ProxyHandler h = new ProxyHandler<>(targetProxy);
        Object testProxy = Proxy.newProxyInstance(TestInterface.class.getClassLoader(), TestInterfaceImpl.class.getInterfaces(),h);
        TestInterface proxy = (TestInterface)testProxy;
        proxy.setName("wangwu");
        proxy.setAge(55);
        System.out.println("Run test name="+targetProxy.getName());
        System.out.println("Run test age="+targetProxy.getAge());
    }
}

 class ProxyHandler<T> implements InvocationHandler {
     T targetProxy ;

     public ProxyHandler(T targetProxy) {
         this.targetProxy = targetProxy;
     }

     @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //return method.invoke(targetProxy,args);
         System.out.println("Before invoke "  + method.getName());
         Object result = method.invoke(targetProxy, args);
         System.out.println("After invoke " + method.getName());
         return result;

    }
}
