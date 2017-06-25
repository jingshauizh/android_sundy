package com.jingshuai.java.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			myClient();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void myClient() throws InterruptedException{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		SocketChannel msocket = null;
		int flag = 0;
		try {
			msocket =SocketChannel.open();
			msocket.configureBlocking(false);
			msocket.connect(new InetSocketAddress("127.0.0.1",8080));
			if(msocket.finishConnect()){
				while(true){
					TimeUnit.SECONDS.sleep(1);
					String info = "I am massage from MyClient "+flag;	
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while(buffer.hasRemaining()){
						msocket.write(buffer);
					}
					
					flag++;
				}
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
	            try{
	                if(msocket!=null){
	                	msocket.close();
	                }
	            }catch(IOException e){
	                e.printStackTrace();
	            }
	        }
	}

}
