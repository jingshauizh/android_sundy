package com.jingshuai.java.study.io.streaming.Object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.jingshuai.java.study.io.streaming.bytes.Util;

public class ObjectStreamExample {
	public static void main(String[] args) {
		Student mStudent = new Student();
		mStudent.setAge(10);
		mStudent.setName("Rose");
		
		ObjectInputStream mObjectInputStream = null;
		java.io.ObjectOutputStream  mObjectOutputStream = null;
		
		
		try {
			mObjectOutputStream = new ObjectOutputStream(new FileOutputStream("H:/java_code/student"));
			mObjectInputStream = new ObjectInputStream(new FileInputStream("H:/java_code/student"));
			mObjectOutputStream.writeObject(mStudent);
			Student mStudent2 = (Student)mObjectInputStream.readObject();
			Util.p(mStudent2.getAge());
			Util.p("%n");
			Util.p(mStudent2.getName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
