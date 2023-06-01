package com.lazypostman.usersmanagement.service;

import com.lazypostman.usersmanagement.model.Rol;
import com.lazypostman.usersmanagement.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolServiceImpl implements IRolService{
    @Autowired
    IRolRepository repo;
    @Override
    public List<Rol> getAllRoles() {
        return repo.findAll();
    }
}
