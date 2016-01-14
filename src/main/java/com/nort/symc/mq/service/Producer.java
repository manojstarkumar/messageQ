package com.nort.symc.mq.service;

import java.util.concurrent.LinkedBlockingQueue;
import com.nort.symc.mq.utils.Constants;

public class Producer implements Runnable{

	Thread thread = null;
	public LinkedBlockingQueue<String> messageQ;
	public String buildNumber;
	
	public Producer() {	
	}
	
	public Producer(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	public Boolean putBuild(String buildNumber) {

		System.out.println("here");
			thread = new Thread(this, "Produce:"+buildNumber);
		  	thread.start();	
		  	return isInsertSuccess(buildNumber);
		
	}
	
	  @SuppressWarnings("deprecation")
	private Boolean isInsertSuccess(String buildNumber) {
		try {
			System.out.println("ins ins");
			Constants.theQueue.put(buildNumber);
			thread.stop();
		} catch (Exception e) {
			e.printStackTrace();
			thread.stop();
			return false;
		}
		return true;
	}

	public void run() {
	}

}
