package com.jingshuai.java.study.anotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/** 
 * ×Ö¶Î×¢ÊÍ
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface MyField {
    public String name() default "";     //Ãû³Æ
    public String type() default "";    //ÀàÐÍ
    
}