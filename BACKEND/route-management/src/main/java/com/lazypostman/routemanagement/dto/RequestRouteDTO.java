package com.lazypostman.routemanagement.dto;

import com.lazypostman.routemanagement.model.Itinerary;
import com.lazypostman.routemanagement.model.WayPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRouteDTO {
    private String name;

    private List<WayPoint> route;

    private List<Itinerary> itinerary;
}
