package com.arouter.jingshuai.javademo.xiangxue.generic.defgeneric;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：
 */
public class NormalGeneric<K> {
    private K data;

    public NormalGeneric() {
    }

    public NormalGeneric(K data) {
        this.data = data;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public static void main(String[] args) {
        NormalGeneric<String> normalGeneric = new NormalGeneric<>();
        normalGeneric.setData("OK");
        //normalGeneric.setData(1);
        System.out.println(normalGeneric.getData());
        NormalGeneric normalGeneric1 = new NormalGeneric();
        normalGeneric1.setData(1);
        normalGeneric1.setData("dsf");
    }
}
