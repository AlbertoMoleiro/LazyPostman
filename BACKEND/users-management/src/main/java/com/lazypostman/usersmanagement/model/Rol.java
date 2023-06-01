package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Rol {
    @Id
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
