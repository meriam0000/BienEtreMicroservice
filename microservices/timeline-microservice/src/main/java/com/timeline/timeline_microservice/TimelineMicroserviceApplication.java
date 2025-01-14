package com.timeline.timeline_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TimelineMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimelineMicroserviceApplication.class, args);
	}

}
