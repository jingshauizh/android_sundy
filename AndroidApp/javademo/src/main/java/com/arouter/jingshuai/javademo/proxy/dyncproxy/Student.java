package com.arouter.jingshuai.javademo.proxy.dyncproxy;

/**
 * Created by jings on 2020/6/30.
 */

public class Student implements Person {

    private String mName;

    public Student(String name) {
        mName = name;
    }

    public void setName(String name) {
        mName = name;
    }
}