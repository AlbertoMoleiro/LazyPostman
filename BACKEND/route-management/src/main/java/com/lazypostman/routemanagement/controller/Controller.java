package com.lazypostman.routemanagement.controller;

import com.lazypostman.routemanagement.dto.RequestRouteDTO;
import com.lazypostman.routemanagement.model.Route;
import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.model.UserRouteId;
import com.lazypostman.routemanagement.model.WayPoint;
import com.lazypostman.routemanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"},allowedHeaders = "*")
@RestController
@RequestMapping("/route-management")
public class Controller {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IRouteService routeService;
    @Autowired
    private ITownService townService;
    @Autowired
    private IUserRouteService userRouteService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/{id}")
    public ResponseEntity<List<WayPoint>> getRouteById(@PathVariable("id") int id){
        return new ResponseEntity(routeService.getRouteById(id).getRoute(), HttpStatus.OK);
    }

    @GetMapping("itinerary/{id}")
    public ResponseEntity<Route> getItinerary(@PathVariable("id") Integer id){
        return new ResponseEntity(routeService.getItineraryById(id), HttpStatus.OK);
    }


    @PostMapping(path="/create-route")
    public ResponseEntity<Route> createRoute(@RequestBody RequestRouteDTO route)throws Exception {
        return new ResponseEntity(routeService.createRoute(route.getName(),route.getRoute(),route.getItinerary()), HttpStatus.OK);
    }

    //•	GET /route-management /users-routes/{userId}: permite a los usuarios obtener su lista de rutas disponibles.

    @PostMapping("/users-routes-create/")
    public ResponseEntity<UserRoute> createUserRoute(@RequestBody UserRouteId userRoute){
        return new ResponseEntity(userRouteService.createUserRoute(new UserRoute(userRoute)), HttpStatus.OK);
    }

    //•	GET /route-management /manager/routes /{userId}: Obtiene la lista de rutas de disponible
    // para el usuario responsable, estas son las rutas propias y las de los empleados a su cargo.

    @GetMapping("/company/{id}")
    public ResponseEntity<String> getLocation(@PathVariable("id") int id){
        Integer idCompany = userService.getCompany(id);
        String address = companyService.getAddress(idCompany);
        Integer idTown = companyService.getTown(idCompany);
        String townName = townService.getTownName(idTown);
        String postalCode = townService.getPostalCode(idTown);
        Integer idProvince = townService.getProvinceId(idTown);
        String province = provinceService.getName(idProvince);

        String location = townName+" "+postalCode+" "+province+" Calle "+address;
        return new ResponseEntity(location, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes(){
        return new ResponseEntity(routeService.getAllRoutes(), HttpStatus.OK);
    }
}
