package com.lazypostman.optimizeroute.model.requestroute;


import com.lazypostman.optimizeroute.model.formcreator.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoad {
    private String province;
    private Town town;
    private Integer postCode;
    private String roadType;
    private String roadName;
    private Integer minOdd;
    private Integer maxOdd;
    private Integer minEven;
    private Integer maxEven;
}
