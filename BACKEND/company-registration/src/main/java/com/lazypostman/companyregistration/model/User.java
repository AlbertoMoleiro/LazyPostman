package com.lazypostman.companyregistration.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastname1;
    private String lastname2;
    private String phoneNumber;
    private String login;
    private String password;
    private LocalDate register;
    @Column(name = "id_company", nullable = false)
    private Integer idCompany;

    @Column(name = "id_role", nullable = false)
    private Integer idRole;

}
