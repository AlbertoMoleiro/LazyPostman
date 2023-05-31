package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.repository.IUserRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRouteImp implements IUserRouteService{

    @Autowired
    private IUserRouteRepository userRouteRepo;
    @Override
    public UserRoute createUserRoute(UserRoute userRoute) {
        return userRouteRepo.save(userRoute);
    }
}
