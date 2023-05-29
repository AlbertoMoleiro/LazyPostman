package com.lazypostman.optimizeroute.controller;

import com.lazypostman.optimizeroute.model.formcreator.Road;
import com.lazypostman.optimizeroute.model.formcreator.Town;
import com.lazypostman.optimizeroute.service.MadridStreetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private MadridStreetsService madridStreetsService;
    @GetMapping("/towns")
    public ResponseEntity<Set<Town>> getTowns() {
        return new ResponseEntity<>(madridStreetsService.getTowns(), HttpStatus.OK);
    }

    @GetMapping("/roads/{cdmuni}")
    public ResponseEntity<List<Road>> getRoadsByTown(@PathVariable("cdmuni") Integer cdmuni) {
        return new ResponseEntity<>(madridStreetsService.getRoadsByTown(cdmuni), HttpStatus.OK);
    }
}
