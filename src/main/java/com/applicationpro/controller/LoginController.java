package com.applicationpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login", "/"})  //localhost:8080/ or localhost:8080/login
    public String login() {
        return "login";
    }

    @RequestMapping("/main2")
    public String welcome() {
        return "main2";
    }

    //dashboard page added
    @RequestMapping("/dashboard.html")
    public String dashboard() {
        return "dashboard";
    }
}
