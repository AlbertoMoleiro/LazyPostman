package com.lazypostman.optimizeroute.service;

import com.lazypostman.optimizeroute.repository.IPostalCodesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostalCodesService implements IPostalCodesService{
    @Autowired
    private IPostalCodesRepo postalCodesRepo;

    @Override
    public List<Integer> findByCdmuni(Integer cdmuni) throws Exception {
        return postalCodesRepo.findPostCodeByCdmuni(cdmuni);
    }

}
