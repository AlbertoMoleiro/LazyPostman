package com.lazypostman.usersmanagement.service;

import com.lazypostman.usersmanagement.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id);

    User changePassword(Integer id, String newPassword);
}

