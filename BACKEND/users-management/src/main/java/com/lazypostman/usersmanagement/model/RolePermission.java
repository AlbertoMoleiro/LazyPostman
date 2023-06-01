package com.lazypostman.usersmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles_permissions")
public class RolePermission {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Rol rol;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_permission")
    private Permission permission;
}
