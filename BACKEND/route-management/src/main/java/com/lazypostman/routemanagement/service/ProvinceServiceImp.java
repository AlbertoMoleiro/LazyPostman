package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImp implements IProvinceService {

    @Autowired
    IProvinceRepository provinceRepo;
    @Override
    public String getName(int id) {
        return provinceRepo.findById(id).get().getName();
    }
}
