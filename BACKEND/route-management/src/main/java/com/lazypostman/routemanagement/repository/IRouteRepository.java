package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Route, Integer> {
}
