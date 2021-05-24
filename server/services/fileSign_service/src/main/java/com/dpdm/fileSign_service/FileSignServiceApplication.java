package com.dpdm.fileSign_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FileSignServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(FileSignServiceApplication.class, args);
	}

}
