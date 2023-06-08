package com.lazypostman.usersmanagement.repository;

import com.lazypostman.usersmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("FROM User WHERE company.id = :companyId AND idRole > 1")
    List<User> findAllByCompany_Id(@Param("companyId") Integer company_id);
    List<User> findAllByManagerId(Integer managerId);


}
