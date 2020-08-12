package com.arouter.jingshuai.javademo.fanxing.entity;

/**
 * Created by jings on 2020/7/5.
 */

public class Fruit  extends Food{


    private String name = "KKKK";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit() {
        System.out.println( "Fruit");
    }

    private void setNewName(String name){
        System.out.println( "setNewName");
        this.name = name;
    }


}
