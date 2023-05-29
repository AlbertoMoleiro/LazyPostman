package com.lazypostman.usermanagement.repository;

import com.lazypostman.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
