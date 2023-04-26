package com.endtoend.bfit.controllers;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.websecurity.AuthService;
import com.endtoend.bfit.websecurity.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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