package com.lazypostman.usermanagement.service;

import com.lazypostman.usermanagement.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
}
