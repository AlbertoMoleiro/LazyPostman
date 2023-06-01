package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
