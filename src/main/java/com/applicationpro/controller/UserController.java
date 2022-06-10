package com.applicationpro.controller;

import com.applicationpro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/list")
    public String addUser(Model model) {
        model.addAttribute("users", userService.listAll());
        return "/user/user-list";
    }
}
