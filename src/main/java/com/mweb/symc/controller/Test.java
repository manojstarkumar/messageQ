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
public class Test {

	
	@Autowired JMeter jmeter;
	@Autowired List<JMeterStats> jmeterStats;
	
	@RequestMapping("/test")
	@Produces("application/json")
	public ModelAndView test(@RequestParam String format, @RequestParam String scenario) {
		ModelAndView test = new ModelAndView("test");
		//Query q = null;
		jmeterStats = jmeter.readMetrics(scenario);
		String json = null;
		LinkedHashMap<String, LinkedHashMap<Integer, String>> totalResults = GetJson.constructMap(jmeterStats);
		if(format.equalsIgnoreCase("chart")) {
			//System.out.println(jmeterStats);
			json = GetJson.chartJsonFromStats(totalResults);
		}
		if(format.equalsIgnoreCase("table")) {
			//System.out.println(jmeterStats);
			json = GetJson.tableJsonFromStats(totalResults);
		}
			
		test.addObject("msg", json);
		//System.out.println(json);
		return test;
	}
}
