package com.lazypostman.optimizeroute.service;


import com.lazypostman.optimizeroute.model.geocoding.GeocodingLocation;
import com.lazypostman.optimizeroute.model.geocoding.GeocodingResponse;
import com.lazypostman.optimizeroute.model.geocoding.GeocodingResult;
import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import com.lazypostman.optimizeroute.model.requestroute.RouteResponse;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {
    @Autowired
    private GeocodingService geocodingService;
    private static final String API_KEY = "YOUR_API_KEY";

    public List<Waypoint> geocodingWaypoints(List<RequestRoad> requestRoads) {
        List<Waypoint> waypoints = new ArrayList<>();
        for (RequestRoad requestRoad : requestRoads) {
            String address = requestRoad.getRoadName()+ ", "+ requestRoad.getMinOdd() + ", " + requestRoad.getTown() + ", " + requestRoad.getProvince();
            String address1 = requestRoad.getRoadName()+ ", "+ requestRoad.getMaxEven() + ", " + requestRoad.getTown() + ", " + requestRoad.getProvince();
            GeocodingLocation location = geocodingService.getCoordinates(address);
            GeocodingLocation location1 = geocodingService.getCoordinates(address1);
            waypoints.add(new Waypoint(location.getLat(), location.getLng()));
            waypoints.add(new Waypoint(location1.getLat(), location1.getLng()));
        }
        return waypoints;
    }
    public RouteResponse calculateRoute(String origin, List<Waypoint> waypoints) {

        String waypointsStr = waypoints.stream()
                .map(waypoint -> waypoint.getLat() + "," + waypoint.getLng())
                .collect(Collectors.joining("|"));

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/directions/json")
                .queryParam("origin", origin)
                .queryParam("destination", origin)
                .queryParam("waypoints", "optimize:true|" + waypointsStr)
                .queryParam("key", API_KEY)
                .build();

        return new RestTemplate().getForObject(uri.toUriString(), RouteResponse.class);
    }

}
