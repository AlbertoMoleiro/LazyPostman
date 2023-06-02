package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvinceRepository extends JpaRepository<Province, Integer> {
}
