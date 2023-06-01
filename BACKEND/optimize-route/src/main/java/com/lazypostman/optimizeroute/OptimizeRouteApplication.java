package com.lazypostman.optimizeroute;

import com.lazypostman.optimizeroute.model.formcreator.Town;
import com.lazypostman.optimizeroute.model.geocoding.GeocodingResponse;
import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import com.lazypostman.optimizeroute.service.GeocodingService;
import com.lazypostman.optimizeroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OptimizeRouteApplication implements CommandLineRunner {
@Autowired
private RouteService routeService;
	public static void main(String[] args) {
		SpringApplication.run(OptimizeRouteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Prueba Geocoding
//		GeocodingResponse response = geocodingService.getCoordinates("Calle GENERAL MOSCARDO 4 Guadarrama Madrid");
//		System.out.println(response.getResults()[0].getGeometry().getLocation());

//		Prueba Route
//		List<Waypoint> waypoints = new ArrayList<>();
//		waypoints.add(new Waypoint(40.47842051514145,-3.8598305104235164));
//		waypoints.add(new Waypoint(40.479562909083214,-3.859178960798713));
//		routeService.optimizeRoute("40.47894971475314,-3.857599781165978",waypoints).getRoutes().forEach(r -> System.out.println(r.getWaypoint_order().toString()));
//		;

//		Prueba Optimize Route
//		List<RequestRoad> roads = new ArrayList<>();
//		roads.add(new RequestRoad("Madrid", new Town(68,"Guadarrama"),28440, "Calle", "ABEDUL",1,11,2,12));
//		roads.add(new RequestRoad("Madrid", new Town(68,"Guadarrama"),28440, "Calle", "ARCE",1,19,2,10));
//		roads.add(new RequestRoad("Madrid", new Town(68,"Guadarrama"),28440, "Calle", "SAUCE",1,1,2,12));
//
//		routeService.calculateRoute(roads,1);
	}
}
