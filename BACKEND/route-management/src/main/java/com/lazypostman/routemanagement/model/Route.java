package com.lazypostman.routemanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
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

    @Column(name = "name", nullable = false)
    private String name;


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "route", nullable = false,columnDefinition = "json")
    private List<WayPoint> route;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "itinerary", nullable = false,columnDefinition = "json")
    private List<Itinerary> itinerary;
}
