package com.lazypostman.authentication.service;

import com.lazypostman.authentication.dto.LoginResponseDTO;
import com.lazypostman.authentication.dto.UserLoginDTO;

public interface IAuthService {
    LoginResponseDTO checkUser(UserLoginDTO userLoginDTO);
}
