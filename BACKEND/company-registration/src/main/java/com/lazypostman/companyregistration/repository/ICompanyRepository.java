package com.lazypostman.companyregistration.repository;

import com.lazypostman.companyregistration.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Integer> {
}
