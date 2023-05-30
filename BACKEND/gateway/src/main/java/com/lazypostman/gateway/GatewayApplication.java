package com.lazypostman.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n***************************");
		System.out.println("GATEWAY STARTED");
		System.out.println("***************************\n");
	}
}
