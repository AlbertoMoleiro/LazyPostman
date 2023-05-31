package com.lazypostman.optimizeroute.service;

import com.lazypostman.optimizeroute.model.formcreator.Road;
import com.lazypostman.optimizeroute.model.formcreator.Town;

import java.util.List;
import java.util.Set;

public interface IMadridStreetsService {

    Set<Town> getTowns() throws Exception;

    List<Road> getRoadsByTown(Integer cdmuni) throws Exception;
}
