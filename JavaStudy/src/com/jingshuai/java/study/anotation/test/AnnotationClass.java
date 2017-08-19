package com.jingshuai.java.study.anotation.test;

public class AnnotationClass {
	@FieldAnno(Vlaue="sssd",Column="Columndddd")
	private String a;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	@FieldAnno
	private String b;
	@FieldAnno
	private String d;
	@FieldAnno
	private String c;
	
	

}
