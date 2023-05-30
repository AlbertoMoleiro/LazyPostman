package com.lazypostman.optimizeroute.model.requestroute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Waypoint {
    private double lat;
    private double lng;
}
