package com.lazypostman.companyregistration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyRegistrationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CompanyRegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n***************************");
		System.out.println("COMPANY-REGISTRATION STARTED");
		System.out.println("***************************\n");
	}
}
