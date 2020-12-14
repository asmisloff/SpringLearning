package ru.asmisloff.spring.ht08.controllers;

import ru.asmisloff.spring.ht08.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //@GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

}
