package com.mweb.symc.model;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class JMeterStats {

	@Id
	public String id;
	
	public String comment;
	public String duration;
	public String test;
	public String users;
	public ArrayList<JSONObject> metric;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public ArrayList<JSONObject> getMetric() {
		return metric;
	}
	public void setMetric(ArrayList<JSONObject> metric) {
		this.metric = metric;
	}
	
	
}
