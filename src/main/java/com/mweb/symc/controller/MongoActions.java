package com.mweb.symc.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.symc.dao.JMeter;
import com.mweb.symc.exec.GetJson;
import com.mweb.symc.model.JMeterStats;

@Controller
@RequestMapping("/rest")
public class MongoActions {
	
	@Autowired JMeter jmeter;
	@Autowired List<JMeterStats> jmeterStats;
	
	@RequestMapping("/getJson")
	@Produces("application/json")
	public ModelAndView test(@RequestParam("format") String format, @RequestParam("scenario") String scenario) {
		ModelAndView jsonOut = new ModelAndView("jsonOut");
		jmeterStats = jmeter.readMetrics(scenario);
		String json = null;
		LinkedHashMap<String, LinkedHashMap<Integer, String>> totalResults = GetJson.constructMap(jmeterStats);
		if(format.equalsIgnoreCase("chart")) {
			json = GetJson.chartJsonFromStats(totalResults);
		}
		if(format.equalsIgnoreCase("table")) {
			json = GetJson.tableJsonFromStats(totalResults);
		}
			
		jsonOut.addObject("json", json);
		return jsonOut;
	}

}
