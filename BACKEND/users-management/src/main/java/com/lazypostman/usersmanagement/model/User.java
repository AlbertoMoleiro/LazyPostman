package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname1", nullable = false)
    private String lastname1;

    @Column(name = "lastname2")
    private String lastname2;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "register", nullable = false)
    private Date register;

    @Column(name = "id_manager", nullable = false)
    private Integer manager;

    @ManyToOne
    @JoinColumn(name = "id_company", nullable = false)
    private Company company;

    @Column(name = "id_role")
    private Integer rol;

}
