package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Route;
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
    public Route createRoute(Route route) {
        return repoRoute.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        Route existingRoute = repoRoute.findById(route.getId()).orElse(null);
        if (existingRoute != null) {
            existingRoute.setName(route.getName());
            existingRoute.setRoute(route.getRoute());

            return repoRoute.save(existingRoute);
        }
        return null;
    }

    @Override
    public void deleteRoute(Integer id) {
       repoRoute.deleteById(id);
    }
}
