package com.lazypostman.authentication.entity;

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
    private Integer id;

    private String name;

    private String lastname1;

    private String lastname2;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String login;

    private String password;

    private Date register;

    @Column(name = "id_manager")
    private Integer managerId;
    @Column(name = "id_company", nullable = false)
    private Integer idCompany;

    @Column(name = "id_role")
    private Integer idRole;

}
