package com.lazypostman.optimizeroute.model.requestroute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
//    private Bounds bounds;
//    private String copyrights;
//    private List<Leg> legs;
//    private Polyline overview_polyline;
    private String summary;
    private List<String> warnings;
    private List<Integer> waypoint_order;
}
