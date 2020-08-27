package com.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"springbootserviciocommons.models.entity"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); 
	}

}
