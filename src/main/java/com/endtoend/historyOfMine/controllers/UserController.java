package com.endtoend.historyOfMine.controllers;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.models.User;
import com.endtoend.historyOfMine.websecurity.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
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