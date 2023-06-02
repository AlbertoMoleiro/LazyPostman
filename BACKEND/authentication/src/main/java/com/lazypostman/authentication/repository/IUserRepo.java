package com.lazypostman.authentication.repository;

import com.lazypostman.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepo extends JpaRepository<User, Integer> {
    @Query("from User u where u.login = :login")
    User findByLogin( @Param("login") String login);
}
