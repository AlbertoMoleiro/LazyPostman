package com.lazypostman.optimizeroute.model.requestroute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeocodedWaypoint {
    private String geocoder_status;
    private String place_id;
    private List<String> types;
}
