package com.lazypostman.usersmanagement.controller;

import com.lazypostman.usersmanagement.dto.PasswordDTO;
import com.lazypostman.usersmanagement.dto.UserDTO;
import com.lazypostman.usersmanagement.exceptions.ModelNotFoundException;
import com.lazypostman.usersmanagement.model.Rol;
import com.lazypostman.usersmanagement.model.User;
import com.lazypostman.usersmanagement.service.IRolService;
import com.lazypostman.usersmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRolService rolService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<UserDTO>> getAllUsers(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ModelNotFoundException("User " + userId + " not found");
        }

        List<UserDTO> userDTOs;
        if (user.getIdRole() == 1) {
            // Usuario con rol "Admin"
            List<User> users = userService.getAllUsersByCompanyId(user.getCompany().getId());
            userDTOs = convertToDTOs(users);
        } else if (user.getIdRole() == 2) {
            // Usuario con rol "Manager"
            List<User> users = userService.getAllUsersByManagerId(userId);
            userDTOs = convertToDTOs(users);
        } else {
            throw new RuntimeException("Invalid user role");
        }

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + "Not found");
        }
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getAllRoles() {
        return new ResponseEntity<>(rolService.getAllRoles(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setId(userDTO.getId());
        newUser.setName(userDTO.getName());
        newUser.setLastname1(userDTO.getLastname1());
        newUser.setLastname2(userDTO.getLastname2());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        newUser.setLogin(userDTO.getLogin());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode("qwerty" + userDTO.getName());
        newUser.setPassword(encryptedPassword);
        newUser.setRegister(Date.valueOf(LocalDate.now()));
        newUser.setManagerId(userDTO.getManagerId());
        newUser.setCompany(userService.getUserById(userDTO.getManagerId()).getCompany());
        newUser.setIdRole(userDTO.getIdRole());

        userService.createUser(newUser);

        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).build();
    }


@PutMapping("/update")
public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO) {
    User userAux = userService.getUserById(userDTO.getId());
    if (userAux == null) {
        throw new ModelNotFoundException("User " + userService.getUserById(userDTO.getId()) + " not found");
    }

    userAux.setName(userDTO.getName());
    userAux.setLastname1(userDTO.getLastname1());
    userAux.setLastname2(userDTO.getLastname2());
    userAux.setPhoneNumber(userDTO.getPhoneNumber());
    userAux.setManagerId(userDTO.getManagerId());
    userAux.setLogin(userDTO.getLogin());
    userAux.setIdRole(userDTO.getIdRole());

    User updatedUser = userService.updateUser(userAux);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}


    @PutMapping("/update/password")
    public ResponseEntity<User> changePassword(@RequestHeader("userId") Integer id, @RequestBody PasswordDTO passwordDTO) {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + " not found");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(passwordDTO.getPassword(), userAux.getPassword())) {
            throw new RuntimeException("Invalid current password");
        }
        String encodedPassword = passwordEncoder.encode(passwordDTO.getNewPassword());
        userAux.setPassword(encodedPassword);
        User updatedUser = userService.updateUser(userAux);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + " not found");
        }
        userService.deleteUser(id);
    }

    private List<UserDTO> convertToDTOs(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setLastname1(user.getLastname1());
            userDTO.setLastname2(user.getLastname2());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setManagerId(user.getManagerId());
            userDTO.setLogin(user.getLogin());
            userDTO.setIdRole(user.getIdRole());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
