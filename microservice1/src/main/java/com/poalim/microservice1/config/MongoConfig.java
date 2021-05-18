package com.poalim.microservice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author - FAwad
 */
@Configuration
public class MongoConfig {

	@Bean
	public MongoDatabaseFactory mongoDbFactory() {
		MongoDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/mongodb");
		return mongoDatabaseFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}
}
