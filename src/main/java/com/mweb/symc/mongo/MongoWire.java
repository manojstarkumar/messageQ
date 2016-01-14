package com.mweb.symc.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DBCollection;
import com.mweb.symc.common.Constants;

public class MongoWire {

	@Autowired
	static MongoTemplate mongoTemplate;
	
	public static DBCollection getConnection(){
		return mongoTemplate.getCollection(Constants.mongoCollection);
	}
}
