package com.jingshuai.java.study.anotation.example;

@Description(desc="this is ElementType.TYPE",author="JTZeng",age=21)
public class Test1 {
	@Description(desc="this is ElementType.METHOD",author="JTZeng",age=18)
	public void run(){
		System.out.println("I can run!");
	}
}