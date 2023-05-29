package com.lazypostman.optimizeroute;

import com.lazypostman.optimizeroute.model.geocoding.GeocodingResponse;
import com.lazypostman.optimizeroute.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OptimizeRouteApplication implements CommandLineRunner {
@Autowired
private GeocodingService geocodingService;
	public static void main(String[] args) {
		SpringApplication.run(OptimizeRouteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		GeocodingResponse response = geocodingService.getCoordinates("Calle GENERAL MOSCARDO 4 Guadarrama Madrid");
		System.out.println(response.getResults()[0].getGeometry().getLocation());
	}
}
