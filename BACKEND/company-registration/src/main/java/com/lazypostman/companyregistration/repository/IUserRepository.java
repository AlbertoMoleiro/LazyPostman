package com.lazypostman.companyregistration.repository;

import com.lazypostman.companyregistration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
