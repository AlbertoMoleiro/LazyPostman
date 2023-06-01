package com.lazypostman.optimizeroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TownDTO {
    private Integer cdmuni;
    private String dsmuni;
    private List<Integer> postCodes;
}
