package com.lazypostman.usermanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n***************************");
		System.out.println("SPRING APPLICATION STARTED");
		System.out.println("***************************\n");
	}
}
