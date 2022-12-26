package com.ms.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

	/*
	* Make a MS Eureka Client :
	1. Use Dependencies of Eureka Client & Spring Cloud
	2. @EnableEurekaClient in Main Class
	3. In application.yaml

	eureka:
	  instance:
		prefer-ip-address: true
	  client:
		fetch-registry: true
		register-with-eureka: true
		service-url:
		  defaultZone: http://localhost:8761/eureka

	Make a MS Eureka Server : i.e. Service Registry or Service Discovery
	1. Use Dependencies of Eureka Server & Spring Cloud
	2. @EnableEurekaServer in Main Class
	3. In application.yaml

	eureka:
	  instance:
		hostname: localhost
	  client:
		register-with-eureka: false
		fetch-registry: false

	server:
	  port: 8761
	*
	* */
}
