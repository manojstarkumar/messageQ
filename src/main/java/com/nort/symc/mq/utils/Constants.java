package com.nort.symc.mq.utils;

import java.util.concurrent.LinkedBlockingQueue;

public class Constants {
	
	public static LinkedBlockingQueue<String> theQueue = new LinkedBlockingQueue<String>(100);
	public static final String restUpdateUrl = "Flash/rest/updateTest.mdo?buildNumber=";
	public static final String machineRoot = "http://localhost:7070/";

}
