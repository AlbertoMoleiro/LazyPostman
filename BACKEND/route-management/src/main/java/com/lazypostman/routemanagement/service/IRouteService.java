package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Itinerary;
import com.lazypostman.routemanagement.model.Route;
import com.lazypostman.routemanagement.model.WayPoint;

import java.util.List;

public interface IRouteService {
    List<Route> getAllRoutes();
    Route getRouteById(Integer id);
    Route createRoute(String nombre, List<WayPoint> waypoints, List<Itinerary> itinerary)throws Exception;
}
