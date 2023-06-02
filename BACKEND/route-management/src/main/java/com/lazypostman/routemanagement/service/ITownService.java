package com.lazypostman.routemanagement.service;

public interface ITownService {
    String getTownName(int id);
    String getPostalCode(int id);

    int getProvinceId(int id);
}
