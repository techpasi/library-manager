package com.pasindu.librarymanager.controller;

import com.pasindu.librarymanager.entity.User;
import com.pasindu.librarymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {


        // Authentication logic to be added
        // For simplicity, assuming authentication is successful
        return userService.save(user);
    }
}
