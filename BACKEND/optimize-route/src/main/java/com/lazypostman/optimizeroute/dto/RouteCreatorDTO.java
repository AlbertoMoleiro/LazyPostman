package com.lazypostman.optimizeroute.dto;

import com.lazypostman.optimizeroute.model.ItineraryItem;
import com.lazypostman.optimizeroute.model.requestroute.Waypoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteCreatorDTO {
    String name;
    List<Waypoint> route;
    List<ItineraryItem> itinerary;
}
