package com.qa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(SpringDemoApplication.class, args);
		System.out.println(ac.getBean("traineeRepo"));
	}

}
