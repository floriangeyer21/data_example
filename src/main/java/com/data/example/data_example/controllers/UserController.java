package com.data.example.data_example.controllers;

import com.data.example.data_example.model.User;
import com.data.example.data_example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
@CrossOrigin("*")
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

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("count", userService.count());
        return modelAndView;*/
        List<User> users = new ArrayList<>();
        users.add(userService.getById(1L));
        return users;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user) {
        User oldUser = userService.getById(user.getId());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setGroups(user.getGroups());
        oldUser.setTweets(user.getTweets());
        userService.save(oldUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
