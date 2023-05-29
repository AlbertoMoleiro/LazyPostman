package com.lazypostman.usermanagement.service;

import com.lazypostman.usermanagement.model.User;
import com.lazypostman.usermanagement.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setLastname1(user.getLastname1());
        existingUser.setLastname2(user.getLastname2());
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        existingUser.setRegister(user.getRegister());
        existingUser.setIdManager(user.getIdManager());
        existingUser.setIdCompany(user.getIdCompany());
        existingUser.setIdRole(user.getIdRole());

        // Actualiza otros campos según sea necesario

        return repo.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }
}
