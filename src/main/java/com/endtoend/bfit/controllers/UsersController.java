package com.endtoend.bfit.controllers;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import com.endtoend.bfit.service.UserService;
import com.endtoend.bfit.websecurity.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserForm userForm){
        return ResponseEntity.ok(userService.createUser(userForm));
    }

    @GetMapping
    public User findUser(@RequestParam String username){
        return userService.getUser(username);
    }

    @GetMapping("/hello")
    public String hello(){return "Hello from secured endpoint";}

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody UserForm userForm) {
        if(userService.deleteUser(userForm)) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping
    public HttpStatus updatePassword(@RequestBody UserForm userForm) {
        if (userService.updatePassword(userForm)) {
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }


}