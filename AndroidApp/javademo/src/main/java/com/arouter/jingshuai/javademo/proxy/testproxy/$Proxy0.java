package com.arouter.jingshuai.javademo.proxy.testproxy;

/**
 * Created by jings on 2020/6/30.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements TestInterface {
    private static Method m1;
    private static Method m4;
    private static Method m3;
    private static Method m2;
    private static Method m6;
    private static Method m5;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  UndeclaredThrowableException{
        super(var1);
    }

    public final boolean equals(Object var1) throws UndeclaredThrowableException {
        try {
            return ((Boolean)super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void setName(String var1) throws UndeclaredThrowableException  {
        try {
            super.h.invoke(this, m4, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String getName() throws  UndeclaredThrowableException{
        try {
            return (String)super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  UndeclaredThrowableException{
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void setAge(Integer var1) throws UndeclaredThrowableException {
        try {
            super.h.invoke(this, m6, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Integer getAge() throws  UndeclaredThrowableException{
        try {
            return (Integer)super.h.invoke(this, m5, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  UndeclaredThrowableException{
        try {
            return ((Integer)super.h.invoke(this, m0, (Object[])null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m4 = Class.forName("com.jings.javabase.proxy2.TestInterface").getMethod("setName", new Class[]{Class.forName("java.lang.String")});
            m3 = Class.forName("com.jings.javabase.proxy2.TestInterface").getMethod("getName", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            m6 = Class.forName("com.jings.javabase.proxy2.TestInterface").getMethod("setAge", new Class[]{Class.forName("java.lang.Integer")});
            m5 = Class.forName("com.jings.javabase.proxy2.TestInterface").getMethod("getAge", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

