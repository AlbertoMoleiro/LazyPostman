package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.dto.NameIdRouteDTO;
import com.lazypostman.routemanagement.model.UserRoute;

import java.util.List;

public interface IUserRouteService {
    UserRoute createUserRoute(UserRoute userRoute);
    List<NameIdRouteDTO> getRoutesUser(Integer id);
}
