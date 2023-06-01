package com.lazypostman.optimizeroute.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postal_codes")
public class PostalCodes {
    @Id
    private Integer id;
    @Column(name="post_code")
    private Integer postCode;
    private Integer cdmuni;
    private String dsmuni;
}
