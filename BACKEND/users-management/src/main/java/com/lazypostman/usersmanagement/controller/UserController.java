package com.lazypostman.usersmanagement.controller;

import com.lazypostman.usersmanagement.exceptions.ModelNotFoundException;
import com.lazypostman.usersmanagement.model.User;
import com.lazypostman.usersmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

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

    @GetMapping("/responsibles/{id}/users")
    public ResponseEntity<List<User>> getUsersByResponsibility(@PathVariable("id") Integer id) {
        List<User> users = userService.getUsersUnderResponsibility(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) throws Exception {
        User userAux = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userAux.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userAux = userService.getUserById(user.getId());
        if (userAux == null) {
            throw new ModelNotFoundException("User " + user.getId() + " not found");
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<User> changePassword(@PathVariable("id") Integer id, @RequestBody String newPassword) {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + " not found");
        }
        User updatedUser = userService.changePassword(id, newPassword);
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
