package com.lazypostman.usersmanagement.repository;

import com.lazypostman.usersmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findByManagerId(Integer id);
}