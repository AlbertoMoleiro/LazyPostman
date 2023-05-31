package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITownRepository extends JpaRepository<Town, Integer> {
}
