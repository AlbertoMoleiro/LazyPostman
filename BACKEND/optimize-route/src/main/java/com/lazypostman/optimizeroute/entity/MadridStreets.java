package com.lazypostman.optimizeroute.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "madrid_streets")
public class MadridStreets {
    @Id
    private Integer id;
    private Integer cdmuni;
    private String dsmuni;
    private String cdtvia;
    private String dspart;
    private String dsvial;
    @Column(name = "dsvial_nor")
    private String dsvialNor;
    private Integer numero;
    @Column(name="coord_x")
    private Double coordX;
    @Column(name="coord_y")
    private Double coordY;

}
