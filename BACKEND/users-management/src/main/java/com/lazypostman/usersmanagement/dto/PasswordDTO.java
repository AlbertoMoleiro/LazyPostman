package com.lazypostman.usersmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordDTO {
    private int idUser;
    private String password;
    private String newPassword;
}
