package com.lazypostman.optimizeroute.service;

import com.lazypostman.optimizeroute.model.formcreator.Road;
import com.lazypostman.optimizeroute.model.formcreator.Town;
import com.lazypostman.optimizeroute.repository.IMadridStreetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MadridStreetsService implements  IMadridStreetsService{

    @Autowired
    private IMadridStreetsRepo madridStreetsRepo;
//    @Override
//    public Set<Town> getTowns() {
//        return madridStreetsRepo.findAll().stream().map(madridStreets -> new Town(madridStreets.getCdmuni(), madridStreets.getDsmuni())).collect(Collectors.toSet());
//    }

    @Override
    public Set<Town> getTowns() throws Exception {
        return madridStreetsRepo.getTowns().stream()
                .map(projection -> new Town(projection.getCdmuni(), projection.getDsmuni()))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Road> getRoadsByTown(Integer cdmuni) throws Exception {
        return madridStreetsRepo.getRoadData(cdmuni).stream()
                .map(projection -> new Road(
                        projection.getName(),
                        Optional.ofNullable(projection.getMinOdd()).orElse(0),
                        Optional.ofNullable(projection.getMaxOdd()).orElse(0),
                        Optional.ofNullable(projection.getMinEven()).orElse(0),
                        Optional.ofNullable(projection.getMaxEven()).orElse(0)
                ))
                .collect(Collectors.toList());
    }
}
