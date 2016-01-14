package com.nort.symc.mq.controller;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.mq.service.Producer;

@Controller
public class QController {

	@RequestMapping("putInQ")
	@Produces("application/json")
	public ModelAndView putMessageInQ(@RequestParam String buildNumber) {
		ModelAndView mv = new ModelAndView("jsonOut");
		String json = "";
		Boolean producerResult = new Producer(buildNumber).putBuild(buildNumber);
		if(producerResult) {
			json = "{\"result\":\"success\"}";
		}
		else {
			json = "{\"result\":\"error\"}";
		}
		mv.addObject("json", json);
		return mv;
	}

	public static void main(String args[]) {
		QController q = new QController();
		System.out.println("Call");
		q.putMessageInQ("62");
		System.out.println();
	}
	
}
