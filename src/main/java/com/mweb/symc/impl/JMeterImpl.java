package com.mweb.symc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mweb.symc.common.Constants;
import com.mweb.symc.dao.JMeter;
import com.mweb.symc.model.JMeterStats;


@Repository
public class JMeterImpl implements JMeter{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public MongoOperations mongoOps;
	
	public JMeterImpl(){
		this.mongoOps = mongoTemplate;
	}
	/*public  JMeterImpl(MongoOperations mongoOperations) {
		this.mongoOps = mongoOperations;
	}*/

	@Override
	public List<JMeterStats> readMetrics(String scenario) {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.mongoDatabase).is(scenario));
		query.limit(Constants.queryLimit);
		query.with(new Sort(Sort.Direction.DESC,"_id"));
		//System.out.println("limmited" + query);
		List <JMeterStats> jstat = mongoTemplate.find(query, JMeterStats.class,Constants.mongoCollection);
		//JMeterStats jstat = mongoOps.findOne(query, JMeter.class); 
		return jstat;
	}

}
