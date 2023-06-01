package com.lazypostman.companyregistration.service;

import com.lazypostman.companyregistration.model.Company;
import com.lazypostman.companyregistration.model.User;
import com.lazypostman.companyregistration.repository.ICompanyRepository;
import com.lazypostman.companyregistration.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyServiceImp implements ICompanyService{

    @Autowired
    private ICompanyRepository repo;

    @Autowired
    private IUserRepository repoUser;

    @Override
    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    @Override
    public Company createCompany(Company company) {

        repo.save(company);
        User user = new User();
        user.setName("admin");
        user.setRegister(LocalDate.now());
        user.setLastname1("admin");
        user.setLastname2("admin");
        user.setLogin("admin");
        user.setPassword("admin");
        user.setIdCompany(company.getId());
        user.setIdRole(1);
        repoUser.save(user);

        return company;
    }

    @Override
    public Company getCompanyById(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void deleteCompany(int id) {
        repo.deleteById(id);
    }

}
