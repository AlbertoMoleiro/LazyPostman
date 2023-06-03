package com.lazypostman.companyregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private String cif;

    private String businessName;
    private String address;
    private String province;
    private Integer idTown;
    private String phoneNumber;
    private String email;
    private String password;



}
