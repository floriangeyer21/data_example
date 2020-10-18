package com.data.example.data_example.controllers;

import com.data.example.data_example.model.User;
import com.data.example.data_example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/search/{id}")
    public String getUserById(@PathVariable Long id) {
        String user = "";
        user = userService.getById(id).toString();
        return user;
    }

   /* @GetMapping("/users/all")
    public List<User> getAllUsers() {
         return userService.getAll();
    }*/
}
