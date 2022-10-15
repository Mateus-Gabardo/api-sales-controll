package com.api.salescontroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesControllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesControllApplication.class, args);
	}
}
