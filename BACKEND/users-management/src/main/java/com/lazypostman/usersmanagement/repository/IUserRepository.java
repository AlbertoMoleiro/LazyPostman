package com.lazypostman.usersmanagement.repository;

import com.lazypostman.usersmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
