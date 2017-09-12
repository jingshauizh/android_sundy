package com.jingshuai.java.study.anotation.example2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ˮ����Ӧ��ע��
 * @author peida
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    /**
     * ��Ӧ�̱��
     * @return
     */
    public int id() default -1;
    
    /**
     * ��Ӧ������
     * @return
     */
    public String name() default "";
    
    /**
     * ��Ӧ�̵�ַ
     * @return
     */
    public String address() default "";
}