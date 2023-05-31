package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.model.UserRouteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRouteRepository extends JpaRepository<UserRoute, UserRouteId> {
}
