package com.endtoend.historyOfMine.controllers;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.websecurity.AuthService;
import com.endtoend.historyOfMine.websecurity.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String loginTemplate() {
        return "login";
    }


    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserForm.AuthenticationForm
                                                                    authenticationForm){
        return ResponseEntity.ok(authService.authenticate(authenticationForm));
    }
}