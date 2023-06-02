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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRolService rolService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
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
    newUser.setName(userDTO.getName());
    newUser.setLastname1(userDTO.getLastname1());
    newUser.setLastname2(userDTO.getLastname2());
    newUser.setPhoneNumber(userDTO.getPhoneNumber());
    newUser.setLogin(userDTO.getLogin());
    newUser.setPassword("qwerty"+userDTO.getName());
    newUser.setRegister(Date.valueOf(LocalDate.now()));
    newUser.setManagerId(userDTO.getManagerId());
    newUser.setCompany(userService.getUserById(userDTO.getManagerId()).getCompany());
    newUser.setIdRole(userDTO.getIdRole());

    userService.createUser(newUser);
    // Devolver una respuesta con el código 201 (Created) y la URI del nuevo usuario
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


    @PutMapping("update/password")
    public ResponseEntity<User> changePassword(@RequestHeader("userId") Integer id, @RequestBody PasswordDTO passwordDTO) {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + " not found");
        }
        if (!passwordDTO.getPassword().equals(userAux.getPassword())) {
            throw new RuntimeException("Invalid current password");
        }
        userAux.setPassword(passwordDTO.getNewPassword());
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
}
