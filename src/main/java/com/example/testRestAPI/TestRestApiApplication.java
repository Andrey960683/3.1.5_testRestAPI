package com.example.testRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.module.Configuration;

@SpringBootApplication
public class TestRestApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TestRestApiApplication.class, args);
		Communication communication = context.getBean("communication", Communication.class);

		System.out.println("Ответ:" + communication.getAnswer());

	}

}
