package com.lazypostman.routemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users_routes")
public class UserRoute {

    @EmbeddedId
    private UserRouteId id;

/*    @Column(name = "id_user", nullable = false)
    private Integer idUser;


    @Column(name = "id_route", nullable = false)
    private Integer idRoute;*/
}
