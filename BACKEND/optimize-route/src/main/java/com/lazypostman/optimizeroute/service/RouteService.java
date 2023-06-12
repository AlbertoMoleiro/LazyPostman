package com.lazypostman.optimizeroute.service;


import com.lazypostman.optimizeroute.dto.RouteCreatorDTO;
import com.lazypostman.optimizeroute.model.ItineraryItem;
import com.lazypostman.optimizeroute.model.geocoding.GeocodingLocation;
import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import com.lazypostman.optimizeroute.model.requestroute.RouteResponse;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import com.lazypostman.optimizeroute.repository.IMadridStreetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    private IMadridStreetsRepo madridStreetsRepo;
    private static final String API_KEY = "AIzaSyAx9PZiui8q41GiFN9Y_daSCyCpVYywQpw";
    public List<Waypoint> calculateRoute(List<RequestRoad> roads, Integer idUser,String routeName) throws Exception{
        //Obtener coordenadas de las calles
        List<Waypoint> waypoints = geocodingWaypoints(roads);

        //Obtener origen
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8082")
                .path("/route-management/company/" + idUser)
                .build();

        String origin = new RestTemplate().getForObject(uri.toUriString(), String.class);

        //Obtener coordenadas del origen
        GeocodingLocation geocodedOrigin = geocodingService.getCoordinates(origin);

        Waypoint originWaypoint = new Waypoint(geocodedOrigin.getLat(), geocodedOrigin.getLng());

        //Optimizar ruta
        List<Integer> order = optimizeRoute(waypoints,originWaypoint);

        //obtener máximo y mínimo de cada calle
        List<ItineraryItem> orderedRoads = new ArrayList<>();
        for (RequestRoad road : roads) {
            int minOdd = road.getMinOdd()!=null?road.getMinOdd():Integer.MAX_VALUE;
            int minEven = road.getMinEven()!=null?road.getMinEven():Integer.MAX_VALUE;
            int maxOdd = road.getMaxOdd()!=null?road.getMaxOdd():Integer.MIN_VALUE;
            int maxEven = road.getMaxEven()!=null?road.getMaxEven():Integer.MIN_VALUE;
            orderedRoads.add(new ItineraryItem(road.getProvince(), road.getTown(), road.getPostCode(),road.getRoadType(), road.getRoadName(), Math.min(minOdd, minEven),0.0,0.0));
            orderedRoads.add(new ItineraryItem(road.getProvince(), road.getTown(), road.getPostCode(),road.getRoadType(), road.getRoadName(), Math.max(maxOdd, maxEven),0.0,0.0));
        }

        //Ordenar roads
        List<ItineraryItem> orderedItinerary = new ArrayList<>();
        for (Integer i : order) {
            orderedItinerary.add(orderedRoads.get(i));
        }
        //Crear Itinerario
        List<ItineraryItem> itinerary = createItinerary(orderedItinerary);

        //Ordenar waypoints
        List<Waypoint> orderedWaypoints = new ArrayList<>();

        for (Integer i : order) {
            orderedWaypoints.add(waypoints.get(i));
        }
        //añadri el origen al principio
        orderedWaypoints.add(0,originWaypoint);

        //Crear Headers y guardar ruta
        HttpHeaders headers = new HttpHeaders();
        headers.set("userId", idUser.toString());

        UriComponents uriSaveRoute = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8082")
                .path("/route-management/create-route")
                .build();

        RouteCreatorDTO routeDTO = new RouteCreatorDTO(routeName, orderedWaypoints, itinerary);
        HttpEntity<RouteCreatorDTO> entity = new HttpEntity<>(routeDTO, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uriSaveRoute.toUriString(), entity, RouteCreatorDTO.class);

        return orderedWaypoints;

    };
    private List<Waypoint> geocodingWaypoints(List<RequestRoad> requestRoads) throws Exception {
        List<Waypoint> waypoints = new ArrayList<>();
        for (RequestRoad requestRoad : requestRoads) {
            int minOdd = requestRoad.getMinOdd()!=null?requestRoad.getMinOdd():Integer.MAX_VALUE;
            int minEven = requestRoad.getMinEven()!=null?requestRoad.getMinEven():Integer.MAX_VALUE;
            int maxOdd = requestRoad.getMaxOdd()!=null?requestRoad.getMaxOdd():Integer.MIN_VALUE;
            int maxEven = requestRoad.getMaxEven()!=null?requestRoad.getMaxEven():Integer.MIN_VALUE;
            String address = requestRoad.getRoadName()+ ", "+ Math.min(minOdd, minEven) + ", " + requestRoad.getTown().getDsmuni() + ", " + requestRoad.getProvince();
            String address1 = requestRoad.getRoadName()+ ", "+ Math.max(maxOdd, maxEven) + ", " + requestRoad.getTown().getDsmuni() + ", " + requestRoad.getProvince();

            GeocodingLocation location = geocodingService.getCoordinates(address);
            GeocodingLocation location1 = geocodingService.getCoordinates(address1);
            waypoints.add(new Waypoint(location.getLat(), location.getLng()));
            waypoints.add(new Waypoint(location1.getLat(), location1.getLng()));
        }
        return waypoints;
    }
    private List<Integer> optimizeRoute(List<Waypoint> waypoints, Waypoint origin) throws Exception {

        String waypointsStr = waypoints.stream()
                .map(waypoint -> waypoint.getLat() + "," + waypoint.getLng())
                .collect(Collectors.joining("|"));

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/directions/json")
                .queryParam("origin", origin.getLat()+","+origin.getLng())
                .queryParam("destination", origin.getLat()+","+origin.getLng())
                .queryParam("waypoints", "optimize:true|" + waypointsStr)
                .queryParam("key", API_KEY)
                .build();

        RouteResponse route =new RestTemplate().getForObject(uri.toUriString(), RouteResponse.class);

        return route.getRoutes().get(0).getWaypoint_order();
    }

    private List<ItineraryItem> createItinerary(List<ItineraryItem> roads) throws Exception{

        List<ItineraryItem> stops;
        List<ItineraryItem> itinerary = new ArrayList<>();

        while(roads.size()>0){

            ItineraryItem origin = roads.get(0);
            ItineraryItem destination = roads.get(1);
            int markedToRemove = 0;
            for (int i = 1; i < roads.size() ; i++) {
                if(roads.get(i).getRoadName().equals(origin.getRoadName()) && roads.get(i).getRoadType().equals(origin.getRoadType())){
                    destination = roads.get(i);
                    markedToRemove = i;
                    break;
                }
            }
            Integer startNumber = origin.getRoadNumber()<destination.getRoadNumber()?origin.getRoadNumber():destination.getRoadNumber();
            Integer endNumber = origin.getRoadNumber()>destination.getRoadNumber()?origin.getRoadNumber():destination.getRoadNumber();

            stops = madridStreetsRepo.findCoordsBetween(startNumber, endNumber, origin.getTown().getCdmuni(), origin.getRoadName(), origin.getRoadType()).stream().map(road -> new ItineraryItem(origin.getProvince(), origin.getTown(), origin.getPostCode(), road.getRoadType(), road.getRoadName(), road.getRoadNumber(), road.getCoordX(), road.getCoordY())).collect(Collectors.toList());
            itinerary.addAll(optimizeStops(stops));

            roads.remove(markedToRemove);
            roads.remove(0);
        }
        return itinerary;


    }
    private List<ItineraryItem> optimizeStops(List<ItineraryItem> roads) throws Exception {

        List<ItineraryItem> itinerary = new ArrayList<>();
        ItineraryItem currentStop = roads.get(0);
        roads.remove(0);

         while (!roads.isEmpty()){
                itinerary.add(currentStop);
                ItineraryItem nearestStop = null;
                double minumDistance = Double.MAX_VALUE;
                for (ItineraryItem stop : roads) {
                    assert currentStop != null;
                    double distance = currentStop.distanceBetween(stop);
                    if (distance < minumDistance) {
                        nearestStop = stop;
                        minumDistance = distance;
                    }
                }
                roads.remove(nearestStop);
                currentStop = nearestStop;
         }
            return itinerary;

    }

}
