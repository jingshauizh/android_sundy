package com.jingshuai.java.study.io.streaming.bytes;

import java.io.*;

public class FileCoultSize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int count = 0;
        InputStream mInputStream = null;
        try{
        	mInputStream = new FileInputStream("H:/java_code/11.gif");
        	while(mInputStream.read()!=-1){
        		count++;
        	}
        	Util.p(count);
        }
        catch(final IOException er){
        	
        }
	}

}
