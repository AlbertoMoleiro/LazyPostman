package com.lazypostman.optimizeroute.dto;

import com.lazypostman.optimizeroute.model.requestroute.RequestRoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRoadDTO {
    String routeName;
    List<RequestRoad> roads;
}
