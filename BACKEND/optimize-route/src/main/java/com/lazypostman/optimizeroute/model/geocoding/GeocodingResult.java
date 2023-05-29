package com.lazypostman.optimizeroute.model.geocoding;

import com.lazypostman.optimizeroute.model.geocoding.GeocodingGeometry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingResult {
    private String formatted_address;
    private GeocodingGeometry geometry;
}
