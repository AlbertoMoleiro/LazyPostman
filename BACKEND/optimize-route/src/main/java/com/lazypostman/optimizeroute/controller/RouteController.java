package com.lazypostman.optimizeroute.controller;

import com.lazypostman.optimizeroute.dto.RequestRoadDTO;
import com.lazypostman.optimizeroute.dto.TownDTO;
import com.lazypostman.optimizeroute.model.formcreator.Road;
import com.lazypostman.optimizeroute.model.formcreator.Town;
import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import com.lazypostman.optimizeroute.service.IPostalCodesService;
import com.lazypostman.optimizeroute.service.MadridStreetsService;
import com.lazypostman.optimizeroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*",allowCredentials = "false")
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private MadridStreetsService madridStreetsService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private IPostalCodesService postalCodesService;
    @GetMapping("/towns")
    public ResponseEntity<Set<TownDTO>> getTowns() throws Exception {
        Set<TownDTO> towns = madridStreetsService.getTowns().stream().map(town -> {
            List<Integer> postalCodes;
            try {
                postalCodes = postalCodesService.findByCdmuni(town.getCdmuni());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return new TownDTO(town.getCdmuni(), town.getDsmuni(), postalCodes);
        }).collect(Collectors.toSet());
        System.out.println(towns);
        return new ResponseEntity<>(towns, HttpStatus.OK);

    }

    @GetMapping("/roads/{cdmuni}")
    public ResponseEntity<List<Road>> getRoadsByTown(@PathVariable("cdmuni") Integer cdmuni) throws Exception {
        return new ResponseEntity<>(madridStreetsService.getRoadsByTown(cdmuni), HttpStatus.OK);
    }

    @PostMapping("/optimize")
    public ResponseEntity<List<Waypoint>> calculateRoute(@RequestBody RequestRoadDTO roads, @RequestHeader("userId") Integer userId) throws Exception {
        System.out.println(roads);
        //Cambiar la clase de TownDTO a Town
        List<RequestRoad> roadsParsed = roads.getRoads().stream().map(road -> new RequestRoad(road.getProvince(),new Town(road.getTown().getCdmuni(),road.getTown().getDsmuni()),road.getPostCode(),road.getRoadType(),road.getRoadName(), road.getMinOdd(),road.getMaxOdd(),road.getMinEven(),road.getMaxEven())).collect(Collectors.toList());
        return new ResponseEntity<>(routeService.calculateRoute(roadsParsed, userId,roads.getRouteName()), HttpStatus.OK);
    }
}
