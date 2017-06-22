package com.jingshuai.java.study.io.streaming.bytes;

import java.io.*;

public class FileCoultSizeBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int count = 0;
        byte [] b = new byte[256];
        InputStream mInputStream = null;
        OutputStream mOut = null;
        try{
        	mInputStream = new FileInputStream("H:/java_code/11.gif");
        	mOut = new FileOutputStream("H:/java_code/22.gif");
        	while(mInputStream.read(b)!=-1){
        		count++;
        		mOut.write(b);
        	}
        	Util.p(count * 256);
        }
        catch(final IOException er){
        	
        }
	}

}
