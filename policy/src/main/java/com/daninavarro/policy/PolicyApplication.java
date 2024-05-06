package com.daninavarro.policy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableConfigurationProperties
@EnableMongoRepositories("com.daninavarro.policy.infraestructure.database")
@SpringBootApplication
public class PolicyApplication {

  public static void main(String[] args) {
    SpringApplication.run(PolicyApplication.class, args);
  }

}
