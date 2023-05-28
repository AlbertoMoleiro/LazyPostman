package com.lazypostman.optimizeroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @GetMapping("/route")
    public String getRoute() {
        return "Hello World";
    }
}
