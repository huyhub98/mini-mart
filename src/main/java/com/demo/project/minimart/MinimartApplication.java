package com.demo.project.minimart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MinimartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinimartApplication.class, args);
	}

}
