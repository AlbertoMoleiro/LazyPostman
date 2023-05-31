package com.lazypostman.routemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String cif;

    @Column(name = "business_name",nullable = false)
    private String businessName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "id_town")
    private Integer idTown;

    private String address;
}
