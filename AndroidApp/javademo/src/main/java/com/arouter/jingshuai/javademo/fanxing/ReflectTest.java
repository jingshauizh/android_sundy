package com.arouter.jingshuai.javademo.fanxing;

import com.arouter.jingshuai.javademo.fanxing.entity.Fruit;
import com.arouter.jingshuai.javademo.fanxing.impl.GenericImpl1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jings on 2020/7/6.
 */

public class ReflectTest {

    public static void main(String [] args){
        try {
            Class geneFruit = Class.forName("com.arouter.jingshuai.javademo.fanxing.entity.Fruit");
            //Class gene1 = Class.forName("com.arouter.jingshuai.javademo.fanxing.interfaces.GenericInterface.GenericImpl1");
            Object tFruit = geneFruit.newInstance();
            Constructor tFruitCon = tFruit.getClass().getConstructor();
            Method methos1 = geneFruit.getMethod("getName");
            Method methos2 = geneFruit.getDeclaredMethod("setNewName",String.class);
            Object object=  methos1.invoke(tFruit);
            System.out.println( "object="+object);
            methos2.setAccessible(true);
            methos2.invoke(tFruit,"DDDDD");
            Object object2=  methos1.invoke(tFruit);
            System.out.println( "object2="+object2);

            Field field1 = geneFruit.getDeclaredField("name");
            field1.setAccessible(true);
            Object object3=  field1.get(tFruit);
            System.out.println( "field1 object3="+object3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
