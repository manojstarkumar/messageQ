package com.mweb.symc.exec;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.bson.types.ObjectId;

import com.mweb.symc.common.Constants;
import com.mweb.symc.model.JMeterStats;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetJson {
	
	public static String chartJsonFromStats(LinkedHashMap<String, LinkedHashMap<Integer, String>>totalResults) {

		JSONArray entireJson = new JSONArray();
		JSONObject metricObject = null;
		for(String metric : totalResults.keySet()) {
			metricObject = new JSONObject();
			metricObject.put("name", metric);
			JSONArray metricTrend = new JSONArray();
			
			for(Integer timeStamp : totalResults.get(metric).keySet()) {
				JSONArray singleMetric = new JSONArray();
				singleMetric.add(timeStamp*1000L);
				singleMetric.add(Long.parseLong(totalResults.get(metric).get(timeStamp)));
				metricTrend.add(singleMetric);
			}
			
			metricObject.put("data", metricTrend);
			entireJson.add(metricObject);
		}
		
		/* Build JSON from string*/
/*		//StringBuilder json = new StringBuilder();
		//json.append("[");
		//json.append("{\"name\":\""+metric+"\",");
		//json.append("\"data\":[");
		//json.append("["+timeStamp+","+totalResults.get(metric).get(timeStamp)+"],");
		//json.deleteCharAt(json.length()-1);
		//json.append("]},");
		//json.deleteCharAt(json.length()-1);
		//json.append("]");
		//System.out.println(json.toString());
		//return json.toString();
*/		
		
		return entireJson.toString();
	}

	public static String tableJsonFromStats(LinkedHashMap<String, LinkedHashMap<Integer, String>>totalResults) {
		LinkedHashSet<Long> allTimeStamps = new LinkedHashSet<Long>();
		for(String metric : totalResults.keySet()) {
			for(Integer timeStamp : totalResults.get(metric).keySet()) {
				allTimeStamps.add((Long.parseLong(timeStamp.toString())));
			}
		}
		JSONObject completeJson = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM kk:mm");
		JSONArray columnName;
		JSONArray columnArray = new JSONArray();
		columnName = new JSONArray();
		columnName.add("Transaction");
		columnArray.add(columnName);
		for(Long timeStamp : allTimeStamps) {
			columnName = new JSONArray();
			Date d = new Date(timeStamp*1000L);
			columnName.add(sdf.format(d));
			columnArray.add(columnName);	
		}
		completeJson.put("columns", columnArray);
		
		JSONArray allData = new JSONArray();
		JSONArray dataRow;
		for(String metric : totalResults.keySet()) {
			dataRow = new JSONArray();
			dataRow.add(metric);
			for(Long timeStamp : allTimeStamps){
				dataRow.add(totalResults.get(metric).get(Integer.parseInt(timeStamp.toString())));
			}
			allData.add(dataRow);
		}
		completeJson.put("data", allData);
		System.out.println(completeJson);
		return completeJson.toString();
	}

	public static LinkedHashMap<String, LinkedHashMap<Integer, String>> constructMap(
			List<JMeterStats> jmeterStats) {
		LinkedHashMap<String, LinkedHashMap<Integer, String>> totalResults = new LinkedHashMap<String, LinkedHashMap<Integer,String>>();
		for(JMeterStats stat : jmeterStats) {
			for(JSONObject jsonObj : stat.getMetric()) {
				LinkedHashMap<Integer, String> temp = null;
				if(jsonObj.getString(Constants.repLabel).equalsIgnoreCase("TOTAL"));
				else {
					temp = totalResults.get(jsonObj.getString(Constants.repLabel));
					if(temp==null)  {
						temp = new LinkedHashMap<Integer, String>();
						totalResults.put(jsonObj.getString(Constants.repLabel), temp);
					}
					temp.put(new ObjectId(stat.getId()).getTimestamp(), jsonObj.getString(Constants.repAvg));
				}
			}
		}
		return totalResults;
	}

}
