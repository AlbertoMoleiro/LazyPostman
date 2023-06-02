package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
