package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    private Long id;

    @Column(name = "cif", nullable = false, unique = true)
    private String cif;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_town")
    private Town town;

    @Column(name = "address", nullable = false)
    private String address;
}
