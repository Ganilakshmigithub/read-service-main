package com.spring.read_service.controllers;


import com.spring.read_service.CustomAnnotations.Admin;
import com.spring.read_service.CustomAnnotations.User;
import com.spring.read_service.entities.Users;
import com.spring.read_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        System.out.println(user.toString());
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        return userService.verify(user);
    }

    @Admin
    @GetMapping("/admin")
    public String WelcometoAdmin() {
        return "Welcome Admin";
    }

    @User
    @GetMapping("/user")
    public String WelcometoUser() {
        return "Welcome User";
    }
}