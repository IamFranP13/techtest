package com.franpesal.techtest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TechtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtestApplication.class, args);
	}

}
