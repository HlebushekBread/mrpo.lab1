package org.mrpo.lab1.controllers;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.User;
import org.mrpo.lab1.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUserUsers() {
        return userService.findAllUserUsers();
    }
}
