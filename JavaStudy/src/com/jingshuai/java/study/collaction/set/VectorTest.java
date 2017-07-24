package com.jingshuai.java.study.collaction.set;

import java.util.Vector;

import com.jingshuai.java.study.util.SystemUtil;

public class VectorTest {
	public static void main(String [] args){
		Vector mVector = new Vector();
		for(int i=0;i<30;i++){
			mVector.add(i);
		}
		
		SystemUtil.print("mVector's size = "+mVector.size());	
		
	}

}
