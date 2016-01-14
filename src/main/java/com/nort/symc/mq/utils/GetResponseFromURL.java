package com.nort.symc.mq.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class GetResponseFromURL {
	
	public static boolean updateRunStatus(String buildNumber) throws IOException {
		URL u = new URL(Constants.restUpdateUrl+buildNumber);
		HttpURLConnection h = (HttpURLConnection) u.openConnection();
		int responseCode = h.getResponseCode();
		if(responseCode!=200) {
			return false;
		}
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(h.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();	
		JSONObject obj = JSONObject.fromObject(response.toString());
		String result = (String) obj.get("result");
		if(result.equalsIgnoreCase("success")) {
			return true;
		}
		System.out.println(obj);
		return false;
	}

}
