package com.lazypostman.usersmanagement.repository;

import com.lazypostman.usersmanagement.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}
