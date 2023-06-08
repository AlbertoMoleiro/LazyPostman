package com.lazypostman.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n***************************");
        System.out.println("AUTHENTICATION STARTED");
        System.out.println("***************************\n");
    }
}
