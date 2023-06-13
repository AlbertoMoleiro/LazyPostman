package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.dto.NameIdRouteDTO;
import com.lazypostman.routemanagement.model.UserRoute;
import com.lazypostman.routemanagement.repository.IRouteRepository;
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
    @Autowired
    private IRouteRepository routeRepo;

    @Override
    public UserRoute createUserRoute(UserRoute userRoute) {
        return userRouteRepo.save(userRoute);
    }

    @Override
    public List<NameIdRouteDTO> getRoutesUser(Integer id) {
        List<Integer> manager = userRepo.findAll().stream().filter(user -> user.getIdRole()<=2)
                .map(user -> user.getId())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
                
        if(manager.contains(id)){
            List<Integer> usuarios = userRepo.findAll().stream().filter(user -> user.getManagerId()==id)
                    .map(user -> user.getId())
                    .collect(Collectors.toList());
            usuarios.add(id);

            //que no haya rutas iguales
            return userRouteRepo.findAll().stream()
                    .filter(userRoute -> usuarios.contains(userRoute.getId().getUserId()))
                    .map(userRoute -> new NameIdRouteDTO(userRoute.getId().getRouteId(), routeRepo.findById(userRoute.getId().getRouteId()).get().getName())).distinct()
                    .collect(Collectors.toList());

        }else{
            return userRouteRepo.findAll().stream().filter(userRoute -> userRoute.getId().getUserId()==id )
                    .map(userRoute -> new NameIdRouteDTO(userRoute.getId().getRouteId(), routeRepo.findById(userRoute.getId().getRouteId()).get().getName()))
                    .collect(Collectors.toList());
        }


    }
}
