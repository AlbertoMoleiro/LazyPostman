package com.lazypostman.companyregistration.service;

import com.lazypostman.companyregistration.dto.CompanyDTO;
import com.lazypostman.companyregistration.model.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> getAllCompanies();
    Company createCompany(CompanyDTO company);
    Company getCompanyById(int id);

    void deleteCompany(int id);
}
