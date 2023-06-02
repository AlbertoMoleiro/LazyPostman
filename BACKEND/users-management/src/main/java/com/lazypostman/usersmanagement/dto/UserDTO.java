package com.lazypostman.usersmanagement.dto;

import com.lazypostman.usersmanagement.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String lastname1;
    private String lastname2;
    private String phoneNumber;
    private Integer managerId;
    private String login;
    private Integer idRole;
}
