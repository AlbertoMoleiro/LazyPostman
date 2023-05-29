package com.lazypostman.optimizeroute.model.requestroute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {

    private List<GeocodedWaypoint> geocoded_waypoints;
    private List<Route> routes;
    private String status;
}
