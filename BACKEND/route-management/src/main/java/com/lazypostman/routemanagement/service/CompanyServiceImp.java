package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Company;
import com.lazypostman.routemanagement.repository.ICompanyRepository;
import com.lazypostman.routemanagement.repository.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImp implements ICompanyService{

    @Autowired
    private ICompanyRepository repoCompany;
    @Override
    public String getAddress(int id) {
        return repoCompany.findById(id).get().getAddress();
    }

    @Override
    public int getTown(int id) {
        return repoCompany.findById(id).get().getIdTown();
    }


}
