package com.lazypostman.optimizeroute.repository;

import com.lazypostman.optimizeroute.entity.MadridStreets;
import com.lazypostman.optimizeroute.entity.PostalCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IPostalCodesRepo extends JpaRepository<PostalCodes, Integer> {

    @Query("SELECT postCode FROM PostalCodes WHERE cdmuni = :cdmuni")
    List<Integer> findPostCodeByCdmuni(Integer cdmuni) throws Exception;
}
