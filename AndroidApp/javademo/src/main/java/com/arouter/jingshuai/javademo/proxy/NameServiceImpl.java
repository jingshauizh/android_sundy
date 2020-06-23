package com.arouter.jingshuai.javademo.proxy;

/**
 * Created by jings on 2020/6/8.
 */

public class NameServiceImpl implements INameService {
    @Override
    public String getName() {
        return "Mike";
    }
}
