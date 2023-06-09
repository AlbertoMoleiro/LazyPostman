package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.repository.IUserRepository;
import com.lazypostman.routemanagement.repository.IUserRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserRouteImp implements IUserRouteService{

    @Autowired
    private IUserRouteRepository userRouteRepo;
    @Autowired
    private IUserRepository userRepo;

    @Override
    public UserRoute createUserRoute(UserRoute userRoute) {
        return userRouteRepo.save(userRoute);
    }

    @Override
    public List<Integer> getRoutesUser(Integer id) {
        List<Integer> manager = userRepo.findAll().stream().filter(user -> user.getIdRole()<=2)
                .map(user -> user.getId())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
                
        if(manager.contains(id)){
            List<Integer> usuarios = userRepo.findAll().stream().filter(user -> user.getManagerId()==id)
                    .map(user -> user.getId())
                    .collect(Collectors.toList());
            usuarios.add(id);

            return userRouteRepo.findAll().stream()
                    .filter(userRoute -> usuarios.contains(userRoute.getId().getUserId()))
                    .map(userRoute -> userRoute.getId().getRouteId())
                    .collect(Collectors.toList());

        }else{
            return userRouteRepo.findAll().stream().filter(userRoute -> userRoute.getId().getUserId()==id )
                    .map(userRoute -> userRoute.getId().getRouteId())
                    .collect(Collectors.toList());
        }


    }
}
