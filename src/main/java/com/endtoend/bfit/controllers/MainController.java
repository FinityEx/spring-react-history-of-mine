package com.endtoend.bfit.controllers;

import com.endtoend.bfit.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String mainPage(){
        return "Hello world";
    }

    @PostMapping("/createUser/{username}/{password}")
    public void createUser(@PathVariable() String username, @PathVariable String password){
        userService.saveUser(username, password);
    }

    @GetMapping("/findUser/{username}")
    public String findUser(@PathVariable String username){
        var user = userService.getUser(username);
        if(user.get() != null){
            return user.get().getId().toString();
        }
        return null;
    }


}
