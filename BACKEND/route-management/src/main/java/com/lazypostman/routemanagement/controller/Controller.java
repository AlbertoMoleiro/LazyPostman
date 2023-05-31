package com.lazypostman.routemanagement.controller;

import com.lazypostman.routemanagement.model.Company;
import com.lazypostman.routemanagement.model.Route;
import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.model.UserRouteId;
import com.lazypostman.routemanagement.service.ICompanyService;
import com.lazypostman.routemanagement.service.IRouteService;
import com.lazypostman.routemanagement.service.ITownService;
import com.lazypostman.routemanagement.service.IUserRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route_management")
public class Controller {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IRouteService routeService;
    @Autowired
    private ITownService townService;
    @Autowired
    private IUserRouteService userRouteService;

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes(){
        return new ResponseEntity(routeService.getAllRoutes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") int id){
        return new ResponseEntity(routeService.getRouteById(id), HttpStatus.OK);
    }

    @PostMapping("/create-route")
    public ResponseEntity<Route> createRoute(@RequestBody Route route){
        return new ResponseEntity(routeService.createRoute(route), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Route> updateRoute(@RequestBody Route route){
        return new ResponseEntity(routeService.updateRoute(route), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        routeService.deleteRoute(id);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<String> getLocationCompany(@PathVariable("id") int id){
        String address = companyService.getAddress(id);
        String twon = townService.getTownName(id);
        String location = "Twon: "+twon+" | Address: "+address;
        return new ResponseEntity(location, HttpStatus.OK);
    }

    @PostMapping("/users_routes_create/")
    public ResponseEntity<UserRoute> createUserRoute(@RequestBody UserRouteId userRoute){

        System.out.println(userRoute.toString());
        return new ResponseEntity(userRouteService.createUserRoute(new UserRoute(userRoute)), HttpStatus.OK);
    }
}
