package com.lazypostman.optimizeroute.model.requestroute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoad {
    private String province;
    private String town;
    private Integer postCode;
    private String roadType;
    private String roadName;
    private Integer minOdd;
    private Integer maxOdd;
    private Integer minEven;
    private Integer maxEven;
}
