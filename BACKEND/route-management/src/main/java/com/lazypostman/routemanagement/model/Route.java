package com.lazypostman.routemanagement.model;

import com.lazypostman.routemanagement.dto.ItineraryConverter;
import com.lazypostman.routemanagement.dto.RouteConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Convert(converter = RouteConverter.class)
    @Column(name = "route", nullable = false,columnDefinition = "json")
    private List<WayPoint> route;

    @Convert(converter = ItineraryConverter.class)
    @Column(name = "itinerary", nullable = false,columnDefinition = "json")
    private List<Itinerary> itinerary;
}
