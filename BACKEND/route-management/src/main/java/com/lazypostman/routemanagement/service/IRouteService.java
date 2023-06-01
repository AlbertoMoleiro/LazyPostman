package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Route;

import java.util.List;

public interface IRouteService {
    List<Route> getAllRoutes();
    Route getRouteById(Integer id);
    Route createRoute(Route route);
    Route updateRoute(Route route);
    void deleteRoute(Integer id);
}
