package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Itinerary;
import com.lazypostman.routemanagement.model.Route;
import com.lazypostman.routemanagement.model.WayPoint;
import com.lazypostman.routemanagement.repository.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImp implements IRouteService {

    @Autowired
    private IRouteRepository repoRoute;
    @Override
    public List<Route> getAllRoutes() {
        return repoRoute.findAll();
    }

    @Override
    public Route getRouteById(Integer id) {
        return repoRoute.findById(id).orElse(null);
    }

    @Override
    public Route createRoute(String nombre, List<WayPoint> waypoints, List<Itinerary> itinerary) {
        Route route = new Route ();
        route.setName(nombre);
        route.setRoute(waypoints);
        route.setItinerary(itinerary);
        return repoRoute.save(route);
    }

}
