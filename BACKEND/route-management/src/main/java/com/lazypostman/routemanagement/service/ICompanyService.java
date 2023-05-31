package com.lazypostman.routemanagement.service;

import com.lazypostman.routemanagement.model.Company;

public interface ICompanyService {
    String getAddress(int id);
    int getTown(int id);
}
