package com.lazypostman.usersmanagement.service;

import com.lazypostman.usersmanagement.model.User;
import com.lazypostman.usersmanagement.repository.IUserRepository;
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
    public User updateUser(User user) {
        User existingUser = repo.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setLastname1(user.getLastname1());
        existingUser.setLastname2(user.getLastname2());
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        existingUser.setRegister(user.getRegister());
        existingUser.setManagerId(user.getManagerId());
        existingUser.setCompany(user.getCompany());
        existingUser.setIdRole(user.getIdRole());


        return repo.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public User changePassword(Integer id, String newPassword) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setPassword(newPassword);
        return repo.save(existingUser);
    }

    @Override
    public List<User> getAllUsersByCompanyId(Integer companyId) {
        return repo.findAllByCompany_Id(companyId);
    }

    @Override
    public List<User> getAllUsersByManagerId(Integer managerId) {
        return repo.findAllByManagerId(managerId);
    }

}
