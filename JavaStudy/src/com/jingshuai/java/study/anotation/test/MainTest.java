package com.jingshuai.java.study.anotation.test;

import java.lang.reflect.Field;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class obj = null;
		try {
			 obj = Class.forName("com.jingshuai.java.study.anotation.test.AnnotationClass");
			 Field[] fields = obj.getDeclaredFields();
			 for(Field filedt : fields){
				 FieldAnno value =  filedt.getAnnotation(FieldAnno.class);
				 System.out.println("arg1="+value.Vlaue());
				 System.out.println("arg2="+value.Column());
				 System.out.println("arg3="+value.Column2());
			 }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
