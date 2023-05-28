package com.lazypostman.optimizeroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {
    private String origin;
    private String destination;
    private List<Road> roads;
        @Data
        static class Road {
            private String name;
            private int minOdd;
            private int maxOdd;
            private int minEven;
            private int maxEven;
        }

}
