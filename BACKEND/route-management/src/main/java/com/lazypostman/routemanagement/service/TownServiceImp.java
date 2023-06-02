package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.repository.ITownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImp implements ITownService {

    @Autowired
    ITownRepository repoTown;

    @Override
    public String getTownName(int id) {
        return repoTown.findById(id).get().getName();
    }

    @Override
    public String getPostalCode(int id) {
        return repoTown.findById(id).get().getPostalCode();
    }

    @Override
    public int getProvinceId(int id) {
        return repoTown.findById(id).get().getIdProvince();
    }
}
