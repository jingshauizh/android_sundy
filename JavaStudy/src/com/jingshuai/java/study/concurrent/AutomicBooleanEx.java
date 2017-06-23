package com.jingshuai.java.study.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutomicBooleanEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BarWorker2 mBarWorker =null;
		
	
		for (int j = 0; j < 20; j++) {
			 mBarWorker = new BarWorker2("app No "+j);
			 mBarWorker.run();
		}
		
	}

	private static class BarWorker2 implements Runnable {

		private static boolean exists = false;

		private String name;

		public BarWorker2(String name) {
			this.name = name;
		}

		public void run() {
			if (!exists) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// do nothing
				}
				exists = true;
				System.out.println(name + " enter");
				try {
					System.out.println(name + " working");
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// do nothing
				}
				System.out.println(name + " leave");
				exists = false;
			} else {
				System.out.println(name + " give up");
			}
		}

	}

	private static class BarWorker3 implements Runnable {

		private static AtomicBoolean exists = new AtomicBoolean(false);

		private String name;

		public BarWorker3(String name) {
			this.name = name;
		}

		public void run() {
			if (exists.compareAndSet(false, true)) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					// do nothing
				}
				// exists = true;
				System.out.println(name + " enter");
				try {
					System.out.println(name + " working");
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// do nothing
				}
				System.out.println(name + " leave");
				// exists = false;
				exists.set(false);  
			} else {
				System.out.println(name + " give up");
			}
		}

	}
	
	private static class BarWorker implements Runnable {  
		  
		  private static AtomicBoolean exists = new AtomicBoolean(false);  
		  
		  private String name;  
		  
		  public BarWorker(String name) {  
		   this.name = name;  
		  }  
		  
		  public void run() {  
		   if (exists.compareAndSet(false, true)) {  
		    System.out.println(name + " enter");  
		    try {  
		     System.out.println(name + " working");  
		     TimeUnit.SECONDS.sleep(2);  
		    } catch (InterruptedException e) {  
		     // do nothing  
		    }  
		    System.out.println(name + " leave");  
		    exists.set(false);  
		   }else{  
		    System.out.println(name + " give up");  
		   }  
		  }  
		  
		 }  

}
