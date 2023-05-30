package com.lazypostman.usermanagement.controller;

import com.lazypostman.usermanagement.exceptions.ModelNotFoundException;
import com.lazypostman.usermanagement.model.User;
import com.lazypostman.usermanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
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

    @GetMapping("/hateos/{id}")
    public EntityModel<User> getUserByIdH(@PathVariable("id") int id) throws Exception {
        User userAux = userService.getUserById(id);
        if (userAux == null) {
            throw new ModelNotFoundException("User " + id + " not found");
        }
        WebMvcLinkBuilder LinkOne = linkTo(methodOn(this.getClass()).getUserById(id));
        return EntityModel.of(userAux).add(LinkOne.withRel("user-link"));
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
            throw  new ModelNotFoundException("User " + user.getId() + " not found");
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
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
