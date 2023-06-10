package com.lazypostman.routemanagement.model;


import com.lazypostman.routemanagement.dto.TownDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Itinerary {
    private String province;
    private TownDTO town;
    private Integer postCode;
    private String roadType;
    private String roadName;
    private Integer roadNumber;
}
