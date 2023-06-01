package com.lazypostman.companyregistration.service;

import com.lazypostman.companyregistration.model.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> getAllCompanies();
    Company createCompany(Company company);
    Company getCompanyById(int id);

    void deleteCompany(int id);
}
