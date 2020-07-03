package com.arouter.jingshuai.javademo.proxy.dyncproxy;

import android.util.Log;
import com.arouter.jingshuai.javademo.proxy.INameService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;



/**
 * Created by jings on 2020/6/30.
 */
public class ProxyUtil {
    private static final String TAG = "ProxyUtil";

    public static void start() {
        System.out.println("start: " + System.currentTimeMillis());
    }

    public static void finish() {
        System.out.println( "finish: " + System.currentTimeMillis());
    }

    public static void log(String name) {
        System.out.println( "log: " + name);
    }

    public static void main(String... args) throws IOException {
        //????????????????????
        Person zhangsan = new Student("zhangsan");

        //????????????? InvocationHandler
        PersonHandler stuHandler = new PersonHandler<>(zhangsan);

        //???????? stuProxy ??? zhangsan?????????????????? Invocation ?? invoke ??
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        //byte [] bytes= ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
        //FileOutputStream fis=new FileOutputStream("E://abc.class");
        //fis.write(bytes);
        //fis.close();

        //???? setName ?????????????? stuHandler ? invoke??????????????????????
        stuProxy.setName("wangwu");
    }

}