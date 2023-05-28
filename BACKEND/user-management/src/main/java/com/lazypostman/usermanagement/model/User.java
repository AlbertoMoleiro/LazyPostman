package com.lazypostman.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
    private int idManager;

    @Column(name = "id_company", nullable = false)
    private int idCompany;

    @Column(name = "id_role", nullable = false)
    private int idRole;


}
