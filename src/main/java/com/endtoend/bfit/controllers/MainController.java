package com.endtoend.bfit.controllers;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import com.endtoend.bfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class MainController {

    @Autowired
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String mainPage(){
        return "Hello world";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserForm userForm){
        userService.saveUser(userForm);
        return "udalo sie";
    }

    @GetMapping("/findUser/")
    public User findUser(UserForm userForm){
        var user = userService.getUser(userForm.getUsername());
        return user.orElse(null);
    }

    @DeleteMapping("/deleteUser/")
    public void deleteUser(@RequestBody UserForm userForm) {
        var user = userService.getUser(userForm.getUsername());
        if (user.isPresent() && Objects.equals(user.get().getPassword(), userForm.getPassword())) {
            userService.deleteUser(userForm.getUsername());
        }
    }

    @PutMapping("/updatePassword")
    public void updatePassword(@RequestBody UserForm userForm){
        var updateUser = findUser(userForm);
        if(userForm.getPassword().equals(updateUser.getPassword())) {
            userService.updatePassword(userForm);
        }
    }


}