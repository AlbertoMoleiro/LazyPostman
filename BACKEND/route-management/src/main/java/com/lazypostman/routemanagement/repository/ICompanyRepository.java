package com.lazypostman.routemanagement.repository;

import com.lazypostman.routemanagement.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Integer> {
}
