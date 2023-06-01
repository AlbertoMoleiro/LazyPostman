package com.lazypostman.optimizeroute.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface IPostalCodesService {
    List<Integer> findByCdmuni(Integer cdmuni) throws Exception;
}
