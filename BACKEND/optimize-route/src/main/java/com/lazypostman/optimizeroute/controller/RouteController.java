package com.lazypostman.optimizeroute.controller;

import com.lazypostman.optimizeroute.dto.RequestRoadDTO;
import com.lazypostman.optimizeroute.model.formcreator.Road;
import com.lazypostman.optimizeroute.model.formcreator.Town;
import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import com.lazypostman.optimizeroute.service.MadridStreetsService;
import com.lazypostman.optimizeroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private MadridStreetsService madridStreetsService;
    @Autowired
    private RouteService routeService;
    @GetMapping("/towns")
    public ResponseEntity<Set<Town>> getTowns() throws Exception {
        return new ResponseEntity<>(madridStreetsService.getTowns(), HttpStatus.OK);
    }

    @GetMapping("/roads/{cdmuni}")
    public ResponseEntity<List<Road>> getRoadsByTown(@PathVariable("cdmuni") Integer cdmuni) throws Exception {
        return new ResponseEntity<>(madridStreetsService.getRoadsByTown(cdmuni), HttpStatus.OK);
    }

    @PostMapping("/optimize")
    public ResponseEntity<List<Waypoint>> calculateRoute(@RequestBody RequestRoadDTO roads, @RequestHeader("userId") Integer userId) throws Exception {
        return new ResponseEntity<>(routeService.calculateRoute(roads.getRoads(), userId,roads.getRouteName()), HttpStatus.OK);
    }
}
