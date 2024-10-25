package com.damla.shoestore.shoestore_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.damla.shoestore.shoestore_admin")
public class ShoestoreAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoestoreAdminApplication.class, args);
		System.err.println("Up");
	}

}






