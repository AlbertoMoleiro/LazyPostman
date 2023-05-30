package com.lazypostman.usersmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname1;

    private String lastname2;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date register;
    @Column(name = "id_manager")
    private Integer idManager;

    @Column(name = "id_company", nullable = false)
    private Integer idCompany;

    @Column(name = "id_role", nullable = false)
    private Integer idRole;


}
