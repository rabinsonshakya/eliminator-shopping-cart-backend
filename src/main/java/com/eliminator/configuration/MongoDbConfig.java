package com.eliminator.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.eliminator.repo")
@Configuration
public class MongoDbConfig {
}
