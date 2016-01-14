package com.nort.symc.mq.utils;

import java.util.concurrent.LinkedBlockingQueue;

public class Constants {
	
	public static LinkedBlockingQueue<String> theQueue = new LinkedBlockingQueue<String>(100);
	public static final String restUpdateUrl = "http://localhost:7070/Flash/rest/updateTest.mdo?buildNumber=";

}
