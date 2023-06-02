package com.lazypostman.authentication.service;

import com.lazypostman.authentication.dto.LoginResponseDTO;
import com.lazypostman.authentication.dto.UserLoginDTO;
import com.lazypostman.authentication.entity.User;
import com.lazypostman.authentication.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private IUserRepo userRepo;


    public LoginResponseDTO checkUser(UserLoginDTO userLoginDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepo.findByLogin(userLoginDTO.getLogin());
        System.out.println(encoder.encode("password"));
        if (user != null && encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return new LoginResponseDTO(user.getId(), user.getIdRole());
        } else {
            return null;
        }
    }

}
