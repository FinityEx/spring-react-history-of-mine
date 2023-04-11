package com.endtoend.bfit.controllers;

import com.endtoend.bfit.forms.UserDTO;
import com.endtoend.bfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UsersController {

    @Autowired
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public HttpStatus createUser(@RequestBody UserDTO userDTO){
        if(userService.createUser(userDTO) != null) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping
    public HttpStatus findUser(@RequestBody UserDTO userDTO){
      return userService.getUser(userDTO) == null ?
              HttpStatus.NOT_FOUND : HttpStatus.FOUND;
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody UserDTO userDTO) {
        if(userService.deleteUser(userDTO)) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping
    public HttpStatus updatePassword(@RequestBody UserDTO userDTO) {
        if (userService.updatePassword(userDTO)) {
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }


}