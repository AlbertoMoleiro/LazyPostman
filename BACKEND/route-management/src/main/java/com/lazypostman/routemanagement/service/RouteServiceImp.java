package com.lazypostman.routemanagement.service;


import com.lazypostman.routemanagement.model.*;
import com.lazypostman.routemanagement.repository.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImp implements IRouteService {

    @Autowired
    private IRouteRepository repoRoute;
    @Autowired
    private IUserRouteService userRouteService;
    @Override
    public List<Route> getAllRoutes() {
        return repoRoute.findAll();
    }

    @Override
    public Route getRouteById(Integer id) {
        return repoRoute.findById(id).orElse(null);
    }

    @Override
    public Route createRoute(String nombre, List<WayPoint> waypoints, List<Itinerary> itinerary,Integer userId) {
        Route route = new Route ();
        //System.out.println("***********************" + itinerary);

        route.setName(nombre);
        route.setRoute(waypoints);
        route.setItinerary(itinerary);
        Route creatredRoute = repoRoute.save(route);
        userRouteService.createUserRoute(new UserRoute(new UserRouteId(userId,creatredRoute.getId())));
        return repoRoute.save(route);
    }

    @Override
    public List <Itinerary> getItineraryById(Integer id) {

         return repoRoute.findById(id).get().getItinerary();

    }

}
