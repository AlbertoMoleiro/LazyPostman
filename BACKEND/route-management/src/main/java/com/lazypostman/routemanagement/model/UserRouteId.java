package com.lazypostman.routemanagement.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRouteId implements Serializable {

    @Column(name = "id_user")
    @JsonAlias("id_user")
    private Integer userId;

    @Column(name = "id_route")
    @JsonAlias("id_route")
    private Integer routeId;
}
