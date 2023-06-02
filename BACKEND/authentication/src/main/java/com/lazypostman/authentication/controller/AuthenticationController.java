package com.lazypostman.authentication.controller;

import com.lazypostman.authentication.dto.LoginResponseDTO;
import com.lazypostman.authentication.dto.UserLoginDTO;
import com.lazypostman.authentication.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private IAuthService authService;

    //End point to check if the user is registered
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        LoginResponseDTO user = authService.checkUser(userLoginDTO);
        System.out.println(user);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login credentials");
        }
    }

}
