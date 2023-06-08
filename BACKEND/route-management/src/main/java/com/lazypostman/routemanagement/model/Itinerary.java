package com.lazypostman.routemanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Itinerary {
    private String province;
    private Town town;
    private Integer postCode;
    private String roadType;
    private String roadName;
    private Integer roadNumber;
}
