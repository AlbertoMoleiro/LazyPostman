package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "towns")
public class Town {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
}
