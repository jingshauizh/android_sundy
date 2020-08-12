package com.arouter.jingshuai.javademo.fanxing;

import com.arouter.jingshuai.javademo.fanxing.entity.Apple;
import com.arouter.jingshuai.javademo.fanxing.entity.Food;
import com.arouter.jingshuai.javademo.fanxing.entity.Fruit;
import com.arouter.jingshuai.javademo.fanxing.impl.GenericImpl1;

/**
 * Created by jings on 2020/7/5.
 */

public class TestClass {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String [] args){
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>(new Fruit());
        Fruit fruit = fruitGenericClass.getmGc();

        GenericImpl1<String> tGenericImpl1 = new GenericImpl1<String>("Mike");


        System.out.println("GenericImpl1 T="+tGenericImpl1.getmT());
        tGenericImpl1.Method2("kkkk",1000);

        GenericImpl1<String> [] array1;
        //GenericImpl1<String> [] array2 = new GenericImpl1<String> [10];

        GenericImpl1<Fruit> genericImpl1Fruit = new GenericImpl1<Fruit>(new Fruit());
        GenericImpl1<Apple> genericImpl1Apple = new GenericImpl1<Apple>(new Apple());
        GenericImpl1<Food> genericImpl1Food = new GenericImpl1<Food>(new Food());
        print1(genericImpl1Fruit);
        print1(genericImpl1Food);

        print2(genericImpl1Fruit);
        print2(genericImpl1Apple);



    }

    public static void print1( GenericImpl1<? super Fruit> gene){

        System.out.println( gene.getmT().toString());
    }

    public static void print2( GenericImpl1<? extends Fruit> gene){

        System.out.println( gene.getmT().toString());
    }
}
