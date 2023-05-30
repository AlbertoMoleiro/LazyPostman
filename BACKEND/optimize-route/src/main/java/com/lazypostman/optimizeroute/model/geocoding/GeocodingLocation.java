package com.lazypostman.optimizeroute.model.geocoding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingLocation {
    private Double lat;
    private Double lng;
}
