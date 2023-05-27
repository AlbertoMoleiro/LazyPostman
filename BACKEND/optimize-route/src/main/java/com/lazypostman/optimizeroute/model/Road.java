package com.lazypostman.optimizeroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Road {
    private String name;
    private int minOdd;
    private int maxOdd;
    private int minEven;
    private int maxEven;
}
