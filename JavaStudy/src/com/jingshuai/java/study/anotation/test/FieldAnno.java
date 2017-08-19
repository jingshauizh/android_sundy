package com.jingshuai.java.study.anotation.test;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FieldAnno {
	String Vlaue() default "Field1";
	String Column() default "Column1";
	String Column2() default "Column2";
}
