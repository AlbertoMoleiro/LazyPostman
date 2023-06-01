package com.lazypostman.optimizeroute.model;

public interface ItinineraryItemProjection {
    String getTown();
    String getRoadType();
    String getRoadName();
    Integer getRoadNumber();
    Double getCoordX();
    Double getCoordY();
}
