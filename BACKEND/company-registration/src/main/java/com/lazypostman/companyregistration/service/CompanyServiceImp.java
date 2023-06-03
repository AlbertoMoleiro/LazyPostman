package com.lazypostman.companyregistration.service;

import com.lazypostman.companyregistration.dto.CompanyDTO;
import com.lazypostman.companyregistration.model.Company;
import com.lazypostman.companyregistration.model.User;
import com.lazypostman.companyregistration.repository.ICompanyRepository;
import com.lazypostman.companyregistration.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Company createCompany(CompanyDTO company) {
        Company companyPersist = new Company();
        companyPersist.setCif(company.getCif());
        companyPersist.setBusinessName(company.getBusinessName());
        companyPersist.setPhoneNumber(company.getPhoneNumber());
        companyPersist.setEmail(company.getEmail());
        companyPersist.setIdTown(company.getIdTown());
        companyPersist.setAddress(company.getAddress());
        repo.save(companyPersist);


        User user = new User();
        user.setName(company.getBusinessName());
        user.setRegister(LocalDate.now());
        user.setLastname1("admin");
        user.setLogin(company.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(company.getPassword());
        user.setPassword(encryptedPassword);
        user.setIdCompany(companyPersist.getId());
        user.setIdRole(1);
        repoUser.save(user);

        return companyPersist;
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
