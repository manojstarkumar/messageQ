package com.nort.symc.mq.service;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.LoggerFactory;
import com.nort.symc.mq.utils.Constants;
import com.nort.symc.mq.utils.GetResponseFromURL;

public class Consumer implements Runnable{
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(Consumer.class);

	public LinkedBlockingQueue<String> messageQ;
	public String buildNumber;
	
	public Consumer(LinkedBlockingQueue<String> messageQ){
	}
	
	public Consumer() {
	}
	
	  public void start() {
		  	Thread thread = new Thread(this, "Consume");
		  	thread.start();
		  }
	
	public String consumeMessage() throws IOException {
		String message = null;
		boolean updateResult = false;
		try {
			message = Constants.theQueue.take();
			Thread.sleep(10000);
			updateResult = GetResponseFromURL.updateRunStatus(message);
			if(!updateResult) return message;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return message;
		}
		return "Pass";
	}

	//May be I am dumb, but how else am i supposed to run this forever?
	public void run() {
		String updateResult = null;
		boolean shouldQuit = false;
		do { //run this service infinitely to always receive and update requests
		try {
			updateResult = consumeMessage();
			if(!updateResult.equalsIgnoreCase("Pass")) {
				logger.error("Failed to update for build : "+updateResult);
			}
		} catch (IOException e) {
			logger.error("Failed to update for build : "+updateResult);
			e.printStackTrace();
		}
		}while(!shouldQuit);
	}

}
