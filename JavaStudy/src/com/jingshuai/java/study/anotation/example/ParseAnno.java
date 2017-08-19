package com.jingshuai.java.study.anotation.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
public class ParseAnno {
	public static void main(String[] args) {
		try {
			/*
			 * 1.ʹ���������������
			 * Class.forName("�����ַ���") ��ע�⣺�����ַ���������ȫ�ƣ�����+������
			 */
			Class c = Class.forName("com.jingshuai.java.study.anotation.example.Test1");
			
			//2.�ж������Ƿ����ע�⣬����ȡ������ע���ʵ��
			if(c.isAnnotationPresent(Description.class)){
				Description Description = (Description) c.getAnnotation(Description.class);
				System.out.println(Description.desc());
				System.out.println(Description.author());
				System.out.println(Description.age());
			}
			
			//3.�жϷ������Ƿ����ע�⣬����ȡ��������ע���ʵ��
			Method[] ms = c.getMethods();
			for (Method method : ms) {
				if(method.isAnnotationPresent(Description.class)){
					Description Description = (Description)method.getAnnotation(Description.class);
					System.out.println(Description.desc());
					System.out.println(Description.author());
					System.out.println(Description.age());
				}
			}
			//��һ�ֻ�ȡ�����ϵ�ע��Ľ�������
			for (Method method : ms) {
				Annotation[] as = method.getAnnotations();
				for (Annotation annotation : as) {
					if(annotation instanceof Description){
						System.out.println(((Description) annotation).desc());
						System.out.println(((Description) annotation).author());
						System.out.println(((Description) annotation).age());
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}