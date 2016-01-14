package com.mweb.symc.dao;

import java.util.List;
import com.mweb.symc.model.JMeterStats;

public interface JMeter {
	
	public List<JMeterStats> readMetrics(String scenario);

}
