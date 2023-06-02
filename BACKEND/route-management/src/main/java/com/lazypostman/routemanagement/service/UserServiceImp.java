package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private IUserRepository userRepo;
    @Override
    public int getCompany(int id) {
        return userRepo.findById(id).get().getIdCompany();
    }
}
